package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 专业管理 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class MajorExcel {

    @ExcelProperty(value = "专业ID", index = 0)
    @ColumnWidth(15)
    private Long majorId;

    @ExcelProperty(value = "专业名称", index = 1)
    @ColumnWidth(20)
    private String majorName;

    @ExcelProperty(value = "专业代码", index = 2)
    @ColumnWidth(15)
    private String majorCode;

    @ExcelProperty(value = "专业类型", index = 3)
    @ColumnWidth(15)
    private String majorTypeName;

    @ExcelProperty(value = "专业描述", index = 4)
    @ColumnWidth(30)
    private String majorDesc;

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