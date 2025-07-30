package com.feian.tms.controller;

import com.feian.tms.common.R;
import com.feian.tms.domain.MachineType;
import com.feian.tms.service.IStudentMachineTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 学员机型关联管理Controller
 * 
 * @author feian
 * @date 2025-01-24
 */
@RestController
@RequestMapping("/api/tms/studentMachineType")
@RequiredArgsConstructor
@Tag(name = "学员机型关联管理", description = "学员机型关联管理相关接口")
public class StudentMachineTypeController {
    
    private final IStudentMachineTypeService studentMachineTypeService;

    /**
     * 获取学员的机型关联列表
     */
    @PostMapping("/listByStudent")
    @Operation(summary = "获取学员机型关联列表", description = "根据学员ID获取其关联的机型列表")
    public R<List<MachineType>> listByStudent(@Valid @RequestBody StudentIdRequest request) {
        List<MachineType> list = studentMachineTypeService.getAvailableMachineTypesByStudentId(request.getStudentId());
        return R.success(list);
    }

    /**
     * 批量更新学员机型关联
     */
    @PostMapping("/batchUpdate")
    @Operation(summary = "批量更新学员机型关联", description = "批量更新学员的机型关联关系")
    public R<Void> batchUpdate(@Valid @RequestBody BatchUpdateRequest request) {
        boolean result = studentMachineTypeService.updateStudentMachineTypes(
            request.getStudentId(), 
            request.getMachineTypeIds(), 
            request.getPrimaryMachineTypeId()
        );
        studentMachineTypeService.setPrimaryMachineName(request.getStudentId(),request.getPrimaryMachineTypeId());
        return result ? R.success() : R.fail("更新失败");
    }

    /**
     * 设置主要机型
     */
    @PostMapping("/setPrimary")
    @Operation(summary = "设置主要机型", description = "为学员设置主要机型")
    public R<Void> setPrimary(@Valid @RequestBody SetPrimaryRequest request) {
        boolean result = studentMachineTypeService.setPrimaryMachineType(request.getStudentId(), request.getMachineTypeId());
        studentMachineTypeService.setPrimaryMachineName(request.getStudentId(),request.getMachineTypeId());
        return result ? R.success() : R.fail("设置失败");
    }

    /**
     * 删除学员机型关联
     */
    @PostMapping("/remove")
    @Operation(summary = "删除学员机型关联", description = "删除学员与机型的关联关系")
    public R<Void> remove(@Valid @RequestBody RemoveRequest request) {
        boolean result = studentMachineTypeService.removeStudentMachineType(request.getStudentId(), request.getMachineTypeId());
        return result ? R.success() : R.fail("删除失败");
    }
    
    /**
     * 请求对象定义
     */
    public static class StudentIdRequest {
        private Long studentId;
        
        public Long getStudentId() { return studentId; }
        public void setStudentId(Long studentId) { this.studentId = studentId; }
    }
    
    public static class BatchUpdateRequest {
        private Long studentId;
        private List<Long> machineTypeIds;
        private Long primaryMachineTypeId;
        
        public Long getStudentId() { return studentId; }
        public void setStudentId(Long studentId) { this.studentId = studentId; }
        
        public List<Long> getMachineTypeIds() { return machineTypeIds; }
        public void setMachineTypeIds(List<Long> machineTypeIds) { this.machineTypeIds = machineTypeIds; }
        
        public Long getPrimaryMachineTypeId() { return primaryMachineTypeId; }
        public void setPrimaryMachineTypeId(Long primaryMachineTypeId) { this.primaryMachineTypeId = primaryMachineTypeId; }
    }
    
    public static class SetPrimaryRequest {
        private Long studentId;
        private Long machineTypeId;
        
        public Long getStudentId() { return studentId; }
        public void setStudentId(Long studentId) { this.studentId = studentId; }
        
        public Long getMachineTypeId() { return machineTypeId; }
        public void setMachineTypeId(Long machineTypeId) { this.machineTypeId = machineTypeId; }
    }
    
    public static class RemoveRequest {
        private Long studentId;
        private Long machineTypeId;
        
        public Long getStudentId() { return studentId; }
        public void setStudentId(Long studentId) { this.studentId = studentId; }
        
        public Long getMachineTypeId() { return machineTypeId; }
        public void setMachineTypeId(Long machineTypeId) { this.machineTypeId = machineTypeId; }
    }
}