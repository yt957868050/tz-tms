package com.feian.tms.exam.service;

import com.feian.tms.exam.dto.ExamPaperDetailResponse;
import com.feian.tms.exam.dto.ExamPaperSessionViewResponse;

public interface ExamPaperQueryService {

    /**
     * 查询试卷及题目内容
     */
    ExamPaperDetailResponse loadPaper(Long paperId);

    /**
     * 按场次返回试卷视图，应用乱序标记（题目乱序在后端处理，选项乱序仅标记）
     */
    ExamPaperSessionViewResponse loadPaperForSession(Long sessionId);
}
