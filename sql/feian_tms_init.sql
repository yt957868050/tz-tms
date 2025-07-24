-- ----------------------------
-- 飞安直升机培训管理系统初始化数据脚本
-- 基于RuoYi框架，修改为适合直升机教学培训的组织架构和基础数据
-- 创建日期: 2025-01-23
-- ----------------------------

-- 清理现有数据，使用TRUNCATE重置自增序列
TRUNCATE TABLE sys_role_dept;
TRUNCATE TABLE sys_user_role;
TRUNCATE TABLE sys_user_post;
TRUNCATE TABLE sys_role_menu;
TRUNCATE TABLE sys_user;
TRUNCATE TABLE sys_dept;
TRUNCATE TABLE sys_post;
TRUNCATE TABLE sys_role;
TRUNCATE TABLE sys_notice;

-- 清理字典数据，添加培训相关字典
DELETE FROM sys_dict_data WHERE dict_type LIKE 'tms_%';
DELETE FROM sys_dict_type WHERE dict_type LIKE 'tms_%';

-- ----------------------------
-- 1、部门表数据初始化 - 直升机培训机构架构
-- ----------------------------
INSERT INTO sys_dept VALUES(100, 0,   '0',            '飞安直升机培训中心',     0, '张主任', '13800138000', 'admin@feian.com', '0', '0', 'admin', NOW(), '', NULL);
INSERT INTO sys_dept VALUES(101, 100, '0,100',        '培训管理部',           1, '李部长', '13800138001', 'training@feian.com', '0', '0', 'admin', NOW(), '', NULL);
INSERT INTO sys_dept VALUES(102, 100, '0,100',        '教学质量部',           2, '王部长', '13800138002', 'quality@feian.com', '0', '0', 'admin', NOW(), '', NULL);
INSERT INTO sys_dept VALUES(103, 100, '0,100',        '安全管理部',           3, '刘部长', '13800138003', 'safety@feian.com', '0', '0', 'admin', NOW(), '', NULL);
INSERT INTO sys_dept VALUES(104, 100, '0,100',        '综合办公室',           4, '陈主任', '13800138004', 'office@feian.com', '0', '0', 'admin', NOW(), '', NULL);

-- 培训管理部下属科室
INSERT INTO sys_dept VALUES(201, 101, '0,100,101',    '理论培训科',           1, '赵科长', '13800138011', 'theory@feian.com', '0', '0', 'admin', NOW(), '', NULL);
INSERT INTO sys_dept VALUES(202, 101, '0,100,101',    '实践培训科',           2, '孙科长', '13800138012', 'practice@feian.com', '0', '0', 'admin', NOW(), '', NULL);
INSERT INTO sys_dept VALUES(203, 101, '0,100,101',    '课件开发科',           3, '周科长', '13800138013', 'courseware@feian.com', '0', '0', 'admin', NOW(), '', NULL);
INSERT INTO sys_dept VALUES(204, 101, '0,100,101',    '学员管理科',           4, '吴科长', '13800138014', 'student@feian.com', '0', '0', 'admin', NOW(), '', NULL);

-- 教学质量部下属科室
INSERT INTO sys_dept VALUES(205, 102, '0,100,102',    '教学监察科',           1, '郑科长', '13800138021', 'inspect@feian.com', '0', '0', 'admin', NOW(), '', NULL);
INSERT INTO sys_dept VALUES(206, 102, '0,100,102',    '考试评估科',           2, '何科长', '13800138022', 'exam@feian.com', '0', '0', 'admin', NOW(), '', NULL);
INSERT INTO sys_dept VALUES(207, 102, '0,100,102',    '证书管理科',           3, '林科长', '13800138023', 'certificate@feian.com', '0', '0', 'admin', NOW(), '', NULL);

-- 安全管理部下属科室
INSERT INTO sys_dept VALUES(208, 103, '0,100,103',    '飞行安全科',           1, '黄科长', '13800138031', 'flight@feian.com', '0', '0', 'admin', NOW(), '', NULL);
INSERT INTO sys_dept VALUES(209, 103, '0,100,103',    '地面安全科',           2, '徐科长', '13800138032', 'ground@feian.com', '0', '0', 'admin', NOW(), '', NULL);

-- ----------------------------
-- 2、岗位信息表数据初始化 - 直升机培训相关岗位
-- ----------------------------
INSERT INTO sys_post VALUES(1, 'director',     '培训中心主任',    1, '0', 'admin', NOW(), '', NULL, '负责培训中心全面工作');
INSERT INTO sys_post VALUES(2, 'dept_manager', '部门经理',        2, '0', 'admin', NOW(), '', NULL, '负责部门管理工作');
INSERT INTO sys_post VALUES(3, 'chief_instructor', '首席教员',    3, '0', 'admin', NOW(), '', NULL, '资深飞行教员，负责教学指导');
INSERT INTO sys_post VALUES(4, 'flight_instructor', '飞行教员',   4, '0', 'admin', NOW(), '', NULL, '执行飞行培训任务');
INSERT INTO sys_post VALUES(5, 'ground_instructor', '地面教员',   5, '0', 'admin', NOW(), '', NULL, '执行理论教学任务');
INSERT INTO sys_post VALUES(6, 'safety_officer', '安全员',       6, '0', 'admin', NOW(), '', NULL, '负责安全管理工作');
INSERT INTO sys_post VALUES(7, 'exam_officer',   '考试员',       7, '0', 'admin', NOW(), '', NULL, '负责考试评估工作');
INSERT INTO sys_post VALUES(8, 'admin_staff',    '行政人员',     8, '0', 'admin', NOW(), '', NULL, '负责行政管理工作');

-- ----------------------------
-- 3、角色信息表数据初始化 - 培训管理角色
-- ----------------------------
INSERT INTO sys_role VALUES('1', '超级管理员',  'admin',           1, 1, 1, 1, '0', '0', 'admin', NOW(), '', NULL, '系统超级管理员');
INSERT INTO sys_role VALUES('2', '培训主管',    'training_manager', 2, 2, 1, 1, '0', '0', 'admin', NOW(), '', NULL, '培训部门主管');
INSERT INTO sys_role VALUES('3', '飞行教员',    'flight_instructor', 3, 3, 1, 1, '0', '0', 'admin', NOW(), '', NULL, '飞行培训教员');
INSERT INTO sys_role VALUES('4', '地面教员',    'ground_instructor', 4, 4, 1, 1, '0', '0', 'admin', NOW(), '', NULL, '地面理论教员');
INSERT INTO sys_role VALUES('5', '学员',        'student',          5, 5, 1, 1, '0', '0', 'admin', NOW(), '', NULL, '培训学员');
INSERT INTO sys_role VALUES('6', '安全管理员',  'safety_manager',   6, 3, 1, 1, '0', '0', 'admin', NOW(), '', NULL, '安全管理人员');
INSERT INTO sys_role VALUES('7', '考试管理员',  'exam_manager',     7, 3, 1, 1, '0', '0', 'admin', NOW(), '', NULL, '考试管理人员');

-- ----------------------------
-- 4、用户信息表数据初始化 - 保留管理员，添加培训相关用户
-- ----------------------------
INSERT INTO sys_user VALUES(1, 100, 'admin', '系统管理员', '00', 'admin@feian.com', '13800138000', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', NOW(), NOW(), 'admin', NOW(), '', NULL, '系统管理员');
INSERT INTO sys_user VALUES(2, 201, 'instructor001', '张教员', '00', 'zhang@feian.com', '13800138101', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', NOW(), NOW(), 'admin', NOW(), '', NULL, '资深飞行教员');
INSERT INTO sys_user VALUES(3, 202, 'instructor002', '李教员', '00', 'li@feian.com', '13800138102', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', NOW(), NOW(), 'admin', NOW(), '', NULL, '实践培训教员');
INSERT INTO sys_user VALUES(4, 204, 'student001', '王学员', '00', 'wang@feian.com', '13800138201', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', NOW(), NOW(), 'admin', NOW(), '', NULL, '培训学员');

-- ----------------------------
-- 5、用户和角色关联表数据初始化
-- ----------------------------
INSERT INTO sys_user_role VALUES ('1', '1');  -- admin -> 超级管理员
INSERT INTO sys_user_role VALUES ('2', '3');  -- 张教员 -> 飞行教员
INSERT INTO sys_user_role VALUES ('3', '4');  -- 李教员 -> 地面教员
INSERT INTO sys_user_role VALUES ('4', '5');  -- 王学员 -> 学员

-- ----------------------------
-- 6、用户与岗位关联表数据初始化
-- ----------------------------
INSERT INTO sys_user_post VALUES ('1', '1');  -- admin -> 培训中心主任
INSERT INTO sys_user_post VALUES ('2', '4');  -- 张教员 -> 飞行教员
INSERT INTO sys_user_post VALUES ('3', '5');  -- 李教员 -> 地面教员

-- ----------------------------
-- 7、角色和部门关联表数据初始化
-- ----------------------------
INSERT INTO sys_role_dept VALUES ('2', '100');
INSERT INTO sys_role_dept VALUES ('2', '101');
INSERT INTO sys_role_dept VALUES ('3', '201');
INSERT INTO sys_role_dept VALUES ('3', '202');
INSERT INTO sys_role_dept VALUES ('4', '201');
INSERT INTO sys_role_dept VALUES ('5', '204');

-- ----------------------------
-- 8、通知公告表数据初始化 - 培训相关公告
-- ----------------------------
INSERT INTO sys_notice VALUES('1', '飞安培训中心2025年培训计划发布', '2', '<p>各位教员、学员：</p><p>2025年直升机培训计划已经制定完成，请各部门按照计划安排做好相关准备工作。</p><p>重点培训项目包括：</p><ul><li>AS350直升机型别培训</li><li>R44直升机初始培训</li><li>仪表飞行规则培训</li><li>应急程序训练</li></ul>', '0', 'admin', NOW(), '', NULL, '培训计划');
INSERT INTO sys_notice VALUES('2', '关于加强飞行安全管理的通知', '1', '<p>为确保培训安全，现就飞行安全管理要求通知如下：</p><p>1. 严格执行飞行前检查程序</p><p>2. 恶劣天气条件下禁止飞行训练</p><p>3. 加强学员安全意识教育</p><p>4. 定期进行安全评估和隐患排查</p>', '0', 'admin', NOW(), '', NULL, '安全管理');
INSERT INTO sys_notice VALUES('3', '新版培训教材和CBT课件上线通知', '2', '<p>各位教员和学员：</p><p>新版培训教材和CBT课件已经上线，主要更新内容包括：</p><ul><li>更新最新的航空法规</li><li>增加实际案例分析</li><li>优化交互式学习体验</li><li>新增VR虚拟现实训练模块</li></ul><p>请及时下载学习使用。</p>', '0', 'admin', NOW(), '', NULL, '教材更新');

-- ----------------------------
-- 9、字典类型表数据初始化 - 添加培训相关字典
-- ----------------------------
INSERT INTO sys_dict_type VALUES(100, '机型类别', 'tms_aircraft_type', '0', 'admin', NOW(), '', NULL, '直升机机型分类');
INSERT INTO sys_dict_type VALUES(101, '培训专业', 'tms_training_major', '0', 'admin', NOW(), '', NULL, '培训专业类别');
INSERT INTO sys_dict_type VALUES(102, '培训类型', 'tms_training_type', '0', 'admin', NOW(), '', NULL, '培训类型分类');
INSERT INTO sys_dict_type VALUES(103, '培训能力', 'tms_training_ability', '0', 'admin', NOW(), '', NULL, '培训能力类别');
INSERT INTO sys_dict_type VALUES(104, '证书类型', 'tms_certificate_type', '0', 'admin', NOW(), '', NULL, '培训证书类型');
INSERT INTO sys_dict_type VALUES(105, '课件类型', 'tms_courseware_type', '0', 'admin', NOW(), '', NULL, '培训课件类型');
INSERT INTO sys_dict_type VALUES(106, '培训状态', 'tms_training_status', '0', 'admin', NOW(), '', NULL, '培训进度状态');
INSERT INTO sys_dict_type VALUES(107, '考试结果', 'tms_exam_result', '0', 'admin', NOW(), '', NULL, '考试结果状态');

-- ----------------------------
-- 10、字典数据表数据初始化 - 培训相关字典数据
-- ----------------------------
-- 机型类别
INSERT INTO sys_dict_data VALUES(100, 1, 'AS350', 'AS350', 'tms_aircraft_type', '', 'primary', 'N', '0', 'admin', NOW(), '', NULL, 'AS350直升机');
INSERT INTO sys_dict_data VALUES(101, 2, 'R44', 'R44', 'tms_aircraft_type', '', 'success', 'N', '0', 'admin', NOW(), '', NULL, 'R44直升机');
INSERT INTO sys_dict_data VALUES(102, 3, 'R22', 'R22', 'tms_aircraft_type', '', 'info', 'N', '0', 'admin', NOW(), '', NULL, 'R22直升机');
INSERT INTO sys_dict_data VALUES(103, 4, 'EC135', 'EC135', 'tms_aircraft_type', '', 'warning', 'N', '0', 'admin', NOW(), '', NULL, 'EC135直升机');
INSERT INTO sys_dict_data VALUES(104, 5, 'Bell206', 'Bell206', 'tms_aircraft_type', '', 'danger', 'N', '0', 'admin', NOW(), '', NULL, 'Bell206直升机');

-- 培训专业
INSERT INTO sys_dict_data VALUES(110, 1, '空勤', 'AIRCREW', 'tms_training_major', '', 'primary', 'Y', '0', 'admin', NOW(), '', NULL, '空勤专业');
INSERT INTO sys_dict_data VALUES(111, 2, '地勤', 'GROUND_CREW', 'tms_training_major', '', 'success', 'N', '0', 'admin', NOW(), '', NULL, '地勤专业');

-- 培训类型
INSERT INTO sys_dict_data VALUES(120, 1, '理论培训', 'THEORY', 'tms_training_type', '', 'primary', 'Y', '0', 'admin', NOW(), '', NULL, '理论培训');
INSERT INTO sys_dict_data VALUES(121, 2, '实践培训', 'PRACTICE', 'tms_training_type', '', 'success', 'N', '0', 'admin', NOW(), '', NULL, '实践培训');

-- 培训能力
INSERT INTO sys_dict_data VALUES(130, 1, '初始培训', 'INITIAL', 'tms_training_ability', '', 'primary', 'Y', '0', 'admin', NOW(), '', NULL, '初始培训');
INSERT INTO sys_dict_data VALUES(131, 2, '复训', 'RECURRENT', 'tms_training_ability', '', 'warning', 'N', '0', 'admin', NOW(), '', NULL, '复训');
INSERT INTO sys_dict_data VALUES(132, 3, '差异培训', 'DIFFERENCE', 'tms_training_ability', '', 'info', 'N', '0', 'admin', NOW(), '', NULL, '差异培训');
INSERT INTO sys_dict_data VALUES(133, 4, '恢复培训', 'RESTORATION', 'tms_training_ability', '', 'danger', 'N', '0', 'admin', NOW(), '', NULL, '恢复培训');

-- 证书类型
INSERT INTO sys_dict_data VALUES(140, 1, '培训合格证', 'TRAINING_CERTIFICATE', 'tms_certificate_type', '', 'primary', 'Y', '0', 'admin', NOW(), '', NULL, '培训合格证');
INSERT INTO sys_dict_data VALUES(141, 2, '技能证书', 'SKILL_CERTIFICATE', 'tms_certificate_type', '', 'success', 'N', '0', 'admin', NOW(), '', NULL, '技能证书');
INSERT INTO sys_dict_data VALUES(142, 3, '资质证书', 'QUALIFICATION_CERTIFICATE', 'tms_certificate_type', '', 'warning', 'N', '0', 'admin', NOW(), '', NULL, '资质证书');

-- 课件类型
INSERT INTO sys_dict_data VALUES(150, 1, '教材', 'TEXTBOOK', 'tms_courseware_type', '', 'primary', 'Y', '0', 'admin', NOW(), '', NULL, '培训教材');
INSERT INTO sys_dict_data VALUES(151, 2, '教案', 'LESSON_PLAN', 'tms_courseware_type', '', 'success', 'N', '0', 'admin', NOW(), '', NULL, '教学教案');
INSERT INTO sys_dict_data VALUES(152, 3, 'PPT', 'PPT', 'tms_courseware_type', '', 'info', 'N', '0', 'admin', NOW(), '', NULL, '演示文稿');
INSERT INTO sys_dict_data VALUES(153, 4, 'CBT', 'CBT', 'tms_courseware_type', '', 'warning', 'N', '0', 'admin', NOW(), '', NULL, '计算机培训');
INSERT INTO sys_dict_data VALUES(154, 5, '工卡', 'JOB_CARD', 'tms_courseware_type', '', 'danger', 'N', '0', 'admin', NOW(), '', NULL, '实操工卡');

-- 培训状态
INSERT INTO sys_dict_data VALUES(160, 1, '未开始', 'NOT_STARTED', 'tms_training_status', '', 'info', 'Y', '0', 'admin', NOW(), '', NULL, '培训未开始');
INSERT INTO sys_dict_data VALUES(161, 2, '进行中', 'IN_PROGRESS', 'tms_training_status', '', 'primary', 'N', '0', 'admin', NOW(), '', NULL, '培训进行中');
INSERT INTO sys_dict_data VALUES(162, 3, '已完成', 'COMPLETED', 'tms_training_status', '', 'success', 'N', '0', 'admin', NOW(), '', NULL, '培训已完成');
INSERT INTO sys_dict_data VALUES(163, 4, '已暂停', 'SUSPENDED', 'tms_training_status', '', 'warning', 'N', '0', 'admin', NOW(), '', NULL, '培训已暂停');
INSERT INTO sys_dict_data VALUES(164, 5, '已取消', 'CANCELLED', 'tms_training_status', '', 'danger', 'N', '0', 'admin', NOW(), '', NULL, '培训已取消');

-- 考试结果
INSERT INTO sys_dict_data VALUES(170, 1, '合格', 'PASS', 'tms_exam_result', '', 'success', 'Y', '0', 'admin', NOW(), '', NULL, '考试合格');
INSERT INTO sys_dict_data VALUES(171, 2, '不合格', 'FAIL', 'tms_exam_result', '', 'danger', 'N', '0', 'admin', NOW(), '', NULL, '考试不合格');
INSERT INTO sys_dict_data VALUES(172, 3, '缺考', 'ABSENT', 'tms_exam_result', '', 'warning', 'N', '0', 'admin', NOW(), '', NULL, '缺考');
INSERT INTO sys_dict_data VALUES(173, 4, '待评分', 'PENDING', 'tms_exam_result', '', 'info', 'N', '0', 'admin', NOW(), '', NULL, '待评分');

-- ----------------------------
-- 11、数据变化追踪表 (trace表)
-- ----------------------------
DROP TABLE IF EXISTS trace_log;
CREATE TABLE trace_log (
  trace_id          BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '追踪ID',
  table_name        VARCHAR(64)     NOT NULL                   COMMENT '表名',
  operation_type    VARCHAR(20)     NOT NULL                   COMMENT '操作类型（INSERT/UPDATE/DELETE/CREATE_TABLE）',
  record_id         VARCHAR(64)     NOT NULL                   COMMENT '记录ID',
  old_data          JSON                                       COMMENT '修改前数据（JSON格式）',
  new_data          JSON                                       COMMENT '修改后数据（JSON格式）',
  changed_fields    VARCHAR(1000)                              COMMENT '变更字段列表',
  operator_id       BIGINT(20)                                 COMMENT '操作人ID',
  operator_name     VARCHAR(64)                                COMMENT '操作人姓名',
  operation_time    DATETIME        NOT NULL                   COMMENT '操作时间',
  operation_ip      VARCHAR(128)                               COMMENT '操作IP',
  remark            VARCHAR(500)                               COMMENT '备注',
  PRIMARY KEY (trace_id),
  KEY idx_trace_table (table_name),
  KEY idx_trace_time (operation_time),
  KEY idx_trace_operator (operator_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '数据变化追踪表';

-- 初始化追踪记录示例
INSERT INTO trace_log VALUES(1, 'sys_dept', 'INSERT', '100', NULL, 
  '{"dept_id": 100, "dept_name": "飞安直升机培训中心", "leader": "张主任"}', 
  'dept_name,leader', 1, 'admin', NOW(), '127.0.0.1', '初始化培训中心架构');
  
INSERT INTO trace_log VALUES(2, 'sys_user', 'INSERT', '2', NULL, 
  '{"user_id": 2, "user_name": "instructor001", "nick_name": "张教员", "dept_id": 201}', 
  'user_name,nick_name,dept_id', 1, 'admin', NOW(), '127.0.0.1', '添加飞行教员');

INSERT INTO trace_log VALUES(3, 'sys_dict_type', 'INSERT', '100', NULL, 
  '{"dict_id": 100, "dict_name": "机型类别", "dict_type": "tms_aircraft_type"}', 
  'dict_name,dict_type', 1, 'admin', NOW(), '127.0.0.1', '初始化培训相关字典');

-- ----------------------------
-- 12、菜单数据初始化 - 完整的系统菜单
-- ----------------------------
TRUNCATE TABLE sys_menu;

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1, '系统管理', 0, 1, 'system', null, '', '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2025-07-23 08:52:17', '', null, '系统管理目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2, '系统监控', 0, 2, 'monitor', null, '', '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2025-07-23 08:52:17', '', null, '系统监控目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (3, '系统工具', 0, 3, 'tool', null, '', '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2025-07-23 08:52:17', '', null, '系统工具目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (4, '飞安培训官网', 0, 4, 'http://www.feian-training.com', null, '', '', 0, 0, 'M', '0', '0', '', 'guide', 'admin', '2025-07-23 08:52:17', '', null, '若依官网地址');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2025-07-23 08:52:17', '', null, '用户管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2025-07-23 08:52:17', '', null, '角色管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2025-07-23 08:52:17', '', null, '菜单管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2025-07-23 08:52:17', '', null, '部门管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2025-07-23 08:52:17', '', null, '岗位管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2025-07-23 08:52:17', '', null, '字典管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2025-07-23 08:52:17', '', null, '参数设置菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2025-07-23 08:52:17', '', null, '通知公告菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (108, '日志管理', 1, 9, 'log', '', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2025-07-23 08:52:17', '', null, '日志管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2025-07-23 08:52:17', '', null, '在线用户菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2025-07-23 08:52:17', '', null, '定时任务菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2025-07-23 08:52:17', '', null, '数据监控菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2025-07-23 08:52:17', '', null, '服务监控菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2025-07-23 08:52:17', '', null, '缓存监控菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (114, '缓存列表', 2, 6, 'cacheList', 'monitor/cache/list', '', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis-list', 'admin', '2025-07-23 08:52:17', '', null, '缓存列表菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (115, '表单构建', 3, 1, 'build', 'tool/build/index', '', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2025-07-23 08:52:17', '', null, '表单构建菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (116, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2025-07-23 08:52:17', '', null, '代码生成菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (117, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', '', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2025-07-23 08:52:17', '', null, '系统接口菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2025-07-23 08:52:17', '', null, '操作日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2025-07-23 08:52:17', '', null, '登录日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1000, '用户查询', 100, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1001, '用户新增', 100, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1002, '用户修改', 100, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1003, '用户删除', 100, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1004, '用户导出', 100, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1005, '用户导入', 100, 6, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1006, '重置密码', 100, 7, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1007, '角色查询', 101, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1008, '角色新增', 101, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1009, '角色修改', 101, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1010, '角色删除', 101, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1011, '角色导出', 101, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1012, '菜单查询', 102, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1013, '菜单新增', 102, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1014, '菜单修改', 102, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1015, '菜单删除', 102, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1016, '部门查询', 103, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1017, '部门新增', 103, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1018, '部门修改', 103, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1019, '部门删除', 103, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1020, '岗位查询', 104, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1021, '岗位新增', 104, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1022, '岗位修改', 104, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1023, '岗位删除', 104, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1024, '岗位导出', 104, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1025, '字典查询', 105, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1026, '字典新增', 105, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1027, '字典修改', 105, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1028, '字典删除', 105, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1029, '字典导出', 105, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1030, '参数查询', 106, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1031, '参数新增', 106, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1032, '参数修改', 106, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1033, '参数删除', 106, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1034, '参数导出', 106, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1035, '公告查询', 107, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1036, '公告新增', 107, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1037, '公告修改', 107, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1038, '公告删除', 107, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1039, '操作查询', 500, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1040, '操作删除', 500, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1041, '日志导出', 500, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1042, '登录查询', 501, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1043, '登录删除', 501, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1044, '日志导出', 501, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1045, '账户解锁', 501, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:unlock', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1046, '在线查询', 109, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1047, '批量强退', 109, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1048, '单条强退', 109, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1049, '任务查询', 110, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1050, '任务新增', 110, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1051, '任务修改', 110, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1052, '任务删除', 110, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1053, '状态修改', 110, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1054, '任务导出', 110, 6, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1055, '生成查询', 116, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1056, '生成修改', 116, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1057, '生成删除', 116, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1058, '导入代码', 116, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1059, '预览代码', 116, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2025-07-23 08:52:17', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1060, '生成代码', 116, 6, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2025-07-23 08:52:17', '', null, '');

-- 显示数据变化统计
SELECT '=== 飞安直升机培训管理系统初始化完成 ===' AS message;
SELECT CONCAT('部门数量: ', COUNT(*)) AS dept_count FROM sys_dept;
SELECT CONCAT('用户数量: ', COUNT(*)) AS user_count FROM sys_user;
SELECT CONCAT('岗位数量: ', COUNT(*)) AS post_count FROM sys_post;
SELECT CONCAT('角色数量: ', COUNT(*)) AS role_count FROM sys_role;
SELECT CONCAT('培训字典类型: ', COUNT(*)) AS dict_type_count FROM sys_dict_type WHERE dict_type LIKE 'tms_%';
SELECT CONCAT('培训字典数据: ', COUNT(*)) AS dict_data_count FROM sys_dict_data WHERE dict_type LIKE 'tms_%';
SELECT CONCAT('追踪记录: ', COUNT(*)) AS trace_count FROM trace_log;

-- ================================
-- 13、TMS业务表结构创建
-- ================================

-- 导入TMS业务表DDL（无外键约束版本）
SOURCE tms_tables_ddl_no_fk.sql;

-- ================================
-- 14、TMS业务表初始化数据
-- ================================

-- ----------------------------
-- 机型管理表初始化数据
-- ----------------------------
INSERT INTO tms_machine_type VALUES
(1, 'AS350直升机', 'AS350', '/images/aircraft/as350.jpg', 'AS350 Ecureuil是法国宇航公司生产的单发涡轴轻型直升机', '0', 1, NOW(), NOW(), 'admin', 'admin', 0, 'AS350系列直升机'),
(2, 'R44直升机', 'R44', '/images/aircraft/r44.jpg', 'Robinson R44是美国罗宾逊直升机公司生产的四座轻型直升机', '0', 2, NOW(), NOW(), 'admin', 'admin', 0, 'R44系列直升机'),
(3, 'R22直升机', 'R22', '/images/aircraft/r22.jpg', 'Robinson R22是美国罗宾逊直升机公司生产的双座轻型直升机', '0', 3, NOW(), NOW(), 'admin', 'admin', 0, 'R22系列直升机'),
(4, 'EC135直升机', 'EC135', '/images/aircraft/ec135.jpg', 'EC135是欧洲直升机公司（现空客直升机）生产的双发轻型直升机', '0', 4, NOW(), NOW(), 'admin', 'admin', 0, 'EC135系列直升机'),
(5, 'Bell206直升机', 'Bell206', '/images/aircraft/bell206.jpg', 'Bell 206是贝尔直升机公司生产的单发轻型直升机', '0', 5, NOW(), NOW(), 'admin', 'admin', 0, 'Bell206系列直升机');

-- ----------------------------
-- 专业管理表初始化数据
-- ----------------------------
INSERT INTO tms_major VALUES
(1, '空勤专业', 'AIRCREW', '1', '负责直升机驾驶和空中作业的专业人员', '0', 1, NOW(), NOW(), 'admin', 'admin', 0, '空勤人员专业'),
(2, '地勤专业', 'GROUND_CREW', '2', '负责直升机维护和地面保障的专业人员', '0', 2, NOW(), NOW(), 'admin', 'admin', 0, '地勤人员专业');

-- ----------------------------
-- 培训类型表初始化数据
-- ----------------------------
INSERT INTO tms_training_type VALUES
(1, '理论培训', 'THEORY', '1', '课堂理论知识学习，包括法规、原理、程序等', '0', 1, NOW(), NOW(), 'admin', 'admin', 0, '理论培训课程'),
(2, '实践培训', 'PRACTICE', '2', '实际操作训练，包括模拟器和真机训练', '0', 2, NOW(), NOW(), 'admin', 'admin', 0, '实践培训课程');

-- ----------------------------
-- 培训能力表初始化数据
-- ----------------------------
INSERT INTO tms_training_ability VALUES
(1, '初始培训', 'INITIAL', '1', '首次获得某机型或专业资质的培训', '0', 1, NOW(), NOW(), 'admin', 'admin', 0, '初始培训能力'),
(2, '复训', 'RECURRENT', '2', '定期进行的复习和更新培训', '0', 2, NOW(), NOW(), 'admin', 'admin', 0, '复训能力'),
(3, '差异培训', 'DIFFERENCE', '3', '不同机型或设备间的差异化培训', '0', 3, NOW(), NOW(), 'admin', 'admin', 0, '差异培训能力'),
(4, '恢复培训', 'RESTORATION', '4', '恢复已过期或暂停资质的培训', '0', 4, NOW(), NOW(), 'admin', 'admin', 0, '恢复培训能力');

-- ----------------------------
-- 课件管理表初始化数据
-- ----------------------------
INSERT INTO tms_courseware VALUES
(1, 'AS350-AIRCREW-THEORY-001', 'AS350基础理论', 'ATA-71', 1, 1, 1, 20.0, 0.0, 'AS350直升机基础理论知识', '掌握AS350直升机基本构造和工作原理', '完成理论考试并达到80分以上', '0', 1, NOW(), NOW(), 'admin', 'admin', 0, 'AS350理论课程'),
(2, 'AS350-AIRCREW-PRACTICE-001', 'AS350飞行训练', 'ATA-27', 1, 1, 2, 0.0, 50.0, 'AS350直升机飞行训练', '掌握AS350直升机基本飞行技能', '完成规定飞行科目和检查', '0', 2, NOW(), NOW(), 'admin', 'admin', 0, 'AS350飞行训练'),
(3, 'R44-AIRCREW-THEORY-001', 'R44基础理论', 'ATA-71', 2, 1, 1, 15.0, 0.0, 'R44直升机基础理论知识', '掌握R44直升机基本构造和工作原理', '完成理论考试并达到80分以上', '0', 3, NOW(), NOW(), 'admin', 'admin', 0, 'R44理论课程'),
(4, 'R44-AIRCREW-PRACTICE-001', 'R44飞行训练', 'ATA-27', 2, 1, 2, 0.0, 40.0, 'R44直升机飞行训练', '掌握R44直升机基本飞行技能', '完成规定飞行科目和检查', '0', 4, NOW(), NOW(), 'admin', 'admin', 0, 'R44飞行训练'),
(5, 'GROUND-THEORY-001', '地勤基础理论', 'ATA-05', 1, 2, 1, 30.0, 0.0, '直升机地勤基础理论', '掌握直升机维护基础知识', '完成理论考试并达到85分以上', '0', 5, NOW(), NOW(), 'admin', 'admin', 0, '地勤理论课程');

-- ----------------------------
-- 学员信息表初始化数据
-- ----------------------------
INSERT INTO tms_student VALUES
(1, 'STU001', '张三', '0', '1995-05-15', '110101199505150011', '13800001001', 'zhangsan@feian.com', '培训管理部', '学员', '2024-01-15', 1, 1, '3', '0', '/avatars/student1.jpg', '0', NOW(), NOW(), 'admin', 'admin', 0, 'AS350初训学员'),
(2, 'STU002', '李四', '1', '1992-08-20', '110101199208200022', '13800001002', 'lisi@feian.com', '培训管理部', '学员', '2024-02-01', 2, 1, '3', '1', '/avatars/student2.jpg', '0', NOW(), NOW(), 'admin', 'admin', 0, 'R44初训学员'),
(3, 'STU003', '王五', '0', '1990-12-10', '110101199012100033', '13800001003', 'wangwu@feian.com', '培训管理部', '学员', '2024-01-20', 1, 2, '4', '0', '/avatars/student3.jpg', '0', NOW(), NOW(), 'admin', 'admin', 0, '地勤专业学员');

-- ----------------------------
-- 教员信息表初始化数据
-- ----------------------------
INSERT INTO tms_instructor VALUES
(1, 'INS001', '张教员', '0', '1980-03-15', '110101198003150011', '13900001001', 'zhang_instructor@feian.com', '理论培训科', '高级教员', '2010-06-01', '4', '2012-01-01', '4', '高级工程师', '0', '/avatars/instructor1.jpg', '资深飞行教员，拥有15年教学经验', '0', NOW(), NOW(), 'admin', 'admin', 0, 'AS350首席教员'),
(2, 'INS002', '李教员', '1', '1985-07-22', '110101198507220022', '13900001002', 'li_instructor@feian.com', '实践培训科', '中级教员', '2015-09-01', '3', '2016-03-01', '3', '工程师', '0', '/avatars/instructor2.jpg', '实践培训专家，专注实操技能培训', '0', NOW(), NOW(), 'admin', 'admin', 0, 'R44飞行教员'),
(3, 'INS003', '王教员', '0', '1982-11-08', '110101198211080033', '13900001003', 'wang_instructor@feian.com', '理论培训科', '高级教员', '2012-04-01', '4', '2013-01-01', '4', '高级工程师', '0', '/avatars/instructor3.jpg', '理论教学专家，擅长复杂理论讲解', '0', NOW(), NOW(), 'admin', 'admin', 0, '地勤理论教员');

-- ----------------------------
-- 培训计划表初始化数据
-- ----------------------------
INSERT INTO tms_training_plan VALUES
(1, '2025年AS350空勤初始培训计划', 'PLAN-2025-AS350-AIRCREW-001', 1, 1, 1, '2025-03-01', '2025-05-30', 80.0, 120.0, 10, 'AS350直升机空勤人员初始培训，包括理论学习和飞行训练', '2', NOW(), NOW(), 'admin', 'admin', 0, '2025年度重点培训计划'),
(2, '2025年R44空勤初始培训计划', 'PLAN-2025-R44-AIRCREW-001', 2, 1, 1, '2025-04-01', '2025-06-30', 60.0, 100.0, 8, 'R44直升机空勤人员初始培训，注重实用性和安全性', '2', NOW(), NOW(), 'admin', 'admin', 0, 'R44专项培训计划'),
(3, '2025年地勤专业培训计划', 'PLAN-2025-GROUND-001', 1, 2, 1, '2025-02-15', '2025-04-30', 120.0, 80.0, 15, '直升机地勤人员专业技能培训', '2', NOW(), NOW(), 'admin', 'admin', 0, '地勤人员培训计划');

-- ----------------------------
-- 培训班次表初始化数据
-- ----------------------------
INSERT INTO tms_training_class VALUES
(1, 'CLASS-2025-001', 'AS350空勤第一期培训班', 1, 1, 1, 1, '2025-03-01', '2025-05-30', NULL, NULL, 10, 0, 1, 'AS350直升机空勤人员初始培训第一期', '0', NOW(), NOW(), 'admin', 'admin', 0, '首期AS350培训班'),
(2, 'CLASS-2025-002', 'R44空勤第一期培训班', 2, 2, 1, 1, '2025-04-01', '2025-06-30', NULL, NULL, 8, 0, 2, 'R44直升机空勤人员初始培训第一期', '0', NOW(), NOW(), 'admin', 'admin', 0, '首期R44培训班');

-- 更新追踪记录以反映TMS表的创建
INSERT INTO trace_log VALUES(4, 'tms_machine_type', 'CREATE_TABLE', 'ALL', NULL, 
  '{"table_name": "tms_machine_type", "columns": 16, "indexes": 4}', 
  'table_structure', 1, 'admin', NOW(), '127.0.0.1', '创建机型管理表');

INSERT INTO trace_log VALUES(5, 'tms_courseware', 'INSERT', '1-5', NULL, 
  '{"count": 5, "courses": ["AS350基础理论", "AS350飞行训练", "R44基础理论", "R44飞行训练", "地勤基础理论"]}', 
  'initial_data', 1, 'admin', NOW(), '127.0.0.1', '初始化课件数据');

INSERT INTO trace_log VALUES(6, 'tms_student', 'INSERT', '1-3', NULL, 
  '{"count": 3, "students": ["张三", "李四", "王五"]}', 
  'initial_data', 1, 'admin', NOW(), '127.0.0.1', '初始化学员数据');

INSERT INTO trace_log VALUES(7, 'tms_instructor', 'INSERT', '1-3', NULL, 
  '{"count": 3, "instructors": ["张教员", "李教员", "王教员"]}', 
  'initial_data', 1, 'admin', NOW(), '127.0.0.1', '初始化教员数据');

-- 推荐下一步操作
SELECT '=== TMS业务系统初始化完成 ===' AS message;
SELECT CONCAT('TMS业务表数量: ', 16) AS tms_tables;
SELECT CONCAT('机型数据: ', (SELECT COUNT(*) FROM tms_machine_type)) AS machine_types;
SELECT CONCAT('专业数据: ', (SELECT COUNT(*) FROM tms_major)) AS majors;
SELECT CONCAT('培训类型: ', (SELECT COUNT(*) FROM tms_training_type)) AS training_types;
SELECT CONCAT('培训能力: ', (SELECT COUNT(*) FROM tms_training_ability)) AS training_abilities;
SELECT CONCAT('课件数量: ', (SELECT COUNT(*) FROM tms_courseware)) AS coursewares;
SELECT CONCAT('学员数量: ', (SELECT COUNT(*) FROM tms_student)) AS students;
SELECT CONCAT('教员数量: ', (SELECT COUNT(*) FROM tms_instructor)) AS instructors;
SELECT CONCAT('培训计划: ', (SELECT COUNT(*) FROM tms_training_plan)) AS training_plans;
SELECT CONCAT('培训班次: ', (SELECT COUNT(*) FROM tms_training_class)) AS training_classes;

SELECT '=== 建议下一步操作 ===' AS next_steps;
SELECT '1. 验证TMS业务表结构和数据完整性' AS step1;
SELECT '2. 测试MyBatis-Plus实体映射和CRUD操作' AS step2;
SELECT '3. 配置TMS业务相关菜单权限' AS step3;
SELECT '4. 完善培训计划明细和班次学员关联' AS step4;
SELECT '5. 开发和测试TMS业务功能模块' AS step5;