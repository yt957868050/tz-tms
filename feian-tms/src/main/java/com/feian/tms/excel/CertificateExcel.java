package com.feian.tms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 证书管理 Excel 导出实体
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
public class CertificateExcel {

    @ExcelProperty(value = "证书ID", index = 0)
    @ColumnWidth(15)
    private Long certificateId;

    @ExcelProperty(value = "证书编号", index = 1)
    @ColumnWidth(20)
    private String certificateCode;

    @ExcelProperty(value = "证书名称", index = 2)
    @ColumnWidth(25)
    private String certificateName;

    @ExcelProperty(value = "学员姓名", index = 3)
    @ColumnWidth(15)
    private String studentName;

    @ExcelProperty(value = "班次名称", index = 4)
    @ColumnWidth(20)
    private String className;

    @ExcelProperty(value = "机型名称", index = 5)
    @ColumnWidth(15)
    private String machineTypeName;

    @ExcelProperty(value = "专业名称", index = 6)
    @ColumnWidth(15)
    private String majorName;

    @ExcelProperty(value = "培训能力", index = 7)
    @ColumnWidth(15)
    private String trainingAbilityName;

    @ExcelProperty(value = "证书类型", index = 8)
    @ColumnWidth(15)
    private String certificateTypeName;

    @ExcelProperty(value = "颁发日期", index = 9)
    @ColumnWidth(15)
    private Date issueDate;

    @ExcelProperty(value = "有效期开始", index = 10)
    @ColumnWidth(15)
    private Date validFrom;

    @ExcelProperty(value = "有效期结束", index = 11)
    @ColumnWidth(15)
    private Date validUntil;

    @ExcelProperty(value = "颁发机构", index = 12)
    @ColumnWidth(20)
    private String issueOrganization;

    @ExcelProperty(value = "签发人", index = 13)
    @ColumnWidth(15)
    private String issuer;

    @ExcelProperty(value = "证书状态", index = 14)
    @ColumnWidth(12)
    private String certificateStatusName;

    @ExcelProperty(value = "作废原因", index = 15)
    @ColumnWidth(25)
    private String voidReason;

    @ExcelProperty(value = "作废时间", index = 16)
    @ColumnWidth(20)
    private Date voidTime;

    @ExcelProperty(value = "创建时间", index = 17)
    @ColumnWidth(20)
    private Date createTime;

    @ExcelProperty(value = "备注", index = 18)
    @ColumnWidth(30)
    private String remark;
}