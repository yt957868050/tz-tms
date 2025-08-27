package com.feian.tms.service.impl;

import com.feian.tms.domain.TrainingPlan;
import com.feian.tms.mapper.TrainingPlanMapper;
import com.feian.tms.service.TrainingPlanService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 培训计划管理Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class TrainingPlanServiceImpl extends MPJBaseServiceImpl<TrainingPlanMapper, TrainingPlan> implements TrainingPlanService {
    @Autowired
    public TrainingPlanMapper trainingPlanMapper;
    @Override
    public boolean deleteBatch(List<Long> idList) {
        trainingPlanMapper.deleteBatch(idList);
        return true;
    }

    @Override
    public String getClassNameById(Long trainingClassId) {
        return trainingPlanMapper.getClassNameById(trainingClassId);
    }

    /**
     * 根据班级Id查询计划Id
     * @param classId
     * @return
     */
    public Long getPlanIdByClass(Long classId) {
        return trainingPlanMapper.getPlanIdByClass(classId);
    }

    @Override
    public Integer getTotalHoursById(Long planId) {
        return trainingPlanMapper.getTotalHoursById(planId);
    }

    @Override
    public Integer getTheoryHoursById(Long planId) {
        return trainingPlanMapper.getTheoryHoursById(planId);
    }

    @Override
    public Integer getPracticeHoursById(Long planId) {
        return trainingPlanMapper.getPracticeHoursById(planId);
    }
}