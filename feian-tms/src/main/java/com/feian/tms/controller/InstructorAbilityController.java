package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.InstructorAbility;
import com.feian.tms.common.PageRequest;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.InstructorAbilityRequest;
import com.feian.tms.dto.response.InstructorAbilityResponse;
import com.feian.tms.excel.InstructorAbilityExcel;
import com.feian.tms.service.InstructorAbilityService;
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
 * 教员授课能力管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/instructorAbility")
@RequiredArgsConstructor
@Tag(name = "教员授课能力管理", description = "教员授课能力管理相关接口")
public class InstructorAbilityController {
    
    private final InstructorAbilityService instructorAbilityService;

    /**
     * 查询教员授课能力列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询教员授课能力列表", description = "根据查询条件分页查询教员授课能力列表")
    public R<Page<InstructorAbilityResponse>> list(@RequestBody PageRequest<InstructorAbilityRequest> pageRequest) {
        Page<InstructorAbility> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        InstructorAbilityRequest query = pageRequest.getQuery();
        if (query == null) {
            query = new InstructorAbilityRequest();
        }
        // 构建查询条件
        var queryWrapper = instructorAbilityService.lambdaQuery()
                .eq(query.getInstructorId() != null, InstructorAbility::getInstructorId, query.getInstructorId())
                .eq(query.getMachineTypeId() != null, InstructorAbility::getMachineTypeId, query.getMachineTypeId())
                .eq(query.getMajorId() != null, InstructorAbility::getMajorId, query.getMajorId())
                .eq(query.getTrainingTypeId() != null, InstructorAbility::getTrainingTypeId, query.getTrainingTypeId())
                .eq(query.getCoursewareId() != null, InstructorAbility::getCoursewareId, query.getCoursewareId())
                .eq(query.getAbilityLevel() != null, InstructorAbility::getAbilityLevel, query.getAbilityLevel())
                .ge(query.getQualificationDate() != null, InstructorAbility::getQualificationDate, query.getQualificationDate())
                .le(query.getValidUntil() != null, InstructorAbility::getValidUntil, query.getValidUntil())
                .eq(query.getIsPrimary() != null, InstructorAbility::getIsPrimary, query.getIsPrimary())
                .eq(query.getStatus() != null, InstructorAbility::getStatus, query.getStatus())
                .orderByDesc(InstructorAbility::getCreateTime);
        
        Page<InstructorAbility> result = queryWrapper.page(page);
        
        // 转换为响应对象
        Page<InstructorAbilityResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    InstructorAbilityResponse response = new InstructorAbilityResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
    }

    /**
     * 获取教员授课能力详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取教员授课能力详细信息", description = "根据ID获取教员授课能力详细信息")
    public R<InstructorAbilityResponse> detail(@Valid @RequestBody IdRequest request) {
        InstructorAbility entity = instructorAbilityService.getById(request.getId());
        if (entity == null) {
            return R.fail("教员授课能力信息不存在");
        }
        
        InstructorAbilityResponse response = new InstructorAbilityResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增教员授课能力
     */
    @PostMapping("/create")
    @Operation(summary = "新增教员授课能力", description = "创建新的教员授课能力信息")
    public R<InstructorAbilityResponse> create(@Valid @RequestBody InstructorAbilityRequest request) {
        InstructorAbility entity = new InstructorAbility();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = instructorAbilityService.save(entity);
        if (result) {
            InstructorAbilityResponse response = new InstructorAbilityResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增教员授课能力失败");
    }

    /**
     * 修改教员授课能力
     */
    @PostMapping("/update")
    @Operation(summary = "修改教员授课能力", description = "更新现有教员授课能力信息")
    public R<InstructorAbilityResponse> update(@Valid @RequestBody InstructorAbilityRequest request) {
        if (request.getInstructorAbilityId() == null) {
            return R.fail("教员授课能力ID不能为空");
        }
        
        InstructorAbility entity = new InstructorAbility();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = instructorAbilityService.updateById(entity);
        if (result) {
            InstructorAbilityResponse response = new InstructorAbilityResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新教员授课能力失败");
    }

    /**
     * 删除教员授课能力
     */
    @PostMapping("/delete")
    @Operation(summary = "删除教员授课能力", description = "根据ID删除教员授课能力信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = instructorAbilityService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除教员授课能力失败");
    }

    /**
     * 导出教员授课能力列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出教员授课能力列表", description = "根据查询条件导出教员授课能力列表到Excel")
    public void export(HttpServletResponse response, @RequestBody InstructorAbilityRequest query) {
        // 查询所有数据（不分页）
        var queryWrapper = instructorAbilityService.lambdaQuery()
                .eq(query.getInstructorId() != null, InstructorAbility::getInstructorId, query.getInstructorId())
                .eq(query.getMachineTypeId() != null, InstructorAbility::getMachineTypeId, query.getMachineTypeId())
                .eq(query.getMajorId() != null, InstructorAbility::getMajorId, query.getMajorId())
                .eq(query.getTrainingTypeId() != null, InstructorAbility::getTrainingTypeId, query.getTrainingTypeId())
                .eq(query.getCoursewareId() != null, InstructorAbility::getCoursewareId, query.getCoursewareId())
                .eq(query.getAbilityLevel() != null, InstructorAbility::getAbilityLevel, query.getAbilityLevel())
                .ge(query.getQualificationDate() != null, InstructorAbility::getQualificationDate, query.getQualificationDate())
                .le(query.getValidUntil() != null, InstructorAbility::getValidUntil, query.getValidUntil())
                .eq(query.getIsPrimary() != null, InstructorAbility::getIsPrimary, query.getIsPrimary())
                .eq(query.getStatus() != null, InstructorAbility::getStatus, query.getStatus())
                .orderByDesc(InstructorAbility::getCreateTime);
        
        List<InstructorAbility> list = queryWrapper.list();
        
        // 转换为导出对象
        List<InstructorAbilityExcel> excelList = list.stream()
                .map(entity -> {
                    InstructorAbilityExcel excel = new InstructorAbilityExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setAbilityLevelName(convertAbilityLevel(entity.getAbilityLevel()));
                    excel.setIsPrimaryName("1".equals(entity.getIsPrimary()) ? "是" : "否");
                    excel.setStatusName("0".equals(entity.getStatus()) ? "正常" : "停用");
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "教员授课能力管理", "教员授课能力列表", InstructorAbilityExcel.class, excelList);
    }

    /**
     * 转换授课资质等级枚举
     */
    private String convertAbilityLevel(String abilityLevel) {
        if (abilityLevel == null) return "";
        return switch (abilityLevel) {
            case "1" -> "初级";
            case "2" -> "中级";
            case "3" -> "高级";
            case "4" -> "专家";
            default -> "";
        };
    }
}