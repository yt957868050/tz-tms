package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 培训计划明细查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训计划明细查询对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class TrainingPlanDetailQuery extends PageRequest<TrainingPlanDetailQuery> {

    /** 培训计划ID */
    @Schema(description = "培训计划ID")
    private Long trainingPlanId;

    /** 课件ID */
    @Schema(description = "课件ID")
    private Long coursewareId;

    /** 课程名称 */
    @Schema(description = "课程名称")
    private String courseName;

    /** 课程编码 */
    @Schema(description = "课程编码")
    private String courseCode;

    /** 是否必修（0选修 1必修） */
    @Schema(description = "是否必修", allowableValues = {"0", "1"})
    private String isRequired;
}