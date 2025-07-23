package com.feian.tms.service.impl;

import com.feian.tms.domain.TrainingPlan;
import com.feian.tms.mapper.TrainingPlanMapper;
import com.feian.tms.service.TrainingPlanService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 培训计划管理Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class TrainingPlanServiceImpl extends MPJBaseServiceImpl<TrainingPlanMapper, TrainingPlan> implements TrainingPlanService {

}