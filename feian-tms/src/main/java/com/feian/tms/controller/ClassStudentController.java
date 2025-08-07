package com.feian.tms.controller;

import com.feian.common.utils.PageUtils;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.tms.domain.ClassStudent;
import com.feian.tms.dto.query.ClassStudentQuery;
import com.feian.tms.dto.request.IdsDeleteRequest;
import com.github.pagehelper.PageInfo;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.ClassStudentRequest;
import com.feian.tms.dto.request.ClassStudentBatchAddRequest;
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

import jakarta.validation.Valid;
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
    public R<PageInfo<ClassStudentResponse>> list(@RequestBody PageRequest<ClassStudentQuery> pageRequest) {
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        ClassStudentQuery query = pageRequest.getQuery();
        if (query == null) {
            query = new ClassStudentQuery();
        }
        
        // 使用Service方法进行连表查询
        List<ClassStudentResponse> responseList = classStudentService.getClassStudentListWithDetails(query);
        PageInfo<ClassStudentResponse> result = new PageInfo<>(responseList);

        return R.success(result);
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
    public R<Void> delete(@Valid @RequestBody IdsDeleteRequest idsDeleteRequest) {
       classStudentService.removeAllByIds(idsDeleteRequest.getIdList());
            return R.success();

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
     * 批量添加学员到班次
     */
    @PostMapping("/batch-add-students")
    @Operation(summary = "批量添加学员到班次", description = "批量添加学员到指定培训班次")
    public R<String> batchAddStudents(@Valid @RequestBody ClassStudentBatchAddRequest request) {
        try {
            int successCount = 0;
            int failCount = 0;
            StringBuilder failMessages = new StringBuilder();
            
            for (Long studentId : request.getStudentIds()) {
                // 检查学员是否已在班次中
                ClassStudent existing = classStudentService.lambdaQuery()
                        .eq(ClassStudent::getTrainingClassId, request.getTrainingClassId())
                        .eq(ClassStudent::getStudentId, studentId)
                        .one();
                
                if (existing != null) {
                    failCount++;
                    failMessages.append("学员ID[").append(studentId).append("]已在班次中; ");
                    continue;
                }
                
                // 创建班次学员关联
                ClassStudent classStudent = new ClassStudent();
                classStudent.setTrainingClassId(request.getTrainingClassId());
                classStudent.setStudentId(studentId);
                classStudent.setEnrollTime(new java.util.Date());
                classStudent.setStudentStatus("0"); // 默认状态：待开班
                classStudent.setRemark(request.getRemark());
                
                boolean result = classStudentService.save(classStudent);
                if (result) {
                    successCount++;
                } else {
                    failCount++;
                    failMessages.append("学员ID[").append(studentId).append("]添加失败; ");
                }
            }
            
            String message = String.format("批量添加完成！成功：%d人，失败：%d人", successCount, failCount);
            if (failCount > 0) {
                message += "。失败原因：" + failMessages.toString();
            }
            
            return R.success(message);
        } catch (Exception e) {
            return R.fail("批量添加学员失败：" + e.getMessage());
        }
    }
    
    /**
     * 获取可选学员列表（排除已在班次中的学员）
     */
    @PostMapping("/available-students")  
    @Operation(summary = "获取可选学员列表", description = "获取指定班次的可选学员列表，排除已在班次中的学员")
    public R<List<ClassStudentResponse>> getAvailableStudents(@Valid @RequestBody IdRequest request) {
        try {
            // 获取已在班次中的学员ID列表
            List<Long> existingStudentIds = classStudentService.lambdaQuery()
                    .eq(ClassStudent::getTrainingClassId, request.getId())
                    .list()
                    .stream()
                    .map(ClassStudent::getStudentId)
                    .toList();
            
            // 这里需要调用Student服务获取所有学员，然后过滤掉已在班次中的
            // 由于当前架构限制，先返回空列表，需要在Service层实现具体逻辑
            List<ClassStudentResponse> availableStudents = classStudentService.getAvailableStudentsForClass(request.getId());
            
            return R.success(availableStudents);
        } catch (Exception e) {
            return R.fail("获取可选学员列表失败：" + e.getMessage());
        }
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