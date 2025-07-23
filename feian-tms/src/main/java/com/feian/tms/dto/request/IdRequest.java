package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 通用ID请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "通用ID请求对象")
public class IdRequest {
    
    @Schema(description = "主键ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "ID不能为空")
    private Long id;
}