package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 班次学员关联 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class ClassStudentExcel {

    @ExcelProperty(value = "关联ID", index = 0)
    @ColumnWidth(15)
    private Long classStudentId;

    @ExcelProperty(value = "班次编号", index = 1)
    @ColumnWidth(20)
    private String classCode;

    @ExcelProperty(value = "班次名称", index = 2)
    @ColumnWidth(25)
    private String className;

    @ExcelProperty(value = "学员工号", index = 3)
    @ColumnWidth(15)
    private String studentCode;

    @ExcelProperty(value = "学员姓名", index = 4)
    @ColumnWidth(15)
    private String studentName;

    @ExcelProperty(value = "报名时间", index = 5)
    @ColumnWidth(20)
    private Date enrollTime;

    @ExcelProperty(value = "学员状态", index = 6)
    @ColumnWidth(12)
    private String studentStatusName;

    @ExcelProperty(value = "退班原因", index = 7)
    @ColumnWidth(25)
    private String withdrawReason;

    @ExcelProperty(value = "退班时间", index = 8)
    @ColumnWidth(20)
    private Date withdrawTime;

    @ExcelProperty(value = "创建时间", index = 9)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "备注", index = 10)
    @ColumnWidth(30)
    private String remark;
}