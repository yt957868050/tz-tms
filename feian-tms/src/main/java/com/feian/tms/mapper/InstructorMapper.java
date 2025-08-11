package com.feian.tms.mapper;

import com.feian.tms.domain.Instructor;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 教员信息Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface InstructorMapper extends MPJBaseMapper<Instructor> {

    void deleteBatch(List<Long> idList);
   @Select("select * from tms_instructor where is_deleted=0")
    List<Instructor> instructorList();
}