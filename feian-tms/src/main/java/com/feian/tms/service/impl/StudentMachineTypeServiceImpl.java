package com.feian.tms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feian.tms.domain.Instructor;
import com.feian.tms.domain.InstructorMachineType;
import com.feian.tms.domain.MachineType;
import com.feian.tms.domain.Student;
import com.feian.tms.domain.StudentMachineType;
import com.feian.tms.mapper.InstructorMapper;
import com.feian.tms.mapper.InstructorMachineTypeMapper;
import com.feian.tms.mapper.MachineTypeMapper;
import com.feian.tms.mapper.StudentMapper;
import com.feian.tms.mapper.StudentMachineTypeMapper;
import com.feian.tms.service.IStudentMachineTypeService;
import com.feian.system.mapper.SysUserMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 学员机型关联Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class StudentMachineTypeServiceImpl extends MPJBaseServiceImpl<StudentMachineTypeMapper, StudentMachineType> implements IStudentMachineTypeService {

    private final StudentMachineTypeMapper studentMachineTypeMapper;
    private final InstructorMachineTypeMapper instructorMachineTypeMapper;
    private final MachineTypeMapper machineTypeMapper;
    private final StudentMapper studentMapper;
    private final InstructorMapper instructorMapper;
    private final SysUserMapper sysUserMapper;

    @Override
    public List<MachineType> getAvailableMachineTypesByStudentId(Long studentId) {
        // 查询学员关联的机型
        List<StudentMachineType> relations = studentMachineTypeMapper.selectByStudentId(studentId);
        if (relations.isEmpty()) {
            return List.of();
        }
        
        List<Long> machineTypeIds = relations.stream()
                .map(StudentMachineType::getMachineTypeId)
                .collect(Collectors.toList());
        
        // 查询机型详情
        return machineTypeMapper.selectBatchIds(machineTypeIds);
    }

    @Override
    public List<MachineType> getAvailableMachineTypesByUserId(Long userId) {
        // 先查询学员信息
        LambdaQueryWrapper<Student> studentWrapper = new LambdaQueryWrapper<>();
        studentWrapper.eq(Student::getUserId, userId);
        Student student = studentMapper.selectOne(studentWrapper);
        
        if (student != null) {
            // 如果是学员，返回学员关联的机型
            return getAvailableMachineTypesByStudentId(student.getStudentId());
        }
        
        // 再查询教员信息
        LambdaQueryWrapper<Instructor> instructorWrapper = new LambdaQueryWrapper<>();
        instructorWrapper.eq(Instructor::getUserId, userId);
        Instructor instructor = instructorMapper.selectOne(instructorWrapper);
        
        if (instructor != null) {
            // 如果是教员，返回教员关联的机型
            return getAvailableMachineTypesByInstructorId(instructor.getInstructorId());
        }
        
        // 都不是，返回空列表
        return List.of();
    }
    
    /**
     * 根据教员ID获取可选择的机型列表
     */
    private List<MachineType> getAvailableMachineTypesByInstructorId(Long instructorId) {
        // 查询教员关联的机型
        List<InstructorMachineType> relations = instructorMachineTypeMapper.selectByInstructorId(instructorId);
        if (relations.isEmpty()) {
            return List.of();
        }
        
        List<Long> machineTypeIds = relations.stream()
                .map(InstructorMachineType::getMachineTypeId)
                .collect(Collectors.toList());
        
        // 查询机型详情
        return machineTypeMapper.selectBatchIds(machineTypeIds);
    }

    @Override
    @Transactional
    public boolean addStudentMachineType(Long studentId, Long machineTypeId, boolean isPrimary) {
        // 检查是否已存在关联
        StudentMachineType existing = studentMachineTypeMapper.selectByStudentIdAndMachineTypeId(studentId, machineTypeId);
        if (existing != null) {
            return false;
        }
        
        // 如果设置为主要机型，先取消其他主要机型
        if (isPrimary) {
            LambdaQueryWrapper<StudentMachineType> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(StudentMachineType::getStudentId, studentId)
                   .eq(StudentMachineType::getIsPrimary, "1");
            
            StudentMachineType update = new StudentMachineType();
            update.setIsPrimary("0");
            studentMachineTypeMapper.update(update, wrapper);
        }
        
        // 创建新关联
        StudentMachineType relation = new StudentMachineType();
        relation.setStudentId(studentId);
        relation.setMachineTypeId(machineTypeId);
        relation.setIsPrimary(isPrimary ? "1" : "0");
        relation.setStatus("0");
        
        return studentMachineTypeMapper.insert(relation) > 0;
    }

    @Override
    public boolean removeStudentMachineType(Long studentId, Long machineTypeId) {
        LambdaQueryWrapper<StudentMachineType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentMachineType::getStudentId, studentId)
               .eq(StudentMachineType::getMachineTypeId, machineTypeId);
        
        return studentMachineTypeMapper.delete(wrapper) > 0;
    }

    @Override
    @Transactional
    public boolean setPrimaryMachineType(Long studentId, Long machineTypeId) {
        // 先取消所有主要机型
        LambdaQueryWrapper<StudentMachineType> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(StudentMachineType::getStudentId, studentId);
        
        StudentMachineType update1 = new StudentMachineType();
        update1.setIsPrimary("0");
        studentMachineTypeMapper.update(update1, wrapper1);
        
        // 设置新的主要机型
        LambdaQueryWrapper<StudentMachineType> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(StudentMachineType::getStudentId, studentId)
                .eq(StudentMachineType::getMachineTypeId, machineTypeId);
        
        StudentMachineType update2 = new StudentMachineType();
        update2.setIsPrimary("1");
        
        boolean result = studentMachineTypeMapper.update(update2, wrapper2) > 0;
        
        if (result) {
            // 更新学员表中的主要机型ID
            Student student = new Student();
            student.setStudentId(studentId);
            student.setPrimaryMachineTypeId(machineTypeId);
            studentMapper.updateById(student);
            
            // 同时更新sys_user表中的current_machine_type_id
            updateUserCurrentMachineType(studentId, machineTypeId);
        }
        
        return result;
    }

    @Override
    @Transactional
    public boolean updateStudentMachineTypes(Long studentId, List<Long> machineTypeIds, Long primaryMachineTypeId) {
        // 删除现有关联
        studentMachineTypeMapper.deleteByStudentId(studentId);
        
        // 添加新关联
        for (Long machineTypeId : machineTypeIds) {
            boolean isPrimary = machineTypeId.equals(primaryMachineTypeId);
            addStudentMachineType(studentId, machineTypeId, isPrimary);
        }
        
        // 更新学员表中的主要机型ID
        Student student = new Student();
        student.setStudentId(studentId);
        student.setPrimaryMachineTypeId(primaryMachineTypeId);
        studentMapper.updateById(student);
        
        // 同时更新sys_user表中的current_machine_type_id
        updateUserCurrentMachineType(studentId, primaryMachineTypeId);
        
        return true;
    }

    /**
     * 根据机型ID设置学员的主要机型名称
     * @param studentId
     * @param primaryMachineTypeId
     */
    public void setPrimaryMachineName(Long studentId, Long primaryMachineTypeId) {
        studentMapper.setPrimaryMachineName(studentId, primaryMachineTypeId);
    }


    /**
     * 更新用户表中的当前机型ID
     */
    private void updateUserCurrentMachineType(Long studentId, Long machineTypeId) {
        // 根据studentId查找对应的用户
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getStudentId, studentId);
        Student student = studentMapper.selectOne(wrapper);
        
        if (student != null && student.getUserId() != null) {
            // 使用新增的方法更新sys_user表的current_machine_type_id
            sysUserMapper.updateUserCurrentMachineType(student.getUserId(), machineTypeId);
        }
    }
}