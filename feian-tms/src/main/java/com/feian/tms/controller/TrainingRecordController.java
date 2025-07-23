package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.TrainingRecord;
import com.feian.tms.dto.query.TrainingRecordQuery;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.TrainingRecordRequest;
import com.feian.tms.dto.response.TrainingRecordResponse;
import com.feian.tms.excel.TrainingRecordExcel;
import com.feian.tms.service.TrainingRecordService;
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
 * 培训记录管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/trainingRecord")
@RequiredArgsConstructor
@Tag(name = "培训记录管理", description = "培训记录管理相关接口")
public class TrainingRecordController {
    
    private final TrainingRecordService trainingRecordService;

    /**
     * 查询培训记录列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询培训记录列表", description = "根据查询条件分页查询培训记录列表")
    public R<Page<TrainingRecordResponse>> list(@RequestBody TrainingRecordQuery query) {
        Page<TrainingRecord> page = new Page<>(query.getPageNum(), query.getPageSize());
        
        // 构建查询条件
        var queryWrapper = trainingRecordService.lambdaQuery()
                .eq(query.getStudentId() != null, TrainingRecord::getStudentId, query.getStudentId())
                .eq(query.getTrainingClassId() != null, TrainingRecord::getTrainingClassId, query.getTrainingClassId())
                .eq(query.getCoursewareId() != null, TrainingRecord::getCoursewareId, query.getCoursewareId())
                .eq(query.getInstructorId() != null, TrainingRecord::getInstructorId, query.getInstructorId())
                .ge(query.getTrainingDate() != null, TrainingRecord::getTrainingDate, query.getTrainingDate())
                .eq(query.getTrainingMethod() != null, TrainingRecord::getTrainingMethod, query.getTrainingMethod())
                .eq(query.getAttendanceStatus() != null, TrainingRecord::getAttendanceStatus, query.getAttendanceStatus())
                .eq(query.getTrainingEffect() != null, TrainingRecord::getTrainingEffect, query.getTrainingEffect())
                .orderByDesc(TrainingRecord::getTrainingDate);
        
        Page<TrainingRecord> result = queryWrapper.page(page);
        
        // 转换为响应对象
        Page<TrainingRecordResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    TrainingRecordResponse response = new TrainingRecordResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
    }

    /**
     * 获取培训记录详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取培训记录详细信息", description = "根据ID获取培训记录详细信息")
    public R<TrainingRecordResponse> detail(@Valid @RequestBody IdRequest request) {
        TrainingRecord entity = trainingRecordService.getById(request.getId());
        if (entity == null) {
            return R.fail("培训记录信息不存在");
        }
        
        TrainingRecordResponse response = new TrainingRecordResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增培训记录
     */
    @PostMapping("/create")
    @Operation(summary = "新增培训记录", description = "创建新的培训记录信息")
    public R<TrainingRecordResponse> create(@Valid @RequestBody TrainingRecordRequest request) {
        TrainingRecord entity = new TrainingRecord();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingRecordService.save(entity);
        if (result) {
            TrainingRecordResponse response = new TrainingRecordResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增培训记录失败");
    }

    /**
     * 修改培训记录
     */
    @PostMapping("/update")
    @Operation(summary = "修改培训记录", description = "更新现有培训记录信息")
    public R<TrainingRecordResponse> update(@Valid @RequestBody TrainingRecordRequest request) {
        if (request.getTrainingRecordId() == null) {
            return R.fail("培训记录ID不能为空");
        }
        
        TrainingRecord entity = new TrainingRecord();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingRecordService.updateById(entity);
        if (result) {
            TrainingRecordResponse response = new TrainingRecordResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新培训记录失败");
    }

    /**
     * 删除培训记录
     */
    @PostMapping("/delete")
    @Operation(summary = "删除培训记录", description = "根据ID删除培训记录信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = trainingRecordService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除培训记录失败");
    }

    /**
     * 导出培训记录列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出培训记录列表", description = "根据查询条件导出培训记录列表到Excel")
    public void export(HttpServletResponse response, @RequestBody TrainingRecordQuery query) {
        // 查询所有数据（不分页）
        var queryWrapper = trainingRecordService.lambdaQuery()
                .eq(query.getStudentId() != null, TrainingRecord::getStudentId, query.getStudentId())
                .eq(query.getTrainingClassId() != null, TrainingRecord::getTrainingClassId, query.getTrainingClassId())
                .eq(query.getCoursewareId() != null, TrainingRecord::getCoursewareId, query.getCoursewareId())
                .eq(query.getInstructorId() != null, TrainingRecord::getInstructorId, query.getInstructorId())
                .ge(query.getTrainingDate() != null, TrainingRecord::getTrainingDate, query.getTrainingDate())
                .eq(query.getTrainingMethod() != null, TrainingRecord::getTrainingMethod, query.getTrainingMethod())
                .eq(query.getAttendanceStatus() != null, TrainingRecord::getAttendanceStatus, query.getAttendanceStatus())
                .eq(query.getTrainingEffect() != null, TrainingRecord::getTrainingEffect, query.getTrainingEffect())
                .orderByDesc(TrainingRecord::getTrainingDate);
        
        List<TrainingRecord> list = queryWrapper.list();
        
        // 转换为导出对象
        List<TrainingRecordExcel> excelList = list.stream()
                .map(entity -> {
                    TrainingRecordExcel excel = new TrainingRecordExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setTrainingMethodName(convertTrainingMethod(entity.getTrainingMethod()));
                    excel.setAttendanceStatusName(convertAttendanceStatus(entity.getAttendanceStatus()));
                    excel.setTrainingEffectName(convertTrainingEffect(entity.getTrainingEffect()));
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "培训记录管理", "培训记录列表", TrainingRecordExcel.class, excelList);
    }

    /**
     * 转换培训类型枚举
     */
    private String convertTrainingMethod(String trainingMethod) {
        if (trainingMethod == null) return "";
        return switch (trainingMethod) {
            case "1" -> "理论";
            case "2" -> "实践";
            default -> "";
        };
    }

    /**
     * 转换出勤状态枚举
     */
    private String convertAttendanceStatus(String attendanceStatus) {
        if (attendanceStatus == null) return "";
        return switch (attendanceStatus) {
            case "1" -> "正常";
            case "2" -> "迟到";
            case "3" -> "早退";
            case "4" -> "请假";
            case "5" -> "缺勤";
            default -> "";
        };
    }

    /**
     * 转换培训效果枚举
     */
    private String convertTrainingEffect(String trainingEffect) {
        if (trainingEffect == null) return "";
        return switch (trainingEffect) {
            case "1" -> "优秀";
            case "2" -> "良好";
            case "3" -> "一般";
            case "4" -> "较差";
            default -> "";
        };
    }
}