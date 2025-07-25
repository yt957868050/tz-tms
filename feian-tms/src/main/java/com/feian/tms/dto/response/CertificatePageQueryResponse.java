package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificatePageQueryResponse {

    @Schema(description = "证书ID", example = "1")
    private Long certificateId;

    @Schema(description = "证书编号", example = "CERT2024001")
    private String certificateNumber;

    @Schema(description = "学员姓名", example = "张三")
    private String studentName;

    @Schema(description = "机型名称", example = "Bell-206")
    private String machineTypeName;

    @Schema(description = "专业名称", example = "飞行员专业")
    private String majorName;

    @Schema(description = "证书类型（1培训证书 2复训证书 3恢复考试证书）", example = "1")
    private String certificateType;

    @Schema(description = "颁发日期", example = "2024-06-30")
    private Date issueDate;

    @Schema(description = "有效期结束", example = "2026-06-30")
    private Date expiryDate;

    @Schema(description = "证书状态（0草稿 1已颁发 2已作废 3已过期）", example = "0")
    private String status;

    @Schema(description = "颁发机构", example = "飞行培训中心")
    private String issuingAuthority;

    @Schema(description = "证书描述", example = "证书描述信息")
    private String certificateDescription;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

    @Schema(description = "机型ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "机型ID不能为空")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "专业ID不能为空")
    private Long majorId;

    @Schema(description = "学员ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "学员ID不能为空")
    private Long studentId;

}
