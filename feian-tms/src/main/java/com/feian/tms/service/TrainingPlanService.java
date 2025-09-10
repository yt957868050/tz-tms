package com.feian.tms.service;

import com.feian.tms.domain.TrainingPlan;
import com.github.yulichang.base.MPJBaseService;

import java.util.Date;
import java.util.List;

/**
 * 培训计划管理Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface TrainingPlanService extends MPJBaseService<TrainingPlan> {

    boolean deleteBatch(List<Long> idList);

    String getClassNameById(Long trainingClassId);

    /**
     * 根据班级Id查询计划id
     * @param classId
     * @return
     */
    Long getPlanIdByClass(Long classId);

    Integer getTotalHoursById(Long planId);

    Integer getTheoryHoursById(Long planId);

    Integer getPracticeHoursById(Long planId);

    Date getStartDateById(Long planId);

    Date getEndDate(Long planId);
}