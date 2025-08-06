package com.feian.tms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

/**
 * 学员信息请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "学员信息请求对象")
public class StudentRequest {
    
    @Schema(description = "学员ID", example = "1")
    private Long studentId;

    @Schema(description = "学员工号", example = "S001", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "学员工号不能为空")
    private String studentCode;

    @Schema(description = "学员姓名", example = "张三", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "学员姓名不能为空")
    private String studentName;

    @Schema(description = "性别", example = "0", allowableValues = {"0", "1", "2"})
    private String gender;

    @Schema(description = "出生日期", example = "1990-01-01")
    private Date birthDate;

    @Schema(description = "身份证号", example = "110101199001011234")
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", 
             message = "身份证号格式不正确")
    private String idCard;

    @Schema(description = "手机号码", example = "13800138000")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phoneNumber;

    @Schema(description = "邮箱", example = "zhangsan@example.com")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "部门", example = "飞行部")
    private String department;

    @Schema(description = "职位", example = "见习飞行员")
    private String position;

    @Schema(description = "入职时间", example = "2024-01-01")
    private Date hireDate;
    @Schema(description = "入学时间", example = "2024-01-01")

    private Date enrollmentDate;


    @Schema(description = "主要机型ID", example = "1")
    private Long primaryMachineTypeId;

    @Schema(description = "主要专业ID", example = "1")
    private Long primaryMajorId;

    @Schema(description = "学历", example = "3", allowableValues = {"1", "2", "3", "4", "5"})
    private String education;

    @Schema(description = "培训状态", example = "0", allowableValues = {"0", "1", "2", "3"})
    private String trainingStatus;

    @Schema(description = "头像路径", example = "/upload/avatar/default.jpg")
    private String avatar;

    @Schema(description = "状态", example = "0", allowableValues = {"0", "1"})
    private String status;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

    @Schema(description = "民族", example = "汉族")
    private String nation;

    @Schema(description = "参加工作时间", example = "2015-09-01")
    private Date workStartDate;

    @Schema(description = "工作单位", example = "XX航空公司")
    private String workUnit;

    @Schema(description = "文化程度", example = "本科")
    private String educationLevel;

    @Schema(description = "毕业院校", example = "XX航空航天大学")
    private String graduateSchool;

    @Schema(description = "所学专业", example = "飞行技术")
    private String major;

    @Schema(description = "英语水平", example = "CET-4")
    private String englishLevel;

    @Schema(description = "诚信记录分", example = "100")
    private Integer integrityScore;

    @Schema(description = "执照类型", example = "私用驾驶员执照")
    private String licenseType;

    @Schema(description = "执照编号", example = "A1234567")
    private String licenseNumber;

    @Schema(description = "机型签署情况", example = "波音737、空客A320")
    private String aircraftEndorsement;
}