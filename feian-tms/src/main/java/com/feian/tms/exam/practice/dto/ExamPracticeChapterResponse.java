package com.feian.tms.exam.practice.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 自测练习-章节列表响应
 */
@Data
public class ExamPracticeChapterResponse {
    private Long categoryId;
    private String name;
    private Long parentId;
    private String path;
    /** 抽题数目（沿用 class_hour） */
    private Integer classHour;
    private Long machineTypeId;

    /** 本章节可自测题目数量（if_check=0） */
    private Long practiceQuestionCount;

    /** 已保存错题数量（当前用户、当前机型） */
    private Long wrongQuestionCount;

    /** 最近一次保存错题时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date wrongSavedTime;
}
