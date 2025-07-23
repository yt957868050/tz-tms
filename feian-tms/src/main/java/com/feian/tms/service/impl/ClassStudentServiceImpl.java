package com.feian.tms.service.impl;

import com.feian.tms.domain.ClassStudent;
import com.feian.tms.mapper.ClassStudentMapper;
import com.feian.tms.service.ClassStudentService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 班次学员关联Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class ClassStudentServiceImpl extends MPJBaseServiceImpl<ClassStudentMapper, ClassStudent> implements ClassStudentService {

}