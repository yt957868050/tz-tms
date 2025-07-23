package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 教员信息 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class InstructorExcel {

    @ExcelProperty(value = "教员ID", index = 0)
    @ColumnWidth(15)
    private Long instructorId;

    @ExcelProperty(value = "教员工号", index = 1)
    @ColumnWidth(15)
    private String instructorCode;

    @ExcelProperty(value = "教员姓名", index = 2)
    @ColumnWidth(15)
    private String instructorName;

    @ExcelProperty(value = "性别", index = 3)
    @ColumnWidth(10)
    private String genderName;

    @ExcelProperty(value = "出生日期", index = 4)
    @ColumnWidth(15)
    private Date birthDate;

    @ExcelProperty(value = "身份证号", index = 5)
    @ColumnWidth(20)
    private String idCard;

    @ExcelProperty(value = "手机号码", index = 6)
    @ColumnWidth(15)
    private String phoneNumber;

    @ExcelProperty(value = "邮箱", index = 7)
    @ColumnWidth(25)
    private String email;

    @ExcelProperty(value = "部门", index = 8)
    @ColumnWidth(20)
    private String department;

    @ExcelProperty(value = "职位", index = 9)
    @ColumnWidth(15)
    private String position;

    @ExcelProperty(value = "入职时间", index = 10)
    @ColumnWidth(15)
    private Date hireDate;

    @ExcelProperty(value = "教员等级", index = 11)
    @ColumnWidth(12)
    private String instructorLevelName;

    @ExcelProperty(value = "从教时间", index = 12)
    @ColumnWidth(15)
    private Date teachingStartDate;

    @ExcelProperty(value = "学历", index = 13)
    @ColumnWidth(10)
    private String educationName;

    @ExcelProperty(value = "专业技术职务", index = 14)
    @ColumnWidth(18)
    private String technicalTitle;

    @ExcelProperty(value = "授课状态", index = 15)
    @ColumnWidth(12)
    private String teachingStatusName;

    @ExcelProperty(value = "个人简介", index = 16)
    @ColumnWidth(35)
    private String biography;

    @ExcelProperty(value = "状态", index = 17)
    @ColumnWidth(10)
    private String statusName;

    @ExcelProperty(value = "创建时间", index = 18)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "备注", index = 19)
    @ColumnWidth(30)
    private String remark;
}