package com.feian.tms.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.exam.domain.ExamAnswerSheet;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.exam.domain.ExamCandidate;
import com.feian.tms.exam.dto.ExamCandidateMakeupRequest;
import com.feian.tms.exam.dto.ExamCandidateMakeupEligibleRequest;
import com.feian.tms.exam.dto.ExamCandidateMakeupOpenRequest;
import com.feian.tms.exam.dto.ExamCandidateResultResponse;
import com.feian.tms.exam.domain.ExamSession;
import com.feian.tms.exam.mapper.ExamAnswerSheetMapper;
import com.feian.tms.exam.service.ExamCandidateService;
import com.feian.tms.exam.service.ExamSessionService;
import com.feian.tms.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 考生管理
 */
@RestController
@RequestMapping("/api/exam/candidate")
@Tag(name = "考试-考生")
@RequiredArgsConstructor
public class ExamCandidateController {

    private final ExamCandidateService examCandidateService;
    private final ExamAnswerSheetMapper examAnswerSheetMapper;
    private final ExamSessionService examSessionService;
    private final StudentService studentService;

    @PostMapping("/list")
    @Operation(summary = "考生列表（可按场次/用户查询）")
    public R<Page<ExamCandidate>> list(@RequestBody PageRequest<ExamCandidate> pageRequest) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限访问考生列表");
        }
        PageRequest<ExamCandidate> req = pageRequest != null ? pageRequest : new PageRequest<>();
        Page<ExamCandidate> page = new Page<>(req.getPageNum(), req.getPageSize());
        ExamCandidate query = req.getQuery();
        Long sessionId = query != null ? query.getSessionId() : null;
        Long userId = query != null ? query.getUserId() : null;
        Long classId = query != null ? query.getClassId() : null;
        var wrapper = examCandidateService.lambdaQuery()
                .eq(sessionId != null, ExamCandidate::getSessionId, sessionId)
                .eq(userId != null, ExamCandidate::getUserId, userId)
                .eq(classId != null, ExamCandidate::getClassId, classId)
                .orderByDesc(ExamCandidate::getCreateTime);
        Page<ExamCandidate> result = wrapper.page(page);
        return R.success(result);
    }

    @PostMapping("/resultList")
    @Operation(summary = "考生结果列表（按场次计算）", description = "为管理端提供：通过/未通过/缺考/未交卷 等结果标签")
    public R<Page<ExamCandidateResultResponse>> resultList(@RequestBody PageRequest<ExamCandidate> pageRequest) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限访问考生结果列表");
        }
        PageRequest<ExamCandidate> req = pageRequest != null ? pageRequest : new PageRequest<>();
        Page<ExamCandidate> page = new Page<>(req.getPageNum(), req.getPageSize());
        ExamCandidate query = req.getQuery();
        Long sessionId = query != null ? query.getSessionId() : null;
        if (sessionId == null) {
            return R.fail("场次ID不能为空");
        }
        ExamSession session = examSessionService.getById(sessionId);
        if (session == null) {
            return R.fail("场次不存在");
        }
        Long userId = query != null ? query.getUserId() : null;
        Long classId = query != null ? query.getClassId() : null;
        var wrapper = examCandidateService.lambdaQuery()
                .eq(ExamCandidate::getSessionId, sessionId)
                .eq(userId != null, ExamCandidate::getUserId, userId)
                .eq(classId != null, ExamCandidate::getClassId, classId)
                .orderByDesc(ExamCandidate::getCreateTime);
        Page<ExamCandidate> candidatePage = wrapper.page(page);
        List<ExamCandidate> candidates = candidatePage.getRecords() != null ? candidatePage.getRecords() : List.of();
        List<ExamCandidateResultResponse> records = buildResultRecords(session, candidates);

        Page<ExamCandidateResultResponse> respPage = new Page<>(candidatePage.getCurrent(), candidatePage.getSize(), candidatePage.getTotal());
        respPage.setRecords(records);
        return R.success(respPage);
    }

    @PostMapping("/makeupEligible")
    @Operation(summary = "可补考考生列表", description = "返回某场次中 未通过/缺考/未交卷 的人员（用于手动下发补考）")
    public R<List<ExamCandidateResultResponse>> makeupEligible(@Valid @RequestBody ExamCandidateMakeupEligibleRequest request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限访问补考名单");
        }
        ExamSession session = examSessionService.getById(request.getSessionId());
        if (session == null) {
            return R.fail("场次不存在");
        }
        List<ExamCandidate> candidates = examCandidateService.lambdaQuery()
                .eq(ExamCandidate::getSessionId, request.getSessionId())
                .eq(request.getClassId() != null, ExamCandidate::getClassId, request.getClassId())
                .orderByDesc(ExamCandidate::getCreateTime)
                .list();
        if (CollectionUtils.isEmpty(candidates)) {
            return R.success(List.of());
        }
        List<ExamCandidateResultResponse> all = buildResultRecords(session, candidates);
        List<ExamCandidateResultResponse> eligible = all.stream()
                .filter(r -> Boolean.TRUE.equals(r.getMakeupEligible()))
                .toList();
        return R.success(eligible);
    }

    @PostMapping("/makeupOpen")
    @Operation(summary = "开放补考窗口（按考生）", description = "发布后不支持补人，仅为已有考生开放补考时间窗口；未通过会自动挂接来源答卷")
    public R<ExamCandidate> makeupOpen(@Valid @RequestBody ExamCandidateMakeupOpenRequest request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作补考");
        }
        ExamCandidate candidate = examCandidateService.getById(request.getCandidateId());
        if (candidate == null) {
            return R.fail("考生不存在");
        }
        ExamSession session = examSessionService.getById(candidate.getSessionId());
        if (session == null) {
            return R.fail("场次不存在");
        }

        // 已通过：不允许再次补考（以最新一次“完成答卷”为准）
        ExamAnswerSheet latestFinished = examAnswerSheetMapper.selectOne(new LambdaQueryWrapper<ExamAnswerSheet>()
                .eq(ExamAnswerSheet::getSessionId, candidate.getSessionId())
                .eq(ExamAnswerSheet::getUserId, candidate.getUserId())
                .eq(ExamAnswerSheet::getStatus, 2)
                .orderByDesc(ExamAnswerSheet::getCreateTime)
                .last("limit 1"));
        if (latestFinished != null && Objects.equals(latestFinished.getPassFlag(), 1)) {
            return R.fail("该学员已通过，无需补考");
        }
        if (latestFinished != null) {
            candidate.setMakeupFromAnswerId(latestFinished.getSheetId());
        }

        Date start = request.getWindowStartOverride() != null ? request.getWindowStartOverride() : new Date();
        candidate.setWindowStartOverride(start);
        candidate.setWindowEndOverride(request.getWindowEndOverride());
        examCandidateService.updateById(candidate);
        return R.success(candidate);
    }

    @PostMapping("/create")
    @Operation(summary = "新增考生")
    public R<ExamCandidate> create(@Valid @RequestBody ExamCandidate request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作考生");
        }
        ExamSession session = examSessionService.getById(request.getSessionId());
        if (session == null) {
            return R.fail("场次不存在");
        }
        if (session.getPublishTime() != null) {
            return R.fail("场次已发布后不支持补人，请使用补考功能");
        }
        request.setId(null);
        request.setUserId(studentService.getUserId(request.getUserId()));
        examCandidateService.save(request);
        return R.success(request);
    }

    @PostMapping("/update")
    @Operation(summary = "更新考生")
    public R<ExamCandidate> update(@Valid @RequestBody ExamCandidate request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作考生");
        }
        if (request.getId() == null) {
            return R.fail("ID不能为空");
        }
        ExamCandidate existing = examCandidateService.getById(request.getId());
        if (existing == null) {
            return R.fail("考生不存在");
        }
        ExamSession session = examSessionService.getById(existing.getSessionId());
        if (session != null && session.getPublishTime() != null) {
            if (!Objects.equals(existing.getSessionId(), request.getSessionId())
                    || !Objects.equals(existing.getUserId(), request.getUserId())
                    || !Objects.equals(existing.getClassId(), request.getClassId())) {
                return R.fail("场次已发布后不允许变更考生名单（仅允许设置补考窗口）");
            }
        }
        examCandidateService.updateById(request);
        return R.success(request);
    }

    @PostMapping("/delete")
    @Operation(summary = "删除考生")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作考生");
        }
        ExamCandidate existing = examCandidateService.getById(request.getId());
        if (existing == null) {
            return R.fail("记录不存在");
        }
        ExamSession session = examSessionService.getById(existing.getSessionId());
        if (session != null && session.getPublishTime() != null) {
            return R.fail("场次已发布后不允许删除考生");
        }
        boolean removed = examCandidateService.removeById(request.getId());
        if (removed) {
            return R.success();
        }
        return R.fail("删除失败");
    }

    @PostMapping("/makeup")
    @Operation(summary = "设置补考", description = "基于一次答卷开放补考窗口：更新考生 makeupFromAnswerId 与时间覆盖字段")
    public R<ExamCandidate> makeup(@Valid @RequestBody ExamCandidateMakeupRequest request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作补考");
        }
        ExamAnswerSheet sheet = examAnswerSheetMapper.selectById(request.getFromAnswerSheetId());
        if (sheet == null) {
            return R.fail("来源答卷不存在");
        }
        ExamCandidate candidate = examCandidateService.lambdaQuery()
                .eq(ExamCandidate::getSessionId, sheet.getSessionId())
                .eq(ExamCandidate::getUserId, sheet.getUserId())
                .last("limit 1")
                .one();
        if (candidate == null) {
            return R.fail("未找到对应考生记录");
        }
        candidate.setMakeupFromAnswerId(sheet.getSheetId());
        if (request.getWindowStartOverride() != null) {
            candidate.setWindowStartOverride(request.getWindowStartOverride());
        }
        if (request.getWindowEndOverride() != null) {
            candidate.setWindowEndOverride(request.getWindowEndOverride());
        }
        examCandidateService.updateById(candidate);
        return R.success(candidate);
    }

    private List<ExamCandidateResultResponse> buildResultRecords(ExamSession session, List<ExamCandidate> candidates) {
        if (session == null || CollectionUtils.isEmpty(candidates)) {
            return List.of();
        }
        List<Long> userIds = candidates.stream()
                .map(ExamCandidate::getUserId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        Map<Long, ExamAnswerSheet> latestSheetByUser = new HashMap<>();
        if (!CollectionUtils.isEmpty(userIds)) {
            List<ExamAnswerSheet> sheets = examAnswerSheetMapper.selectList(new LambdaQueryWrapper<ExamAnswerSheet>()
                    .eq(ExamAnswerSheet::getSessionId, session.getSessionId())
                    .in(ExamAnswerSheet::getUserId, userIds)
                    .orderByDesc(ExamAnswerSheet::getCreateTime));
            for (ExamAnswerSheet sheet : sheets) {
                if (sheet == null || sheet.getUserId() == null) {
                    continue;
                }
                latestSheetByUser.putIfAbsent(sheet.getUserId(), sheet);
            }
        }

        Date now = new Date();
        List<ExamCandidateResultResponse> result = new ArrayList<>(candidates.size());
        for (ExamCandidate c : candidates) {
            ExamAnswerSheet latest = c.getUserId() != null ? latestSheetByUser.get(c.getUserId()) : null;
            result.add(toResult(session, c, latest, now));
        }
        return result;
    }

    private ExamCandidateResultResponse toResult(ExamSession session, ExamCandidate candidate, ExamAnswerSheet latestSheet, Date now) {
        ExamCandidateResultResponse r = new ExamCandidateResultResponse();
        r.setId(candidate.getId());
        r.setSessionId(candidate.getSessionId());
        r.setUserId(candidate.getUserId());
        r.setClassId(candidate.getClassId());
        r.setMakeupFromAnswerId(candidate.getMakeupFromAnswerId());
        r.setWindowStartOverride(candidate.getWindowStartOverride());
        r.setWindowEndOverride(candidate.getWindowEndOverride());

        if (latestSheet != null) {
            r.setLatestSheetId(latestSheet.getSheetId());
            r.setLatestSheetStatus(latestSheet.getStatus());
            r.setLatestPassFlag(latestSheet.getPassFlag());
            r.setLatestSheetCreateTime(latestSheet.getCreateTime());
        }

        Date start = candidate.getWindowStartOverride() != null ? candidate.getWindowStartOverride() : session.getWindowStart();
        Date end = candidate.getWindowEndOverride() != null ? candidate.getWindowEndOverride() : session.getWindowEnd();

        String status;
        if (latestSheet == null) {
            if (end != null && now.after(end)) {
                status = "ABSENT";
            } else if (start != null && now.before(start)) {
                status = "NOT_STARTED";
            } else {
                status = "NOT_STARTED";
            }
        } else if (Objects.equals(latestSheet.getStatus(), 2)) {
            status = Objects.equals(latestSheet.getPassFlag(), 1) ? "PASS" : "FAIL";
        } else {
            // 进行中/未交卷
            if (end != null && now.after(end)) {
                status = "NOT_SUBMITTED";
            } else {
                status = "IN_PROGRESS";
            }
        }

        r.setResultStatus(status);
        r.setResultLabel(resultLabel(status));
        r.setMakeupEligible(List.of("FAIL", "ABSENT", "NOT_SUBMITTED").contains(status));
        return r;
    }

    private String resultLabel(String status) {
        if (status == null) {
            return "";
        }
        return switch (status) {
            case "PASS" -> "通过";
            case "FAIL" -> "未通过";
            case "ABSENT" -> "缺考";
            case "NOT_SUBMITTED" -> "未交卷";
            case "IN_PROGRESS" -> "进行中";
            default -> "未开始";
        };
    }
}
