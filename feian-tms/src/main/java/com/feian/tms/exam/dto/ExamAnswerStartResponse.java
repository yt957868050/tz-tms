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

    /** 总时长（秒，按场次建议时长计算，可能为空） */
    private Integer totalTimeSec;

    /** 剩余时长（秒，综合场次窗口与建议时长计算） */
    private Integer remainTimeSec;

    /** 作弊次数（切屏/离开次数，基于监考事件统计） */
    private Integer cheatCount;
}
