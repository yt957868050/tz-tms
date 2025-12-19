package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 题目分类归档响应对象
 * * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "题目分类归档响应对象")
public class QuestionArchiveResponse {

    @Schema(description = "分类ID", example = "1")
    private Long id;

    @Schema(description = "题目分类名称", example = "基础知识分类一")
    private String name;

    @Schema(description = "机型ID", example = "101")
    private Long machineTypeId;

    @Schema(description = "专业ID", example = "202")
    private Long majorId;

    @Schema(description = "培训学时", example = "16")
    private Integer classHour;

    @Schema(description = "上级节点分类ID", example = "1")
    private Long parentId;

    @Schema(description = "分类层级/路径（TEXT类型）", example = "/101/202/1/")
    private String level;



    @Schema(description = "子节点列表")
    private List<QuestionArchiveResponse> children;


}
