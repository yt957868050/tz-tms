package com.feian.tms.exam.dto;

import lombok.Data;

/**
 * 题目编辑（界面化）-选项/填空项
 */
@Data
public class ExamQuestionUiItem {

    /** 选项 UUID（填空题可用） */
    private String itemUuid;

    /** 选项前缀（如 A/B/C 或 填空序号 1/2/...） */
    private String prefix;

    /** 选项/标答内容（支持富文本 HTML） */
    private String content;

    /** 选项/填空分值（可选） */
    private Integer score;
}

