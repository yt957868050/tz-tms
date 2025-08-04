package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.feian.common.utils.PageUtils;
import com.feian.tms.domain.*;
import com.feian.tms.dto.request.IdsDeleteRequest;
import com.github.pagehelper.PageInfo;
import java.time.ZoneId;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.TrainingPlanRequest;
import com.feian.tms.dto.response.TrainingPlanResponse;
import com.feian.tms.dto.response.InstructorResponse;
import com.feian.tms.service.TrainingPlanService;
import com.feian.tms.service.TrainingPlanInstructorService;
import com.feian.tms.service.InstructorService;
import com.feian.tms.service.MachineTypeService;
import com.feian.tms.service.MajorService;
import com.feian.tms.service.TrainingAbilityService;
import com.feian.tms.service.TrainingPlanScheduleService;
import com.feian.tms.service.TrainingClassService;
import com.feian.tms.service.CoursewareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;  
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 培训计划Controller
 * 
 * @author feian
 * @date 2025-01-24
 */
@RestController
@RequestMapping("/api/tms/trainingPlan")
@RequiredArgsConstructor
@Tag(name = "培训计划管理", description = "培训计划管理相关接口")
public class TrainingPlanController {
    
    private final TrainingPlanService trainingPlanService;
    private final TrainingPlanInstructorService trainingPlanInstructorService;
    private final InstructorService instructorService;
    private final MachineTypeService machineTypeService;
    private final MajorService majorService;
    private final TrainingAbilityService trainingAbilityService;
    private final TrainingPlanScheduleService trainingPlanScheduleService;
    private final TrainingClassService trainingClassService;
    private final CoursewareService coursewareService;

    /**
     * 查询培训计划列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询培训计划列表", description = "根据查询条件分页查询培训计划列表")
    public R<PageInfo<TrainingPlanResponse>> list(@RequestBody PageRequest<TrainingPlanRequest> pageRequest) {
        // 启动分页
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        TrainingPlanRequest query = pageRequest.getQuery();
        if (query == null) {
            query = new TrainingPlanRequest();
        }
        
        // 构建查询条件
        var queryWrapper = trainingPlanService.lambdaQuery()
                .like(query.getPlanName() != null, TrainingPlan::getPlanName, query.getPlanName())
                .like(query.getPlanCode() != null, TrainingPlan::getPlanCode, query.getPlanCode())
                .eq(query.getMachineTypeId() != null, TrainingPlan::getMachineTypeId, query.getMachineTypeId())
                .eq(query.getMajorId() != null, TrainingPlan::getMajorId, query.getMajorId())
                .eq(query.getTrainingAbilityId() != null, TrainingPlan::getTrainingAbilityId, query.getTrainingAbilityId())
                .eq(query.getPlanStatus() != null, TrainingPlan::getPlanStatus, query.getPlanStatus())
                .eq(query.getStatus() != null, TrainingPlan::getStatus, query.getStatus())
                .ge(query.getStartDate() != null, TrainingPlan::getStartDate, query.getStartDate())
                .le(query.getEndDate() != null, TrainingPlan::getEndDate, query.getEndDate())
                .orderByDesc(TrainingPlan::getCreateTime);
        
        List<TrainingPlan> list = queryWrapper.list();
        
        // 为每个计划查询关联的教员信息
        var responseList = list.stream()
                .map(entity -> {
                    TrainingPlanResponse response = new TrainingPlanResponse();
                    BeanUtils.copyProperties(entity, response);
                    if(entity.getTrainingClassId()!=null){
                        response.setClassName(trainingPlanService.getClassNameById(entity.getTrainingClassId()));
                    }
                    // 设置关联字段名称
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
                            response.setTrainingAbilityName(trainingAbility.getAbilityName());
                        }
                    }
                    
                    // 设置状态名称
                    response.setPlanStatusName(convertPlanStatus(entity.getPlanStatus()));
                    
                    // 查询关联教员
                    var instructors = trainingPlanInstructorService.lambdaQuery()
                            .eq(TrainingPlanInstructor::getPlanId, entity.getPlanId())
                            .eq(TrainingPlanInstructor::getStatus, "0")
                            .list();
                    
                    if (!instructors.isEmpty()) {
                        var instructorIds = instructors.stream()
                                .map(TrainingPlanInstructor::getInstructorId)
                                .toList();
                        
                        var instructorList = instructorService.lambdaQuery()
                                .in(Instructor::getInstructorId, instructorIds)
                                .eq(Instructor::getStatus, "0")
                                .list();
                        
                        var instructorResponses = instructorList.stream()
                                .map(instructor -> {
                                    InstructorResponse ir = new InstructorResponse();
                                    BeanUtils.copyProperties(instructor, ir);
                                    return ir;
                                })
                                .toList();
                        
                        response.setInstructors(instructorResponses);
                        response.setInstructorCount(instructorResponses.size());
                    } else {
                        response.setInstructorCount(0);
                    }
                    
                    return response;
                })
                .toList();
        
        // 返回分页信息
        return R.success(new PageInfo<>(responseList));
    }

    /**
     * 获取培训计划详细信息
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
        // 设置关联字段名称
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
                response.setTrainingAbilityName(trainingAbility.getAbilityName());
            }
        }

        //查询主教员
        TrainingPlanInstructor chefInstructor = trainingPlanInstructorService.lambdaQuery()
                .eq(TrainingPlanInstructor::getPlanId, entity.getPlanId())
                .eq(TrainingPlanInstructor::getIsChief, "1")
                .one();


        
        // 查询关联教员信息
        var instructors = trainingPlanInstructorService.lambdaQuery()
                .eq(TrainingPlanInstructor::getPlanId, entity.getPlanId())
                .eq(TrainingPlanInstructor::getStatus, "0")
                .eq(TrainingPlanInstructor::getIsChief, "0")
                .list();
        
        if (!instructors.isEmpty()) {
            var instructorIds = instructors.stream()
                    .map(TrainingPlanInstructor::getInstructorId)
                    .toList();
            
            var instructorList = instructorService.lambdaQuery()
                    .in(Instructor::getInstructorId, instructorIds)
                    .eq(Instructor::getStatus, "0")
                    .list();
            
            var instructorResponses = instructorList.stream()
                    .map(instructor -> {
                        InstructorResponse ir = new InstructorResponse();
                        BeanUtils.copyProperties(instructor, ir);
                        return ir;
                    })
                    .toList();
            
            response.setInstructors(instructorResponses);
            response.setTrainingPlanInstructor(chefInstructor);
            response.setInstructorCount(instructorResponses.size());
        }
        
        return R.success(response);
    }

    /**
     * 新增培训计划
     */
    @PostMapping("/create")
    @Operation(summary = "新增培训计划", description = "创建新的培训计划信息")
    public R<TrainingPlanResponse> create(@Valid @RequestBody TrainingPlanRequest request) {
        // 检查计划编号是否重复
        boolean exists = trainingPlanService.lambdaQuery()
                .eq(TrainingPlan::getPlanCode, request.getPlanCode())
                .exists();
        if (exists) {
            return R.fail("计划编号已存在");
        }
        
        TrainingPlan entity = new TrainingPlan();
        BeanUtils.copyProperties(request, entity);
        
        // 设置默认值
        if (entity.getPlanStatus() == null) {
            entity.setPlanStatus("0"); // 默认草稿状态
        }
        if (entity.getScheduleGenerated() == null) {
            entity.setScheduleGenerated("0"); // 默认未生成课表
        }
        
        boolean result = trainingPlanService.save(entity);
        if (result) {
            //保存主责教员
            TrainingPlanInstructor tp = new TrainingPlanInstructor();
            tp.setPlanId(entity.getPlanId());
            tp.setInstructorId(request.getChiefInstructorId());
            tp.setStatus("0");
            tp.setIsChief("1");
            trainingPlanInstructorService.save(tp);

            // 保存教员关联关系
            if (request.getInstructorIds() != null && !request.getInstructorIds().isEmpty()) {
                for (Long instructorId : request.getInstructorIds()) {
                    TrainingPlanInstructor tpi = new TrainingPlanInstructor();
                    tpi.setPlanId(entity.getPlanId());
                    tpi.setInstructorId(instructorId);
//                    tpi.setIsChief(instructorId.equals(request.getChiefInstructorId()) ? "1" : "0");
                    tpi.setStatus("0");
                    trainingPlanInstructorService.save(tpi);
                }
            }

            
            TrainingPlanResponse response = new TrainingPlanResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增培训计划失败");
    }

    /**
     * 修改培训计划
     */
    @PostMapping("/update")
    @Operation(summary = "修改培训计划", description = "更新现有培训计划信息")
    public R<TrainingPlanResponse> update(@Valid @RequestBody TrainingPlanRequest request) {
        if (request.getPlanId() == null) {
            return R.fail("培训计划ID不能为空");
        }

        TrainingPlan entity = new TrainingPlan();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingPlanService.updateById(entity);
        if (result) {
            try{
            // 更新教员关联关系
            if (request.getInstructorIds() != null) {
                // 删除原有关联
                trainingPlanInstructorService.delete(entity.getPlanId());

//                trainingPlanInstructorService.lambdaUpdate()
//                        .eq(TrainingPlanInstructor::getPlanId, entity.getPlanId())
//                        .remove();
                //添加主责教员
                TrainingPlanInstructor tp = new TrainingPlanInstructor();
                tp.setPlanId(entity.getPlanId());
                tp.setInstructorId(request.getChiefInstructorId());
                tp.setStatus("0");
                tp.setIsChief("1");
                trainingPlanInstructorService.save(tp);

                // 添加新的关联
                for (Long instructorId : request.getInstructorIds()) {
                    TrainingPlanInstructor tpi = new TrainingPlanInstructor();
                    tpi.setPlanId(entity.getPlanId());
                    tpi.setInstructorId(instructorId);
//                    tpi.setIsChief(instructorId.equals(request.getChiefInstructorId()) ? "1" : "0");
                    tpi.setStatus("0");
                    trainingPlanInstructorService.save(tpi);
                }
            }} catch (Exception e) {
                throw new RuntimeException(e);
            }


            TrainingPlanResponse response = new TrainingPlanResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新培训计划失败");
    }

    /**
     * 删除培训计划
     */
    @PostMapping("/delete")
    @Operation(summary = "删除培训计划", description = "根据ID删除培训计划信息")
    public R<Void> delete(@Valid @RequestBody IdRequest idRequest) {
        // 删除培训计划
        boolean result = trainingPlanService.removeById(idRequest.getId());
        if (result) {
            // 删除关联的教员关系
            trainingPlanInstructorService.lambdaUpdate()
                    .eq(TrainingPlanInstructor::getPlanId,idRequest.getId())
                    .remove();
            
            return R.success();
        }
        return R.fail("删除培训计划失败");
    }

    /**
     * 自动排课功能
     */
    @PostMapping("/auto-schedule")
    @Operation(summary = "自动排课", description = "根据培训计划自动生成课程安排")
    public R<Map<String, Object>> autoSchedule(@RequestBody TrainingPlanRequest request) {
        try {
            // 1. 创建培训计划
            TrainingPlan trainingPlan = new TrainingPlan();
            BeanUtils.copyProperties(request, trainingPlan);
            
            // 生成计划编号和名称
            String planCode = generatePlanCode(request.getMachineTypeId(), request.getMajorId(), request.getTrainingAbilityId());
            trainingPlan.setPlanCode(planCode);
            trainingPlan.setPlanName(generatePlanName(request.getMachineTypeId(), request.getMajorId(), request.getTrainingAbilityId()));
            trainingPlan.setPlanStatus("0"); // 草稿状态
            trainingPlan.setScheduleGenerated("0"); // 未生成课表
            trainingPlan.setStatus("0"); // 正常状态
            
            // 保存培训计划
            trainingPlanService.save(trainingPlan);
            
            // 2. 生成班次编号（年份+序号）
            String classCode = generateClassCode();
            
            // 创建培训班次
            TrainingClass trainingClass = new TrainingClass();
            trainingClass.setClassCode(classCode);
            trainingClass.setClassName(trainingPlan.getPlanName() + "-" + classCode + "班");
            trainingClass.setTrainingPlanId(trainingPlan.getPlanId());
            trainingClass.setMachineTypeId(request.getMachineTypeId());
            trainingClass.setMajorId(request.getMajorId());
            trainingClass.setTrainingAbilityId(request.getTrainingAbilityId());
            trainingClass.setPlanStartTime(request.getStartDate());
            trainingClass.setStatus("0"); // 待开班
            
            trainingClassService.save(trainingClass);
            
            // 3. 自动生成课程安排
            List<TrainingPlanSchedule> schedules = generateAutoSchedule(trainingPlan, request.getStartDate());
            
            // 批量保存课程安排
            if (!schedules.isEmpty()) {
                trainingPlanScheduleService.saveBatch(schedules);
            }
            
            // 4. 更新培训计划状态
            trainingPlan.setScheduleGenerated("1");
            trainingPlan.setClassScheduleName(trainingClass.getClassName() + "-课程表");
            trainingPlan.setTeachingScheduleName(trainingClass.getClassName() + "-教学进度安排表");
            trainingPlan.setPracticalProjectListName(trainingClass.getClassName() + "-实作项目清单");
            trainingPlanService.updateById(trainingPlan);
            
            // 5. 保存责任教员关系
            if (request.getInstructorIds() != null && !request.getInstructorIds().isEmpty()) {
                for (Long instructorId : request.getInstructorIds()) {
                    TrainingPlanInstructor tpi = new TrainingPlanInstructor();
                    tpi.setPlanId(trainingPlan.getPlanId());
                    tpi.setInstructorId(instructorId);
                    tpi.setIsChief(instructorId.equals(request.getChiefInstructorId()) ? "1" : "0");
                    tpi.setStatus("0");
                    trainingPlanInstructorService.save(tpi);
                }
            }
            
            // 6. 返回生成的表格名称
            Map<String, Object> result = new HashMap<>();
            result.put("planId", trainingPlan.getPlanId());
            result.put("classCode", classCode);
            result.put("classScheduleName", trainingPlan.getClassScheduleName());
            result.put("teachingScheduleName", trainingPlan.getTeachingScheduleName());
            result.put("practicalProjectListName", trainingPlan.getPracticalProjectListName());
            result.put("totalDays", calculateTotalDays(schedules));
            
            return R.success(result);
            
        } catch (Exception e) {
            return R.fail("自动排课失败：" + e.getMessage());
        }
    }
    
    /**
     * 生成课程安排
     */
    private List<TrainingPlanSchedule> generateAutoSchedule(TrainingPlan trainingPlan, Date startDate) {
        List<TrainingPlanSchedule> schedules = new ArrayList<>();
        
        // 获取机型相关的课件
        var coursewareList = coursewareService.lambdaQuery()
                .eq(Courseware::getMachineTypeId, trainingPlan.getMachineTypeId())
                .eq(Courseware::getMajorId, trainingPlan.getMajorId())
                .eq(Courseware::getStatus, "0")
                .orderByAsc(Courseware::getOrderNum)
                .list();
        
        LocalDate currentDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int dayNumber = 1;
        
        // 每天7小时，上午3.5小时，下午3.5小时
        BigDecimal dailyHours = new BigDecimal("7.0");
        BigDecimal morningHours = new BigDecimal("3.5");
        BigDecimal afternoonHours = new BigDecimal("3.5");
        
        for (var courseware : coursewareList) {
            // 理论课安排
            if (courseware.getTheoryHours() != null && courseware.getTheoryHours().compareTo(BigDecimal.ZERO) > 0) {
                TrainingPlanSchedule theorySchedule = new TrainingPlanSchedule();
                theorySchedule.setPlanId(trainingPlan.getPlanId());
                theorySchedule.setScheduleType("class_schedule");
                theorySchedule.setDayNumber(dayNumber);
                theorySchedule.setTimeSlot("08:30-12:00");
                theorySchedule.setCoursewareId(courseware.getCoursewareId());
                theorySchedule.setCourseName(courseware.getCourseName());
                theorySchedule.setCourseType("1"); // 理论
                theorySchedule.setCourseHours(morningHours);
                theorySchedule.setCourseContent("理论学习：" + courseware.getCourseName());
                theorySchedule.setStatus("0");
                schedules.add(theorySchedule);
            }
            
            // 实践课安排
            if (courseware.getPracticeHours() != null && courseware.getPracticeHours().compareTo(BigDecimal.ZERO) > 0) {
                TrainingPlanSchedule practiceSchedule = new TrainingPlanSchedule();
                practiceSchedule.setPlanId(trainingPlan.getPlanId());
                practiceSchedule.setScheduleType("class_schedule");
                practiceSchedule.setDayNumber(dayNumber);
                practiceSchedule.setTimeSlot("14:00-17:30");
                practiceSchedule.setCoursewareId(courseware.getCoursewareId());
                practiceSchedule.setCourseName(courseware.getCourseName());
                practiceSchedule.setCourseType("2"); // 实践
                practiceSchedule.setCourseHours(afternoonHours);
                practiceSchedule.setCourseContent("实践训练：" + courseware.getCourseName());
                practiceSchedule.setStatus("0");
                schedules.add(practiceSchedule);
            }
            
            dayNumber++;
        }
        
        return schedules;
    }
    
    /**
     * 生成计划编号
     */
    private String generatePlanCode(Long machineTypeId, Long majorId, Long trainingAbilityId) {
        String year = String.valueOf(LocalDate.now().getYear());
        
        // 查询当年已有的计划数量
        long count = trainingPlanService.lambdaQuery()
                .eq(TrainingPlan::getMachineTypeId, machineTypeId)
                .eq(TrainingPlan::getMajorId, majorId)
                .eq(TrainingPlan::getTrainingAbilityId, trainingAbilityId)
                .like(TrainingPlan::getPlanCode, year)
                .count();
        
        return String.format("%s-%s-%03d", year, "PLAN", count + 1);
    }
    
    /**
     * 生成计划名称
     */
    private String generatePlanName(Long machineTypeId, Long majorId, Long trainingAbilityId) {
        var machineType = machineTypeService.getById(machineTypeId);
        var major = majorService.getById(majorId);
        var trainingAbility = trainingAbilityService.getById(trainingAbilityId);
        
        return String.format("%s-%s-%s-培训计划", 
                machineType != null ? machineType.getMachineTypeName() : "",
                major != null ? major.getMajorName() : "",
                trainingAbility != null ? trainingAbility.getAbilityName() : "");
    }
    
    /**
     * 生成班次编号（年份+序号顺延）
     */
    private String generateClassCode() {
        String year = String.valueOf(LocalDate.now().getYear());
        
        // 查询当年已有的班次数量
        long count = trainingClassService.lambdaQuery()
                .like(TrainingClass::getClassCode, year)
                .count();
        
        return String.format("%s-%03d", year, count + 1);
    }
    
    /**
     * 计算总天数
     */
    private int calculateTotalDays(List<TrainingPlanSchedule> schedules) {
        return (int) schedules.stream()
                .mapToInt(TrainingPlanSchedule::getDayNumber)
                .max()
                .orElse(0);
    }
    
    /**
     * 获取课程表详情
     */
    @PostMapping("/schedule-detail")
    @Operation(summary = "获取课程表详情", description = "根据计划ID获取详细的课程安排")
    public R<Map<String, Object>> getScheduleDetail(@RequestBody IdRequest request) {
        try {
            // 获取培训计划信息
            TrainingPlan trainingPlan = trainingPlanService.getById(request.getId());
            if (trainingPlan == null) {
                return R.fail("培训计划不存在");
            }
            
            // 获取课程安排列表
            List<TrainingPlanSchedule> schedules = trainingPlanScheduleService.lambdaQuery()
                    .eq(TrainingPlanSchedule::getPlanId, request.getId())
                    .eq(TrainingPlanSchedule::getStatus, "0")
                    .orderByAsc(TrainingPlanSchedule::getDayNumber)
                    .orderByAsc(TrainingPlanSchedule::getTimeSlot)
                    .list();
            
            // 按类型分组
            Map<String, List<TrainingPlanSchedule>> groupedSchedules = schedules.stream()
                    .collect(Collectors.groupingBy(TrainingPlanSchedule::getScheduleType));
            
            Map<String, Object> result = new HashMap<>();
            result.put("planInfo", trainingPlan);
            result.put("classSchedule", groupedSchedules.get("class_schedule")); // 班次课程表
            result.put("teachingSchedule", groupedSchedules.get("teaching_schedule")); // 教学进度安排表
            result.put("practicalProject", groupedSchedules.get("practical_project")); // 实作项目清单
            
            return R.success(result);
            
        } catch (Exception e) {
            return R.fail("获取课程表详情失败：" + e.getMessage());
        }
    }
    
    /**
     * 修改课程安排
     */
    @PostMapping("/update-schedule")
    @Operation(summary = "修改课程安排", description = "修改特定的课程安排")
    public R<Void> updateSchedule(@RequestBody TrainingPlanSchedule schedule) {
        try {
            boolean result = trainingPlanScheduleService.updateById(schedule);
            if (result) {
                return R.success();
            }
            return R.fail("修改课程安排失败");
        } catch (Exception e) {
            return R.fail("修改课程安排失败：" + e.getMessage());
        }
    }

    /**
     * 转换计划状态
     */
    private String convertPlanStatus(String planStatus) {
        if (planStatus == null) return "";
        return switch (planStatus) {
            case "0" -> "草稿";
            case "1" -> "执行中";
            case "2" -> "已完成";
            case "3" -> "已取消";
            default -> "";
        };
    }
}