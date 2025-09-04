package com.feian.tms.service.impl;

import com.feian.tms.domain.TrainingAbility;
import com.feian.tms.mapper.TrainingAbilityMapper;
import com.feian.tms.service.TrainingAbilityService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 培训能力Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class TrainingAbilityServiceImpl extends MPJBaseServiceImpl<TrainingAbilityMapper, TrainingAbility> implements TrainingAbilityService {
   @Autowired
    TrainingAbilityMapper trainingAbilityMapper;
    @Override
    public String gettrainingAbilityName(Long trainingAbilityId) {
        return trainingAbilityMapper.gettrainingAbilityName(trainingAbilityId);
    }
}