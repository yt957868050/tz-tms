package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 培训能力管理响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训能力管理响应对象")
@Data
public class TrainingAbilityResponse {

    /** 培训能力ID */
    @Schema(description = "培训能力ID")
    private Long trainingAbilityId;

    /** 培训能力名称 */
    @Schema(description = "培训能力名称")
    private String trainingAbilityName;

    /** 培训能力代码 */
    @Schema(description = "培训能力代码")
    private String trainingAbilityCode;

    /** 培训能力类型（1培训 2复训 3恢复考试） */
    @Schema(description = "培训能力类型")
    private String abilityType;

    /** 培训能力类型名称 */
    @Schema(description = "培训能力类型名称")
    private String abilityTypeName;

    /** 培训能力描述 */
    @Schema(description = "培训能力描述")
    private String trainingAbilityDesc;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态")
    private String status;

    /** 状态名称 */
    @Schema(description = "状态名称")
    private String statusName;

    /** 排序 */
    @Schema(description = "排序")
    private Integer orderNum;

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