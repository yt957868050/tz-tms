package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 机型管理对象 tms_machine_type
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_machine_type")
public class MachineType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 机型ID */
    @TableId(type = IdType.AUTO)
    private Long machineTypeId;

    /** 机型名称 */
    @Excel(name = "机型名称")
    private String machineTypeName;

    /** 机型代码 */
    @Excel(name = "机型代码")
    private String machineTypeCode;

    /** 机型图片 */
    @Excel(name = "机型图片")
    private String machineTypeImage;

    /** 机型描述 */
    @Excel(name = "机型描述")
    private String machineTypeDesc;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;
}