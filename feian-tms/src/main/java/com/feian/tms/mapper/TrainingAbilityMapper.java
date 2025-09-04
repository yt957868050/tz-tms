package com.feian.tms.mapper;

import com.feian.tms.domain.TrainingAbility;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 培训能力Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface TrainingAbilityMapper extends MPJBaseMapper<TrainingAbility> {

    @Select("select ability_name from tms_training_ability where  training_ability_id=#{trainingAbilityId} and is_deleted=0")
    String gettrainingAbilityName(Long trainingAbilityId);
}