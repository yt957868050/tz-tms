package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 证书管理查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "证书管理查询对象")
public class CertificateQuery extends PageRequest<CertificateQuery> {
    
    @Schema(description = "证书编号", example = "CERT2024001")
    private String certificateCode;

    @Schema(description = "证书名称", example = "Bell-206飞行员培训证书")
    private String certificateName;

    @Schema(description = "学员ID", example = "1")
    private Long studentId;

    @Schema(description = "学员姓名", example = "张三")
    private String studentName;

    @Schema(description = "培训班次ID", example = "1")
    private Long trainingClassId;

    @Schema(description = "机型ID", example = "1")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1")
    private Long majorId;

    @Schema(description = "培训能力ID", example = "1")
    private Long trainingAbilityId;

    @Schema(description = "证书类型（1培训证书 2复训证书 3恢复考试证书）", example = "1", allowableValues = {"1", "2", "3"})
    private String certificateType;

    @Schema(description = "证书状态（0草稿 1已颁发 2已作废 3已过期）", example = "0", allowableValues = {"0", "1", "2", "3"})
    private String certificateStatus;

    @Schema(description = "颁发机构", example = "飞行培训中心")
    private String issueOrganization;

    @Schema(description = "签发人", example = "张主任")
    private String issuer;

    @Schema(description = "颁发日期-开始", example = "2024-01-01")
    private Date issueDate;

    @Schema(description = "有效期结束日期", example = "2026-12-31")
    private Date validUntil;


}