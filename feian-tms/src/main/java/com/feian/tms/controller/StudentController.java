package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.Student;
import com.feian.tms.dto.query.StudentQuery;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.StudentRequest;
import com.feian.tms.dto.response.StudentResponse;
import com.feian.tms.excel.StudentExcel;
import com.feian.tms.service.StudentService;
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
 * 学员信息管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/student")
@RequiredArgsConstructor
@Tag(name = "学员信息管理", description = "学员信息管理相关接口")
public class StudentController {
    
    private final StudentService studentService;

    /**
     * 查询学员信息列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询学员信息列表", description = "根据查询条件分页查询学员信息列表")
    public R<Page<StudentResponse>> list(@RequestBody StudentQuery query) {
        Page<Student> page = new Page<>(query.getPageNum(), query.getPageSize());
        
        // 构建查询条件
        var queryWrapper = studentService.lambdaQuery()
                .like(query.getStudentName() != null, Student::getStudentName, query.getStudentName())
                .like(query.getStudentCode() != null, Student::getStudentCode, query.getStudentCode())
                .eq(query.getGender() != null, Student::getGender, query.getGender())
                .like(query.getPhoneNumber() != null, Student::getPhoneNumber, query.getPhoneNumber())
                .like(query.getDepartment() != null, Student::getDepartment, query.getDepartment())
                .eq(query.getPrimaryMachineTypeId() != null, Student::getPrimaryMachineTypeId, query.getPrimaryMachineTypeId())
                .eq(query.getPrimaryMajorId() != null, Student::getPrimaryMajorId, query.getPrimaryMajorId())
                .eq(query.getEducation() != null, Student::getEducation, query.getEducation())
                .eq(query.getTrainingStatus() != null, Student::getTrainingStatus, query.getTrainingStatus())
                .eq(query.getStatus() != null, Student::getStatus, query.getStatus())
                .orderByDesc(Student::getCreateTime);
        
        Page<Student> result = queryWrapper.page(page);
        
        // 转换为响应对象
        Page<StudentResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(result, responsePage);
        
        var responseList = result.getRecords().stream()
                .map(entity -> {
                    StudentResponse response = new StudentResponse();
                    BeanUtils.copyProperties(entity, response);
                    return response;
                })
                .toList();
        
        responsePage.setRecords(responseList);
        return R.success(responsePage);
    }

    /**
     * 获取学员信息详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取学员详细信息", description = "根据ID获取学员详细信息")
    public R<StudentResponse> detail(@Valid @RequestBody IdRequest request) {
        Student entity = studentService.getById(request.getId());
        if (entity == null) {
            return R.fail("学员信息不存在");
        }
        
        StudentResponse response = new StudentResponse();
        BeanUtils.copyProperties(entity, response);
        return R.success(response);
    }

    /**
     * 新增学员信息
     */
    @PostMapping("/create")
    @Operation(summary = "新增学员", description = "创建新的学员信息")
    public R<StudentResponse> create(@Valid @RequestBody StudentRequest request) {
        Student entity = new Student();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = studentService.save(entity);
        if (result) {
            StudentResponse response = new StudentResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增学员失败");
    }

    /**
     * 修改学员信息
     */
    @PostMapping("/update")
    @Operation(summary = "修改学员", description = "更新现有学员信息")
    public R<StudentResponse> update(@Valid @RequestBody StudentRequest request) {
        if (request.getStudentId() == null) {
            return R.fail("学员ID不能为空");
        }
        
        Student entity = new Student();
        BeanUtils.copyProperties(request, entity);
        
        boolean result = studentService.updateById(entity);
        if (result) {
            StudentResponse response = new StudentResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新学员失败");
    }

    /**
     * 删除学员信息
     */
    @PostMapping("/delete")
    @Operation(summary = "删除学员", description = "根据ID删除学员信息")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        boolean result = studentService.removeById(request.getId());
        if (result) {
            return R.success();
        }
        return R.fail("删除学员失败");
    }

    /**
     * 导出学员信息列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出学员列表", description = "根据查询条件导出学员列表到Excel")
    public void export(HttpServletResponse response, @RequestBody StudentQuery query) {
        // 查询所有数据（不分页）
        var queryWrapper = studentService.lambdaQuery()
                .like(query.getStudentName() != null, Student::getStudentName, query.getStudentName())
                .like(query.getStudentCode() != null, Student::getStudentCode, query.getStudentCode())
                .eq(query.getGender() != null, Student::getGender, query.getGender())
                .like(query.getPhoneNumber() != null, Student::getPhoneNumber, query.getPhoneNumber())
                .like(query.getDepartment() != null, Student::getDepartment, query.getDepartment())
                .eq(query.getPrimaryMachineTypeId() != null, Student::getPrimaryMachineTypeId, query.getPrimaryMachineTypeId())
                .eq(query.getPrimaryMajorId() != null, Student::getPrimaryMajorId, query.getPrimaryMajorId())
                .eq(query.getEducation() != null, Student::getEducation, query.getEducation())
                .eq(query.getTrainingStatus() != null, Student::getTrainingStatus, query.getTrainingStatus())
                .eq(query.getStatus() != null, Student::getStatus, query.getStatus())
                .orderByDesc(Student::getCreateTime);
        
        List<Student> list = queryWrapper.list();
        
        // 转换为导出对象
        List<StudentExcel> excelList = list.stream()
                .map(entity -> {
                    StudentExcel excel = new StudentExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setGenderName(convertGender(entity.getGender()));
                    excel.setEducationName(convertEducation(entity.getEducation()));
                    excel.setTrainingStatusName(convertTrainingStatus(entity.getTrainingStatus()));
                    excel.setStatusName("0".equals(entity.getStatus()) ? "正常" : "停用");
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "学员信息管理", "学员信息列表", StudentExcel.class, excelList);
    }

    /**
     * 转换性别枚举
     */
    private String convertGender(String gender) {
        if (gender == null) return "";
        return switch (gender) {
            case "0" -> "男";
            case "1" -> "女";
            case "2" -> "未知";
            default -> "";
        };
    }

    /**
     * 转换学历枚举
     */
    private String convertEducation(String education) {
        if (education == null) return "";
        return switch (education) {
            case "1" -> "中专";
            case "2" -> "大专";
            case "3" -> "本科";
            case "4" -> "硕士";
            case "5" -> "博士";
            default -> "";
        };
    }

    /**
     * 转换培训状态枚举
     */
    private String convertTrainingStatus(String trainingStatus) {
        if (trainingStatus == null) return "";
        return switch (trainingStatus) {
            case "0" -> "待培训";
            case "1" -> "培训中";
            case "2" -> "已培训";
            case "3" -> "培训暂停";
            default -> "";
        };
    }
}