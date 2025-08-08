package com.feian.tms.mapper;

import com.feian.tms.domain.Student;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学员信息Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface StudentMapper extends MPJBaseMapper<Student> {

    void deleteIds(List<Long> idList);


    void setPrimaryMachineName(Long studentId, Long primaryMachineTypeId);

    void setPrimaryMajor(Long studentId, Long primaryMajorId);

    List<Student> studentListByIds(List<Long> idList);

    List<Student> studentList();
}