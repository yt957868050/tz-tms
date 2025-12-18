package com.feian.tms.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.domain.Courseware;
import com.feian.tms.exam.domain.ExamCategory;
import com.feian.tms.exam.dto.ExamCategoryTree;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

public interface ExamCategoryService extends MPJBaseService<ExamCategory> {

    Page<ExamCategory> pageQuery(PageRequest<ExamCategory> pageRequest);

    List<ExamCategory> listByMachine(Long machineTypeId);

    void saveCategory(Courseware entity);

    ExamCategoryTree pageExamCategoryTree();
}
