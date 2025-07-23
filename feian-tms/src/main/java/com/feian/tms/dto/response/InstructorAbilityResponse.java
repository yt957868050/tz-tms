package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 教员授课能力响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "教员授课能力响应对象")
@Data
public class InstructorAbilityResponse {

    /** 教员授课能力ID */
    @Schema(description = "教员授课能力ID")
    private Long instructorAbilityId;

    /** 教员ID */
    @Schema(description = "教员ID")
    private Long instructorId;

    /** 机型ID */
    @Schema(description = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Schema(description = "专业ID")
    private Long majorId;

    /** 培训类型ID */
    @Schema(description = "培训类型ID")
    private Long trainingTypeId;

    /** 课件ID */
    @Schema(description = "课件ID")
    private Long coursewareId;

    /** 授课资质等级（1初级 2中级 3高级 4专家） */
    @Schema(description = "授课资质等级")
    private String abilityLevel;

    /** 授课资质等级名称 */
    @Schema(description = "授课资质等级名称")
    private String abilityLevelName;

    /** 资质获得时间 */
    @Schema(description = "资质获得时间")
    private Date qualificationDate;

    /** 资质有效期至 */
    @Schema(description = "资质有效期至")
    private Date validUntil;

    /** 授课次数 */
    @Schema(description = "授课次数")
    private Integer teachingCount;

    /** 授课评分 */
    @Schema(description = "授课评分")
    private Float teachingScore;

    /** 是否主讲（0否 1是） */
    @Schema(description = "是否主讲")
    private String isPrimary;

    /** 是否主讲名称 */
    @Schema(description = "是否主讲名称")
    private String isPrimaryName;

    /** 备注 */
    @Schema(description = "备注")
    private String abilityRemark;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态")
    private String status;

    /** 状态名称 */
    @Schema(description = "状态名称")
    private String statusName;

    /** 教员姓名 */
    @Schema(description = "教员姓名")
    private String instructorName;

    /** 机型名称 */
    @Schema(description = "机型名称")
    private String machineTypeName;

    /** 专业名称 */
    @Schema(description = "专业名称")
    private String majorName;

    /** 培训类型名称 */
    @Schema(description = "培训类型名称")
    private String trainingTypeName;

    /** 课程名称 */
    @Schema(description = "课程名称")
    private String courseName;

    /** 创建者 */
    @Schema(description = "创建者")
    private String createBy;

    /** 创建时间 */
    @Schema(description = "创建时间")
    private Date createTime;

    /** 更新者 */
    @Schema(description = "更新者")
    private String updateBy;

    /** 更新时间 */
    @Schema(description = "更新时间")
    private Date updateTime;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;
}