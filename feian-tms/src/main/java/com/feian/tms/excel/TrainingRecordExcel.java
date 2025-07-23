package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训记录 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class TrainingRecordExcel {

    @ExcelProperty(value = "培训记录ID", index = 0)
    @ColumnWidth(15)
    private Long trainingRecordId;

    @ExcelProperty(value = "学员姓名", index = 1)
    @ColumnWidth(15)
    private String studentName;

    @ExcelProperty(value = "班次名称", index = 2)
    @ColumnWidth(20)
    private String className;

    @ExcelProperty(value = "课程名称", index = 3)
    @ColumnWidth(25)
    private String courseName;

    @ExcelProperty(value = "教员姓名", index = 4)
    @ColumnWidth(15)
    private String instructorName;

    @ExcelProperty(value = "培训日期", index = 5)
    @ColumnWidth(15)
    private Date trainingDate;

    @ExcelProperty(value = "培训类型", index = 6)
    @ColumnWidth(12)
    private String trainingMethodName;

    @ExcelProperty(value = "培训时长", index = 7)
    @ColumnWidth(12)
    private BigDecimal trainingHours;

    @ExcelProperty(value = "出勤状态", index = 8)
    @ColumnWidth(12)
    private String attendanceStatusName;

    @ExcelProperty(value = "课堂表现评分", index = 9)
    @ColumnWidth(15)
    private BigDecimal performanceScore;

    @ExcelProperty(value = "作业成绩", index = 10)
    @ColumnWidth(12)
    private BigDecimal homeworkScore;

    @ExcelProperty(value = "考试成绩", index = 11)
    @ColumnWidth(12)
    private BigDecimal examScore;

    @ExcelProperty(value = "综合评分", index = 12)
    @ColumnWidth(12)
    private BigDecimal totalScore;

    @ExcelProperty(value = "培训内容", index = 13)
    @ColumnWidth(35)
    private String trainingContent;

    @ExcelProperty(value = "学习心得", index = 14)
    @ColumnWidth(35)
    private String learningNotes;

    @ExcelProperty(value = "教员评价", index = 15)
    @ColumnWidth(35)
    private String instructorComment;

    @ExcelProperty(value = "培训效果", index = 16)
    @ColumnWidth(12)
    private String trainingEffectName;

    @ExcelProperty(value = "创建时间", index = 17)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "备注", index = 18)
    @ColumnWidth(30)
    private String remark;
}