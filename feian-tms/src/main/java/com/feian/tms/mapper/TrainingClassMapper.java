package com.feian.tms.mapper;

import com.feian.tms.domain.TrainingClass;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 培训班次Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface TrainingClassMapper extends MPJBaseMapper<TrainingClass> {

    void deleteBatch(List<Long> idList);
    @Select("select count(0) from tms_class_student where training_class_id=#{trainingClassId} and is_deleted=0")
    Integer getStudentCountById(Long trainingClassId);
    @Select("select * from tms_training_class where is_deleted=0")
    List<TrainingClass> trainingClassList();
    @Select("select class_code from tms_training_class where training_class_id=#{trainingClassId}")
    String getClassCode(Long trainingClassId);
    @Select("select class_name from tms_training_class where training_class_id=#{trainingClassId}")
    String getClassName(Long trainingClassId);
}