package com.feian.tms.mapper;

import com.feian.tms.domain.StudentMachineType;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学员机型关联Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface StudentMachineTypeMapper extends MPJBaseMapper<StudentMachineType> {
    
    /**
     * 根据学员ID查询关联的机型
     */
    List<StudentMachineType> selectByStudentId(@Param("studentId") Long studentId);
    
    /**
     * 根据学员ID和机型ID查询关联关系
     */
    StudentMachineType selectByStudentIdAndMachineTypeId(@Param("studentId") Long studentId, @Param("machineTypeId") Long machineTypeId);
    
    /**
     * 删除学员的所有机型关联
     */
    int deleteByStudentId(@Param("studentId") Long studentId);
}