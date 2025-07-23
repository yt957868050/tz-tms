package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 培训计划明细请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训计划明细请求对象")
@Data
public class TrainingPlanDetailRequest {

    /** 培训计划明细ID */
    @Schema(description = "培训计划明细ID")
    private Long trainingPlanDetailId;

    /** 培训计划ID */
    @Schema(description = "培训计划ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "培训计划ID不能为空")
    private Long trainingPlanId;

    /** 课件ID */
    @Schema(description = "课件ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "课件ID不能为空")
    private Long coursewareId;

    /** 课程顺序 */
    @Schema(description = "课程顺序")
    private Integer courseOrder;

    /** 理论学时 */
    @Schema(description = "理论学时")
    private BigDecimal theoryHours;

    /** 实践学时 */
    @Schema(description = "实践学时")
    private BigDecimal practiceHours;

    /** 是否必修（0选修 1必修） */
    @Schema(description = "是否必修", allowableValues = {"0", "1"})
    private String isRequired;

    /** 课程要求 */
    @Schema(description = "课程要求")
    private String courseRequirement;
}