package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 机型管理查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "机型管理查询对象")
public class MachineTypeQuery extends PageRequest<MachineTypeQuery> {
    
    @Schema(description = "机型名称", example = "Bell-206")
    private String machineTypeName;

    @Schema(description = "机型代码", example = "B206")
    private String machineTypeCode;

    @Schema(description = "状态（0正常 1停用）", example = "0", allowableValues = {"0", "1"})
    private String status;
}