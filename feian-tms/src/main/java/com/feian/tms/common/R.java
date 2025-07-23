package com.feian.tms.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 通用返回对象
 * 用于统一API接口的返回格式
 *
 * @param <T> 数据类型
 */
@Data
@Schema(description = "通用返回对象")
public class R<T> {
    @Schema(description = "状态码")
    private final int code;

    @Schema(description = "返回信息")
    private final String msg;

    @Schema(description = "数据")
    private final T data;

    @Schema(description = "时间戳")
    private final long timestamp;

    private R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功返回，携带数据
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> success(T data) {
        return new R<>(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回，无数据
     *
     * @param <T> 数据类型
     * @return R
     */
    public static <T> R<T> success() {
        return new R<>(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回，自定义信息
     *
     * @param msg 信息
     * @param <T> 数据类型
     * @return R
     */
    public static <T> R<T> success(String msg) {
        return new R<>(HttpStatusEnum.SUCCESS.getCode(), msg, null);
    }

    /**
     * 成功返回，自定义信息和数据
     *
     * @param msg  信息
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> success(String msg, T data) {
        return new R<>(HttpStatusEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 失败返回，自定义信息
     *
     * @param msg 信息
     * @param <T> 数据类型
     * @return R
     */
    public static <T> R<T> fail(String msg) {
        return new R<>(HttpStatusEnum.FAIL.getCode(), msg, null);
    }

    /**
     * 失败返回，使用状态码
     *
     * @param status 状态码
     * @param <T>    数据类型
     * @return R
     */
    public static <T> R<T> fail(HttpStatusEnum status) {
        return new R<>(status.getCode(), status.getMessage(), null);
    }

    /**
     * 失败返回，自定义信息和数据
     *
     * @param msg  信息
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> fail(String msg, T data) {
        return new R<>(HttpStatusEnum.FAIL.getCode(), msg, data);
    }

    /**
     * 失败返回，自定义状态码和信息
     *
     * @param code 状态码
     * @param msg  信息
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, msg, null);
    }

    /**
     * 失败返回，自定义状态码、信息和数据
     *
     * @param code 状态码
     * @param msg  信息
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> fail(int code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    /**
     * 判断是否成功
     *
     * @return 是否成功
     */
    public boolean isSuccess() {
        return this.code == HttpStatusEnum.SUCCESS.getCode();
    }
}