package com.feian.tms.mapper;

import com.feian.tms.domain.InstructorMachineType;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教员机型关联Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface InstructorMachineTypeMapper extends MPJBaseMapper<InstructorMachineType> {
    
    /**
     * 根据教员ID查询关联的机型
     */
    List<InstructorMachineType> selectByInstructorId(@Param("instructorId") Long instructorId);
    
    /**
     * 根据教员ID和机型ID查询关联关系
     */
    InstructorMachineType selectByInstructorIdAndMachineTypeId(@Param("instructorId") Long instructorId, @Param("machineTypeId") Long machineTypeId);
    
    /**
     * 删除教员的所有机型关联
     */
    int deleteByInstructorId(@Param("instructorId") Long instructorId);
}