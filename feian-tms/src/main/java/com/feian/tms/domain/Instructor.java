package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 教员信息对象 tms_instructor
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_instructor")
public class Instructor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 教员ID */
    @TableId(type = IdType.AUTO)
    private Long instructorId;

    /** 教员工号 */
    @Excel(name = "教员工号")
    private String instructorCode;

    /** 教员姓名 */
    @Excel(name = "教员姓名")
    private String instructorName;

    /** 性别（0男 1女 2未知） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女")
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

    /** 教员等级（1初级 2中级 3高级 4专家） */
    @Excel(name = "教员等级", readConverterExp = "1=初级,2=中级,3=高级,4=专家")
    private String instructorLevel;

    /** 从教时间 */
    @Excel(name = "从教时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date teachingStartDate;

    /** 学历（1中专 2大专 3本科 4硕士 5博士） */
    @Excel(name = "学历", readConverterExp = "1=中专,2=大专,3=本科,4=硕士,5=博士")
    private String education;

    /** 专业技术职务 */
    @Excel(name = "专业技术职务")
    private String technicalTitle;

    /** 授课状态（0可授课 1授课中 2暂停授课） */
    @Excel(name = "授课状态", readConverterExp = "0=可授课,1=授课中,2=暂停授课")
    private String teachingStatus;

    /** 头像路径 */
    @Excel(name = "头像路径")
    private String avatar;

    /** 个人简介 */
    @Excel(name = "个人简介")
    private String biography;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**用户id **/
    private Long userId;

    /** 教员授课能力列表 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private List<InstructorAbility> instructorAbilityList;
}