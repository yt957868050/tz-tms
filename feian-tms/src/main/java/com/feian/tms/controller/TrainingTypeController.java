package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.TrainingType;
import com.feian.tms.common.PageRequest;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.TrainingTypeRequest;
import com.feian.tms.dto.response.TrainingTypeResponse;
import com.feian.tms.excel.TrainingTypeExcel;
import com.feian.tms.service.TrainingTypeService;
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
 * 培训类型管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/trainingType")
@RequiredArgsConstructor
@Tag(name = "培训类型管理", description = "培训类型管理相关接口")
public class TrainingTypeController {
    
    private final TrainingTypeService trainingTypeService;

    /**
     * 查询培训类型管理列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询培训类型管理列表", description = "根据查询条件分页查询培训类型列表")
    public R<Page<TrainingTypeResponse>> list(@RequestBody PageRequest<TrainingTypeRequest> pageRequest) {
        Page<TrainingType> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        TrainingTypeRequest query = pageRequest.getQuery();
        if (query == null) {
            query = new TrainingTypeRequest();
        }
        // 构建查询条件
        var queryWrapper = trainingTypeService.lambdaQuery()
                .like(query.getTrainingTypeName() != null, TrainingType::getTrainingTypeName, query.getTrainingTypeName())
                .like(query.getTrainingTypeCode() != null, TrainingType::getTrainingTypeCode, query.getTrainingTypeCode())
                .eq(query.getTrainingMethod() != null, TrainingType::getTrainingMethod, query.getTrainingMethod())
                .eq(query.getStatus() != null, TrainingType::getStatus, query.getStatus())
                .orderByAsc(TrainingType::getOrderNum)
                .orderByDesc(TrainingType::getCreateTime);
        
        Page<TrainingType> result = queryWrapper.page(page);
        
        // 转换为响应对象
        Page<TrainingTypeResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    TrainingTypeResponse response = new TrainingTypeResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
    }

    /**
     * 获取培训类型管理详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取培训类型详细信息", description = "根据ID获取培训类型详细信息")
    public R<TrainingTypeResponse> detail(@Valid @RequestBody IdRequest request) {
        TrainingType entity = trainingTypeService.getById(request.getId());
        if (entity == null) {
            return R.fail("培训类型信息不存在");
        }
        
        TrainingTypeResponse response = new TrainingTypeResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增培训类型管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增培训类型", description = "创建新的培训类型信息")
    public R<TrainingTypeResponse> create(@Valid @RequestBody TrainingTypeRequest request) {
        TrainingType entity = new TrainingType();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingTypeService.save(entity);
        if (result) {
            TrainingTypeResponse response = new TrainingTypeResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增培训类型失败");
    }

    /**
     * 修改培训类型管理
     */
    @PostMapping("/update")
    @Operation(summary = "修改培训类型", description = "更新现有培训类型信息")
    public R<TrainingTypeResponse> update(@Valid @RequestBody TrainingTypeRequest request) {
        if (request.getTrainingTypeId() == null) {
            return R.fail("培训类型ID不能为空");
        }
        
        TrainingType entity = new TrainingType();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingTypeService.updateById(entity);
        if (result) {
            TrainingTypeResponse response = new TrainingTypeResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新培训类型失败");
    }

    /**
     * 删除培训类型管理
     */
    @PostMapping("/delete")
    @Operation(summary = "删除培训类型", description = "根据ID删除培训类型信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = trainingTypeService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除培训类型失败");
    }

    /**
     * 导出培训类型管理列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出培训类型列表", description = "根据查询条件导出培训类型列表到Excel")
    public void export(HttpServletResponse response, @RequestBody TrainingTypeRequest query) {
        // 查询所有数据（不分页）
        var queryWrapper = trainingTypeService.lambdaQuery()
                .like(query.getTrainingTypeName() != null, TrainingType::getTrainingTypeName, query.getTrainingTypeName())
                .like(query.getTrainingTypeCode() != null, TrainingType::getTrainingTypeCode, query.getTrainingTypeCode())
                .eq(query.getTrainingMethod() != null, TrainingType::getTrainingMethod, query.getTrainingMethod())
                .eq(query.getStatus() != null, TrainingType::getStatus, query.getStatus())
                .orderByAsc(TrainingType::getOrderNum)
                .orderByDesc(TrainingType::getCreateTime);
        
        List<TrainingType> list = queryWrapper.list();
        
        // 转换为导出对象
        List<TrainingTypeExcel> excelList = list.stream()
                .map(entity -> {
                    TrainingTypeExcel excel = new TrainingTypeExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setTrainingMethodName(convertTrainingMethod(entity.getTrainingMethod()));
                    excel.setStatusName("0".equals(entity.getStatus()) ? "正常" : "停用");
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "培训类型管理", "培训类型列表", TrainingTypeExcel.class, excelList);
    }

    /**
     * 转换培训方式枚举
     */
    private String convertTrainingMethod(String trainingMethod) {
        if (trainingMethod == null) return "";
        return switch (trainingMethod) {
            case "1" -> "理论";
            case "2" -> "实践";
            default -> "";
        };
    }
}