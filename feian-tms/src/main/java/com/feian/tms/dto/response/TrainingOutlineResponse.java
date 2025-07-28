package com.feian.tms.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 培训大纲响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "培训大纲响应对象")
public class TrainingOutlineResponse {

    /** 大纲ID */
    @Schema(description = "大纲ID")
    private Long outlineId;

    /** 机型ID */
    @Schema(description = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Schema(description = "专业ID")
    private Long majorId;

    /** 培训能力ID */
    @Schema(description = "培训能力ID")
    private Long trainingAbilityId;

    /** 大纲名称 */
    @Schema(description = "大纲名称")
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
    @Schema(description = "理论培训课时")
    private BigDecimal theoryHours;

    /** 实作培训课时 */
    @Schema(description = "实作培训课时")
    private BigDecimal practicalHours;

    /** 总课时 */
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

    // 关联信息

    /** 机型名称 */
    @Schema(description = "机型名称")
    private String machineTypeName;

    /** 机型图片 */
    @Schema(description = "机型图片")
    private String machineTypeImage;

    /** 专业名称 */
    @Schema(description = "专业名称")
    private String majorName;

    /** 培训能力名称 */
    @Schema(description = "培训能力名称")
    private String trainingAbilityName;

    /** 机型信息 */
    @Schema(description = "机型信息")
    private MachineTypeResponse machineType;

    /** 专业信息 */
    @Schema(description = "专业信息")
    private MajorResponse major;

    /** 培训能力信息 */
    @Schema(description = "培训能力信息")
    private TrainingAbilityResponse trainingAbility;

    /** 大纲章节列表 */
    @Schema(description = "大纲章节列表")
    private List<OutlineChapterResponse> chapters;

    /** 章节总数 */
    @Schema(description = "章节总数")
    private Integer chapterCount;

    /** 是否有文件 */
    @Schema(description = "是否有培训规范文件")
    private Boolean hasRegulationFile;

    /** 是否有大纲文件 */
    @Schema(description = "是否有大纲文件")
    private Boolean hasOutlineFile;
}