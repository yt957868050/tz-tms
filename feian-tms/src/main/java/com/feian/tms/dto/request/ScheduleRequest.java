package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 排课请求对象
 *
 * @author feian
 * @date 2025-XX-XX (请根据实际生成日期修改)
 */
@Data
@Schema(description = "排课请求对象")
public class ScheduleRequest {

    @Schema(description = "机型ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "机型ID不能为空")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "专业ID不能为空")
    private Long majorId;

    @Schema(description = "计划ID", example = "37", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "计划ID不能为空")
    private Long planId;

    @Schema(description = "课程类型，1是理论，2是实作", example = "1", allowableValues = {"1", "2"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "课程类型不能为空")
    private Integer courseType; // 对应 JSON 的 Number 类型

    @Schema(description = "开始时间", example = "2025-02-01")
    @NotNull(message = "开始时间不能为空", groups = {TrainingPlanRequest.Create.class})
    private Date startDate;

    @Schema(description = "培训能力ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "培训能力ID不能为空")
    private Long trainingAbilityId;

    @Schema(description = "培训班级ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "培训班级ID不能为空")
    private Long trainingClassId;

    @Schema(description = "总时长（分钟）", example = "3.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总时长不能为空")
    private BigDecimal totalMinutes; // 使用 BigDecimal 处理小数精度

    @Schema(description = "理论时长（分钟）", example = "2.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "理论时长不能为空")
    private BigDecimal theoryMinutes; // 使用 BigDecimal 处理小数精度

    @Schema(description = "实践时长（分钟）", example = "1.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实践时长不能为空")
    private BigDecimal practiceMinutes; // 使用 BigDecimal 处理小数精度
}

