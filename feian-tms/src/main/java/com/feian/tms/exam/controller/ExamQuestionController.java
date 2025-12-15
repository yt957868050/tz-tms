package com.feian.tms.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.exam.domain.ExamQuestion;
import com.feian.tms.exam.dto.ExamPaperDetailResponse;
import com.feian.tms.exam.dto.ExamQuestionRequest;
import com.feian.tms.exam.service.ExamQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 题库管理
 */
@RestController
@RequestMapping("/api/exam/question")
@Tag(name = "考试-题库")
@RequiredArgsConstructor
public class ExamQuestionController {

    private final ExamQuestionService examQuestionService;

    @PostMapping("/list")
    @Operation(summary = "题目列表")
    public R<Page<ExamQuestion>> list(@RequestBody PageRequest<ExamQuestionRequest> pageRequest) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限访问题库");
        }
        return R.success(examQuestionService.pageQuery(pageRequest));
    }

    @PostMapping("/detail")
    @Operation(summary = "题目详情")
    public R<ExamPaperDetailResponse.Item> detail(@Valid @RequestBody IdRequest request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限访问题库");
        }
        ExamPaperDetailResponse.Item detail = examQuestionService.getDetail(request.getId());
        if (detail == null) {
            return R.fail("题目不存在");
        }
        return R.success(detail);
    }

    @PostMapping("/create")
    @Operation(summary = "新增题目")
    public R<Long> create(@Valid @RequestBody ExamQuestionRequest request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作题库");
        }
        Long id = examQuestionService.saveOrUpdateQuestion(request);
        return R.success(id);
    }

    @PostMapping("/update")
    @Operation(summary = "更新题目")
    public R<Long> update(@Valid @RequestBody ExamQuestionRequest request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作题库");
        }
        if (request.getQuestionId() == null) {
            return R.fail("题目ID不能为空");
        }
        Long id = examQuestionService.saveOrUpdateQuestion(request);
        return R.success(id);
    }

    @PostMapping("/delete")
    @Operation(summary = "删除题目")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作题库");
        }
        examQuestionService.deleteQuestion(request.getId());
        return R.success();
    }
}
