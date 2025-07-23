package com.feian.tms.service.impl;

import com.feian.tms.domain.CoursewareFile;
import com.feian.tms.mapper.CoursewareFileMapper;
import com.feian.tms.service.CoursewareFileService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 课件文件Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class CoursewareFileServiceImpl extends MPJBaseServiceImpl<CoursewareFileMapper, CoursewareFile> implements CoursewareFileService {

}