package com.feian.tms.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 人工批改请求（已废弃：当前考试仅选择题自动判分）
 */
@Data
public class ExamManualScoreRequest {
    @NotNull(message = "答卷ID不能为空")
    private Long sheetId;

    private List<ItemScore> items;

    /** 是否直接判定通过（手工确认） */
    private Boolean passFlag;

    @Data
    public static class ItemScore {
        @NotNull(message = "题目ID不能为空")
        private Long questionId;
        private Integer score;
        private Integer correctFlag;
    }
}
