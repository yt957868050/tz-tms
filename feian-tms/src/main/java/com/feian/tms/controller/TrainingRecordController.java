package com.feian.tms.controller;

import com.feian.common.utils.PageUtils;
import com.feian.tms.domain.TrainingClass;
import com.feian.tms.dto.request.IdsRequest;
import com.feian.tms.service.*;
import com.github.pagehelper.PageInfo;
import com.feian.tms.common.R;
import com.feian.tms.domain.TrainingRecord;
import com.feian.tms.common.PageRequest;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.TrainingRecordRequest;
import com.feian.tms.dto.response.TrainingRecordResponse;
import com.feian.tms.excel.TrainingRecordExcel;
import com.feian.tms.utils.EasyExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.ArrayList;
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
    private final StudentService studentService;
    private final TrainingClassService trainingClassService;
    private final CoursewareService coursewareService;
    private final InstructorService instructorService;
    private final MachineTypeService machineTypeService;
    private final MajorService majorService;
    private final TrainingAbilityService trainingAbilityService;
    private final ClassStudentService classStudentService;
    private final TrainingPlanService trainingPlanService;

    /**
     * 查询培训记录列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询培训记录列表", description = "根据查询条件分页查询培训记录列表")
    public R<PageInfo<TrainingRecordResponse>> list(@RequestBody PageRequest<TrainingRecordRequest> pageRequest) {
        // 启动分页
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        TrainingRecordRequest query = pageRequest.getQuery();
        if (query == null) {
            query = new TrainingRecordRequest();
        }
        // 构建查询条件
        var queryWrapper = trainingRecordService.lambdaQuery()
                .eq(query.getStudentId() != null, TrainingRecord::getStudentId, query.getStudentId())
                .eq(query.getTrainingClassId() != null, TrainingRecord::getTrainingClassId, query.getTrainingClassId())
                .eq(query.getMachineTypeId() != null, TrainingRecord::getMachineTypeId, query.getMachineTypeId())
                .eq(query.getMajorId() != null, TrainingRecord::getMajorId, query.getMajorId())
                .ge(query.getTrainingAbilityId() != null, TrainingRecord::getTrainingAbilityId, query.getTrainingAbilityId())
                .orderByDesc(TrainingRecord::getTrainingRecordId);
        
        List<TrainingRecord> list = queryWrapper.list();
        PageInfo<TrainingRecord> pageInfo = new PageInfo<>(list);
        // 转换为响应对象并填充关联字段
        var responseList = list.stream()
                .map(entity -> {
                    TrainingRecordResponse response = new TrainingRecordResponse();
                    BeanUtils.copyProperties(entity, response);
                    
                    // 填充关联字段
                    if (entity.getStudentId() != null) {
                        var student = studentService.getById(entity.getStudentId());
                        if (student != null) {
                            response.setStudentId(entity.getStudentId());
                            response.setStudentName(student.getStudentName());
                            response.setStudentCode(student.getStudentCode());
                        }
                    }
                    
                    if (entity.getTrainingClassId() != null) {
                        var trainingClass = trainingClassService.getById(entity.getTrainingClassId());
                        if (trainingClass != null) {
                            response.setClassName(trainingClass.getClassName());
                        }
                    }
                    
                    if (entity.getMachineTypeId() != null) {
                        var machineType = machineTypeService.getById(entity.getMachineTypeId());
                        if (machineType != null) {
                            response.setMachineTypeName(machineType.getMachineTypeName());
                        }
                    }
                    
                    if (entity.getMajorId() != null) {
                        var major = majorService.getById(entity.getMajorId());
                        if (major != null) {
                            response.setMajorName(major.getMajorName());
                        }
                    }
                    if (entity.getTrainingAbilityId() != null) {
                        var trainingAbility = trainingAbilityService.getById(entity.getTrainingAbilityId());
                        if (trainingAbility != null) {
                            response.setAbilityName(trainingAbility.getAbilityName());
                        }
                    }
                    Long classId=classStudentService.getClassIdByStudent(entity.getStudentId());
                    Long planId=trainingPlanService.getPlanIdByClass(classId);
                    response.setTotalHour(trainingPlanService.getTotalHoursById(planId));
                    response.setTheoryHour(trainingPlanService.getTheoryHoursById(planId));
                    response.setPracticeHour(trainingPlanService.getPracticeHoursById(planId));
                    
                    return response;
                })
                .toList();
        // 创建新的PageInfo并保留原有分页信息
        PageInfo<TrainingRecordResponse> result = new PageInfo<>(responseList);
        BeanUtils.copyProperties(pageInfo, result, "list");
        // 返回分页信息
        return R.success(result);
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
        
        // 填充关联字段
        if (entity.getStudentId() != null) {
            var student = studentService.getById(entity.getStudentId());
            if (student != null) {
                response.setStudentName(student.getStudentName());
                response.setStudentCode(student.getStudentCode());
            }
        }
        
        if (entity.getTrainingClassId() != null) {
            var trainingClass = trainingClassService.getById(entity.getTrainingClassId());
            if (trainingClass != null) {
                response.setClassName(trainingClass.getClassName());
            }
        }

        if (entity.getMachineTypeId() != null) {
            var machineType = machineTypeService.getById(entity.getMachineTypeId());
            if (machineType != null) {
                response.setMachineTypeName(machineType.getMachineTypeName());
            }
        }

        if (entity.getMajorId() != null) {
            var major = majorService.getById(entity.getMajorId());
            if (major != null) {
                response.setMajorName(major.getMajorName());
            }
        }
        if (entity.getTrainingAbilityId() != null) {
            var trainingAbility = trainingAbilityService.getById(entity.getTrainingAbilityId());
            if (trainingAbility != null) {
                response.setAbilityName(trainingAbility.getAbilityName());
            }
        }
        Long classId=classStudentService.getClassIdByStudent(entity.getStudentId());
        Long planId=trainingPlanService.getPlanIdByClass(classId);
        response.setTotalHour(trainingPlanService.getTotalHoursById(planId));
        response.setTheoryHour(trainingPlanService.getTheoryHoursById(planId));
        response.setPracticeHour(trainingPlanService.getPracticeHoursById(planId));
        
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
    public R<Void> delete(@Valid @RequestBody IdsRequest idsRequest) {
        boolean result = trainingRecordService.removeByIds(idsRequest.getIdList());
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
    public void export(HttpServletResponse response, @RequestBody IdsRequest idsRequest) {
        List<TrainingRecord> list;
        // 1. 获取原始的 id 对象
        Object id = idsRequest.getId();
        // 2. 创建一个 Long 列表用于安全存储 ID
        List<Long> idList = new ArrayList<>();
        // 3. 根据 id 的类型进行不同的处理
        if (id instanceof List) {
            // 如果 id 是一个列表，遍历它并安全地转换为 Long
            List<?> rawList = (List<?>) id;
            for (Object obj : rawList) {
                if (obj instanceof Number) {
                    // 安全地将每个数字元素转换为 Long
                    idList.add(((Number) obj).longValue());
                }
                // 如果列表里有非数字元素，这里会忽略它们
            }
        } else if (id instanceof Number) {
            // 如果 id 是一个单独的数字
            idList.add(((Number) id).longValue());
        }
        if(idList.size() == 1 && idList.get(0) == 0L){
            list =trainingRecordService.trainingRecordList();
        }else {
            // 根据ID查询所有数据（不分页）
            list =trainingRecordService.listByIds(idsRequest.getIdList());
        }
//        var queryWrapper = trainingRecordService.lambdaQuery()
//                .eq(query.getStudentId() != null, TrainingRecord::getStudentId, query.getStudentId())
//                .eq(query.getTrainingClassId() != null, TrainingRecord::getTrainingClassId, query.getTrainingClassId())
//                .eq(query.getCoursewareId() != null, TrainingRecord::getCoursewareId, query.getCoursewareId())
//                .eq(query.getInstructorId() != null, TrainingRecord::getInstructorId, query.getInstructorId())
//                .ge(query.getTrainingDate() != null, TrainingRecord::getTrainingDate, query.getTrainingDate())
//                .eq(query.getTrainingMethod() != null, TrainingRecord::getTrainingMethod, query.getTrainingMethod())
//                .eq(query.getAttendanceStatus() != null, TrainingRecord::getAttendanceStatus, query.getAttendanceStatus())
//                .eq(query.getTrainingEffect() != null, TrainingRecord::getTrainingEffect, query.getTrainingEffect())
//                .orderByDesc(TrainingRecord::getTrainingDate);
//
//        List<TrainingRecord> list = queryWrapper.list();
        
        // 转换为导出对象
        List<TrainingRecordExcel> excelList = list.stream()
                .map(entity -> {
                    TrainingRecordExcel excel = new TrainingRecordExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
//                    excel.setTrainingMethodName(convertTrainingMethod(entity.getTrainingMethod()));
//                    excel.setAttendanceStatusName(convertAttendanceStatus(entity.getAttendanceStatus()));
//                    excel.setTrainingEffectName(convertTrainingEffect(entity.getTrainingEffect()));
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