package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 班次学员批量添加请求对象
 *
 * @author feian
 * @date 2025-01-24
 */
@Data
@Schema(description = "班次学员批量添加请求对象")
public class ClassStudentBatchAddRequest {

    @Schema(description = "培训班次ID")
    @NotNull(message = "培训班次ID不能为空")
    private Long trainingClassId;

    @Schema(description = "学员ID列表")
    @NotNull(message = "学员ID列表不能为空")
    private List<Long> studentIds;

    @Schema(description = "备注")
    private String remark;
}