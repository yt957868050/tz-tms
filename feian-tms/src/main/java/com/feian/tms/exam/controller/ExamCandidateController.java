package com.feian.tms.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.exam.domain.ExamCandidate;
import com.feian.tms.exam.service.ExamCandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 考生管理
 */
@RestController
@RequestMapping("/api/exam/candidate")
@Tag(name = "考试-考生")
@RequiredArgsConstructor
public class ExamCandidateController {

    private final ExamCandidateService examCandidateService;

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

    @PostMapping("/create")
    @Operation(summary = "新增考生")
    public R<ExamCandidate> create(@Valid @RequestBody ExamCandidate request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作考生");
        }
        request.setId(null);
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
        boolean removed = examCandidateService.removeById(request.getId());
        if (removed) {
            return R.success();
        }
        return R.fail("删除失败");
    }
}
