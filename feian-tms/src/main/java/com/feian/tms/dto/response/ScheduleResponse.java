package com.feian.tms.dto.response;
import com.feian.tms.dto.scheduleDto.ScheduleDayDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 排课计划响应对象，对应后端返回给前端的顶层数据结构
 */
@Data
public class ScheduleResponse {

    /** 计划ID */
    private Long planId;

    /** 排课开始日期 */
    private Date startDate;

    /** 培训班级ID */
    private Long trainingClassId;

    /** 计划名称 */
    private String planName;

    /** 班级名称 */
    private String className;

    /** 总天数 */
    private Integer totalDays;

    /** 每日课程列表 */
    private List<ScheduleDayDTO> dayscourses;
}