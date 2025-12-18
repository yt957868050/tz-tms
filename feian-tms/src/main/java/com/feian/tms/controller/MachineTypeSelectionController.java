package com.feian.tms.controller;

import com.feian.common.core.controller.BaseController;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.common.R;
import com.feian.common.core.context.MachineTypeContextHolder;
import com.feian.tms.domain.MachineType;
import com.feian.tms.mapper.MachineTypeMapper;
import com.feian.tms.service.IStudentMachineTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
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
    private final MachineTypeMapper machineTypeMapper;

    /**
     * 获取当前用户可选择的机型列表
     */
    @Operation(summary = "获取当前用户可选择的机型列表")
    @PostMapping("/availableMachineTypes")
    public R<List<MachineType>> getAvailableMachineTypes() {
        Long userId = SecurityUtils.getUserId();
        SysUser user = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        List<MachineType> machineTypes;
        if (user != null && user.isAdmin()) {
            machineTypes = machineTypeMapper.selectList(null);
        } else {
            machineTypes = studentMachineTypeService.getAvailableMachineTypesByUserId(userId);
        }
        return R.success(machineTypes);
    }

    /**
     * 设置当前用户选择的机型
     */
    @Operation(summary = "设置当前用户选择的机型")
    @PostMapping("/selectMachineType")
    public R<Void> selectMachineType(@RequestBody MachineTypeSelectionRequest request) {
        Long userId = SecurityUtils.getUserId();
        SysUser loginUser = SecurityUtils.getLoginUser() != null ? SecurityUtils.getLoginUser().getUser() : null;
        if (loginUser == null) {
            return R.fail("未登录");
        }
        if (request == null || request.getMachineTypeId() == null) {
            return R.fail("机型ID不能为空");
        }

        MachineType machineType = machineTypeMapper.selectById(request.getMachineTypeId());
        if (machineType == null) {
            return R.fail("机型不存在");
        }
        
        // 验证用户是否有权限选择该机型
        if (!loginUser.isAdmin()) {
            List<MachineType> availableMachineTypes = studentMachineTypeService.getAvailableMachineTypesByUserId(userId);
            boolean hasPermission = availableMachineTypes.stream()
                    .anyMatch(mt -> mt.getMachineTypeId().equals(request.getMachineTypeId()));
            if (!hasPermission) {
                return R.fail("您没有权限选择该机型");
            }
        }

        // 机型上下文由前端保存并随请求传递；后端仅校验，不落库、不刷新 token
        return R.success();
    }

    /**
     * 获取当前用户选择的机型
     */
    @Operation(summary = "获取当前用户选择的机型")
    @PostMapping("/currentMachineType")
    public R<MachineTypeSelectionResponse> getCurrentMachineType(
            @RequestHeader(value = MachineTypeContextHolder.HEADER_MACHINE_TYPE_ID, required = false) String headerMachineTypeId) {
        Long machineTypeId = null;
        if (StringUtils.hasText(headerMachineTypeId)) {
            try {
                machineTypeId = Long.valueOf(headerMachineTypeId.trim());
            } catch (Exception ignore) {
            }
        }
        MachineTypeSelectionResponse response = new MachineTypeSelectionResponse();
        response.setMachineTypeId(machineTypeId);
        if (machineTypeId != null) {
            MachineType machineType = machineTypeMapper.selectById(machineTypeId);
            response.setMachineTypeName(machineType != null ? machineType.getMachineTypeName() : null);
        }
        return R.success(response);
    }

    /**
     * 清除当前用户的机型选择（退出登录时调用）
     */
    @Operation(summary = "清除当前用户的机型选择")
    @PostMapping("/clearMachineType")
    public R<Void> clearMachineType() {
        // 机型上下文由前端维护（localStorage），后端无需清理
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
