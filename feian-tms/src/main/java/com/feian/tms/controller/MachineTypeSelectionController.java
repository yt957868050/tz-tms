package com.feian.tms.controller;

import com.feian.common.core.controller.BaseController;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.framework.web.service.TokenService;
import com.feian.system.service.ISysUserService;
import com.feian.tms.common.R;
import com.feian.tms.domain.MachineType;
import com.feian.tms.service.IStudentMachineTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机型选择控制器
 * 
 * @author feian
 * @date 2025-01-23
 */
@Tag(name = "机型选择管理", description = "处理用户登录时的机型选择")
@RestController
@RequestMapping("/api/tms/machineTypeSelection")
@RequiredArgsConstructor
public class MachineTypeSelectionController extends BaseController {

    private final IStudentMachineTypeService studentMachineTypeService;
    private final ISysUserService userService;
    private final TokenService tokenService;

    /**
     * 获取当前用户可选择的机型列表
     */
    @Operation(summary = "获取当前用户可选择的机型列表")
    @PostMapping("/availableMachineTypes")
    public R<List<MachineType>> getAvailableMachineTypes() {
        Long userId = SecurityUtils.getUserId();
        List<MachineType> machineTypes = studentMachineTypeService.getAvailableMachineTypesByUserId(userId);
        return R.success(machineTypes);
    }

    /**
     * 设置当前用户选择的机型
     */
    @Operation(summary = "设置当前用户选择的机型")
    @PostMapping("/selectMachineType")
    public R<Void> selectMachineType(@RequestBody MachineTypeSelectionRequest request) {
        Long userId = SecurityUtils.getUserId();
        
        // 验证用户是否有权限选择该机型
        List<MachineType> availableMachineTypes = studentMachineTypeService.getAvailableMachineTypesByUserId(userId);
        boolean hasPermission = availableMachineTypes.stream()
                .anyMatch(mt -> mt.getMachineTypeId().equals(request.getMachineTypeId()));
        
        if (!hasPermission) {
            return R.fail("您没有权限选择该机型");
        }
        
        // 更新用户的当前机型选择
        SysUser user = new SysUser();
        user.setUserId(userId);
        user.setCurrentMachineTypeId(request.getMachineTypeId());
        user.setCurrentMachineTypeName(request.getMachineTypeName());
        
        userService.updateUser(user);
        
        // 刷新token中的用户信息
        tokenService.refreshToken(SecurityUtils.getLoginUser());
        
        return R.success();
    }

    /**
     * 获取当前用户选择的机型
     */
    @Operation(summary = "获取当前用户选择的机型")
    @PostMapping("/currentMachineType")
    public R<MachineTypeSelectionResponse> getCurrentMachineType() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        
        MachineTypeSelectionResponse response = new MachineTypeSelectionResponse();
        response.setMachineTypeId(user.getCurrentMachineTypeId());
        response.setMachineTypeName(user.getCurrentMachineTypeName());
        
        return R.success(response);
    }

    /**
     * 清除当前用户的机型选择（退出登录时调用）
     */
    @Operation(summary = "清除当前用户的机型选择")
    @PostMapping("/clearMachineType")
    public R<Void> clearMachineType() {
        Long userId = SecurityUtils.getUserId();
        
        SysUser user = new SysUser();
        user.setUserId(userId);
        user.setCurrentMachineTypeId(null);
        user.setCurrentMachineTypeName(null);
        
        userService.updateUser(user);
        
        return R.success();
    }

    /**
     * 机型选择请求对象
     */
    public static class MachineTypeSelectionRequest {
        private Long machineTypeId;
        private String machineTypeName;

        public Long getMachineTypeId() {
            return machineTypeId;
        }

        public void setMachineTypeId(Long machineTypeId) {
            this.machineTypeId = machineTypeId;
        }

        public String getMachineTypeName() {
            return machineTypeName;
        }

        public void setMachineTypeName(String machineTypeName) {
            this.machineTypeName = machineTypeName;
        }
    }

    /**
     * 机型选择响应对象
     */
    public static class MachineTypeSelectionResponse {
        private Long machineTypeId;
        private String machineTypeName;

        public Long getMachineTypeId() {
            return machineTypeId;
        }

        public void setMachineTypeId(Long machineTypeId) {
            this.machineTypeId = machineTypeId;
        }

        public String getMachineTypeName() {
            return machineTypeName;
        }

        public void setMachineTypeName(String machineTypeName) {
            this.machineTypeName = machineTypeName;
        }
    }
}