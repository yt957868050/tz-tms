package com.feian.tms.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 答卷明细
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_answer_item")
public class ExamAnswerItem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long sheetId;
    private Long questionId;
    private String answerJson;
    private Integer score;
    private Integer correctFlag;
}
