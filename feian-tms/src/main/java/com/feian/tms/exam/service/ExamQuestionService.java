package com.feian.tms.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.exam.domain.ExamQuestion;
import com.feian.tms.exam.dto.ExamPaperDetailResponse;
import com.feian.tms.exam.dto.ExamQuestionRequest;
import com.github.yulichang.base.MPJBaseService;

public interface ExamQuestionService extends MPJBaseService<ExamQuestion> {

    Page<ExamQuestion> pageQuery(PageRequest<ExamQuestionRequest> pageRequest);

    ExamPaperDetailResponse.Item getDetail(Long questionId);

    Long saveOrUpdateQuestion(ExamQuestionRequest request);

    void deleteQuestion(Long questionId);
}
