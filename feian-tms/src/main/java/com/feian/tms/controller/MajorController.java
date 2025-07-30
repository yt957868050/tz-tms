package com.feian.tms.controller;

import com.feian.common.utils.PageUtils;
import com.feian.tms.dto.request.IdsDeleteRequest;
import com.feian.tms.dto.request.IdsRequest;
import com.github.pagehelper.PageInfo;
import com.feian.tms.common.R;
import com.feian.tms.common.PageRequest;
import com.feian.tms.domain.Major;
import com.feian.tms.dto.query.MajorQuery;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.MajorRequest;
import com.feian.tms.dto.response.MajorResponse;
import com.feian.tms.excel.MajorExcel;
import com.feian.tms.service.MajorService;
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
 * 专业管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/major")
@RequiredArgsConstructor
@Tag(name = "专业管理", description = "专业管理相关接口")
public class MajorController {
    
    private final MajorService majorService;

    /**
     * 查询专业管理列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询专业管理列表", description = "根据查询条件分页查询专业列表")
    public R<PageInfo<MajorResponse>> list(@RequestBody PageRequest<MajorQuery> pageRequest) {
        // 启动分页
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        MajorQuery query = pageRequest.getQuery();
        if (query == null) {
            query = new MajorQuery();
        }
        
        // 构建查询条件
        var queryWrapper = majorService.lambdaQuery()
                .like(query.getMajorName() != null, Major::getMajorName, query.getMajorName())
                .like(query.getMajorCode() != null, Major::getMajorCode, query.getMajorCode())
                .eq(query.getMajorType() != null, Major::getMajorType, query.getMajorType())
                .eq(query.getStatus() != null, Major::getStatus, query.getStatus())
                .orderByAsc(Major::getOrderNum)
                .orderByDesc(Major::getCreateTime);
        
        List<Major> list = queryWrapper.list();
        
        // 转换为响应对象
        var responseList = list.stream()
                .map(entity -> {
                    MajorResponse response = new MajorResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        // 返回分页信息
        return R.success(new PageInfo<>(responseList));
    }

    /**
     * 获取专业管理详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取专业详细信息", description = "根据ID获取专业详细信息")
    public R<MajorResponse> detail(@Valid @RequestBody IdRequest request) {
        Major entity = majorService.getById(request.getId());
        if (entity == null) {
            return R.fail("专业信息不存在");
        }
        
        MajorResponse response = new MajorResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增专业管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增专业", description = "创建新的专业信息")
    public R<MajorResponse> create( @RequestBody MajorRequest request) {
        Major entity = new Major();
        BeanUtils.copyProperties(request, entity);
        entity.setMajorType(String.valueOf(entity.getOrderNum()));
        boolean result = majorService.save(entity);
        if (result) {
            MajorResponse response = new MajorResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增专业失败");
    }

    /**
     * 修改专业管理
     */
    @PostMapping("/update")
    @Operation(summary = "修改专业", description = "更新现有专业信息")
    public R<MajorResponse> update(@Valid @RequestBody MajorRequest request) {
        if (request.getMajorId() == null) {
            return R.fail("专业ID不能为空");
        }
        
        Major entity = new Major();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = majorService.updateById(entity);
        if (result) {
            MajorResponse response = new MajorResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新专业失败");
    }

    /**
     * 删除专业管理
     */
    @PostMapping("/delete")
    @Operation(summary = "删除专业", description = "根据ID删除专业信息")
    public R<Void> delete(@Valid @RequestBody IdsDeleteRequest idsDeleteRequest) {
        boolean result = majorService.removeByIds(idsDeleteRequest.getIdList());
        if (result) {
            return R.success();
        }
        return R.fail("删除专业失败");
    }

    /**
     * 导出专业管理列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出专业列表", description = "根据查询条件导出专业列表到Excel")
    public void export(HttpServletResponse response, @RequestBody IdsRequest idsRequest) {
        // 根据id查询所有数据（不分页）
        List<Major> list = majorService.listByIds(idsRequest.getIdList());
//        var queryWrapper = majorService.lambdaQuery()
//                .like(query.getMajorName() != null, Major::getMajorName, query.getMajorName())
//                .like(query.getMajorCode() != null, Major::getMajorCode, query.getMajorCode())
//                .eq(query.getMajorType() != null, Major::getMajorType, query.getMajorType())
//                .eq(query.getStatus() != null, Major::getStatus, query.getStatus())
//                .orderByAsc(Major::getOrderNum)
//                .orderByDesc(Major::getCreateTime);
//
//        List<Major> list = queryWrapper.list();
        
        // 转换为导出对象
        List<MajorExcel> excelList = list.stream()
                .map(entity -> {
                    MajorExcel excel = new MajorExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换状态显示文本
                    excel.setStatusName("0".equals(entity.getStatus()) ? "正常" : "停用");
                    // 转换专业类型显示文本
                    excel.setMajorTypeName("1".equals(entity.getMajorType()) ? "空勤" : "地勤");
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "专业管理", "专业列表", MajorExcel.class, excelList);
    }
}