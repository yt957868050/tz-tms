package com.feian.tms.exam.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.exam.domain.*;
import com.feian.tms.exam.dto.ExamPaperDetailResponse;
import com.feian.tms.exam.dto.ExamPaperSessionViewResponse;
import com.feian.tms.exam.mapper.*;
import com.feian.tms.exam.service.ExamPaperQueryService;

import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamPaperQueryServiceImpl extends MPJBaseServiceImpl<ExamPaperTemplateMapper, ExamPaperTemplate> implements ExamPaperQueryService {

    private final ExamPaperTemplateMapper examPaperTemplateMapper;
    private final ExamPaperTemplateItemMapper examPaperTemplateItemMapper;
    private final ExamQuestionContentMapper examQuestionContentMapper;
    private final ExamSessionMapper examSessionMapper;

    @Override
    public ExamPaperDetailResponse loadPaper(Long paperId) {
        ExamPaperTemplate paper = examPaperTemplateMapper.selectById(paperId);
        if (paper == null) {
            return null;
        }
        List<ExamPaperTemplateItem> items = examPaperTemplateItemMapper.selectList(
                new LambdaQueryWrapper<ExamPaperTemplateItem>()
                        .eq(ExamPaperTemplateItem::getPaperId, paperId)
                        .orderByAsc(ExamPaperTemplateItem::getOrd));
        List<Long> qIds = items.stream().map(ExamPaperTemplateItem::getQuestionId).filter(Objects::nonNull).toList();
        Map<Long, ExamQuestionContent> contentMap = new HashMap<>();
        if (!qIds.isEmpty()) {
            List<ExamQuestionContent> contents = examQuestionContentMapper.selectBatchIds(qIds);
            contentMap = contents.stream().collect(Collectors.toMap(ExamQuestionContent::getQuestionId, c -> c));
        }
        final Map<Long, ExamQuestionContent> contentMapFinal = contentMap;
        ExamPaperDetailResponse response = new ExamPaperDetailResponse();
        response.setPaper(paper);
        response.setItems(items.stream().map(it -> {
            ExamPaperDetailResponse.Item dto = new ExamPaperDetailResponse.Item();
            dto.setQuestionId(it.getQuestionId());
            dto.setScore(it.getScore());
            dto.setOrd(it.getOrd());
            dto.setSection(it.getSection());
            ExamQuestionContent c = contentMapFinal.get(it.getQuestionId());
            if (c != null) {
                dto.setContentJson(c.getContentJson());
            }
            return dto;
        }).toList());
        return response;
    }

    @Override
    public ExamPaperSessionViewResponse loadPaperForSession(Long sessionId) {
        ExamSession session = examSessionMapper.selectById(sessionId);
        if (session == null) {
            return null;
        }
        ExamPaperDetailResponse detail = loadPaper(session.getPaperId());
        if (detail == null) {
            return null;
        }
        List<ExamPaperDetailResponse.Item> items = new ArrayList<>(detail.getItems());
        // 题目乱序：按照场次标记打乱返回顺序（不改数据库 ord）
        if (Boolean.TRUE.equals(session.getShuffleQuestion() != null && session.getShuffleQuestion() == 1)) {
            Collections.shuffle(items);
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setOrd(i + 1);
            }
        }
        ExamPaperSessionViewResponse view = new ExamPaperSessionViewResponse();
        view.setPaperDetail(detail);
        view.setItems(items);
        view.setShuffleQuestion(session.getShuffleQuestion() != null && session.getShuffleQuestion() == 1);
        view.setShuffleOption(session.getShuffleOption() != null && session.getShuffleOption() == 1);
        view.setAntiCheatCheat(session.getAntiCheatCheat() != null && session.getAntiCheatCheat() == 1);
        view.setAntiCheatWatch(session.getAntiCheatWatch() != null && session.getAntiCheatWatch() == 1);
        view.setAntiCheatCapture(session.getAntiCheatCapture() != null && session.getAntiCheatCapture() == 1);
        if (view.isShuffleOption()) {
            items = items.stream().map(this::shuffleOptions).toList();
            view.setItems(items);
        }
        return view;
    }

    @Override
    public List<ExamPaperTemplate> list() {
        return examPaperTemplateMapper.selectList(new LambdaQueryWrapper<ExamPaperTemplate>()
                .eq(ExamPaperTemplate::getIsDeleted, 0)
                .orderByDesc(ExamPaperTemplate::getCreateTime));

    }

    @Override
    public Page<ExamPaperTemplate> pageQuery(PageRequest<ExamPaperTemplate> pageRequest) {
        PageRequest<ExamPaperTemplate> req = pageRequest != null ? pageRequest : new PageRequest<>();
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ExamPaperTemplate> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(req.getPageNum(), req.getPageSize());
        ExamPaperTemplate query = req.getQuery();
        Long machineTypeId = query != null ? query.getMachineTypeId() : null;
        String name = query != null ? query.getName() : null;

        var wrapper = new LambdaQueryWrapper<ExamPaperTemplate>()
                .eq(machineTypeId != null, ExamPaperTemplate::getMachineTypeId, machineTypeId)
                .like(StringUtils.hasText(name), ExamPaperTemplate::getName, name)
                .orderByAsc(ExamPaperTemplate::getPaperId);
        return this.baseMapper.selectPage(page, wrapper);
    }


    /**
     * 按选项乱序标记打乱 contentJson 中的 options 数组，并记录原索引
     */
    private ExamPaperDetailResponse.Item shuffleOptions(ExamPaperDetailResponse.Item item) {
        if (item == null || item.getContentJson() == null) {
            return item;
        }
        try {
            JSONObject obj = JSON.parseObject(item.getContentJson());
            // 兼容两种题目内容格式：options / questionItemFrames（旧系统 QuestionFrame）
            String field = obj.containsKey("options") ? "options" : (obj.containsKey("questionItemFrames") ? "questionItemFrames" : null);
            if (field == null) {
                return item;
            }
            JSONArray options = obj.getJSONArray(field);
            if (options == null || options.isEmpty()) {
                return item;
            }
            List<Integer> idx = new ArrayList<>();
            for (int i = 0; i < options.size(); i++) {
                idx.add(i);
            }
            Collections.shuffle(idx);
            JSONArray shuffled = new JSONArray();
            for (Integer i : idx) {
                shuffled.add(options.get(i));
            }
            obj.put(field, shuffled);
            item.setContentJson(obj.toJSONString());
            item.setOptionOrder(idx);
        } catch (Exception ignore) {
            // 格式异常时忽略
        }
        return item;
    }
}
