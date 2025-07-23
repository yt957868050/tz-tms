package com.feian.tms.dto.query;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 班次学员关联查询对象
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "班次学员关联查询对象")
public class ClassStudentQuery extends PageRequest<ClassStudentQuery> {
    
    @Schema(description = "培训班次ID", example = "1")
    private Long trainingClassId;

    @Schema(description = "学员ID", example = "1")
    private Long studentId;

    @Schema(description = "班次编号", example = "CLASS2024001")
    private String classCode;

    @Schema(description = "班次名称", example = "第一期培训班")
    private String className;

    @Schema(description = "学员姓名", example = "张三")
    private String studentName;

    @Schema(description = "学员工号", example = "S001")
    private String studentCode;

    @Schema(description = "学员状态（0待开班 1培训中 2已结业 3已退班 4请假中）", example = "0", allowableValues = {"0", "1", "2", "3", "4"})
    private String studentStatus;

    @Schema(description = "报名时间", example = "2024-01-01")
    private Date enrollTime;

    @Schema(description = "退班时间", example = "2024-12-31")
    private Date withdrawTime;
    
    // Getter methods for controller compatibility
    public Long getTrainingClassId() {
        return trainingClassId;
    }
    
    public Long getStudentId() {
        return studentId;
    }
    
    public Date getEnrollTime() {
        return enrollTime;
    }
    
    public String getStudentStatus() {
        return studentStatus;
    }
    
    public String getClassCode() {
        return classCode;
    }
    
    public String getClassName() {
        return className;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public String getStudentCode() {
        return studentCode;
    }
}