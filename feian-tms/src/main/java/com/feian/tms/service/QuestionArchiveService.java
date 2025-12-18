package com.feian.tms.service;

import com.feian.tms.domain.QuestionArchive;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

public interface QuestionArchiveService extends MPJBaseService<QuestionArchive> {

    /**
     * 获取题目分类树形结构
     * @return
     */
    List<QuestionArchive> getRootQuestionArchive();

    /**
     * 根据父级id获取题目分类
     * @param id
     * @return
     */
    List<QuestionArchive> getQuestionArchiveByParentId(Long id);
}
