package com.feian.tms.dto.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 机型管理查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "机型管理查询对象")
public class MachineTypeQuery {
    
    @Schema(description = "机型名称", example = "Bell-206")
    private String machineTypeName;

    @Schema(description = "机型代码", example = "B206")
    private String machineTypeCode;

    @Schema(description = "状态（0正常 1停用）", example = "0", allowableValues = {"0", "1"})
    private String status;

    @Schema(description = "页码", example = "1", defaultValue = "1")
    private Integer pageNum = 1;

    @Schema(description = "页面大小", example = "10", defaultValue = "10")
    private Integer pageSize = 10;
}