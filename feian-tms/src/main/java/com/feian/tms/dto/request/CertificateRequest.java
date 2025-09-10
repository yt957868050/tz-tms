package com.feian.tms.dto.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * 证书管理请求对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@Schema(description = "证书管理请求对象")
public class CertificateRequest {
    /** 证书ID **/
    @Schema(description = "证书ID", example = "1")
    private Long certificateId;
    /** 证书编号 */
    @Schema(description = "证书编号", example = "CERT001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String certificateCode;

    /** 学生ID */
    @Schema(description = "学生ID", example = "81", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "学生ID不能为空")
    private Long studentId;

    /** 学生编号 */
    @Schema(description = "学生编号", example = "ST001", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "学生编号不能为空")
    private String studentCode;

    /** 学生姓名 */
    @Schema(description = "学生姓名", example = "小明", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "学生姓名不能为空")
    private String studentName;

    /** 学生英文姓名 */
    @Schema(description = "学生英文姓名", example = "Xiao Ming")
    private String engStudentName;

    /** 课程名称 */
    @Schema(description = "课程名称", example = "H425-100(ARRIEL2H)型直升机维修培训课程", requiredMode = Schema.RequiredMode.REQUIRED)
    private String trainingCourse;

    /** 课程英文姓名 */
    @Schema(description = "课程英文姓名", example = "H425-100(ARRIEL 2H) Helicopter Maintenance Training Course")
    private String engTrainingCourse;

    /** 开始日期 */
    @Schema(description = "开始日期", example = "2025-08-13", requiredMode = Schema.RequiredMode.REQUIRED)
    private Date startDate;

    /** 结束日期 */
    @Schema(description = "结束日期", example = "2025-08-13", requiredMode = Schema.RequiredMode.REQUIRED)
    private Date endDate;


}