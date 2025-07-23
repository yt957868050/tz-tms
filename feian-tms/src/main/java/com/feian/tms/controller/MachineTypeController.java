package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.MachineType;
import com.feian.tms.dto.query.MachineTypeQuery;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.MachineTypeRequest;
import com.feian.tms.dto.response.MachineTypeResponse;
import com.feian.tms.excel.MachineTypeExcel;
import com.feian.tms.service.MachineTypeService;
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
 * 机型管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/machineType")
@RequiredArgsConstructor
@Tag(name = "机型管理", description = "机型管理相关接口")
public class MachineTypeController {
    
    private final MachineTypeService machineTypeService;

    /**
     * 查询机型管理列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询机型管理列表", description = "根据查询条件分页查询机型列表")
    public R<Page<MachineTypeResponse>> list(@RequestBody MachineTypeQuery query) {
        Page<MachineType> page = new Page<>(query.getPageNum(), query.getPageSize());
        
        // 构建查询条件
        var queryWrapper = machineTypeService.lambdaQuery()
                .like(query.getMachineTypeName() != null, MachineType::getMachineTypeName, query.getMachineTypeName())
                .like(query.getMachineTypeCode() != null, MachineType::getMachineTypeCode, query.getMachineTypeCode())
                .eq(query.getStatus() != null, MachineType::getStatus, query.getStatus())
                .orderByAsc(MachineType::getOrderNum)
                .orderByDesc(MachineType::getCreateTime);
        
        Page<MachineType> result = queryWrapper.page(page);
        
        // 转换为响应对象
        Page<MachineTypeResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    MachineTypeResponse response = new MachineTypeResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
    }

    /**
     * 获取机型管理详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取机型详细信息", description = "根据ID获取机型详细信息")
    public R<MachineTypeResponse> detail(@Valid @RequestBody IdRequest request) {
        MachineType entity = machineTypeService.getById(request.getId());
        if (entity == null) {
            return R.fail("机型信息不存在");
        }
        
        MachineTypeResponse response = new MachineTypeResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增机型管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增机型", description = "创建新的机型信息")
    public R<MachineTypeResponse> create(@Valid @RequestBody MachineTypeRequest request) {
        MachineType entity = new MachineType();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = machineTypeService.save(entity);
        if (result) {
            MachineTypeResponse response = new MachineTypeResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增机型失败");
    }

    /**
     * 修改机型管理
     */
    @PostMapping("/update")
    @Operation(summary = "修改机型", description = "更新现有机型信息")
    public R<MachineTypeResponse> update(@Valid @RequestBody MachineTypeRequest request) {
        if (request.getMachineTypeId() == null) {
            return R.fail("机型ID不能为空");
        }
        
        MachineType entity = new MachineType();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = machineTypeService.updateById(entity);
        if (result) {
            MachineTypeResponse response = new MachineTypeResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新机型失败");
    }

    /**
     * 删除机型管理
     */
    @PostMapping("/delete")
    @Operation(summary = "删除机型", description = "根据ID删除机型信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = machineTypeService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除机型失败");
    }

    /**
     * 导出机型管理列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出机型列表", description = "根据查询条件导出机型列表到Excel")
    public void export(HttpServletResponse response, @RequestBody MachineTypeQuery query) {
        // 查询所有数据（不分页）
        var queryWrapper = machineTypeService.lambdaQuery()
                .like(query.getMachineTypeName() != null, MachineType::getMachineTypeName, query.getMachineTypeName())
                .like(query.getMachineTypeCode() != null, MachineType::getMachineTypeCode, query.getMachineTypeCode())
                .eq(query.getStatus() != null, MachineType::getStatus, query.getStatus())
                .orderByAsc(MachineType::getOrderNum)
                .orderByDesc(MachineType::getCreateTime);
        
        List<MachineType> list = queryWrapper.list();
        
        // 转换为导出对象
        List<MachineTypeExcel> excelList = list.stream()
                .map(entity -> {
                    MachineTypeExcel excel = new MachineTypeExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换状态显示文本
                    excel.setStatusName("0".equals(entity.getStatus()) ? "正常" : "停用");
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "机型管理", "机型列表", MachineTypeExcel.class, excelList);
    }
}