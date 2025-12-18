package com.feian.tms.exam.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 题目编辑（界面化）请求：前端不直接操作 contentJson，由后端组装并落库。
 */
@Data
public class ExamQuestionUiRequest {

    /** 题目ID；为空则新增 */
    private Long questionId;

    @NotNull(message = "机型不能为空")
    private Long machineTypeId;

    /** 章节ID */
    private Long categoryId;

    /** 题型 */
    @NotNull(message = "题型不能为空")
    private Integer questionType;

    /** 分值 */
    private Integer score;

    /** 难度 */
    private Integer difficult;

    /** 状态 */
    private Integer status;

    /** 来源标识 */
    private String source;

    /** 原题内容ID（便于对照，沿用旧系统 UUID） */
    private String questionFrameId;

    /** 是否考核: 0=否(可自测) 1=是(仅考试) */
    private Integer ifCheck;

    /** 题干（富文本 HTML） */
    @NotBlank(message = "题干不能为空")
    private String title;

    /** 解析（富文本 HTML，可选） */
    private String analyze;

    /** 题干图片（可选，单图 URL/Path） */
    private String path;

    /** 选项/填空项 */
    @Valid
    private List<ExamQuestionUiItem> items;

    /** 单选/判断/简答：标答 */
    private String correct;

    /** 多选/不定项：标答 */
    private List<String> correctArray;
}

