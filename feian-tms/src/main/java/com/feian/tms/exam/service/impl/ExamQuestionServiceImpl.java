package com.feian.tms.exam.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.exam.domain.ExamQuestion;
import com.feian.tms.exam.domain.ExamQuestionContent;
import com.feian.tms.exam.dto.ExamPaperDetailResponse;
import com.feian.tms.exam.dto.ExamQuestionRequest;
import com.feian.tms.exam.dto.ExamQuestionUiItem;
import com.feian.tms.exam.dto.ExamQuestionUiRequest;
import com.feian.tms.exam.dto.ExamQuestionUiResponse;
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
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamQuestionServiceImpl extends MPJBaseServiceImpl<ExamQuestionMapper, ExamQuestion> implements ExamQuestionService {

    /**
     * 当前考试系统仅使用选择题题型（含判断/不定项）。
     * 1 单选，2 多选，3 判断，6 不定项选择
     */
    private static final Set<Integer> CHOICE_TYPES = Set.of(1, 2, 3, 6);

    private final ExamQuestionMapper examQuestionMapper;
    private final ExamQuestionContentMapper examQuestionContentMapper;

    private void assertChoiceType(Integer questionType) {
        if (questionType == null || !CHOICE_TYPES.contains(questionType)) {
            throw new IllegalArgumentException("仅支持选择题题型（1单选/2多选/3判断/6不定项）");
        }
    }

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
        Integer ifCheck = query != null ? query.getIfCheck() : null;
        String questionFrameId = query != null ? query.getQuestionFrameId() : null;
        var wrapper = new LambdaQueryWrapper<ExamQuestion>()
                .eq(machineTypeId != null, ExamQuestion::getMachineTypeId, machineTypeId)
                .eq(categoryId != null, ExamQuestion::getCategoryId, categoryId)
                .eq(questionType != null, ExamQuestion::getQuestionType, questionType)
                .eq(difficult != null, ExamQuestion::getDifficult, difficult)
                .eq(status != null, ExamQuestion::getStatus, status)
                .eq(ifCheck != null, ExamQuestion::getIfCheck, ifCheck)
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
        assertChoiceType(request.getQuestionType());
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
    public ExamQuestionUiResponse getUiDetail(Long questionId) {
        ExamQuestion q = examQuestionMapper.selectById(questionId);
        if (q == null) {
            return null;
        }
        ExamQuestionContent content = examQuestionContentMapper.selectById(questionId);
        JSONObject obj = null;
        if (content != null && StringUtils.hasText(content.getContentJson())) {
            try {
                obj = JSON.parseObject(content.getContentJson());
            } catch (Exception ignore) {
            }
        }
        ExamQuestionUiResponse resp = new ExamQuestionUiResponse();
        resp.setQuestionId(q.getQuestionId());
        resp.setMachineTypeId(q.getMachineTypeId());
        resp.setCategoryId(q.getCategoryId());
        resp.setQuestionType(q.getQuestionType());
        resp.setScore(q.getScore());
        resp.setDifficult(q.getDifficult());
        resp.setStatus(q.getStatus());
        resp.setSource(q.getSource());
        resp.setQuestionFrameId(q.getQuestionFrameId());
        resp.setIfCheck(q.getIfCheck());

        if (obj != null) {
            resp.setTitle(obj.getString("title"));
            resp.setAnalyze(obj.getString("analyze"));
            resp.setPath(obj.getString("path"));

            JSONArray items = obj.getJSONArray("questionItemFrames");
            if (items == null) {
                items = obj.getJSONArray("options");
            }
            if (items != null) {
                List<ExamQuestionUiItem> uiItems = items.stream()
                        .map(it -> it instanceof JSONObject jo ? jo : null)
                        .filter(Objects::nonNull)
                        .map(jo -> {
                            ExamQuestionUiItem ui = new ExamQuestionUiItem();
                            ui.setItemUuid(jo.getString("itemUuid"));
                            ui.setPrefix(jo.getString("prefix"));
                            if (!StringUtils.hasText(ui.getPrefix())) {
                                ui.setPrefix(jo.getString("label"));
                            }
                            ui.setContent(jo.getString("content"));
                            if (!StringUtils.hasText(ui.getContent())) {
                                ui.setContent(jo.getString("html"));
                            }
                            ui.setScore(jo.getInteger("score"));
                            return ui;
                        })
                        .toList();
                resp.setItems(uiItems);
            }

            Integer type = q.getQuestionType();
            String correctPrefix = obj.getString("correctPrefix");
            if (type != null) {
                if (type == 1 || type == 3) {
                    if (StringUtils.hasText(correctPrefix)) {
                        resp.setCorrect(correctPrefix);
                    } else {
                        resp.setCorrect(obj.getString("correct"));
                    }
                } else if (type == 2 || type == 6) {
                    if (StringUtils.hasText(correctPrefix)) {
                        resp.setCorrectArray(List.of(correctPrefix.split("\\s+")));
                    } else {
                        JSONArray arr = obj.getJSONArray("correctArray");
                        if (arr != null) {
                            resp.setCorrectArray(arr.stream().map(String::valueOf).toList());
                        }
                    }
                } else if (type == 5) {
                    resp.setCorrect(obj.getString("correct"));
                }
            }
        }
        return resp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdateUi(ExamQuestionUiRequest request) {
        assertChoiceType(request.getQuestionType());
        Long questionId = request.getQuestionId();
        boolean isNew = (questionId == null);
        if (isNew) {
            questionId = IdWorker.getId();
        }
        ExamQuestion existing = isNew ? null : examQuestionMapper.selectById(questionId);
        String frameId = StringUtils.hasText(request.getQuestionFrameId())
                ? request.getQuestionFrameId()
                : (existing != null ? existing.getQuestionFrameId() : null);
        if (!StringUtils.hasText(frameId)) {
            frameId = UUID.randomUUID().toString();
        }

        ExamQuestion entity = new ExamQuestion();
        BeanUtils.copyProperties(request, entity);
        entity.setQuestionId(questionId);
        entity.setQuestionFrameId(frameId);
        entity.setUpdateTime(new Date());
        if (isNew) {
            entity.setCreateTime(new Date());
            examQuestionMapper.insert(entity);
        } else {
            examQuestionMapper.updateById(entity);
        }

        String contentJson = buildContentJson(request, questionId, frameId);
        ExamQuestionContent content = examQuestionContentMapper.selectById(questionId);
        if (content == null) {
            content = new ExamQuestionContent();
            content.setQuestionId(questionId);
            content.setCreateTime(new Date());
            content.setContentJson(contentJson);
            examQuestionContentMapper.insert(content);
        } else {
            content.setContentJson(contentJson);
            content.setUpdateTime(new Date());
            examQuestionContentMapper.updateById(content);
        }
        return questionId;
    }

    private String buildContentJson(ExamQuestionUiRequest request, Long questionId, String frameId) {
        JSONObject obj = new JSONObject();
        obj.put("id", frameId);
        obj.put("questionId", questionId);
        obj.put("questionArchiveId", request.getCategoryId());
        obj.put("questionType", request.getQuestionType());
        obj.put("title", request.getTitle());
        obj.put("analyze", request.getAnalyze());
        obj.put("path", request.getPath());
        obj.put("difficult", request.getDifficult());
        obj.put("score", request.getScore());

        List<ExamQuestionUiItem> items = request.getItems();
        JSONArray itemArr = new JSONArray();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                ExamQuestionUiItem ui = items.get(i);
                if (ui == null) {
                    continue;
                }
                JSONObject it = new JSONObject();
                it.put("key", i);
                it.put("itemUuid", ui.getItemUuid());
                it.put("prefix", ui.getPrefix());
                it.put("content", ui.getContent());
                it.put("score", ui.getScore());
                itemArr.add(it);
            }
        }
        obj.put("questionItemFrames", itemArr);

        Integer type = request.getQuestionType();
        if (type != null) {
            if (type == 1) {
                String correct = request.getCorrect();
                if (StringUtils.hasText(correct) && items != null) {
                    for (int i = 0; i < items.size(); i++) {
                        ExamQuestionUiItem ui = items.get(i);
                        if (ui != null && Objects.equals(correct, ui.getPrefix())) {
                            obj.put("correctKey", i);
                            obj.put("correctPrefix", ui.getPrefix());
                            break;
                        }
                    }
                }
            } else if (type == 2 || type == 6) {
                List<String> correctArray = request.getCorrectArray();
                if (correctArray != null && items != null) {
                    JSONArray arr = new JSONArray();
                    List<String> prefixList = new java.util.ArrayList<>();
                    for (int i = 0; i < items.size(); i++) {
                        ExamQuestionUiItem ui = items.get(i);
                        if (ui == null) {
                            continue;
                        }
                        if (correctArray.contains(ui.getPrefix())) {
                            arr.add(i);
                            prefixList.add(ui.getPrefix());
                        }
                    }
                    obj.put("correctArrayKey", arr);
                    obj.put("correctPrefix", String.join(" ", prefixList));
                }
            } else if (type == 3) {
                String correct = request.getCorrect();
                if (StringUtils.hasText(correct) && items != null) {
                    for (int i = 0; i < items.size(); i++) {
                        ExamQuestionUiItem ui = items.get(i);
                        if (ui != null && Objects.equals(correct, ui.getPrefix())) {
                            obj.put("correctKey", i);
                            obj.put("correctPrefix", ui.getPrefix());
                            obj.put("correctContent", ui.getContent());
                            break;
                        }
                    }
                }
            } else if (type == 5) {
                obj.put("correct", request.getCorrect());
            }
        }
        return obj.toJSONString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQuestion(Long questionId) {
        examQuestionMapper.deleteById(questionId);
        examQuestionContentMapper.deleteById(questionId);
    }
}
