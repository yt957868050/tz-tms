package com.feian.tms.exam.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feian.tms.exam.domain.ExamPaperTemplate;
import com.feian.tms.exam.domain.ExamPaperTemplateItem;
import com.feian.tms.exam.domain.ExamQuestionContent;
import com.feian.tms.exam.domain.ExamSession;
import com.feian.tms.exam.dto.ExamPaperDetailResponse;
import com.feian.tms.exam.dto.ExamPaperSessionViewResponse;
import com.feian.tms.exam.mapper.ExamPaperTemplateItemMapper;
import com.feian.tms.exam.mapper.ExamPaperTemplateMapper;
import com.feian.tms.exam.mapper.ExamQuestionContentMapper;
import com.feian.tms.exam.mapper.ExamSessionMapper;
import com.feian.tms.exam.service.ExamPaperQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamPaperQueryServiceImpl implements ExamPaperQueryService {

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

    /**
     * 按选项乱序标记打乱 contentJson 中的 options 数组，并记录原索引
     */
    private ExamPaperDetailResponse.Item shuffleOptions(ExamPaperDetailResponse.Item item) {
        if (item == null || item.getContentJson() == null) {
            return item;
        }
        try {
            JSONObject obj = JSON.parseObject(item.getContentJson());
            JSONArray options = obj.getJSONArray("options");
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
            obj.put("options", shuffled);
            item.setContentJson(obj.toJSONString());
            item.setOptionOrder(idx);
        } catch (Exception ignore) {
            // 格式异常时忽略
        }
        return item;
    }
}
