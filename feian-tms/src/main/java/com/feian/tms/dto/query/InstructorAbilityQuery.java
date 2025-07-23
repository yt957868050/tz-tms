package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 教员授课能力查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "教员授课能力查询对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class InstructorAbilityQuery extends PageRequest<InstructorAbilityQuery> {

    /** 教员ID */
    @Schema(description = "教员ID")
    private Long instructorId;

    /** 教员姓名 */
    @Schema(description = "教员姓名")
    private String instructorName;

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
    @Schema(description = "授课资质等级", allowableValues = {"1", "2", "3", "4"})
    private String abilityLevel;

    /** 是否主讲（0否 1是） */
    @Schema(description = "是否主讲", allowableValues = {"0", "1"})
    private String isPrimary;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;

    /** 资质获得开始时间 */
    @Schema(description = "资质获得开始时间")
    private Date qualificationStartDate;

    /** 资质获得结束时间 */
    @Schema(description = "资质获得结束时间")
    private Date qualificationEndDate;

    /** 资质有效期开始时间 */
    @Schema(description = "资质有效期开始时间")
    private Date validStartDate;

    /** 资质有效期结束时间 */
    @Schema(description = "资质有效期结束时间")
    private Date validEndDate;

    /** 资质获得时间 */
    @Schema(description = "资质获得时间")
    private Date qualificationDate;

    /** 资质有效期 */
    @Schema(description = "资质有效期")
    private Date validUntil;
}