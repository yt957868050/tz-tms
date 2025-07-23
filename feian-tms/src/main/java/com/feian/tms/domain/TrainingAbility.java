package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 培训能力管理对象 tms_training_ability
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_ability")
public class TrainingAbility extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 培训能力ID */
    @TableId(type = IdType.AUTO)
    private Long trainingAbilityId;

    /** 培训能力名称 */
    @Excel(name = "培训能力名称")
    private String trainingAbilityName;

    /** 培训能力代码 */
    @Excel(name = "培训能力代码")  
    private String trainingAbilityCode;

    /** 培训能力类型（1培训 2复训 3恢复考试） */
    @Excel(name = "培训能力类型", readConverterExp = "1=培训,2=复训,3=恢复考试")
    private String abilityType;

    /** 培训能力描述 */
    @Excel(name = "培训能力描述")
    private String trainingAbilityDesc;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;
}