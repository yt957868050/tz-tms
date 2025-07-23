package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 机型管理请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "机型管理请求对象")
public class MachineTypeRequest {
    
    @Schema(description = "机型ID", example = "1")
    private Long machineTypeId;

    @Schema(description = "机型名称", example = "Bell-206", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "机型名称不能为空")
    private String machineTypeName;

    @Schema(description = "机型代码", example = "B206", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "机型代码不能为空")
    private String machineTypeCode;

    @Schema(description = "机型图片URL", example = "http://example.com/image.jpg")
    private String machineTypeImage;

    @Schema(description = "机型描述", example = "双发直升机")
    private String machineTypeDesc;

    @Schema(description = "状态", example = "0", allowableValues = {"0", "1"})
    private String status;

    @Schema(description = "排序号", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    private Integer orderNum;

    @Schema(description = "备注", example = "备注信息")
    private String remark;
}