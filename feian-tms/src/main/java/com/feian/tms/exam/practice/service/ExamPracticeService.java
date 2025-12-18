package com.feian.tms.exam.practice.service;

import com.feian.tms.exam.practice.dto.ExamPracticeChapterResponse;
import com.feian.tms.exam.practice.dto.ExamPracticeQuestionResponse;

import java.util.List;

public interface ExamPracticeService {

    List<ExamPracticeChapterResponse> listChapters(Long userId, Long machineTypeId);

    List<ExamPracticeQuestionResponse> listAllQuestions(Long machineTypeId, Long categoryId);

    List<ExamPracticeQuestionResponse> listWrongQuestions(Long userId, Long machineTypeId, Long categoryId);

    SaveResult saveWrongQuestions(Long userId, Long machineTypeId, Long categoryId, List<Long> questionIds);

    record SaveResult(int savedCount, int ignoredCount) {}
}

