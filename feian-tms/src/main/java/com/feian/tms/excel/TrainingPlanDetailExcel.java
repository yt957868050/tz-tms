package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训计划明细 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class TrainingPlanDetailExcel {

    @ExcelProperty(value = "计划明细ID", index = 0)
    @ColumnWidth(15)
    private Long trainingPlanDetailId;

    @ExcelProperty(value = "课程名称", index = 1)
    @ColumnWidth(25)
    private String courseName;

    @ExcelProperty(value = "课程编码", index = 2)
    @ColumnWidth(18)
    private String courseCode;

    @ExcelProperty(value = "课程顺序", index = 3)
    @ColumnWidth(12)
    private Integer courseOrder;

    @ExcelProperty(value = "理论学时", index = 4)
    @ColumnWidth(12)
    private BigDecimal theoryHours;

    @ExcelProperty(value = "实践学时", index = 5)
    @ColumnWidth(12)
    private BigDecimal practiceHours;

    @ExcelProperty(value = "是否必修", index = 6)
    @ColumnWidth(12)
    private String isRequiredName;

    @ExcelProperty(value = "课程要求", index = 7)
    @ColumnWidth(35)
    private String courseRequirement;

    @ExcelProperty(value = "创建时间", index = 8)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "备注", index = 9)
    @ColumnWidth(30)
    private String remark;
}