package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificatePageResponse {
    @Schema(description = "记录列表数据数组")
    private List records;

    @Schema(description = "总记录数", example = "100")
    private Long total; // 总记录数通常用 Long，因为可能很大

    @Schema(description = "每页大小", example = "10")
    private Integer size;

    @Schema(description = "当前页码", example = "1")
    private Integer current;

    @Schema(description = "总页数", example = "10")
    private Integer pages;
}
