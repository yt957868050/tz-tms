package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 班次学员关联请求对象
 *
 * @author feian
 * @date 2025-01-24
 */
@Data
@Schema(description = "班次学员关联请求对象")
public class ClassStudentRequest {

    @Schema(description = "班次学员关联ID")
    private Long classStudentId;

    @Schema(description = "培训班次ID", example = "1")
    @NotNull(message = "培训班次ID不能为空")
    private Long trainingClassId;

    @Schema(description = "学员ID", example = "1")
    @NotNull(message = "学员ID不能为空")
    private Long studentId;

    @Schema(description = "学员ID列表，用于批量添加")
    private List<Long> studentIds;

    @Schema(description = "报名时间")
    private Date enrollTime;

    @Schema(description = "学员状态（0正常 1请假 2退课 3毕业）", example = "0")
    private String studentStatus;

    @Schema(description = "退课原因")
    private String withdrawReason;

    @Schema(description = "退课时间")
    private Date withdrawTime;

    @Schema(description = "备注")
    private String remark;
}