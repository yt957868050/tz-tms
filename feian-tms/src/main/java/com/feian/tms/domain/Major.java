package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 专业管理对象 tms_major
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_major")
public class Major extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 专业ID */
    @TableId(type = IdType.AUTO)
    private Long majorId;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String majorName;

    /** 专业代码 */
    @Excel(name = "专业代码")
    private String majorCode;

    /** 专业类型（1空勤 2地勤） */
    @Excel(name = "专业类型", readConverterExp = "1=空勤,2=地勤")
    private String majorType;

    /** 专业描述 */
    @Excel(name = "专业描述")
    private String majorDesc;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;
}