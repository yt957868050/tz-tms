package com.feian.tms.controller;

import com.feian.common.annotation.DataScope;
import com.feian.common.utils.PageUtils;
import com.feian.framework.web.service.TokenService;
import com.feian.tms.dto.request.IdsDeleteRequest;
import com.feian.tms.dto.request.IdsRequest;
import com.feian.tms.service.ClassStudentService;
import com.github.pagehelper.PageInfo;
import com.feian.tms.common.R;
import com.feian.tms.domain.Student;
import com.feian.tms.common.PageRequest;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.StudentRequest;
import com.feian.tms.dto.response.StudentResponse;
import com.feian.tms.excel.StudentExcel;
import com.feian.tms.service.StudentService;
import com.feian.tms.service.IStudentMachineTypeService;
import com.feian.tms.service.MajorService;
import com.feian.tms.domain.MachineType;
import com.feian.tms.domain.StudentMachineType;
import com.feian.tms.domain.Major;
import com.feian.tms.utils.EasyExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.context.AnalysisContext;
import java.util.Collections;
import java.util.ArrayList;

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
    private final IStudentMachineTypeService studentMachineTypeService;
    private final MajorService majorService;
    private final ClassStudentService classStudentService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询学员信息列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询学员信息列表", description = "根据查询条件分页查询学员信息列表")
    @DataScope(enableMachineTypeFilter = true, studentAlias = "s")
    public R<PageInfo<StudentResponse>> list(@RequestBody PageRequest<StudentRequest> pageRequest) {

        // 启动分页
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        StudentRequest query = pageRequest.getQuery();
        if (query == null) {
            query = new StudentRequest();
        }
        
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
        
        List<Student> list = queryWrapper.list();
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        // 转换为响应对象
        var responseList = list.stream()
                .map(entity -> {
                    StudentResponse response = new StudentResponse();
                    BeanUtils.copyProperties(entity, response);
                    
                    // 添加机型信息
                    List<MachineType> machineTypes = studentMachineTypeService.getAvailableMachineTypesByStudentId(entity.getStudentId());
                    List<StudentResponse.MachineTypeInfo> machineTypeInfos = machineTypes.stream()
                            .map(mt -> {
                                StudentResponse.MachineTypeInfo info = new StudentResponse.MachineTypeInfo();
                                info.setMachineTypeId(mt.getMachineTypeId());
                                info.setMachineTypeName(mt.getMachineTypeName());
                                info.setIsPrimary(mt.getMachineTypeId().equals(entity.getPrimaryMachineTypeId()));
                                
                                // 设置主要机型名称
                                if (info.getIsPrimary()) {
                                    response.setPrimaryMachineTypeName(mt.getMachineTypeName());
                                }
                                
                                return info;
                            })
                            .toList();
                    response.setMachineTypes(machineTypeInfos);
                    
                    // 添加专业名称
                    if (entity.getPrimaryMajorId() != null) {
                        Major major = majorService.getById(entity.getPrimaryMajorId());
                        if (major != null) {
                            response.setPrimaryMajorName(major.getMajorName());
                        }
                    }

                    return response;
                })
                .toList();
        // 创建新的PageInfo并保留原有分页信息
        PageInfo<StudentResponse> result = new PageInfo<>(responseList);
        BeanUtils.copyProperties(pageInfo, result, "list");
        // 返回分页信息
        return R.success(result);
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
        
        // 添加机型信息
        List<MachineType> machineTypes = studentMachineTypeService.getAvailableMachineTypesByStudentId(entity.getStudentId());
        List<StudentResponse.MachineTypeInfo> machineTypeInfos = machineTypes.stream()
                .map(mt -> {
                    StudentResponse.MachineTypeInfo info = new StudentResponse.MachineTypeInfo();
                    info.setMachineTypeId(mt.getMachineTypeId());
                    info.setMachineTypeName(mt.getMachineTypeName());
                    info.setIsPrimary(mt.getMachineTypeId().equals(entity.getPrimaryMachineTypeId()));
                    
                    // 设置主要机型名称
                    if (info.getIsPrimary()) {
                        response.setPrimaryMachineTypeName(mt.getMachineTypeName());
                    }
                    
                    return info;
                })
                .toList();
        response.setMachineTypes(machineTypeInfos);
        
        // 添加专业名称
        if (entity.getPrimaryMajorId() != null) {
            Major major = majorService.getById(entity.getPrimaryMajorId());
            if (major != null) {
                response.setPrimaryMajorName(major.getMajorName());
            }
        }
        
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
        studentService.setPrimaryMajor(entity.getStudentId(), entity.getPrimaryMajorId());
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
    public R<Void> delete(@Valid @RequestBody IdsDeleteRequest idsDeleteRequest) {
        boolean result = studentService.removeByIds(idsDeleteRequest.getIdList());
        classStudentService.removeStudentByIds(idsDeleteRequest.getIdList());

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
    public void export(HttpServletResponse response, @RequestBody IdsRequest idsRequest) {
        List<Student> list;
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
            list =studentService.studentList();
        }else {
            // 根据ID查询所有数据（不分页）
             list = studentService.studentListByIds(idsRequest.getIdList());
        }
        // 转换为导出对象
        List<StudentExcel> excelList = list.stream()
                .map(entity -> {
                    StudentExcel excel = new StudentExcel();
                    excel.setStudentName(entity.getStudentName());
                    excel.setStudentCode(entity.getStudentCode());
                    excel.setGender(convertGender(entity.getGender()));
                    excel.setPrimaryMajor(convertPrimaryMajor(String.valueOf(entity.getPrimaryMajorId())));
                    excel.setNation(entity.getNation());
                    excel.setBirthDate(formatDate(entity.getBirthDate()));
                    excel.setIdCard(entity.getIdCard());
                    excel.setWorkStartDate(formatDate(entity.getWorkStartDate()));
                    excel.setWorkUnit(entity.getWorkUnit());
                    excel.setEducationLevel(entity.getEducationLevel());
                    excel.setGraduateSchool(entity.getGraduateSchool());
                    excel.setMajorStudied(entity.getMajor());
                    excel.setEnglishLevel(entity.getEnglishLevel());
                    excel.setIntegrityScore(entity.getIntegrityScore());
                    excel.setLicenseType(entity.getLicenseType());
                    excel.setLicenseNumber(entity.getLicenseNumber());
                    excel.setAircraftEndorsement(entity.getAircraftEndorsement());
                    // workExperience用remark字段
                    excel.setWorkExperience(entity.getRemark());
                    excel.setStatus(convertStatus(entity.getStatus()));
                    return excel;
                })
                .toList();
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "学员信息管理", "学员信息列表", StudentExcel.class, excelList);
    }

    /**
     * 下载学员导入模板
     */
    @PostMapping("/importTemplate")
    @Operation(summary = "下载学员导入模板", description = "下载学员信息Excel导入模板")
    public void importTemplate(HttpServletResponse response) {
        // 生成空数据模板
        List<StudentExcel> emptyList = java.util.Collections.emptyList();
        EasyExcelUtil.exportExcel(response, "学员信息导入模板", "学员信息", StudentExcel.class, emptyList);
    }

    /**
     * 导入学员信息
     */
    /**
     * 导入学员信息
     */
    @PostMapping("/importData")
    @Operation(summary = "导入学员信息", description = "通过Excel批量导入学员信息")
    @Transactional(rollbackFor = Exception.class) // 添加事务注解，确保异常时回滚
    public R<String> importData(@RequestParam("file") MultipartFile file) {
        try {
            ReadListener<StudentExcel> listener = new ReadListener<StudentExcel>() {
                // 存储从Excel读取的数据
                private final List<StudentExcel> dataList = new ArrayList<>();

                @Override
                public void invoke(StudentExcel data, AnalysisContext context) {
                    dataList.add(data);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    // 收集所有需要保存的实体
                    List<Student> entities = new ArrayList<>();
                    for (StudentExcel excel : dataList) {
                        Student entity = new Student();
                        entity.setStudentName(excel.getStudentName());
                        entity.setStudentCode(excel.getStudentCode());
                        entity.setGender(convertIGender(excel.getGender()));
                        entity.setPrimaryMajorId(convertIPrimaryMajor(excel.getPrimaryMajor()));
                        entity.setPrimaryMajorName(excel.getPrimaryMajor());
                        entity.setNation(excel.getNation());
                        entity.setBirthDate(parseDate(excel.getBirthDate()));
                        entity.setIdCard(excel.getIdCard());
                        entity.setWorkStartDate(parseDate(excel.getWorkStartDate()));
                        entity.setWorkUnit(excel.getWorkUnit());
                        entity.setEducationLevel(excel.getEducationLevel());
                        entity.setGraduateSchool(excel.getGraduateSchool());
                        entity.setMajor(excel.getMajorStudied());
                        entity.setEnglishLevel(excel.getEnglishLevel());
                        entity.setIntegrityScore(excel.getIntegrityScore());
                        entity.setLicenseType(excel.getLicenseType());
                        entity.setLicenseNumber(excel.getLicenseNumber());
                        entity.setAircraftEndorsement(excel.getAircraftEndorsement());
                        entity.setRemark(excel.getWorkExperience());
                        entity.setStatus(convertStatus(excel.getStatus()));
                        entities.add(entity);
                    }
                    // 批量保存到数据库
                    studentService.saveBatch(entities);
                }
            };
            EasyExcelUtil.readExcel(file.getInputStream(), StudentExcel.class, listener);
            return R.success("导入成功");
        } catch (Exception e) {
            // 捕获异常，抛出运行时异常，让Spring事务管理器进行回滚
            // 提示：你可能需要根据业务逻辑，对 e.getMessage() 进行更友好的封装
            throw new RuntimeException("导入失败: " + e.getMessage(), e);
        }
    }

    /**
     * 导出转换性别枚举
     */
    private String convertGender(String gender) {
        if (gender == null) return "";
        return switch (gender) {
            case "0" -> "男";
            case "1" -> "女";
            default -> "";
        };
    }

    /**
     * 导入转换性别枚举
     */
    private String convertIGender(String gender) {
        if (gender == null) return "";
        return switch (gender) {
            case "男" -> "0";
            case "女" -> "1";
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

    /**
     * 导出转换专业
     */
    private String convertPrimaryMajor(String primaryMajor) {
        if (primaryMajor == null) return "";
        return switch (primaryMajor) {
            case "1" -> "空勤专业";
            case "2" -> "地勤专业";
            default -> "";
        };
    }
    /**
     * 导入转换专业
     */
    private Long convertIPrimaryMajor(String primaryMajor) {
        if (primaryMajor == null) return 0L;
        return switch (primaryMajor) {
            case "空勤专业" -> 1L;
            case "地勤专业" -> 2L;
            default -> 0L;
        };
    }

    /**
     * 格式化日期为 yyyy/M/d 格式
     */
    private String formatDate(java.util.Date date) {
        if (date == null) return "";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/M/d");
        return sdf.format(date);
    }

    /**
     * 解析日期字符串为Date对象
     */
    private java.util.Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/M/d");
            return sdf.parse(dateStr);
        } catch (java.text.ParseException e) {
            // 如果解析失败，返回null
            return null;
        }
    }

    /**
     * 导出转换性别枚举
     */
    private String convertStatus(String status) {
        if (status == null) return "";
        return switch (status) {
            case "0" -> "正常";
            case "1" -> "停用";
            default -> "";
        };
    }
}