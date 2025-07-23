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
import java.util.List;

/**
 * 培训计划管理对象 tms_training_plan
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_plan")
public class TrainingPlan extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 培训计划ID */
    @TableId(type = IdType.AUTO)
    private Long trainingPlanId;

    /** 培训计划名称 */
    @Excel(name = "培训计划名称")
    private String trainingPlanName;

    /** 培训计划编号 */
    @Excel(name = "培训计划编号")
    private String trainingPlanCode;

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

    /** 总理论学时 */
    @Excel(name = "总理论学时")
    private BigDecimal totalTheoryHours;

    /** 总实践学时 */
    @Excel(name = "总实践学时")
    private BigDecimal totalPracticeHours;

    /** 计划人数 */
    @Excel(name = "计划人数")
    private Integer planStudentCount;

    /** 培训计划描述 */
    @Excel(name = "培训计划描述")
    private String trainingPlanDesc;

    /** 状态（0草稿 1待审核 2已审核 3执行中 4已完成 5已取消） */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=待审核,2=已审核,3=执行中,4=已完成,5=已取消")
    private String status;

    /** 机型名称 */
    private String machineTypeName;

    /** 专业名称 */
    private String majorName;

    /** 培训能力名称 */
    private String trainingAbilityName;

    /** 培训计划明细列表 */
    private List<TrainingPlanDetail> trainingPlanDetailList;
}