package com.feian.tms.service.impl;

import com.feian.tms.domain.Student;
import com.feian.tms.mapper.StudentMapper;
import com.feian.tms.service.StudentService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 学员信息Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends MPJBaseServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    public StudentMapper studentMapper;
    @Override
    public boolean deleteBatch(List<Long> idList) {
        studentMapper.deleteIds(idList);
        return true;
    }

    @Override
    public void setPrimaryMajor(Long studentId, Long primaryMajorId) {
        studentMapper.setPrimaryMajor(studentId, primaryMajorId);
    }

    @Override
    public List<Student> studentListByIds(List<Long> idList) {
        // 进行非空判断，避免传入空列表导致SQL报错或查询无意义
        if (idList == null || idList.isEmpty()) {
            return Collections.emptyList(); // 返回空列表，而不是null
        }
        return studentMapper.studentListByIds(idList);
    }

    /**
     * 查询所有学员
     * @return
     */

    public List<Student> studentList() {
        return studentMapper.studentList();
    }

    @Override
    public String getStudentName(Long StudentId) {
        return studentMapper.getStudentName(StudentId);
    }

    @Override
    public String getStudentCode(Long StudentId) {
        return studentMapper.getStudentCode(StudentId);
    }

    @Override
    public String getIdCardById(Long studentId) {
        return studentMapper.getIdCardById(studentId);
    }

    @Override
    public Long getStudentIdByCode(String studentCode) {
        return studentMapper.getStudentIdByCode(studentCode);
    }

}