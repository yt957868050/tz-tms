package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 教员信息请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "教员信息请求对象")
public class InstructorRequest {
    
    @Schema(description = "教员ID", example = "1")
    private Long instructorId;

    @Schema(description = "教员工号", example = "T001", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "教员工号不能为空")
    private String instructorCode;

    @Schema(description = "教员姓名", example = "李教员", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "教员姓名不能为空")
    private String instructorName;

    @Schema(description = "性别", example = "0", allowableValues = {"0", "1", "2"})
    private String gender;

    @Schema(description = "出生日期", example = "1980-01-01")
    private Date birthDate;

    @Schema(description = "身份证号", example = "110101198001011234")
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", 
             message = "身份证号格式不正确")
    private String idCard;

    @Schema(description = "手机号码", example = "13800138000")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phoneNumber;

    @Schema(description = "邮箱", example = "teacher@example.com")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "部门", example = "飞行部")
    private String department;

    @Schema(description = "职位", example = "高级飞行教员")
    private String position;

    @Schema(description = "入职时间", example = "2020-01-01")
    private Date hireDate;

    @Schema(description = "教员等级", example = "3", allowableValues = {"1", "2", "3", "4"})
    private String instructorLevel;

    @Schema(description = "从教时间", example = "2020-06-01")
    private Date teachingStartDate;

    @Schema(description = "学历", example = "4", allowableValues = {"1", "2", "3", "4", "5"})
    private String education;

    @Schema(description = "专业技术职务", example = "高级工程师")
    private String technicalTitle;

    @Schema(description = "授课状态", example = "0", allowableValues = {"0", "1", "2"})
    private String teachingStatus;

    @Schema(description = "头像路径", example = "/upload/avatar/default.jpg")
    private String avatar;

    @Schema(description = "个人简介", example = "资深飞行教员，具有丰富的教学经验")
    private String biography;

    @Schema(description = "状态", example = "0", allowableValues = {"0", "1"})
    private String status;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

    @Schema(description = "教员授课能力列表")
    private List<InstructorAbilityRequest> instructorAbilityList;
}