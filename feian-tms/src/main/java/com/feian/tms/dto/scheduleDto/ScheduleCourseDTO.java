package com.feian.tms.dto.scheduleDto;


import lombok.Data;

/**
 * 单节课程响应对象，对应 "Daycourses" 列表中的每个元素
 */
@Data
public class ScheduleCourseDTO {

    /** 课程时间ID */
    private Long timeId;

    /** 课程时段（上午/下午） */
    private Long dayTime;

    /** ATA章节 */
    private String ataChapter;

    /** 课程章节ID */
    private Long coursewareId;

    /** 课程章节名称 */
    private String courseName;

    /** 教员ID */
    private Long instructorId;

    /** 教员姓名 */
    private String instructorName;
}