package com.feian.tms.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 答卷
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_answer_sheet")
public class ExamAnswerSheet extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long sheetId;

    private Long sessionId;
    private Long paperId;
    private Long userId;

    private Integer scoreSystem;
    private Integer scoreUser;
    private Integer correctCount;
    private Integer passFlag;
    private Integer status;        // 1进行中 2完成
    private Integer durationSec;
    private Long judgeUser;

    /** 原始答卷 JSON */
    private String rawAnswerJson;
}
