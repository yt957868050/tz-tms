package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 培训能力管理请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训能力管理请求对象")
@Data
public class TrainingAbilityRequest {

    /** 培训能力ID */
    @Schema(description = "培训能力ID")
    private Long trainingAbilityId;

    /** 培训能力名称 */
    @Schema(description = "培训能力名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "培训能力名称不能为空")
    private String trainingAbilityName;

    /** 培训能力代码 */
    @Schema(description = "培训能力代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "培训能力代码不能为空")
    private String trainingAbilityCode;

    /** 培训能力类型（1培训 2复训 3恢复考试） */
    @Schema(description = "培训能力类型", allowableValues = {"1", "2", "3"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "培训能力类型不能为空")
    private String abilityType;

    /** 培训能力描述 */
    @Schema(description = "培训能力描述")
    private String trainingAbilityDesc;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;

    /** 排序 */
    @Schema(description = "排序")
    private Integer orderNum;
}