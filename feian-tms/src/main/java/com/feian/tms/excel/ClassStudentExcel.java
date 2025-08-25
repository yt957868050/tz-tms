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

    @ExcelProperty(value = "班次编号", index = 0)
    @ColumnWidth(20)
    private String classCode;

    @ExcelProperty(value = "班次名称", index = 1)
    @ColumnWidth(25)
    private String className;

    @ExcelProperty(value = "学员编号", index = 2)
    @ColumnWidth(15)
    private String studentCode;

    @ExcelProperty(value = "学员姓名", index = 3)
    @ColumnWidth(15)
    private String studentName;


}