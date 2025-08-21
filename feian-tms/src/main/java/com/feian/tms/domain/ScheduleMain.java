package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date; // 用于映射 DATE 类型的 startDate

/**
 * 排课主表对象 tms_schedule_main
 *
 * @author feian
 * @date 2025-XX-XX (请根据实际生成日期修改)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_schedule_main")
public class ScheduleMain extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long scheduleMainId;

    /** 计划ID */
    @Excel(name = "计划ID")
    private Long planId;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long majorId;

    /** 机型ID */
    @Excel(name = "机型ID")
    private Long machineTypeId;

    /** 课程类型，1是理论，2是实作 */
    @Excel(name = "课程类型", readConverterExp = "1=理论,2=实作")
    private Integer courseType; // 对应 SQL 的 INT 类型

    /** 排课日期，开始排课如2025/8/11 */
    @Excel(name = "排课日期")
    private Date startDate; // 对应 SQL 的 DATE 类型

    /** 培训班级ID */
    @Excel(name = "培训班级ID")
    private Long trainingClassId;

     //关联显示字段
     @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String planName;
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String majorName;
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String machineTypeName;
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String trainingClassName;
}
