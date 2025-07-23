package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 培训类型管理响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训类型管理响应对象")
@Data
public class TrainingTypeResponse {

    /** 培训类型ID */
    @Schema(description = "培训类型ID")
    private Long trainingTypeId;

    /** 培训类型名称 */
    @Schema(description = "培训类型名称")
    private String trainingTypeName;

    /** 培训类型代码 */
    @Schema(description = "培训类型代码")
    private String trainingTypeCode;

    /** 培训方式（1理论 2实践） */
    @Schema(description = "培训方式")
    private String trainingMethod;

    /** 培训方式名称 */
    @Schema(description = "培训方式名称")
    private String trainingMethodName;

    /** 培训类型描述 */
    @Schema(description = "培训类型描述")
    private String trainingTypeDesc;

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