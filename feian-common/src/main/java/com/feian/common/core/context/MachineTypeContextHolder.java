package com.feian.common.core.context;

/**
 * 机型上下文（每请求）：
 * - 由前端随请求头传递，后端仅做权限校验与约束过滤
 * - 通过 ThreadLocal 在本次请求链路中透传给业务层
 *
 * Header: X-Machine-Type-Id
 */
public final class MachineTypeContextHolder {

    private MachineTypeContextHolder() {
    }

    public static final String HEADER_MACHINE_TYPE_ID = "X-Machine-Type-Id";

    private static final ThreadLocal<Long> MACHINE_TYPE_ID = new ThreadLocal<>();

    public static void setMachineTypeId(Long machineTypeId) {
        MACHINE_TYPE_ID.set(machineTypeId);
    }

    public static Long getMachineTypeId() {
        return MACHINE_TYPE_ID.get();
    }

    public static Long requireMachineTypeId() {
        Long id = getMachineTypeId();
        if (id == null) {
            throw new IllegalArgumentException("请先选择机型");
        }
        return id;
    }

    public static void clear() {
        MACHINE_TYPE_ID.remove();
    }
}

