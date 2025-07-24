package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 培训计划教员关联对象 tms_training_plan_instructor
 * 
 * @author feian
 * @date 2025-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_plan_instructor")
public class TrainingPlanInstructor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 关联ID */
    @TableId(type = IdType.AUTO)
    private Long relationId;

    /** 计划ID */
    @Excel(name = "计划ID")
    private Long planId;

    /** 教员ID */
    @Excel(name = "教员ID")
    private Long instructorId;

    /** 是否为主责教员（0否 1是） */
    @Excel(name = "是否主责教员", readConverterExp = "0=否,1=是")
    private String isChief;

    /** 责任描述 */
    @Excel(name = "责任描述")
    private String responsibility;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    // 关联显示字段
    /** 教员姓名 */
    private String instructorName;

    /** 计划名称 */
    private String planName;
}