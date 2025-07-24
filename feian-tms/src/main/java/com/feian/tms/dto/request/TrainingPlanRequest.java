package com.feian.tms.dto.request;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 培训计划请求对象
 * 
 * @author feian
 * @date 2025-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "培训计划请求对象")
public class TrainingPlanRequest extends PageRequest {
    
    @Schema(description = "计划ID", example = "1")
    private Long planId;

    @Schema(description = "计划编号", example = "PLAN-AS350-AIR-001")
    @NotBlank(message = "计划编号不能为空", groups = {Create.class})
    private String planCode;

    @Schema(description = "计划名称", example = "AS350空勤人员培训计划")
    @NotBlank(message = "计划名称不能为空", groups = {Create.class})
    private String planName;

    @Schema(description = "机型ID", example = "1")
    @NotNull(message = "机型ID不能为空", groups = {Create.class})
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "1")
    @NotNull(message = "专业ID不能为空", groups = {Create.class})
    private Long majorId;

    @Schema(description = "培训能力ID", example = "1")
    @NotNull(message = "培训能力ID不能为空", groups = {Create.class})
    private Long trainingAbilityId;

    @Schema(description = "开始时间", example = "2025-02-01")
    @NotNull(message = "开始时间不能为空", groups = {Create.class})
    private Date startDate;

    @Schema(description = "结束时间", example = "2025-04-15")
    private Date endDate;

    @Schema(description = "总培训时长", example = "315.0")
    private BigDecimal totalHours;

    @Schema(description = "理论时长", example = "120.0")
    private BigDecimal theoryHours;

    @Schema(description = "实践时长", example = "195.0")
    private BigDecimal practiceHours;

    @Schema(description = "计划状态", example = "0", allowableValues = {"0", "1", "2", "3"})
    private String planStatus;

    @Schema(description = "是否已生成课表", example = "0", allowableValues = {"0", "1"})
    private String scheduleGenerated;

    @Schema(description = "班次课程表名称")
    private String classScheduleName;

    @Schema(description = "教学进度安排表名称")
    private String teachingScheduleName;

    @Schema(description = "实作项目清单名称")
    private String practicalProjectListName;

    @Schema(description = "状态", example = "0", allowableValues = {"0", "1"})
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "关联教员ID列表")
    private List<Long> instructorIds;

    @Schema(description = "主责教员ID")
    private Long chiefInstructorId;

    // 验证分组接口
    public interface Create {}
    public interface Update {}
}