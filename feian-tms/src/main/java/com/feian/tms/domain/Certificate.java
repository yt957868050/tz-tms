package com.feian.tms.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.*;

import java.util.Date;

/**
 * 证书管理对象 tms_certificate
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tms_certificate")
public class Certificate extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 证书ID */
    @TableId(type = IdType.AUTO)
    private Long certificateId;

    /** 证书编号 */
    @Excel(name = "证书编号")
    private String certificateCode;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 学生编号 */
    @Excel(name = "学生编号")
    private String studentCode;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 学生英文姓名 */
    @Excel(name = "学生英文姓名")
    private String engStudentName;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String trainingCourse;

    /** 课程英文姓名 */
    @Excel(name = "课程英文姓名")
    private String engTrainingCourse;

    /** 开始日期 */
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束日期 */
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 总课时 */
    @Excel(name = "总课时")
    private Integer totalHours;

    /** 理论课时 */
    @Excel(name = "理论课时")
    private Integer theoryHours;

    /** 实作课时 */
    @Excel(name = "实作课时")
    private Integer practiceHours;


}