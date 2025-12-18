package com.feian.tms.config;

import com.alibaba.fastjson2.JSON;
import com.feian.common.core.context.MachineTypeContextHolder;
import com.feian.common.core.domain.entity.SysUser;
import com.feian.common.utils.SecurityUtils;
import com.feian.tms.common.HttpStatusEnum;
import com.feian.tms.common.R;
import com.feian.tms.domain.MachineType;
import com.feian.tms.service.IStudentMachineTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 机型上下文拦截器：
 * - 从请求头 {@code X-Machine-Type-Id} 读取当前机型
 * - 对学员/教员校验“可操作机型权限”
 * - 将机型ID写入 {@link MachineTypeContextHolder} 供业务层读取
 */
@Component
@RequiredArgsConstructor
public class MachineTypeContextInterceptor implements HandlerInterceptor {

    private final IStudentMachineTypeService studentMachineTypeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MachineTypeContextHolder.clear();

        String uri = request.getRequestURI();
        if (shouldIgnore(uri)) {
            return true;
        }

        String header = request.getHeader(MachineTypeContextHolder.HEADER_MACHINE_TYPE_ID);
        if (!StringUtils.hasText(header)) {
            return true;
        }

        Long machineTypeId;
        try {
            machineTypeId = Long.valueOf(header.trim());
        } catch (Exception e) {
            writeJson(response, R.fail(HttpStatusEnum.BAD_REQUEST.getCode(), "机型ID格式错误"));
            return false;
        }

        SysUser user = null;
        try {
            user = SecurityUtils.getLoginUser().getUser();
        } catch (Exception ignore) {
        }

        // 未登录：仅透传（不做权限校验）
        if (user == null) {
            MachineTypeContextHolder.setMachineTypeId(machineTypeId);
            return true;
        }

        // 管理员：允许任意机型（由业务权限控制具体可见范围）
        if (user.isAdmin()) {
            MachineTypeContextHolder.setMachineTypeId(machineTypeId);
            return true;
        }

        Long userId = user.getUserId();
        List<MachineType> available = studentMachineTypeService.getAvailableMachineTypesByUserId(userId);
        boolean hasAssignments = available != null && !available.isEmpty();

        // 学员：必须校验机型权限（未分配任何机型则直接拒绝）
        if (user.getStudentId() != null) {
            if (!hasAssignments) {
                writeJson(response, R.fail(HttpStatusEnum.FORBIDDEN.getCode(), "您当前没有分配任何机型，请联系管理员"));
                return false;
            }
            boolean allowed = available.stream().anyMatch(mt -> mt != null && machineTypeId.equals(mt.getMachineTypeId()));
            if (!allowed) {
                writeJson(response, R.fail(HttpStatusEnum.FORBIDDEN.getCode(), "您没有权限选择该机型"));
                return false;
            }
            MachineTypeContextHolder.setMachineTypeId(machineTypeId);
            return true;
        }

        // 非学员账号：
        // - 若该账号也配置了机型关联（例如教员），则按关联列表校验
        // - 否则放行（例如后台管理人员不受机型权限表约束）
        if (hasAssignments) {
            boolean allowed = available.stream().anyMatch(mt -> mt != null && machineTypeId.equals(mt.getMachineTypeId()));
            if (!allowed) {
                writeJson(response, R.fail(HttpStatusEnum.FORBIDDEN.getCode(), "您没有权限选择该机型"));
                return false;
            }
        }

        MachineTypeContextHolder.setMachineTypeId(machineTypeId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MachineTypeContextHolder.clear();
    }

    private boolean shouldIgnore(String uri) {
        if (!StringUtils.hasText(uri)) {
            return false;
        }
        // 允许在机型选择接口下“自救”切换（避免旧 localStorage 的机型导致被拦截）
        if (uri.startsWith("/api/tms/machineTypeSelection")) {
            return true;
        }
        // 登录/验证码等接口不参与机型上下文
        return uri.startsWith("/login") || uri.startsWith("/logout") || uri.startsWith("/getInfo") || uri.startsWith("/captchaImage") || uri.startsWith("/register");
    }

    private void writeJson(HttpServletResponse response, Object body) throws Exception {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(body));
        response.getWriter().flush();
    }
}
