package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训记录响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训记录响应对象")
@Data
public class TrainingRecordResponse {

    /** 培训记录ID */
    @Schema(description = "培训记录ID")
    private Long trainingRecordId;

    /** 学员ID */
    @Schema(description = "学员ID")
    private Long studentId;

    /** 培训班次ID */
    @Schema(description = "培训班次ID")
    private Long trainingClassId;

    /** 课件ID */
    @Schema(description = "课件ID")
    private Long coursewareId;

    /** 教员ID */
    @Schema(description = "教员ID")
    private Long instructorId;

    /** 培训日期 */
    @Schema(description = "培训日期")
    private Date trainingDate;

    /** 培训类型（1理论 2实践） */
    @Schema(description = "培训类型")
    private String trainingMethod;

    /** 培训类型名称 */
    @Schema(description = "培训类型名称")
    private String trainingMethodName;

    /** 培训时长(小时) */
    @Schema(description = "培训时长(小时)")
    private BigDecimal trainingHours;

    /** 出勤状态（1正常 2迟到 3早退 4请假 5缺勤） */
    @Schema(description = "出勤状态")
    private String attendanceStatus;

    /** 出勤状态名称 */
    @Schema(description = "出勤状态名称")
    private String attendanceStatusName;

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
    @Schema(description = "培训效果")
    private String trainingEffect;

    /** 培训效果名称 */
    @Schema(description = "培训效果名称")
    private String trainingEffectName;

    /** 学员姓名 */
    @Schema(description = "学员姓名")
    private String studentName;

    /** 学员编号 */
    @Schema(description = "学员编号")
    private String studentCode;

    /** 班次名称 */
    @Schema(description = "班次名称")
    private String className;

    /** 课程名称 */
    @Schema(description = "课程名称")
    private String courseName;

    /** 教员姓名 */
    @Schema(description = "教员姓名")
    private String instructorName;

    /** 创建者 */
    @Schema(description = "创建者")
    private String createBy;

    /** 创建时间 */
    @Schema(description = "创建时间")
    private Date createTime;

    /** 更新者 */
    @Schema(description = "更新者")
    private String updateBy;

    /** 更新时间 */
    @Schema(description = "更新时间")
    private Date updateTime;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;
}