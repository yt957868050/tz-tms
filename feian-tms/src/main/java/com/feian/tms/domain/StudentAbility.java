package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 学员学习能力对象 tms_student_ability
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_student_ability")
public class StudentAbility extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学员学习能力ID */
    @TableId(type = IdType.AUTO)
    private Long studentAbilityId;

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

    /** 课件ID */
    @Excel(name = "课件ID")
    private Long coursewareId;

    /** 学习进度（0未开始 1学习中 2已完成） */
    @Excel(name = "学习进度", readConverterExp = "0=未开始,1=学习中,2=已完成")
    private String learningProgress;

    /** 理论成绩 */
    @Excel(name = "理论成绩")
    private BigDecimal theoryScore;

    /** 实践成绩 */
    @Excel(name = "实践成绩")
    private BigDecimal practiceScore;

    /** 综合成绩 */
    @Excel(name = "综合成绩")
    private BigDecimal totalScore;

    /** 学习开始时间 */
    @Excel(name = "学习开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date learningStartTime;

    /** 学习完成时间 */
    @Excel(name = "学习完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date learningEndTime;

    /** 资质状态（0未获得 1已获得 2已过期） */
    @Excel(name = "资质状态", readConverterExp = "0=未获得,1=已获得,2=已过期")
    private String qualificationStatus;

    /** 资质获得时间 */
    @Excel(name = "资质获得时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qualificationDate;

    /** 资质有效期至 */
    @Excel(name = "资质有效期至", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validUntil;

    /** 学习备注 */
    @Excel(name = "学习备注")
    private String learningRemark;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 学员姓名 */
    private String studentName;

    /** 机型名称 */
    private String machineTypeName;

    /** 专业名称 */
    private String majorName;

    /** 培训能力名称 */
    private String trainingAbilityName;

    /** 课程名称 */
    private String courseName;
}