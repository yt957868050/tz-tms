package com.feian.tms.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 分页请求
 * 用于封装前端分页查询参数
 */
@Schema(description = "分页请求")
@Data
public class PageRequest<T> {
    /**
     * 页码，最小值为1
     */
    @Schema(description = "页码", defaultValue = "1")
    @Min(value = 1, message = "页码不能小于1")
    private int pageNum = 1;

    /**
     * 每页数量，最小值为1，最大值为100
     */
    @Schema(description = "每页数量", defaultValue = "10")
    @Min(value = 1, message = "每页数量不能小于1")
    @Max(value = 100, message = "每页数量不能大于100")
    private int pageSize = 10;

    /**
     * 查询条件
     */
    @Schema(description = "查询条件")
    private T query;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private List<SortField> sortField;


    /**
     * 排序字段类
     */
    @Schema(description = "排序字段类")
    @Data
    public static class SortField {
        /**
         * 字段名
         */
        @Schema(description = "字段名")
        @NotNull(message = "排序字段名不能为空")
        private String fieldName;

        /**
         * 排序方式，默认升序：ASC, DESC
         */
        @Schema(description = "排序方式", allowableValues = {"ASC", "DESC"}, defaultValue = "ASC")
        private String order = "ASC";
    }

    /**
     * 转换为Spring Data JPA分页对象
     *
     * @return Spring Data JPA分页对象
     */
    public org.springframework.data.domain.Pageable toPageable() {
        org.springframework.data.domain.Sort sort = org.springframework.data.domain.Sort.unsorted();
        if (sortField != null && !sortField.isEmpty()) {
            org.springframework.data.domain.Sort.Order[] orders = sortField.stream()
                    .map(s -> "DESC".equalsIgnoreCase(s.getOrder()) 
                            ? org.springframework.data.domain.Sort.Order.desc(s.getFieldName())
                            : org.springframework.data.domain.Sort.Order.asc(s.getFieldName()))
                    .toArray(org.springframework.data.domain.Sort.Order[]::new);
            sort = org.springframework.data.domain.Sort.by(orders);
        }
        return org.springframework.data.domain.PageRequest.of(pageNum - 1, pageSize, sort);
    }
}
