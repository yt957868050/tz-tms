package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 培训计划管理查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "培训计划管理查询对象")
public class TrainingPlanQuery extends PageRequest<TrainingPlanQuery> {
    
    @Schema(description = "培训计划名称", example = "Bell-206飞行员培训计划")
    private String trainingPlanName;

    @Schema(description = "培训计划编号", example = "TP2024001")
    private String trainingPlanCode;

    @Schema(description = "机型ID", example = "1")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1")
    private Long majorId;

    @Schema(description = "培训能力ID", example = "1")
    private Long trainingAbilityId;

    @Schema(description = "状态（0草稿 1待审核 2已审核 3执行中 4已完成 5已取消）", example = "0", allowableValues = {"0", "1", "2", "3", "4", "5"})
    private String status;

    @Schema(description = "计划开始时间-开始", example = "2024-01-01")
    private Date planStartTimeBegin;

    @Schema(description = "计划开始时间-结束", example = "2024-12-31")
    private Date planStartTimeEnd;

    @Schema(description = "计划结束时间-开始", example = "2024-01-01")
    private Date planEndTimeBegin;

    @Schema(description = "计划结束时间-结束", example = "2024-12-31")
    private Date planEndTimeEnd;

    @Schema(description = "计划开始时间", example = "2024-01-01")
    private Date planStartTime;

    @Schema(description = "计划结束时间", example = "2024-12-31")
    private Date planEndTime;
}