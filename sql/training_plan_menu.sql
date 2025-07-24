-- 培训计划管理菜单SQL脚本

-- 1. 添加培训计划管理菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('培训计划管理', 2000, 6, 'training-plan', 'tms/training-plan/index', 1, 0, 'C', '0', '0', 'tms:trainingPlan:list', 'el-icon-calendar', 'admin', sysdate(), '', null, '培训计划管理菜单');

-- 获取刚插入的培训计划管理菜单ID
SET @training_plan_menu_id = LAST_INSERT_ID();

-- 2. 添加培训计划管理的按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES 
('培训计划查询', @training_plan_menu_id, 1, '', '', 1, 0, 'F', '0', '0', 'tms:trainingPlan:query', '', 'admin', sysdate(), '', null, ''),
('培训计划新增', @training_plan_menu_id, 2, '', '', 1, 0, 'F', '0', '0', 'tms:trainingPlan:add', '', 'admin', sysdate(), '', null, ''),
('培训计划修改', @training_plan_menu_id, 3, '', '', 1, 0, 'F', '0', '0', 'tms:trainingPlan:edit', '', 'admin', sysdate(), '', null, ''),
('培训计划删除', @training_plan_menu_id, 4, '', '', 1, 0, 'F', '0', '0', 'tms:trainingPlan:remove', '', 'admin', sysdate(), '', null, ''),
('课程表生成', @training_plan_menu_id, 5, '', '', 1, 0, 'F', '0', '0', 'tms:trainingPlan:schedule', '', 'admin', sysdate(), '', null, ''),
('培训计划导出', @training_plan_menu_id, 6, '', '', 1, 0, 'F', '0', '0', 'tms:trainingPlan:export', '', 'admin', sysdate(), '', null, '');

-- 3. 为管理员角色分配培训计划管理权限
-- 获取管理员角色ID (假设角色ID为1)
INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 1, menu_id FROM sys_menu WHERE menu_name IN ('培训计划管理', '培训计划查询', '培训计划新增', '培训计划修改', '培训计划删除', '课程表生成', '培训计划导出')
AND parent_id IN (2000, @training_plan_menu_id);

-- 4. 为教员角色分配培训计划查看权限 (假设教员角色ID为3)
INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 3, menu_id FROM sys_menu WHERE menu_name IN ('培训计划管理', '培训计划查询')
AND parent_id IN (2000, @training_plan_menu_id);

-- 5. 验证菜单创建结果
SELECT 
    m.menu_id,
    m.menu_name,
    m.path,
    m.component,
    m.perms,
    CASE 
        WHEN m.parent_id = 2000 THEN '直升机培训管理'
        ELSE pm.menu_name
    END as parent_menu_name,
    m.order_num
FROM sys_menu m
LEFT JOIN sys_menu pm ON m.parent_id = pm.menu_id
WHERE m.menu_name LIKE '%培训计划%'
   OR m.parent_id = @training_plan_menu_id
ORDER BY m.parent_id, m.order_num;

SELECT '=== 培训计划管理菜单创建完成 ===' as message;