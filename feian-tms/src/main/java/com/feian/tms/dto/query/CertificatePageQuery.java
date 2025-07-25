package com.feian.tms.dto.query;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "证书编号", example = "CERT2024001", nullable = true)
    @TableField("certificate_code")
    private String certificateNumber;

    @Schema(description = "学员姓名", example = "张三", nullable = true)

    private String studentName;

    @Schema(description = "证书类型（1:培训证书, 2:合格证, 3:执照）", example = "1", allowableValues = {"1", "2", "3"}, nullable = true)
    private String certificateType;

    @Schema(description = "证书状态（0:有效, 1:过期, 2:吊销）", example = "0", allowableValues = {"0", "1", "2"}, nullable = true)
    @TableField("certificate_status")
    private String status;
}
