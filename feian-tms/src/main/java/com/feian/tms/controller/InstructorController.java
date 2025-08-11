package com.feian.tms.controller;

import com.feian.common.utils.PageUtils;
import com.feian.tms.dto.request.IdsDeleteRequest;
import com.feian.tms.dto.request.IdsRequest;
import com.github.pagehelper.PageInfo;
import com.feian.tms.common.R;
import com.feian.tms.domain.Instructor;
import com.feian.tms.common.PageRequest;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.InstructorRequest;
import com.feian.tms.dto.response.InstructorResponse;
import com.feian.tms.excel.InstructorExcel;
import com.feian.tms.service.InstructorService;
import com.feian.tms.utils.EasyExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 教员信息管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/instructor")
@RequiredArgsConstructor
@Tag(name = "教员信息管理", description = "教员信息管理相关接口")
public class InstructorController {
    
    private final InstructorService instructorService;

    /**
     * 查询教员信息列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询教员信息列表", description = "根据查询条件分页查询教员信息列表")
    public R<PageInfo<InstructorResponse>> list(@RequestBody PageRequest<InstructorRequest> pageRequest) {
        // 启动分页
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        InstructorRequest query = pageRequest.getQuery();
        if (query == null) {
            query = new InstructorRequest();
        }
        
        // 构建查询条件
        var queryWrapper = instructorService.lambdaQuery()
                .like(query.getInstructorName() != null, Instructor::getInstructorName, query.getInstructorName())
                .like(query.getInstructorCode() != null, Instructor::getInstructorCode, query.getInstructorCode())
                .eq(query.getGender() != null, Instructor::getGender, query.getGender())
                .like(query.getPhoneNumber() != null, Instructor::getPhoneNumber, query.getPhoneNumber())
                .like(query.getDepartment() != null, Instructor::getDepartment, query.getDepartment())
                .eq(query.getInstructorLevel() != null, Instructor::getInstructorLevel, query.getInstructorLevel())
                .eq(query.getEducation() != null, Instructor::getEducation, query.getEducation())
                .like(query.getTechnicalTitle() != null, Instructor::getTechnicalTitle, query.getTechnicalTitle())
                .eq(query.getTeachingStatus() != null, Instructor::getTeachingStatus, query.getTeachingStatus())
                .eq(query.getStatus() != null, Instructor::getStatus, query.getStatus())
                .orderByDesc(Instructor::getCreateTime);
        
        List<Instructor> list = queryWrapper.list();
        PageInfo<Instructor> pageInfo = new PageInfo<>(list);
        // 转换为响应对象
        var responseList = list.stream()
                .map(entity -> {
                    InstructorResponse response = new InstructorResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        // 创建新的PageInfo并保留原有分页信息
        PageInfo<InstructorResponse> result = new PageInfo<>(responseList);
        BeanUtils.copyProperties(pageInfo, result, "list");
        // 返回分页信息
        return R.success(result);
    }

    /**
     * 获取教员信息详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取教员详细信息", description = "根据ID获取教员详细信息")
    public R<InstructorResponse> detail(@Valid @RequestBody IdRequest request) {
        Instructor entity = instructorService.getById(request.getId());
        if (entity == null) {
            return R.fail("教员信息不存在");
        }
        
        InstructorResponse response = new InstructorResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增教员信息
     */
    @PostMapping("/create")
    @Operation(summary = "新增教员", description = "创建新的教员信息")
    public R<InstructorResponse> create(@Valid @RequestBody InstructorRequest request) {
        Instructor entity = new Instructor();
        BeanUtils.copyProperties(request, entity);
        Long UserId=1L;//用户ID不能为空，待定
        entity.setUserId(UserId);
        boolean result = instructorService.save(entity);
        if (result) {
            InstructorResponse response = new InstructorResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增教员失败");
    }

    /**
     * 修改教员信息
     */
    @PostMapping("/update")
    @Operation(summary = "修改教员", description = "更新现有教员信息")
    public R<InstructorResponse> update(@Valid @RequestBody InstructorRequest request) {
        if (request.getInstructorId() == null) {
            return R.fail("教员ID不能为空");
        }
        
        Instructor entity = new Instructor();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = instructorService.updateById(entity);
        if (result) {
            InstructorResponse response = new InstructorResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新教员失败");
    }

    /**
     * 删除教员信息
     */
    @PostMapping("/delete")
    @Operation(summary = "删除教员", description = "根据ID删除教员信息")
    public R<Void> delete(@Valid @RequestBody IdsDeleteRequest idsDeleteRequest) {
        boolean result = instructorService.removeByIds(idsDeleteRequest.getIdList());
        if (result) {
            return R.success();
        }
        return R.fail("删除教员失败");
    }

    /**
     * 导出教员信息列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出教员列表", description = "根据查询条件导出教员列表到Excel")
    public void export(HttpServletResponse response, @RequestBody IdsRequest idsRequest) {
        List<Instructor> list;
        // 1. 获取原始的 id 对象
        Object id = idsRequest.getId();
        // 2. 创建一个 Long 列表用于安全存储 ID
        List<Long> idList = new ArrayList<>();
        // 3. 根据 id 的类型进行不同的处理
        if (id instanceof List) {
            // 如果 id 是一个列表，遍历它并安全地转换为 Long
            List<?> rawList = (List<?>) id;
            for (Object obj : rawList) {
                if (obj instanceof Number) {
                    // 安全地将每个数字元素转换为 Long
                    idList.add(((Number) obj).longValue());
                }
                // 如果列表里有非数字元素，这里会忽略它们
            }
        } else if (id instanceof Number) {
            // 如果 id 是一个单独的数字
            idList.add(((Number) id).longValue());
        }
        if(idList.size() == 1 && idList.get(0) == 0L){
            list =instructorService.instructorList();
        }else {
            // 根据ID查询所有数据（不分页）
             list =instructorService.listByIds(idsRequest.getIdList());
        }

//        var queryWrapper = instructorService.lambdaQuery()
//                .like(query.getInstructorName() != null, Instructor::getInstructorName, query.getInstructorName())
//                .like(query.getInstructorCode() != null, Instructor::getInstructorCode, query.getInstructorCode())
//                .eq(query.getGender() != null, Instructor::getGender, query.getGender())
//                .like(query.getPhoneNumber() != null, Instructor::getPhoneNumber, query.getPhoneNumber())
//                .like(query.getDepartment() != null, Instructor::getDepartment, query.getDepartment())
//                .eq(query.getInstructorLevel() != null, Instructor::getInstructorLevel, query.getInstructorLevel())
//                .eq(query.getEducation() != null, Instructor::getEducation, query.getEducation())
//                .like(query.getTechnicalTitle() != null, Instructor::getTechnicalTitle, query.getTechnicalTitle())
//                .eq(query.getTeachingStatus() != null, Instructor::getTeachingStatus, query.getTeachingStatus())
//                .eq(query.getStatus() != null, Instructor::getStatus, query.getStatus())
//                .orderByDesc(Instructor::getCreateTime);
//
//        List<Instructor> list = queryWrapper.list();
        
        // 转换为导出对象
        List<InstructorExcel> excelList = list.stream()
                .map(entity -> {
                    InstructorExcel excel = new InstructorExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setGenderName(convertGender(entity.getGender()));
                    excel.setInstructorLevelName(convertInstructorLevel(entity.getInstructorLevel()));
                    excel.setEducationName(convertEducation(entity.getEducation()));
                    excel.setTeachingStatusName(convertTeachingStatus(entity.getTeachingStatus()));
                    excel.setStatusName("0".equals(entity.getStatus()) ? "正常" : "停用");
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "教员信息管理", "教员信息列表", InstructorExcel.class, excelList);
    }

    /**
     * 转换性别枚举
     */
    private String convertGender(String gender) {
        if (gender == null) return "";
        return switch (gender) {
            case "0" -> "男";
            case "1" -> "女";
            case "2" -> "未知";
            default -> "";
        };
    }

    /**
     * 转换教员等级枚举
     */
    private String convertInstructorLevel(String instructorLevel) {
        if (instructorLevel == null) return "";
        return switch (instructorLevel) {
            case "1" -> "初级";
            case "2" -> "中级";
            case "3" -> "高级";
            case "4" -> "专家";
            default -> "";
        };
    }

    /**
     * 转换学历枚举
     */
    private String convertEducation(String education) {
        if (education == null) return "";
        return switch (education) {
            case "1" -> "中专";
            case "2" -> "大专";
            case "3" -> "本科";
            case "4" -> "硕士";
            case "5" -> "博士";
            default -> "";
        };
    }

    /**
     * 转换授课状态枚举
     */
    private String convertTeachingStatus(String teachingStatus) {
        if (teachingStatus == null) return "";
        return switch (teachingStatus) {
            case "0" -> "可授课";
            case "1" -> "授课中";
            case "2" -> "暂停授课";
            default -> "";
        };
    }
}