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
 * 培训计划明细对象 tms_training_plan_detail
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_plan_detail")
public class TrainingPlanDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 培训计划明细ID */
    @TableId(type = IdType.AUTO)
    private Long trainingPlanDetailId;

    /** 培训计划ID */
    @Excel(name = "培训计划ID")
    private Long trainingPlanId;

    /** 课件ID */
    @Excel(name = "课件ID")
    private Long coursewareId;

    /** 课程顺序 */
    @Excel(name = "课程顺序")
    private Integer courseOrder;

    /** 理论学时 */
    @Excel(name = "理论学时")
    private BigDecimal theoryHours;

    /** 实践学时 */
    @Excel(name = "实践学时")
    private BigDecimal practiceHours;

    /** 是否必修（0选修 1必修） */
    @Excel(name = "是否必修", readConverterExp = "0=选修,1=必修")
    private String isRequired;

    /** 课程要求 */
    @Excel(name = "课程要求")
    private String courseRequirement;

    /** 课程名称 */
    private String courseName;

    /** 课程编码 */
    private String courseCode;
}