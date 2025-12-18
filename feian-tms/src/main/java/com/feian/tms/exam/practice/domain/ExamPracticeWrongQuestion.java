package com.feian.tms.exam.practice.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自测练习-错题（每人每章仅保留最新一版）
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_practice_wrong_question")
public class ExamPracticeWrongQuestion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long machineTypeId;

    private Long categoryId;

    private Long questionId;
}

