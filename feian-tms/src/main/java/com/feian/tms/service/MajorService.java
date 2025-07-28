package com.feian.tms.service;

import com.feian.tms.domain.Major;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 专业管理Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface MajorService extends MPJBaseService<Major> {

    boolean removeIds(List<Long> idList);
}