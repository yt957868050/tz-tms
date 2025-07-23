package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 培训班次管理请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "培训班次管理请求对象")
public class TrainingClassRequest {
    
    @Schema(description = "培训班次ID", example = "1")
    private Long trainingClassId;

    @Schema(description = "班次编号", example = "CLASS2024001", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "班次编号不能为空")
    private String classCode;

    @Schema(description = "班次名称", example = "第一期Bell-206飞行员培训班", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "班次名称不能为空")
    private String className;

    @Schema(description = "培训计划ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "培训计划ID不能为空")
    private Long trainingPlanId;

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

    @Schema(description = "实际开始时间", example = "2024-01-02")
    private Date actualStartTime;

    @Schema(description = "实际结束时间", example = "2024-07-01")
    private Date actualEndTime;

    @Schema(description = "计划人数", example = "20")
    private Integer planStudentCount;

    @Schema(description = "实际人数", example = "18")
    private Integer actualStudentCount;

    @Schema(description = "主要教员ID", example = "1")
    private Long primaryInstructorId;

    @Schema(description = "班次描述", example = "第一期Bell-206飞行员培训班详细描述")
    private String classDesc;

    @Schema(description = "状态", example = "0", allowableValues = {"0", "1", "2", "3"})
    private String status;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

    @Schema(description = "班次学员列表")
    private List<ClassStudentRequest> classStudentList;
}