package com.feian.tms.exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 监考事件上报
 */
@Data
public class ExamProctorEventRequest {
    @NotNull(message = "答卷ID不能为空")
    private Long sheetId;

    @NotBlank(message = "事件类型不能为空")
    private String eventType;

    /** 事件内容 JSON */
    private String payload;
}
