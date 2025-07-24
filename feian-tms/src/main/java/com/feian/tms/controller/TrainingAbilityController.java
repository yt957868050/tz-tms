package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.tms.domain.TrainingAbility;
import com.feian.tms.dto.query.TrainingAbilityQuery;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.TrainingAbilityRequest;
import com.feian.tms.dto.response.TrainingAbilityResponse;
import com.feian.tms.excel.TrainingAbilityExcel;
import com.feian.tms.service.TrainingAbilityService;
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
 * 培训能力管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/training-ability")
@RequiredArgsConstructor
@Tag(name = "培训能力管理", description = "培训能力管理相关接口")
public class TrainingAbilityController {
    
    private final TrainingAbilityService trainingAbilityService;

    /**
     * 查询培训能力列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询培训能力列表", description = "根据查询条件分页查询培训能力列表")
    public R<Page<TrainingAbilityResponse>> list(@RequestBody PageRequest<TrainingAbilityRequest> pageQuery) {
        Page<TrainingAbility> page = new Page<>(pageQuery.getPageNum() ,
                pageQuery.getPageSize());

        TrainingAbilityRequest query = pageQuery.getQuery();
        // 构建查询条件
        var queryWrapper = trainingAbilityService.lambdaQuery()
                .like(query.getAbilityName() != null, TrainingAbility::getAbilityName, query.getAbilityName())
                .like(query.getAbilityCode() != null, TrainingAbility::getAbilityCode, query.getAbilityCode())
                .eq(query.getStatus() != null, TrainingAbility::getStatus, query.getStatus())
                .orderByAsc(TrainingAbility::getTrainingAbilityId);
        
        Page<TrainingAbility> result = queryWrapper.page(page);
        
        // 转换为响应对象  
        Page<TrainingAbilityResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    TrainingAbilityResponse response = new TrainingAbilityResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
    }

    /**
     * 获取培训能力管理详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取培训能力详细信息", description = "根据ID获取培训能力详细信息")
    public R<TrainingAbilityResponse> detail(@Valid @RequestBody IdRequest request) {
        TrainingAbility entity = trainingAbilityService.getById(request.getId());
        if (entity == null) {
            return R.fail("培训能力信息不存在");
        }
        
        TrainingAbilityResponse response = new TrainingAbilityResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增培训能力管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增培训能力", description = "创建新的培训能力信息")
    public R<TrainingAbilityResponse> create(@Valid @RequestBody TrainingAbilityRequest request) {
        TrainingAbility entity = new TrainingAbility();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingAbilityService.save(entity);
        if (result) {
            TrainingAbilityResponse response = new TrainingAbilityResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增培训能力失败");
    }

    /**
     * 修改培训能力管理
     */
    @PostMapping("/update")
    @Operation(summary = "修改培训能力", description = "更新现有培训能力信息")
    public R<TrainingAbilityResponse> update(@Valid @RequestBody TrainingAbilityRequest request) {
        if (request.getTrainingAbilityId() == null) {
            return R.fail("培训能力ID不能为空");
        }
        
        TrainingAbility entity = new TrainingAbility();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingAbilityService.updateById(entity);
        if (result) {
            TrainingAbilityResponse response = new TrainingAbilityResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新培训能力失败");
    }

    /**
     * 删除培训能力管理
     */
    @PostMapping("/delete")
    @Operation(summary = "删除培训能力", description = "根据ID删除培训能力信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = trainingAbilityService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除培训能力失败");
    }

    /**
     * 导出培训能力管理列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出培训能力列表", description = "根据查询条件导出培训能力列表到Excel")
    public void export(HttpServletResponse response, @RequestBody TrainingAbilityQuery query) {
        // 查询所有数据（不分页）
        var queryWrapper = trainingAbilityService.lambdaQuery()
                .like(query.getTrainingAbilityName() != null, TrainingAbility::getAbilityName, query.getTrainingAbilityName())
                .like(query.getTrainingAbilityCode() != null, TrainingAbility::getAbilityCode, query.getTrainingAbilityCode())
                .eq(query.getAbilityType() != null, TrainingAbility::getTrainingAbilityId, query.getAbilityType())
                .eq(query.getStatus() != null, TrainingAbility::getStatus, query.getStatus())
                .orderByDesc(TrainingAbility::getCreateTime);
        
        List<TrainingAbility> list = queryWrapper.list();
        
        // 转换为导出对象
        List<TrainingAbilityExcel> excelList = list.stream()
                .map(entity -> {
                    TrainingAbilityExcel excel = new TrainingAbilityExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setStatusName("0".equals(entity.getStatus()) ? "正常" : "停用");
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "培训能力管理", "培训能力列表", TrainingAbilityExcel.class, excelList);
    }

    /**
     * 转换培训能力类型枚举
     */
    private String convertAbilityType(String abilityType) {
        if (abilityType == null) return "";
        return switch (abilityType) {
            case "1" -> "培训";
            case "2" -> "复训";
            case "3" -> "恢复考试";
            default -> "";
        };
    }
}