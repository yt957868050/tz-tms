package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训记录对象 tms_training_record
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_record")
public class TrainingRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 培训记录ID */
    @TableId(type = IdType.AUTO)
    private Long trainingRecordId;

    /** 学员ID */
    @Excel(name = "学员ID")
    private Long studentId;

    /** 培训班次ID */
    @Excel(name = "培训班次ID")
    private Long trainingClassId;

    /** 课件ID */
    @Excel(name = "课件ID")
    private Long coursewareId;

    /** 教员ID */
    @Excel(name = "教员ID")
    private Long instructorId;

    /** 培训日期 */
    @Excel(name = "培训日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trainingDate;

    /** 培训类型（1理论 2实践） */
    @Excel(name = "培训类型", readConverterExp = "1=理论,2=实践")
    private String trainingMethod;

    /** 培训时长(小时) */
    @Excel(name = "培训时长")
    private BigDecimal trainingHours;

    /** 出勤状态（1正常 2迟到 3早退 4请假 5缺勤） */
    @Excel(name = "出勤状态", readConverterExp = "1=正常,2=迟到,3=早退,4=请假,5=缺勤")
    private String attendanceStatus;

    /** 课堂表现评分 */
    @Excel(name = "课堂表现评分")
    private BigDecimal performanceScore;

    /** 作业成绩 */
    @Excel(name = "作业成绩")
    private BigDecimal homeworkScore;

    /** 考试成绩 */
    @Excel(name = "考试成绩")
    private BigDecimal examScore;

    /** 综合评分 */
    @Excel(name = "综合评分")
    private BigDecimal totalScore;

    /** 培训内容 */
    @Excel(name = "培训内容")
    private String trainingContent;

    /** 学习心得 */
    @Excel(name = "学习心得")
    private String learningNotes;

    /** 教员评价 */
    @Excel(name = "教员评价")
    private String instructorComment;

    /** 培训效果（1优秀 2良好 3一般 4较差） */
    @Excel(name = "培训效果", readConverterExp = "1=优秀,2=良好,3=一般,4=较差")
    private String trainingEffect;

    /** 学员姓名 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String studentName;

    /** 学员编号 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String studentCode;

    /** 班次名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String className;

    /** 课程名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String courseName;

    /** 教员姓名 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String instructorName;
}