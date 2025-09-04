package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 证书管理 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class CertificateExcel {


    /** 证书编号 */
    @ExcelProperty(value = "证书编号", index = 0)
    @ColumnWidth(20)
    private String certificateCode;


    /** 学生编号 */
    @ExcelProperty(value = "学生编号", index = 1)
    @ColumnWidth(20)
    private String studentCode;

    /** 学生姓名 */
    @ExcelProperty(value = "学生姓名", index = 2)
    @ColumnWidth(15)
    private String studentName;

    /** 学生英文姓名 */
    @ExcelProperty(value = "学生英文姓名", index = 3)
    @ColumnWidth(20)
    private String engStudentName;

    /** 课程名称 */
    @ExcelProperty(value = "课程名称", index = 4)
    @ColumnWidth(25)
    private String trainingCourse;

    /** 课程英文名称 */
    @ExcelProperty(value = "课程英文姓名", index = 5)
    @ColumnWidth(25)
    private String engTrainingCourse;

    /** 开始日期 */
    @ExcelProperty(value = "开始日期", index = 6)
    @ColumnWidth(30)
    private String startDate;

    /** 结束日期 */
    @ExcelProperty(value = "结束日期", index = 7)
    @ColumnWidth(30)
    private String endDate;


}