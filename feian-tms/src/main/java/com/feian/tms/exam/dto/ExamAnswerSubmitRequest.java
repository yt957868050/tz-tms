package com.feian.tms.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 提交答卷请求
 */
@Data
public class ExamAnswerSubmitRequest {

    @NotNull(message = "答卷ID不能为空")
    private Long sheetId;

    /** 作答耗时（秒） */
    private Integer durationSec;

    /** 用户自评分（保留字段，当前考试仅自动判分） */
    private Integer scoreUser;

    /** 答题明细 */
    private List<AnswerItem> answers;

    @Data
    public static class AnswerItem {
        @NotNull(message = "题目ID不能为空")
        private Long questionId;
        /** 原始答题 JSON */
        private String answerJson;
        /** 系统判定得分 */
        private Integer score;
        /** 是否正确 */
        private Integer correctFlag;
    }
}
