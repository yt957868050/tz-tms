package com.feian.tms.exam.practice.dto;

import lombok.Data;

/**
 * 自测练习-题目返回
 */
@Data
public class ExamPracticeQuestionResponse {
    private Long questionId;
    private Long categoryId;
    private Integer questionType;
    private Integer score;
    private Integer difficult;
    private String contentJson;
}

