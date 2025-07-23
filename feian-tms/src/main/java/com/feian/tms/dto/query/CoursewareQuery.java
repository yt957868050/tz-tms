package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 课件管理查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "课件管理查询对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class CoursewareQuery extends PageRequest<CoursewareQuery> {

    /** 课程编码 */
    @Schema(description = "课程编码")
    private String courseCode;

    /** 课程名称 */
    @Schema(description = "课程名称")
    private String courseName;

    /** ATA章节 */
    @Schema(description = "ATA章节")
    private String ataChapter;

    /** 机型ID */
    @Schema(description = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Schema(description = "专业ID")
    private Long majorId;

    /** 培训类型ID */
    @Schema(description = "培训类型ID")
    private Long trainingTypeId;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;

    /** 机型名称 */
    @Schema(description = "机型名称")
    private String machineTypeName;

    /** 专业名称 */
    @Schema(description = "专业名称")
    private String majorName;

    /** 培训类型名称 */
    @Schema(description = "培训类型名称")
    private String trainingTypeName;
}