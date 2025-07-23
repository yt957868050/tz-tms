package com.feian.tms.mapper;

import com.feian.tms.domain.Student;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学员信息Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface StudentMapper extends MPJBaseMapper<Student> {

}