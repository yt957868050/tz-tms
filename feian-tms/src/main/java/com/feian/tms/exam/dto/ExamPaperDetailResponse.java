package com.feian.tms.exam.dto;

import com.feian.tms.exam.domain.ExamPaperTemplate;
import lombok.Data;

import java.util.List;

/**
 * 试卷完整信息（含题目内容）
 */
@Data
public class ExamPaperDetailResponse {
    private ExamPaperTemplate paper;
    private List<Item> items;

    @Data
    public static class Item {
        private Long questionId;
        private Integer score;
        private Integer ord;
        private String section;
        /** 题目 JSON 内容 */
        private String contentJson;
        /** 选项乱序后的索引（基于原 contentJson.options） */
        private List<Integer> optionOrder;
    }
}
