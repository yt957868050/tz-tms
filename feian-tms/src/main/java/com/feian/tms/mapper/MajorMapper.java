package com.feian.tms.mapper;

import com.feian.tms.domain.Major;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("select major_name from tms_major where major_id=#{majorId} and is_deleted=0")
    String getMajorName(Long majorId);
    @Select("select major_id from tms_major where major_name like concat('%',#{part},'%') and is_deleted=0")
    Long getIdByName(String part);
}