package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 专业管理请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "专业管理请求对象")
public class MajorRequest {
    
    @Schema(description = "专业ID", example = "1")
    private Long majorId;

    @Schema(description = "专业名称", example = "飞行员专业", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "专业名称不能为空")
    private String majorName;

    @Schema(description = "专业代码", example = "FLY001", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "专业代码不能为空")
    private String majorCode;

    @Schema(description = "专业类型", example = "1", allowableValues = {"1", "2"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "专业类型不能为空")
    private String majorType;

    @Schema(description = "专业描述", example = "直升机飞行员专业")
    private String majorDesc;

    @Schema(description = "状态", example = "0", allowableValues = {"0", "1"})
    private String status;

    @Schema(description = "排序号", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    private Integer orderNum;

    @Schema(description = "备注", example = "备注信息")
    private String remark;
}