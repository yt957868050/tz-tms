package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训计划管理 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class TrainingPlanExcel {

    @ExcelProperty(value = "培训计划ID", index = 0)
    @ColumnWidth(15)
    private Long trainingPlanId;

    @ExcelProperty(value = "培训计划名称", index = 1)
    @ColumnWidth(25)
    private String trainingPlanName;

    @ExcelProperty(value = "培训计划编号", index = 2)
    @ColumnWidth(20)
    private String trainingPlanCode;

    @ExcelProperty(value = "机型名称", index = 3)
    @ColumnWidth(15)
    private String machineTypeName;

    @ExcelProperty(value = "专业名称", index = 4)
    @ColumnWidth(15)
    private String majorName;

    @ExcelProperty(value = "培训能力", index = 5)
    @ColumnWidth(15)
    private String trainingAbilityName;

    @ExcelProperty(value = "计划开始时间", index = 6)
    @ColumnWidth(18)
    private Date planStartTime;

    @ExcelProperty(value = "计划结束时间", index = 7)
    @ColumnWidth(18)
    private Date planEndTime;

    @ExcelProperty(value = "总理论学时", index = 8)
    @ColumnWidth(12)
    private BigDecimal totalTheoryHours;

    @ExcelProperty(value = "总实践学时", index = 9)
    @ColumnWidth(12)
    private BigDecimal totalPracticeHours;

    @ExcelProperty(value = "计划人数", index = 10)
    @ColumnWidth(12)
    private Integer planStudentCount;

    @ExcelProperty(value = "培训计划描述", index = 11)
    @ColumnWidth(30)
    private String trainingPlanDesc;

    @ExcelProperty(value = "状态", index = 12)
    @ColumnWidth(12)
    private String statusName;

    @ExcelProperty(value = "创建时间", index = 13)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "备注", index = 14)
    @ColumnWidth(30)
    private String remark;
}