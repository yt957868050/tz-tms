package com.feian.tms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 证书管理响应对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "证书管理响应对象")
public class CertificateResponse {
    @Schema(description = "证书ID", example = "1")
    private Long certificateId;

    @Schema(description = "证书编号", example = "CERT001")
    private String certificateCode;

    @Schema(description = "学生ID", example = "81")
    private Long studentId;

    @Schema(description = "学生编号", example = "ST001")
    private String studentCode;

    @Schema(description = "学生姓名", example = "小明")
    private String studentName;

    @Schema(description = "学生英文姓名", example = "Xiao Ming")
    private String engStudentName;

    @Schema(description = "课程名称", example = "H425-100(ARRIEL2H)型直升机维修培训课程")
    private String trainingCourse;

    @Schema(description = "课程英文姓名", example = "H425-100(ARRIEL 2H) Helicopter Maintenance Training Course")
    private String engTrainingCourse;

    @Schema(description = "开始日期", example = "2025-08-13")
    private Date startDate;

    @Schema(description = "结束日期", example = "2025-08-13")
    private Date endDate;

    @Schema(description = "总课时", example = "100")
    private Integer totalHours;

    @Schema(description = "理论课时", example = "60")
    private Integer theoryHours;

    @Schema(description = "实作课时", example = "40")
    private Integer practiceHours;

    @Schema(description = "综合评分")
    private BigDecimal totalScore;
}