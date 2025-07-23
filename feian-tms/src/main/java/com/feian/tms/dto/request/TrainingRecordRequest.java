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

    /** 培训班次ID */
    @Schema(description = "培训班次ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "培训班次ID不能为空")
    private Long trainingClassId;

    /** 课件ID */
    @Schema(description = "课件ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "课件ID不能为空")
    private Long coursewareId;

    /** 教员ID */
    @Schema(description = "教员ID")
    private Long instructorId;

    /** 培训日期 */
    @Schema(description = "培训日期")
    private Date trainingDate;

    /** 培训类型（1理论 2实践） */
    @Schema(description = "培训类型", allowableValues = {"1", "2"})
    private String trainingMethod;

    /** 培训时长(小时) */
    @Schema(description = "培训时长(小时)")
    private BigDecimal trainingHours;

    /** 出勤状态（1正常 2迟到 3早退 4请假 5缺勤） */
    @Schema(description = "出勤状态", allowableValues = {"1", "2", "3", "4", "5"})
    private String attendanceStatus;

    /** 课堂表现评分 */
    @Schema(description = "课堂表现评分")
    private BigDecimal performanceScore;

    /** 作业成绩 */
    @Schema(description = "作业成绩")
    private BigDecimal homeworkScore;

    /** 考试成绩 */
    @Schema(description = "考试成绩")
    private BigDecimal examScore;

    /** 综合评分 */
    @Schema(description = "综合评分")
    private BigDecimal totalScore;

    /** 培训内容 */
    @Schema(description = "培训内容")
    private String trainingContent;

    /** 学习心得 */
    @Schema(description = "学习心得")
    private String learningNotes;

    /** 教员评价 */
    @Schema(description = "教员评价")
    private String instructorComment;

    /** 培训效果（1优秀 2良好 3一般 4较差） */
    @Schema(description = "培训效果", allowableValues = {"1", "2", "3", "4"})
    private String trainingEffect;
}