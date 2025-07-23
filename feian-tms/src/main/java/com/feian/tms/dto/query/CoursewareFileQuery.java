package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 课件文件查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "课件文件查询对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class CoursewareFileQuery extends PageRequest<CoursewareFileQuery> {

    /** 课件ID */
    @Schema(description = "课件ID")
    private Long coursewareId;

    /** 文件名称 */
    @Schema(description = "文件名称")
    private String fileName;

    /** 文件类型（1教材 2教案 3PPT 4CBT 5工卡 6其他） */
    @Schema(description = "文件类型", allowableValues = {"1", "2", "3", "4", "5", "6"})
    private String fileType;

    /** 文件扩展名 */
    @Schema(description = "文件扩展名")
    private String fileExtension;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;

    /** 课程名称 */
    @Schema(description = "课程名称")
    private String courseName;
}