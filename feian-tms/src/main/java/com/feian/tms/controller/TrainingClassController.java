package com.feian.tms.controller;

import com.feian.common.utils.PageUtils;
import com.feian.tms.domain.Instructor;
import com.feian.tms.dto.request.IdsDeleteRequest;
import com.feian.tms.dto.request.IdsRequest;
import com.feian.tms.service.*;
import com.github.pagehelper.PageInfo;
import com.feian.tms.common.R;
import com.feian.tms.domain.TrainingClass;
import com.feian.tms.common.PageRequest;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.TrainingClassRequest;
import com.feian.tms.dto.response.TrainingClassResponse;
import com.feian.tms.excel.TrainingClassExcel;
import com.feian.tms.utils.EasyExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 培训班次管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/trainingClass")
@RequiredArgsConstructor
@Tag(name = "培训班次管理", description = "培训班次管理相关接口")
public class TrainingClassController {
    
    private final TrainingClassService trainingClassService;
    private final TrainingPlanService trainingPlanService;
    private final MachineTypeService machineTypeService;
    private final MajorService majorService;
    private final TrainingAbilityService trainingAbilityService;
    private final InstructorService instructorService;
    private final ClassStudentService classStudentService;
    /**
     * 查询培训班次管理列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询培训班次管理列表", description = "根据查询条件分页查询培训班次列表")
    public R<PageInfo<TrainingClassResponse>> list(@RequestBody PageRequest<TrainingClassRequest> pageRequest) {
        // 启动分页
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        TrainingClassRequest query = pageRequest.getQuery();
        if (query == null) {
            query = new TrainingClassRequest();
        }
        // 构建查询条件
        var queryWrapper = trainingClassService.lambdaQuery()
                .like(query.getClassCode() != null, TrainingClass::getClassCode, query.getClassCode())
                .like(query.getClassName() != null, TrainingClass::getClassName, query.getClassName())
                .eq(query.getTrainingPlanId() != null, TrainingClass::getTrainingPlanId, query.getTrainingPlanId())
                .eq(query.getMachineTypeId() != null, TrainingClass::getMachineTypeId, query.getMachineTypeId())
                .eq(query.getMajorId() != null, TrainingClass::getMajorId, query.getMajorId())
                .eq(query.getTrainingAbilityId() != null, TrainingClass::getTrainingAbilityId, query.getTrainingAbilityId())
                .ge(query.getPlanStartTime() != null, TrainingClass::getPlanStartTime, query.getPlanStartTime())
                .le(query.getPlanEndTime() != null, TrainingClass::getPlanEndTime, query.getPlanEndTime())
                .eq(query.getPrimaryInstructorId() != null, TrainingClass::getPrimaryInstructorId, query.getPrimaryInstructorId())
                .eq(query.getStatus() != null, TrainingClass::getStatus, query.getStatus())
                .orderByDesc(TrainingClass::getCreateTime);
        
        List<TrainingClass> list = queryWrapper.list();
        PageInfo<TrainingClass> pageInfo = new PageInfo<>(list);
        // 转换为响应对象
        var responseList = list.stream()
                .map(entity -> {
                    TrainingClassResponse response = new TrainingClassResponse();
                    BeanUtils.copyProperties(entity, response);
                    
                    // 设置关联字段名称
                    if (entity.getTrainingPlanId() != null) {
                        var trainingPlan = trainingPlanService.getById(entity.getTrainingPlanId());
                        if (trainingPlan != null) {
                            response.setTrainingPlanName(trainingPlan.getPlanName());
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
                            response.setTrainingAbilityName(trainingAbility.getAbilityName());
                        }
                    }
                    
                    if (entity.getPrimaryInstructorId() != null) {
                        var instructor = instructorService.getById(entity.getPrimaryInstructorId());
                        if (instructor != null) {
                            response.setPrimaryInstructorName(instructor.getInstructorName());
                        }
                    }
                    response.setActualStudentCount(trainingClassService.getStudentCountById(response.getTrainingClassId()));
                    return response;
                })
                .toList();
        // 创建新的PageInfo并保留原有分页信息
        PageInfo<TrainingClassResponse> result = new PageInfo<>(responseList);
        BeanUtils.copyProperties(pageInfo, result, "list");
        // 返回分页信息
        return R.success(result);
    }

    /**
     * 获取培训班次管理详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取培训班次详细信息", description = "根据ID获取培训班次详细信息")
    public R<TrainingClassResponse> detail(@Valid @RequestBody IdRequest request) {
        TrainingClass entity = trainingClassService.getById(request.getId());
        if (entity == null) {
            return R.fail("培训班次信息不存在");
        }
        
        TrainingClassResponse response = new TrainingClassResponse();
        BeanUtils.copyProperties(entity, response);
        
        // 设置关联字段名称
        if (entity.getTrainingPlanId() != null) {
            var trainingPlan = trainingPlanService.getById(entity.getTrainingPlanId());
            if (trainingPlan != null) {
                response.setTrainingPlanName(trainingPlan.getPlanName());
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
                response.setTrainingAbilityName(trainingAbility.getAbilityName());
            }
        }
        
        if (entity.getPrimaryInstructorId() != null) {
            var instructor = instructorService.getById(entity.getPrimaryInstructorId());
            if (instructor != null) {
                response.setPrimaryInstructorName(instructor.getInstructorName());
            }
        }
        
        return R.success(response);
    }

    /**
     * 新增培训班次管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增培训班次", description = "创建新的培训班次信息")
    public R<TrainingClassResponse> create(@Valid @RequestBody TrainingClassRequest request) {
        TrainingClass entity = new TrainingClass();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingClassService.save(entity);
        if (result) {
            TrainingClassResponse response = new TrainingClassResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增培训班次失败");
    }

    /**
     * 修改培训班次管理
     */
    @PostMapping("/update")
    @Operation(summary = "修改培训班次", description = "更新现有培训班次信息")
    public R<TrainingClassResponse> update(@Valid @RequestBody TrainingClassRequest request) {
        if (request.getTrainingClassId() == null) {
            return R.fail("培训班次ID不能为空");
        }
        
        TrainingClass entity = new TrainingClass();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = trainingClassService.updateById(entity);
        if (result) {
            TrainingClassResponse response = new TrainingClassResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新培训班次失败");
    }

    /**
     * 删除培训班次管理
     */
    @PostMapping("/delete")
    @Operation(summary = "删除培训班次", description = "根据ID删除培训班次信息")
    public R<Void> delete(@Valid @RequestBody IdsDeleteRequest idsDeleteRequest) {
        boolean result = trainingClassService.removeByIds(idsDeleteRequest.getIdList());
        classStudentService.removeByClassIds(idsDeleteRequest.getIdList());
        if (result) {
            return R.success();
        }
        return R.fail("删除培训班次失败");
    }

    /**
     * 导出培训班次管理列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出培训班次列表", description = "根据查询条件导出培训班次列表到Excel")
    public void export(HttpServletResponse response, @RequestBody IdsRequest idsRequest) {
        List<TrainingClass> list;
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
            list =trainingClassService.trainingClassList();
        }else {
            // 根据ID查询所有数据（不分页）
            list =trainingClassService.listByIds(idsRequest.getIdList());
        }

//        var queryWrapper = trainingClassService.lambdaQuery()
//                .like(query.getClassCode() != null, TrainingClass::getClassCode, query.getClassCode())
//                .like(query.getClassName() != null, TrainingClass::getClassName, query.getClassName())
//                .eq(query.getTrainingPlanId() != null, TrainingClass::getTrainingPlanId, query.getTrainingPlanId())
//                .eq(query.getMachineTypeId() != null, TrainingClass::getMachineTypeId, query.getMachineTypeId())
//                .eq(query.getMajorId() != null, TrainingClass::getMajorId, query.getMajorId())
//                .eq(query.getTrainingAbilityId() != null, TrainingClass::getTrainingAbilityId, query.getTrainingAbilityId())
//                .ge(query.getPlanStartTime() != null, TrainingClass::getPlanStartTime, query.getPlanStartTime())
//                .le(query.getPlanEndTime() != null, TrainingClass::getPlanEndTime, query.getPlanEndTime())
//                .eq(query.getPrimaryInstructorId() != null, TrainingClass::getPrimaryInstructorId, query.getPrimaryInstructorId())
//                .eq(query.getStatus() != null, TrainingClass::getStatus, query.getStatus())
//                .orderByDesc(TrainingClass::getCreateTime);
//
//        List<TrainingClass> list = queryWrapper.list();
        
        // 转换为导出对象
        List<TrainingClassExcel> excelList = list.stream()
                .map(entity -> {
                    TrainingClassExcel excel = new TrainingClassExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换状态显示文本
                    excel.setStatusName(convertStatus(entity.getStatus()));
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "培训班次管理", "培训班次列表", TrainingClassExcel.class, excelList);
    }

    /**
     * 转换状态枚举
     */
    private String convertStatus(String status) {
        if (status == null) return "";
        return switch (status) {
            case "0" -> "待开班";
            case "1" -> "培训中";
            case "2" -> "已结班";
            case "3" -> "已取消";
            default -> "";
        };
    }
}