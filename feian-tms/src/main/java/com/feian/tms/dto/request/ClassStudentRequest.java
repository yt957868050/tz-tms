package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * 班次学员关联请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "班次学员关联请求对象")
public class ClassStudentRequest {
    
    @Schema(description = "班次学员关联ID", example = "1")
    private Long classStudentId;

    @Schema(description = "培训班次ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "培训班次ID不能为空")
    private Long trainingClassId;

    @Schema(description = "学员ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "学员ID不能为空")
    private Long studentId;

    @Schema(description = "报名时间", example = "2024-01-01 10:00:00")
    private Date enrollTime;

    @Schema(description = "学员状态", example = "0", allowableValues = {"0", "1", "2", "3", "4"})
    private String studentStatus;

    @Schema(description = "退班原因", example = "个人原因")
    private String withdrawReason;

    @Schema(description = "退班时间", example = "2024-02-01 10:00:00")
    private Date withdrawTime;

    @Schema(description = "备注", example = "备注信息")
    private String remark;
}