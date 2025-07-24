package com.feian.tms.controller;

import com.feian.common.utils.PageUtils;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.tms.domain.CoursewareFile;
import com.feian.tms.dto.query.CoursewareFileQuery;
import com.github.pagehelper.PageInfo;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.CoursewareFileRequest;
import com.feian.tms.dto.response.CoursewareFileResponse;
import com.feian.tms.excel.CoursewareFileExcel;
import com.feian.tms.service.CoursewareFileService;
import com.feian.tms.utils.EasyExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 课件文件管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/coursewareFile")
@RequiredArgsConstructor
@Tag(name = "课件文件管理", description = "课件文件管理相关接口")
public class CoursewareFileController {
    
    private final CoursewareFileService coursewareFileService;

    /**
     * 查询课件文件管理列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询课件文件管理列表", description = "根据查询条件分页查询课件文件列表")
    public R<PageInfo<CoursewareFileResponse>> list(@RequestBody PageRequest<CoursewareFileQuery> pageRequest) {
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        CoursewareFileQuery query = pageRequest.getQuery();
        if (query == null) {
            query = new CoursewareFileQuery();
        }
        
        // 构建查询条件
        var queryWrapper = coursewareFileService.lambdaQuery()
                .eq(query.getCoursewareId() != null, CoursewareFile::getCoursewareId, query.getCoursewareId())
                .like(query.getFileName() != null, CoursewareFile::getFileName, query.getFileName())
                .eq(query.getFileType() != null, CoursewareFile::getFileType, query.getFileType())
                .like(query.getFileExtension() != null, CoursewareFile::getFileExtension, query.getFileExtension())
                .eq(query.getStatus() != null, CoursewareFile::getStatus, query.getStatus())
                .orderByAsc(CoursewareFile::getOrderNum)
                .orderByDesc(CoursewareFile::getCreateTime);
        
        List<CoursewareFile> list = queryWrapper.list();
        
        // 转换为响应对象
        var responseList = list.stream()
                .map(entity -> {
                    CoursewareFileResponse response = new CoursewareFileResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        return R.success(new PageInfo<>(responseList));
    }

    /**
     * 获取课件文件管理详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取课件文件详细信息", description = "根据ID获取课件文件详细信息")
    public R<CoursewareFileResponse> detail(@Valid @RequestBody IdRequest request) {
        CoursewareFile entity = coursewareFileService.getById(request.getId());
        if (entity == null) {
            return R.fail("课件文件信息不存在");
        }
        
        CoursewareFileResponse response = new CoursewareFileResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增课件文件管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增课件文件", description = "创建新的课件文件信息")
    public R<CoursewareFileResponse> create(@Valid @RequestBody CoursewareFileRequest request) {
        CoursewareFile entity = new CoursewareFile();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = coursewareFileService.save(entity);
        if (result) {
            CoursewareFileResponse response = new CoursewareFileResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增课件文件失败");
    }

    /**
     * 修改课件文件管理
     */
    @PostMapping("/update")
    @Operation(summary = "修改课件文件", description = "更新现有课件文件信息")
    public R<CoursewareFileResponse> update(@Valid @RequestBody CoursewareFileRequest request) {
        if (request.getCoursewareFileId() == null) {
            return R.fail("课件文件ID不能为空");
        }
        
        CoursewareFile entity = new CoursewareFile();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = coursewareFileService.updateById(entity);
        if (result) {
            CoursewareFileResponse response = new CoursewareFileResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新课件文件失败");
    }

    /**
     * 删除课件文件管理
     */
    @PostMapping("/delete")
    @Operation(summary = "删除课件文件", description = "根据ID删除课件文件信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = coursewareFileService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除课件文件失败");
    }

    /**
     * 导出课件文件管理列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出课件文件列表", description = "根据查询条件导出课件文件列表到Excel")
    public void export(HttpServletResponse response, @RequestBody CoursewareFileQuery query) {
        // 查询所有数据（不分页）
        var queryWrapper = coursewareFileService.lambdaQuery()
                .eq(query.getCoursewareId() != null, CoursewareFile::getCoursewareId, query.getCoursewareId())
                .like(query.getFileName() != null, CoursewareFile::getFileName, query.getFileName())
                .eq(query.getFileType() != null, CoursewareFile::getFileType, query.getFileType())
                .like(query.getFileExtension() != null, CoursewareFile::getFileExtension, query.getFileExtension())
                .eq(query.getStatus() != null, CoursewareFile::getStatus, query.getStatus())
                .orderByAsc(CoursewareFile::getOrderNum)
                .orderByDesc(CoursewareFile::getCreateTime);
        
        List<CoursewareFile> list = queryWrapper.list();
        
        // 转换为导出对象
        List<CoursewareFileExcel> excelList = list.stream()
                .map(entity -> {
                    CoursewareFileExcel excel = new CoursewareFileExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setFileTypeName(convertFileType(entity.getFileType()));
                    excel.setStatusName("0".equals(entity.getStatus()) ? "正常" : "停用");
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "课件文件管理", "课件文件列表", CoursewareFileExcel.class, excelList);
    }

    /**
     * 转换文件类型枚举
     */
    private String convertFileType(String fileType) {
        if (fileType == null) return "";
        return switch (fileType) {
            case "1" -> "教材";
            case "2" -> "教案";
            case "3" -> "PPT";
            case "4" -> "CBT";
            case "5" -> "工卡";
            case "6" -> "其他";
            default -> "";
        };
    }
}