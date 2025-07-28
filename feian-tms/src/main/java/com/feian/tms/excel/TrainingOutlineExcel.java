package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训大纲管理 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class TrainingOutlineExcel {

    @ExcelProperty(value = "大纲ID", index = 0)
    @ColumnWidth(15)
    private Long outlineId;

    @ExcelProperty(value = "大纲名称", index = 1)
    @ColumnWidth(25)
    private String outlineName;

    @ExcelProperty(value = "大纲编码", index = 2)
    @ColumnWidth(20)
    private String outlineCode;

    @ExcelProperty(value = "机型", index = 3)
    @ColumnWidth(15)
    private String machineTypeName;

    @ExcelProperty(value = "专业", index = 4)
    @ColumnWidth(15)
    private String majorName;

    @ExcelProperty(value = "培训能力", index = 5)
    @ColumnWidth(15)
    private String trainingAbilityName;

    @ExcelProperty(value = "理论课时", index = 6)
    @ColumnWidth(12)
    private BigDecimal theoryHours;

    @ExcelProperty(value = "实作课时", index = 7)
    @ColumnWidth(12)
    private BigDecimal practicalHours;

    @ExcelProperty(value = "总课时", index = 8)
    @ColumnWidth(12)
    private BigDecimal totalHours;

    @ExcelProperty(value = "版本号", index = 9)
    @ColumnWidth(15)
    private String version;

    @ExcelProperty(value = "生效日期", index = 10)
    @ColumnWidth(15)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date effectiveDate;

    @ExcelProperty(value = "失效日期", index = 11)
    @ColumnWidth(15)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;

    @ExcelProperty(value = "状态", index = 12)
    @ColumnWidth(10)
    private String statusName;

    @ExcelProperty(value = "培训规范文件", index = 13)
    @ColumnWidth(25)
    private String regulationFileName;

    @ExcelProperty(value = "大纲文件", index = 14)
    @ColumnWidth(25)
    private String outlineFileName;

    @ExcelProperty(value = "描述", index = 15)
    @ColumnWidth(35)
    private String description;

    @ExcelProperty(value = "创建人", index = 16)
    @ColumnWidth(15)
    private String createBy;

    @ExcelProperty(value = "创建时间", index = 17)
    @ColumnWidth(20)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ExcelProperty(value = "备注", index = 18)
    @ColumnWidth(30)
    private String remark;
}