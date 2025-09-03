package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训记录响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "培训记录响应对象")
@Data
public class TrainingRecordResponse {

    /** 培训记录ID */
    @Schema(description = "培训记录ID")
    private Long trainingRecordId;

    /** 学员ID */
    @Schema(description = "学员ID")
    private Long studentId;

    /** 学员姓名 */
    @Schema(description = "学员姓名")
    private String studentName;

    /** 学员编号 */
    @Schema(description = "学员工号", example = "S001")
    private String studentCode;

    /** 机型ID */
    @Schema(description = "机型ID")
    private Long machineTypeId;

    /** 机型名称 */
    @Schema(description = "机型名称")
    private String machineTypeName;

    /** 专业ID */
    @Schema(description = "专业ID")
    private Long majorId;

    /** 专业名称 */
    @Schema(description = "专业名称")
    private String majorName;

    /** 培训能力ID */
    @Schema(description = "培训能力ID")
    private Long trainingAbilityId;

    /** 培训能力名称 */
    @Schema(description = "培训能力名称")
    private String abilityName;

    /** 培训班次ID */
    @Schema(description = "培训班次ID")
    private Long trainingClassId;

    /** 班次名称 */
    @Schema(description = "班次名称")
    private String className;

    /** 理论培训时长 */
    @Schema(description = "理论培训时长")
    private Integer theoryHour;

    /** 实作培训时长 */
    @Schema(description = "实作培训时长")
    private Integer practiceHour;

    /** 培训总时长 */
    @Schema(description = "培训总时长")
    private Integer totalHour;

    /** 理论成绩 */
    @Schema(description = "理论成绩")
    private Integer theoryScore;

    /** 实作成绩（1合格 0不合格） */
    @Schema(description = "实作成绩")
    private Integer practiceScore;
}