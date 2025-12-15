package com.feian.tms.exam.dto;

import lombok.Data;

import java.util.List;

/**
 * 组卷结果
 */
@Data
public class ExamPaperBuildResponse {
    private Long paperId;
    private String name;
    private Integer totalQuestions;
    private Integer totalScore;
    private List<CategoryStat> categories;

    @Data
    public static class CategoryStat {
        private Long categoryId;
        private Integer desired;
        private Integer available;
        private Integer allocated;
    }
}
