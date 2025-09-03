package com.feian.tms.service;

import com.feian.tms.domain.TrainingRecord;
import com.github.yulichang.base.MPJBaseService;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

/**
 * 培训记录Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface TrainingRecordService extends MPJBaseService<TrainingRecord> {

    List<TrainingRecord> trainingRecordList();

    /**
     * 根据学员ID获取理论成绩
     * @param studentId
     * @return
     */
    Integer getTheoryScoreByStuId(@NotNull(message = "学生ID不能为空") Long studentId);

    /**
     * 根据学员ID获取实作成绩
     * @param studentId
     * @return
     */
    Integer getPracticeScoreByStuId(@NotNull(message = "学生ID不能为空") Long studentId);
}