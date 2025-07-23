package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 专业管理查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "专业管理查询对象")
public class MajorQuery extends PageRequest<MajorQuery> {
    
    @Schema(description = "专业名称", example = "飞行员专业")
    private String majorName;

    @Schema(description = "专业代码", example = "FLY001")
    private String majorCode;

    @Schema(description = "专业类型（1空勤 2地勤）", example = "1", allowableValues = {"1", "2"})
    private String majorType;

    @Schema(description = "状态（0正常 1停用）", example = "0", allowableValues = {"0", "1"})
    private String status;
}