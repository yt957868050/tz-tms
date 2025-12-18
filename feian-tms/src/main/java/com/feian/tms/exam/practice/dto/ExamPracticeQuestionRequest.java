package com.feian.tms.exam.practice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 自测练习-题目列表请求
 */
@Data
public class ExamPracticeQuestionRequest {
    /**
     * 管理员可传；学员/教员将忽略并使用当前选择机型
     */
    private Long machineTypeId;

    @NotNull(message = "章节ID不能为空")
    private Long categoryId;

    /**
     * all | wrong
     */
    private String mode;
}

