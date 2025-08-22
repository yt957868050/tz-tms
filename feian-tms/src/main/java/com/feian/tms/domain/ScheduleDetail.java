package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date; // 用于映射 DATE 类型的 coursewareDate

/**
 * 排课详情表对象 tms_schedule_detail
 *
 * @author feian
 * @date 2025-XX-XX (请根据实际生成日期修改)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_schedule_detail")
public class ScheduleDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 排课主表ID，关联schedule_main表 */
    @Excel(name = "排课主表ID")
    private Long scheduleMainId;

    /** 课程类型，1是理论，2是实作 */
    @Excel(name = "课程类型", readConverterExp = "1=理论,2=实作")
    private Integer courseType;

    /** 课程日期，2025/8/11 */
    @Excel(name = "课程日期")
    private Date coursewareDate; // 对应 SQL 的 DATE 类型

    /** 排课天数（第几天，从1开始） */
    @Excel(name = "排课天数")
    private Long dayNumber; // 对应 SQL 的 BIGINT 类型

    /** 课程时段，上午为1，下午为2 */
    @Excel(name = "课程时段", readConverterExp = "1=上午,2=下午")
    private Long dayTime; // 对应 SQL 的 BIGINT 类型

    /** 课程时间，8:30-9:30为1，9:30-10:30为2，...16:00-17:00为7 */
    @Excel(name = "课程时间", readConverterExp = "1=8:30-9:30,2=9:30-10:30,3=10:30-11:30,4=13:00-14:00,5=14:00-15:00,6=15:00-16:00,7=16:00-17:00")
    private Long timeId; // 对应 SQL 的 BIGINT 类型

    /** 课程章节ID，关联课程章节表 */
    @Excel(name = "课程章节ID")
    private Long coursewareId;

    /** 教员ID，关联教员表 */
    @Excel(name = "课程章节ID")
    private Long instructorId;

    // 关联显示字段 (如果需要，可在此处添加)
     @com.baomidou.mybatisplus.annotation.TableField(exist = false)
     private String courseName; // 课程章节名称
     @com.baomidou.mybatisplus.annotation.TableField(exist = false)
     private String ataChapter; // ATA章节
     @com.baomidou.mybatisplus.annotation.TableField(exist = false)
     private String instructorName; // 教员姓名

}
