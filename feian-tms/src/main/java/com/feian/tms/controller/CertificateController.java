package com.feian.tms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.R;
import com.feian.tms.domain.Certificate;
import com.feian.tms.domain.MachineType;
import com.feian.tms.domain.Major;
import com.feian.tms.domain.Student;
import com.feian.tms.dto.query.CertificatePageQuery;
import com.feian.tms.dto.query.CertificateQuery;
import com.feian.tms.dto.request.CertificateDeleteRequest;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.request.CertificateRequest;
import com.feian.tms.dto.response.CertificateDetailsResponse;
import com.feian.tms.dto.response.CertificatePageQueryResponse;
import com.feian.tms.dto.response.CertificatePageResponse;
import com.feian.tms.dto.response.CertificateResponse;
import com.feian.tms.excel.CertificateExcel;
import com.feian.tms.service.CertificateService;
import com.feian.tms.service.MachineTypeService;
import com.feian.tms.service.MajorService;
import com.feian.tms.service.StudentService;
import com.feian.tms.utils.EasyExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

        CertificateDetailsResponse response =CertificateDetailsResponse
                .builder()
                .certificateId(entity.getCertificateId())
                .certificateNumber(entity.getCertificateCode())
                .certificateName(entity.getCertificateName())
                .studentId(entity.getStudentId())
                .trainingClassId(entity.getTrainingClassId())
                .machineTypeId(entity.getMachineTypeId())
                .majorId(entity.getMajorId())
                .trainingAbilityId(entity.getTrainingAbilityId())
                .certificateType(entity.getCertificateType())
                .issueDate(entity.getIssueDate())
                .expiryDate(entity.getValidUntil())
                .status(entity.getCertificateStatus())
                .issuingAuthority(entity.getIssueOrganization())
                .remark(entity.getRemark())
                .certificateDescription(entity.getCertificateDescription())
                .build();


        return R.success(response);
    }

    /**
     * 新增证书管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增证书", description = "创建新的证书信息")
    public R create( @RequestBody CertificateRequest request) {
        Long trainingClassId=1L;
        Certificate entity =Certificate.builder()
                .certificateDescription(request.getCertificateDescription())
                .certificateCode(request.getCertificateNumber())
                .certificateType(request.getCertificateType())
                .issueDate(request.getIssueDate())
                .validUntil(request.getExpiryDate())
                .issueOrganization(request.getIssuingAuthority())
                .machineTypeId(request.getMachineTypeId())
                .majorId(request.getMajorId())
                .remark(request.getRemark())
                .certificateStatus(request.getStatus())
                .studentId(request.getStudentId())
                .trainingClassId(trainingClassId)
                .trainingAbilityId(trainingClassId)
                .createTime(request.getIssueDate())
                .updateTime(request.getIssueDate())
                .certificateName("01证书")
                .build();

        Student studentById = studentService.getById(entity.getStudentId());
        MachineType machineTypeById = machineTypeService.getById(entity.getMachineTypeId());
        Major majorById = majorService.getById(entity.getMajorId());
        entity.setStudentName(studentById.getStudentName());
        entity.setMachineTypeName(machineTypeById.getMachineTypeName());
        entity.setMajorName(majorById.getMajorName());
        certificateService.saveCertificate(entity);
        return R.success();

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
        if (request.getCertificateId() == null) {
            return R.fail("证书ID不能为空");
        }
        
        Certificate entity = Certificate.builder()
                .certificateId(request.getCertificateId())
                .certificateDescription(request.getCertificateDescription())
                .certificateCode(request.getCertificateNumber())
                .certificateType(request.getCertificateType())
                .certificateName(request.getCertificateName())
                .issueDate(request.getIssueDate())
                .validUntil(request.getExpiryDate())
                .issueOrganization(request.getIssuingAuthority())
                .machineTypeId(request.getMachineTypeId())
                .majorId(request.getMajorId())
                .remark(request.getRemark())
                .certificateStatus(request.getStatus())
                .studentId(request.getStudentId())
                .build();

        Student studentById = studentService.getById(entity.getStudentId());
        MachineType machineTypeById = machineTypeService.getById(entity.getMachineTypeId());
        Major majorById = majorService.getById(entity.getMajorId());
        entity.setStudentName(studentById.getStudentName());
        entity.setMachineTypeName(machineTypeById.getMachineTypeName());
        entity.setMajorName(majorById.getMajorName());
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
    public R<Void> delete(@RequestBody CertificateDeleteRequest certificateDeleteRequest) {
        boolean result = certificateService.deleteBatch(certificateDeleteRequest.getIdList());
        if (result) {
            return R.success();
        }
        return R.fail("删除证书失败");
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
    public void export(HttpServletResponse response, @RequestBody CertificateQuery query) {
        // 查询所有数据（不分页）
        var queryWrapper = certificateService.lambdaQuery()
                .like(query.getCertificateCode() != null, Certificate::getCertificateCode, query.getCertificateCode())
                .like(query.getCertificateName() != null, Certificate::getCertificateName, query.getCertificateName())
                .eq(query.getStudentId() != null, Certificate::getStudentId, query.getStudentId())
                .eq(query.getTrainingClassId() != null, Certificate::getTrainingClassId, query.getTrainingClassId())
                .eq(query.getMachineTypeId() != null, Certificate::getMachineTypeId, query.getMachineTypeId())
                .eq(query.getMajorId() != null, Certificate::getMajorId, query.getMajorId())
                .eq(query.getTrainingAbilityId() != null, Certificate::getTrainingAbilityId, query.getTrainingAbilityId())
                .eq(query.getCertificateType() != null, Certificate::getCertificateType, query.getCertificateType())
                .ge(query.getIssueDate() != null, Certificate::getIssueDate, query.getIssueDate())
                .le(query.getValidUntil() != null, Certificate::getValidUntil, query.getValidUntil())
                .like(query.getIssueOrganization() != null, Certificate::getIssueOrganization, query.getIssueOrganization())
                .eq(query.getCertificateStatus() != null, Certificate::getCertificateStatus, query.getCertificateStatus())
                .orderByDesc(Certificate::getCreateTime);
        
        List<Certificate> list = queryWrapper.list();
        
        // 转换为导出对象
        List<CertificateExcel> excelList = list.stream()
                .map(entity -> {
                    CertificateExcel excel = new CertificateExcel();
                    BeanUtils.copyProperties(entity, excel);
                    // 转换枚举值为中文显示
                    excel.setCertificateTypeName(convertCertificateType(entity.getCertificateType()));
                    excel.setCertificateStatusName(convertCertificateStatus(entity.getCertificateStatus()));
                    return excel;
                })
                .toList();
        
        // 使用EasyExcel导出
        EasyExcelUtil.exportExcel(response, "证书管理", "证书列表", CertificateExcel.class, excelList);
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
}