package com.feian.tms.service;

import com.feian.tms.domain.MachineType;
import com.feian.tms.domain.StudentMachineType;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 学员机型关联Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface IStudentMachineTypeService extends MPJBaseService<StudentMachineType> {
    
    /**
     * 根据学员ID获取可选择的机型列表
     */
    List<MachineType> getAvailableMachineTypesByStudentId(Long studentId);
    
    /**
     * 根据用户ID获取可选择的机型列表（同时支持学员和教员）
     */
    List<MachineType> getAvailableMachineTypesByUserId(Long userId);
    
    /**
     * 为学员添加机型关联
     */
    boolean addStudentMachineType(Long studentId, Long machineTypeId, boolean isPrimary);
    
    /**
     * 移除学员机型关联
     */
    boolean removeStudentMachineType(Long studentId, Long machineTypeId);
    
    /**
     * 设置学员的主要机型
     */
    boolean setPrimaryMachineType(Long studentId, Long machineTypeId);
    
    /**
     * 批量更新学员机型关联
     */
    boolean updateStudentMachineTypes(Long studentId, List<Long> machineTypeIds, Long primaryMachineTypeId);

    /**
     * 设置学员的主要机型名称
     * @param studentId
     * @param primaryMachineTypeId
     */
    void setPrimaryMachineName(Long studentId, Long primaryMachineTypeId);
}