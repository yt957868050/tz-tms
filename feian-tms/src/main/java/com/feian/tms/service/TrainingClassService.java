package com.feian.tms.service;

import com.feian.tms.domain.TrainingClass;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 培训班次Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface TrainingClassService extends MPJBaseService<TrainingClass> {

    boolean deleteBatch(List<Long> idList);

    Integer getStudentCountById(Long trainingClassId);

    List<TrainingClass> trainingClassList();
}