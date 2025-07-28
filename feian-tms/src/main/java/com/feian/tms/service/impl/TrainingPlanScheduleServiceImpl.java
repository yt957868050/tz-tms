package com.feian.tms.service.impl;

import com.feian.tms.domain.TrainingPlanSchedule;
import com.feian.tms.mapper.TrainingPlanScheduleMapper;
import com.feian.tms.service.TrainingPlanScheduleService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 培训计划课程安排Service业务层处理
 * 
 * @author feian
 * @date 2025-01-24
 */
@Service
@RequiredArgsConstructor
public class TrainingPlanScheduleServiceImpl extends MPJBaseServiceImpl<TrainingPlanScheduleMapper, TrainingPlanSchedule> implements TrainingPlanScheduleService {

}