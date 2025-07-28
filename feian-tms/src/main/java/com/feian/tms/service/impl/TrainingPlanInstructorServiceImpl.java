package com.feian.tms.service.impl;

import com.feian.tms.domain.TrainingPlanInstructor;
import com.feian.tms.mapper.TrainingPlanInstructorMapper;
import com.feian.tms.service.TrainingPlanInstructorService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 培训计划教员关联Service业务层处理
 * 
 * @author feian
 * @date 2025-01-24
 */
@Service
@RequiredArgsConstructor
public class TrainingPlanInstructorServiceImpl extends MPJBaseServiceImpl<TrainingPlanInstructorMapper, TrainingPlanInstructor> implements TrainingPlanInstructorService {

}