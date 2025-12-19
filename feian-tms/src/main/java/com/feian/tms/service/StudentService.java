package com.feian.tms.service;

import com.feian.tms.domain.Student;
import com.github.yulichang.base.MPJBaseService;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 学员信息Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface StudentService extends MPJBaseService<Student> {

    boolean deleteBatch(List<Long> idList);


    void setPrimaryMajor(Long studentId, Long primaryMajorId);

    List<Student> studentListByIds(List<Long> idList);

    List<Student> studentList();

    String getStudentName(Long StudentId);

    String getStudentCode(Long StudentId);

    String getIdCardById(@NotNull(message = "学生ID不能为空") Long studentId);

    Long getStudentIdByCode(String studentCode);

    Long getUserId(Long userId);
}