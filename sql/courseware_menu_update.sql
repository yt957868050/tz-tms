-- 课件管理模块菜单更新SQL脚本
-- 将原有课件管理拆分为理论课件和实践课件两个模块

-- ============================================
-- 1. 删除现有课件管理菜单及其子菜单
-- ============================================

-- 删除课件管理的按钮权限
DELETE FROM sys_menu WHERE parent_id = 2025;

-- 删除课件管理主菜单
DELETE FROM sys_menu WHERE menu_name = '课件管理' AND parent_id = 2000;

-- ============================================
-- 2. 创建理论课件管理菜单
-- ============================================

-- 理论课件主菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('理论课件', 2000, 5, 'theory-courseware', 'tms/courseware/theory/index', NULL, 1, 0, 'C', '0', '0', 'tms:courseware:theory:list', 'education', 'admin', NOW(), 'admin', NOW(), '理论课件管理菜单');

-- 获取理论课件菜单ID（假设为3001）
SET @theory_menu_id = LAST_INSERT_ID();

-- 理论课件按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
('理论课件查询', @theory_menu_id, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:theory:query', '#', 'admin', NOW(), 'admin', NOW(), ''),
('理论课件新增', @theory_menu_id, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:theory:add', '#', 'admin', NOW(), 'admin', NOW(), ''),
('理论课件修改', @theory_menu_id, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:theory:edit', '#', 'admin', NOW(), 'admin', NOW(), ''),
('理论课件删除', @theory_menu_id, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:theory:remove', '#', 'admin', NOW(), 'admin', NOW(), ''),
('理论课件导出', @theory_menu_id, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:theory:export', '#', 'admin', NOW(), 'admin', NOW(), '');

-- ============================================
-- 3. 创建实践课件管理菜单
-- ============================================

-- 实践课件主菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('实践课件', 2000, 6, 'practical-courseware', 'tms/courseware/practical/index', NULL, 1, 0, 'C', '0', '0', 'tms:courseware:practical:list', 'tool', 'admin', NOW(), 'admin', NOW(), '实践课件管理菜单');

-- 获取实践课件菜单ID
SET @practical_menu_id = LAST_INSERT_ID();

-- 实践课件按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
('实践课件查询', @practical_menu_id, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:practical:query', '#', 'admin', NOW(), 'admin', NOW(), ''),
('实践课件新增', @practical_menu_id, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:practical:add', '#', 'admin', NOW(), 'admin', NOW(), ''),
('实践课件修改', @practical_menu_id, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:practical:edit', '#', 'admin', NOW(), 'admin', NOW(), ''),
('实践课件删除', @practical_menu_id, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:practical:remove', '#', 'admin', NOW(), 'admin', NOW(), ''),
('实践课件导出', @practical_menu_id, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:practical:export', '#', 'admin', NOW(), 'admin', NOW(), '');

-- ============================================
-- 4. 更新CoursewareFile表，添加VMT文件类型支持
-- ============================================

-- 更新文件类型注释，添加VMT文件类型（7=VMT）
ALTER TABLE tms_courseware_file MODIFY COLUMN file_type VARCHAR(10) COMMENT '文件类型（1教材 2教案 3PPT 4CBT 5工卡 6其他 7VMT）';

-- ============================================
-- 5. 角色权限分配
-- ============================================

-- 管理员角色（role_id=1）拥有所有权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id)
SELECT 1, menu_id FROM sys_menu WHERE menu_name IN ('理论课件', '实践课件') AND parent_id = 2000;

INSERT IGNORE INTO sys_role_menu (role_id, menu_id)
SELECT 1, menu_id FROM sys_menu WHERE parent_id IN (
  SELECT menu_id FROM sys_menu WHERE menu_name IN ('理论课件', '实践课件') AND parent_id = 2000
);

-- 飞行教员角色（role_id=3）- 理论和实践课件全部权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- 理论课件权限
(3, @theory_menu_id),
(3, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件查询' AND parent_id = @theory_menu_id)),
(3, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件新增' AND parent_id = @theory_menu_id)),
(3, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件修改' AND parent_id = @theory_menu_id)),
(3, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件删除' AND parent_id = @theory_menu_id)),
(3, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件导出' AND parent_id = @theory_menu_id)),
-- 实践课件权限
(3, @practical_menu_id),
(3, (SELECT menu_id FROM sys_menu WHERE menu_name = '实践课件查询' AND parent_id = @practical_menu_id)),
(3, (SELECT menu_id FROM sys_menu WHERE menu_name = '实践课件新增' AND parent_id = @practical_menu_id)),
(3, (SELECT menu_id FROM sys_menu WHERE menu_name = '实践课件修改' AND parent_id = @practical_menu_id)),
(3, (SELECT menu_id FROM sys_menu WHERE menu_name = '实践课件删除' AND parent_id = @practical_menu_id)),
(3, (SELECT menu_id FROM sys_menu WHERE menu_name = '实践课件导出' AND parent_id = @practical_menu_id));

-- 地面教员角色（role_id=4）- 理论课件全部权限，实践课件查询权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- 理论课件权限
(4, @theory_menu_id),
(4, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件查询' AND parent_id = @theory_menu_id)),
(4, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件新增' AND parent_id = @theory_menu_id)),
(4, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件修改' AND parent_id = @theory_menu_id)),
(4, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件删除' AND parent_id = @theory_menu_id)),
(4, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件导出' AND parent_id = @theory_menu_id)),
-- 实践课件查询权限
(4, @practical_menu_id),
(4, (SELECT menu_id FROM sys_menu WHERE menu_name = '实践课件查询' AND parent_id = @practical_menu_id));

-- 学员角色（role_id=5）- 只有查询权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- 理论课件查询权限
(5, @theory_menu_id),
(5, (SELECT menu_id FROM sys_menu WHERE menu_name = '理论课件查询' AND parent_id = @theory_menu_id)),
-- 实践课件查询权限
(5, @practical_menu_id),
(5, (SELECT menu_id FROM sys_menu WHERE menu_name = '实践课件查询' AND parent_id = @practical_menu_id));

-- ============================================
-- 6. 验证菜单创建结果
-- ============================================

-- 查看新创建的课件管理菜单
SELECT
    menu_id,
    menu_name,
    parent_id,
    order_num,
    path,
    component,
    perms,
    menu_type,
    visible,
    status
FROM sys_menu
WHERE parent_id = 2000 AND menu_name IN ('理论课件', '实践课件')
ORDER BY order_num;

-- 查看课件管理的按钮权限
SELECT
    m.menu_id,
    m.menu_name,
    m.parent_id,
    m.perms,
    pm.menu_name as parent_name
FROM sys_menu m
LEFT JOIN sys_menu pm ON m.parent_id = pm.menu_id
WHERE pm.menu_name IN ('理论课件', '实践课件') AND pm.parent_id = 2000
ORDER BY m.parent_id, m.order_num;

-- 查看角色权限分配
SELECT
    r.role_name,
    COUNT(rm.menu_id) as menu_count,
    GROUP_CONCAT(m.menu_name ORDER BY m.order_num) as menus
FROM sys_role r
LEFT JOIN sys_role_menu rm ON r.role_id = rm.role_id
LEFT JOIN sys_menu m ON rm.menu_id = m.menu_id
WHERE r.role_id IN (1, 3, 4, 5)
  AND (m.menu_name LIKE '%课件%' OR m.parent_id IN (
    SELECT menu_id FROM sys_menu WHERE menu_name IN ('理论课件', '实践课件')
  ))
GROUP BY r.role_id, r.role_name
ORDER BY r.role_id;

SELECT '=== 课件管理模块菜单更新完成 ===' as message;