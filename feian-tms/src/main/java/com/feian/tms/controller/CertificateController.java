package com.feian.tms.controller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.*;
import com.feian.tms.dto.certificateDto.CertificateEvidence;
import com.feian.tms.dto.certificateDto.CertificateOfCompliance;
import com.feian.tms.dto.query.CertificatePageQuery;
import com.feian.tms.dto.query.CertificateQuery;
import com.feian.tms.dto.request.*;
import com.feian.tms.dto.response.CertificateDetailsResponse;
import com.feian.tms.dto.response.CertificatePageQueryResponse;
import com.feian.tms.dto.response.CertificatePageResponse;
import com.feian.tms.dto.response.CertificateResponse;
import com.feian.tms.excel.CertificateExcel;
import com.feian.tms.excel.StudentExcel;
import com.feian.tms.service.*;
import com.feian.tms.utils.EasyExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 证书管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/certificate")
@RequiredArgsConstructor
@Tag(name = "证书管理", description = "证书管理相关接口")
public class CertificateController {
    @Autowired
    private  CertificateService certificateService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MachineTypeService machineTypeService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private TrainingPlanService trainingPlanService;
    @Autowired
    private ClassStudentService classStudentService;
    @Autowired
    private TrainingRecordService trainingRecordService;
    @Autowired
    private TrainingClassService trainingClassService;

    /**
     * 查询证书管理列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询证书管理列表", description = "根据查询条件分页查询证书列表")
    public R<CertificatePageResponse> list(@RequestBody CertificatePageQuery certificatePageQuery){

         CertificatePageResponse certificatePageResponse=certificateService.pageQuery(certificatePageQuery);
        return R.success(certificatePageResponse);
    }
//    public R<Page<CertificateResponse>> list(@RequestBody CertificateQuery query) {
//        Page<Certificate> page = new Page<>(query.getPageNum(), query.getPageSize());
//
//        // 构建查询条件
//        var queryWrapper = certificateService.lambdaQuery()
//                .like(query.getCertificateCode() != null, Certificate::getCertificateCode, query.getCertificateCode())
//                .like(query.getCertificateName() != null, Certificate::getCertificateName, query.getCertificateName())
//                .eq(query.getStudentId() != null, Certificate::getStudentId, query.getStudentId())
//                .eq(query.getTrainingClassId() != null, Certificate::getTrainingClassId, query.getTrainingClassId())
//                .eq(query.getMachineTypeId() != null, Certificate::getMachineTypeId, query.getMachineTypeId())
//                .eq(query.getMajorId() != null, Certificate::getMajorId, query.getMajorId())
//                .eq(query.getTrainingAbilityId() != null, Certificate::getTrainingAbilityId, query.getTrainingAbilityId())
//                .eq(query.getCertificateType() != null, Certificate::getCertificateType, query.getCertificateType())
//                .ge(query.getIssueDate() != null, Certificate::getIssueDate, query.getIssueDate())
//                .le(query.getValidUntil() != null, Certificate::getValidUntil, query.getValidUntil())
//                .like(query.getIssueOrganization() != null, Certificate::getIssueOrganization, query.getIssueOrganization())
//                .eq(query.getCertificateStatus() != null, Certificate::getCertificateStatus, query.getCertificateStatus())
//                .orderByDesc(Certificate::getCreateTime);
//
//        Page<Certificate> result = queryWrapper.page(page);
//
//        // 转换为响应对象
//        Page<CertificateResponse> responsePage = new Page<>();
//        BeanUtils.copyProperties(result, responsePage);
//
//        var responseList = result.getRecords().stream()
//                .map(entity -> {
//                    CertificateResponse response = new CertificateResponse();
//                    BeanUtils.copyProperties(entity, response);
//                    return response;
//                })
//                .toList();
//
//        responsePage.setRecords(responseList);
//        return R.success(responsePage);
//    }

    /**
     * 获取证书管理详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取证书详细信息", description = "根据ID获取证书详细信息")
    public R<CertificateDetailsResponse> detail(@Valid @RequestBody IdRequest request) {
        Certificate entity = certificateService.getById(request.getId());
        if (entity == null) {
            return R.fail("证书信息不存在");
        }

        CertificateDetailsResponse response =new CertificateDetailsResponse();
        BeanUtils.copyProperties(entity,response);

        return R.success(response);
    }

    /**
     * 新增证书管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增证书", description = "创建新的证书信息")
    public R create( @RequestBody CertificateRequest request) {
        Student student = studentService.getById(request.getStudentId());
        Certificate entity=new Certificate();
        BeanUtils.copyProperties(request,entity);
        entity.setCertificateCode(request.getStudentCode());
        entity.setEngStudentName(student.getEngStudentName());
        Long classId=classStudentService.getClassIdByStudent(request.getStudentId());
        TrainingClass trainingClass=trainingClassService.getById(classId);
        entity.setTrainingCourse(trainingClass.getClassName());
        entity.setEngTrainingCourse(trainingClass.getEngTrainingCourse());
        Long planId=trainingPlanService.getPlanIdByClass(classId);
        entity.setStartDate(trainingPlanService.getStartDateById(planId));
        entity.setEndDate(trainingPlanService.getEndDate(planId));
        entity.setTotalHours(trainingPlanService.getTotalHoursById(planId));
        entity.setTheoryHours(trainingPlanService.getTheoryHoursById(planId));
        entity.setPracticeHours(trainingPlanService.getPracticeHoursById(planId));
        entity.setTheoryScore(trainingRecordService.getTheoryScoreByStuId(request.getStudentId()));
        entity.setPracticeScore(trainingRecordService.getPracticeScoreByStuId(request.getStudentId()));
        certificateService.save(entity);
        return R.success();
//        Certificate entity =Certificate.builder()
//                .certificateCode(request.getCertificateNumber())
//                .certificateType(request.getCertificateType())
//                .issueDate(request.getIssueDate())
//                .validUntil(request.getExpiryDate())
//                .issueOrganization(request.getIssuingAuthority())
//                .machineTypeId(request.getMachineTypeId())
//                .majorId(request.getMajorId())
//                .remark(request.getRemark())
//                .certificateStatus(request.getStatus())
//                .studentId(request.getStudentId())
//                .trainingClassId(trainingClassId)
//                .trainingAbilityId(trainingClassId)
//                .createTime(request.getIssueDate())
//                .updateTime(request.getIssueDate())
//                .build();

//        Student studentById = studentService.getById(entity.getStudentId());
//        MachineType machineTypeById = machineTypeService.getById(entity.getMachineTypeId());
//        Major majorById = majorService.getById(entity.getMajorId());
//        entity.setStudentName(studentById.getStudentName());
//
//        certificateService.saveCertificate(entity);


//        Certificate entity = new Certificate();
//        BeanUtils.copyProperties(request, entity);
//
//        boolean result = certificateService.save(entity);
//        if (result) {
//            CertificateResponse response = new CertificateResponse();
//            BeanUtils.copyProperties(entity, response);
//            return R.success(response);
//        }
//        return R.fail("新增证书失败");
    }

    /**
     * 修改证书管理
     */
    @PostMapping("/update")
    @Operation(summary = "修改证书", description = "更新现有证书信息")
    public R<CertificateResponse> update(@Valid @RequestBody CertificateRequest request) {
        Student student = studentService.getById(request.getStudentId());
        Certificate entity=new Certificate();
        BeanUtils.copyProperties(request,entity);
        entity.setCertificateCode(request.getStudentCode());
        entity.setEngStudentName(student.getEngStudentName());
        Long classId=classStudentService.getClassIdByStudent(request.getStudentId());
        TrainingClass trainingClass=trainingClassService.getById(classId);
        entity.setTrainingCourse(trainingClass.getClassName());
        entity.setEngTrainingCourse(trainingClass.getEngTrainingCourse());
        Long planId=trainingPlanService.getPlanIdByClass(classId);
        entity.setStartDate(trainingPlanService.getStartDateById(planId));
        entity.setEndDate(trainingPlanService.getEndDate(planId));
        entity.setTotalHours(trainingPlanService.getTotalHoursById(planId));
        entity.setTheoryHours(trainingPlanService.getTheoryHoursById(planId));
        entity.setPracticeHours(trainingPlanService.getPracticeHoursById(planId));
        entity.setTheoryScore(trainingRecordService.getTheoryScoreByStuId(request.getStudentId()));
        entity.setPracticeScore(trainingRecordService.getPracticeScoreByStuId(request.getStudentId()));

        boolean result = certificateService.updateById(entity);
        if (result) {
            CertificateResponse response = new CertificateResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新证书失败");
    }

    /**
     * 删除证书管理
     */
    @PostMapping("/delete")
    @Operation(summary = "删除证书", description = "根据ID删除证书信息")
    public R<Void> delete(@Valid @RequestBody IdsDeleteRequest idsDeleteRequest) {
        boolean result = certificateService.removeByIds(idsDeleteRequest.getIdList());
        if (result) {
            return R.success();
        }
        return R.fail("删除证书失败");
    }


    /**
     * 批量添加学员证书
     */
    @PostMapping("/batch-add-certificates")
    @Operation(summary = "批量添加学员证书", description = "批量添加学员证书")
    public R<String> batchAddStudents(@jakarta.validation.Valid @RequestBody CertificateBatchAddRequest request) {
        try {
            int successCount = 0;
            int failCount = 0;
            StringBuilder failMessages = new StringBuilder();

            for (Long studentId : request.getStudentIds()) {
                Student student = studentService.getById(studentId);
                // 检查学员是否已在班次中
                Certificate existing = certificateService.lambdaQuery()
                        .eq(Certificate::getStudentId, studentId)
                        .one();

                if (existing != null) {
                    failCount++;
                    failMessages.append("学员[").append(student.getStudentCode()).append(student.getStudentName()).append("]已经存在证书; ");
                    continue;
                }

                // 创建对应学员证书
                Certificate entity=new Certificate();
                entity.setStudentId(studentId);
                entity.setCertificateCode(student.getStudentCode());
                entity.setStudentName(student.getStudentName());
                entity.setStudentCode(student.getStudentCode());
                entity.setEngStudentName(student.getEngStudentName());
                Long classId=classStudentService.getClassIdByStudent(studentId);
                TrainingClass trainingClass=trainingClassService.getById(classId);
                entity.setTrainingCourse(trainingClass.getClassName());
                entity.setEngTrainingCourse(trainingClass.getEngTrainingCourse());
                Long planId=trainingPlanService.getPlanIdByClass(classId);
                entity.setStartDate(trainingPlanService.getStartDateById(planId));
                entity.setEndDate(trainingPlanService.getEndDate(planId));
                entity.setTotalHours(trainingPlanService.getTotalHoursById(planId));
                entity.setTheoryHours(trainingPlanService.getTheoryHoursById(planId));
                entity.setPracticeHours(trainingPlanService.getPracticeHoursById(planId));
                entity.setTheoryScore(trainingRecordService.getTheoryScoreByStuId(studentId));
                entity.setPracticeScore(trainingRecordService.getPracticeScoreByStuId(studentId));

                boolean result = certificateService.save(entity);
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



//    public R<Void> delete(@Valid @RequestBody IdRequest request) {
//        boolean result = certificateService.removeById(request.getId());
//        if (result) {
//            return R.success();
//        }
//        return R.fail("删除证书失败");
//    }

    /**
     * 导出证书管理列表（使用EasyExcel）
     */
    @PostMapping("/export")
    @Operation(summary = "导出证书列表", description = "根据查询条件导出证书列表到Excel")
    public void export(HttpServletResponse response, @RequestBody IdsRequest idsRequest) {
        List<Certificate> list;
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
            list =certificateService.certificateList();
        }else {
            // 根据ID查询所有数据（不分页）
            list =certificateService.listByIds(idsRequest.getIdList());
        }
//        var queryWrapper = certificateService.lambdaQuery()
//                .in(Certificate::getCertificateId, idsRequest.getIdList());
//        var queryWrapper = certificateService.lambdaQuery()
//                .like(query.getCertificateCode() != null, Certificate::getCertificateCode, query.getCertificateCode())
//                .like(query.getCertificateName() != null, Certificate::getCertificateName, query.getCertificateName())
//                .eq(query.getStudentId() != null, Certificate::getStudentId, query.getStudentId())
//                .eq(query.getTrainingClassId() != null, Certificate::getTrainingClassId, query.getTrainingClassId())
//                .eq(query.getMachineTypeId() != null, Certificate::getMachineTypeId, query.getMachineTypeId())
//                .eq(query.getMajorId() != null, Certificate::getMajorId, query.getMajorId())
//                .eq(query.getTrainingAbilityId() != null, Certificate::getTrainingAbilityId, query.getTrainingAbilityId())
//                .eq(query.getCertificateType() != null, Certificate::getCertificateType, query.getCertificateType())
//                .ge(query.getIssueDate() != null, Certificate::getIssueDate, query.getIssueDate())
//                .le(query.getValidUntil() != null, Certificate::getValidUntil, query.getValidUntil())
//               .like(query.getIssueOrganization() != null, Certificate::getIssueOrganization, query.getIssueOrganization())
//                .eq(query.getCertificateStatus() != null, Certificate::getCertificateStatus, query.getCertificateStatus())
//                .orderByDesc(Certificate::getCreateTime);

//        List<Certificate> list = queryWrapper.list();

        // 转换为导出对象
        List<CertificateExcel> excelList = list.stream()
                .map(entity -> {
                    CertificateExcel excel = new CertificateExcel();
                    excel.setCertificateCode(entity.getCertificateCode());
                    excel.setStudentCode(entity.getStudentCode());
                    excel.setStudentName(entity.getStudentName());
                    excel.setEngStudentName(entity.getEngStudentName());
                    excel.setTrainingCourse(entity.getTrainingCourse());
                    excel.setEngTrainingCourse(entity.getEngTrainingCourse());
                    excel.setStartDate(formatDate(entity.getStartDate()));
                    excel.setEndDate(formatDate(entity.getEndDate()));

                    // 转换枚举值为中文显示
                    return excel;
                })
                .toList();

        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "证书管理", "证书列表", CertificateExcel.class, excelList);
    }

    /**
     * 下载证书导入模板
     */
    @PostMapping("/importTemplate")
    @Operation(summary = "下载证书导入模板", description = "下载证书信息Excel导入模板")
    public void importTemplate(HttpServletResponse response) {
        // 生成空数据模板
        List<CertificateExcel> emptyList = java.util.Collections.emptyList();
        EasyExcelUtil.exportExcel(response, "学员信息导入模板", "学员信息", CertificateExcel.class, emptyList);
    }



    /**
     * 导入证书信息
     */
    @PostMapping("/importData")
    @Operation(summary = "导入证书信息", description = "通过Excel批量导入证书信息")
    @Transactional(rollbackFor = Exception.class) // 添加事务注解，确保异常时回滚
    public R<String> importData(@RequestParam("file") MultipartFile file) {
        try {
            ReadListener<CertificateExcel> listener = new ReadListener<CertificateExcel>() {
                // 存储从Excel读取的数据
                private final List<CertificateExcel> dataList = new ArrayList<>();

                @Override
                public void invoke(CertificateExcel data, AnalysisContext context) {
                    dataList.add(data);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    // 收集所有需要保存的实体
                    List<Certificate> entities = new ArrayList<>();
                    for (CertificateExcel excel : dataList) {
                        Long studentId=studentService.getStudentIdByCode(excel.getStudentCode());
                        Long classId=classStudentService.getClassIdByStudent(studentId);
                        Long planId=trainingPlanService.getPlanIdByClass(classId);
                        Certificate entity = new Certificate();
                        entity.setCertificateCode(excel.getCertificateCode());
                        entity.setStudentId(studentId);
                        entity.setStudentCode(excel.getStudentCode());
                        entity.setStudentName(excel.getStudentName());
                        entity.setEngStudentName(excel.getEngStudentName());
                        entity.setTrainingCourse(excel.getTrainingCourse());
                        entity.setEngTrainingCourse(excel.getEngTrainingCourse());
                        entity.setStartDate(parseDate(excel.getStartDate()));
                        entity.setEndDate(parseDate(excel.getEndDate()));
                        entity.setTotalHours(trainingPlanService.getTotalHoursById(planId));
                        entity.setTheoryHours(trainingPlanService.getTheoryHoursById(planId));
                        entity.setPracticeHours(trainingPlanService.getPracticeHoursById(planId));

                        entities.add(entity);
                    }
                    // 批量保存到数据库
                    certificateService.saveBatch(entities);
                }
            };
            EasyExcelUtil.readExcel(file.getInputStream(), CertificateExcel.class, listener);
            return R.success("导入成功");
        } catch (Exception e) {
            // 捕获异常，抛出运行时异常，让Spring事务管理器进行回滚
            // 提示：你可能需要根据业务逻辑，对 e.getMessage() 进行更友好的封装
            throw new RuntimeException("导入失败: " + e.getMessage(), e);
        }
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
     * 转换证书类型枚举
     */
    private String convertCertificateType(String certificateType) {
        if (certificateType == null) return "";
        return switch (certificateType) {
            case "1" -> "培训证书";
            case "2" -> "复训证书";
            case "3" -> "恢复考试证书";
            default -> "";
        };
    }

    /**
     * 转换证书状态枚举
     */
    private String convertCertificateStatus(String certificateStatus) {
        if (certificateStatus == null) return "";
        return switch (certificateStatus) {
            case "0" -> "草稿";
            case "1" -> "已颁发";
            case "2" -> "已作废";
            case "3" -> "已过期";
            default -> "";
        };
    }

    @PostMapping("/exportWord")
    @Operation(summary = "导出培训合格证Word")
    public void exportWord(HttpServletResponse response, @RequestBody CertificateRequest request) {
        CertificateOfCompliance data = new CertificateOfCompliance();
        BeanUtils.copyProperties(request,data);
        //获取时间年月日
        LocalDateTime localStartTime = LocalDateTime.ofInstant(request.getStartDate().toInstant(), ZoneId.systemDefault());
        LocalDateTime localEndTime = LocalDateTime.ofInstant(request.getEndDate().toInstant(), ZoneId.systemDefault());
        String startYear = String.valueOf(localStartTime.getYear());
        String startMonth = String.format("%02d", localStartTime.getMonthValue());
        String startDay = String.format("%02d", localStartTime.getDayOfMonth());
        String engStartDate = startYear + "." + startMonth + "." + startDay;
        String endYear = String.valueOf(localEndTime.getYear());
        String endMonth = String.format("%02d", localEndTime.getMonthValue());
        String endDay = String.format("%02d", localEndTime.getDayOfMonth());
        String engEndDate = endYear + "." + endMonth + "." + endDay;

        //获取时长
        Long classId=classStudentService.getClassIdByStudent(request.getStudentId());
        Long planId=trainingPlanService.getPlanIdByClass(classId);
        String engTotalHours = trainingPlanService.getTotalHoursById(planId) + "h";
        String engTheoryHours = trainingPlanService.getTheoryHoursById(planId) + "h";
        String engPracticeHours = trainingPlanService.getPracticeHoursById(planId) + "h";

        //对dto进行赋值
        data.setIdCard(studentService.getIdCardById(request.getStudentId()));
        data.setStartYear(startYear);
        data.setStartMonth(startMonth);
        data.setStartDay(startDay);
        data.setEngStartDate(engStartDate);
        data.setEndYear(endYear);
        data.setEndMonth(endMonth);
        data.setEndDay(endDay);
        data.setEngEndDate(engEndDate);
        data.setEngTotalHours(engTotalHours);
        data.setEngTheoryHours(engTheoryHours);
        data.setEngPracticeHours(engPracticeHours);

        //转换成Map
        Map<String, String> vars = new HashMap<>();
        vars.put("certificateCode", data.getCertificateCode());
        vars.put("studentName", nz(data.getStudentName()));
        vars.put("engStudentName", nz(data.getEngStudentName()));
        vars.put("idCard",nz(data.getIdCard()));
        vars.put("trainingCourse", nz(data.getTrainingCourse()));
        vars.put("engTrainingCourse", nz(data.getEngTrainingCourse()));
        vars.put("startYear",nz(data.getStartYear()));
        vars.put("startMonth",nz(data.getStartMonth()));
        vars.put("startDay",nz(data.getStartDay()));
        vars.put("engStartDate", nz(data.getEngStartDate()));
        vars.put("endYear",nz(data.getEndYear()));
        vars.put("endMonth",nz(data.getEndMonth()));
        vars.put("endDay",nz(data.getEndDay()));
        vars.put("engEndDate", nz(data.getEngEndDate()));
        vars.put("engTotalHours", nz(data.getEngTotalHours()));
        vars.put("engTheoryHours", nz(data.getEngTheoryHours()));
        vars.put("engPracticeHours", nz(data.getEngPracticeHours()));

        String templatePath = "CertificateTemplate.docx"; //word模板路径
        try (InputStream in = new org.springframework.core.io.ClassPathResource(templatePath).getInputStream();
             org.apache.poi.xwpf.usermodel.XWPFDocument doc = new org.apache.poi.xwpf.usermodel.XWPFDocument(in)) {

            replacePlaceholdersDocx(doc, vars);

            String fileName = "培训合格证-" + java.net.URLEncoder.encode(data.getStudentName(), java.nio.charset.StandardCharsets.UTF_8).replace("+", "%20") + ".docx";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"; filename*=UTF-8''" + fileName);
            try (OutputStream os = response.getOutputStream()) {
                doc.write(os);
                os.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException("导出Word失败: " + e.getMessage(), e);
        }
    }


    @PostMapping("/exportEvidence")
    @Operation(summary = "导出培训证明Word")
    public void exportEvidence(HttpServletResponse response, @RequestBody CertificateRequest request) {
        CertificateEvidence data = new CertificateEvidence();
        BeanUtils.copyProperties(request,data);
        //获取时间年月日
        LocalDateTime localStartTime = LocalDateTime.ofInstant(request.getStartDate().toInstant(), ZoneId.systemDefault());
        LocalDateTime localEndTime = LocalDateTime.ofInstant(request.getEndDate().toInstant(), ZoneId.systemDefault());
        String startYear = String.valueOf(localStartTime.getYear());
        String startMonth = String.format("%02d", localStartTime.getMonthValue());
        String startDay = String.format("%02d", localStartTime.getDayOfMonth());
        String engStartDate = startYear + "." + startMonth + "." + startDay;
        String endYear = String.valueOf(localEndTime.getYear());
        String endMonth = String.format("%02d", localEndTime.getMonthValue());
        String endDay = String.format("%02d", localEndTime.getDayOfMonth());
        String engEndDate = endYear + "." + endMonth + "." + endDay;

        //获取时长
        Long classId=classStudentService.getClassIdByStudent(request.getStudentId());
        Long planId=trainingPlanService.getPlanIdByClass(classId);
        String engTotalHours = trainingPlanService.getTotalHoursById(planId) + "h";
        String engTheoryHours = trainingPlanService.getTheoryHoursById(planId) + "h";
        String engPracticeHours = trainingPlanService.getPracticeHoursById(planId) + "h";

        //对dto进行赋值
        data.setIdCard(studentService.getIdCardById(request.getStudentId()));
        data.setStartYear(startYear);
        data.setStartMonth(startMonth);
        data.setStartDay(startDay);
        data.setEngStartDate(engStartDate);
        data.setEndYear(endYear);
        data.setEndMonth(endMonth);
        data.setEndDay(endDay);
        data.setEngEndDate(engEndDate);
        data.setEngTotalHours(engTotalHours);
        data.setEngTheoryHours(engTheoryHours);
        data.setEngPracticeHours(engPracticeHours);

        //转换成Map
        Map<String, String> vars = new HashMap<>();
        vars.put("studentName", nz(data.getStudentName()));
        vars.put("engStudentName", nz(data.getEngStudentName()));
        vars.put("idCard",nz(data.getIdCard()));
        vars.put("trainingCourse", nz(data.getTrainingCourse()));
        vars.put("engTrainingCourse", nz(data.getEngTrainingCourse()));
        vars.put("startYear",nz(data.getStartYear()));
        vars.put("startMonth",nz(data.getStartMonth()));
        vars.put("startDay",nz(data.getStartDay()));
        vars.put("engStartDate", nz(data.getEngStartDate()));
        vars.put("endYear",nz(data.getEndYear()));
        vars.put("endMonth",nz(data.getEndMonth()));
        vars.put("endDay",nz(data.getEndDay()));
        vars.put("engEndDate", nz(data.getEngEndDate()));
        vars.put("engTotalHours", nz(data.getEngTotalHours()));
        vars.put("engTheoryHours", nz(data.getEngTheoryHours()));
        vars.put("engPracticeHours", nz(data.getEngPracticeHours()));

        String templatePath = "EvidenceTemplate.docx"; //word模板路径
        try (InputStream in = new org.springframework.core.io.ClassPathResource(templatePath).getInputStream();
             org.apache.poi.xwpf.usermodel.XWPFDocument doc = new org.apache.poi.xwpf.usermodel.XWPFDocument(in)) {

            replacePlaceholdersDocx(doc, vars);

            String fileName = "培训证明-" + java.net.URLEncoder.encode(data.getStudentName(), java.nio.charset.StandardCharsets.UTF_8).replace("+", "%20") + ".docx";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"; filename*=UTF-8''" + fileName);
            try (OutputStream os = response.getOutputStream()) {
                doc.write(os);
                os.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException("导出Word失败: " + e.getMessage(), e);
        }
    }

    private String nz(String v) { return v == null ? "" : v; }

    private void replacePlaceholdersDocx(org.apache.poi.xwpf.usermodel.XWPFDocument doc, Map<String, String> vars) {
        for (var p : doc.getParagraphs()) {
            replaceInRuns(p.getRuns(), vars);
        }
        for (var table : doc.getTables()) {
            for (var row : table.getRows()) {
                for (var cell : row.getTableCells()) {
                    for (var p : cell.getParagraphs()) {
                        replaceInRuns(p.getRuns(), vars);
                    }
                }
            }
        }
    }

    private void replaceInRuns(List<org.apache.poi.xwpf.usermodel.XWPFRun> runs, Map<String, String> vars) {
        if (runs == null || runs.isEmpty()) return;

        // 预取每个 run 的文本
        List<String> texts = new ArrayList<>(runs.size());
        for (var r : runs) texts.add(r.toString());

        // 扫描跨 Run 的 {{key}} 片段
        int i = 0;
        while (i < texts.size()) {
            // 寻找 "{{"
            int startRun = -1, startIdx = -1;
            for (; i < texts.size(); i++) {
                String t = texts.get(i);
                if (t == null) t = "";
                int idx = t.indexOf("{{");
                if (idx >= 0) { startRun = i; startIdx = idx; break; }
            }
            if (startRun == -1) break;

            // 从 startRun 开始寻找 "}}"
            int endRun = -1, endIdx = -1;
            StringBuilder placeholder = new StringBuilder();
            // 先拼接 startRun 这一段 "{{" 后面的部分
            String ts = texts.get(startRun);
            placeholder.append(ts.substring(startIdx));
            int j = startRun;
            for (; j < texts.size(); j++) {
                String t = texts.get(j);
                if (t == null) t = "";
                int searchFrom = (j == startRun) ? startIdx + 2 : 0;
                int end = t.indexOf("}}", searchFrom);
                if (j != startRun) placeholder.append(t);
                if (end >= 0) {
                    endRun = j; endIdx = end + 1; // 第二个 } 的位置
                    break;
                }
            }
            if (endRun == -1) {
                // 没有匹配到 "}}": 终止（避免误替换）
                break;
            }

            // 解析 key：去掉外层 {{ }}
            String raw = placeholder.toString();
            int open = raw.indexOf("{{");
            int close = raw.indexOf("}}");
            String key = (open >= 0 && close > open) ? raw.substring(open + 2, close).trim() : null;

            String replacement = (key != null && vars.containsKey(key)) ? (vars.get(key) == null ? "" : vars.get(key)) : null;
            if (replacement != null) {
                // 执行替换：只在 startRun 写入替换后文本，其它涉及的 run 清空相关内容
                // 1) startRun：保留 "{{" 前面的前缀 + replacement + (若 endRun 与 startRun 同一 run，保留 "}}" 之后的后缀)
                String startText = texts.get(startRun);
                String prefix = startText.substring(0, startIdx);
                String suffixStartRun = "";
                if (startRun == endRun) {
                    String endText = texts.get(endRun);
                    suffixStartRun = endText.substring(endIdx + 1); // endIdx 指向第二个 '}', 再 +1 取之后内容
                }
                String newStart = prefix + replacement + suffixStartRun;
                runs.get(startRun).setText(newStart, 0);
                texts.set(startRun, newStart);

                // 2) 中间的 run（startRun+1 .. endRun-1）清空
                for (int k = startRun + 1; k < endRun; k++) {
                    runs.get(k).setText("", 0);
                    texts.set(k, "");
                }

                // 3) endRun：仅保留 "}}" 后的后缀（如果 endRun != startRun）
                if (endRun != startRun) {
                    String endText = texts.get(endRun);
                    String suffix = (endIdx + 1 < endText.length()) ? endText.substring(endIdx + 1) : "";
                    runs.get(endRun).setText(suffix, 0);
                    texts.set(endRun, suffix);
                }

                // 移动 i 到 endRun 之后继续
                i = endRun + 1;
            } else {
                // 未命中变量表：跳过这个 "{{...}}"，继续向后
                i = endRun + 1;
            }
        }

        // 最后，对每个 run 内的“非跨 run”占位符（完整在单个 run 内）做一次直替
        for (int r = 0; r < runs.size(); r++) {
            String t = runs.get(r).toString();
            if (t == null || t.isEmpty()) continue;
            String replaced = t;
            for (var e : vars.entrySet()) {
                replaced = replaced.replace("{{" + e.getKey() + "}}", e.getValue() == null ? "" : e.getValue());
            }
            if (!replaced.equals(t)) runs.get(r).setText(replaced, 0);
        }
    }


}
