-- TMS培训管理系统完整菜单数据SQL脚本
-- 执行前请确保数据库连接正常，建议先备份现有菜单数据

-- 清理旧的TMS菜单数据（可选，如需要完全重建菜单）
-- DELETE FROM sys_role_menu WHERE menu_id >= 2000 AND menu_id < 3000;
-- DELETE FROM sys_menu WHERE menu_id >= 2000 AND menu_id < 3000;

-- 1. 插入TMS主菜单 (ID: 2000)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2000, '培训管理系统', 0, 5, 'tms', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'education', 'admin', sysdate(), 'admin', sysdate(), 'TMS培训管理系统');

-- =========================== 基础数据管理 ===========================

-- 2. 机型管理菜单 (ID: 2001)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2001, '机型管理', 2000, 1, 'machineType', 'tms/machineType/index', NULL, 1, 0, 'C', '0', '0', 'tms:machineType:list', 'component', 'admin', sysdate(), 'admin', sysdate(), '机型管理菜单');

-- 机型管理按钮权限 (ID: 2002-2006)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2002, '机型查询', 2001, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2003, '机型新增', 2001, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2004, '机型修改', 2001, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2005, '机型删除', 2001, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2006, '机型导出', 2001, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 3. 专业管理菜单 (ID: 2007)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2007, '专业管理', 2000, 2, 'major', 'tms/major/index', NULL, 1, 0, 'C', '0', '0', 'tms:major:list', 'skill', 'admin', sysdate(), 'admin', sysdate(), '专业管理菜单');

-- 专业管理按钮权限 (ID: 2008-2012)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2008, '专业查询', 2007, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2009, '专业新增', 2007, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2010, '专业修改', 2007, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2011, '专业删除', 2007, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2012, '专业导出', 2007, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 4. 培训类型管理菜单 (ID: 2013)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2013, '培训类型管理', 2000, 3, 'trainingType', 'tms/trainingType/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingType:list', 'list', 'admin', sysdate(), 'admin', sysdate(), '培训类型管理菜单');

-- 培训类型管理按钮权限 (ID: 2014-2018)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2014, '培训类型查询', 2013, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingType:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2015, '培训类型新增', 2013, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingType:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2016, '培训类型修改', 2013, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingType:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2017, '培训类型删除', 2013, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingType:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2018, '培训类型导出', 2013, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingType:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 5. 培训能力管理菜单 (ID: 2019)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2019, '培训能力管理', 2000, 4, 'trainingAbility', 'tms/trainingAbility/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingAbility:list', 'tree-table', 'admin', sysdate(), 'admin', sysdate(), '培训能力管理菜单');

-- 培训能力管理按钮权限 (ID: 2020-2024)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2020, '培训能力查询', 2019, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingAbility:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2021, '培训能力新增', 2019, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingAbility:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2022, '培训能力修改', 2019, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingAbility:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2023, '培训能力删除', 2019, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingAbility:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2024, '培训能力导出', 2019, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingAbility:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 人员管理 ===========================

-- 6. 学员管理菜单 (ID: 2025)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2025, '学员管理', 2000, 5, 'student', 'tms/student/index', NULL, 1, 0, 'C', '0', '0', 'tms:student:list', 'peoples', 'admin', sysdate(), 'admin', sysdate(), '学员管理菜单');

-- 学员管理按钮权限 (ID: 2026-2030)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2026, '学员查询', 2025, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2027, '学员新增', 2025, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2028, '学员修改', 2025, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2029, '学员删除', 2025, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2030, '学员导出', 2025, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 7. 教员管理菜单 (ID: 2031)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2031, '教员管理', 2000, 6, 'instructor', 'tms/instructor/index', NULL, 1, 0, 'C', '0', '0', 'tms:instructor:list', 'user', 'admin', sysdate(), 'admin', sysdate(), '教员管理菜单');

-- 教员管理按钮权限 (ID: 2032-2036)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2032, '教员查询', 2031, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2033, '教员新增', 2031, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2034, '教员修改', 2031, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2035, '教员删除', 2031, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2036, '教员导出', 2031, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 8. 学员学习能力菜单 (ID: 2037)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2037, '学员学习能力', 2000, 7, 'studentAbility', 'tms/studentAbility/index', NULL, 1, 0, 'C', '0', '0', 'tms:studentAbility:list', 'chart', 'admin', sysdate(), 'admin', sysdate(), '学员学习能力菜单');

-- 学员学习能力按钮权限 (ID: 2038-2042)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2038, '学习能力查询', 2037, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:studentAbility:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2039, '学习能力新增', 2037, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:studentAbility:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2040, '学习能力修改', 2037, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:studentAbility:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2041, '学习能力删除', 2037, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:studentAbility:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2042, '学习能力导出', 2037, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:studentAbility:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 9. 教员授课能力菜单 (ID: 2043)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2043, '教员授课能力', 2000, 8, 'instructorAbility', 'tms/instructorAbility/index', NULL, 1, 0, 'C', '0', '0', 'tms:instructorAbility:list', 'guide', 'admin', sysdate(), 'admin', sysdate(), '教员授课能力菜单');

-- 教员授课能力按钮权限 (ID: 2044-2048)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2044, '授课能力查询', 2043, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructorAbility:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2045, '授课能力新增', 2043, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructorAbility:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2046, '授课能力修改', 2043, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructorAbility:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2047, '授课能力删除', 2043, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructorAbility:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2048, '授课能力导出', 2043, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructorAbility:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 课件管理 ===========================

-- 10. 课件管理菜单 (ID: 2049)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2049, '课件管理', 2000, 9, 'courseware', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'documentation', 'admin', sysdate(), 'admin', sysdate(), '课件管理目录');

-- 10.1 理论课件管理 (ID: 2050)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2050, '理论课件', 2049, 1, 'theory', 'tms/courseware/theory/index', NULL, 1, 0, 'C', '0', '0', 'tms:courseware:list', 'education', 'admin', sysdate(), 'admin', sysdate(), '理论课件管理');

-- 理论课件按钮权限 (ID: 2051-2055)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2051, '理论课件查询', 2050, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2052, '理论课件新增', 2050, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2053, '理论课件修改', 2050, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2054, '理论课件删除', 2050, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2055, '理论课件导出', 2050, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 10.2 实践课件管理 (ID: 2056)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2056, '实践课件', 2049, 2, 'practical', 'tms/courseware/practical/index', NULL, 1, 0, 'C', '0', '0', 'tms:courseware:list', 'build', 'admin', sysdate(), 'admin', sysdate(), '实践课件管理');

-- 实践课件按钮权限 (ID: 2057-2061)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2057, '实践课件查询', 2056, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2058, '实践课件新增', 2056, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2059, '实践课件修改', 2056, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2060, '实践课件删除', 2056, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2061, '实践课件导出', 2056, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 培训计划管理 ===========================

-- 11. 培训计划菜单 (ID: 2062)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2062, '培训计划', 2000, 10, 'trainingPlan', 'tms/trainingPlan/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingPlan:list', 'date-range', 'admin', sysdate(), 'admin', sysdate(), '培训计划管理');

-- 培训计划按钮权限 (ID: 2063-2067)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2063, '培训计划查询', 2062, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlan:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2064, '培训计划新增', 2062, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlan:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2065, '培训计划修改', 2062, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlan:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2066, '培训计划删除', 2062, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlan:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2067, '培训计划导出', 2062, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlan:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 12. 培训计划明细菜单 (ID: 2068)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2068, '培训计划明细', 2000, 11, 'trainingPlanDetail', 'tms/trainingPlanDetail/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingPlanDetail:list', 'cascader', 'admin', sysdate(), 'admin', sysdate(), '培训计划明细管理');

-- 培训计划明细按钮权限 (ID: 2069-2073)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2069, '计划明细查询', 2068, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlanDetail:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2070, '计划明细新增', 2068, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlanDetail:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2071, '计划明细修改', 2068, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlanDetail:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2072, '计划明细删除', 2068, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlanDetail:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2073, '计划明细导出', 2068, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlanDetail:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 培训执行管理 ===========================

-- 13. 培训班次菜单 (ID: 2074)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2074, '培训班次', 2000, 12, 'trainingClass', 'tms/trainingClass/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingClass:list', 'nested', 'admin', sysdate(), 'admin', sysdate(), '培训班次管理');

-- 培训班次按钮权限 (ID: 2075-2079)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2075, '班次查询', 2074, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2076, '班次新增', 2074, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2077, '班次修改', 2074, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2078, '班次删除', 2074, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2079, '班次导出', 2074, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 14. 班次学员管理菜单 (ID: 2080)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2080, '班次学员管理', 2000, 13, 'classStudent', 'tms/classStudent/index', NULL, 1, 0, 'C', '0', '0', 'tms:classStudent:list', 'peoples', 'admin', sysdate(), 'admin', sysdate(), '班次学员管理');

-- 班次学员管理按钮权限 (ID: 2081-2085)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2081, '班次学员查询', 2080, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:classStudent:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2082, '班次学员新增', 2080, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:classStudent:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2083, '班次学员修改', 2080, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:classStudent:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2084, '班次学员删除', 2080, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:classStudent:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2085, '班次学员导出', 2080, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:classStudent:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 15. 培训记录菜单 (ID: 2086)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2086, '培训记录', 2000, 14, 'trainingRecord', 'tms/trainingRecord/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingRecord:list', 'log', 'admin', sysdate(), 'admin', sysdate(), '培训记录管理');

-- 培训记录按钮权限 (ID: 2087-2091)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2087, '培训记录查询', 2086, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingRecord:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2088, '培训记录新增', 2086, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingRecord:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2089, '培训记录修改', 2086, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingRecord:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2090, '培训记录删除', 2086, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingRecord:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2091, '培训记录导出', 2086, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingRecord:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 证书管理 ===========================

-- 16. 证书管理菜单 (ID: 2092)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2092, '证书管理', 2000, 15, 'certificate', 'tms/certificate/index', NULL, 1, 0, 'C', '0', '0', 'tms:certificate:list', 'star', 'admin', sysdate(), 'admin', sysdate(), '证书管理');

-- 证书管理按钮权限 (ID: 2093-2097)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2093, '证书查询', 2092, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2094, '证书新增', 2092, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2095, '证书修改', 2092, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2096, '证书删除', 2092, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2097, '证书导出', 2092, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 统计报表 ===========================

-- 17. 统计报表菜单 (ID: 2098)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2098, '统计报表', 2000, 16, 'reports', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'chart', 'admin', sysdate(), 'admin', sysdate(), '统计报表目录');

-- 17.1 培训统计 (ID: 2099)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2099, '培训统计', 2098, 1, 'trainingStats', 'tms/reports/trainingStats/index', NULL, 1, 0, 'C', '0', '0', 'tms:reports:trainingStats', 'chart', 'admin', sysdate(), 'admin', sysdate(), '培训统计报表');

-- 17.2 学员统计 (ID: 2100)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2100, '学员统计', 2098, 2, 'studentStats', 'tms/reports/studentStats/index', NULL, 1, 0, 'C', '0', '0', 'tms:reports:studentStats', 'user', 'admin', sysdate(), 'admin', sysdate(), '学员统计报表');

-- 17.3 教员统计 (ID: 2101)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2101, '教员统计', 2098, 3, 'instructorStats', 'tms/reports/instructorStats/index', NULL, 1, 0, 'C', '0', '0', 'tms:reports:instructorStats', 'people', 'admin', sysdate(), 'admin', sysdate(), '教员统计报表');

-- 17.4 证书统计 (ID: 2102)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2102, '证书统计', 2098, 4, 'certificateStats', 'tms/reports/certificateStats/index', NULL, 1, 0, 'C', '0', '0', 'tms:reports:certificateStats', 'star', 'admin', sysdate(), 'admin', sysdate(), '证书统计报表');

-- =========================== 系统设置 ===========================

-- 18. 系统设置菜单 (ID: 2103)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2103, '系统设置', 2000, 17, 'settings', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'system', 'admin', sysdate(), 'admin', sysdate(), '系统设置目录');

-- 18.1 培训参数设置 (ID: 2104)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2104, '培训参数设置', 2103, 1, 'trainingConfig', 'tms/settings/trainingConfig/index', NULL, 1, 0, 'C', '0', '0', 'tms:settings:trainingConfig', 'tool', 'admin', sysdate(), 'admin', sysdate(), '培训参数设置');

-- 18.2 系统日志 (ID: 2105)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2105, '系统日志', 2103, 2, 'systemLog', 'tms/settings/systemLog/index', NULL, 1, 0, 'C', '0', '0', 'tms:settings:systemLog', 'log', 'admin', sysdate(), 'admin', sysdate(), '系统日志查询');

-- =========================== 角色权限分配 ===========================

-- 管理员角色(ID=1)：全部权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id)
SELECT 1, menu_id FROM sys_menu WHERE menu_id >= 2000 AND menu_id <= 2105;

-- 学员角色(ID=5)：只读权限，主要查看自己相关的信息
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- TMS主菜单
(5, 2000),
-- 学员管理（只能查询）
(5, 2025), (5, 2026),
-- 理论课件（只能查询）
(5, 2050), (5, 2051),
-- 实践课件（只能查询）
(5, 2056), (5, 2057),
-- 培训班次（只能查询）
(5, 2074), (5, 2075),
-- 培训记录（只能查询）
(5, 2086), (5, 2087),
-- 证书管理（只能查询自己的证书）
(5, 2092), (5, 2093);

-- 飞行教员角色(ID=3)：教学相关权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- TMS主菜单
(3, 2000),
-- 基础数据（查询）
(3, 2001), (3, 2002), (3, 2007), (3, 2008), (3, 2013), (3, 2014), (3, 2019), (3, 2020),
-- 学员管理（查询、修改）
(3, 2025), (3, 2026), (3, 2028),
-- 教员管理（查询、修改自己）
(3, 2031), (3, 2032), (3, 2034),
-- 学员学习能力（全部权限）
(3, 2037), (3, 2038), (3, 2039), (3, 2040), (3, 2041), (3, 2042),
-- 教员授课能力（查询、修改自己）
(3, 2043), (3, 2044), (3, 2046),
-- 课件管理（全部权限）
(3, 2049), (3, 2050), (3, 2051), (3, 2052), (3, 2053), (3, 2054), (3, 2055),
(3, 2056), (3, 2057), (3, 2058), (3, 2059), (3, 2060), (3, 2061),
-- 培训计划（查询、修改）
(3, 2062), (3, 2063), (3, 2065), (3, 2068), (3, 2069), (3, 2071),
-- 培训班次（查询、修改）
(3, 2074), (3, 2075), (3, 2077), (3, 2080), (3, 2081), (3, 2083),
-- 培训记录（全部权限）
(3, 2086), (3, 2087), (3, 2088), (3, 2089), (3, 2090), (3, 2091),
-- 证书管理（查询、新增、修改）
(3, 2092), (3, 2093), (3, 2094), (3, 2095),
-- 统计报表（查询）
(3, 2098), (3, 2099), (3, 2100), (3, 2101), (3, 2102);

-- 地面教员角色(ID=4)：理论教学权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- TMS主菜单
(4, 2000),
-- 基础数据（查询）
(4, 2001), (4, 2002), (4, 2007), (4, 2008), (4, 2013), (4, 2014), (4, 2019), (4, 2020),
-- 学员管理（查询、修改）
(4, 2025), (4, 2026), (4, 2028),
-- 学员学习能力（全部权限）
(4, 2037), (4, 2038), (4, 2039), (4, 2040), (4, 2041), (4, 2042),
-- 理论课件（全部权限）
(4, 2049), (4, 2050), (4, 2051), (4, 2052), (4, 2053), (4, 2054), (4, 2055),
-- 培训计划（查询、修改）
(4, 2062), (4, 2063), (4, 2065), (4, 2068), (4, 2069), (4, 2071),
-- 培训班次（查询、修改）
(4, 2074), (4, 2075), (4, 2077), (4, 2080), (4, 2081), (4, 2083),
-- 培训记录（全部权限）
(4, 2086), (4, 2087), (4, 2088), (4, 2089), (4, 2090), (4, 2091),
-- 证书管理（查询、新增）
(4, 2092), (4, 2093), (4, 2094),
-- 统计报表（查询）
(4, 2098), (4, 2099), (4, 2100), (4, 2102);

COMMIT;

-- 菜单说明：
-- M = 目录菜单, C = 组件菜单, F = 功能按钮
-- visible: 0=显示, 1=隐藏
-- status: 0=正常, 1=停用
-- is_frame: 0=否, 1=是（外链）
-- is_cache: 0=不缓存, 1=缓存

-- 菜单层级结构：
-- ├── 培训管理系统 (2000)
-- │   ├── 机型管理 (2001)
-- │   ├── 专业管理 (2007) 
-- │   ├── 培训类型管理 (2013)
-- │   ├── 培训能力管理 (2019)
-- │   ├── 学员管理 (2025)
-- │   ├── 教员管理 (2031)
-- │   ├── 学员学习能力 (2037)
-- │   ├── 教员授课能力 (2043)
-- │   ├── 课件管理 (2049)
-- │   │   ├── 理论课件 (2050)
-- │   │   └── 实践课件 (2056)
-- │   ├── 培训计划 (2062)
-- │   ├── 培训计划明细 (2068)
-- │   ├── 培训班次 (2074)
-- │   ├── 班次学员管理 (2080)
-- │   ├── 培训记录 (2086)
-- │   ├── 证书管理 (2092)
-- │   ├── 统计报表 (2098)
-- │   │   ├── 培训统计 (2099)
-- │   │   ├── 学员统计 (2100)
-- │   │   ├── 教员统计 (2101)
-- │   │   └── 证书统计 (2102)
-- │   └── 系统设置 (2103)
-- │       ├── 培训参数设置 (2104)
-- │       └── 系统日志 (2105)