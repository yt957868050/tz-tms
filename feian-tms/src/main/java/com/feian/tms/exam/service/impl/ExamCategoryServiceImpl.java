package com.feian.tms.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.common.PageRequest;
import com.feian.tms.domain.Courseware;
import com.feian.tms.domain.MachineType;
import com.feian.tms.domain.Major;
import com.feian.tms.exam.domain.ExamCategory;
import com.feian.tms.exam.dto.ExamCategoryBranch;
import com.feian.tms.exam.dto.ExamCategoryTree;
import com.feian.tms.exam.mapper.ExamCategoryMapper;
import com.feian.tms.exam.service.ExamCategoryService;
import com.feian.tms.service.MachineTypeService;
import com.feian.tms.service.MajorService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;

@Service
public class ExamCategoryServiceImpl extends MPJBaseServiceImpl<ExamCategoryMapper, ExamCategory> implements ExamCategoryService {

    private final MachineTypeService machineTypeService;
    private final MajorService majorService;

    public ExamCategoryServiceImpl(MachineTypeService machineTypeService, MajorService majorService) {
        this.machineTypeService = machineTypeService;
        this.majorService = majorService;
    }

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
        List<ExamCategory> list = this.baseMapper.selectList(wrapper);
        return this.baseMapper.selectPage(page, wrapper);
    }

    @Override
    public List<ExamCategory> listByMachine(Long machineTypeId) {
        return this.list(new LambdaQueryWrapper<ExamCategory>()
                .eq(machineTypeId != null, ExamCategory::getMachineTypeId, machineTypeId)
                .orderByAsc(ExamCategory::getCategoryId));
    }

    @Override
    public void saveCategory(Courseware entity) {
        ExamCategory category = new ExamCategory();
        category.setName(entity.getCourseName());
//        Long parentId=Major.(majorService.getMajorName(entity.getMajorId()));
        category.setParentId(entity.getMajorId());
        String path= "/"+machineTypeService.getMachineTypeName(entity.getMachineTypeId()) + "/" + majorService.getMajorName(entity.getMajorId())+"/"+entity.getCourseName();
        category.setPath(path);
        category.setMachineTypeId(entity.getMachineTypeId());
        category.setTmsOutlineId(entity.getCoursewareId());
        int theoryHours = (int) Math.ceil(entity.getTheoryMinutes().doubleValue() / 45.0);
        category.setClassHour(theoryHours);
        category.setStatus(0);
        this.save(category);
    }

    @Override
    public ExamCategoryTree pageExamCategoryTree() {
        ExamCategoryTree tree = new ExamCategoryTree();
        List<MachineType> machineTypes = machineTypeService.list();
        List<Major> majors = majorService.list();
        for(MachineType machineType:machineTypes){
            for(Major major:majors){
                ExamCategoryBranch branch = new ExamCategoryBranch();
                branch.setMachineType(machineType.getMachineTypeName());
                branch.setMajorName(major.getMajorName());
                branch.setExamCategoryList(this.list(new LambdaQueryWrapper<ExamCategory>()
                        .eq(ExamCategory::getMachineTypeId, machineType.getMachineTypeId())
                        .eq(ExamCategory::getParentId, major.getMajorId())
                        .orderByAsc(ExamCategory::getCategoryId)));
                tree.getExamCategoryBranchList().add(branch);
            }
        }


        return tree;
    }
}
