package com.feian.tms.exam.dto;

import lombok.Data;

import java.util.Date;

/**
 * 考生结果视图（按“本场次+本人最新一次答卷”计算）
 */
@Data
public class ExamCandidateResultResponse {

    /** 考生记录ID */
    private Long id;

    private Long sessionId;
    private Long userId;
    private Long classId;

    /** 补考来源答卷（若有） */
    private Long makeupFromAnswerId;
    private Date windowStartOverride;
    private Date windowEndOverride;

    /** 最新答卷（可能为空=缺考） */
    private Long latestSheetId;
    private Integer latestSheetStatus; // 1进行中 2完成
    private Integer latestPassFlag;    // 0未通过 1通过
    private Date latestSheetCreateTime;

    /**
     * 结果状态：
     * PASS / FAIL / ABSENT / NOT_SUBMITTED / IN_PROGRESS / NOT_STARTED
     */
    private String resultStatus;

    /** 结果中文（便于前端直接展示） */
    private String resultLabel;

    /** 是否可补考（未通过/缺考/未交卷） */
    private Boolean makeupEligible;
}

