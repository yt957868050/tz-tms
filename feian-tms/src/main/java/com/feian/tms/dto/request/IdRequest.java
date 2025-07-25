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
    private Object id;
    
    // Explicit getter method for controller compatibility
    public Long getId() {
        if (id == null) {
            return null;
        }
        if (id instanceof Number) {
            return ((Number) id).longValue();
        }
        if (id instanceof String) {
            try {
                return Long.valueOf((String) id);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        if (id instanceof java.util.List<?> list && !list.isEmpty()) {
            Object first = list.get(0);
            if (first instanceof Number) {
                return ((Number) first).longValue();
            }
            if (first instanceof String) {
                try {
                    return Long.valueOf((String) first);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return null;
    }


}