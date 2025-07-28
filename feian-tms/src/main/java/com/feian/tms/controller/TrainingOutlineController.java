package com.feian.tms.controller;

import com.feian.common.utils.PageUtils;
import com.feian.tms.dto.request.IdsDeleteRequest;
import com.github.pagehelper.PageInfo;
import com.feian.tms.common.R;
import com.feian.tms.domain.TrainingOutline;
import com.feian.tms.common.PageRequest;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.TrainingOutlineRequest;
import com.feian.tms.dto.response.TrainingOutlineResponse;
import com.feian.tms.excel.TrainingOutlineExcel;
import com.feian.tms.service.TrainingOutlineService;
import com.feian.tms.utils.EasyExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 培训大纲管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/outline")
@RequiredArgsConstructor
@Tag(name = "培训大纲管理", description = "培训大纲管理相关接口")
public class TrainingOutlineController {
    
    private final TrainingOutlineService trainingOutlineService;

    /**
     * 查询培训大纲列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询培训大纲列表", description = "根据查询条件分页查询培训大纲列表")
    public R<PageInfo<TrainingOutlineResponse>> list(@RequestBody PageRequest<TrainingOutlineRequest> pageRequest) {
        // 启动分页
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        TrainingOutlineRequest query = pageRequest.getQuery();
        if (query == null) {
            query = new TrainingOutlineRequest();
        }
        
        List<TrainingOutlineResponse> list = trainingOutlineService.selectTrainingOutlineList(query);
        
        // 返回分页信息
        return R.success(new PageInfo<>(list));
    }

    /**
     * 获取培训大纲详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取培训大纲详细信息", description = "根据ID获取培训大纲详细信息")
    public R<TrainingOutlineResponse> detail(@Valid @RequestBody IdRequest request) {
        TrainingOutlineResponse response = trainingOutlineService.selectTrainingOutlineById(request.getId());
        if (response == null) {
            return R.fail("培训大纲信息不存在");
        }
        return R.success(response);
    }

    /**
     * 新增培训大纲
     */
    @PostMapping("/create")
    @Operation(summary = "新增培训大纲", description = "创建新的培训大纲信息")
    public R<TrainingOutlineResponse> create(@Valid @RequestBody TrainingOutlineRequest request) {
        // 检查唯一性
        if (!trainingOutlineService.checkUniqueness(request.getMachineTypeId(), 
                request.getMajorId(), request.getTrainingAbilityId(), null)) {
            return R.fail("该机型、专业、培训能力组合的大纲已存在");
        }
        
        TrainingOutlineResponse response = trainingOutlineService.insertTrainingOutline(request);
        if (response != null) {
            return R.success(response);
        }
        return R.fail("新增培训大纲失败");
    }

    /**
     * 修改培训大纲
     */
    @PostMapping("/update")
    @Operation(summary = "修改培训大纲", description = "更新现有培训大纲信息")
    public R<TrainingOutlineResponse> update(@Valid @RequestBody TrainingOutlineRequest request) {
        if (request.getOutlineId() == null) {
            return R.fail("大纲ID不能为空");
        }
        
        // 检查唯一性
        if (!trainingOutlineService.checkUniqueness(request.getMachineTypeId(), 
                request.getMajorId(), request.getTrainingAbilityId(), request.getOutlineId())) {
            return R.fail("该机型、专业、培训能力组合的大纲已存在");
        }
        
        TrainingOutlineResponse response = trainingOutlineService.updateTrainingOutline(request);
        if (response != null) {
            return R.success(response);
        }
        return R.fail("更新培训大纲失败");
    }

    /**
     * 删除培训大纲
     */
    @PostMapping("/delete")
    @Operation(summary = "删除培训大纲", description = "根据ID删除培训大纲信息")
    public R<Void> delete(@Valid @RequestBody IdsDeleteRequest idsDeleteRequest) {
        boolean result = trainingOutlineService.deleteBatch(idsDeleteRequest.getIdList());
        if (result) {
            return R.success();
        }
        return R.fail("删除培训大纲失败");
    }

    /**
     * 导出培训大纲列表
     */
    @PostMapping("/export")
    @Operation(summary = "导出培训大纲列表", description = "根据查询条件导出培训大纲列表到Excel")
    public void export(HttpServletResponse response, @RequestBody TrainingOutlineRequest query) {
        List<TrainingOutlineResponse> list = trainingOutlineService.exportTrainingOutlineList(query);
        
        // 转换为导出对象
        List<TrainingOutlineExcel> excelList = list.stream()
                .map(entity -> {
                    TrainingOutlineExcel excel = new TrainingOutlineExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setStatusName("0".equals(entity.getStatus()) ? "正常" : "停用");
                    excel.setMachineTypeName(entity.getMachineTypeName());
                    excel.setMajorName(entity.getMajorName());
                    excel.setTrainingAbilityName(entity.getTrainingAbilityName());
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "培训大纲管理", "培训大纲列表", TrainingOutlineExcel.class, excelList);
    }

    /**
     * 上传培训规范文件
     */
    @PostMapping("/upload/regulation")
    @Operation(summary = "上传培训规范文件", description = "上传培训规范文件")
    public R<String> uploadRegulationFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.fail("上传文件不能为空");
        }
        
        // 检查文件类型
        String fileName = file.getOriginalFilename();
        if (fileName == null || (!fileName.toLowerCase().endsWith(".pdf") && 
                !fileName.toLowerCase().endsWith(".doc") && 
                !fileName.toLowerCase().endsWith(".docx"))) {
            return R.fail("只支持PDF、DOC、DOCX格式文件");
        }
        
        // TODO: 实现文件上传逻辑，保存到文件服务器或本地
        // 这里返回模拟的文件路径
        String filePath = "/uploads/regulation/" + System.currentTimeMillis() + "_" + fileName;
        
        return R.success(filePath);
    }

    /**
     * 上传大纲文件
     */
    @PostMapping("/upload/outline")
    @Operation(summary = "上传大纲文件", description = "上传大纲文件")
    public R<String> uploadOutlineFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.fail("上传文件不能为空");
        }
        
        // 检查文件类型
        String fileName = file.getOriginalFilename();
        if (fileName == null || (!fileName.toLowerCase().endsWith(".pdf") && 
                !fileName.toLowerCase().endsWith(".doc") && 
                !fileName.toLowerCase().endsWith(".docx"))) {
            return R.fail("只支持PDF、DOC、DOCX格式文件");
        }
        
        // TODO: 实现文件上传逻辑，保存到文件服务器或本地
        // 这里返回模拟的文件路径
        String filePath = "/uploads/outline/" + System.currentTimeMillis() + "_" + fileName;
        
        return R.success(filePath);
    }

    /**
     * 预览培训规范文件
     */
    @PostMapping("/preview/regulation")
    @Operation(summary = "预览培训规范文件", description = "预览培训规范文件")
    public R<String> previewRegulationFile(@Valid @RequestBody IdRequest request) {
        TrainingOutlineResponse outline = trainingOutlineService.selectTrainingOutlineById(request.getId());
        if (outline == null || outline.getRegulationFilePath() == null) {
            return R.fail("文件不存在");
        }
        
        // TODO: 实现文件预览逻辑，返回预览URL或Base64
        String previewUrl = "/preview" + outline.getRegulationFilePath();
        
        return R.success(previewUrl);
    }

    /**
     * 预览大纲文件
     */
    @PostMapping("/preview/outline")
    @Operation(summary = "预览大纲文件", description = "预览大纲文件")
    public R<String> previewOutlineFile(@Valid @RequestBody IdRequest request) {
        TrainingOutlineResponse outline = trainingOutlineService.selectTrainingOutlineById(request.getId());
        if (outline == null || outline.getOutlineFilePath() == null) {
            return R.fail("文件不存在");
        }
        
        // TODO: 实现文件预览逻辑，返回预览URL或Base64
        String previewUrl = "/preview" + outline.getOutlineFilePath();
        
        return R.success(previewUrl);
    }

    /**
     * 下载培训规范文件
     */
    @PostMapping("/download/regulation")
    @Operation(summary = "下载培训规范文件", description = "下载培训规范文件")
    public ResponseEntity<Resource> downloadRegulationFile(@Valid @RequestBody IdRequest request) {
        try {
            TrainingOutlineResponse outline = trainingOutlineService.downloadRegulationFile(request.getId());
            if (outline == null || outline.getRegulationFilePath() == null) {
                return ResponseEntity.notFound().build();
            }
            
            // TODO: 实现文件下载逻辑，从文件服务器或本地读取文件
            // 这里返回模拟的响应
            String fileName = URLEncoder.encode(outline.getRegulationFileName(), StandardCharsets.UTF_8);
            
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 下载大纲文件
     */
    @PostMapping("/download/outline")
    @Operation(summary = "下载大纲文件", description = "下载大纲文件")
    public ResponseEntity<Resource> downloadOutlineFile(@Valid @RequestBody IdRequest request) {
        try {
            TrainingOutlineResponse outline = trainingOutlineService.downloadOutlineFile(request.getId());
            if (outline == null || outline.getOutlineFilePath() == null) {
                return ResponseEntity.notFound().build();
            }
            
            // TODO: 实现文件下载逻辑，从文件服务器或本地读取文件
            // 这里返回模拟的响应
            String fileName = URLEncoder.encode(outline.getOutlineFileName(), StandardCharsets.UTF_8);
            
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 根据机型、专业、培训能力查询有效大纲
     */
    @PostMapping("/active")
    @Operation(summary = "查询有效大纲", description = "根据机型、专业、培训能力查询有效大纲")
    public R<TrainingOutlineResponse> getActiveOutline(@RequestBody TrainingOutlineRequest request) {
        if (request.getMachineTypeId() == null || request.getMajorId() == null || request.getTrainingAbilityId() == null) {
            return R.fail("机型、专业、培训能力不能为空");
        }
        
        TrainingOutlineResponse response = trainingOutlineService.getActiveOutline(
                request.getMachineTypeId(), request.getMajorId(), request.getTrainingAbilityId());
        
        if (response != null) {
            return R.success(response);
        }
        return R.fail("未找到有效的培训大纲");
    }

    /**
     * 检查唯一性
     */
    @PostMapping("/check-uniqueness")
    @Operation(summary = "检查唯一性", description = "检查机型、专业、培训能力组合的唯一性")
    public R<Boolean> checkUniqueness(@RequestBody TrainingOutlineRequest request) {
        boolean isUnique = trainingOutlineService.checkUniqueness(
                request.getMachineTypeId(), request.getMajorId(), 
                request.getTrainingAbilityId(), request.getOutlineId());
        
        return R.success(isUnique);
    }
}