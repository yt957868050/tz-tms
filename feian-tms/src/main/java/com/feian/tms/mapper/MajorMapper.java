package com.feian.tms.mapper;

import com.feian.tms.domain.Major;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 专业管理Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface MajorMapper extends MPJBaseMapper<Major> {

    void deleteIds(List<Long> idList);
}