package com.feian.tms.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.exam.domain.ExamAnswerItem;
import com.feian.tms.exam.domain.ExamAnswerSheet;
import com.feian.tms.exam.domain.ExamProctorEvent;
import com.feian.tms.exam.dto.ExamAnswerStartRequest;
import com.feian.tms.exam.dto.ExamAnswerStartResponse;
import com.feian.tms.exam.dto.ExamAnswerSubmitRequest;
import com.feian.tms.exam.dto.ExamManualScoreRequest;
import com.feian.tms.exam.dto.ExamProctorEventRequest;
import com.feian.tms.exam.mapper.ExamAnswerItemMapper;
import com.feian.tms.exam.mapper.ExamAnswerSheetMapper;
import com.feian.tms.exam.service.ExamAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/exam/answer")
@Tag(name = "考试-答卷")
@RequiredArgsConstructor
public class ExamAnswerController {

    private final ExamAnswerService examAnswerService;
    private final ExamAnswerSheetMapper examAnswerSheetMapper;
    private final ExamAnswerItemMapper examAnswerItemMapper;

    @PostMapping("/list")
    @Operation(summary = "答卷列表（可按场次/用户/通过状态）")
    public R<Page<ExamAnswerSheet>> list(@RequestBody PageRequest<ExamAnswerSheet> pageRequest) {
        return R.success(examAnswerService.pageQuery(pageRequest));
    }

    @PostMapping("/stats")
    @Operation(summary = "答卷统计（总数/通过数/平均分等）")
    public R<Map<String, Object>> stats(@RequestBody(required = false) ExamAnswerSheet query) {
        return R.success(examAnswerService.stats(query));
    }

    @PostMapping("/start")
    @Operation(summary = "开始考试，生成答卷")
    public R<ExamAnswerSheet> start(@Valid @RequestBody ExamAnswerStartRequest request) {
        return R.success(examAnswerService.startExam(request));
    }

    @PostMapping("/startWithPaper")
    @Operation(summary = "开始考试并返回试卷（含乱序/防作弊标记）")
    public R<ExamAnswerStartResponse> startWithPaper(@Valid @RequestBody ExamAnswerStartRequest request) {
        return R.success(examAnswerService.startExamWithPaper(request));
    }

    @PostMapping("/submit")
    @Operation(summary = "提交答卷")
    public R<ExamAnswerSheet> submit(@Valid @RequestBody ExamAnswerSubmitRequest request) {
        return R.success(examAnswerService.submit(request));
    }

    @GetMapping("/detail/{sheetId}")
    @Operation(summary = "答卷详情（含答题明细）")
    public R<Map<String, Object>> detail(@PathVariable Long sheetId) {
        ExamAnswerSheet sheet = examAnswerSheetMapper.selectById(sheetId);
        if (sheet == null) {
            return R.fail("答卷不存在");
        }
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user == null) {
            return R.fail("未登录");
        }
        if (!user.isAdmin()) {
            Long me = SecurityUtils.getUserId();
            if (me == null || !Objects.equals(sheet.getUserId(), me)) {
                return R.fail("无权查看他人答卷");
            }
        }
        List<ExamAnswerItem> items = examAnswerItemMapper.selectList(new LambdaQueryWrapper<ExamAnswerItem>()
                .eq(ExamAnswerItem::getSheetId, sheetId)
                .orderByAsc(ExamAnswerItem::getId));
        Map<String, Object> data = new HashMap<>();
        data.put("sheet", sheet);
        data.put("items", items);
        return R.success(data);
    }

    @PostMapping("/manualScore")
    @Operation(summary = "人工批改答卷")
    public R<ExamAnswerSheet> manualScore(@Valid @RequestBody ExamManualScoreRequest request) {
        return R.success(examAnswerService.manualScore(request));
    }

    @PostMapping("/proctor")
    @Operation(summary = "上报监考事件")
    public R<Void> proctor(@Valid @RequestBody ExamProctorEventRequest request) {
        ExamProctorEvent event = new ExamProctorEvent();
        event.setSheetId(request.getSheetId());
        event.setEventType(request.getEventType());
        event.setPayload(request.getPayload());
        examAnswerService.recordProctorEvent(event);
        return R.success();
    }

    @GetMapping("/proctor/{sheetId}")
    @Operation(summary = "查看监考事件")
    public R<List<ExamProctorEvent>> proctorEvents(@PathVariable Long sheetId) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user == null) {
            return R.fail("未登录");
        }
        if (!user.isAdmin()) {
            Long me = SecurityUtils.getUserId();
            ExamAnswerSheet sheet = examAnswerSheetMapper.selectById(sheetId);
            if (sheet == null) {
                return R.fail("答卷不存在");
            }
            if (me == null || !Objects.equals(sheet.getUserId(), me)) {
                return R.fail("无权查看他人监考事件");
            }
        }
        return R.success(examAnswerService.listProctorEvents(sheetId));
    }
}
