package com.feian.tms.exam.controller;

import com.feian.tms.common.R;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.exam.domain.ExamCandidate;
import com.feian.tms.exam.domain.ExamSession;
import com.feian.tms.exam.dto.ExamPaperDetailResponse;
import com.feian.tms.exam.dto.ExamPaperSessionViewResponse;
import com.feian.tms.exam.mapper.ExamCandidateMapper;
import com.feian.tms.exam.mapper.ExamSessionMapper;
import com.feian.tms.exam.service.ExamPaperQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exam/paper")
@Tag(name = "考试-试卷查询")
@RequiredArgsConstructor
public class ExamPaperQueryController {

    private final ExamPaperQueryService examPaperQueryService;
    private final ExamCandidateMapper examCandidateMapper;
    private final ExamSessionMapper examSessionMapper;

    @GetMapping("/full/{paperId}")
    @Operation(summary = "获取试卷完整信息（含题目内容）")
    public R<ExamPaperDetailResponse> full(@PathVariable Long paperId) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user == null) {
            return R.fail("未登录");
        }
        if (!user.isAdmin()) {
            return R.fail("无权查看试卷详情");
        }
        ExamPaperDetailResponse response = examPaperQueryService.loadPaper(paperId);
        if (response == null) {
            return R.fail("试卷不存在");
        }
        return R.success(response);
    }

    @GetMapping("/session/{sessionId}")
    @Operation(summary = "按场次获取试卷（应用乱序标记、防作弊标记）")
    public R<ExamPaperSessionViewResponse> sessionView(@PathVariable Long sessionId) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user == null) {
            return R.fail("未登录");
        }
        if (!user.isAdmin()) {
            Long me = SecurityUtils.getUserId();
            if (me == null) {
                return R.fail("未登录");
            }
            ExamCandidate candidate = examCandidateMapper.selectOne(
                    new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<ExamCandidate>()
                            .eq(ExamCandidate::getSessionId, sessionId)
                            .eq(ExamCandidate::getUserId, me)
                            .last("limit 1")
            );
            if (candidate == null) {
                return R.fail("无权查看该场次试卷");
            }
            Long currentMachineTypeId = user.getCurrentMachineTypeId();
            if (currentMachineTypeId != null) {
                ExamSession session = examSessionMapper.selectById(sessionId);
                if (session != null && session.getMachineTypeId() != null && !currentMachineTypeId.equals(session.getMachineTypeId())) {
                    return R.fail("当前机型与考试场次不匹配，请切换机型后再进入");
                }
            }
        }
        ExamPaperSessionViewResponse resp = examPaperQueryService.loadPaperForSession(sessionId);
        if (resp == null) {
            return R.fail("场次或试卷不存在");
        }
        return R.success(resp);
    }
}
