package com.feian.tms.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 题目内容（标准化）
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_question_content")
public class ExamQuestionContent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 题目ID（与 exam_question 一致） */
    @TableId(type = IdType.INPUT)
    private Long questionId;

    /** 标准化后的题目内容 JSON */
    private String contentJson;
}
