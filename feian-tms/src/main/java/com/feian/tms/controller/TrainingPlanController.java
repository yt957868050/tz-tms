package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.TrainingPlan;
import com.feian.tms.dto.query.TrainingPlanQuery;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.TrainingPlanRequest;
import com.feian.tms.dto.response.TrainingPlanResponse;
import com.feian.tms.excel.TrainingPlanExcel;
import com.feian.tms.service.TrainingPlanService;
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
 * 培训计划管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/trainingPlan")
@RequiredArgsConstructor
@Tag(name = "培训计划管理", description = "培训计划管理相关接口")
public class TrainingPlanController {
    
    private final TrainingPlanService trainingPlanService;

    /**
     * 查询培训计划管理列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询培训计划管理列表", description = "根据查询条件分页查询培训计划列表")
    public R<Page<TrainingPlanResponse>> list(@RequestBody TrainingPlanQuery query) {
        Page<TrainingPlan> page = new Page<>(query.getPageNum(), query.getPageSize());
        
        // 构建查询条件
        var queryWrapper = trainingPlanService.lambdaQuery()
                .like(query.getTrainingPlanName() != null, TrainingPlan::getTrainingPlanName, query.getTrainingPlanName())
                .like(query.getTrainingPlanCode() != null, TrainingPlan::getTrainingPlanCode, query.getTrainingPlanCode())
                .eq(query.getMachineTypeId() != null, TrainingPlan::getMachineTypeId, query.getMachineTypeId())
                .eq(query.getMajorId() != null, TrainingPlan::getMajorId, query.getMajorId())
                .eq(query.getTrainingAbilityId() != null, TrainingPlan::getTrainingAbilityId, query.getTrainingAbilityId())
                .ge(query.getPlanStartTime() != null, TrainingPlan::getPlanStartTime, query.getPlanStartTime())
                .le(query.getPlanEndTime() != null, TrainingPlan::getPlanEndTime, query.getPlanEndTime())
                .eq(query.getStatus() != null, TrainingPlan::getStatus, query.getStatus())
                .orderByDesc(TrainingPlan::getCreateTime);
        
        Page<TrainingPlan> result = queryWrapper.page(page);
        
        // 转换为响应对象
        Page<TrainingPlanResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    TrainingPlanResponse response = new TrainingPlanResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
    }

    /**
     * 获取培训计划管理详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取培训计划详细信息", description = "根据ID获取培训计划详细信息")
    public R<TrainingPlanResponse> detail(@Valid @RequestBody IdRequest request) {
        TrainingPlan entity = trainingPlanService.getById(request.getId());
        if (entity == null) {
            return R.fail("培训计划信息不存在");
        }
        
        TrainingPlanResponse response = new TrainingPlanResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增培训计划管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增培训计划", description = "创建新的培训计划信息")
    public R<TrainingPlanResponse> create(@Valid @RequestBody TrainingPlanRequest request) {
        TrainingPlan entity = new TrainingPlan();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingPlanService.save(entity);
        if (result) {
            TrainingPlanResponse response = new TrainingPlanResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增培训计划失败");
    }

    /**
     * 修改培训计划管理
     */
    @PostMapping("/update")
    @Operation(summary = "修改培训计划", description = "更新现有培训计划信息")
    public R<TrainingPlanResponse> update(@Valid @RequestBody TrainingPlanRequest request) {
        if (request.getTrainingPlanId() == null) {
            return R.fail("培训计划ID不能为空");
        }
        
        TrainingPlan entity = new TrainingPlan();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingPlanService.updateById(entity);
        if (result) {
            TrainingPlanResponse response = new TrainingPlanResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新培训计划失败");
    }

    /**
     * 删除培训计划管理
     */
    @PostMapping("/delete")
    @Operation(summary = "删除培训计划", description = "根据ID删除培训计划信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = trainingPlanService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除培训计划失败");
    }

    /**
     * 导出培训计划管理列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出培训计划列表", description = "根据查询条件导出培训计划列表到Excel")
    public void export(HttpServletResponse response, @RequestBody TrainingPlanQuery query) {
        // 查询所有数据（不分页）
        var queryWrapper = trainingPlanService.lambdaQuery()
                .like(query.getTrainingPlanName() != null, TrainingPlan::getTrainingPlanName, query.getTrainingPlanName())
                .like(query.getTrainingPlanCode() != null, TrainingPlan::getTrainingPlanCode, query.getTrainingPlanCode())
                .eq(query.getMachineTypeId() != null, TrainingPlan::getMachineTypeId, query.getMachineTypeId())
                .eq(query.getMajorId() != null, TrainingPlan::getMajorId, query.getMajorId())
                .eq(query.getTrainingAbilityId() != null, TrainingPlan::getTrainingAbilityId, query.getTrainingAbilityId())
                .ge(query.getPlanStartTime() != null, TrainingPlan::getPlanStartTime, query.getPlanStartTime())
                .le(query.getPlanEndTime() != null, TrainingPlan::getPlanEndTime, query.getPlanEndTime())
                .eq(query.getStatus() != null, TrainingPlan::getStatus, query.getStatus())
                .orderByDesc(TrainingPlan::getCreateTime);
        
        List<TrainingPlan> list = queryWrapper.list();
        
        // 转换为导出对象
        List<TrainingPlanExcel> excelList = list.stream()
                .map(entity -> {
                    TrainingPlanExcel excel = new TrainingPlanExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换状态显示文本
                    excel.setStatusName(convertStatus(entity.getStatus()));
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "培训计划管理", "培训计划列表", TrainingPlanExcel.class, excelList);
    }

    /**
     * 转换状态枚举
     */
    private String convertStatus(String status) {
        if (status == null) return "";
        return switch (status) {
            case "0" -> "草稿";
            case "1" -> "待审核";
            case "2" -> "已审核";
            case "3" -> "执行中";
            case "4" -> "已完成";
            case "5" -> "已取消";
            default -> "";
        };
    }
}