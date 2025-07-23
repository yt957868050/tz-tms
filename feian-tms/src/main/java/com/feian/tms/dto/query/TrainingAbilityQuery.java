package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 培训能力管理查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训能力管理查询对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class TrainingAbilityQuery extends PageRequest<TrainingAbilityQuery> {

    /** 培训能力名称 */
    @Schema(description = "培训能力名称")
    private String trainingAbilityName;

    /** 培训能力代码 */
    @Schema(description = "培训能力代码")
    private String trainingAbilityCode;

    /** 培训能力类型（1培训 2复训 3恢复考试） */
    @Schema(description = "培训能力类型", allowableValues = {"1", "2", "3"})
    private String abilityType;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;
}