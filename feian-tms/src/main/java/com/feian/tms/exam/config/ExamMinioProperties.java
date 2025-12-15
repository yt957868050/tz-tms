package com.feian.tms.exam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "exam.minio")
public class ExamMinioProperties {
    /** Minio 访问端点，如 http://host:9000 */
    private String endpoint;
    private String accessKey;
    private String secretKey;
    /** 默认桶 */
    private String bucket;
    /** 对外访问域名（可用于拼接 URL），可与 endpoint 相同 */
    private String baseUrl;
    /** 签名有效期（秒），默认 3600 */
    private Integer expirySeconds = 3600;
    /** 上传最大字节数（默认 10MB） */
    private Long maxSizeBytes = 10 * 1024 * 1024L;
    /** 允许的扩展名，逗号分隔（不区分大小写），为空则不限制 */
    private String allowedExt;

    /** 是否启用（endpoint/accessKey/secretKey/bucket 均存在时认为启用） */
    public boolean enabled() {
        return hasText(endpoint) && hasText(accessKey) && hasText(secretKey) && hasText(bucket);
    }

    private boolean hasText(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
