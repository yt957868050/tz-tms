package com.feian.tms.exam.practice.dto;

import lombok.Data;

/**
 * 自测练习-章节列表请求
 */
@Data
public class ExamPracticeChapterRequest {
    /**
     * 管理员可传；学员/教员将忽略并使用当前选择机型
     */
    private Long machineTypeId;
}

