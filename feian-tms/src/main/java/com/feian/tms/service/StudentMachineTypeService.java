package com.feian.tms.service;

import com.github.yulichang.base.MPJBaseService;
import com.feian.tms.domain.StudentMachineType;

import java.util.List;

/**
 * 学员机型关联Service接口
 * 
 * @author feian
 * @date 2025-01-24
 */
public interface StudentMachineTypeService extends MPJBaseService<StudentMachineType> {
    
    /**
     * 根据学员ID获取机型关联列表
     */
    List<StudentMachineType> listByStudentId(Long studentId);
    
    /**
     * 批量更新学员机型关联
     */
    void batchUpdateStudentMachineTypes(Long studentId, List<Long> machineTypeIds, Long primaryMachineTypeId);
    
    /**
     * 设置主要机型
     */
    void setPrimaryMachineType(Long studentId, Long machineTypeId);
    
    /**
     * 删除学员机型关联
     */
    void removeStudentMachineType(Long studentId, Long machineTypeId);
    
    /**
     * 获取学员的主要机型ID
     */
    Long getPrimaryMachineTypeId(Long studentId);
    
    /**
     * 获取学员的所有机型ID列表
     */
    List<Long> getMachineTypeIdsByStudentId(Long studentId);
}