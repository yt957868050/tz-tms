package com.feian.tms.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.exam.domain.ExamCategory;
import com.feian.tms.exam.mapper.ExamCategoryMapper;
import com.feian.tms.exam.service.ExamCategoryService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ExamCategoryServiceImpl extends MPJBaseServiceImpl<ExamCategoryMapper, ExamCategory> implements ExamCategoryService {

    @Override
    public Page<ExamCategory> pageQuery(PageRequest<ExamCategory> pageRequest) {
        PageRequest<ExamCategory> req = pageRequest != null ? pageRequest : new PageRequest<>();
        Page<ExamCategory> page = new Page<>(req.getPageNum(), req.getPageSize());
        ExamCategory query = req.getQuery();
        Long machineTypeId = query != null ? query.getMachineTypeId() : null;
        String name = query != null ? query.getName() : null;
        Long parentId = query != null ? query.getParentId() : null;
        var wrapper = new LambdaQueryWrapper<ExamCategory>()
                .eq(machineTypeId != null, ExamCategory::getMachineTypeId, machineTypeId)
                .like(StringUtils.hasText(name), ExamCategory::getName, name)
                .eq(parentId != null, ExamCategory::getParentId, parentId)
                .orderByAsc(ExamCategory::getCategoryId);
        return this.baseMapper.selectPage(page, wrapper);
    }

    @Override
    public List<ExamCategory> listByMachine(Long machineTypeId) {
        return this.list(new LambdaQueryWrapper<ExamCategory>()
                .eq(machineTypeId != null, ExamCategory::getMachineTypeId, machineTypeId)
                .orderByAsc(ExamCategory::getCategoryId));
    }
}
