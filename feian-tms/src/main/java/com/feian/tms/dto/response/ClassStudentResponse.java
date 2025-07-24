package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 班次学员关联响应对象
 *
 * @author feian
 * @date 2025-01-24
 */
@Data
@Schema(description = "班次学员关联响应对象")
public class ClassStudentResponse {

    @Schema(description = "班次学员关联ID")
    private Long classStudentId;

    @Schema(description = "培训班次ID")
    private Long trainingClassId;

    @Schema(description = "班次编号")
    private String classCode;

    @Schema(description = "班次名称")
    private String className;

    @Schema(description = "学员ID")
    private Long studentId;

    @Schema(description = "学员编号")
    private String studentCode;

    @Schema(description = "学员姓名")
    private String studentName;

    @Schema(description = "学员身份证号")
    private String idCard;

    @Schema(description = "学员手机号")
    private String phoneNumber;

    @Schema(description = "主要机型名称")
    private String primaryMachineTypeName;

    @Schema(description = "主要专业名称")
    private String primaryMajorName;

    @Schema(description = "报名时间")
    private Date enrollTime;

    @Schema(description = "学员状态（0正常 1请假 2退课 3毕业）")
    private String studentStatus;

    @Schema(description = "学员状态名称")
    private String studentStatusName;

    @Schema(description = "退课原因")
    private String withdrawReason;

    @Schema(description = "退课时间")
    private Date withdrawTime;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "备注")
    private String remark;
}