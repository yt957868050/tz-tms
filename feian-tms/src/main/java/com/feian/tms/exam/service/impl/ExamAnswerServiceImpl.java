package com.feian.tms.exam.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feian.tms.exam.domain.ExamAnswerItem;
import com.feian.tms.exam.domain.ExamAnswerSheet;
import com.feian.tms.exam.domain.ExamCandidate;
import com.feian.tms.exam.domain.ExamProctorEvent;
import com.feian.tms.exam.domain.ExamSession;
import com.feian.tms.exam.domain.ExamQuestion;
import com.feian.tms.exam.domain.ExamQuestionContent;
import com.feian.tms.common.PageRequest;
import com.feian.tms.exam.dto.ExamAnswerStartRequest;
import com.feian.tms.exam.dto.ExamAnswerStartResponse;
import com.feian.tms.exam.dto.ExamAnswerSubmitRequest;
import com.feian.tms.exam.dto.ExamManualScoreRequest;
import com.feian.tms.exam.dto.ExamPaperDetailResponse;
import com.feian.tms.exam.dto.ExamPaperSessionViewResponse;
import com.feian.tms.exam.mapper.ExamAnswerItemMapper;
import com.feian.tms.exam.mapper.ExamAnswerSheetMapper;
import com.feian.tms.exam.mapper.ExamCandidateMapper;
import com.feian.tms.exam.mapper.ExamProctorEventMapper;
import com.feian.tms.exam.mapper.ExamSessionMapper;
import com.feian.tms.exam.mapper.ExamQuestionContentMapper;
import com.feian.tms.exam.mapper.ExamQuestionMapper;
import com.feian.tms.exam.service.ExamAutoScoringService;
import com.feian.tms.exam.service.ExamAnswerService;
import com.feian.tms.exam.service.ExamPaperQueryService;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Map;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service
@RequiredArgsConstructor
public class ExamAnswerServiceImpl implements ExamAnswerService {

    private final ExamSessionMapper examSessionMapper;
    private final ExamCandidateMapper examCandidateMapper;
    private final ExamQuestionMapper examQuestionMapper;
    private final ExamQuestionContentMapper examQuestionContentMapper;
    private final ExamAnswerSheetMapper examAnswerSheetMapper;
    private final ExamAnswerItemMapper examAnswerItemMapper;
    private final ExamProctorEventMapper examProctorEventMapper;
    private final ExamAutoScoringService examAutoScoringService;
    private final ExamPaperQueryService examPaperQueryService;

    private SysUser currentUser() {
        return SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
    }

    private Long currentUserId() {
        return SecurityUtils.getUserId();
    }

    private boolean isAdmin(SysUser user) {
        return user != null && user.isAdmin();
    }

    @Override
    public Page<ExamAnswerSheet> pageQuery(PageRequest<ExamAnswerSheet> pageRequest) {
        PageRequest<ExamAnswerSheet> req = pageRequest != null ? pageRequest : new PageRequest<>();
        Page<ExamAnswerSheet> page = new Page<>(req.getPageNum(), req.getPageSize());
        ExamAnswerSheet query = req.getQuery();
        Long sessionId = query != null ? query.getSessionId() : null;
        Long userId = query != null ? query.getUserId() : null;
        Integer passFlag = query != null ? query.getPassFlag() : null;
        Integer status = query != null ? query.getStatus() : null;
        SysUser user = currentUser();
        if (!isAdmin(user)) {
            Long me = currentUserId();
            if (me == null) {
                throw new IllegalArgumentException("未登录");
            }
            if (userId != null && !me.equals(userId)) {
                throw new IllegalArgumentException("无权查询他人答卷");
            }
            userId = me;
        }
        return examAnswerSheetMapper.selectPage(page, new LambdaQueryWrapper<ExamAnswerSheet>()
                .eq(sessionId != null, ExamAnswerSheet::getSessionId, sessionId)
                .eq(userId != null, ExamAnswerSheet::getUserId, userId)
                .eq(passFlag != null, ExamAnswerSheet::getPassFlag, passFlag)
                .eq(status != null, ExamAnswerSheet::getStatus, status)
                .orderByDesc(ExamAnswerSheet::getCreateTime));
    }

    @Override
    public Map<String, Object> stats(ExamAnswerSheet query) {
        Long sessionId = query != null ? query.getSessionId() : null;
        Long userId = query != null ? query.getUserId() : null;
        Integer passFlag = query != null ? query.getPassFlag() : null;
        Integer status = query != null ? query.getStatus() : null;
        SysUser user = currentUser();
        if (!isAdmin(user)) {
            Long me = currentUserId();
            if (me == null) {
                throw new IllegalArgumentException("未登录");
            }
            if (userId != null && !me.equals(userId)) {
                throw new IllegalArgumentException("无权查询他人答卷");
            }
            userId = me;
        }
        List<ExamAnswerSheet> list = examAnswerSheetMapper.selectList(new LambdaQueryWrapper<ExamAnswerSheet>()
                .eq(sessionId != null, ExamAnswerSheet::getSessionId, sessionId)
                .eq(userId != null, ExamAnswerSheet::getUserId, userId)
                .eq(passFlag != null, ExamAnswerSheet::getPassFlag, passFlag)
                .eq(status != null, ExamAnswerSheet::getStatus, status));
        int total = list.size();
        long pass = list.stream().filter(s -> Objects.equals(s.getPassFlag(), 1)).count();
        int totalScore = list.stream().map(s -> Objects.requireNonNullElse(s.getScoreSystem(), 0)).reduce(0, Integer::sum);
        int maxScore = list.stream().map(s -> Objects.requireNonNullElse(s.getScoreSystem(), 0)).reduce(0, Integer::max);
        int minScore = list.stream().map(s -> Objects.requireNonNullElse(s.getScoreSystem(), 0)).reduce(Integer.MAX_VALUE, Integer::min);
        if (list.isEmpty()) {
            minScore = 0;
        }
        Map<String, Object> result = new java.util.HashMap<>();
        result.put("total", total);
        result.put("pass", pass);
        result.put("fail", total - pass);
        result.put("avgScore", total == 0 ? 0 : (double) totalScore / total);
        result.put("maxScore", maxScore);
        result.put("minScore", minScore);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamAnswerSheet startExam(ExamAnswerStartRequest request) {
        SysUser user = currentUser();
        Long me = currentUserId();
        Long requestedUserId = request.getUserId() != null ? request.getUserId() : me;
        if (requestedUserId == null) {
            throw new IllegalArgumentException("未登录");
        }
        if (!isAdmin(user) && me != null && !me.equals(requestedUserId)) {
            throw new IllegalArgumentException("无权代他人开考");
        }
        ExamSession session = examSessionMapper.selectById(request.getSessionId());
        if (session == null) {
            throw new IllegalArgumentException("场次不存在");
        }
        if (!isAdmin(user)) {
            Long currentMachineTypeId = user != null ? user.getCurrentMachineTypeId() : null;
            if (currentMachineTypeId == null) {
                throw new IllegalArgumentException("请先选择机型");
            }
            if (session.getMachineTypeId() != null && !currentMachineTypeId.equals(session.getMachineTypeId())) {
                throw new IllegalArgumentException("当前机型与考试场次不匹配，请切换机型后再进入");
            }
        }
        ExamCandidate candidate = examCandidateMapper.selectOne(new LambdaQueryWrapper<ExamCandidate>()
                .eq(ExamCandidate::getSessionId, request.getSessionId())
                .eq(ExamCandidate::getUserId, requestedUserId)
                .last("limit 1"));
        if (candidate == null) {
            throw new IllegalArgumentException("考生不在该场次名单中");
        }
        Date now = new Date();
        Date start = candidate.getWindowStartOverride() != null ? candidate.getWindowStartOverride() : session.getWindowStart();
        Date end = candidate.getWindowEndOverride() != null ? candidate.getWindowEndOverride() : session.getWindowEnd();
        if (start != null && now.before(start)) {
            throw new IllegalArgumentException("考试未开始");
        }
        if (end != null && now.after(end)) {
            throw new IllegalArgumentException("考试已结束");
        }
        Long paperId = session.getPaperId();
        if (request.getPaperId() != null) {
            paperId = request.getPaperId();
        }
        ExamAnswerSheet sheet = new ExamAnswerSheet();
        sheet.setSessionId(session.getSessionId());
        sheet.setPaperId(paperId);
        sheet.setUserId(requestedUserId);
        sheet.setStatus(1); // 待提交/待批改
        sheet.setCreateTime(new Date());
        if (candidate.getMakeupFromAnswerId() != null) {
            sheet.setRemark("makeupFrom=" + candidate.getMakeupFromAnswerId());
        }
        examAnswerSheetMapper.insert(sheet);
        return sheet;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamAnswerStartResponse startExamWithPaper(ExamAnswerStartRequest request) {
        ExamAnswerSheet sheet = startExam(request);
        ExamPaperSessionViewResponse view = examPaperQueryService.loadPaperForSession(request.getSessionId());
        if (view != null && view.getItems() != null) {
            // 将乱序映射存入 rawAnswerJson，便于回放
            Map<String, Object> meta = Map.of(
                    "questionOrder", view.getItems().stream().map(ExamPaperDetailResponse.Item::getQuestionId).toList(),
                    "optionOrders", view.getItems().stream()
                            .collect(Collectors.toMap(
                                    ExamPaperDetailResponse.Item::getQuestionId,
                                    ExamPaperDetailResponse.Item::getOptionOrder
                            ))
            );
            sheet.setRawAnswerJson(JSON.toJSONString(meta));
            examAnswerSheetMapper.updateById(sheet);
        }
        ExamAnswerStartResponse resp = new ExamAnswerStartResponse();
        resp.setSheet(sheet);
        resp.setPaperView(view);
        return resp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamAnswerSheet submit(ExamAnswerSubmitRequest request) {
        SysUser user = currentUser();
        Long me = currentUserId();
        ExamAnswerSheet sheet = examAnswerSheetMapper.selectById(request.getSheetId());
        if (sheet == null) {
            throw new IllegalArgumentException("答卷不存在");
        }
        if (!isAdmin(user)) {
            if (me == null) {
                throw new IllegalArgumentException("未登录");
            }
            if (!me.equals(sheet.getUserId())) {
                throw new IllegalArgumentException("无权提交他人答卷");
            }
        }
        // 批量拉取题目分值与标准答案，便于自动判分
        List<Long> questionIds = CollectionUtils.isEmpty(request.getAnswers()) ? List.of() :
                request.getAnswers().stream().map(ExamAnswerSubmitRequest.AnswerItem::getQuestionId).toList();
        Map<Long, ExamQuestion> questionMap = questionIds.isEmpty() ? Map.of()
                : examQuestionMapper.selectBatchIds(questionIds).stream()
                    .collect(Collectors.toMap(ExamQuestion::getQuestionId, q -> q));
        Map<Long, ExamQuestionContent> contentMap = questionIds.isEmpty() ? Map.of()
                : examQuestionContentMapper.selectBatchIds(questionIds).stream()
                    .collect(Collectors.toMap(ExamQuestionContent::getQuestionId, c -> c));
        examAnswerItemMapper.delete(new LambdaQueryWrapper<ExamAnswerItem>()
                .eq(ExamAnswerItem::getSheetId, request.getSheetId()));

        int totalScore = 0;
        int correctCount = 0;
        if (!CollectionUtils.isEmpty(request.getAnswers())) {
            for (ExamAnswerSubmitRequest.AnswerItem itemDto : request.getAnswers()) {
                ExamAnswerItem item = new ExamAnswerItem();
                item.setSheetId(sheet.getSheetId());
                item.setQuestionId(itemDto.getQuestionId());
                item.setAnswerJson(itemDto.getAnswerJson());
                // 取题目默认分值作为基准
                Integer defaultScore = null;
                ExamQuestion q = questionMap.get(itemDto.getQuestionId());
                if (q != null) {
                    defaultScore = q.getScore();
                }
                ExamQuestionContent content = contentMap.get(itemDto.getQuestionId());
                ExamAutoScoringService.ScoreResult sr = examAutoScoringService.autoScore(
                        content != null ? content.getContentJson() : null,
                        itemDto.getAnswerJson(),
                        Objects.requireNonNullElse(itemDto.getScore(), defaultScore)
                );
                Integer finalScore = sr != null ? sr.getScore() : Objects.requireNonNullElse(itemDto.getScore(),
                        Objects.requireNonNullElse(defaultScore, 0));
                Integer finalCorrect = sr != null ? sr.getCorrectFlag()
                        : (itemDto.getCorrectFlag() != null ? itemDto.getCorrectFlag() : 0);

                item.setScore(finalScore);
                item.setCorrectFlag(finalCorrect);
                item.setCreateTime(new Date());
                examAnswerItemMapper.insert(item);
                totalScore += Objects.requireNonNullElse(finalScore, 0);
                if (Objects.equals(finalCorrect, 1)) {
                    correctCount++;
                }
            }
        }

        sheet.setScoreSystem(totalScore);
        sheet.setScoreUser(request.getScoreUser());
        sheet.setCorrectCount(correctCount);
        sheet.setDurationSec(request.getDurationSec());
        sheet.setStatus(2); // 完成

        ExamSession session = examSessionMapper.selectById(sheet.getSessionId());
        if (session != null) {
            boolean pass = false;
            if ("score".equalsIgnoreCase(session.getPassRuleType()) && session.getPassScore() != null) {
                pass = totalScore >= session.getPassScore();
            } else if ("question_num".equalsIgnoreCase(session.getPassRuleType()) && session.getPassQuestionNum() != null) {
                pass = correctCount >= session.getPassQuestionNum();
            } else if (session.getPassScore() != null) {
                pass = totalScore >= session.getPassScore();
            }
            sheet.setPassFlag(pass ? 1 : 0);
        }
        sheet.setUpdateTime(new Date());
        examAnswerSheetMapper.updateById(sheet);
        return sheet;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamAnswerSheet manualScore(ExamManualScoreRequest request) {
        SysUser user = currentUser();
        if (!isAdmin(user)) {
            throw new IllegalArgumentException("无权限批改答卷");
        }
        ExamAnswerSheet sheet = examAnswerSheetMapper.selectById(request.getSheetId());
        if (sheet == null) {
            throw new IllegalArgumentException("答卷不存在");
        }
        if (!CollectionUtils.isEmpty(request.getItems())) {
            for (ExamManualScoreRequest.ItemScore dto : request.getItems()) {
                ExamAnswerItem item = examAnswerItemMapper.selectOne(new LambdaQueryWrapper<ExamAnswerItem>()
                        .eq(ExamAnswerItem::getSheetId, sheet.getSheetId())
                        .eq(ExamAnswerItem::getQuestionId, dto.getQuestionId())
                        .last("limit 1"));
                if (item != null) {
                    item.setScore(dto.getScore());
                    item.setCorrectFlag(dto.getCorrectFlag());
                    item.setUpdateTime(new Date());
                    examAnswerItemMapper.updateById(item);
                }
            }
        }
        // 重新汇总
        List<ExamAnswerItem> items = examAnswerItemMapper.selectList(new LambdaQueryWrapper<ExamAnswerItem>()
                .eq(ExamAnswerItem::getSheetId, sheet.getSheetId()));
        int totalScore = items.stream().map(i -> Objects.requireNonNullElse(i.getScore(), 0)).reduce(0, Integer::sum);
        int correctCount = (int) items.stream().filter(i -> Objects.equals(i.getCorrectFlag(), 1)).count();
        sheet.setScoreUser(totalScore);
        if (request.getPassFlag() != null) {
            sheet.setPassFlag(Boolean.TRUE.equals(request.getPassFlag()) ? 1 : 0);
        }
        sheet.setCorrectCount(correctCount);
        sheet.setUpdateTime(new Date());
        examAnswerSheetMapper.updateById(sheet);
        return sheet;
    }

    @Override
    public void recordProctorEvent(ExamProctorEvent event) {
        SysUser user = currentUser();
        Long me = currentUserId();
        if (!isAdmin(user)) {
            if (me == null) {
                throw new IllegalArgumentException("未登录");
            }
            ExamAnswerSheet sheet = examAnswerSheetMapper.selectById(event.getSheetId());
            if (sheet == null) {
                throw new IllegalArgumentException("答卷不存在");
            }
            if (!me.equals(sheet.getUserId())) {
                throw new IllegalArgumentException("无权上报他人监考事件");
            }
        }
        event.setCreateTime(new Date());
        examProctorEventMapper.insert(event);
        // 如果是违规事件，直接标记答卷为不通过并记录 remark
        if (event.getEventType() != null) {
            String type = event.getEventType().toLowerCase();
            if (type.contains("cheat") || type.contains("switch") || type.contains("capture")) {
                ExamAnswerSheet sheet = examAnswerSheetMapper.selectById(event.getSheetId());
                if (sheet != null) {
                    sheet.setPassFlag(0);
                    String remark = sheet.getRemark();
                    String addition = "proctor:" + event.getEventType();
                    if (StringUtils.hasText(remark)) {
                        remark = remark + "; " + addition;
                    } else {
                        remark = addition;
                    }
                    sheet.setRemark(remark);
                    sheet.setUpdateTime(new Date());
                    examAnswerSheetMapper.updateById(sheet);
                }
            }
        }
    }

    @Override
    public List<ExamProctorEvent> listProctorEvents(Long sheetId) {
        return examProctorEventMapper.selectList(new LambdaQueryWrapper<ExamProctorEvent>()
                .eq(ExamProctorEvent::getSheetId, sheetId)
                .orderByAsc(ExamProctorEvent::getCreateTime));
    }
}
