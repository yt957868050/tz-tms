package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 课件文件管理 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class CoursewareFileExcel {

    @ExcelProperty(value = "课件文件ID", index = 0)
    @ColumnWidth(15)
    private Long coursewareFileId;

    @ExcelProperty(value = "课程名称", index = 1)
    @ColumnWidth(25)
    private String courseName;

    @ExcelProperty(value = "文件名称", index = 2)
    @ColumnWidth(30)
    private String fileName;

    @ExcelProperty(value = "文件类型", index = 3)
    @ColumnWidth(12)
    private String fileTypeName;

    @ExcelProperty(value = "文件路径", index = 4)
    @ColumnWidth(35)
    private String filePath;

    @ExcelProperty(value = "文件大小(KB)", index = 5)
    @ColumnWidth(15)
    private Long fileSize;

    @ExcelProperty(value = "文件扩展名", index = 6)
    @ColumnWidth(12)
    private String fileExtension;

    @ExcelProperty(value = "文件描述", index = 7)
    @ColumnWidth(30)
    private String fileDesc;

    @ExcelProperty(value = "下载次数", index = 8)
    @ColumnWidth(12)
    private Integer downloadCount;

    @ExcelProperty(value = "状态", index = 9)
    @ColumnWidth(10)
    private String statusName;

    @ExcelProperty(value = "排序", index = 10)
    @ColumnWidth(10)
    private Integer orderNum;

    @ExcelProperty(value = "创建时间", index = 11)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "备注", index = 12)
    @ColumnWidth(30)
    private String remark;
}