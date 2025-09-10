package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 学员信息响应对象
 *
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "学员信息响应对象")
public class StudentResponse {

    @Schema(description = "学员ID", example = "1")
    private Long studentId;

    @Schema(description = "学员工号", example = "S001")
    private String studentCode;

    @Schema(description = "学员姓名", example = "张三")
    private String studentName;

    @Schema(description = "学生英文姓名", example = "Xiao Ming")
    private String engStudentName;

    @Schema(description = "性别（0男 1女 2未知）", example = "0")
    private String gender;

    @Schema(description = "出生日期", example = "1990-01-01")
    private Date birthDate;

    @Schema(description = "身份证号", example = "110101199001011234")
    private String idCard;

    @Schema(description = "手机号码", example = "13800138000")
    private String phoneNumber;

    @Schema(description = "邮箱", example = "zhangsan@example.com")
    private String email;

    @Schema(description = "部门", example = "飞行部")
    private String department;

    @Schema(description = "职位", example = "见习飞行员")
    private String position;

    @Schema(description = "入职时间", example = "2024-01-01")
    private Date hireDate;
    @Schema(description = "入学时间", example = "2024-01-01")
    private Date enrollmentDate;

    @Schema(description = "主要机型ID", example = "1")
    private Long primaryMachineTypeId;

    @Schema(description = "主要专业ID", example = "1")
    private Long primaryMajorId;

    @Schema(description = "学历（1中专 2大专 3本科 4硕士 5博士）", example = "3")
    private String education;

    @Schema(description = "培训状态（0待培训 1培训中 2已培训 3培训暂停）", example = "0")
    private String trainingStatus;

    @Schema(description = "头像路径", example = "/upload/avatar/default.jpg")
    private String avatar;

    @Schema(description = "状态（0正常 1停用）", example = "0")
    private String status;

    @Schema(description = "主要机型名称", example = "Bell-206")
    private String primaryMachineTypeName;

    @Schema(description = "主要专业名称", example = "飞行员专业")
    private String primaryMajorName;

    @Schema(description = "创建者", example = "admin")
    private String createBy;

    @Schema(description = "创建时间", example = "2024-01-01 10:00:00")
    private Date createTime;

    @Schema(description = "更新者", example = "admin")
    private String updateBy;

    @Schema(description = "更新时间", example = "2024-01-01 10:00:00")
    private Date updateTime;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

    @Schema(description = "关联的机型列表")
    private List<MachineTypeInfo> machineTypes;

    @Schema(description = "民族", example = "汉族")
    private String nation;

    @Schema(description = "参加工作时间", example = "2015-09-01")
    private Date workStartDate;

    @Schema(description = "工作单位", example = "XX航空公司")
    private String workUnit;

    @Schema(description = "文化程度", example = "本科")
    private String educationLevel;

    @Schema(description = "毕业院校", example = "XX航空航天大学")
    private String graduateSchool;

    @Schema(description = "所学专业", example = "飞行技术")
    private String major;

    @Schema(description = "英语水平", example = "CET-4")
    private String englishLevel;

    @Schema(description = "诚信记录分", example = "100")
    private Integer integrityScore;

    @Schema(description = "执照类型", example = "私用驾驶员执照")
    private String licenseType;

    @Schema(description = "执照编号", example = "A1234567")
    private String licenseNumber;

    @Schema(description = "机型签署情况", example = "波音737、空客A320")
    private String aircraftEndorsement;
    /**
     * 机型信息对象
     */
    @Data
    @Schema(description = "机型信息")
    public static class MachineTypeInfo {
        @Schema(description = "机型ID")
        private Long machineTypeId;

        @Schema(description = "机型名称")
        private String machineTypeName;

        @Schema(description = "是否为主要机型")
        private Boolean isPrimary;
    }
}