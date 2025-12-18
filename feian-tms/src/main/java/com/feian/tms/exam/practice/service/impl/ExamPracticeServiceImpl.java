package com.feian.tms.exam.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feian.tms.exam.domain.ExamCategory;
import com.feian.tms.exam.domain.ExamQuestion;
import com.feian.tms.exam.mapper.ExamCategoryMapper;
import com.feian.tms.exam.mapper.ExamQuestionMapper;
import com.feian.tms.exam.practice.domain.ExamPracticeWrongQuestion;
import com.feian.tms.exam.practice.dto.ExamPracticeChapterResponse;
import com.feian.tms.exam.practice.dto.ExamPracticeQuestionResponse;
import com.feian.tms.exam.practice.mapper.ExamPracticeQueryMapper;
import com.feian.tms.exam.practice.mapper.ExamPracticeWrongQuestionMapper;
import com.feian.tms.exam.practice.service.ExamPracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamPracticeServiceImpl implements ExamPracticeService {

    private final ExamPracticeQueryMapper examPracticeQueryMapper;
    private final ExamPracticeWrongQuestionMapper examPracticeWrongQuestionMapper;
    private final ExamCategoryMapper examCategoryMapper;
    private final ExamQuestionMapper examQuestionMapper;

    @Override
    public List<ExamPracticeChapterResponse> listChapters(Long userId, Long machineTypeId) {
        return examPracticeQueryMapper.selectChapterList(userId, machineTypeId);
    }

    @Override
    public List<ExamPracticeQuestionResponse> listAllQuestions(Long machineTypeId, Long categoryId) {
        return examPracticeQueryMapper.selectPracticeQuestions(machineTypeId, categoryId);
    }

    @Override
    public List<ExamPracticeQuestionResponse> listWrongQuestions(Long userId, Long machineTypeId, Long categoryId) {
        return examPracticeQueryMapper.selectWrongQuestions(userId, machineTypeId, categoryId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaveResult saveWrongQuestions(Long userId, Long machineTypeId, Long categoryId, List<Long> questionIds) {
        ExamCategory category = examCategoryMapper.selectById(categoryId);
        if (category == null || category.getIsDeleted() != null && category.getIsDeleted() == 1) {
            throw new IllegalArgumentException("章节不存在");
        }
        if (category.getMachineTypeId() != null && !Objects.equals(category.getMachineTypeId(), machineTypeId)) {
            throw new IllegalArgumentException("章节不属于当前机型");
        }

        Set<Long> dedup = CollectionUtils.isEmpty(questionIds)
                ? new HashSet<>()
                : questionIds.stream().filter(Objects::nonNull).collect(Collectors.toCollection(HashSet::new));

        // 先清空旧错题（覆盖式）
        examPracticeWrongQuestionMapper.purgeByUserMachineCategory(userId, machineTypeId, categoryId);

        if (dedup.isEmpty()) {
            return new SaveResult(0, 0);
        }

        // 仅保留有效题目（同机型、同章节、可自测）
        List<ExamQuestion> valid = examQuestionMapper.selectList(
                new LambdaQueryWrapper<ExamQuestion>()
                        .select(ExamQuestion::getQuestionId)
                        .eq(ExamQuestion::getMachineTypeId, machineTypeId)
                        .eq(ExamQuestion::getCategoryId, categoryId)
                        .eq(ExamQuestion::getIfCheck, 0)
                        .in(ExamQuestion::getQuestionId, dedup)
        );
        Set<Long> validIds = valid.stream().map(ExamQuestion::getQuestionId).filter(Objects::nonNull).collect(Collectors.toSet());

        int ignored = dedup.size() - validIds.size();
        if (validIds.isEmpty()) {
            return new SaveResult(0, ignored);
        }

        for (Long qid : validIds) {
            ExamPracticeWrongQuestion row = new ExamPracticeWrongQuestion();
            row.setUserId(userId);
            row.setMachineTypeId(machineTypeId);
            row.setCategoryId(categoryId);
            row.setQuestionId(qid);
            examPracticeWrongQuestionMapper.insert(row);
        }
        return new SaveResult(validIds.size(), ignored);
    }
}

