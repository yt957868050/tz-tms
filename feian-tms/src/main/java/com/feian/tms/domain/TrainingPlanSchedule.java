package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 培训计划课程安排对象 tms_training_plan_schedule
 * 
 * @author feian
 * @date 2025-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_plan_schedule")
public class TrainingPlanSchedule extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 课程安排ID */
    @TableId(type = IdType.AUTO)
    private Long scheduleId;

    /** 计划ID */
    @Excel(name = "计划ID")
    private Long planId;

    /** 安排类型 */
    @Excel(name = "安排类型", readConverterExp = "class_schedule=班次课程表,teaching_schedule=教学进度安排表,practical_project=实作项目清单")
    private String scheduleType;

    /** 天数（第几天） */
    @Excel(name = "天数")
    private Integer dayNumber;

    /** 时间段 */
    @Excel(name = "时间段")
    private String timeSlot;

    /** 课件ID */
    @Excel(name = "课件ID")
    private Long coursewareId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程类型（1理论 2实践） */
    @Excel(name = "课程类型", readConverterExp = "1=理论,2=实践")
    private String courseType;

    /** 课程时长 */
    @Excel(name = "课程时长")
    private BigDecimal courseHours;

    /** 授课教员ID */
    @Excel(name = "授课教员ID")
    private Long instructorId;

    /** 教室/实训场地 */
    @Excel(name = "教室/实训场地")
    private String classroom;

    /** 课程内容 */
    @Excel(name = "课程内容")
    private String courseContent;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    // 关联显示字段
    /** 计划名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String planName;

    /** 课件名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String coursewareName;

    /** 教员姓名 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String instructorName;
}