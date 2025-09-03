package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 培训记录对象 tms_training_record
 *
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode
@TableName("tms_training_record")
public class TrainingRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 培训记录ID */
    @TableId(type = IdType.AUTO)
    private Long trainingRecordId;

    /** 学员ID */
    @Excel(name = "学员ID")
    private Long studentId;

    /** 机型ID */
    @Excel(name = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long majorId;

    /** 培训能力ID */
    @Excel(name = "培训能力ID")
    private Long trainingAbilityId;

    /** 班次ID */
    @Excel(name = "班次ID")
    private Long trainingClassId;

    /** 理论成绩 */
    @Excel(name = "理论成绩")
    private Integer theoryScore;

    /** 实作成绩（1合格 0不合格） */
    @Excel(name = "实作成绩")
    private Integer practiceScore;


    /** 逻辑删除标志(0未删除1已删除) */
    private Integer isDeleted;

    /** 备注信息 */
    @Excel(name = "备注信息")
    private String remark;

    // 非数据库字段，用于前端展示
    /** 学员姓名 */
    @TableField(exist = false)
    @Excel(name = "学员姓名")
    private String studentName;

    /** 机型名称 */
    @TableField(exist = false)
    @Excel(name = "机型名称")
    private String machineTypeName;

    /** 专业名称 */
    @TableField(exist = false)
    @Excel(name = "专业名称")
    private String majorName;

    /** 培训能力名称 */
    @TableField(exist = false)
    @Excel(name = "培训能力名称")
    private String trainingAbilityName;
}