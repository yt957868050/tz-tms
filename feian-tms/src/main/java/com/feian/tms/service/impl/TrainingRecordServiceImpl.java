package com.feian.tms.service.impl;

import com.feian.tms.domain.TrainingRecord;
import com.feian.tms.mapper.TrainingRecordMapper;
import com.feian.tms.service.TrainingRecordService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 培训记录Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class TrainingRecordServiceImpl extends MPJBaseServiceImpl<TrainingRecordMapper, TrainingRecord> implements TrainingRecordService {

}