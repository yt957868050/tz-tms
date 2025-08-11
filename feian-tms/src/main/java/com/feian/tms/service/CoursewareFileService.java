package com.feian.tms.service;

import com.feian.tms.domain.CoursewareFile;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 课件文件Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface CoursewareFileService extends MPJBaseService<CoursewareFile> {
    /**
     * 删除课件时删除课件文件关联
     * @param idList
     */
    void removeByCoursewareIds(List<Long> idList);
}