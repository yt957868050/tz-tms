package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 班次学员关联响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "班次学员关联响应对象")
public class ClassStudentResponse {
    
    @Schema(description = "班次学员关联ID", example = "1")
    private Long classStudentId;

    @Schema(description = "培训班次ID", example = "1")
    private Long trainingClassId;

    @Schema(description = "学员ID", example = "1")
    private Long studentId;

    @Schema(description = "报名时间", example = "2024-01-01 10:00:00")
    private Date enrollTime;

    @Schema(description = "学员状态（0待开班 1培训中 2已结业 3已退班 4请假中）", example = "0")
    private String studentStatus;

    @Schema(description = "退班原因", example = "个人原因")
    private String withdrawReason;

    @Schema(description = "退班时间", example = "2024-02-01 10:00:00")
    private Date withdrawTime;

    @Schema(description = "班次编号", example = "CLASS2024001")
    private String classCode;

    @Schema(description = "班次名称", example = "第一期培训班")
    private String className;

    @Schema(description = "学员姓名", example = "张三")
    private String studentName;

    @Schema(description = "学员工号", example = "S001")
    private String studentCode;

    @Schema(description = "创建者", example = "admin")
    private String createBy;

    @Schema(description = "创建时间", example = "2024-01-01 10:00:00")
    private Date createTime;

    @Schema(description = "更新者", example = "admin")
    private String updateBy;

    @Schema(description = "更新时间", example = "2024-01-01 10:00:00")
    private Date updateTime;

    @Schema(description = "备注", example = "备注信息")
    private String remark;
}