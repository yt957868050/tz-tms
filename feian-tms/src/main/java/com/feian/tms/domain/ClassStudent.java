package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 班次学员关联对象 tms_class_student
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_class_student")
public class ClassStudent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 班次学员关联ID */
    @TableId(type = IdType.AUTO)
    private Long classStudentId;

    /** 培训班次ID */
    @Excel(name = "培训班次ID")
    private Long trainingClassId;

    /** 学员ID */
    @Excel(name = "学员ID")
    private Long studentId;

    /** 报名时间 */
    @Excel(name = "报名时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date enrollTime;

    /** 学员状态（0待开班 1培训中 2已结业 3已退班 4请假中） */
    @Excel(name = "学员状态", readConverterExp = "0=待开班,1=培训中,2=已结业,3=已退班,4=请假中")
    private String studentStatus;

    /** 退班原因 */
    @Excel(name = "退班原因")
    private String withdrawReason;

    /** 退班时间 */
    @Excel(name = "退班时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date withdrawTime;

    /** 班次编号 */
    private String classCode;

    /** 班次名称 */
    private String className;

    /** 学员姓名 */
    private String studentName;

    /** 学员工号 */
    private String studentCode;
}