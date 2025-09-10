package com.feian.tms.mapper;

import com.feian.tms.domain.TrainingPlan;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 培训计划管理Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface TrainingPlanMapper extends MPJBaseMapper<TrainingPlan> {

    void deleteBatch(List<Long> idList);
       @Select("select class_name from tms_training_class where training_class_id=#{trainingClassId}")
    String getClassNameById(Long trainingClassId);

    @Select("select plan_id from tms_training_plan where training_class_id=#{classId}")
    Long getPlanIdByClass(Long classId);
    
    @Select("select total_hours from tms_training_plan where plan_id=#{planId}")
    Integer getTotalHoursById(Long planId);
    @Select("select theory_hours from tms_training_plan where plan_id=#{planId}")
    Integer getTheoryHoursById(Long planId);
    @Select("select practice_hours from tms_training_plan where plan_id=#{planId}")
    Integer getPracticeHoursById(Long planId);

    @Select("select  start_date from tms_training_plan where plan_id=#{planId} and is_deleted=0")
    Date getStartDateById(Long planId);

    @Select("select  end_date from tms_training_plan where plan_id=#{planId} and is_deleted=0")
    Date getEndDate(Long planId);
}