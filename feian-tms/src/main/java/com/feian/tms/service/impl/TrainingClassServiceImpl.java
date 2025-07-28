package com.feian.tms.service.impl;

import com.feian.tms.domain.TrainingClass;
import com.feian.tms.mapper.TrainingClassMapper;
import com.feian.tms.service.TrainingClassService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 培训班次Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class TrainingClassServiceImpl extends MPJBaseServiceImpl<TrainingClassMapper, TrainingClass> implements TrainingClassService {
    @Autowired
    public TrainingClassMapper trainingClassMapper;
    @Override
    public boolean deleteBatch(List<Long> idList) {
        trainingClassMapper.deleteBatch(idList);
        return true;
    }
}