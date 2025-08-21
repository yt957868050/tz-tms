package com.feian.tms.dto.scheduleDto;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 每日课程响应对象，对应 "Dayscourses" 列表中的每个元素
 */
@Data
public class ScheduleDayDTO {

    /** 课程日期 */
    private Date date;

    /** 一天内的所有课程列表 */
    private List<ScheduleCourseDTO> daycourses;
}
