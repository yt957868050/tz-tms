package com.feian.tms.mapper;

import com.feian.tms.domain.ScheduleMain;
import com.github.yulichang.base.MPJBaseMapper;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

public interface ScheduleMainMapper extends MPJBaseMapper<ScheduleMain> {
    /**
     * 查询主表ID
     * @param planId
     * @param courseType
     * @return
     */
    @Select("select schedule_main_id from tms_schedule_main where plan_id=#{planId} and course_type=#{courseType}")
    Long getIdByPlan(@NotNull(message = "计划ID不能为空") Long planId, @NotNull(message = "课程类型不能为空") Integer courseType);

    /**
     * 删除主表
     * @param scheduleMainId
     */
    @Delete("delete from tms_schedule_main where schedule_main_id=#{scheduleMainId}")
    void deleteAll(Long scheduleMainId);
}
