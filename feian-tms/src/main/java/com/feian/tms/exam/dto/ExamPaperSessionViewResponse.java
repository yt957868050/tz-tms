package com.feian.tms.exam.dto;

import lombok.Data;

import java.util.List;

/**
 * 按场次获取试卷时返回的视图，带乱序/防作弊标记
 */
@Data
public class ExamPaperSessionViewResponse {
    private ExamPaperDetailResponse paperDetail;

    /** 是否题目乱序、选项乱序（选项乱序仅标记，不在后端改写内容） */
    private boolean shuffleQuestion;
    private boolean shuffleOption;

    /** 防作弊策略标记 */
    private boolean antiCheatCheat;
    private boolean antiCheatWatch;
    private boolean antiCheatCapture;

    /** 实际的题目序列（可能乱序后的 ord） */
    private List<ExamPaperDetailResponse.Item> items;
}
