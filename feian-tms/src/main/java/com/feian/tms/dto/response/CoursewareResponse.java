package com.feian.tms.dto.response;

import lombok.Data;
import com.feian.tms.domain.CoursewareFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 课件管理响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
public class CoursewareResponse {
    
    /** 课件ID */
    private Long coursewareId;

    /** 课程编码 */
    private String courseCode;

    /** 课程名称 */
    private String courseName;

    /** ATA章节 */
    private String ataChapter;

    /** 机型ID */
    private Long machineTypeId;

    /** 专业ID */
    private Long majorId;

    /** 培训类型ID */
    private Long trainingTypeId;

    /** 理论培训时长(小时) */
    private BigDecimal theoryMinutes;

    /** 实践培训时长(小时) */
    private BigDecimal practiceMinutes;

    /** 课程描述 */
    private String courseDesc;

    /** 课程目标 */
    private String courseObjective;

    /** 课程要求 */
    private String courseRequirement;

    /** 状态（0正常 1停用） */
    private String status;

    /** 排序 */
    private Integer orderNum;

    /** 机型名称 */
    private String machineTypeName;

    /** 专业名称 */
    private String majorName;

    /** 培训类型名称 */
    private String trainingTypeName;

    /** 教员ID */
    private Long instructorId;

    /** 教员姓名 */
    private String instructorName;


    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 培训类型（1理论 2实践） */
    private String trainingType;

    /** 关联的课件文件列表 */
    private List<CoursewareFile> files;
}