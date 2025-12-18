package com.feian.tms.exam.dto;

import lombok.Data;

import java.util.List;

/**
 * 题目编辑（界面化）响应
 */
@Data
public class ExamQuestionUiResponse {

    private Long questionId;
    private Long machineTypeId;
    private Long categoryId;
    private Integer questionType;
    private Integer score;
    private Integer difficult;
    private Integer status;
    private String source;
    private String questionFrameId;
    private Integer ifCheck;

    private String title;
    private String analyze;
    private String path;

    private List<ExamQuestionUiItem> items;
    private String correct;
    private List<String> correctArray;
}

