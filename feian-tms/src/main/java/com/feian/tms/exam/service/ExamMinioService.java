package com.feian.tms.exam.service;

import com.feian.tms.exam.service.dto.MinioUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface ExamMinioService {

    boolean enabled();

    MinioUploadResult upload(MultipartFile file, String dir);

    String presignedGet(String path, Integer expirySeconds);

    String toUrl(String path);
}
