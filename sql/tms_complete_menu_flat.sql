-- TMS培训管理系统完整菜单数据SQL脚本（平级结构）
-- 执行前请确保数据库连接正常，建议先备份现有菜单数据

-- 清理旧的TMS菜单数据（可选，如需要完全重建菜单）
DELETE FROM sys_role_menu WHERE menu_id >= 2000 AND menu_id < 3000;
DELETE FROM sys_menu WHERE menu_id >= 2000 AND menu_id < 3000;

-- =========================== 基础数据管理 ===========================

-- 1. 基础数据管理主菜单 (ID: 2000)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2000, '基础数据管理', 0, 1, 'basicData', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'system', 'admin', sysdate(), 'admin', sysdate(), '基础数据管理');

-- 1.1 机型管理 (ID: 2001)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2001, '机型管理', 2000, 1, 'machineType', 'tms/machineType/index', NULL, 1, 0, 'C', '0', '0', 'tms:machineType:list', 'component', 'admin', sysdate(), 'admin', sysdate(), '机型管理');

-- 机型管理按钮权限 (ID: 2002-2006)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2002, '机型查询', 2001, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2003, '机型新增', 2001, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2004, '机型修改', 2001, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2005, '机型删除', 2001, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2006, '机型导出', 2001, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:machineType:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 1.2 专业管理 (ID: 2007)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2007, '专业管理', 2000, 2, 'major', 'tms/major/index', NULL, 1, 0, 'C', '0', '0', 'tms:major:list', 'skill', 'admin', sysdate(), 'admin', sysdate(), '专业管理');

-- 专业管理按钮权限 (ID: 2008-2012)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2008, '专业查询', 2007, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2009, '专业新增', 2007, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2010, '专业修改', 2007, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2011, '专业删除', 2007, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2012, '专业导出', 2007, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:major:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 1.3 培训类型管理 (ID: 2013)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2013, '培训类型管理', 2000, 3, 'trainingType', 'tms/trainingType/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingType:list', 'list', 'admin', sysdate(), 'admin', sysdate(), '培训类型管理');

-- 培训类型管理按钮权限 (ID: 2014-2018)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2014, '培训类型查询', 2013, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingType:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2015, '培训类型新增', 2013, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingType:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2016, '培训类型修改', 2013, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingType:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2017, '培训类型删除', 2013, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingType:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2018, '培训类型导出', 2013, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingType:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 1.4 培训能力管理 (ID: 2019)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2019, '培训能力管理', 2000, 4, 'trainingAbility', 'tms/trainingAbility/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingAbility:list', 'tree-table', 'admin', sysdate(), 'admin', sysdate(), '培训能力管理');

-- 培训能力管理按钮权限 (ID: 2020-2024)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2020, '培训能力查询', 2019, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingAbility:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2021, '培训能力新增', 2019, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingAbility:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2022, '培训能力修改', 2019, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingAbility:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2023, '培训能力删除', 2019, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingAbility:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2024, '培训能力导出', 2019, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingAbility:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 1.5 大纲管理 (ID: 2025)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2025, '大纲管理', 2000, 5, 'outline', 'tms/outline/index', NULL, 1, 0, 'C', '0', '0', 'tms:outline:list', 'documentation', 'admin', sysdate(), 'admin', sysdate(), '培训大纲管理');

-- 大纲管理按钮权限 (ID: 2026-2030)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2026, '大纲查询', 2025, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:outline:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2027, '大纲新增', 2025, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:outline:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2028, '大纲修改', 2025, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:outline:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2029, '大纲删除', 2025, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:outline:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2030, '大纲导出', 2025, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:outline:export', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2031, '大纲文件预览', 2025, 6, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:outline:preview', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2032, '大纲文件下载', 2025, 7, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:outline:download', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 人员管理 ===========================

-- 2. 人员管理主菜单 (ID: 2033)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2033, '人员管理', 0, 2, 'personnel', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'peoples', 'admin', sysdate(), 'admin', sysdate(), '人员管理');

-- 2.1 学员管理 (ID: 2026)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2026, '学员管理', 2025, 1, 'student', 'tms/student/index', NULL, 1, 0, 'C', '0', '0', 'tms:student:list', 'user', 'admin', sysdate(), 'admin', sysdate(), '学员管理');

-- 学员管理按钮权限 (ID: 2027-2031)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2027, '学员查询', 2026, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2028, '学员新增', 2026, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2029, '学员修改', 2026, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2030, '学员删除', 2026, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2031, '学员导出', 2026, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:student:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 2.2 教员管理 (ID: 2032)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2032, '教员管理', 2025, 2, 'instructor', 'tms/instructor/index', NULL, 1, 0, 'C', '0', '0', 'tms:instructor:list', 'guide', 'admin', sysdate(), 'admin', sysdate(), '教员管理');

-- 教员管理按钮权限 (ID: 2033-2037)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2033, '教员查询', 2032, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2034, '教员新增', 2032, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2035, '教员修改', 2032, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2036, '教员删除', 2032, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2037, '教员导出', 2032, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructor:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 2.3 学员学习能力 (ID: 2038)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2038, '学员学习能力', 2025, 3, 'studentAbility', 'tms/studentAbility/index', NULL, 1, 0, 'C', '0', '0', 'tms:studentAbility:list', 'chart', 'admin', sysdate(), 'admin', sysdate(), '学员学习能力');

-- 学员学习能力按钮权限 (ID: 2039-2043)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2039, '学习能力查询', 2038, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:studentAbility:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2040, '学习能力新增', 2038, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:studentAbility:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2041, '学习能力修改', 2038, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:studentAbility:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2042, '学习能力删除', 2038, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:studentAbility:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2043, '学习能力导出', 2038, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:studentAbility:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 2.4 教员授课能力 (ID: 2044)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2044, '教员授课能力', 2025, 4, 'instructorAbility', 'tms/instructorAbility/index', NULL, 1, 0, 'C', '0', '0', 'tms:instructorAbility:list', 'education', 'admin', sysdate(), 'admin', sysdate(), '教员授课能力');

-- 教员授课能力按钮权限 (ID: 2045-2049)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2045, '授课能力查询', 2044, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructorAbility:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2046, '授课能力新增', 2044, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructorAbility:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2047, '授课能力修改', 2044, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructorAbility:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2048, '授课能力删除', 2044, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructorAbility:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2049, '授课能力导出', 2044, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:instructorAbility:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 课件管理 ===========================

-- 3. 课件管理主菜单 (ID: 2050)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2050, '课件管理', 0, 3, 'courseware', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'documentation', 'admin', sysdate(), 'admin', sysdate(), '课件管理');

-- 3.1 理论课件 (ID: 2051)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2051, '理论课件', 2050, 1, 'theory', 'tms/courseware/theory/index', NULL, 1, 0, 'C', '0', '0', 'tms:courseware:list', 'education', 'admin', sysdate(), 'admin', sysdate(), '理论课件管理');

-- 理论课件按钮权限 (ID: 2052-2056)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2052, '理论课件查询', 2051, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2053, '理论课件新增', 2051, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2054, '理论课件修改', 2051, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2055, '理论课件删除', 2051, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2056, '理论课件导出', 2051, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 3.2 实践课件 (ID: 2057)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2057, '实践课件', 2050, 2, 'practical', 'tms/courseware/practical/index', NULL, 1, 0, 'C', '0', '0', 'tms:courseware:list', 'build', 'admin', sysdate(), 'admin', sysdate(), '实践课件管理');

-- 实践课件按钮权限 (ID: 2058-2062)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2058, '实践课件查询', 2057, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2059, '实践课件新增', 2057, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2060, '实践课件修改', 2057, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2061, '实践课件删除', 2057, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2062, '实践课件导出', 2057, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:courseware:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 培训计划 ===========================

-- 4. 培训计划主菜单 (ID: 2063)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2063, '培训计划', 0, 4, 'plan', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'date-range', 'admin', sysdate(), 'admin', sysdate(), '培训计划');

-- 4.1 培训计划 (ID: 2064)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2064, '培训计划', 2063, 1, 'trainingPlan', 'tms/trainingPlan/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingPlan:list', 'calendar', 'admin', sysdate(), 'admin', sysdate(), '培训计划管理');

-- 培训计划按钮权限 (ID: 2065-2069)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2065, '培训计划查询', 2064, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlan:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2066, '培训计划新增', 2064, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlan:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2067, '培训计划修改', 2064, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlan:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2068, '培训计划删除', 2064, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlan:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2069, '培训计划导出', 2064, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlan:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 4.2 培训计划明细 (ID: 2070)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2070, '培训计划明细', 2063, 2, 'trainingPlanDetail', 'tms/trainingPlanDetail/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingPlanDetail:list', 'cascader', 'admin', sysdate(), 'admin', sysdate(), '培训计划明细管理');

-- 培训计划明细按钮权限 (ID: 2071-2075)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2071, '计划明细查询', 2070, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlanDetail:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2072, '计划明细新增', 2070, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlanDetail:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2073, '计划明细修改', 2070, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlanDetail:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2074, '计划明细删除', 2070, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlanDetail:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2075, '计划明细导出', 2070, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingPlanDetail:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 培训执行 ===========================

-- 5. 培训执行主菜单 (ID: 2076)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2076, '培训执行', 0, 5, 'execution', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'nested', 'admin', sysdate(), 'admin', sysdate(), '培训执行');

-- 5.1 培训班次 (ID: 2077)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2077, '培训班次', 2076, 1, 'trainingClass', 'tms/trainingClass/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingClass:list', 'tab', 'admin', sysdate(), 'admin', sysdate(), '培训班次管理');

-- 培训班次按钮权限 (ID: 2078-2082)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2078, '班次查询', 2077, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2079, '班次新增', 2077, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2080, '班次修改', 2077, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2081, '班次删除', 2077, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2082, '班次导出', 2077, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingClass:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 5.2 班次学员管理 (ID: 2083)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2083, '班次学员管理', 2076, 2, 'classStudent', 'tms/classStudent/index', NULL, 1, 0, 'C', '0', '0', 'tms:classStudent:list', 'peoples', 'admin', sysdate(), 'admin', sysdate(), '班次学员管理');

-- 班次学员管理按钮权限 (ID: 2084-2088)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2084, '班次学员查询', 2083, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:classStudent:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2085, '班次学员新增', 2083, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:classStudent:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2086, '班次学员修改', 2083, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:classStudent:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2087, '班次学员删除', 2083, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:classStudent:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2088, '班次学员导出', 2083, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:classStudent:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- 5.3 培训记录 (ID: 2089)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2089, '培训记录', 2076, 3, 'trainingRecord', 'tms/trainingRecord/index', NULL, 1, 0, 'C', '0', '0', 'tms:trainingRecord:list', 'log', 'admin', sysdate(), 'admin', sysdate(), '培训记录管理');

-- 培训记录按钮权限 (ID: 2090-2094)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2090, '培训记录查询', 2089, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingRecord:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2091, '培训记录新增', 2089, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingRecord:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2092, '培训记录修改', 2089, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingRecord:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2093, '培训记录删除', 2089, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingRecord:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2094, '培训记录导出', 2089, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:trainingRecord:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 证书管理 ===========================

-- 6. 证书管理主菜单 (ID: 2095)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2095, '证书管理', 0, 6, 'certificate', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'star', 'admin', sysdate(), 'admin', sysdate(), '证书管理');

-- 6.1 证书管理 (ID: 2096)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2096, '证书管理', 2095, 1, 'certificateManage', 'tms/certificate/index', NULL, 1, 0, 'C', '0', '0', 'tms:certificate:list', 'medal', 'admin', sysdate(), 'admin', sysdate(), '证书管理');

-- 证书管理按钮权限 (ID: 2097-2101)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2097, '证书查询', 2096, 1, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:query', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2098, '证书新增', 2096, 2, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:add', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2099, '证书修改', 2096, 3, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:edit', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2100, '证书删除', 2096, 4, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:remove', '#', 'admin', sysdate(), 'admin', sysdate(), ''),
(2101, '证书导出', 2096, 5, '', '', NULL, 1, 0, 'F', '0', '0', 'tms:certificate:export', '#', 'admin', sysdate(), 'admin', sysdate(), '');

-- =========================== 统计报表 ===========================

-- 7. 统计报表主菜单 (ID: 2102)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2102, '统计报表', 0, 7, 'reports', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'chart', 'admin', sysdate(), 'admin', sysdate(), '统计报表');

-- 7.1 培训统计 (ID: 2103)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2103, '培训统计', 2102, 1, 'trainingStats', 'tms/reports/trainingStats/index', NULL, 1, 0, 'C', '0', '0', 'tms:reports:trainingStats', 'pie-chart', 'admin', sysdate(), 'admin', sysdate(), '培训统计报表');

-- 7.2 学员统计 (ID: 2104)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2104, '学员统计', 2102, 2, 'studentStats', 'tms/reports/studentStats/index', NULL, 1, 0, 'C', '0', '0', 'tms:reports:studentStats', 'user', 'admin', sysdate(), 'admin', sysdate(), '学员统计报表');

-- 7.3 教员统计 (ID: 2105)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2105, '教员统计', 2102, 3, 'instructorStats', 'tms/reports/instructorStats/index', NULL, 1, 0, 'C', '0', '0', 'tms:reports:instructorStats', 'people', 'admin', sysdate(), 'admin', sysdate(), '教员统计报表');

-- 7.4 证书统计 (ID: 2106)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2106, '证书统计', 2102, 4, 'certificateStats', 'tms/reports/certificateStats/index', NULL, 1, 0, 'C', '0', '0', 'tms:reports:certificateStats', 'medal', 'admin', sysdate(), 'admin', sysdate(), '证书统计报表');

-- =========================== 系统设置 ===========================

-- 8. 系统设置主菜单 (ID: 2107)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2107, '系统设置', 0, 8, 'settings', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'system', 'admin', sysdate(), 'admin', sysdate(), '系统设置');

-- 8.1 培训参数设置 (ID: 2108)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2108, '培训参数设置', 2107, 1, 'trainingConfig', 'tms/settings/trainingConfig/index', NULL, 1, 0, 'C', '0', '0', 'tms:settings:trainingConfig', 'tool', 'admin', sysdate(), 'admin', sysdate(), '培训参数设置');

-- 8.2 系统日志 (ID: 2109)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (2109, '系统日志', 2107, 2, 'systemLog', 'tms/settings/systemLog/index', NULL, 1, 0, 'C', '0', '0', 'tms:settings:systemLog', 'log', 'admin', sysdate(), 'admin', sysdate(), '系统日志查询');

-- =========================== 角色权限分配 ===========================

-- 管理员角色(ID=1)：全部权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id)
SELECT 1, menu_id FROM sys_menu WHERE menu_id >= 2000 AND menu_id <= 2109;

-- 学员角色(ID=5)：只读权限，主要查看自己相关的信息
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- 人员管理
(5, 2025), (5, 2026), (5, 2027),
-- 课件管理（只能查询）
(5, 2050), (5, 2051), (5, 2052), (5, 2057), (5, 2058),
-- 培训执行（只能查询）
(5, 2076), (5, 2077), (5, 2078), (5, 2089), (5, 2090),
-- 证书管理（只能查询自己的证书）
(5, 2095), (5, 2096), (5, 2097);

-- 飞行教员角色(ID=3)：教学相关权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- 基础数据（查询）
(3, 2000), (3, 2001), (3, 2002), (3, 2007), (3, 2008), (3, 2013), (3, 2014), (3, 2019), (3, 2020),
-- 人员管理（查询、修改）
(3, 2025), (3, 2026), (3, 2027), (3, 2029), (3, 2032), (3, 2033), (3, 2035),
(3, 2038), (3, 2039), (3, 2040), (3, 2041), (3, 2042), (3, 2043), (3, 2044), (3, 2045), (3, 2047),
-- 课件管理（全部权限）
(3, 2050), (3, 2051), (3, 2052), (3, 2053), (3, 2054), (3, 2055), (3, 2056),
(3, 2057), (3, 2058), (3, 2059), (3, 2060), (3, 2061), (3, 2062),
-- 培训计划（查询、修改）
(3, 2063), (3, 2064), (3, 2065), (3, 2067), (3, 2070), (3, 2071), (3, 2073),
-- 培训执行（查询、修改）
(3, 2076), (3, 2077), (3, 2078), (3, 2080), (3, 2083), (3, 2084), (3, 2086),
(3, 2089), (3, 2090), (3, 2091), (3, 2092), (3, 2093), (3, 2094),
-- 证书管理（查询、新增、修改）
(3, 2095), (3, 2096), (3, 2097), (3, 2098), (3, 2099),
-- 统计报表（查询）
(3, 2102), (3, 2103), (3, 2104), (3, 2105), (3, 2106);

-- 地面教员角色(ID=4)：理论教学权限
INSERT IGNORE INTO sys_role_menu (role_id, menu_id) VALUES
-- 基础数据（查询）
(4, 2000), (4, 2001), (4, 2002), (4, 2007), (4, 2008), (4, 2013), (4, 2014), (4, 2019), (4, 2020),
-- 人员管理（查询、修改）
(4, 2025), (4, 2026), (4, 2027), (4, 2029), (4, 2038), (4, 2039), (4, 2040), (4, 2041), (4, 2042), (4, 2043),
-- 理论课件（全部权限）
(4, 2050), (4, 2051), (4, 2052), (4, 2053), (4, 2054), (4, 2055), (4, 2056),
-- 培训计划（查询、修改）
(4, 2063), (4, 2064), (4, 2065), (4, 2067), (4, 2070), (4, 2071), (4, 2073),
-- 培训执行（查询、修改）
(4, 2076), (4, 2077), (4, 2078), (4, 2080), (4, 2083), (4, 2084), (4, 2086),
(4, 2089), (4, 2090), (4, 2091), (4, 2092), (4, 2093), (4, 2094),
-- 证书管理（查询、新增）
(4, 2095), (4, 2096), (4, 2097), (4, 2098),
-- 统计报表（查询）
(4, 2102), (4, 2103), (4, 2104), (4, 2106);

COMMIT;

-- 菜单说明：
-- M = 目录菜单, C = 组件菜单, F = 功能按钮
-- visible: 0=显示, 1=隐藏
-- status: 0=正常, 1=停用
-- is_frame: 0=否, 1=是（外链）
-- is_cache: 0=不缓存, 1=缓存

-- 平级菜单层级结构：
-- ├── 基础数据管理 (2000)
-- │   ├── 机型管理 (2001)
-- │   ├── 专业管理 (2007)
-- │   ├── 培训类型管理 (2013)
-- │   └── 培训能力管理 (2019)
-- ├── 人员管理 (2025)
-- │   ├── 学员管理 (2026)
-- │   ├── 教员管理 (2032)
-- │   ├── 学员学习能力 (2038)
-- │   └── 教员授课能力 (2044)
-- ├── 课件管理 (2050)
-- │   ├── 理论课件 (2051)
-- │   └── 实践课件 (2057)
-- ├── 培训计划 (2063)
-- │   ├── 培训计划 (2064)
-- │   └── 培训计划明细 (2070)
-- ├── 培训执行 (2076)
-- │   ├── 培训班次 (2077)
-- │   ├── 班次学员管理 (2083)
-- │   └── 培训记录 (2089)
-- ├── 证书管理 (2095)
-- │   └── 证书管理 (2096)
-- ├── 统计报表 (2102)
-- │   ├── 培训统计 (2103)
-- │   ├── 学员统计 (2104)
-- │   ├── 教员统计 (2105)
-- │   └── 证书统计 (2106)
-- └── 系统设置 (2107)
--     ├── 培训参数设置 (2108)
--     └── 系统日志 (2109)