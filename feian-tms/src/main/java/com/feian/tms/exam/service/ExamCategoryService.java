package com.feian.tms.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.exam.domain.ExamCategory;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

public interface ExamCategoryService extends MPJBaseService<ExamCategory> {

    Page<ExamCategory> pageQuery(PageRequest<ExamCategory> pageRequest);

    List<ExamCategory> listByMachine(Long machineTypeId);
}
