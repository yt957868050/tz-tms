package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 学员信息 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class StudentExcel {

    @ExcelProperty(value = "学员ID", index = 0)
    @ColumnWidth(15)
    private Long studentId;

    @ExcelProperty(value = "学员工号", index = 1)
    @ColumnWidth(15)
    private String studentCode;

    @ExcelProperty(value = "学员姓名", index = 2)
    @ColumnWidth(15)
    private String studentName;

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

    @ExcelProperty(value = "主要机型", index = 11)
    @ColumnWidth(15)
    private String primaryMachineTypeName;

    @ExcelProperty(value = "主要专业", index = 12)
    @ColumnWidth(15)
    private String primaryMajorName;

    @ExcelProperty(value = "学历", index = 13)
    @ColumnWidth(10)
    private String educationName;

    @ExcelProperty(value = "培训状态", index = 14)
    @ColumnWidth(12)
    private String trainingStatusName;

    @ExcelProperty(value = "状态", index = 15)
    @ColumnWidth(10)
    private String statusName;

    @ExcelProperty(value = "创建时间", index = 16)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "备注", index = 17)
    @ColumnWidth(30)
    private String remark;
}