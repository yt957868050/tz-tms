package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 学员学习能力查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "学员学习能力查询对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentAbilityQuery extends PageRequest<StudentAbilityQuery> {

    /** 学员ID */
    @Schema(description = "学员ID")
    private Long studentId;

    /** 学员姓名 */
    @Schema(description = "学员姓名")
    private String studentName;

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
    @Schema(description = "学习进度", allowableValues = {"0", "1", "2"})
    private String learningProgress;

    /** 资质状态（0未获得 1已获得 2已过期） */
    @Schema(description = "资质状态", allowableValues = {"0", "1", "2"})
    private String qualificationStatus;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;

    /** 学习开始时间范围 - 开始 */
    @Schema(description = "学习开始时间范围 - 开始")
    private Date learningStartTimeBegin;

    /** 学习开始时间范围 - 结束 */
    @Schema(description = "学习开始时间范围 - 结束")
    private Date learningStartTimeEnd;

    /** 学习完成时间范围 - 开始 */
    @Schema(description = "学习完成时间范围 - 开始")
    private Date learningEndTimeBegin;

    /** 学习完成时间范围 - 结束 */
    @Schema(description = "学习完成时间范围 - 结束")
    private Date learningEndTimeEnd;

    /** 资质获得时间范围 - 开始 */
    @Schema(description = "资质获得时间范围 - 开始")
    private Date qualificationDateBegin;

    /** 资质获得时间范围 - 结束 */
    @Schema(description = "资质获得时间范围 - 结束")
    private Date qualificationDateEnd;

    /** 资质有效期范围 - 开始 */
    @Schema(description = "资质有效期范围 - 开始")
    private Date validUntilBegin;

    /** 资质有效期范围 - 结束 */
    @Schema(description = "资质有效期范围 - 结束")
    private Date validUntilEnd;

    /** 学习开始时间 */
    @Schema(description = "学习开始时间")
    private Date learningStartTime;

    /** 学习结束时间 */
    @Schema(description = "学习结束时间")
    private Date learningEndTime;
}