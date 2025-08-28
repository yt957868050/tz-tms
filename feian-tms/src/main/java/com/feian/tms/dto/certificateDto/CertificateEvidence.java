package com.feian.tms.dto.certificateDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "培训证明DTO对象")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificateEvidence {

    @Schema(description = "学生姓名", example = "小明", requiredMode = Schema.RequiredMode.REQUIRED)
    private String studentName;

    @Schema(description = "学生英文姓名", example = "Xiao Ming")
    private String engStudentName;

    @Schema(description = "学生身份证号", example = "130423199912194319", requiredMode = Schema.RequiredMode.REQUIRED)
    private String idCard;

    @Schema(description = "课程名称", example = "H425-100(ARRIEL 2H)型直升机维修/复训培训课程", requiredMode = Schema.RequiredMode.REQUIRED)
    private String trainingCourse;

    @Schema(description = "课程英文名称", example = "H425-100(ARRIEL 2H) Helicopter Maintenance Training Course")
    private String engTrainingCourse;

    @Schema(description = "开始年份", example = "2025", requiredMode = Schema.RequiredMode.REQUIRED)
    private String startYear;

    @Schema(description = "开始月份", example = "03", requiredMode = Schema.RequiredMode.REQUIRED)
    private String startMonth;

    @Schema(description = "开始日", example = "03", requiredMode = Schema.RequiredMode.REQUIRED)
    private String startDay;

    @Schema(description = "英文开始日期", example = "2025.03.03")
    private String engStartDate;

    @Schema(description = "结束年份", example = "2025", requiredMode = Schema.RequiredMode.REQUIRED)
    private String endYear;

    @Schema(description = "结束月份", example = "03", requiredMode = Schema.RequiredMode.REQUIRED)
    private String endMonth;

    @Schema(description = "结束日", example = "22", requiredMode = Schema.RequiredMode.REQUIRED)
    private String endDay;

    @Schema(description = "英文结束日期", example = "2025.03.22")
    private String engEndDate;

    @Schema(description = "总学时", example = "101h")
    private String engTotalHours;

    @Schema(description = "理论学时", example = "66h")
    private String engTheoryHours;

    @Schema(description = "实作学时", example = "35h")
    private String engPracticeHours;
}
