package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训计划明细响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训计划明细响应对象")
@Data
public class TrainingPlanDetailResponse {

    /** 培训计划明细ID */
    @Schema(description = "培训计划明细ID")
    private Long trainingPlanDetailId;

    /** 培训计划ID */
    @Schema(description = "培训计划ID")
    private Long trainingPlanId;

    /** 课件ID */
    @Schema(description = "课件ID")
    private Long coursewareId;

    /** 课程顺序 */
    @Schema(description = "课程顺序")
    private Integer courseOrder;

    /** 理论学时 */
    @Schema(description = "理论学时")
    private BigDecimal theoryHours;

    /** 实践学时 */
    @Schema(description = "实践学时")
    private BigDecimal practiceHours;

    /** 是否必修（0选修 1必修） */
    @Schema(description = "是否必修")
    private String isRequired;

    /** 是否必修名称 */
    @Schema(description = "是否必修名称")
    private String isRequiredName;

    /** 课程要求 */
    @Schema(description = "课程要求")
    private String courseRequirement;

    /** 课程名称 */
    @Schema(description = "课程名称")
    private String courseName;

    /** 课程编码 */
    @Schema(description = "课程编码")
    private String courseCode;

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