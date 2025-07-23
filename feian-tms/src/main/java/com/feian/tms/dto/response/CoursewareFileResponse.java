package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 课件文件响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "课件文件响应对象")
@Data
public class CoursewareFileResponse {

    /** 课件文件ID */
    @Schema(description = "课件文件ID")
    private Long coursewareFileId;

    /** 课件ID */
    @Schema(description = "课件ID")
    private Long coursewareId;

    /** 文件名称 */
    @Schema(description = "文件名称")
    private String fileName;

    /** 文件类型（1教材 2教案 3PPT 4CBT 5工卡 6其他） */
    @Schema(description = "文件类型")
    private String fileType;

    /** 文件类型名称 */
    @Schema(description = "文件类型名称")
    private String fileTypeName;

    /** 文件路径 */
    @Schema(description = "文件路径")
    private String filePath;

    /** 文件大小(KB) */
    @Schema(description = "文件大小(KB)")
    private Long fileSize;

    /** 文件扩展名 */
    @Schema(description = "文件扩展名")
    private String fileExtension;

    /** 文件描述 */
    @Schema(description = "文件描述")
    private String fileDesc;

    /** 下载次数 */
    @Schema(description = "下载次数")
    private Integer downloadCount;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态")
    private String status;

    /** 状态名称 */
    @Schema(description = "状态名称")
    private String statusName;

    /** 排序 */
    @Schema(description = "排序")
    private Integer orderNum;

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