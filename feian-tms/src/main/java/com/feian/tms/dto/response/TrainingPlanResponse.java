package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 培训计划管理响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "培训计划管理响应对象")
public class TrainingPlanResponse {
    
    @Schema(description = "培训计划ID", example = "1")
    private Long trainingPlanId;

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

    @Schema(description = "计划开始时间", example = "2024-01-01")
    private Date planStartTime;

    @Schema(description = "计划结束时间", example = "2024-06-30")
    private Date planEndTime;

    @Schema(description = "总理论学时", example = "120.5")
    private BigDecimal totalTheoryHours;

    @Schema(description = "总实践学时", example = "80.0")
    private BigDecimal totalPracticeHours;

    @Schema(description = "计划人数", example = "20")
    private Integer planStudentCount;

    @Schema(description = "培训计划描述", example = "Bell-206飞行员培训计划详细描述")
    private String trainingPlanDesc;

    @Schema(description = "状态（0草稿 1待审核 2已审核 3执行中 4已完成 5已取消）", example = "0")
    private String status;

    @Schema(description = "机型名称", example = "Bell-206")
    private String machineTypeName;

    @Schema(description = "专业名称", example = "飞行员专业")
    private String majorName;

    @Schema(description = "培训能力名称", example = "培训")
    private String trainingAbilityName;

    @Schema(description = "创建者", example = "admin")
    private String createBy;

    @Schema(description = "创建时间", example = "2024-01-01 10:00:00")
    private Date createTime;

    @Schema(description = "更新者", example = "admin")
    private String updateBy;

    @Schema(description = "更新时间", example = "2024-01-01 10:00:00")
    private Date updateTime;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

    @Schema(description = "培训计划明细列表")
    private List<TrainingPlanDetailResponse> trainingPlanDetailList;
}