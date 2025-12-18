package com.feian.tms.exam.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 试卷快照（按答卷维度固化：题序/选项序/题目内容）
 *
 * 用途：
 * - 确保考试过程中的乱序一致性（刷新/重进不变）
 * - 支持答卷回放（查看当次考试实际看到的题目与选项）
 */
@Data
public class ExamPaperSnapshot {
    /** 快照版本（便于兼容升级） */
    private Integer snapshotVersion;

    private Long sessionId;
    private Long paperId;

    private Boolean shuffleQuestion;
    private Boolean shuffleOption;
    private Boolean antiCheatCheat;
    private Boolean antiCheatWatch;
    private Boolean antiCheatCapture;

    /** 快照生成时间 */
    private Date snapshotTime;

    /** 实际题目序列（含 contentJson，可能已应用选项乱序） */
    private List<ExamPaperDetailResponse.Item> items;
}

