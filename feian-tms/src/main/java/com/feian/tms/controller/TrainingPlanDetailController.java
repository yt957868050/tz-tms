package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.TrainingPlanDetail;
import com.feian.tms.common.PageRequest;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.TrainingPlanDetailRequest;
import com.feian.tms.dto.response.TrainingPlanDetailResponse;
import com.feian.tms.excel.TrainingPlanDetailExcel;
import com.feian.tms.service.TrainingPlanDetailService;
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
 * 培训计划明细管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/trainingPlanDetail")
@RequiredArgsConstructor
@Tag(name = "培训计划明细管理", description = "培训计划明细管理相关接口")
public class TrainingPlanDetailController {
    
    private final TrainingPlanDetailService trainingPlanDetailService;

    /**
     * 查询培训计划明细管理列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询培训计划明细管理列表", description = "根据查询条件分页查询培训计划明细列表")
    public R<Page<TrainingPlanDetailResponse>> list(@RequestBody PageRequest<TrainingPlanDetailRequest> pageRequest) {
        Page<TrainingPlanDetail> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        TrainingPlanDetailRequest query = pageRequest.getQuery();
        if (query == null) {
            query = new TrainingPlanDetailRequest();
        }
        // 构建查询条件
        var queryWrapper = trainingPlanDetailService.lambdaQuery()
                .eq(query.getTrainingPlanId() != null, TrainingPlanDetail::getTrainingPlanId, query.getTrainingPlanId())
                .eq(query.getCoursewareId() != null, TrainingPlanDetail::getCoursewareId, query.getCoursewareId())
                .eq(query.getIsRequired() != null, TrainingPlanDetail::getIsRequired, query.getIsRequired())
                .orderByAsc(TrainingPlanDetail::getCourseOrder)
                .orderByDesc(TrainingPlanDetail::getCreateTime);
        
        Page<TrainingPlanDetail> result = queryWrapper.page(page);
        
        // 转换为响应对象
        Page<TrainingPlanDetailResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    TrainingPlanDetailResponse response = new TrainingPlanDetailResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
    }

    /**
     * 获取培训计划明细管理详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取培训计划明细详细信息", description = "根据ID获取培训计划明细详细信息")
    public R<TrainingPlanDetailResponse> detail(@Valid @RequestBody IdRequest request) {
        TrainingPlanDetail entity = trainingPlanDetailService.getById(request.getId());
        if (entity == null) {
            return R.fail("培训计划明细信息不存在");
        }
        
        TrainingPlanDetailResponse response = new TrainingPlanDetailResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增培训计划明细管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增培训计划明细", description = "创建新的培训计划明细信息")
    public R<TrainingPlanDetailResponse> create(@Valid @RequestBody TrainingPlanDetailRequest request) {
        TrainingPlanDetail entity = new TrainingPlanDetail();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingPlanDetailService.save(entity);
        if (result) {
            TrainingPlanDetailResponse response = new TrainingPlanDetailResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增培训计划明细失败");
    }

    /**
     * 修改培训计划明细管理
     */
    @PostMapping("/update")
    @Operation(summary = "修改培训计划明细", description = "更新现有培训计划明细信息")
    public R<TrainingPlanDetailResponse> update(@Valid @RequestBody TrainingPlanDetailRequest request) {
        if (request.getTrainingPlanDetailId() == null) {
            return R.fail("培训计划明细ID不能为空");
        }
        
        TrainingPlanDetail entity = new TrainingPlanDetail();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingPlanDetailService.updateById(entity);
        if (result) {
            TrainingPlanDetailResponse response = new TrainingPlanDetailResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新培训计划明细失败");
    }

    /**
     * 删除培训计划明细管理
     */
    @PostMapping("/delete")
    @Operation(summary = "删除培训计划明细", description = "根据ID删除培训计划明细信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = trainingPlanDetailService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除培训计划明细失败");
    }

    /**
     * 导出培训计划明细管理列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出培训计划明细列表", description = "根据查询条件导出培训计划明细列表到Excel")
    public void export(HttpServletResponse response, @RequestBody TrainingPlanDetailRequest query) {
        // 查询所有数据（不分页）
        var queryWrapper = trainingPlanDetailService.lambdaQuery()
                .eq(query.getTrainingPlanId() != null, TrainingPlanDetail::getTrainingPlanId, query.getTrainingPlanId())
                .eq(query.getCoursewareId() != null, TrainingPlanDetail::getCoursewareId, query.getCoursewareId())
                .eq(query.getIsRequired() != null, TrainingPlanDetail::getIsRequired, query.getIsRequired())
                .orderByAsc(TrainingPlanDetail::getCourseOrder)
                .orderByDesc(TrainingPlanDetail::getCreateTime);
        
        List<TrainingPlanDetail> list = queryWrapper.list();
        
        // 转换为导出对象
        List<TrainingPlanDetailExcel> excelList = list.stream()
                .map(entity -> {
                    TrainingPlanDetailExcel excel = new TrainingPlanDetailExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setIsRequiredName("1".equals(entity.getIsRequired()) ? "必修" : "选修");
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "培训计划明细管理", "培训计划明细列表", TrainingPlanDetailExcel.class, excelList);
    }
}