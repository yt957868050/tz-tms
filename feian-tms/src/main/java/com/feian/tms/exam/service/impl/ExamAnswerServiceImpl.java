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
import com.feian.tms.exam.dto.ExamPaperSnapshot;
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
import com.feian.common.core.context.MachineTypeContextHolder;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Map;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Random;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONArray;

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
            Integer publishStatus = session.getPublishStatus();
            if (publishStatus != null && publishStatus != 2) {
                throw new IllegalArgumentException("考试未发布");
            }
            if (StringUtils.hasText(session.getPassword())) {
                String pwd = request.getPassword();
                if (!StringUtils.hasText(pwd) || !session.getPassword().equals(pwd)) {
                    throw new IllegalArgumentException("考试密码错误");
                }
            }
            Long currentMachineTypeId = MachineTypeContextHolder.requireMachineTypeId();
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

        // 进行中答卷：默认续考（避免刷新/重复点击产生多份答卷）
        // 但当已为该考生开放了新的补考窗口（windowStartOverride）时，
        // 旧窗口中遗留的“未交卷答卷”应视为历史记录，不应被继续复用。
        ExamAnswerSheet inProgress = examAnswerSheetMapper.selectOne(new LambdaQueryWrapper<ExamAnswerSheet>()
                .eq(ExamAnswerSheet::getSessionId, session.getSessionId())
                .eq(ExamAnswerSheet::getUserId, requestedUserId)
                .eq(ExamAnswerSheet::getStatus, 1)
                .orderByDesc(ExamAnswerSheet::getCreateTime)
                .last("limit 1"));
        if (inProgress != null) {
            if (start != null && inProgress.getCreateTime() != null && inProgress.getCreateTime().before(start)) {
                // ignore stale in-progress sheet from previous window
            } else {
                return inProgress;
            }
        }

        // 已完成答卷：默认不允许重复参加；补考则按 makeupFromAnswerId 控制
        if (candidate.getMakeupFromAnswerId() == null) {
            Long finishedCount = examAnswerSheetMapper.selectCount(new LambdaQueryWrapper<ExamAnswerSheet>()
                    .eq(ExamAnswerSheet::getSessionId, session.getSessionId())
                    .eq(ExamAnswerSheet::getUserId, requestedUserId)
                    .eq(ExamAnswerSheet::getStatus, 2));
            if (finishedCount != null && finishedCount > 0) {
                throw new IllegalArgumentException("已完成该场次考试，不能重复开考");
            }
        } else {
            String makeupTag = "makeupFrom=" + candidate.getMakeupFromAnswerId();
            ExamAnswerSheet existingMakeup = examAnswerSheetMapper.selectOne(new LambdaQueryWrapper<ExamAnswerSheet>()
                    .eq(ExamAnswerSheet::getSessionId, session.getSessionId())
                    .eq(ExamAnswerSheet::getUserId, requestedUserId)
                    .like(ExamAnswerSheet::getRemark, makeupTag)
                    .orderByDesc(ExamAnswerSheet::getCreateTime)
                    .last("limit 1"));
            if (existingMakeup != null) {
                if (Objects.equals(existingMakeup.getStatus(), 1)) {
                    return existingMakeup;
                }
                throw new IllegalArgumentException("补考已完成，不能重复开考");
            }
        }

        Long paperId = session.getPaperId();
        if (request.getPaperId() != null) {
            paperId = request.getPaperId();
        }
        ExamAnswerSheet sheet = new ExamAnswerSheet();
        sheet.setSessionId(session.getSessionId());
        sheet.setPaperId(paperId);
        sheet.setUserId(requestedUserId);
        sheet.setStatus(1); // 进行中/未交卷
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
        ExamSession session = examSessionMapper.selectById(sheet.getSessionId());
        ExamPaperSnapshot snapshot = ensurePaperSnapshot(sheet, session);
        ExamPaperSessionViewResponse view = snapshotToView(snapshot);

        ExamAnswerStartResponse resp = new ExamAnswerStartResponse();
        resp.setSheet(sheet);
        resp.setPaperView(view);
        // 计时/作弊统计（替代旧系统 redis PaperSession）
        fillTimingAndCheat(resp, sheet, session);
        return resp;
    }

    private void fillTimingAndCheat(ExamAnswerStartResponse resp, ExamAnswerSheet sheet, ExamSession session) {
        if (resp == null || sheet == null || session == null) {
            return;
        }
        Date now = new Date();
        Integer totalTimeSec = session.getSuggestTimeMin() != null && session.getSuggestTimeMin() > 0
                ? session.getSuggestTimeMin() * 60
                : null;

        Integer remain = null;
        if (totalTimeSec != null && sheet.getCreateTime() != null) {
            long used = Math.max(0, (now.getTime() - sheet.getCreateTime().getTime()) / 1000);
            remain = (int) Math.max(0, totalTimeSec - used);
        }
        // 场次窗口（含考生覆盖）
        ExamCandidate candidate = examCandidateMapper.selectOne(new LambdaQueryWrapper<ExamCandidate>()
                .eq(ExamCandidate::getSessionId, sheet.getSessionId())
                .eq(ExamCandidate::getUserId, sheet.getUserId())
                .last("limit 1"));
        Date end = candidate != null && candidate.getWindowEndOverride() != null ? candidate.getWindowEndOverride() : session.getWindowEnd();
        if (end != null) {
            int byWindow = (int) Math.max(0, (end.getTime() - now.getTime()) / 1000);
            remain = remain == null ? byWindow : Math.min(remain, byWindow);
        }

        // 作弊次数（切屏/离开）
        Long cheatCount = examProctorEventMapper.selectCount(new LambdaQueryWrapper<ExamProctorEvent>()
                .eq(ExamProctorEvent::getSheetId, sheet.getSheetId())
                .and(w -> w.like(ExamProctorEvent::getEventType, "switch").or().like(ExamProctorEvent::getEventType, "cheat")));

        resp.setTotalTimeSec(totalTimeSec);
        resp.setRemainTimeSec(remain);
        resp.setCheatCount(cheatCount != null ? cheatCount.intValue() : 0);
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
        // 快照优先：用于回放/判分一致性（题目可能被后续编辑）
        Map<Long, String> snapshotContent = loadSnapshotContent(sheet.getRawAnswerJson());

        // 批量拉取题目分值与标准答案，便于自动判分（作为兜底）
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
                String contentJson = snapshotContent.get(itemDto.getQuestionId());
                if (contentJson == null) {
                    ExamQuestionContent content = contentMap.get(itemDto.getQuestionId());
                    contentJson = content != null ? content.getContentJson() : null;
                }
                ExamAutoScoringService.ScoreResult sr = examAutoScoringService.autoScore(
                        contentJson,
                        itemDto.getAnswerJson(),
                        Objects.requireNonNullElse(itemDto.getScore(), defaultScore),
                        q != null ? q.getQuestionType() : null
                );
                Integer finalScore;
                Integer finalCorrect;
                if (sr != null) {
                    finalScore = sr.getScore();
                    finalCorrect = sr.getCorrectFlag();
                } else {
                    // 理论上不会进入：自动判分服务对无法解析场景会返回 0 分/错误
                    finalScore = 0;
                    finalCorrect = 0;
                }

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
        sheet.setStatus(2); // 提交即自动判分完成

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

    private ExamPaperSessionViewResponse snapshotToView(ExamPaperSnapshot snapshot) {
        ExamPaperSessionViewResponse view = new ExamPaperSessionViewResponse();
        if (snapshot == null) {
            return view;
        }
        view.setItems(snapshot.getItems());
        view.setShuffleQuestion(Boolean.TRUE.equals(snapshot.getShuffleQuestion()));
        view.setShuffleOption(Boolean.TRUE.equals(snapshot.getShuffleOption()));
        view.setAntiCheatCheat(Boolean.TRUE.equals(snapshot.getAntiCheatCheat()));
        view.setAntiCheatWatch(Boolean.TRUE.equals(snapshot.getAntiCheatWatch()));
        view.setAntiCheatCapture(Boolean.TRUE.equals(snapshot.getAntiCheatCapture()));
        return view;
    }

    /**
     * 确保答卷已固化试卷快照（含题序/选项序/题目内容）。
     * 兼容旧格式：rawAnswerJson 中仅存 questionOrder/optionOrders 的情况。
     */
    private ExamPaperSnapshot ensurePaperSnapshot(ExamAnswerSheet sheet, ExamSession session) {
        if (sheet == null) {
            throw new IllegalArgumentException("答卷不存在");
        }
        if (session == null) {
            throw new IllegalArgumentException("场次不存在");
        }

        ExamPaperSnapshot existing = parseSnapshot(sheet.getRawAnswerJson());
        if (existing != null && existing.getItems() != null && !existing.getItems().isEmpty()) {
            return existing;
        }

        JSONObject meta = parseJson(sheet.getRawAnswerJson());
        List<Long> questionOrder = readQuestionOrder(meta);
        Map<Long, List<Integer>> optionOrders = readOptionOrders(meta);

        // 使用 sheet.paperId（允许 start 时指定 paperId 覆盖场次默认）
        ExamPaperSnapshot snapshot = buildSnapshot(session, sheet.getPaperId(), sheet.getSheetId(), questionOrder, optionOrders);
        sheet.setRawAnswerJson(JSON.toJSONString(snapshot));
        examAnswerSheetMapper.updateById(sheet);
        return snapshot;
    }

    private ExamPaperSnapshot parseSnapshot(String rawAnswerJson) {
        if (!StringUtils.hasText(rawAnswerJson)) {
            return null;
        }
        try {
            ExamPaperSnapshot snapshot = JSON.parseObject(rawAnswerJson, ExamPaperSnapshot.class);
            if (snapshot != null && snapshot.getSnapshotVersion() != null) {
                return snapshot;
            }
        } catch (Exception ignore) {
        }
        return null;
    }

    private JSONObject parseJson(String rawAnswerJson) {
        if (!StringUtils.hasText(rawAnswerJson)) {
            return null;
        }
        try {
            return JSON.parseObject(rawAnswerJson);
        } catch (Exception e) {
            return null;
        }
    }

    private List<Long> readQuestionOrder(JSONObject obj) {
        if (obj == null) return null;
        try {
            JSONArray arr = obj.getJSONArray("questionOrder");
            if (arr == null || arr.isEmpty()) return null;
            List<Long> ids = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                Long v = arr.getLong(i);
                if (v != null) ids.add(v);
            }
            return ids.isEmpty() ? null : ids;
        } catch (Exception ignore) {
            return null;
        }
    }

    private Map<Long, List<Integer>> readOptionOrders(JSONObject obj) {
        if (obj == null) return Map.of();
        Object oo = obj.get("optionOrders");
        if (!(oo instanceof JSONObject optionObj)) {
            return Map.of();
        }
        Map<Long, List<Integer>> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> e : optionObj.entrySet()) {
            Long qid;
            try {
                qid = Long.valueOf(e.getKey());
            } catch (Exception ignore) {
                continue;
            }
            if (!(e.getValue() instanceof JSONArray arr)) {
                continue;
            }
            List<Integer> idx = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                Integer v = arr.getInteger(i);
                if (v != null) idx.add(v);
            }
            if (!idx.isEmpty()) {
                result.put(qid, idx);
            }
        }
        return result;
    }

    private ExamPaperSnapshot buildSnapshot(ExamSession session,
                                           Long paperId,
                                           Long sheetId,
                                           List<Long> questionOrder,
                                           Map<Long, List<Integer>> optionOrders) {
        if (paperId == null) {
            throw new IllegalArgumentException("试卷ID不能为空");
        }
        ExamPaperDetailResponse detail = examPaperQueryService.loadPaper(paperId);
        if (detail == null || CollectionUtils.isEmpty(detail.getItems())) {
            throw new IllegalArgumentException("试卷不存在或无题目");
        }
        List<ExamPaperDetailResponse.Item> items = new ArrayList<>(detail.getItems());

        // 题序：优先按历史 meta 回放；否则按场次配置生成一次乱序
        if (!CollectionUtils.isEmpty(questionOrder)) {
            Map<Long, ExamPaperDetailResponse.Item> byId = items.stream()
                    .filter(i -> i.getQuestionId() != null)
                    .collect(Collectors.toMap(ExamPaperDetailResponse.Item::getQuestionId, i -> i, (a, b) -> a));
            List<ExamPaperDetailResponse.Item> ordered = new ArrayList<>();
            for (Long qid : questionOrder) {
                ExamPaperDetailResponse.Item it = byId.remove(qid);
                if (it != null) ordered.add(it);
            }
            ordered.addAll(byId.values());
            items = ordered;
        } else if (session.getShuffleQuestion() != null && session.getShuffleQuestion() == 1) {
            Random r = new Random(Objects.requireNonNullElse(sheetId, System.nanoTime()));
            java.util.Collections.shuffle(items, r);
        }
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setOrd(i + 1);
        }

        // 选项：优先按历史 meta 回放；否则按场次配置生成一次乱序
        boolean shuffleOption = session.getShuffleOption() != null && session.getShuffleOption() == 1;
        Random optionRandom = new Random(Objects.requireNonNullElse(sheetId, System.nanoTime()) ^ 0x9E3779B97F4A7C15L);
        List<ExamPaperDetailResponse.Item> finalItems = new ArrayList<>(items.size());
        for (ExamPaperDetailResponse.Item item : items) {
            if (item == null) continue;
            List<Integer> fixedOrder = optionOrders != null ? optionOrders.get(item.getQuestionId()) : null;
            if (!CollectionUtils.isEmpty(fixedOrder)) {
                item.setContentJson(applyOptionOrder(item.getContentJson(), fixedOrder));
                item.setOptionOrder(fixedOrder);
                finalItems.add(item);
                continue;
            }
            if (shuffleOption) {
                shuffleOptionsInPlace(item, optionRandom);
            }
            finalItems.add(item);
        }

        ExamPaperSnapshot snapshot = new ExamPaperSnapshot();
        snapshot.setSnapshotVersion(1);
        snapshot.setSessionId(session.getSessionId());
        snapshot.setPaperId(paperId);
        snapshot.setShuffleQuestion(session.getShuffleQuestion() != null && session.getShuffleQuestion() == 1);
        snapshot.setShuffleOption(session.getShuffleOption() != null && session.getShuffleOption() == 1);
        snapshot.setAntiCheatCheat(session.getAntiCheatCheat() != null && session.getAntiCheatCheat() == 1);
        snapshot.setAntiCheatWatch(session.getAntiCheatWatch() != null && session.getAntiCheatWatch() == 1);
        snapshot.setAntiCheatCapture(session.getAntiCheatCapture() != null && session.getAntiCheatCapture() == 1);
        snapshot.setSnapshotTime(new Date());
        snapshot.setItems(finalItems);
        return snapshot;
    }

    private Map<Long, String> loadSnapshotContent(String rawAnswerJson) {
        ExamPaperSnapshot snapshot = parseSnapshot(rawAnswerJson);
        if (snapshot == null || CollectionUtils.isEmpty(snapshot.getItems())) {
            return Map.of();
        }
        Map<Long, String> map = new HashMap<>();
        for (ExamPaperDetailResponse.Item it : snapshot.getItems()) {
            if (it == null || it.getQuestionId() == null) continue;
            if (StringUtils.hasText(it.getContentJson())) {
                map.put(it.getQuestionId(), it.getContentJson());
            }
        }
        return map;
    }

    private void shuffleOptionsInPlace(ExamPaperDetailResponse.Item item, Random random) {
        if (item == null || !StringUtils.hasText(item.getContentJson())) {
            return;
        }
        try {
            JSONObject obj = JSON.parseObject(item.getContentJson());
            String field = obj.containsKey("options") ? "options"
                    : (obj.containsKey("questionItemFrames") ? "questionItemFrames" : null);
            if (field == null) return;
            JSONArray options = obj.getJSONArray(field);
            if (options == null || options.isEmpty()) return;
            List<Integer> idx = new ArrayList<>();
            for (int i = 0; i < options.size(); i++) idx.add(i);
            java.util.Collections.shuffle(idx, random);
            JSONArray shuffled = new JSONArray();
            for (Integer i : idx) shuffled.add(options.get(i));
            obj.put(field, shuffled);
            item.setContentJson(obj.toJSONString());
            item.setOptionOrder(idx);
        } catch (Exception ignore) {
        }
    }

    private String applyOptionOrder(String contentJson, List<Integer> idxOrder) {
        if (!StringUtils.hasText(contentJson) || CollectionUtils.isEmpty(idxOrder)) {
            return contentJson;
        }
        try {
            JSONObject obj = JSON.parseObject(contentJson);
            String field = obj.containsKey("options") ? "options"
                    : (obj.containsKey("questionItemFrames") ? "questionItemFrames" : null);
            if (field == null) return contentJson;
            JSONArray options = obj.getJSONArray(field);
            if (options == null || options.isEmpty()) return contentJson;
            JSONArray reordered = new JSONArray();
            for (Integer i : idxOrder) {
                if (i == null || i < 0 || i >= options.size()) continue;
                reordered.add(options.get(i));
            }
            if (reordered.size() != options.size()) {
                // 异常时回退原始，避免丢选项
                return contentJson;
            }
            obj.put(field, reordered);
            return obj.toJSONString();
        } catch (Exception e) {
            return contentJson;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamAnswerSheet manualScore(ExamManualScoreRequest request) {
        throw new IllegalArgumentException("当前考试仅支持选择题自动判分，无需人工批改");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamPaperSessionViewResponse loadPaperViewForSheet(Long sheetId) {
        if (sheetId == null) {
            throw new IllegalArgumentException("答卷ID不能为空");
        }
        ExamAnswerSheet sheet = examAnswerSheetMapper.selectById(sheetId);
        if (sheet == null) {
            throw new IllegalArgumentException("答卷不存在");
        }
        ExamSession session = examSessionMapper.selectById(sheet.getSessionId());
        if (session == null) {
            throw new IllegalArgumentException("场次不存在");
        }
        ExamPaperSnapshot snapshot = ensurePaperSnapshot(sheet, session);
        return snapshotToView(snapshot);
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
