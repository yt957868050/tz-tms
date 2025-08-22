package com.feian.tms.service;


import com.feian.tms.dto.request.ScheduleRequest;
import com.feian.tms.dto.response.ScheduleResponse;


public interface ScheduleService {
    /**
     * 自动排课功能
     * @param request
     */
    void autoScheduleTheory(ScheduleRequest request);

    /**
     * 查询课表
     * @param request
     * @return
     */
    ScheduleResponse getTheorySchedule(ScheduleRequest request);

    /**
     * 删除课表
     * @param request
     */
    void deleteSchedule(ScheduleRequest request);
}
