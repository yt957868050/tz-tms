package com.feian.tms.dto.request;

import com.feian.tms.domain.CoursewareFile;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 课件管理请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
public class CoursewareRequest {
    
    /** 课件ID */
    private Long coursewareId;

    /** 课程编码 */
    @NotBlank(message = "课程编码不能为空")
    private String courseCode;

    /** 课程名称 */
    @NotBlank(message = "课程名称不能为空")
    private String courseName;

    /** ATA章节 */
    private String ataChapter;

    /** 机型ID */
    @NotNull(message = "机型不能为空")
    private Long machineTypeId;

    /** 专业ID */
    @NotNull(message = "专业不能为空")
    private Long majorId;

    /** 培训类型ID */
    @NotNull(message = "培训类型不能为空")
    private Long trainingTypeId;

    /** 理论培训时长(小时) */
    private BigDecimal theoryHours;

    /** 实践培训时长(小时) */
    private BigDecimal practiceHours;

    /** 课程描述 */
    private String courseDesc;

    /** 课程目标 */
    private String courseObjective;

    /** 课程要求 */
    private String courseRequirement;

    /** 培训类型（1理论 2实践） */
    private String trainingType;

    /** 状态（0正常 1停用） */
    private String status;

    /** 排序 */
    @NotNull(message = "排序不能为空")
    private Integer orderNum;

    /** 备注 */
    private String remark;

    /** 教员ID */
    private Long instructorId;

    /** 教员姓名 */
    private String instructorName;

    /** 关联的文件列表 */
    private List<CoursewareFile> files;
}