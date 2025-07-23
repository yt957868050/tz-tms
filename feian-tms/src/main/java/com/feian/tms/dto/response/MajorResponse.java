package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 专业管理响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "专业管理响应对象")
public class MajorResponse {
    
    @Schema(description = "专业ID", example = "1")
    private Long majorId;

    @Schema(description = "专业名称", example = "飞行员专业")
    private String majorName;

    @Schema(description = "专业代码", example = "FLY001")
    private String majorCode;

    @Schema(description = "专业类型（1空勤 2地勤）", example = "1")
    private String majorType;

    @Schema(description = "专业描述", example = "直升机飞行员专业")
    private String majorDesc;

    @Schema(description = "状态（0正常 1停用）", example = "0")
    private String status;

    @Schema(description = "排序号", example = "1")
    private Integer orderNum;

    @Schema(description = "创建者", example = "admin")
    private String createBy;

    @Schema(description = "创建时间", example = "2024-01-01 10:00:00")
    private Date createTime;

    @Schema(description = "更新者", example = "admin")
    private String updateBy;

    @Schema(description = "更新时间", example = "2024-01-01 10:00:00")
    private Date updateTime;

    @Schema(description = "备注", example = "备注信息")
    private String remark;
}