package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 学员学习能力响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "学员学习能力响应对象")
@Data
public class StudentAbilityResponse {

    /** 学员学习能力ID */
    @Schema(description = "学员学习能力ID")
    private Long studentAbilityId;

    /** 学员ID */
    @Schema(description = "学员ID")
    private Long studentId;

    /** 机型ID */
    @Schema(description = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Schema(description = "专业ID")
    private Long majorId;

    /** 培训能力ID */
    @Schema(description = "培训能力ID")
    private Long trainingAbilityId;

    /** 课件ID */
    @Schema(description = "课件ID")
    private Long coursewareId;

    /** 学习进度（0未开始 1学习中 2已完成） */
    @Schema(description = "学习进度")
    private String learningProgress;

    /** 学习进度名称 */
    @Schema(description = "学习进度名称")
    private String learningProgressName;

    /** 理论成绩 */
    @Schema(description = "理论成绩")
    private BigDecimal theoryScore;

    /** 实践成绩 */
    @Schema(description = "实践成绩")
    private BigDecimal practiceScore;

    /** 综合成绩 */
    @Schema(description = "综合成绩")
    private BigDecimal totalScore;

    /** 学习开始时间 */
    @Schema(description = "学习开始时间")
    private Date learningStartTime;

    /** 学习完成时间 */
    @Schema(description = "学习完成时间")
    private Date learningEndTime;

    /** 资质状态（0未获得 1已获得 2已过期） */
    @Schema(description = "资质状态")
    private String qualificationStatus;

    /** 资质状态名称 */
    @Schema(description = "资质状态名称")
    private String qualificationStatusName;

    /** 资质获得时间 */
    @Schema(description = "资质获得时间")
    private Date qualificationDate;

    /** 资质有效期至 */
    @Schema(description = "资质有效期至")
    private Date validUntil;

    /** 学习备注 */
    @Schema(description = "学习备注")
    private String learningRemark;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态")
    private String status;

    /** 状态名称 */
    @Schema(description = "状态名称")
    private String statusName;

    /** 学员姓名 */
    @Schema(description = "学员姓名")
    private String studentName;

    /** 机型名称 */
    @Schema(description = "机型名称")
    private String machineTypeName;

    /** 专业名称 */
    @Schema(description = "专业名称")
    private String majorName;

    /** 培训能力名称 */
    @Schema(description = "培训能力名称")
    private String trainingAbilityName;

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