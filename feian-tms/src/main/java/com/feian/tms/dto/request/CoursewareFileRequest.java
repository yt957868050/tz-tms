package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 课件文件请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Schema(description = "课件文件请求对象")
@Data
public class CoursewareFileRequest {

    /** 课件文件ID */
    @Schema(description = "课件文件ID")
    private Long coursewareFileId;

    /** 课件ID */
    @Schema(description = "课件ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "课件ID不能为空")
    private Long coursewareId;

    /** 文件名称 */
    @Schema(description = "文件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "文件名称不能为空")
    private String fileName;

    /** 文件类型（1教材 2教案 3PPT 4CBT 5工卡 6其他） */
    @Schema(description = "文件类型", allowableValues = {"1", "2", "3", "4", "5", "6"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "文件类型不能为空")
    private String fileType;

    /** 文件路径 */
    @Schema(description = "文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "文件路径不能为空")
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
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;

    /** 排序 */
    @Schema(description = "排序")
    private Integer orderNum;
}