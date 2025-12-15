package com.feian.tms.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 试卷模板题目明细
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_paper_template_item")
public class ExamPaperTemplateItem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long paperId;
    private Long questionId;
    private Integer score;
    private Integer ord;
    private String section;
}
