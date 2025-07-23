package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 培训计划管理请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "培训计划管理请求对象")
public class TrainingPlanRequest {
    
    @Schema(description = "培训计划ID", example = "1")
    private Long trainingPlanId;

    @Schema(description = "培训计划名称", example = "Bell-206飞行员培训计划", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "培训计划名称不能为空")
    private String trainingPlanName;

    @Schema(description = "培训计划编号", example = "TP2024001", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "培训计划编号不能为空")
    private String trainingPlanCode;

    @Schema(description = "机型ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "机型ID不能为空")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "专业ID不能为空")
    private Long majorId;

    @Schema(description = "培训能力ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "培训能力ID不能为空")
    private Long trainingAbilityId;

    @Schema(description = "计划开始时间", example = "2024-01-01", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "计划开始时间不能为空")
    private Date planStartTime;

    @Schema(description = "计划结束时间", example = "2024-06-30", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "计划结束时间不能为空")
    private Date planEndTime;

    @Schema(description = "总理论学时", example = "120.5")
    private BigDecimal totalTheoryHours;

    @Schema(description = "总实践学时", example = "80.0")
    private BigDecimal totalPracticeHours;

    @Schema(description = "计划人数", example = "20")
    private Integer planStudentCount;

    @Schema(description = "培训计划描述", example = "Bell-206飞行员培训计划详细描述")
    private String trainingPlanDesc;

    @Schema(description = "状态", example = "0", allowableValues = {"0", "1", "2", "3", "4", "5"})
    private String status;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

    @Schema(description = "培训计划明细列表")
    private List<TrainingPlanDetailRequest> trainingPlanDetailList;
}