package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 班次学员关联对象 tms_class_student
 * 
 * @author feian
 * @date 2025-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_class_student")
public class ClassStudent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 班次学员关联ID */
    @TableId(value = "class_student_id", type = IdType.AUTO)
    private Long classStudentId;

    /** 培训班次ID */
    private Long trainingClassId;

    /** 学员ID */
    private Long studentId;

    /** 报名时间 */
    private Date enrollTime;

    /** 学员状态（0正常 1请假 2退课 3毕业） */
    private String studentStatus;

    /** 退课原因 */
    private String withdrawReason;

    /** 退课时间 */
    private Date withdrawTime;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    private Integer isDeleted;

    /** 学员姓名 */
    @TableField(exist = false)
    private String studentName;

    /** 班次名称 */
    @TableField(exist = false)
    private String className;
}