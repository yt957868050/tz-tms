package com.feian.tms.dto.query;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificatePageQuery {

    @Schema(description = "当前页码", example = "1")
    private Integer pageNum;

    @Schema(description = "每页显示条数", example = "10")
    private Integer pageSize;
    @Schema(description = "查询条件对象")
    private Query query;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Query {
        // 查询条件
        private String certificateCode;
        private String studentName;

        public String getCertificateCode() {
            return certificateCode;
        }

        public String getStudentName() {
            return studentName;
        }
    }

}
