package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 培训班次管理查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "培训班次管理查询对象")
public class TrainingClassQuery extends PageRequest<TrainingClassQuery> {
    
    @Schema(description = "班次编号", example = "CLASS2024001")
    private String classCode;

    @Schema(description = "班次名称", example = "第一期Bell-206飞行员培训班")
    private String className;

    @Schema(description = "培训计划ID", example = "1")
    private Long trainingPlanId;

    @Schema(description = "机型ID", example = "1")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1")
    private Long majorId;

    @Schema(description = "培训能力ID", example = "1")
    private Long trainingAbilityId;

    @Schema(description = "主要教员ID", example = "1")
    private Long primaryInstructorId;

    @Schema(description = "状态（0待开班 1培训中 2已结班 3已取消）", example = "0", allowableValues = {"0", "1", "2", "3"})
    private String status;

    @Schema(description = "计划开始时间-开始", example = "2024-01-01")
    private Date planStartTimeBegin;

    @Schema(description = "计划开始时间-结束", example = "2024-12-31")
    private Date planStartTimeEnd;

    @Schema(description = "计划结束时间-开始", example = "2024-01-01")
    private Date planEndTimeBegin;

    @Schema(description = "计划结束时间-结束", example = "2024-12-31")
    private Date planEndTimeEnd;

    @Schema(description = "实际开始时间-开始", example = "2024-01-01")
    private Date actualStartTimeBegin;

    @Schema(description = "实际开始时间-结束", example = "2024-12-31")
    private Date actualStartTimeEnd;

    @Schema(description = "实际结束时间-开始", example = "2024-01-01")
    private Date actualEndTimeBegin;

    @Schema(description = "实际结束时间-结束", example = "2024-12-31")
    private Date actualEndTimeEnd;

    @Schema(description = "计划开始时间", example = "2024-01-01")
    private Date planStartTime;

    @Schema(description = "计划结束时间", example = "2024-12-31")
    private Date planEndTime;
}