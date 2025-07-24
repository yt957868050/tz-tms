package com.feian.tms.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训大纲请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "培训大纲请求对象")
public class TrainingOutlineRequest {

    /** 大纲ID */
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

    /** 备注 */
    @Schema(description = "备注")
    private String remark;

    // 查询条件字段

    /** 机型名称（查询用） */
    @Schema(description = "机型名称")
    private String machineTypeName;

    /** 专业名称（查询用） */
    @Schema(description = "专业名称")
    private String majorName;

    /** 培训能力名称（查询用） */
    @Schema(description = "培训能力名称")
    private String trainingAbilityName;

    /** 生效开始日期（查询用） */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "生效开始日期")
    private Date effectiveDateStart;

    /** 生效结束日期（查询用） */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "生效结束日期")
    private Date effectiveDateEnd;
}