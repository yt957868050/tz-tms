package com.feian.tms.dto.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * 证书管理请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "证书管理请求对象")
public class CertificateRequest {
    
    @Schema(description = "证书ID", example = "1")
    private Long certificateId;

    @Schema(description = "证书编号", example = "CERT2024001", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "证书编号不能为空")
    private String certificateNumber;

    @Schema(description = "证书名称", example = "Bell-206飞行员培训证书", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "证书名称不能为空")
    private String certificateName;

    @Schema(description = "学员ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "学员ID不能为空")
    private Long studentId;

    @Schema(description = "培训班次ID", example = "1")
    private Long trainingClassId;

    @Schema(description = "机型ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "机型ID不能为空")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "专业ID不能为空")
    private Long majorId;

    @Schema(description = "培训能力ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "培训能力ID不能为空")
    private Long trainingAbilityId;

    @Schema(description = "证书类型", example = "1", allowableValues = {"1", "2", "3"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "证书类型不能为空")
    private String certificateType;

    @Schema(description = "颁发日期", example = "2024-06-30", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "颁发日期不能为空")
    private Date issueDate;

    @Schema(description = "有效期开始", example = "2024-06-30", requiredMode = Schema.RequiredMode.REQUIRED)
    private Date validFrom;

    @Schema(description = "有效期结束", example = "2026-06-30", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "有效期结束不能为空")
    private Date expiryDate;

    @Schema(description = "颁发机构", example = "飞行培训中心")
    private String issuingAuthority;

    @Schema(description = "签发人", example = "张主任")
    private String issuer;

    @Schema(description = "证书模板路径", example = "/template/certificate_template.pdf")
    private String templatePath;

    @Schema(description = "证书文件路径", example = "/upload/certificate/cert_001.pdf")
    private String certificateFilePath;

    @Schema(description = "证书状态", example = "0", allowableValues = {"0", "1", "2", "3"})
    private String status;

    @Schema(description = "作废原因", example = "证书错误")
    private String voidReason;

    @Schema(description = "作废时间", example = "2024-07-01 10:00:00")
    private Date voidTime;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

    @Schema(description = "证书描述", example = "该证书用于证明学员完成Bell-206直升机培训。")
    private String certificateDescription;
}