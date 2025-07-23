package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * 教员授课能力请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "教员授课能力请求对象")
public class InstructorAbilityRequest {
    
    @Schema(description = "教员授课能力ID", example = "1")
    private Long instructorAbilityId;

    @Schema(description = "教员ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "教员ID不能为空")
    private Long instructorId;

    @Schema(description = "机型ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "机型ID不能为空")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "专业ID不能为空")
    private Long majorId;

    @Schema(description = "培训类型ID", example = "1")
    private Long trainingTypeId;

    @Schema(description = "课件ID", example = "1")
    private Long coursewareId;

    @Schema(description = "授课资质等级", example = "3", allowableValues = {"1", "2", "3", "4"})
    private String abilityLevel;

    @Schema(description = "资质获得时间", example = "2024-01-01")
    private Date qualificationDate;

    @Schema(description = "资质有效期至", example = "2026-01-01")
    private Date validUntil;

    @Schema(description = "授课次数", example = "10")
    private Integer teachingCount;

    @Schema(description = "授课评分", example = "95.5")
    private Float teachingScore;

    @Schema(description = "是否主讲", example = "1", allowableValues = {"0", "1"})
    private String isPrimary;

    @Schema(description = "能力备注", example = "具备优秀的教学能力")
    private String abilityRemark;

    @Schema(description = "状态", example = "0", allowableValues = {"0", "1"})
    private String status;

    @Schema(description = "备注", example = "备注信息")
    private String remark;
}