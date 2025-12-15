package com.feian.tms.exam.service;

import com.feian.tms.exam.dto.ExamPaperBuildRequest;
import com.feian.tms.exam.dto.ExamPaperBuildResponse;

public interface ExamPaperBuildService {

    /**
     * 按章节 class_hour 自动抽题生成试卷
     */
    ExamPaperBuildResponse buildPaper(ExamPaperBuildRequest request);
}
