package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 培训类型管理对象 tms_training_type
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_type")
public class TrainingType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 培训类型ID */
    @TableId(type = IdType.AUTO)
    private Long trainingTypeId;

    /** 培训类型名称 */
    @Excel(name = "培训类型名称")
    private String trainingTypeName;

    /** 培训类型代码 */
    @Excel(name = "培训类型代码")
    private String trainingTypeCode;

    /** 培训方式（1理论 2实践） */
    @Excel(name = "培训方式", readConverterExp = "1=理论,2=实践")
    private String trainingMethod;

    /** 培训类型描述 */
    @Excel(name = "培训类型描述")
    private String trainingTypeDesc;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;
}