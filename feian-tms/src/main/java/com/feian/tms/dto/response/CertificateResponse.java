package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 证书管理响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "证书管理响应对象")
public class CertificateResponse {
    
    @Schema(description = "证书ID", example = "1")
    private Long certificateId;

    @Schema(description = "证书编号", example = "CERT2024001")
    private String certificateCode;

    @Schema(description = "证书名称", example = "Bell-206飞行员培训证书")
    private String certificateName;

    @Schema(description = "学员ID", example = "1")
    private Long studentId;

    @Schema(description = "培训班次ID", example = "1")
    private Long trainingClassId;

    @Schema(description = "机型ID", example = "1")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1")
    private Long majorId;

    @Schema(description = "培训能力ID", example = "1")
    private Long trainingAbilityId;

    @Schema(description = "证书类型（1培训证书 2复训证书 3恢复考试证书）", example = "1")
    private String certificateType;

    @Schema(description = "颁发日期", example = "2024-06-30")
    private Date issueDate;

    @Schema(description = "有效期开始", example = "2024-06-30")
    private Date validFrom;

    @Schema(description = "有效期结束", example = "2026-06-30")
    private Date validUntil;

    @Schema(description = "颁发机构", example = "飞行培训中心")
    private String issueOrganization;

    @Schema(description = "签发人", example = "张主任")
    private String issuer;

    @Schema(description = "证书模板路径", example = "/template/certificate_template.pdf")
    private String templatePath;

    @Schema(description = "证书文件路径", example = "/upload/certificate/cert_001.pdf")
    private String certificateFilePath;

    @Schema(description = "证书状态（0草稿 1已颁发 2已作废 3已过期）", example = "0")
    private String certificateStatus;

    @Schema(description = "作废原因", example = "证书错误")
    private String voidReason;

    @Schema(description = "作废时间", example = "2024-07-01 10:00:00")
    private Date voidTime;

    @Schema(description = "学员姓名", example = "张三")
    private String studentName;

    @Schema(description = "班次名称", example = "第一期培训班")
    private String className;

    @Schema(description = "机型名称", example = "Bell-206")
    private String machineTypeName;

    @Schema(description = "专业名称", example = "飞行员专业")
    private String majorName;

    @Schema(description = "培训能力名称", example = "培训")
    private String trainingAbilityName;

    @Schema(description = "创建者", example = "admin")
    private String createBy;

    @Schema(description = "创建时间", example = "2024-01-01 10:00:00")
    private Date createTime;

    @Schema(description = "更新者", example = "admin")
    private String updateBy;

    @Schema(description = "更新时间", example = "2024-01-01 10:00:00")
    private Date updateTime;

    @Schema(description = "备注", example = "备注信息")
    private String remark;
}