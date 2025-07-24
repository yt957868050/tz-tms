package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 培训大纲对象 tms_training_outline
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_training_outline")
@Schema(description = "培训大纲")
public class TrainingOutline extends BaseEntity {
    
    private static final long serialVersionUID = 1L;

    /** 大纲ID */
    @TableId(type = IdType.AUTO)
    @Schema(description = "大纲ID")
    private Long outlineId;

    /** 机型ID */
    @NotNull(message = "机型不能为空")
    @Schema(description = "机型ID", required = true)
    private Long machineTypeId;

    /** 专业ID */
    @NotNull(message = "专业不能为空")
    @Schema(description = "专业ID", required = true)
    private Long majorId;

    /** 培训能力ID */
    @NotNull(message = "培训能力不能为空")
    @Schema(description = "培训能力ID", required = true)
    private Long trainingAbilityId;

    /** 大纲名称 */
    @NotBlank(message = "大纲名称不能为空")
    @Schema(description = "大纲名称", required = true)
    private String outlineName;

    /** 大纲编码 */
    @Schema(description = "大纲编码")
    private String outlineCode;

    /** 培训规范文件ID */
    @Schema(description = "培训规范文件ID")
    private Long regulationFileId;

    /** 培训规范文件名 */
    @Schema(description = "培训规范文件名")
    private String regulationFileName;

    /** 培训规范文件路径 */
    @Schema(description = "培训规范文件路径")
    private String regulationFilePath;

    /** 大纲文件ID */
    @Schema(description = "大纲文件ID")
    private Long outlineFileId;

    /** 大纲文件名 */
    @Schema(description = "大纲文件名")
    private String outlineFileName;

    /** 大纲文件路径 */
    @Schema(description = "大纲文件路径")
    private String outlineFilePath;

    /** 理论培训课时 */
    @NotNull(message = "理论培训课时不能为空")
    @DecimalMin(value = "0.0", message = "理论培训课时不能小于0")
    @Schema(description = "理论培训课时", required = true)
    private BigDecimal theoryHours;

    /** 实作培训课时 */
    @NotNull(message = "实作培训课时不能为空")
    @DecimalMin(value = "0.0", message = "实作培训课时不能小于0")
    @Schema(description = "实作培训课时", required = true)
    private BigDecimal practicalHours;

    /** 总课时（计算字段） */
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    @Schema(description = "总课时")
    private BigDecimal totalHours;

    /** 大纲描述 */
    @Schema(description = "大纲描述")
    private String description;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "生效日期")
    private Date effectiveDate;

    /** 失效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "失效日期")
    private Date expiryDate;

    /** 版本号 */
    @Schema(description = "版本号")
    private String version;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态（0正常 1停用）")
    private String status;

    // 非数据库字段，用于关联查询
    
    /** 机型名称 */
    @TableField(exist = false)
    @Schema(description = "机型名称")
    private String machineTypeName;

    /** 专业名称 */
    @TableField(exist = false)
    @Schema(description = "专业名称")
    private String majorName;

    /** 培训能力名称 */
    @TableField(exist = false)
    @Schema(description = "培训能力名称")
    private String trainingAbilityName;

    /** 机型信息 */
    @TableField(exist = false)
    @Schema(description = "机型信息")
    private MachineType machineType;

    /** 专业信息 */
    @TableField(exist = false)
    @Schema(description = "专业信息")
    private Major major;

    /** 培训能力信息 */
    @TableField(exist = false)
    @Schema(description = "培训能力信息")
    private TrainingAbility trainingAbility;

    /** 大纲章节列表 */
    @TableField(exist = false)
    @Schema(description = "大纲章节列表")
    private List<OutlineChapter> chapters;
}