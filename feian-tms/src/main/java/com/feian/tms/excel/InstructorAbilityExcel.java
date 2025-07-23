package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 教员授课能力 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class InstructorAbilityExcel {

    @ExcelProperty(value = "授课能力ID", index = 0)
    @ColumnWidth(15)
    private Long instructorAbilityId;

    @ExcelProperty(value = "教员姓名", index = 1)
    @ColumnWidth(15)
    private String instructorName;

    @ExcelProperty(value = "机型名称", index = 2)
    @ColumnWidth(15)
    private String machineTypeName;

    @ExcelProperty(value = "专业名称", index = 3)
    @ColumnWidth(15)
    private String majorName;

    @ExcelProperty(value = "培训类型", index = 4)
    @ColumnWidth(15)
    private String trainingTypeName;

    @ExcelProperty(value = "课程名称", index = 5)
    @ColumnWidth(25)
    private String courseName;

    @ExcelProperty(value = "授课资质等级", index = 6)
    @ColumnWidth(15)
    private String abilityLevelName;

    @ExcelProperty(value = "资质获得时间", index = 7)
    @ColumnWidth(18)
    private Date qualificationDate;

    @ExcelProperty(value = "资质有效期至", index = 8)
    @ColumnWidth(18)
    private Date validUntil;

    @ExcelProperty(value = "授课次数", index = 9)
    @ColumnWidth(12)
    private Integer teachingCount;

    @ExcelProperty(value = "授课评分", index = 10)
    @ColumnWidth(12)
    private Float teachingScore;

    @ExcelProperty(value = "是否主讲", index = 11)
    @ColumnWidth(12)
    private String isPrimaryName;

    @ExcelProperty(value = "状态", index = 12)
    @ColumnWidth(10)
    private String statusName;

    @ExcelProperty(value = "创建时间", index = 13)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "能力备注", index = 14)
    @ColumnWidth(30)
    private String abilityRemark;
}