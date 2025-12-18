package com.feian.tms.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 题目创建/更新请求
 */
@Data
public class ExamQuestionRequest {

    /** 题目ID，可为空时自动生成（保持与旧系统兼容使用外部ID） */
    private Long questionId;

    @NotNull(message = "机型不能为空")
    private Long machineTypeId;

    private Long categoryId;
    private Integer questionType;
    private Integer score;
    private Integer difficult;
    private Integer status;
    private String source;
    private String questionFrameId;

    /** 是否考核: 0=否(可自测练习) 1=是(仅供考试) */
    private Integer ifCheck;

    /** 原始题目 JSON 快照 */
    private String rawJson;

    /** 标准化的题目内容 JSON */
    @NotNull(message = "题目内容不能为空")
    private String contentJson;
}
