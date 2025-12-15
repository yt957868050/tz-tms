package com.feian.tms.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feian.tms.exam.domain.ExamCategory;
import com.feian.tms.exam.domain.ExamPaperTemplate;
import com.feian.tms.exam.domain.ExamPaperTemplateItem;
import com.feian.tms.exam.domain.ExamQuestion;
import com.feian.tms.exam.dto.ExamPaperBuildRequest;
import com.feian.tms.exam.dto.ExamPaperBuildResponse;
import com.feian.tms.exam.mapper.ExamCategoryMapper;
import com.feian.tms.exam.mapper.ExamPaperTemplateItemMapper;
import com.feian.tms.exam.mapper.ExamPaperTemplateMapper;
import com.feian.tms.exam.mapper.ExamQuestionMapper;
import com.feian.tms.exam.service.ClassHourQuotaCalculator;
import com.feian.tms.exam.service.ExamPaperBuildService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamPaperBuildServiceImpl implements ExamPaperBuildService {

    private final ExamCategoryMapper examCategoryMapper;
    private final ExamQuestionMapper examQuestionMapper;
    private final ExamPaperTemplateMapper examPaperTemplateMapper;
    private final ExamPaperTemplateItemMapper examPaperTemplateItemMapper;
    private final ClassHourQuotaCalculator classHourQuotaCalculator;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamPaperBuildResponse buildPaper(ExamPaperBuildRequest request) {
        if (request.getMachineTypeId() == null) {
            throw new IllegalArgumentException("机型不能为空");
        }

        List<ExamPaperBuildRequest.CategoryQuota> quotas = resolveCategories(request);
        if (CollectionUtils.isEmpty(quotas)) {
            throw new IllegalArgumentException("未找到可用章节，请检查机型或章节配置");
        }

        Map<Long, ExamCategory> categoryMap = loadCategories(quotas);
        LinkedHashMap<Long, Integer> desired = new LinkedHashMap<>();
        LinkedHashMap<Long, Integer> available = new LinkedHashMap<>();
        for (ExamPaperBuildRequest.CategoryQuota quota : quotas) {
            Long categoryId = quota.getCategoryId();
            ExamCategory category = categoryMap.get(categoryId);
            Integer defaultQuota = quota.getQuota();
            if (defaultQuota == null && category != null) {
                defaultQuota = category.getClassHour();
            }
            desired.put(categoryId, Math.max(0, Objects.requireNonNullElse(defaultQuota, 0)));

            Integer availableCount = examQuestionMapper.countAvailable(
                    request.getMachineTypeId(), categoryId, request.getQuestionTypes(), request.getDifficultList());
            available.put(categoryId, Objects.requireNonNullElse(availableCount, 0));
        }

        Map<Long, Integer> allocation = classHourQuotaCalculator.allocate(desired, available);
        List<ExamPaperTemplateItem> items = new ArrayList<>();
        int ord = 1;
        int totalScore = 0;
        for (Map.Entry<Long, Integer> entry : allocation.entrySet()) {
            Long categoryId = entry.getKey();
            Integer limit = entry.getValue();
            if (limit == null || limit <= 0) {
                continue;
            }
            List<ExamQuestion> picked = examQuestionMapper.selectRandomByCategory(
                    request.getMachineTypeId(), categoryId, request.getQuestionTypes(), request.getDifficultList(), limit);
            for (ExamQuestion question : picked) {
                ExamPaperTemplateItem item = new ExamPaperTemplateItem();
                item.setPaperId(null); // set after template insert
                item.setQuestionId(question.getQuestionId());
                item.setScore(question.getScore());
                item.setOrd(ord++);
                item.setSection(categoryId != null ? categoryId.toString() : null);
                items.add(item);
                totalScore += Optional.ofNullable(question.getScore()).orElse(0);
            }
        }

        ExamPaperTemplate paper = new ExamPaperTemplate();
        paper.setName(StringUtils.hasText(request.getName()) ? request.getName() : buildDefaultName(request.getMachineTypeId()));
        paper.setMachineTypeId(request.getMachineTypeId());
        paper.setTotalScore(totalScore);
        paper.setPassScore(request.getPassScore());
        paper.setPassQuestionNum(request.getPassQuestionNum());
        paper.setShuffleQuestion(toFlag(request.getShuffleQuestion()));
        paper.setShuffleOption(toFlag(request.getShuffleOption()));
        paper.setAntiCheatCheat(toFlag(request.getAntiCheatCheat()));
        paper.setAntiCheatWatch(toFlag(request.getAntiCheatWatch()));
        paper.setAntiCheatCapture(toFlag(request.getAntiCheatCapture()));
        examPaperTemplateMapper.insert(paper);

        for (ExamPaperTemplateItem item : items) {
            item.setPaperId(paper.getPaperId());
            examPaperTemplateItemMapper.insert(item);
        }

        ExamPaperBuildResponse response = new ExamPaperBuildResponse();
        response.setPaperId(paper.getPaperId());
        response.setName(paper.getName());
        response.setTotalQuestions(items.size());
        response.setTotalScore(totalScore);
        response.setCategories(allocation.entrySet().stream().map(entry -> {
            ExamPaperBuildResponse.CategoryStat stat = new ExamPaperBuildResponse.CategoryStat();
            stat.setCategoryId(entry.getKey());
            stat.setDesired(desired.get(entry.getKey()));
            stat.setAvailable(available.get(entry.getKey()));
            stat.setAllocated(entry.getValue());
            return stat;
        }).collect(Collectors.toList()));
        return response;
    }

    private List<ExamPaperBuildRequest.CategoryQuota> resolveCategories(ExamPaperBuildRequest request) {
        if (!CollectionUtils.isEmpty(request.getCategories())) {
            return request.getCategories();
        }
        List<ExamCategory> categories = examCategoryMapper.selectList(
                new LambdaQueryWrapper<ExamCategory>()
                        .eq(ExamCategory::getMachineTypeId, request.getMachineTypeId()));
        return categories.stream().map(cat -> {
            ExamPaperBuildRequest.CategoryQuota quota = new ExamPaperBuildRequest.CategoryQuota();
            quota.setCategoryId(cat.getCategoryId());
            quota.setQuota(cat.getClassHour());
            return quota;
        }).collect(Collectors.toList());
    }

    private Map<Long, ExamCategory> loadCategories(List<ExamPaperBuildRequest.CategoryQuota> quotas) {
        List<Long> ids = quotas.stream().map(ExamPaperBuildRequest.CategoryQuota::getCategoryId).toList();
        if (ids.isEmpty()) {
            return Map.of();
        }
        List<ExamCategory> categories = examCategoryMapper.selectBatchIds(ids);
        return categories.stream().collect(Collectors.toMap(ExamCategory::getCategoryId, c -> c));
    }

    private int toFlag(Boolean value) {
        return Boolean.TRUE.equals(value) ? 1 : 0;
    }

    private String buildDefaultName(Long machineTypeId) {
        return "机型-" + machineTypeId + "-随机组卷";
    }
}
