package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 教员信息响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "教员信息响应对象")
public class InstructorResponse {
    
    @Schema(description = "教员ID", example = "1")
    private Long instructorId;

    @Schema(description = "教员工号", example = "T001")
    private String instructorCode;

    @Schema(description = "教员姓名", example = "李教员")
    private String instructorName;

    @Schema(description = "性别（0男 1女 2未知）", example = "0")
    private String gender;

    @Schema(description = "出生日期", example = "1980-01-01")
    private Date birthDate;

    @Schema(description = "身份证号", example = "110101198001011234")
    private String idCard;

    @Schema(description = "手机号码", example = "13800138000")
    private String phoneNumber;

    @Schema(description = "邮箱", example = "teacher@example.com")
    private String email;

    @Schema(description = "部门", example = "飞行部")
    private String department;

    @Schema(description = "职位", example = "高级飞行教员")
    private String position;

    @Schema(description = "入职时间", example = "2020-01-01")
    private Date hireDate;

    @Schema(description = "教员等级（1初级 2中级 3高级 4专家）", example = "3")
    private String instructorLevel;

    @Schema(description = "从教时间", example = "2020-06-01")
    private Date teachingStartDate;

    @Schema(description = "学历（1中专 2大专 3本科 4硕士 5博士）", example = "4")
    private String education;

    @Schema(description = "专业技术职务", example = "高级工程师")
    private String technicalTitle;

    @Schema(description = "授课状态（0可授课 1授课中 2暂停授课）", example = "0")
    private String teachingStatus;

    @Schema(description = "头像路径", example = "/upload/avatar/default.jpg")
    private String avatar;

    @Schema(description = "个人简介", example = "资深飞行教员，具有丰富的教学经验")
    private String biography;

    @Schema(description = "状态（0正常 1停用）", example = "0")
    private String status;

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

    @Schema(description = "教员授课能力列表")
    private List<InstructorAbilityResponse> instructorAbilityList;
}