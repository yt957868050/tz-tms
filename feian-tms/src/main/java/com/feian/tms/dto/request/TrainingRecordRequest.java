package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训记录请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训记录请求对象")
@Data
public class TrainingRecordRequest {

    /** 培训记录ID */
    @Schema(description = "培训记录ID")
    private Long trainingRecordId;

    /** 学员ID */
    @Schema(description = "学员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "学员ID不能为空")
    private Long studentId;

    /** 机型ID */
    @Schema(description = "机型ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "机型ID不能为空")
    private Long machineTypeId;

    /** 专业ID */
    @Schema(description = "专业ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "专业ID不能为空")
    private Long majorId;

    /** 培训能力ID */
    @Schema(description = "培训能力ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "培训能力ID不能为空")
    private Long trainingAbilityId;

    /** 班次ID */
    @Schema(description = "班次ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "培训班次ID不能为空")
    private Long trainingClassId;

    /** 理论成绩 */
    @Schema(description = "理论成绩")
    private Integer theoryScore;

    /** 实作成绩（1合格 0不合格） */
    @Schema(description = "实作成绩")
    private Integer practiceScore;
}