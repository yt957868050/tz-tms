package com.feian.tms.exam.service;

import com.feian.tms.exam.domain.ExamAnswerSheet;
import com.feian.tms.exam.domain.ExamProctorEvent;
import com.feian.tms.exam.dto.ExamAnswerStartRequest;
import com.feian.tms.exam.dto.ExamAnswerStartResponse;
import com.feian.tms.exam.dto.ExamAnswerSubmitRequest;
import com.feian.tms.exam.dto.ExamManualScoreRequest;
import com.feian.tms.exam.dto.ExamPaperSessionViewResponse;
import com.feian.tms.common.PageRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public interface ExamAnswerService {

    Page<ExamAnswerSheet> pageQuery(PageRequest<ExamAnswerSheet> pageRequest);

    Map<String, Object> stats(ExamAnswerSheet query);

    ExamAnswerSheet startExam(ExamAnswerStartRequest request);

    ExamAnswerStartResponse startExamWithPaper(ExamAnswerStartRequest request);

    ExamAnswerSheet submit(ExamAnswerSubmitRequest request);

    ExamAnswerSheet manualScore(ExamManualScoreRequest request);

    /** 按答卷ID获取当次考试试卷视图（快照优先） */
    ExamPaperSessionViewResponse loadPaperViewForSheet(Long sheetId);

    void recordProctorEvent(ExamProctorEvent event);

    List<ExamProctorEvent> listProctorEvents(Long sheetId);
}
