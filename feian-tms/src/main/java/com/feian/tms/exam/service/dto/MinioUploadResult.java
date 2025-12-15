package com.feian.tms.exam.service.dto;

import lombok.Data;

@Data
public class MinioUploadResult {
    /** 对象存储路径（桶内 objectName） */
    private String path;
    /** 可访问 URL（baseUrl + path 或签名 URL） */
    private String url;
}
