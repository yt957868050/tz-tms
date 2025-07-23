package com.feian.tms.service.impl;

import com.feian.tms.domain.Instructor;
import com.feian.tms.mapper.InstructorMapper;
import com.feian.tms.service.InstructorService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 教员管理Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class InstructorServiceImpl extends MPJBaseServiceImpl<InstructorMapper, Instructor> implements InstructorService {

}