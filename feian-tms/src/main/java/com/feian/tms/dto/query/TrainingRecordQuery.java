package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 培训记录查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训记录查询对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class TrainingRecordQuery extends PageRequest<TrainingRecordQuery> {

    /** 学员ID */
    @Schema(description = "学员ID")
    private Long studentId;

    /** 学员姓名 */
    @Schema(description = "学员姓名")
    private String studentName;

    /** 培训班次ID */
    @Schema(description = "培训班次ID")
    private Long trainingClassId;

    /** 班次名称 */
    @Schema(description = "班次名称")
    private String className;

    /** 课件ID */
    @Schema(description = "课件ID")
    private Long coursewareId;

    /** 课程名称 */
    @Schema(description = "课程名称")
    private String courseName;

    /** 教员ID */
    @Schema(description = "教员ID")
    private Long instructorId;

    /** 教员姓名 */
    @Schema(description = "教员姓名")
    private String instructorName;

    /** 培训类型（1理论 2实践） */
    @Schema(description = "培训类型", allowableValues = {"1", "2"})
    private String trainingMethod;

    /** 出勤状态（1正常 2迟到 3早退 4请假 5缺勤） */
    @Schema(description = "出勤状态", allowableValues = {"1", "2", "3", "4", "5"})
    private String attendanceStatus;

    /** 培训效果（1优秀 2良好 3一般 4较差） */
    @Schema(description = "培训效果", allowableValues = {"1", "2", "3", "4"})
    private String trainingEffect;

    /** 培训日期开始 */
    @Schema(description = "培训日期开始")
    private Date trainingDateBegin;

    /** 培训日期结束 */
    @Schema(description = "培训日期结束")
    private Date trainingDateEnd;

    /** 培训日期 */
    @Schema(description = "培训日期")
    private Date trainingDate;
}