package com.feian.tms.exam.dto;

import lombok.Data;

import java.util.Date;

/**
 * 我的考试场次（含考生信息）
 */
@Data
public class ExamMySessionResponse {

    private Long sessionId;
    private String name;
    private Long paperId;
    private Long machineTypeId;

    private Date windowStart;
    private Date windowEnd;
    private Integer suggestTimeMin;

    private String passRuleType;
    private Integer passScore;
    private Integer passQuestionNum;

    private Integer shuffleQuestion;
    private Integer shuffleOption;
    private Integer antiCheatCheat;
    private Integer antiCheatWatch;
    private Integer antiCheatCapture;

    /** 考生记录ID */
    private Long candidateId;
    private Long classId;
    private Long makeupFromAnswerId;
    private Date windowStartOverride;
    private Date windowEndOverride;
}

