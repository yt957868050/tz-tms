package com.feian.tms.mapper;

import com.feian.tms.domain.Courseware;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 课件管理Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface CoursewareMapper extends MPJBaseMapper<Courseware> {
    @Select("select courseware_id from tms_courseware where course_code = #{courseCode}")
    Long getCoursewareIdBycourse_code(String courseCode);
}