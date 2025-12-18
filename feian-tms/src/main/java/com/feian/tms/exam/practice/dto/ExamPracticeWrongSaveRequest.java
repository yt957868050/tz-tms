package com.feian.tms.exam.practice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 自测练习-保存错题列表（覆盖式）
 */
@Data
public class ExamPracticeWrongSaveRequest {
    /**
     * 管理员可传；学员/教员将忽略并使用当前选择机型
     */
    private Long machineTypeId;

    @NotNull(message = "章节ID不能为空")
    private Long categoryId;

    /**
     * 错题题目ID列表；为空表示清空
     */
    private List<Long> questionIds;
}

