package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 学员信息对象 tms_student
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_student")
public class Student extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学员ID */
    @TableId(type = IdType.AUTO)
    private Long studentId;

    /** 关联的用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 学员工号 */
    @Excel(name = "学员工号")
    private String studentCode;

    /** 学员姓名 */
    @Excel(name = "学员姓名")
    private String studentName;

    /** 性别（0男 1女 2未知） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String gender;

    /** 出生日期 */
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 部门 */
    @Excel(name = "部门")
    private String department;

    /** 职位 */
    @Excel(name = "职位")
    private String position;

    /** 入职时间 */
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hireDate;

    /** 入学时间 */
    @Excel(name = "入学时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrollmentDate;

    /** 主要机型ID */
    @Excel(name = "主要机型ID")
    private Long primaryMachineTypeId;

    /** 主要专业ID */
    @Excel(name = "主要专业ID")
    private Long primaryMajorId;

    /** 学历（1中专 2大专 3本科 4硕士 5博士） */
    @Excel(name = "学历", readConverterExp = "1=中专,2=大专,3=本科,4=硕士,5=博士")
    private String education;

    /** 培训状态（0待培训 1培训中 2已培训 3培训暂停） */
    @Excel(name = "培训状态", readConverterExp = "0=待培训,1=培训中,2=已培训,3=培训暂停")
    private String trainingStatus;

    /** 头像路径 */
    @Excel(name = "头像路径")
    private String avatar;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 主要机型名称 */
    private String primaryMachineTypeName;

    /** 主要专业名称 */
    private String primaryMajorName;




}