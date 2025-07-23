package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 学员学习能力请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "学员学习能力请求对象")
@Data
public class StudentAbilityRequest {

    /** 学员学习能力ID */
    @Schema(description = "学员学习能力ID")
    private Long studentAbilityId;

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

    /** 课件ID */
    @Schema(description = "课件ID")
    private Long coursewareId;

    /** 学习进度（0未开始 1学习中 2已完成） */
    @Schema(description = "学习进度", allowableValues = {"0", "1", "2"})
    private String learningProgress;

    /** 理论成绩 */
    @Schema(description = "理论成绩")
    private BigDecimal theoryScore;

    /** 实践成绩 */
    @Schema(description = "实践成绩")
    private BigDecimal practiceScore;

    /** 综合成绩 */
    @Schema(description = "综合成绩")
    private BigDecimal totalScore;

    /** 学习开始时间 */
    @Schema(description = "学习开始时间")
    private Date learningStartTime;

    /** 学习完成时间 */
    @Schema(description = "学习完成时间")
    private Date learningEndTime;

    /** 资质状态（0未获得 1已获得 2已过期） */
    @Schema(description = "资质状态", allowableValues = {"0", "1", "2"})
    private String qualificationStatus;

    /** 资质获得时间 */
    @Schema(description = "资质获得时间")
    private Date qualificationDate;

    /** 资质有效期至 */
    @Schema(description = "资质有效期至")
    private Date validUntil;

    /** 学习备注 */
    @Schema(description = "学习备注")
    private String learningRemark;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;
}