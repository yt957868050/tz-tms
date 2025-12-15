package com.feian.tms.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.exam.domain.ExamSession;
import com.feian.tms.exam.dto.ExamMySessionRequest;
import com.feian.tms.exam.dto.ExamMySessionResponse;
import com.feian.tms.exam.mapper.ExamSessionMapper;
import com.feian.tms.exam.service.ExamSessionService;
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
 * 考试场次基本管理
 */
@RestController
@RequestMapping("/api/exam/session")
@Tag(name = "考试-场次")
@RequiredArgsConstructor
public class ExamSessionController {

    private final ExamSessionService examSessionService;
    private final ExamSessionMapper examSessionMapper;

    @PostMapping("/list")
    @Operation(summary = "场次列表")
    public R<Page<ExamSession>> list(@RequestBody PageRequest<ExamSession> pageRequest) {
        SysUser currentUser = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (currentUser != null && currentUser.getStudentId() != null && !currentUser.isAdmin()) {
            return R.fail("无权限查看场次列表");
        }
        PageRequest<ExamSession> req = pageRequest != null ? pageRequest : new PageRequest<>();
        Page<ExamSession> page = new Page<>(req.getPageNum(), req.getPageSize());
        ExamSession query = req.getQuery();
        String name = query != null ? query.getName() : null;
        Long machineTypeId = query != null ? query.getMachineTypeId() : null;
        Long paperId = query != null ? query.getPaperId() : null;

        // 非管理员默认按当前选择机型过滤，避免机型上下文缺失导致“全量可见”
        if (currentUser != null && !currentUser.isAdmin()) {
            Long currentMachineTypeId = currentUser.getCurrentMachineTypeId();
            if (currentMachineTypeId != null) {
                machineTypeId = currentMachineTypeId;
            }
        }

        var wrapper = examSessionService.lambdaQuery()
                .like(StringUtils.hasText(name), ExamSession::getName, name)
                .eq(machineTypeId != null, ExamSession::getMachineTypeId, machineTypeId)
                .eq(paperId != null, ExamSession::getPaperId, paperId)
                .orderByDesc(ExamSession::getCreateTime);
        Page<ExamSession> result = wrapper.page(page);
        return R.success(result);
    }

    @PostMapping("/my")
    @Operation(summary = "我的考试场次", description = "按当前登录用户的考生名单返回可参加的场次")
    public R<List<ExamMySessionResponse>> my(@RequestBody(required = false) ExamMySessionRequest request) {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            return R.fail("未登录");
        }
        SysUser currentUser = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        Long machineTypeId = request != null ? request.getMachineTypeId() : null;
        if (currentUser != null && !currentUser.isAdmin()) {
            Long currentMachineTypeId = currentUser.getCurrentMachineTypeId();
            if (currentMachineTypeId == null) {
                return R.fail("请先选择机型");
            }
            machineTypeId = currentMachineTypeId;
        }
        return R.success(examSessionMapper.selectMySessions(userId, machineTypeId));
    }

    @PostMapping("/detail")
    @Operation(summary = "场次详情")
    public R<ExamSession> detail(@Valid @RequestBody IdRequest request) {
        ExamSession session = examSessionService.getById(request.getId());
        if (session == null) {
            return R.fail("场次不存在");
        }
        return R.success(session);
    }

    @PostMapping("/create")
    @Operation(summary = "新增场次")
    public R<ExamSession> create(@Valid @RequestBody ExamSession request) {
        SysUser currentUser = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (currentUser != null && currentUser.getStudentId() != null && !currentUser.isAdmin()) {
            return R.fail("无权限操作场次");
        }
        request.setSessionId(null);
        examSessionService.save(request);
        return R.success(request);
    }

    @PostMapping("/update")
    @Operation(summary = "更新场次")
    public R<ExamSession> update(@Valid @RequestBody ExamSession request) {
        SysUser currentUser = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (currentUser != null && currentUser.getStudentId() != null && !currentUser.isAdmin()) {
            return R.fail("无权限操作场次");
        }
        if (request.getSessionId() == null) {
            return R.fail("场次ID不能为空");
        }
        examSessionService.updateById(request);
        return R.success(request);
    }

    @PostMapping("/delete")
    @Operation(summary = "删除场次")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        SysUser currentUser = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (currentUser != null && currentUser.getStudentId() != null && !currentUser.isAdmin()) {
            return R.fail("无权限操作场次");
        }
        boolean removed = examSessionService.removeById(request.getId());
        if (removed) {
            return R.success();
        }
        return R.fail("删除失败");
    }
}
