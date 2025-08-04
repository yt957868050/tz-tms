package com.feian.tms.service;

import com.feian.tms.domain.TrainingPlanInstructor;
import com.github.yulichang.base.MPJBaseService;

/**
 * 培训计划教员关联Service接口
 * 
 * @author feian
 * @date 2025-01-24
 */
public interface TrainingPlanInstructorService extends MPJBaseService<TrainingPlanInstructor> {

    void delete(Long planId);
}