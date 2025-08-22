package com.feian.tms.mapper;

import com.feian.tms.domain.ScheduleDetail;
import com.github.yulichang.base.MPJBaseMapper;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Delete;

public interface ScheduleDetailMapper extends MPJBaseMapper<ScheduleDetail> {
    /**
     * 删除课程详细表
     * @param scheduleMainId
     * @param courseType
     */
    @Delete("delete from tms_schedule_detail where schedule_main_id=#{scheduleMainId} and course_type=#{courseType}")
    void deleteAll(Long scheduleMainId, Integer courseType);
}
