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
 * 培训能力对象 tms_training_ability
 * 
 * @author feian
 * @date 2025-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_ability")
public class TrainingAbility extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 培训能力ID */
    @TableId(type = IdType.AUTO)
    private Long trainingAbilityId;

    /** 能力编码 */
    @Excel(name = "能力编码")
    private String abilityCode;

    /** 能力名称 */
    @Excel(name = "能力名称")
    private String abilityName;

    /** 能力描述 */
    @Excel(name = "能力描述")
    private String abilityDesc;

    /** 每日培训时长 */
    @Excel(name = "每日培训时长")
    private BigDecimal dailyHours;

    /** 建议培训天数 */
    @Excel(name = "建议培训天数")
    private Integer totalDays;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
}