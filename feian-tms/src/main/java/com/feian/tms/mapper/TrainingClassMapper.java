package com.feian.tms.mapper;

import com.feian.tms.domain.TrainingClass;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
}