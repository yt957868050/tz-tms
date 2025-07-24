package com.feian.tms.domain;


import com.baomidou.mybatisplus.annotation.IdType;
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

    /** 证书名称 */
    @Excel(name = "证书名称")
    private String certificateName;

    /** 学员ID */
    @Excel(name = "学员ID")
    private Long studentId;

    /** 培训班次ID */
    @Excel(name = "培训班次ID")
    private Long trainingClassId;

    /** 机型ID */
    @Excel(name = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long majorId;

    /** 培训能力ID */
    @Excel(name = "培训能力ID")
    private Long trainingAbilityId;

    /** 证书类型（1培训证书 2复训证书 3恢复考试证书） */
    @Excel(name = "证书类型", readConverterExp = "1=培训证书,2=复训证书,3=恢复考试证书")
    private String certificateType;

    /** 颁发日期 */
    @Excel(name = "颁发日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueDate;

    /** 有效期开始 */
    @Excel(name = "有效期开始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validFrom;

    /** 有效期结束 */
    @Excel(name = "有效期结束", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validUntil;

    /** 颁发机构 */
    @Excel(name = "颁发机构")
    private String issueOrganization;

    /** 签发人 */
    @Excel(name = "签发人")
    private String issuer;

    /** 证书模板路径 */
    @Excel(name = "证书模板路径")
    private String templatePath;

    /** 证书文件路径 */
    @Excel(name = "证书文件路径")
    private String certificateFilePath;

    /** 证书状态（0草稿 1已颁发 2已作废 3已过期） */
    @Excel(name = "证书状态", readConverterExp = "0=草稿,1=已颁发,2=已作废,3=已过期")
    private String certificateStatus;

    /** 作废原因 */
    @Excel(name = "作废原因")
    private String voidReason;

    /** 作废时间 */
    @Excel(name = "作废时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date voidTime;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 学员姓名 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String studentName;

    /** 班次名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String className;

    /** 机型名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String machineTypeName;

    /** 专业名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String majorName;

    /** 培训能力名称 */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String trainingAbilityName;

    /** 证书描述 */
    private String certificateDescription;

    /** 备注 */
    private String remark;



}