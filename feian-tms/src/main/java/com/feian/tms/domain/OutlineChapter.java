package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

/**
 * 大纲章节对象 tms_outline_chapter
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_outline_chapter")
@Schema(description = "大纲章节")
public class OutlineChapter extends BaseEntity {
    
    private static final long serialVersionUID = 1L;

    /** 章节ID */
    @TableId(type = IdType.AUTO)
    @Schema(description = "章节ID")
    private Long chapterId;

    /** 大纲ID */
    @NotNull(message = "大纲ID不能为空")
    @Schema(description = "大纲ID", required = true)
    private Long outlineId;

    /** 章节编码（如ATA章节） */
    @NotBlank(message = "章节编码不能为空")
    @Schema(description = "章节编码", required = true)
    private String chapterCode;

    /** 章节名称 */
    @NotBlank(message = "章节名称不能为空")
    @Schema(description = "章节名称", required = true)
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
    @DecimalMin(value = "0.0", message = "理论课时不能小于0")
    @Schema(description = "理论课时")
    private BigDecimal theoryHours;

    /** 实作课时 */
    @DecimalMin(value = "0.0", message = "实作课时不能小于0")
    @Schema(description = "实作课时")
    private BigDecimal practicalHours;

    /** 是否必修（0选修 1必修） */
    @Schema(description = "是否必修（0选修 1必修）")
    private String isRequired;

    /** 章节描述 */
    @Schema(description = "章节描述")
    private String description;

    /** 学习目标 */
    @Schema(description = "学习目标")
    private String learningObjectives;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态（0正常 1停用）")
    private String status;

    // 非数据库字段

    /** 子章节列表 */
    @TableField(exist = false)
    @Schema(description = "子章节列表")
    private List<OutlineChapter> children;

    /** 父章节名称 */
    @TableField(exist = false)
    @Schema(description = "父章节名称")
    private String parentName;

    /** 总课时 */
    @TableField(exist = false)
    @Schema(description = "总课时")
    private BigDecimal totalHours;

    /** 是否有子章节 */
    @TableField(exist = false)
    @Schema(description = "是否有子章节")
    private Boolean hasChildren;
}