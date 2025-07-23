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
 * 教员授课能力对象 tms_instructor_ability
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_instructor_ability")
public class InstructorAbility extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 教员授课能力ID */
    @TableId(type = IdType.AUTO)
    private Long instructorAbilityId;

    /** 教员ID */
    @Excel(name = "教员ID")
    private Long instructorId;

    /** 机型ID */
    @Excel(name = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long majorId;

    /** 培训类型ID */
    @Excel(name = "培训类型ID")
    private Long trainingTypeId;

    /** 课件ID */
    @Excel(name = "课件ID")
    private Long coursewareId;

    /** 授课资质等级（1初级 2中级 3高级 4专家） */
    @Excel(name = "授课资质等级", readConverterExp = "1=初级,2=中级,3=高级,4=专家")
    private String abilityLevel;

    /** 资质获得时间 */
    @Excel(name = "资质获得时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qualificationDate;

    /** 资质有效期至 */
    @Excel(name = "资质有效期至", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validUntil;

    /** 授课次数 */
    @Excel(name = "授课次数")
    private Integer teachingCount;

    /** 授课评分 */
    @Excel(name = "授课评分")
    private Float teachingScore;

    /** 是否主讲（0否 1是） */
    @Excel(name = "是否主讲", readConverterExp = "0=否,1=是")
    private String isPrimary;

    /** 备注 */
    @Excel(name = "备注")
    private String abilityRemark;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 教员姓名 */
    private String instructorName;

    /** 机型名称 */
    private String machineTypeName;

    /** 专业名称 */
    private String majorName;

    /** 培训类型名称 */
    private String trainingTypeName;

    /** 课程名称 */
    private String courseName;
}