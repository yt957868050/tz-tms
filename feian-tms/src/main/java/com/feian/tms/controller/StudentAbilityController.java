package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.StudentAbility;
import com.feian.tms.dto.query.StudentAbilityQuery;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.StudentAbilityRequest;
import com.feian.tms.dto.response.StudentAbilityResponse;
import com.feian.tms.excel.StudentAbilityExcel;
import com.feian.tms.service.StudentAbilityService;
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
 * 学员学习能力管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/studentAbility")
@RequiredArgsConstructor
@Tag(name = "学员学习能力管理", description = "学员学习能力管理相关接口")
public class StudentAbilityController {
    
    private final StudentAbilityService studentAbilityService;

    /**
     * 查询学员学习能力列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询学员学习能力列表", description = "根据查询条件分页查询学员学习能力列表")
    public R<Page<StudentAbilityResponse>> list(@RequestBody StudentAbilityQuery query) {
        Page<StudentAbility> page = new Page<>(query.getPageNum(), query.getPageSize());
        
        // 构建查询条件
        var queryWrapper = studentAbilityService.lambdaQuery()
                .eq(query.getStudentId() != null, StudentAbility::getStudentId, query.getStudentId())
                .eq(query.getMachineTypeId() != null, StudentAbility::getMachineTypeId, query.getMachineTypeId())
                .eq(query.getMajorId() != null, StudentAbility::getMajorId, query.getMajorId())
                .eq(query.getTrainingAbilityId() != null, StudentAbility::getTrainingAbilityId, query.getTrainingAbilityId())
                .eq(query.getCoursewareId() != null, StudentAbility::getCoursewareId, query.getCoursewareId())
                .eq(query.getLearningProgress() != null, StudentAbility::getLearningProgress, query.getLearningProgress())
                .ge(query.getLearningStartTime() != null, StudentAbility::getLearningStartTime, query.getLearningStartTime())
                .le(query.getLearningEndTime() != null, StudentAbility::getLearningEndTime, query.getLearningEndTime())
                .eq(query.getQualificationStatus() != null, StudentAbility::getQualificationStatus, query.getQualificationStatus())
                .eq(query.getStatus() != null, StudentAbility::getStatus, query.getStatus())
                .orderByDesc(StudentAbility::getCreateTime);
        
        Page<StudentAbility> result = queryWrapper.page(page);
        
        // 转换为响应对象
        Page<StudentAbilityResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    StudentAbilityResponse response = new StudentAbilityResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
    }

    /**
     * 获取学员学习能力详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取学员学习能力详细信息", description = "根据ID获取学员学习能力详细信息")
    public R<StudentAbilityResponse> detail(@Valid @RequestBody IdRequest request) {
        StudentAbility entity = studentAbilityService.getById(request.getId());
        if (entity == null) {
            return R.fail("学员学习能力信息不存在");
        }
        
        StudentAbilityResponse response = new StudentAbilityResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增学员学习能力
     */
    @PostMapping("/create")
    @Operation(summary = "新增学员学习能力", description = "创建新的学员学习能力信息")
    public R<StudentAbilityResponse> create(@Valid @RequestBody StudentAbilityRequest request) {
        StudentAbility entity = new StudentAbility();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = studentAbilityService.save(entity);
        if (result) {
            StudentAbilityResponse response = new StudentAbilityResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增学员学习能力失败");
    }

    /**
     * 修改学员学习能力
     */
    @PostMapping("/update")
    @Operation(summary = "修改学员学习能力", description = "更新现有学员学习能力信息")
    public R<StudentAbilityResponse> update(@Valid @RequestBody StudentAbilityRequest request) {
        if (request.getStudentAbilityId() == null) {
            return R.fail("学员学习能力ID不能为空");
        }
        
        StudentAbility entity = new StudentAbility();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = studentAbilityService.updateById(entity);
        if (result) {
            StudentAbilityResponse response = new StudentAbilityResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新学员学习能力失败");
    }

    /**
     * 删除学员学习能力
     */
    @PostMapping("/delete")
    @Operation(summary = "删除学员学习能力", description = "根据ID删除学员学习能力信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = studentAbilityService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除学员学习能力失败");
    }

    /**
     * 导出学员学习能力列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出学员学习能力列表", description = "根据查询条件导出学员学习能力列表到Excel")
    public void export(HttpServletResponse response, @RequestBody StudentAbilityQuery query) {
        // 查询所有数据（不分页）
        var queryWrapper = studentAbilityService.lambdaQuery()
                .eq(query.getStudentId() != null, StudentAbility::getStudentId, query.getStudentId())
                .eq(query.getMachineTypeId() != null, StudentAbility::getMachineTypeId, query.getMachineTypeId())
                .eq(query.getMajorId() != null, StudentAbility::getMajorId, query.getMajorId())
                .eq(query.getTrainingAbilityId() != null, StudentAbility::getTrainingAbilityId, query.getTrainingAbilityId())
                .eq(query.getCoursewareId() != null, StudentAbility::getCoursewareId, query.getCoursewareId())
                .eq(query.getLearningProgress() != null, StudentAbility::getLearningProgress, query.getLearningProgress())
                .ge(query.getLearningStartTime() != null, StudentAbility::getLearningStartTime, query.getLearningStartTime())
                .le(query.getLearningEndTime() != null, StudentAbility::getLearningEndTime, query.getLearningEndTime())
                .eq(query.getQualificationStatus() != null, StudentAbility::getQualificationStatus, query.getQualificationStatus())
                .eq(query.getStatus() != null, StudentAbility::getStatus, query.getStatus())
                .orderByDesc(StudentAbility::getCreateTime);
        
        List<StudentAbility> list = queryWrapper.list();
        
        // 转换为导出对象
        List<StudentAbilityExcel> excelList = list.stream()
                .map(entity -> {
                    StudentAbilityExcel excel = new StudentAbilityExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setLearningProgressName(convertLearningProgress(entity.getLearningProgress()));
                    excel.setQualificationStatusName(convertQualificationStatus(entity.getQualificationStatus()));
                    excel.setStatusName("0".equals(entity.getStatus()) ? "正常" : "停用");
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "学员学习能力管理", "学员学习能力列表", StudentAbilityExcel.class, excelList);
    }

    /**
     * 转换学习进度枚举
     */
    private String convertLearningProgress(String learningProgress) {
        if (learningProgress == null) return "";
        return switch (learningProgress) {
            case "0" -> "未开始";
            case "1" -> "学习中";
            case "2" -> "已完成";
            default -> "";
        };
    }

    /**
     * 转换资质状态枚举
     */
    private String convertQualificationStatus(String qualificationStatus) {
        if (qualificationStatus == null) return "";
        return switch (qualificationStatus) {
            case "0" -> "未获得";
            case "1" -> "已获得";
            case "2" -> "已过期";
            default -> "";
        };
    }
}