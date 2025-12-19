package com.feian.tms.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.exam.domain.ExamCategory;
import com.feian.tms.exam.domain.ExamPaperTemplate;
import com.feian.tms.exam.dto.ExamPaperDetailResponse;
import com.feian.tms.exam.dto.ExamPaperSessionViewResponse;

import com.github.yulichang.base.MPJBaseService;

import java.util.List;

public interface ExamPaperQueryService extends MPJBaseService<ExamPaperTemplate> {

    /**
     * 查询试卷及题目内容
     */
    ExamPaperDetailResponse loadPaper(Long paperId);

    /**
     * 按场次返回试卷视图，应用乱序标记（题目乱序在后端处理，选项乱序仅标记）
     */
    ExamPaperSessionViewResponse loadPaperForSession(Long sessionId);

    List<ExamPaperTemplate> list();


    Page<ExamPaperTemplate> pageQuery(PageRequest<ExamPaperTemplate> pageRequest);
}
