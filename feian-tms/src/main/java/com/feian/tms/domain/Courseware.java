package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 课件管理对象 tms_courseware
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_courseware")
public class Courseware extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 课件ID */
    @TableId(type = IdType.AUTO)
    private Long coursewareId;

    /** 课程编码 */
    @Excel(name = "课程编码")
    private String courseCode;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** ATA章节 */
    @Excel(name = "ATA章节")
    private String ataChapter;

    /** 机型ID */
    @Excel(name = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long majorId;

    /** 培训类型ID */
    @Excel(name = "培训类型ID")
    private Long trainingTypeId;

    /** 理论培训时长(小时) */
    @Excel(name = "理论培训时长")
    private BigDecimal theoryHours;

    /** 实践培训时长(小时) */
    @Excel(name = "实践培训时长")
    private BigDecimal practiceHours;

    /** 课程描述 */
    @Excel(name = "课程描述")
    private String courseDesc;

    /** 课程目标 */
    @Excel(name = "课程目标")
    private String courseObjective;

    /** 课程要求 */
    @Excel(name = "课程要求")
    private String courseRequirement;

    /** 培训类型（1理论 2实践） */
    @Excel(name = "培训类型", readConverterExp = "1=理论,2=实践")
    private String trainingType;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;

    /** 教员ID */
    @Excel(name = "教员ID")
    private Long instructorId;

    /** 教员姓名 */
    @Excel(name = "教员姓名")
    private String instructorName;

    /** 机型名称 */
    @TableField(exist = false)
    private String machineTypeName;

    /** 专业名称 */
    @TableField(exist = false)
    private String majorName;

    /** 培训类型名称 */
    @TableField(exist = false)
    private String trainingTypeName;



}