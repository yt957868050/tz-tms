package com.feian.tms.mapper;

import com.feian.tms.domain.TrainingPlanInstructor;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * 培训计划教员关联Mapper接口
 * 
 * @author feian
 * @date 2025-01-24
 */
@Mapper
public interface TrainingPlanInstructorMapper extends MPJBaseMapper<TrainingPlanInstructor> {
     @Delete("delete from tms_training_plan_instructor where plan_id=#{planId}")
    void deleteNew(Long planId);
}