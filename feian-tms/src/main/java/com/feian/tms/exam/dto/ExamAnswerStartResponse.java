package com.feian.tms.exam.dto;

import com.feian.tms.exam.domain.ExamAnswerSheet;
import lombok.Data;

/**
 * 开考并返回试卷视图
 */
@Data
public class ExamAnswerStartResponse {
    private ExamAnswerSheet sheet;
    private ExamPaperSessionViewResponse paperView;
}
