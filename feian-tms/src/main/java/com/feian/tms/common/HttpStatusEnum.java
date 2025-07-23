package com.feian.tms.common;

import lombok.Getter;

/**
 * HTTP状态码
 */
@Getter
public enum HttpStatusEnum {

    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    UNAUTHORIZED(401, "未认证"),
    FORBIDDEN(403, "无权限"),
    NOT_FOUND(404, "资源不存在"),
    BAD_REQUEST(400, "请求参数错误"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持当前媒体类型"),
    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),

    // 业务错误码，使用1000以上的编码
    PARAM_ERROR(1000, "参数错误"),
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_ALREADY_EXIST(1002, "用户已存在"),
    PASSWORD_ERROR(1003, "密码错误"),
    ACCOUNT_LOCKED(1004, "账号已锁定"),
    TOKEN_EXPIRED(1005, "令牌已过期"),
    TOKEN_INVALID(1006, "令牌无效"),
    PERMISSION_DENIED(1007, "权限不足"),
    FILE_UPLOAD_FAILED(1008, "文件上传失败"),
    FILE_DOWNLOAD_FAILED(1009, "文件下载失败"),
    FILE_TYPE_ERROR(1010, "文件类型错误"),
    FILE_SIZE_EXCEED(1011, "文件大小超出限制"),
    RATE_LIMIT_EXCEEDED(1012, "请求频率超出限制"),
    DATA_ALREADY_EXIST(1013, "数据已存在"),
    DATA_NOT_EXIST(1014, "数据不存在"),
    OPERATION_FAILED(1015, "操作失败"),
    ENCRYPT_ERROR(1016, "加密失败"),
    ;

    private final int code;
    private final String message;

    HttpStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据状态码获取枚举
     *
     * @param code 状态码
     * @return 枚举
     */
    public static HttpStatusEnum valueOf(int code) {
        for (HttpStatusEnum status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }
}
