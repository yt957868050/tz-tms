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
 * 培训计划对象 tms_training_plan
 * 
 * @author feian
 * @date 2025-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_plan")
public class TrainingPlan extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 计划ID */
    @TableId(type = IdType.AUTO)
    private Long planId;

    /** 计划编号 */
    @Excel(name = "计划编号")
    private String planCode;

    /** 计划名称 */
    @Excel(name = "计划名称")
    private String planName;

    /** 机型ID */
    @Excel(name = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long majorId;

    /** 培训能力ID */
    @Excel(name = "培训能力ID")
    private Long trainingAbilityId;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 总培训时长 */
    @Excel(name = "总培训时长")
    private BigDecimal totalHours;

    /** 理论时长 */
    @Excel(name = "理论时长")
    private BigDecimal theoryHours;

    /** 实践时长 */
    @Excel(name = "实践时长")
    private BigDecimal practiceHours;

    /** 计划状态（0草稿 1执行中 2已完成 3已取消） */
    @Excel(name = "计划状态", readConverterExp = "0=草稿,1=执行中,2=已完成,3=已取消")
    private String planStatus;

    /** 是否已生成课表（0否 1是） */
    @Excel(name = "课表生成状态", readConverterExp = "0=未生成,1=已生成")
    private String scheduleGenerated;

    /** 班次课程表名称 */
    @Excel(name = "班次课程表名称")
    private String classScheduleName;

    /** 教学进度安排表名称 */
    @Excel(name = "教学进度安排表名称")
    private String teachingScheduleName;

    /** 机型实作培训项目清单名称 */
    @Excel(name = "实作项目清单名称")
    private String practicalProjectListName;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    // 关联显示字段
    /** 机型名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String machineTypeName;

    /** 专业名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String majorName;

    /** 培训能力名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String trainingAbilityName;

    /** 培训班次ID */
    @Excel(name = "培训班次ID")
    private Long trainingClassId;
}