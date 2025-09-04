package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Builder;
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
@Builder
@HeadRowHeight(20)
@ContentRowHeight(18)
public class TrainingRecordExcel {

    /** 班次ID */
    @ExcelProperty(value = "培训班次", index = 0)
    @ColumnWidth(12)
    private String className;

    /** 学员姓名 */
    @ExcelProperty(value = "学员姓名", index = 1)
    @ColumnWidth(15)
    private String studentName;

    /** 机型名称 */
    @ExcelProperty(value = "机型名称", index = 2)
    @ColumnWidth(15)
    private String machineTypeName;

    /** 专业名称 */
    @ExcelProperty(value = "专业名称", index = 3)
    @ColumnWidth(15)
    private String majorName;

    /** 培训能力名称 */
    @ExcelProperty(value = "培训能力名称", index = 4)
    @ColumnWidth(20)
    private String trainingAbilityName;

    /** 理论培训时长 */
    @ExcelProperty(value = "理论培训时长（小时）", index = 5)
    @ColumnWidth(30)
    private Integer theoryHour;

    /** 实作培训时长 */
    @ExcelProperty(value = "实作培训时长（小时）", index = 6)
    @ColumnWidth(30)
    private Integer practiceHour;

    /** 培训总时长 */
    @ExcelProperty(value = "培训总时长（小时）", index = 7)
    @ColumnWidth(30)
    private Integer totalHour;

    /** 理论成绩 */
    @ExcelProperty(value = "理论成绩", index = 8)
    @ColumnWidth(12)
    private Integer theoryScore;

    /** 实作成绩 */
    @ExcelProperty(value = "实作成绩", index = 9)
    @ColumnWidth(12)
    private String practiceScore;


}