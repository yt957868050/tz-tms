package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 培训班次管理响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "培训班次管理响应对象")
public class TrainingClassResponse {
    
    @Schema(description = "培训班次ID", example = "1")
    private Long trainingClassId;

    @Schema(description = "班次编号", example = "CLASS2024001")
    private String classCode;

    @Schema(description = "班次名称", example = "第一期Bell-206飞行员培训班")
    private String className;

    @Schema(description = "课程英文姓名", example = "H425-100(ARRIEL 2H) Helicopter Maintenance Training Course")
    private String engTrainingCourse;

    @Schema(description = "培训计划ID", example = "1")
    private Long trainingPlanId;

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

    @Schema(description = "状态（0待开班 1培训中 2已结班 3已取消）", example = "0")
    private String status;

    @Schema(description = "培训计划名称", example = "Bell-206飞行员培训计划")
    private String trainingPlanName;

    @Schema(description = "机型名称", example = "Bell-206")
    private String machineTypeName;

    @Schema(description = "专业名称", example = "飞行员专业")
    private String majorName;

    @Schema(description = "培训能力名称", example = "培训")
    private String trainingAbilityName;

    @Schema(description = "主要教员姓名", example = "李教员")
    private String primaryInstructorName;

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

    @Schema(description = "班次学员列表")
    private List<ClassStudentResponse> classStudentList;
}