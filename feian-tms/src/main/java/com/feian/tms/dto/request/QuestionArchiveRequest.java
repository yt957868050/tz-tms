package com.feian.tms.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 题目分类归档请求对象
 * * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "题目分类归档请求对象")
public class QuestionArchiveRequest {

    @Schema(description = "分类ID (更新时使用)", example = "1")
    private Long id;

    @Schema(description = "题目分类名称", example = "基础知识分类一", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "分类名称不能为空")
    private String name;

    @Schema(description = "机型ID", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "机型ID不能为空")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "202", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "专业ID不能为空")
    private Long majorId;

    @Schema(description = "培训学时", example = "16")
    private Integer classHour;

    @Schema(description = "上级节点分类ID (根节点为null)", example = "0")
    private Long parentId;


}

