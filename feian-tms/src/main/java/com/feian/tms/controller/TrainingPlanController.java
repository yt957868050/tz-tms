package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.tms.domain.TrainingPlan;
import com.feian.tms.domain.TrainingPlanInstructor;
import com.feian.tms.domain.Instructor;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 培训计划Controller
 * 
 * @author feian
 * @date 2025-01-24
 */
@RestController
@RequestMapping("/api/tms/training-plan")
@RequiredArgsConstructor
@Tag(name = "培训计划管理", description = "培训计划管理相关接口")
public class TrainingPlanController {
    
    private final TrainingPlanService trainingPlanService;
    private final TrainingPlanInstructorService trainingPlanInstructorService;
    private final InstructorService instructorService;
    private final MachineTypeService machineTypeService;
    private final MajorService majorService;
    private final TrainingAbilityService trainingAbilityService;

    /**
     * 查询培训计划列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询培训计划列表", description = "根据查询条件分页查询培训计划列表")
    public R<Page<TrainingPlanResponse>> list(@RequestBody PageRequest<TrainingPlanRequest> pageRequest) {
        Page<TrainingPlan> page = new Page<>(pageRequest.getPageNum() ,
                pageRequest.getPageSize());

        TrainingPlanRequest query = pageRequest.getQuery();
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
        
        Page<TrainingPlan> result = queryWrapper.page(page);
        
        // 转换为响应对象
        Page<TrainingPlanResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        // 为每个计划查询关联的教员信息
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    TrainingPlanResponse response = new TrainingPlanResponse();
                    BeanUtils.copyProperties(entity, response);
                    
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
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
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
        
        // 查询关联教员信息
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
            // 保存教员关联关系
            if (request.getInstructorIds() != null && !request.getInstructorIds().isEmpty()) {
                for (Long instructorId : request.getInstructorIds()) {
                    TrainingPlanInstructor tpi = new TrainingPlanInstructor();
                    tpi.setPlanId(entity.getPlanId());
                    tpi.setInstructorId(instructorId);
                    tpi.setIsChief(instructorId.equals(request.getChiefInstructorId()) ? "1" : "0");
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
            // 更新教员关联关系
            if (request.getInstructorIds() != null) {
                // 删除原有关联
                trainingPlanInstructorService.lambdaUpdate()
                        .eq(TrainingPlanInstructor::getPlanId, entity.getPlanId())
                        .remove();
                
                // 添加新的关联
                for (Long instructorId : request.getInstructorIds()) {
                    TrainingPlanInstructor tpi = new TrainingPlanInstructor();
                    tpi.setPlanId(entity.getPlanId());
                    tpi.setInstructorId(instructorId);
                    tpi.setIsChief(instructorId.equals(request.getChiefInstructorId()) ? "1" : "0");
                    tpi.setStatus("0");
                    trainingPlanInstructorService.save(tpi);
                }
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
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        // 删除培训计划
        boolean result = trainingPlanService.removeById(request.getId());
        if (result) {
            // 删除关联的教员关系
            trainingPlanInstructorService.lambdaUpdate()
                    .eq(TrainingPlanInstructor::getPlanId, request.getId())
                    .remove();
            
            return R.success();
        }
        return R.fail("删除培训计划失败");
    }

    /**
     * 生成课程表
     */
    @PostMapping("/generate-schedule")
    @Operation(summary = "生成课程表", description = "为培训计划自动生成课程表")
    public R<Void> generateSchedule(@Valid @RequestBody IdRequest request) {
        // TODO: 实现自动排课算法
        return R.fail("自动排课功能正在开发中");
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