package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 培训类型管理请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训类型管理请求对象")
@Data
public class TrainingTypeRequest {

    /** 培训类型ID */
    @Schema(description = "培训类型ID")
    private Long trainingTypeId;

    /** 培训类型名称 */
    @Schema(description = "培训类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "培训类型名称不能为空")
    private String trainingTypeName;

    /** 培训类型代码 */
    @Schema(description = "培训类型代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "培训类型代码不能为空")
    private String trainingTypeCode;

    /** 培训方式（1理论 2实践） */
    @Schema(description = "培训方式", allowableValues = {"1", "2"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "培训方式不能为空")
    private String trainingMethod;

    /** 培训类型描述 */
    @Schema(description = "培训类型描述")
    private String trainingTypeDesc;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;

    /** 排序 */
    @Schema(description = "排序")
    private Integer orderNum;
}