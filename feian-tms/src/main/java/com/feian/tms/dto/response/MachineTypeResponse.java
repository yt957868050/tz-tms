package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 机型管理响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "机型管理响应对象")
public class MachineTypeResponse {
    
    @Schema(description = "机型ID", example = "1")
    private Long machineTypeId;

    @Schema(description = "机型名称", example = "Bell-206")
    private String machineTypeName;

    @Schema(description = "机型代码", example = "B206")
    private String machineTypeCode;

    @Schema(description = "机型图片URL", example = "http://example.com/image.jpg")
    private String machineTypeImage;

    @Schema(description = "机型描述", example = "双发直升机")
    private String machineTypeDesc;

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