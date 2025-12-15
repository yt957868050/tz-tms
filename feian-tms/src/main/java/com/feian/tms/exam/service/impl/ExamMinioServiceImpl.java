package com.feian.tms.exam.service.impl;

import com.feian.tms.exam.config.ExamMinioProperties;
import com.feian.tms.exam.service.ExamMinioService;
import com.feian.tms.exam.service.dto.MinioUploadResult;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamMinioServiceImpl implements ExamMinioService {

    private final ExamMinioProperties properties;

    private MinioClient client() {
        return MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }

    @Override
    public boolean enabled() {
        return properties.enabled();
    }

    @Override
    public MinioUploadResult upload(MultipartFile file, String dir) {
        if (!enabled()) {
            throw new IllegalStateException("Minio 未配置");
        }
        try {
            String suffix = "";
            String original = file.getOriginalFilename();
            if (StringUtils.hasText(original) && original.contains(".")) {
                suffix = original.substring(original.lastIndexOf("."));
            }
            String folder = StringUtils.hasText(dir) ? dir : LocalDate.now().toString();
            String objectName = folder + "/" + UUID.randomUUID() + suffix;

            MinioClient client = client();
            // 确认桶存在
            boolean exists = client.bucketExists(
                    io.minio.BucketExistsArgs.builder().bucket(properties.getBucket()).build()
            );
            if (!exists) {
                client.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucket()).build());
            }
            try (InputStream is = file.getInputStream()) {
                client.putObject(PutObjectArgs.builder()
                        .bucket(properties.getBucket())
                        .object(objectName)
                        .stream(is, file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
            }
            MinioUploadResult result = new MinioUploadResult();
            result.setPath(objectName);
            result.setUrl(toUrl(objectName));
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Minio 上传失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String presignedGet(String path, Integer expirySeconds) {
        if (!enabled()) {
            throw new IllegalStateException("Minio 未配置");
        }
        try {
            int exp = expirySeconds != null ? expirySeconds : properties.getExpirySeconds();
            return client().getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(properties.getBucket())
                    .object(path)
                    .expiry(exp)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("生成签名 URL 失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String toUrl(String path) {
        if (!StringUtils.hasText(path)) {
            return path;
        }
        if (StringUtils.hasText(properties.getBaseUrl())) {
            String base = properties.getBaseUrl().endsWith("/") ? properties.getBaseUrl() : properties.getBaseUrl() + "/";
            String p = path.startsWith("/") ? path.substring(1) : path;
            return base + p;
        }
        // 兜底返回 endpoint + bucket + path
        String endpoint = properties.getEndpoint();
        if (endpoint != null && endpoint.endsWith("/")) {
            endpoint = endpoint.substring(0, endpoint.length() - 1);
        }
        String p = path.startsWith("/") ? path.substring(1) : path;
        return endpoint + "/" + properties.getBucket() + "/" + p;
    }
}
