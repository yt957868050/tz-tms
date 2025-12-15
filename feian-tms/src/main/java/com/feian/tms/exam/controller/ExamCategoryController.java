package com.feian.tms.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.exam.domain.ExamCategory;
import com.feian.tms.exam.service.ExamCategoryService;
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

import java.util.List;

/**
 * 章节/分类管理
 */
@RestController
@RequestMapping("/api/exam/category")
@Tag(name = "考试-章节/分类")
@RequiredArgsConstructor
public class ExamCategoryController {

    private final ExamCategoryService examCategoryService;

    @PostMapping("/list")
    @Operation(summary = "分页查询章节")
    public R<Page<ExamCategory>> list(@RequestBody PageRequest<ExamCategory> pageRequest) {
        return R.success(examCategoryService.pageQuery(pageRequest));
    }

    @GetMapping("/machine/{machineTypeId}")
    @Operation(summary = "按机型查询全部章节")
    public R<List<ExamCategory>> byMachine(@PathVariable Long machineTypeId) {
        return R.success(examCategoryService.listByMachine(machineTypeId));
    }

    @PostMapping("/create")
    @Operation(summary = "新增章节")
    public R<ExamCategory> create(@Valid @RequestBody ExamCategory request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作章节");
        }
        request.setCategoryId(null);
        examCategoryService.save(request);
        return R.success(request);
    }

    @PostMapping("/update")
    @Operation(summary = "更新章节")
    public R<ExamCategory> update(@Valid @RequestBody ExamCategory request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作章节");
        }
        if (request.getCategoryId() == null) {
            return R.fail("章节ID不能为空");
        }
        examCategoryService.updateById(request);
        return R.success(request);
    }

    @PostMapping("/delete")
    @Operation(summary = "删除章节")
    public R<Void> delete(@Valid @RequestBody IdRequest request) {
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (user != null && user.getStudentId() != null && !user.isAdmin()) {
            return R.fail("无权限操作章节");
        }
        examCategoryService.removeById(request.getId());
        return R.success();
    }
}
