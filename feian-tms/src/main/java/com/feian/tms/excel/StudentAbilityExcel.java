package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 学员学习能力 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class StudentAbilityExcel {

    @ExcelProperty(value = "学习能力ID", index = 0)
    @ColumnWidth(15)
    private Long studentAbilityId;

    @ExcelProperty(value = "学员姓名", index = 1)
    @ColumnWidth(15)
    private String studentName;

    @ExcelProperty(value = "机型名称", index = 2)
    @ColumnWidth(15)
    private String machineTypeName;

    @ExcelProperty(value = "专业名称", index = 3)
    @ColumnWidth(15)
    private String majorName;

    @ExcelProperty(value = "培训能力", index = 4)
    @ColumnWidth(15)
    private String trainingAbilityName;

    @ExcelProperty(value = "课程名称", index = 5)
    @ColumnWidth(25)
    private String courseName;

    @ExcelProperty(value = "学习进度", index = 6)
    @ColumnWidth(12)
    private String learningProgressName;

    @ExcelProperty(value = "理论成绩", index = 7)
    @ColumnWidth(12)
    private BigDecimal theoryScore;

    @ExcelProperty(value = "实践成绩", index = 8)
    @ColumnWidth(12)
    private BigDecimal practiceScore;

    @ExcelProperty(value = "综合成绩", index = 9)
    @ColumnWidth(12)
    private BigDecimal totalScore;

    @ExcelProperty(value = "学习开始时间", index = 10)
    @ColumnWidth(18)
    private Date learningStartTime;

    @ExcelProperty(value = "学习完成时间", index = 11)
    @ColumnWidth(18)
    private Date learningEndTime;

    @ExcelProperty(value = "资质状态", index = 12)
    @ColumnWidth(12)
    private String qualificationStatusName;

    @ExcelProperty(value = "资质获得时间", index = 13)
    @ColumnWidth(18)
    private Date qualificationDate;

    @ExcelProperty(value = "资质有效期至", index = 14)
    @ColumnWidth(18)
    private Date validUntil;

    @ExcelProperty(value = "状态", index = 15)
    @ColumnWidth(10)
    private String statusName;

    @ExcelProperty(value = "创建时间", index = 16)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "学习备注", index = 17)
    @ColumnWidth(30)
    private String learningRemark;
}