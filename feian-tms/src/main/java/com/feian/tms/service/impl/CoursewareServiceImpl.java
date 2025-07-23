package com.feian.tms.service.impl;

import com.feian.tms.domain.Courseware;
import com.feian.tms.mapper.CoursewareMapper;
import com.feian.tms.service.CoursewareService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 课件管理Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class CoursewareServiceImpl extends MPJBaseServiceImpl<CoursewareMapper, Courseware> implements CoursewareService {

}