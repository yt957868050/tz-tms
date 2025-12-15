package com.feian.tms.exam.controller;

import com.feian.tms.common.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.net.URI;

/**
 * 题目/试卷附件 Minio 代理下载（轻量版，无签名写入）
 * 配置：
 *   exam.minio.base-url: http://host:port (可选，用于拼接 path 或校验 url)
 */
@RestController
@RequestMapping("/api/exam/file")
@Tag(name = "考试-文件预览")
@RequiredArgsConstructor
public class ExamFileController {

    @Value("${exam.minio.base-url:}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final com.feian.tms.exam.service.ExamMinioService examMinioService;

    @GetMapping("/preview")
    @Operation(summary = "Minio 文件预览代理", description = "支持传入完整 url 或基于 base-url 的 path")
    public Object preview(@RequestParam(required = false) String url,
                          @RequestParam(required = false) String path,
                          HttpServletResponse response) {
        String target = buildTargetUrl(url, path);
        if (!StringUtils.hasText(target)) {
            return R.fail("缺少 url/path 或未配置 exam.minio.base-url");
        }
        try {
            ResponseEntity<byte[]> entity = restTemplate.getForEntity(URI.create(target), byte[].class);
            if (!entity.getStatusCode().is2xxSuccessful() || entity.getBody() == null) {
                return R.fail("文件获取失败");
            }
            MediaType mediaType = entity.getHeaders().getContentType();
            response.setStatus(HttpStatus.OK.value());
            response.setHeader(HttpHeaders.CONTENT_TYPE, mediaType != null ? mediaType.toString() : MediaType.APPLICATION_OCTET_STREAM_VALUE);
            // 禁止缓存
            response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store, no-cache, must-revalidate, max-age=0");
            byte[] body = entity.getBody();
            try (OutputStream os = response.getOutputStream()) {
                os.write(body);
                os.flush();
            }
            return null;
        } catch (Exception e) {
            return R.fail("文件代理失败: " + e.getMessage());
        }
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传文件到 Minio（考试域）")
    public R<?> upload(@RequestParam("file") MultipartFile file,
                       @RequestParam(value = "dir", required = false) String dir) {
        if (!examMinioService.enabled()) {
            return R.fail("Minio 未配置");
        }
        try {
            var result = examMinioService.upload(file, dir);
            return R.success(result);
        } catch (Exception e) {
            return R.fail("上传失败: " + e.getMessage());
        }
    }

    @GetMapping("/sign")
    @Operation(summary = "获取文件签名 URL")
    public R<String> sign(@RequestParam("path") String path,
                          @RequestParam(value = "expiry", required = false) Integer expiry) {
        if (!examMinioService.enabled()) {
            return R.fail("Minio 未配置");
        }
        try {
            String url = examMinioService.presignedGet(path, expiry);
            return R.success(url);
        } catch (Exception e) {
            return R.fail("签名失败: " + e.getMessage());
        }
    }

    private String buildTargetUrl(String url, String path) {
        if (StringUtils.hasText(url)) {
            if (StringUtils.hasText(baseUrl) && !url.startsWith(baseUrl)) {
                // 非白名单则拒绝，避免 SSRF
                return null;
            }
            return url;
        }
        if (StringUtils.hasText(path) && StringUtils.hasText(baseUrl)) {
            String cleaned = path.startsWith("/") ? path.substring(1) : path;
            if (!baseUrl.endsWith("/")) {
                return baseUrl + "/" + cleaned;
            }
            return baseUrl + cleaned;
        }
        return null;
    }
}
