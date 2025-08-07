package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 学员信息 Excel 导入导出模板
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class StudentExcel {
    @ExcelProperty(value = "学员姓名", index = 0)
    @ColumnWidth(15)
    private String studentName;

    @ExcelProperty(value = "学员工号", index = 1)
    @ColumnWidth(15)
    private String studentCode;

    @ExcelProperty(value = "性别", index = 2)
    @ColumnWidth(10)
    private String gender;

    @ExcelProperty(value = "专业", index = 3)
    @ColumnWidth(10)
    private Long primaryMajorId;

    @ExcelProperty(value = "所学专业", index = 4)
    @ColumnWidth(15)
    private String major;

    @ExcelProperty(value = "民族", index = 5)
    @ColumnWidth(10)
    private String nation;

    @ExcelProperty(value = "出生年月", index = 6)
    @ColumnWidth(15)
    private Date birthDate;

    @ExcelProperty(value = "身份证号", index = 7)
    @ColumnWidth(20)
    private String idCard;

    @ExcelProperty(value = "参加工作时间", index = 8)
    @ColumnWidth(15)
    private Date workStartDate;

    @ExcelProperty(value = "工作单位", index = 9)
    @ColumnWidth(20)
    private String workUnit;

    @ExcelProperty(value = "文化程度", index = 10)
    @ColumnWidth(10)
    private String educationLevel;

    @ExcelProperty(value = "毕业院校", index = 11)
    @ColumnWidth(20)
    private String graduateSchool;

    @ExcelProperty(value = "所学专业", index = 12)
    @ColumnWidth(15)
    private String majorStudied;

    @ExcelProperty(value = "英语水平", index = 13)
    @ColumnWidth(10)
    private String englishLevel;

    @ExcelProperty(value = "诚信记录分", index = 14)
    @ColumnWidth(10)
    private Integer integrityScore;

    @ExcelProperty(value = "执照类型", index = 15)
    @ColumnWidth(15)
    private String licenseType;

    @ExcelProperty(value = "执照编号", index = 16)
    @ColumnWidth(15)
    private String licenseNumber;

    @ExcelProperty(value = "机型签署情况", index = 17)
    @ColumnWidth(20)
    private String aircraftEndorsement;

    @ExcelProperty(value = "工作经历", index = 18)
    @ColumnWidth(30)
    private String workExperience;
}