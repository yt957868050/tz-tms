package com.feian.tms.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.common.R;
import com.feian.tms.exam.domain.ExamPaperTemplate;
import com.feian.tms.exam.domain.ExamPaperTemplateItem;
import com.feian.tms.exam.dto.ExamPaperBuildRequest;
import com.feian.tms.exam.dto.ExamPaperBuildResponse;
import com.feian.tms.exam.dto.ExamQuestionUiResponse;
import com.feian.tms.exam.mapper.ExamPaperTemplateItemMapper;
import com.feian.tms.exam.mapper.ExamPaperTemplateMapper;
import com.feian.tms.exam.service.ExamPaperBuildService;
import com.feian.tms.exam.service.ExamQuestionService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exam/paper")
@Tag(name = "考试-组卷")
@RequiredArgsConstructor
public class ExamPaperBuildController {

    private final ExamPaperBuildService examPaperBuildService;
    private final ExamPaperTemplateMapper examPaperTemplateMapper;
    private final ExamPaperTemplateItemMapper examPaperTemplateItemMapper;
    private final ExamQuestionService examQuestionService;

    @PostMapping("/build")
    @Operation(summary = "按章节学时自动组卷")
    public R<ExamPaperBuildResponse> build(@Valid @RequestBody ExamPaperBuildRequest request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限组卷");
        }
        return R.success(examPaperBuildService.buildPaper(request));
    }

    @GetMapping("/preview/{paperId}")
    @Operation(summary = "试卷预览")
    public R<Map<String, Object>> preview(@PathVariable Long paperId) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限预览试卷");
        }
        ExamPaperTemplate template = examPaperTemplateMapper.selectById(paperId);
        if (template == null) {
            return R.fail("试卷不存在");
        }
        List<ExamPaperTemplateItem> items = examPaperTemplateItemMapper.selectList(
                new LambdaQueryWrapper<ExamPaperTemplateItem>()
                        .eq(ExamPaperTemplateItem::getPaperId, paperId)
                        .orderByAsc(ExamPaperTemplateItem::getOrd));
        Map<String, Object> data = new HashMap<>();
        List <ExamQuestionUiResponse> questions =new ArrayList<>();
        for(ExamPaperTemplateItem item:items) {
            ExamQuestionUiResponse detail = examQuestionService.getUiDetail(item.getQuestionId());
            questions.add(detail);
        }
        data.put("paper", template);
        data.put("question", questions);
        return R.success(data);
    }
}
