package com.feian.tms.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 大纲章节响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "大纲章节响应对象")
public class OutlineChapterResponse {

    /** 章节ID */
    @Schema(description = "章节ID")
    private Long chapterId;

    /** 大纲ID */
    @Schema(description = "大纲ID")
    private Long outlineId;

    /** 章节编码 */
    @Schema(description = "章节编码")
    private String chapterCode;

    /** 章节名称 */
    @Schema(description = "章节名称")
    private String chapterName;

    /** 父章节ID */
    @Schema(description = "父章节ID")
    private Long parentId;

    /** 章节层级 */
    @Schema(description = "章节层级")
    private Integer chapterLevel;

    /** 排序 */
    @Schema(description = "排序")
    private Integer sortOrder;

    /** 理论课时 */
    @Schema(description = "理论课时")
    private BigDecimal theoryHours;

    /** 实作课时 */
    @Schema(description = "实作课时")
    private BigDecimal practicalHours;

    /** 总课时 */
    @Schema(description = "总课时")
    private BigDecimal totalHours;

    /** 是否必修 */
    @Schema(description = "是否必修（0选修 1必修）")
    private String isRequired;

    /** 是否必修名称 */
    @Schema(description = "是否必修名称")
    private String isRequiredName;

    /** 章节描述 */
    @Schema(description = "章节描述")
    private String description;

    /** 学习目标 */
    @Schema(description = "学习目标")
    private String learningObjectives;

    /** 状态 */
    @Schema(description = "状态（0正常 1停用）")
    private String status;

    /** 状态名称 */
    @Schema(description = "状态名称")
    private String statusName;

    /** 创建者 */
    @Schema(description = "创建者")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private Date createTime;

    /** 更新者 */
    @Schema(description = "更新者")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private Date updateTime;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;

    // 树形结构字段

    /** 子章节列表 */
    @Schema(description = "子章节列表")
    private List<OutlineChapterResponse> children;

    /** 父章节名称 */
    @Schema(description = "父章节名称")
    private String parentName;

    /** 是否有子章节 */
    @Schema(description = "是否有子章节")
    private Boolean hasChildren;

    /** 子章节数量 */
    @Schema(description = "子章节数量")
    private Integer childrenCount;
}