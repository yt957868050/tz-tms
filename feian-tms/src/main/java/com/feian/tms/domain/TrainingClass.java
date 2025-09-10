package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 培训班次管理对象 tms_training_class
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_class")
public class TrainingClass extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 培训班次ID */
    @TableId(type = IdType.AUTO)
    private Long trainingClassId;

    /** 班次编号 */
    @Excel(name = "班次编号")
    private String classCode;

    /** 班次名称 */
    @Excel(name = "班次名称")
    private String className;

    /** 课程英文姓名 */
    @Excel(name = "课程英文姓名")
    private String engTrainingCourse;

    /** 培训计划ID */
    @Excel(name = "培训计划ID")
    private Long trainingPlanId;

    /** 机型ID */
    @Excel(name = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long majorId;

    /** 培训能力ID */
    @Excel(name = "培训能力ID")
    private Long trainingAbilityId;

    /** 计划开始时间 */
    @Excel(name = "计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planStartTime;

    /** 计划结束时间 */
    @Excel(name = "计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planEndTime;

    /** 实际开始时间 */
    @Excel(name = "实际开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualStartTime;

    /** 实际结束时间 */
    @Excel(name = "实际结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualEndTime;

    /** 计划人数 */
    @Excel(name = "计划人数")
    private Integer planStudentCount;

    /** 实际人数 */
    @Excel(name = "实际人数")
    private Integer actualStudentCount;

    /** 主要教员ID */
    @Excel(name = "主要教员ID")
    private Long primaryInstructorId;

    /** 班次描述 */
    @Excel(name = "班次描述")
    private String classDesc;

    /** 状态（0待开班 1培训中 2已结班 3已取消） */
    @Excel(name = "状态", readConverterExp = "0=待开班,1=培训中,2=已结班,3=已取消")
    private String status;

    /** 培训计划名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String trainingPlanName;

    /** 机型名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String machineTypeName;

    /** 专业名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String majorName;

    /** 培训能力名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String trainingAbilityName;

    /** 主要教员姓名 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String primaryInstructorName;

    /** 班次学员列表 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private List<ClassStudent> classStudentList;

    /** 删除标记（0正常 1删除） */
    @Excel(name = "删除标记", readConverterExp = "0=正常,1=删除")
    private Integer isDeleted;
}