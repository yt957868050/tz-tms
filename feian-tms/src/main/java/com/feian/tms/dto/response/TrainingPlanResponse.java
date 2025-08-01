package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 培训计划响应对象
 * 
 * @author feian
 * @date 2025-01-24
 */
@Data
@Schema(description = "培训计划响应对象")
public class TrainingPlanResponse {
    
    @Schema(description = "计划ID", example = "1")
    private Long planId;

    @Schema(description = "计划编号", example = "PLAN-AS350-AIR-001")
    private String planCode;

    @Schema(description = "计划名称", example = "AS350空勤人员培训计划")
    private String planName;

    @Schema(description = "机型ID", example = "1")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1")
    private Long majorId;

    @Schema(description = "培训能力ID", example = "1")
    private Long trainingAbilityId;

    @Schema(description = "开始时间", example = "2025-02-01")
    private Date startDate;

    @Schema(description = "结束时间", example = "2025-04-15")
    private Date endDate;

    @Schema(description = "总培训时长", example = "315.0")
    private BigDecimal totalHours;

    @Schema(description = "理论时长", example = "120.0")
    private BigDecimal theoryHours;

    @Schema(description = "实践时长", example = "195.0")
    private BigDecimal practiceHours;

    @Schema(description = "计划状态", example = "0")
    private String planStatus;

    @Schema(description = "计划状态名称", example = "草稿")
    private String planStatusName;

    @Schema(description = "是否已生成课表", example = "0")
    private String scheduleGenerated;

    @Schema(description = "班次课程表名称")
    private String classScheduleName;

    @Schema(description = "教学进度安排表名称")
    private String teachingScheduleName;

    @Schema(description = "实作项目清单名称")
    private String practicalProjectListName;

    @Schema(description = "状态", example = "0")
    private String status;

    @Schema(description = "机型名称", example = "AS350")
    private String machineTypeName;

    @Schema(description = "专业名称", example = "空勤")
    private String majorName;

    @Schema(description = "培训能力名称", example = "培训")
    private String trainingAbilityName;

    @Schema(description = "创建者", example = "admin")
    private String createBy;

    @Schema(description = "创建时间", example = "2025-01-24 10:00:00")
    private Date createTime;

    @Schema(description = "更新者", example = "admin")
    private String updateBy;

    @Schema(description = "更新时间", example = "2025-01-24 10:00:00")
    private Date updateTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "关联教员列表")
    private List<InstructorResponse> instructors;

    @Schema(description = "教员数量")
    private Integer instructorCount;

    @Schema(description = "培训班次ID")
    private Long trainingClassId;

    @Schema(description = "培训班次")
    private String className;
}