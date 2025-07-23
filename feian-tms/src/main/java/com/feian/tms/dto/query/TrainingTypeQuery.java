package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 培训类型管理查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训类型管理查询对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class TrainingTypeQuery extends PageRequest<TrainingTypeQuery> {

    /** 培训类型名称 */
    @Schema(description = "培训类型名称")
    private String trainingTypeName;

    /** 培训类型代码 */
    @Schema(description = "培训类型代码")
    private String trainingTypeCode;

    /** 培训方式（1理论 2实践） */
    @Schema(description = "培训方式", allowableValues = {"1", "2"})
    private String trainingMethod;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;
}