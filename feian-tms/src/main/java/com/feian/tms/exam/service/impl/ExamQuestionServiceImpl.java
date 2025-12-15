package com.feian.tms.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.exam.domain.ExamQuestion;
import com.feian.tms.exam.domain.ExamQuestionContent;
import com.feian.tms.exam.dto.ExamPaperDetailResponse;
import com.feian.tms.exam.dto.ExamQuestionRequest;
import com.feian.tms.exam.mapper.ExamQuestionContentMapper;
import com.feian.tms.exam.mapper.ExamQuestionMapper;
import com.feian.tms.exam.service.ExamQuestionService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ExamQuestionServiceImpl extends MPJBaseServiceImpl<ExamQuestionMapper, ExamQuestion> implements ExamQuestionService {

    private final ExamQuestionMapper examQuestionMapper;
    private final ExamQuestionContentMapper examQuestionContentMapper;

    @Override
    public Page<ExamQuestion> pageQuery(PageRequest<ExamQuestionRequest> pageRequest) {
        PageRequest<ExamQuestionRequest> req = pageRequest != null ? pageRequest : new PageRequest<>();
        Page<ExamQuestion> page = new Page<>(req.getPageNum(), req.getPageSize());
        ExamQuestionRequest query = req.getQuery();
        Long machineTypeId = query != null ? query.getMachineTypeId() : null;
        Long categoryId = query != null ? query.getCategoryId() : null;
        Integer questionType = query != null ? query.getQuestionType() : null;
        Integer difficult = query != null ? query.getDifficult() : null;
        Integer status = query != null ? query.getStatus() : null;
        String questionFrameId = query != null ? query.getQuestionFrameId() : null;
        var wrapper = new LambdaQueryWrapper<ExamQuestion>()
                .eq(machineTypeId != null, ExamQuestion::getMachineTypeId, machineTypeId)
                .eq(categoryId != null, ExamQuestion::getCategoryId, categoryId)
                .eq(questionType != null, ExamQuestion::getQuestionType, questionType)
                .eq(difficult != null, ExamQuestion::getDifficult, difficult)
                .eq(status != null, ExamQuestion::getStatus, status)
                .like(StringUtils.hasText(questionFrameId), ExamQuestion::getQuestionFrameId, questionFrameId)
                .orderByDesc(ExamQuestion::getCreateTime);
        return examQuestionMapper.selectPage(page, wrapper);
    }

    @Override
    public ExamPaperDetailResponse.Item getDetail(Long questionId) {
        ExamQuestion question = examQuestionMapper.selectById(questionId);
        if (question == null) {
            return null;
        }
        ExamQuestionContent content = examQuestionContentMapper.selectById(questionId);
        ExamPaperDetailResponse.Item item = new ExamPaperDetailResponse.Item();
        item.setQuestionId(question.getQuestionId());
        item.setSection(question.getCategoryId() != null ? String.valueOf(question.getCategoryId()) : null);
        item.setScore(question.getScore());
        item.setContentJson(content != null ? content.getContentJson() : null);
        return item;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdateQuestion(ExamQuestionRequest request) {
        Long questionId = request.getQuestionId();
        boolean isNew = (questionId == null);
        if (isNew) {
            questionId = IdWorker.getId();
        }
        ExamQuestion entity = new ExamQuestion();
        BeanUtils.copyProperties(request, entity);
        entity.setQuestionId(questionId);
        entity.setUpdateTime(new Date());
        if (isNew) {
            entity.setCreateTime(new Date());
            examQuestionMapper.insert(entity);
        } else {
            examQuestionMapper.updateById(entity);
        }

        ExamQuestionContent content = examQuestionContentMapper.selectById(questionId);
        if (content == null) {
            content = new ExamQuestionContent();
            content.setQuestionId(questionId);
            content.setCreateTime(new Date());
            content.setContentJson(request.getContentJson());
            examQuestionContentMapper.insert(content);
        } else {
            content.setContentJson(request.getContentJson());
            content.setUpdateTime(new Date());
            examQuestionContentMapper.updateById(content);
        }
        return questionId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQuestion(Long questionId) {
        examQuestionMapper.deleteById(questionId);
        examQuestionContentMapper.deleteById(questionId);
    }
}
