package com.feian.tms.service;

import com.feian.tms.domain.Instructor;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 教员管理Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface InstructorService extends MPJBaseService<Instructor> {

    boolean deleteBatch(List<Long> idList);

    List<Instructor> instructorList();
}