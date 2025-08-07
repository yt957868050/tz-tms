package com.feian.tms.mapper;

import com.feian.tms.domain.ClassStudent;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 班次学员关联Mapper接口
 * 
 * @author feian
 * @date 2025-01-24
 */
@Mapper
public interface ClassStudentMapper extends MPJBaseMapper<ClassStudent> {

    void remremoveStudentByIds(List<Long> idList);


    void removeAllByIds(List<Long> idList);
}