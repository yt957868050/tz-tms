package com.feian.tms.service;

import com.feian.tms.domain.Student;
import com.github.yulichang.base.MPJBaseService;

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
}