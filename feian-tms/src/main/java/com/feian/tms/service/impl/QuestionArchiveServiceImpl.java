package com.feian.tms.service.impl;

import com.feian.tms.domain.QuestionArchive;
import com.feian.tms.mapper.QuestionArchiveMapper;
import com.feian.tms.service.QuestionArchiveService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionArchiveServiceImpl extends MPJBaseServiceImpl< QuestionArchiveMapper ,QuestionArchive> implements QuestionArchiveService {
    @Autowired
    public QuestionArchiveMapper questionArchiveMapper;
    /**
     * 获取题目分类树形结构
     * @return
     */
    public List<QuestionArchive> getRootQuestionArchive() {
        return questionArchiveMapper.getRootQuestionArchive();
    }

    /**
     * 根据父级ID获取子级分类
     * @param id
     * @return
     */
    public List<QuestionArchive> getQuestionArchiveByParentId(Long id) {
        return questionArchiveMapper.getQuestionArchiveByParentId(id);
    }
}
