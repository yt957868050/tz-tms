package com.feian.tms.exam.practice.controller;

import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.common.R;
import com.feian.tms.exam.practice.dto.ExamPracticeChapterRequest;
import com.feian.tms.exam.practice.dto.ExamPracticeChapterResponse;
import com.feian.tms.exam.practice.dto.ExamPracticeQuestionRequest;
import com.feian.tms.exam.practice.dto.ExamPracticeQuestionResponse;
import com.feian.tms.exam.practice.dto.ExamPracticeWrongSaveRequest;
import com.feian.tms.exam.practice.service.ExamPracticeService;
import com.feian.common.core.context.MachineTypeContextHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 自测练习（独立于考试发布/参加）
 */
@RestController
@RequestMapping("/api/exam/practice")
@Tag(name = "考试-自测练习")
@RequiredArgsConstructor
public class ExamPracticeController {

    private final ExamPracticeService examPracticeService;

    @PostMapping("/chapter/list")
    @Operation(summary = "章节列表（自测）", description = "按当前机型返回章节，并附带可自测题目数/已保存错题数")
    public R<List<ExamPracticeChapterResponse>> chapterList(@RequestBody(required = false) ExamPracticeChapterRequest request) {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            return R.fail("未登录");
        }
        Long machineTypeId = resolveMachineTypeId(request != null ? request.getMachineTypeId() : null);
        if (machineTypeId == null) {
            return R.fail("请先选择机型");
        }
        return R.success(examPracticeService.listChapters(userId, machineTypeId));
    }

    @PostMapping("/question/list")
    @Operation(summary = "题目列表（自测）", description = "mode=all 返回章节全部可自测题；mode=wrong 返回当前用户已保存错题")
    public R<List<ExamPracticeQuestionResponse>> questionList(@Valid @RequestBody ExamPracticeQuestionRequest request) {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            return R.fail("未登录");
        }
        Long machineTypeId = resolveMachineTypeId(request.getMachineTypeId());
        if (machineTypeId == null) {
            return R.fail("请先选择机型");
        }
        String mode = StringUtils.hasText(request.getMode()) ? request.getMode().trim() : "all";
        if ("wrong".equalsIgnoreCase(mode)) {
            return R.success(examPracticeService.listWrongQuestions(userId, machineTypeId, request.getCategoryId()));
        }
        return R.success(examPracticeService.listAllQuestions(machineTypeId, request.getCategoryId()));
    }

    @PostMapping("/wrong/save")
    @Operation(summary = "保存错题（自测）", description = "覆盖式保存：每个用户每个章节仅保留最新一次错题列表")
    public R<Void> saveWrong(@Valid @RequestBody ExamPracticeWrongSaveRequest request) {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            return R.fail("未登录");
        }
        Long machineTypeId = resolveMachineTypeId(request.getMachineTypeId());
        if (machineTypeId == null) {
            return R.fail("请先选择机型");
        }
        ExamPracticeService.SaveResult result;
        try {
            result = examPracticeService.saveWrongQuestions(userId, machineTypeId, request.getCategoryId(), request.getQuestionIds());
        } catch (IllegalArgumentException e) {
            return R.fail(e.getMessage());
        }
        if (result.ignoredCount() > 0) {
            return R.success("已保存错题 " + result.savedCount() + " 道，忽略无效题目 " + result.ignoredCount() + " 道");
        }
        return R.success("已保存错题 " + result.savedCount() + " 道");
    }

    private Long resolveMachineTypeId(Long requestMachineTypeId) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user == null) {
            return null;
        }
        if (!user.isAdmin()) {
            return MachineTypeContextHolder.getMachineTypeId();
        }
        if (requestMachineTypeId != null) {
            return requestMachineTypeId;
        }
        return MachineTypeContextHolder.getMachineTypeId();
    }
}
