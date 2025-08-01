package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

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
        //  id 是一个 Map (对应前端的 {id: {id: 17}} 这种对象)
        // Spring Boot 默认的 Jackson 库会将 JSON 对象解析为 Map<String, Object>
        if (id instanceof Map) {
            Map<?, ?> idMap = (Map<?, ?>) id;
            Object innerId = idMap.get("id"); // 获取嵌套的 "id" 键的值
            if (innerId != null) {
                // 递归调用 getId() 来处理嵌套的 id，因为它也可能是 Number, String, 或其他类型
                // 注意：这里需要稍微调整一下逻辑，直接处理 innerId
                if (innerId instanceof Number) {
                    return ((Number) innerId).longValue();
                }
                if (innerId instanceof String) {
                    try {
                        return Long.valueOf((String) innerId);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
                // 如果嵌套的 id 还有更深层的结构，也可以在这里继续处理，但通常不建议太深
            }
        }
        return null;
    }


}