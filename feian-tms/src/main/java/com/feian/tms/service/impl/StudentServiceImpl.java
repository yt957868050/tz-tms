package com.feian.tms.service.impl;

import com.feian.tms.domain.Student;
import com.feian.tms.mapper.StudentMapper;
import com.feian.tms.service.StudentService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 学员信息Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends MPJBaseServiceImpl<StudentMapper, Student> implements StudentService {

}