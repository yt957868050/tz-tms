package com.feian.tms.service;

import com.feian.tms.domain.TrainingRecord;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 培训记录Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface TrainingRecordService extends MPJBaseService<TrainingRecord> {

    List<TrainingRecord> trainingRecordList();
}