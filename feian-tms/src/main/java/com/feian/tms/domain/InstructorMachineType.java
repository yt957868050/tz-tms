package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 教员机型关联对象 tms_instructor_machine_type
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_instructor_machine_type")
public class InstructorMachineType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 关联ID */
    @TableId(type = IdType.AUTO)
    private Long relationId;

    /** 教员ID */
    private Long instructorId;

    /** 机型ID */
    private Long machineTypeId;

    /** 是否为主要机型（0否 1是） */
    private String isPrimary;

    /** 教员等级（STANDARD=标准教员, SENIOR=高级教员, CHIEF=首席教员） */
    private String instructorLevel;

    /** 状态（0正常 1停用） */
    private String status;
}