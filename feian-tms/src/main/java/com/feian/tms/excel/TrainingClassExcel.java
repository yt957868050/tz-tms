package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 培训班次管理 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class TrainingClassExcel {

    @ExcelProperty(value = "培训班次ID", index = 0)
    @ColumnWidth(15)
    private Long trainingClassId;

    @ExcelProperty(value = "班次编号", index = 1)
    @ColumnWidth(20)
    private String classCode;

    @ExcelProperty(value = "班次名称", index = 2)
    @ColumnWidth(25)
    private String className;

    @ExcelProperty(value = "培训计划名称", index = 3)
    @ColumnWidth(25)
    private String trainingPlanName;

    @ExcelProperty(value = "机型名称", index = 4)
    @ColumnWidth(15)
    private String machineTypeName;

    @ExcelProperty(value = "专业名称", index = 5)
    @ColumnWidth(15)
    private String majorName;

    @ExcelProperty(value = "培训能力", index = 6)
    @ColumnWidth(15)
    private String trainingAbilityName;

    @ExcelProperty(value = "计划开始时间", index = 7)
    @ColumnWidth(18)
    private Date planStartTime;

    @ExcelProperty(value = "计划结束时间", index = 8)
    @ColumnWidth(18)
    private Date planEndTime;

    @ExcelProperty(value = "实际开始时间", index = 9)
    @ColumnWidth(18)
    private Date actualStartTime;

    @ExcelProperty(value = "实际结束时间", index = 10)
    @ColumnWidth(18)
    private Date actualEndTime;

    @ExcelProperty(value = "计划人数", index = 11)
    @ColumnWidth(12)
    private Integer planStudentCount;

    @ExcelProperty(value = "实际人数", index = 12)
    @ColumnWidth(12)
    private Integer actualStudentCount;

    @ExcelProperty(value = "主要教员", index = 13)
    @ColumnWidth(15)
    private String primaryInstructorName;

    @ExcelProperty(value = "班次描述", index = 14)
    @ColumnWidth(30)
    private String classDesc;

    @ExcelProperty(value = "状态", index = 15)
    @ColumnWidth(12)
    private String statusName;

    @ExcelProperty(value = "创建时间", index = 16)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "备注", index = 17)
    @ColumnWidth(30)
    private String remark;
}