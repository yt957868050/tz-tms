package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 教员信息查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "教员信息查询对象")
public class InstructorQuery extends PageRequest<InstructorQuery> {
    
    @Schema(description = "教员工号", example = "T001")
    private String instructorCode;

    @Schema(description = "教员姓名", example = "李教员")
    private String instructorName;

    @Schema(description = "性别（0男 1女 2未知）", example = "0", allowableValues = {"0", "1", "2"})
    private String gender;

    @Schema(description = "手机号码", example = "13800138000")
    private String phoneNumber;

    @Schema(description = "部门", example = "飞行部")
    private String department;

    @Schema(description = "职位", example = "高级飞行教员")
    private String position;

    @Schema(description = "技术职称", example = "高级工程师")
    private String technicalTitle;

    @Schema(description = "教员等级（1初级 2中级 3高级 4专家）", example = "3", allowableValues = {"1", "2", "3", "4"})
    private String instructorLevel;

    @Schema(description = "学历（1中专 2大专 3本科 4硕士 5博士）", example = "4", allowableValues = {"1", "2", "3", "4", "5"})
    private String education;

    @Schema(description = "授课状态（0可授课 1授课中 2暂停授课）", example = "0", allowableValues = {"0", "1", "2"})
    private String teachingStatus;

    @Schema(description = "状态（0正常 1停用）", example = "0", allowableValues = {"0", "1"})
    private String status;

    @Schema(description = "入职开始时间", example = "2020-01-01")
    private Date hireStartDate;

    @Schema(description = "入职结束时间", example = "2024-12-31")
    private Date hireEndDate;

    @Schema(description = "从教开始时间", example = "2020-06-01")
    private Date teachingStartDateBegin;

    @Schema(description = "从教结束时间", example = "2024-12-31")
    private Date teachingStartDateEnd;

    @Schema(description = "出生开始日期", example = "1980-01-01")
    private Date birthStartDate;

    @Schema(description = "出生结束日期", example = "1990-12-31")
    private Date birthEndDate;
}