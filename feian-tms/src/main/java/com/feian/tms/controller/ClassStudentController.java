package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.ClassStudent;
import com.feian.tms.dto.query.ClassStudentQuery;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.ClassStudentRequest;
import com.feian.tms.dto.response.ClassStudentResponse;
import com.feian.tms.excel.ClassStudentExcel;
import com.feian.tms.service.ClassStudentService;
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
 * 班次学员关联管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/classStudent")
@RequiredArgsConstructor
@Tag(name = "班次学员关联管理", description = "班次学员关联管理相关接口")
public class ClassStudentController {
    
    private final ClassStudentService classStudentService;

    /**
     * 查询班次学员关联列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询班次学员关联列表", description = "根据查询条件分页查询班次学员关联列表")
    public R<Page<ClassStudentResponse>> list(@RequestBody ClassStudentQuery query) {
        Page<ClassStudent> page = new Page<>(query.getPageNum(), query.getPageSize());
        
        // 构建查询条件
        var queryWrapper = classStudentService.lambdaQuery()
                .eq(query.getTrainingClassId() != null, ClassStudent::getTrainingClassId, query.getTrainingClassId())
                .eq(query.getStudentId() != null, ClassStudent::getStudentId, query.getStudentId())
                .ge(query.getEnrollTime() != null, ClassStudent::getEnrollTime, query.getEnrollTime())
                .eq(query.getStudentStatus() != null, ClassStudent::getStudentStatus, query.getStudentStatus())
                .orderByDesc(ClassStudent::getEnrollTime);
        
        Page<ClassStudent> result = queryWrapper.page(page);
        
        // 转换为响应对象
        Page<ClassStudentResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    ClassStudentResponse response = new ClassStudentResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
    }

    /**
     * 获取班次学员关联详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取班次学员关联详细信息", description = "根据ID获取班次学员关联详细信息")
    public R<ClassStudentResponse> detail(@Valid @RequestBody IdRequest request) {
        ClassStudent entity = classStudentService.getById(request.getId());
        if (entity == null) {
            return R.fail("班次学员关联信息不存在");
        }
        
        ClassStudentResponse response = new ClassStudentResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增班次学员关联
     */
    @PostMapping("/create")
    @Operation(summary = "新增班次学员关联", description = "创建新的班次学员关联信息")
    public R<ClassStudentResponse> create(@Valid @RequestBody ClassStudentRequest request) {
        ClassStudent entity = new ClassStudent();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = classStudentService.save(entity);
        if (result) {
            ClassStudentResponse response = new ClassStudentResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增班次学员关联失败");
    }

    /**
     * 修改班次学员关联
     */
    @PostMapping("/update")
    @Operation(summary = "修改班次学员关联", description = "更新现有班次学员关联信息")
    public R<ClassStudentResponse> update(@Valid @RequestBody ClassStudentRequest request) {
        if (request.getClassStudentId() == null) {
            return R.fail("班次学员关联ID不能为空");
        }
        
        ClassStudent entity = new ClassStudent();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = classStudentService.updateById(entity);
        if (result) {
            ClassStudentResponse response = new ClassStudentResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新班次学员关联失败");
    }

    /**
     * 删除班次学员关联
     */
    @PostMapping("/delete")
    @Operation(summary = "删除班次学员关联", description = "根据ID删除班次学员关联信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = classStudentService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除班次学员关联失败");
    }

    /**
     * 导出班次学员关联列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出班次学员关联列表", description = "根据查询条件导出班次学员关联列表到Excel")
    public void export(HttpServletResponse response, @RequestBody ClassStudentQuery query) {
        // 查询所有数据（不分页）
        var queryWrapper = classStudentService.lambdaQuery()
                .eq(query.getTrainingClassId() != null, ClassStudent::getTrainingClassId, query.getTrainingClassId())
                .eq(query.getStudentId() != null, ClassStudent::getStudentId, query.getStudentId())
                .ge(query.getEnrollTime() != null, ClassStudent::getEnrollTime, query.getEnrollTime())
                .eq(query.getStudentStatus() != null, ClassStudent::getStudentStatus, query.getStudentStatus())
                .orderByDesc(ClassStudent::getEnrollTime);
        
        List<ClassStudent> list = queryWrapper.list();
        
        // 转换为导出对象
        List<ClassStudentExcel> excelList = list.stream()
                .map(entity -> {
                    ClassStudentExcel excel = new ClassStudentExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换学员状态显示文本
                    excel.setStudentStatusName(convertStudentStatus(entity.getStudentStatus()));
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "班次学员关联管理", "班次学员关联列表", ClassStudentExcel.class, excelList);
    }

    /**
     * 转换学员状态枚举
     */
    private String convertStudentStatus(String studentStatus) {
        if (studentStatus == null) return "";
        return switch (studentStatus) {
            case "0" -> "待开班";
            case "1" -> "培训中";
            case "2" -> "已结业";
            case "3" -> "已退班";
            case "4" -> "请假中";
            default -> "";
        };
    }
}