-- TMS培训管理系统菜单数据SQL脚本
-- 执行前请确保数据库连接正常，建议先备份现有菜单数据

-- 1. 插入TMS主菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('培训管理系统', 0, 5, 'tms', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'education', 'admin', sysdate(), 'admin', sysdate(), 'TMS培训管理系统');

-- 获取刚插入的TMS主菜单ID（假设为2000，实际使用时需要查询获取）
-- SELECT menu_id FROM sys_menu WHERE menu_name = '培训管理系统' AND parent_id = 0;

-- 2. 机型管理菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('机型管理', 2000, 1, 'machineType', 'tms/machineType/index', NULL, 1, 0, 'C', '0', '0', 'tms:machineType:list', 'component', 'admin', sysdate(), 'admin', sysdate(), '机型管理菜单');

-- 机型管理按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('机型查询', 2001, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:query', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('机型新增', 2001, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:add', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('机型修改', 2001, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:edit', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('机型删除', 2001, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:remove', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('机型导出', 2001, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 3. 专业管理菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('专业管理', 2000, 2, 'major', 'tms/major/index', NULL, 1, 0, 'C', '0', '0', 'tms:major:list', 'skill', 'admin', sysdate(), 'admin', sysdate(), '专业管理菜单');

-- 专业管理按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('专业查询', 2007, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:query', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('专业新增', 2007, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:add', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('专业修改', 2007, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:edit', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('专业删除', 2007, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:remove', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('专业导出', 2007, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 4. 学员管理菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('学员管理', 2000, 3, 'student', 'tms/student/index', NULL, 1, 0, 'C', '0', '0', 'tms:student:list', 'peoples', 'admin', sysdate(), 'admin', sysdate(), '学员管理菜单');

-- 学员管理按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('学员查询', 2013, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:query', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('学员新增', 2013, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:add', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('学员修改', 2013, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:edit', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('学员删除', 2013, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:remove', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('学员导出', 2013, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 5. 教员管理菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('教员管理', 2000, 4, 'instructor', 'tms/instructor/index', NULL, 1, 0, 'C', '0', '0', 'tms:instructor:list', 'user', 'admin', sysdate(), 'admin', sysdate(), '教员管理菜单');

-- 教员管理按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('教员查询', 2019, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:query', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('教员新增', 2019, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:add', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('教员修改', 2019, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:edit', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('教员删除', 2019, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:remove', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('教员导出', 2019, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 6. 课件管理菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('课件管理', 2000, 5, 'courseware', 'tms/courseware/index', NULL, 1, 0, 'C', '0', '0', 'tms:courseware:list', 'documentation', 'admin', sysdate(), 'admin', sysdate(), '课件管理菜单');

-- 课件管理按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('课件查询', 2025, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:query', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('课件新增', 2025, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:add', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('课件修改', 2025, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:edit', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('课件删除', 2025, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:remove', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('课件导出', 2025, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 7. 培训班次菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('培训班次', 2000, 6, 'trainingClass', 'tms/trainingClass/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingClass:list', 'nested', 'admin', sysdate(), 'admin', sysdate(), '培训班次菜单');

-- 培训班次按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('班次查询', 2031, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:query', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('班次新增', 2031, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:add', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('班次修改', 2031, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:edit', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('班次删除', 2031, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:remove', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('班次导出', 2031, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 8. 证书管理菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('证书管理', 2000, 7, 'certificate', 'tms/certificate/index', NULL, 1, 0, 'C', '0', '0', 'tms:certificate:list', 'star', 'admin', sysdate(), 'admin', sysdate(), '证书管理菜单');

-- 证书管理按钮权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('证书查询', 2037, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:query', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('证书新增', 2037, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:add', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('证书修改', 2037, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:edit', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('证书删除', 2037, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:remove', '#', 'admin', sysdate(), 'admin', sysdate(), '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('证书导出', 2037, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 为不同角色分配TMS模块权限
-- 管理员角色(ID=1)：全部权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id)
SELECT 1, menu_id FROM sys_menu WHERE menu_id >= 2000 AND menu_id < 3000;

-- 学员角色(ID=5)：只读权限，主要查看自己相关的信息
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- TMS主菜单
(5, 2000),
-- 学员管理（只能查询，不能增删改）
(5, 2013), (5, 2014),  -- 学员管理主菜单和查询权限
-- 课件管理（只能查询和下载）
(5, 2025), (5, 2026),  -- 课件管理主菜单和查询权限
-- 培训班次（只能查询）
(5, 2031), (5, 2032),  -- 培训班次主菜单和查询权限
-- 证书管理（只能查询自己的证书）
(5, 2037), (5, 2038);  -- 证书管理主菜单和查询权限

-- 飞行教员角色(ID=3)：教学相关权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- TMS主菜单
(3, 2000),
-- 机型管理（查询）
(3, 2001), (3, 2002),
-- 专业管理（查询）
(3, 2007), (3, 2008),
-- 学员管理（查询、修改）
(3, 2013), (3, 2014), (3, 2016),
-- 教员管理（查询、修改自己）
(3, 2019), (3, 2020), (3, 2022),
-- 课件管理（全部权限）
(3, 2025), (3, 2026), (3, 2027), (3, 2028), (3, 2029), (3, 2030),
-- 培训班次（查询、修改）
(3, 2031), (3, 2032), (3, 2034),
-- 证书管理（查询、新增、修改）
(3, 2037), (3, 2038), (3, 2039), (3, 2040);

-- 地面教员角色(ID=4)：理论教学权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- TMS主菜单
(4, 2000),
-- 学员管理（查询、修改）
(4, 2013), (4, 2014), (4, 2016),
-- 课件管理（全部权限）
(4, 2025), (4, 2026), (4, 2027), (4, 2028), (4, 2029), (4, 2030),
-- 培训班次（查询、修改）
(4, 2031), (4, 2032), (4, 2034),
-- 证书管理（查询、新增）
(4, 2037), (4, 2038), (4, 2039);

COMMIT;