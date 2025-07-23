package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 学员信息查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "学员信息查询对象")
public class StudentQuery extends PageRequest<StudentQuery> {
    
    @Schema(description = "学员工号", example = "S001")
    private String studentCode;

    @Schema(description = "学员姓名", example = "张三")
    private String studentName;

    @Schema(description = "性别（0男 1女 2未知）", example = "0", allowableValues = {"0", "1", "2"})
    private String gender;

    @Schema(description = "身份证号", example = "110101199001011234")
    private String idCard;

    @Schema(description = "手机号码", example = "13800138000")
    private String phoneNumber;

    @Schema(description = "部门", example = "飞行部")
    private String department;

    @Schema(description = "职位", example = "见习飞行员")
    private String position;

    @Schema(description = "主要机型ID", example = "1")
    private Long primaryMachineTypeId;

    @Schema(description = "主要专业ID", example = "1")
    private Long primaryMajorId;

    @Schema(description = "学历（1中专 2大专 3本科 4硕士 5博士）", example = "3", allowableValues = {"1", "2", "3", "4", "5"})
    private String education;

    @Schema(description = "培训状态（0待培训 1培训中 2已培训 3培训暂停）", example = "0", allowableValues = {"0", "1", "2", "3"})
    private String trainingStatus;

    @Schema(description = "状态（0正常 1停用）", example = "0", allowableValues = {"0", "1"})
    private String status;

    @Schema(description = "入职开始时间", example = "2024-01-01")
    private Date hireStartDate;

    @Schema(description = "入职结束时间", example = "2024-12-31")
    private Date hireEndDate;

    @Schema(description = "出生开始日期", example = "1990-01-01")
    private Date birthStartDate;

    @Schema(description = "出生结束日期", example = "2000-12-31")
    private Date birthEndDate;
}