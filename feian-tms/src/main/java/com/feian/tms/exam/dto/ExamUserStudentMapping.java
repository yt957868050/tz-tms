package com.feian.tms.exam.dto;

import lombok.Data;

/**
 * sys_user.user_id 与 tms_student.student_id 的关联映射
 */
@Data
public class ExamUserStudentMapping {
    private Long userId;
    private Long studentId;
}

