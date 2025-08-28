package com.feian.tms.mapper;

import com.feian.tms.domain.TrainingRecord;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * 培训记录Mapper接口
 * 
 * @author feian
 * @date 2025-01-24
 */
@Mapper
public interface TrainingRecordMapper extends MPJBaseMapper<TrainingRecord> {
    @Select("select * from tms_training_record where is_deleted=0")
    List<TrainingRecord> trainingRecordList();
    @Select("select total_score from tms_training_record where student_id=#{studentId} and is_deleted=0")
    BigDecimal getTotalScoreByStuId(Long studentId);
}