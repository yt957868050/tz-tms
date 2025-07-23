package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 培训类型管理 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class TrainingTypeExcel {

    @ExcelProperty(value = "培训类型ID", index = 0)
    @ColumnWidth(15)
    private Long trainingTypeId;

    @ExcelProperty(value = "培训类型名称", index = 1)
    @ColumnWidth(20)
    private String trainingTypeName;

    @ExcelProperty(value = "培训类型代码", index = 2)
    @ColumnWidth(18)
    private String trainingTypeCode;

    @ExcelProperty(value = "培训方式", index = 3)
    @ColumnWidth(12)
    private String trainingMethodName;

    @ExcelProperty(value = "培训类型描述", index = 4)
    @ColumnWidth(30)
    private String trainingTypeDesc;

    @ExcelProperty(value = "状态", index = 5)
    @ColumnWidth(10)
    private String statusName;

    @ExcelProperty(value = "排序", index = 6)
    @ColumnWidth(10)
    private Integer orderNum;

    @ExcelProperty(value = "创建时间", index = 7)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "备注", index = 8)
    @ColumnWidth(30)
    private String remark;
}