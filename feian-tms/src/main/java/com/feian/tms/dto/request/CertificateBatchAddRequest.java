package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 证书批量添加请求对象
 *
 * @author feian
 * @date 2025-01-24
 */
@Data
@Schema(description = "证书批量添加请求对象")
public class CertificateBatchAddRequest {

    @Schema(description = "学员ID列表")
    @NotNull(message = "学员ID列表不能为空")
    private List<Long> studentIds;
}
