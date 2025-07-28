-- ==========================================
-- TMS 培训管理系统完整初始化脚本
-- 生成时间: 2025-07-24
-- 说明: 包含数据库结构和基础数据
-- ==========================================

-- 设置字符集
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ==========================================
-- 1. 删除已存在的表
-- ==========================================

-- 删除TMS业务表
DROP TABLE IF EXISTS `tms_training_record`;
DROP TABLE IF EXISTS `tms_training_plan_schedule`;
DROP TABLE IF EXISTS `tms_training_plan_instructor`;
DROP TABLE IF EXISTS `tms_training_plan_detail`;
DROP TABLE IF EXISTS `tms_training_plan`;
DROP TABLE IF EXISTS `tms_class_student`;
DROP TABLE IF EXISTS `tms_training_class`;
DROP TABLE IF EXISTS `tms_certificate`;
DROP TABLE IF EXISTS `tms_student_ability`;
DROP TABLE IF EXISTS `tms_student_machine_type`;
DROP TABLE IF EXISTS `tms_student`;
DROP TABLE IF EXISTS `tms_instructor_ability`;
DROP TABLE IF EXISTS `tms_instructor_machine_type`;
DROP TABLE IF EXISTS `tms_instructor`;
DROP TABLE IF EXISTS `tms_courseware_file`;
DROP TABLE IF EXISTS `tms_courseware`;
DROP TABLE IF EXISTS `tms_outline_chapter`;
DROP TABLE IF EXISTS `tms_training_outline`;
DROP TABLE IF EXISTS `tms_training_ability`;
DROP TABLE IF EXISTS `tms_training_type`;
DROP TABLE IF EXISTS `tms_major`;
DROP TABLE IF EXISTS `tms_machine_type`;

-- 删除系统表
DROP TABLE IF EXISTS `sys_user_role`;
DROP TABLE IF EXISTS `sys_user_post`;
DROP TABLE IF EXISTS `sys_role_menu`;
DROP TABLE IF EXISTS `sys_role_dept`;
DROP TABLE IF EXISTS `sys_oper_log`;
DROP TABLE IF EXISTS `sys_logininfor`;
DROP TABLE IF EXISTS `sys_user`;
DROP TABLE IF EXISTS `sys_role`;
DROP TABLE IF EXISTS `sys_post`;
DROP TABLE IF EXISTS `sys_notice`;
DROP TABLE IF EXISTS `sys_menu`;
DROP TABLE IF EXISTS `sys_dict_data`;
DROP TABLE IF EXISTS `sys_dict_type`;
DROP TABLE IF EXISTS `sys_dept`;
DROP TABLE IF EXISTS `sys_config`;

-- ==========================================
-- 2. 创建系统基础表
-- ==========================================

-- 系统配置表
CREATE TABLE `sys_config` (
    `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
    `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
    `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
    `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数配置表';

-- 部门表
CREATE TABLE `sys_dept` (
    `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `parent_id` bigint DEFAULT '0' COMMENT '父部门id',
    `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
    `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
    `order_num` int DEFAULT '0' COMMENT '显示顺序',
    `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
    `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
    `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
    `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

-- 字典类型表
CREATE TABLE `sys_dict_type` (
    `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
    `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_id`),
    UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';

-- 字典数据表
CREATE TABLE `sys_dict_data` (
    `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
    `dict_sort` int DEFAULT '0' COMMENT '字典排序',
    `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
    `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
    `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
    `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
    `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';

-- 菜单权限表
CREATE TABLE `sys_menu` (
    `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
    `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
    `order_num` int DEFAULT '0' COMMENT '显示顺序',
    `path` varchar(200) DEFAULT '' COMMENT '路由地址',
    `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
    `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
    `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
    `is_cache` int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
    `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
    `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- 岗位信息表
CREATE TABLE `sys_post` (
    `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
    `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
    `post_sort` int NOT NULL COMMENT '显示顺序',
    `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='岗位信息表';

-- 角色信息表
CREATE TABLE `sys_role` (
    `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name` varchar(30) NOT NULL COMMENT '角色名称',
    `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
    `role_sort` int NOT NULL COMMENT '显示顺序',
    `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
    `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
    `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- 用户信息表
CREATE TABLE `sys_user` (
    `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
    `user_name` varchar(30) NOT NULL COMMENT '用户账号',
    `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
    `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
    `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
    `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
    `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
    `password` varchar(100) DEFAULT '' COMMENT '密码',
    `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
    `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- 通知公告表
CREATE TABLE `sys_notice` (
    `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
    `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
    `notice_content` longblob COMMENT '公告内容',
    `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告表';

-- 操作日志记录
CREATE TABLE `sys_oper_log` (
    `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title` varchar(50) DEFAULT '' COMMENT '模块标题',
    `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method` varchar(100) DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
    `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
    `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
    `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
    `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
    `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
    `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
    `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
    `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
    `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
    `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
    `cost_time` bigint DEFAULT '0' COMMENT '消耗时间',
    PRIMARY KEY (`oper_id`),
    KEY `idx_sys_oper_log_bt` (`business_type`),
    KEY `idx_sys_oper_log_s` (`status`),
    KEY `idx_sys_oper_log_ot` (`oper_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';

-- 系统访问记录
CREATE TABLE `sys_logininfor` (
    `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
    `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
    `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
    `os` varchar(50) DEFAULT '' COMMENT '操作系统',
    `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
    `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
    `login_time` datetime DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`info_id`),
    KEY `idx_sys_logininfor_s` (`status`),
    KEY `idx_sys_logininfor_lt` (`login_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';

-- 角色和部门关联表
CREATE TABLE `sys_role_dept` (
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `dept_id` bigint NOT NULL COMMENT '部门ID',
    PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和部门关联表';

-- 角色和菜单关联表
CREATE TABLE `sys_role_menu` (
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `menu_id` bigint NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';

-- 用户和岗位关联表
CREATE TABLE `sys_user_post` (
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `post_id` bigint NOT NULL COMMENT '岗位ID',
    PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与岗位关联表';

-- 用户和角色关联表
CREATE TABLE `sys_user_role` (
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';

-- ==========================================
-- 3. 创建TMS业务表
-- ==========================================

-- 机型管理表
CREATE TABLE `tms_machine_type` (
    `machine_type_id` bigint NOT NULL AUTO_INCREMENT COMMENT '机型ID',
    `machine_type_code` varchar(50) NOT NULL COMMENT '机型编码',
    `machine_type_name` varchar(100) NOT NULL COMMENT '机型名称',
    `manufacturer` varchar(100) DEFAULT NULL COMMENT '制造商',
    `model_series` varchar(50) DEFAULT NULL COMMENT '型号系列',
    `max_seats` int DEFAULT NULL COMMENT '最大座位数',
    `max_weight` decimal(10,2) DEFAULT NULL COMMENT '最大起飞重量(kg)',
    `engine_type` varchar(100) DEFAULT NULL COMMENT '发动机型号',
    `avionics_system` varchar(200) DEFAULT NULL COMMENT '航电系统',
    `certification_level` varchar(50) DEFAULT NULL COMMENT '适航认证等级',
    `image_url` varchar(500) DEFAULT NULL COMMENT '机型图片URL',
    `technical_specs` text COMMENT '技术规格说明',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`machine_type_id`),
    UNIQUE KEY `uk_machine_type_code` (`machine_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机型管理表';

-- 专业管理表
CREATE TABLE `tms_major` (
    `major_id` bigint NOT NULL AUTO_INCREMENT COMMENT '专业ID',
    `major_code` varchar(50) NOT NULL COMMENT '专业编码',
    `major_name` varchar(100) NOT NULL COMMENT '专业名称',
    `major_category` varchar(50) DEFAULT NULL COMMENT '专业类别（空勤/地勤）',
    `description` text COMMENT '专业描述',
    `required_qualifications` text COMMENT '所需资质要求',
    `training_duration` int DEFAULT NULL COMMENT '标准培训周期（天）',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`major_id`),
    UNIQUE KEY `uk_major_code` (`major_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='专业管理表';

-- 培训类型表
CREATE TABLE `tms_training_type` (
    `training_type_id` bigint NOT NULL AUTO_INCREMENT COMMENT '培训类型ID',
    `type_code` varchar(50) NOT NULL COMMENT '类型编码',
    `type_name` varchar(100) NOT NULL COMMENT '类型名称',
    `description` text COMMENT '类型描述',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`training_type_id`),
    UNIQUE KEY `uk_type_code` (`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训类型表';

-- 培训能力表
CREATE TABLE `tms_training_ability` (
    `training_ability_id` bigint NOT NULL AUTO_INCREMENT COMMENT '培训能力ID',
    `ability_code` varchar(50) NOT NULL COMMENT '能力编码',
    `ability_name` varchar(100) NOT NULL COMMENT '能力名称',
    `description` text COMMENT '能力描述',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`training_ability_id`),
    UNIQUE KEY `uk_ability_code` (`ability_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训能力表';

-- 培训大纲表
CREATE TABLE `tms_training_outline` (
    `outline_id` bigint NOT NULL AUTO_INCREMENT COMMENT '大纲ID',
    `outline_code` varchar(50) NOT NULL COMMENT '大纲编码',
    `outline_name` varchar(200) NOT NULL COMMENT '大纲名称',
    `machine_type_id` bigint NOT NULL COMMENT '机型ID',
    `major_id` bigint NOT NULL COMMENT '专业ID',
    `training_ability_id` bigint NOT NULL COMMENT '培训能力ID',
    `outline_version` varchar(20) DEFAULT '1.0' COMMENT '大纲版本',
    `total_theory_hours` decimal(5,1) DEFAULT '0.0' COMMENT '理论总学时',
    `total_practice_hours` decimal(5,1) DEFAULT '0.0' COMMENT '实践总学时',
    `outline_content` longtext COMMENT '大纲内容',
    `approval_status` char(1) DEFAULT '0' COMMENT '审批状态（0待审批 1已审批 2已发布）',
    `effective_date` date DEFAULT NULL COMMENT '生效日期',
    `expire_date` date DEFAULT NULL COMMENT '失效日期',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`outline_id`),
    UNIQUE KEY `uk_outline_code` (`outline_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训大纲表';

-- 大纲章节表
CREATE TABLE `tms_outline_chapter` (
    `chapter_id` bigint NOT NULL AUTO_INCREMENT COMMENT '章节ID',
    `outline_id` bigint NOT NULL COMMENT '大纲ID',
    `parent_id` bigint DEFAULT '0' COMMENT '父章节ID',
    `chapter_code` varchar(50) NOT NULL COMMENT '章节编码',
    `chapter_name` varchar(200) NOT NULL COMMENT '章节名称',
    `chapter_level` int DEFAULT '1' COMMENT '章节层级',
    `sort_order` int DEFAULT '0' COMMENT '排序顺序',
    `theory_hours` decimal(4,1) DEFAULT '0.0' COMMENT '理论学时',
    `practice_hours` decimal(4,1) DEFAULT '0.0' COMMENT '实践学时',
    `learning_objectives` text COMMENT '学习目标',
    `chapter_content` longtext COMMENT '章节内容',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`chapter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='大纲章节表';

-- 课件管理表
CREATE TABLE `tms_courseware` (
    `courseware_id` bigint NOT NULL AUTO_INCREMENT COMMENT '课件ID',
    `courseware_code` varchar(50) NOT NULL COMMENT '课件编码',
    `courseware_name` varchar(200) NOT NULL COMMENT '课件名称',
    `machine_type_id` bigint NOT NULL COMMENT '机型ID',
    `major_id` bigint NOT NULL COMMENT '专业ID',
    `training_type_id` bigint NOT NULL COMMENT '培训类型ID',
    `ata_chapter` varchar(20) DEFAULT NULL COMMENT 'ATA章节',
    `course_category` varchar(50) DEFAULT NULL COMMENT '课程类别',
    `theory_hours` decimal(4,1) DEFAULT '0.0' COMMENT '理论学时',
    `practice_hours` decimal(4,1) DEFAULT '0.0' COMMENT '实践学时',
    `course_objectives` text COMMENT '课程目标',
    `course_outline` longtext COMMENT '课程大纲',
    `prerequisite_courses` varchar(500) DEFAULT NULL COMMENT '前置课程要求',
    `assessment_method` varchar(200) DEFAULT NULL COMMENT '考核方式',
    `difficulty_level` char(1) DEFAULT '1' COMMENT '难度等级（1初级 2中级 3高级）',
    `version` varchar(20) DEFAULT '1.0' COMMENT '版本号',
    `author` varchar(100) DEFAULT NULL COMMENT '课件作者',
    `reviewer` varchar(100) DEFAULT NULL COMMENT '审核人',
    `approval_status` char(1) DEFAULT '0' COMMENT '审批状态（0待审批 1已审批 2已发布）',
    `publish_date` date DEFAULT NULL COMMENT '发布日期',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`courseware_id`),
    UNIQUE KEY `uk_courseware_code` (`courseware_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课件管理表';

-- 课件文件表
CREATE TABLE `tms_courseware_file` (
    `file_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件ID',
    `courseware_id` bigint NOT NULL COMMENT '课件ID',
    `file_name` varchar(200) NOT NULL COMMENT '文件名称',
    `file_type` varchar(50) NOT NULL COMMENT '文件类型（教材/教案/PPT/CBT/工卡等）',
    `file_format` varchar(20) DEFAULT NULL COMMENT '文件格式',
    `file_size` bigint DEFAULT NULL COMMENT '文件大小（字节）',
    `file_path` varchar(500) NOT NULL COMMENT '文件路径',
    `original_name` varchar(200) DEFAULT NULL COMMENT '原始文件名',
    `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
    `download_count` int DEFAULT '0' COMMENT '下载次数',
    `version` varchar(20) DEFAULT '1.0' COMMENT '文件版本',
    `description` text COMMENT '文件描述',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课件文件表';

-- 教员表
CREATE TABLE `tms_instructor` (
    `instructor_id` bigint NOT NULL AUTO_INCREMENT COMMENT '教员ID',
    `instructor_code` varchar(50) NOT NULL COMMENT '教员工号',
    `instructor_name` varchar(100) NOT NULL COMMENT '教员姓名',
    `gender` char(1) DEFAULT '0' COMMENT '性别（0男 1女）',
    `birth_date` date DEFAULT NULL COMMENT '出生日期',
    `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
    `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号码',
    `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
    `education` varchar(50) DEFAULT NULL COMMENT '学历',
    `professional_title` varchar(50) DEFAULT NULL COMMENT '职称',
    `department` varchar(100) DEFAULT NULL COMMENT '所属部门',
    `hire_date` date DEFAULT NULL COMMENT '入职日期',
    `instructor_level` varchar(20) DEFAULT NULL COMMENT '教员等级',
    `total_teaching_hours` decimal(8,1) DEFAULT '0.0' COMMENT '累计授课时数',
    `specialties` varchar(500) DEFAULT NULL COMMENT '专业特长',
    `qualifications` text COMMENT '资质证书',
    `teaching_experience` text COMMENT '教学经历',
    `photo_url` varchar(500) DEFAULT NULL COMMENT '照片URL',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`instructor_id`),
    UNIQUE KEY `uk_instructor_code` (`instructor_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教员信息表';

-- 教员机型关联表
CREATE TABLE `tms_instructor_machine_type` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `instructor_id` bigint NOT NULL COMMENT '教员ID',
    `machine_type_id` bigint NOT NULL COMMENT '机型ID',
    `qualification_level` varchar(20) DEFAULT NULL COMMENT '资质等级',
    `certification_date` date DEFAULT NULL COMMENT '取证日期',
    `expiry_date` date DEFAULT NULL COMMENT '到期日期',
    `certificate_number` varchar(100) DEFAULT NULL COMMENT '证书编号',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_instructor_machine` (`instructor_id`,`machine_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教员机型关联表';

-- 教员授课能力表
CREATE TABLE `tms_instructor_ability` (
    `ability_id` bigint NOT NULL AUTO_INCREMENT COMMENT '能力ID',
    `instructor_id` bigint NOT NULL COMMENT '教员ID',
    `machine_type_id` bigint NOT NULL COMMENT '机型ID',
    `major_id` bigint NOT NULL COMMENT '专业ID',
    `training_ability_id` bigint NOT NULL COMMENT '培训能力ID',
    `qualification_level` varchar(20) DEFAULT NULL COMMENT '资格等级',
    `authorization_date` date DEFAULT NULL COMMENT '授权日期',
    `expiry_date` date DEFAULT NULL COMMENT '到期日期',
    `authorization_agency` varchar(100) DEFAULT NULL COMMENT '授权机构',
    `certificate_number` varchar(100) DEFAULT NULL COMMENT '证书号码',
    `annual_review_date` date DEFAULT NULL COMMENT '年度复查日期',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用 2过期）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`ability_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教员授课能力表';

-- 学员表
CREATE TABLE `tms_student` (
    `student_id` bigint NOT NULL AUTO_INCREMENT COMMENT '学员ID',
    `student_code` varchar(50) NOT NULL COMMENT '学员学号',
    `student_name` varchar(100) NOT NULL COMMENT '学员姓名',
    `gender` char(1) DEFAULT '0' COMMENT '性别（0男 1女）',
    `birth_date` date DEFAULT NULL COMMENT '出生日期',
    `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
    `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号码',
    `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
    `education` varchar(50) DEFAULT NULL COMMENT '学历',
    `work_unit` varchar(200) DEFAULT NULL COMMENT '工作单位',
    `position` varchar(100) DEFAULT NULL COMMENT '职位',
    `primary_machine_type_id` bigint DEFAULT NULL COMMENT '主要机型ID',
    `primary_major_id` bigint DEFAULT NULL COMMENT '主要专业ID',
    `enrollment_date` date DEFAULT NULL COMMENT '入学日期',
    `graduation_date` date DEFAULT NULL COMMENT '毕业日期',
    `total_training_hours` decimal(8,1) DEFAULT '0.0' COMMENT '累计培训时数',
    `flight_hours` decimal(8,1) DEFAULT '0.0' COMMENT '飞行小时数',
    `emergency_contact` varchar(100) DEFAULT NULL COMMENT '紧急联系人',
    `emergency_phone` varchar(11) DEFAULT NULL COMMENT '紧急联系电话',
    `medical_certificate` varchar(100) DEFAULT NULL COMMENT '体检证书号',
    `medical_expiry_date` date DEFAULT NULL COMMENT '体检到期日期',
    `photo_url` varchar(500) DEFAULT NULL COMMENT '照片URL',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用 2毕业 3退学）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`student_id`),
    UNIQUE KEY `uk_student_code` (`student_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学员信息表';

-- 学员机型关联表
CREATE TABLE `tms_student_machine_type` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `student_id` bigint NOT NULL COMMENT '学员ID',
    `machine_type_id` bigint NOT NULL COMMENT '机型ID',
    `training_status` char(1) DEFAULT '0' COMMENT '培训状态（0计划中 1培训中 2已完成 3已暂停）',
    `start_date` date DEFAULT NULL COMMENT '开始日期',
    `completion_date` date DEFAULT NULL COMMENT '完成日期',
    `total_hours` decimal(6,1) DEFAULT '0.0' COMMENT '总培训时数',
    `theory_hours` decimal(6,1) DEFAULT '0.0' COMMENT '理论时数',
    `practice_hours` decimal(6,1) DEFAULT '0.0' COMMENT '实践时数',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_student_machine` (`student_id`,`machine_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学员机型关联表';

-- 学员学习能力表
CREATE TABLE `tms_student_ability` (
    `ability_id` bigint NOT NULL AUTO_INCREMENT COMMENT '能力ID',
    `student_id` bigint NOT NULL COMMENT '学员ID',
    `machine_type_id` bigint NOT NULL COMMENT '机型ID',
    `major_id` bigint NOT NULL COMMENT '专业ID',
    `training_ability_id` bigint NOT NULL COMMENT '培训能力ID',
    `current_level` varchar(20) DEFAULT NULL COMMENT '当前等级',
    `target_level` varchar(20) DEFAULT NULL COMMENT '目标等级',
    `progress_percentage` decimal(5,2) DEFAULT '0.00' COMMENT '进度百分比',
    `theory_score` decimal(5,2) DEFAULT NULL COMMENT '理论成绩',
    `practice_score` decimal(5,2) DEFAULT NULL COMMENT '实践成绩',
    `overall_score` decimal(5,2) DEFAULT NULL COMMENT '综合成绩',
    `assessment_date` date DEFAULT NULL COMMENT '考核日期',
    `qualification_status` char(1) DEFAULT '0' COMMENT '资质状态（0培训中 1合格 2不合格 3待考核）',
    `certificate_number` varchar(100) DEFAULT NULL COMMENT '证书编号',
    `issue_date` date DEFAULT NULL COMMENT '颁发日期',
    `expiry_date` date DEFAULT NULL COMMENT '到期日期',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`ability_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学员学习能力表';

-- 培训计划表
CREATE TABLE `tms_training_plan` (
    `plan_id` bigint NOT NULL AUTO_INCREMENT COMMENT '培训计划ID',
    `plan_code` varchar(50) NOT NULL COMMENT '计划编码',
    `plan_name` varchar(200) NOT NULL COMMENT '计划名称',
    `machine_type_id` bigint NOT NULL COMMENT '机型ID',
    `major_id` bigint NOT NULL COMMENT '专业ID',
    `training_ability_id` bigint NOT NULL COMMENT '培训能力ID',
    `plan_type` char(1) DEFAULT '1' COMMENT '计划类型（1培训 2复训 3考试）',
    `target_students` int DEFAULT '0' COMMENT '目标学员数',
    `plan_start_date` date DEFAULT NULL COMMENT '计划开始日期',
    `plan_end_date` date DEFAULT NULL COMMENT '计划结束日期',
    `total_theory_hours` decimal(6,1) DEFAULT '0.0' COMMENT '理论总学时',
    `total_practice_hours` decimal(6,1) DEFAULT '0.0' COMMENT '实践总学时',
    `training_location` varchar(200) DEFAULT NULL COMMENT '培训地点',
    `training_objectives` text COMMENT '培训目标',
    `training_requirements` text COMMENT '培训要求',
    `assessment_criteria` text COMMENT '考核标准',
    `plan_status` char(1) DEFAULT '0' COMMENT '计划状态（0草稿 1待审批 2已审批 3执行中 4已完成 5已取消）',
    `approval_date` date DEFAULT NULL COMMENT '审批日期',
    `approver` varchar(100) DEFAULT NULL COMMENT '审批人',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`plan_id`),
    UNIQUE KEY `uk_plan_code` (`plan_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划表';

-- 培训计划明细表
CREATE TABLE `tms_training_plan_detail` (
    `detail_id` bigint NOT NULL AUTO_INCREMENT COMMENT '明细ID',
    `plan_id` bigint NOT NULL COMMENT '培训计划ID',
    `courseware_id` bigint NOT NULL COMMENT '课件ID',
    `sequence_order` int DEFAULT '0' COMMENT '顺序号',
    `theory_hours` decimal(4,1) DEFAULT '0.0' COMMENT '理论学时',
    `practice_hours` decimal(4,1) DEFAULT '0.0' COMMENT '实践学时',
    `training_method` varchar(50) DEFAULT NULL COMMENT '培训方式',
    `training_location` varchar(200) DEFAULT NULL COMMENT '培训地点',
    `required_equipment` varchar(500) DEFAULT NULL COMMENT '所需设备',
    `assessment_required` char(1) DEFAULT '0' COMMENT '是否需要考核（0否 1是）',
    `assessment_method` varchar(100) DEFAULT NULL COMMENT '考核方式',
    `pass_criteria` varchar(200) DEFAULT NULL COMMENT '通过标准',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划明细表';

-- 培训计划教员分配表
CREATE TABLE `tms_training_plan_instructor` (
    `assignment_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分配ID',
    `plan_id` bigint NOT NULL COMMENT '培训计划ID',
    `instructor_id` bigint NOT NULL COMMENT '教员ID',
    `role_type` varchar(20) DEFAULT 'INSTRUCTOR' COMMENT '角色类型（CHIEF_INSTRUCTOR主教员, INSTRUCTOR教员, ASSISTANT助教）',
    `assigned_courses` varchar(500) DEFAULT NULL COMMENT '分配课程',
    `assigned_hours` decimal(6,1) DEFAULT '0.0' COMMENT '分配学时',
    `assignment_date` date DEFAULT NULL COMMENT '分配日期',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`assignment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划教员分配表';

-- 培训计划课程表
CREATE TABLE `tms_training_plan_schedule` (
    `schedule_id` bigint NOT NULL AUTO_INCREMENT COMMENT '课程表ID',
    `plan_id` bigint NOT NULL COMMENT '培训计划ID',
    `courseware_id` bigint NOT NULL COMMENT '课件ID',
    `instructor_id` bigint DEFAULT NULL COMMENT '授课教员ID',
    `training_date` date NOT NULL COMMENT '培训日期',
    `start_time` time NOT NULL COMMENT '开始时间',
    `end_time` time NOT NULL COMMENT '结束时间',
    `training_type` char(1) DEFAULT '1' COMMENT '培训类型（1理论 2实践）',
    `classroom` varchar(100) DEFAULT NULL COMMENT '教室/场地',
    `training_hours` decimal(4,1) DEFAULT '0.0' COMMENT '培训学时',
    `attendance_required` char(1) DEFAULT '1' COMMENT '是否需要考勤（0否 1是）',
    `schedule_status` char(1) DEFAULT '0' COMMENT '课程状态（0计划中 1进行中 2已完成 3已取消）',
    `actual_start_time` datetime DEFAULT NULL COMMENT '实际开始时间',
    `actual_end_time` datetime DEFAULT NULL COMMENT '实际结束时间',
    `attendance_count` int DEFAULT '0' COMMENT '出勤人数',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划课程表';

-- 培训班次表
CREATE TABLE `tms_training_class` (
    `training_class_id` bigint NOT NULL AUTO_INCREMENT COMMENT '培训班次ID',
    `class_code` varchar(50) NOT NULL COMMENT '班次编号',
    `class_name` varchar(200) NOT NULL COMMENT '班次名称',
    `training_plan_id` bigint NOT NULL COMMENT '培训计划ID',
    `machine_type_id` bigint NOT NULL COMMENT '机型ID',
    `major_id` bigint NOT NULL COMMENT '专业ID',
    `training_ability_id` bigint NOT NULL COMMENT '培训能力ID',
    `plan_start_time` date DEFAULT NULL COMMENT '计划开始时间',
    `plan_end_time` date DEFAULT NULL COMMENT '计划结束时间',
    `actual_start_date` date DEFAULT NULL COMMENT '实际开始时间',
    `actual_end_date` date DEFAULT NULL COMMENT '实际结束时间',
    `plan_student_count` int DEFAULT '0' COMMENT '计划人数',
    `actual_student_count` int DEFAULT '0' COMMENT '实际人数',
    `primary_instructor_id` bigint DEFAULT NULL COMMENT '主要教员ID',
    `class_desc` text COMMENT '班次描述',
    `status` char(1) DEFAULT '0' COMMENT '状态（0计划中 1进行中 2已完成 3已取消）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`training_class_id`),
    UNIQUE KEY `uk_class_code` (`class_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训班次表';

-- 班次学员关联表
CREATE TABLE `tms_class_student` (
    `class_student_id` bigint NOT NULL AUTO_INCREMENT COMMENT '班次学员关联ID',
    `training_class_id` bigint NOT NULL COMMENT '培训班次ID',
    `student_id` bigint NOT NULL COMMENT '学员ID',
    `enroll_time` datetime DEFAULT NULL COMMENT '报名时间',
    `student_status` char(1) DEFAULT '0' COMMENT '学员状态（0正常 1请假 2退课 3毕业）',
    `withdraw_reason` varchar(500) DEFAULT NULL COMMENT '退课原因',
    `withdraw_time` datetime DEFAULT NULL COMMENT '退课时间',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`class_student_id`),
    UNIQUE KEY `uk_class_student` (`training_class_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班次学员关联表';

-- 培训记录表
CREATE TABLE `tms_training_record` (
    `training_record_id` bigint NOT NULL AUTO_INCREMENT COMMENT '培训记录ID',
    `student_id` bigint NOT NULL COMMENT '学员ID',
    `training_class_id` bigint DEFAULT NULL COMMENT '培训班次ID',
    `courseware_id` bigint DEFAULT NULL COMMENT '课件ID',
    `instructor_id` bigint DEFAULT NULL COMMENT '教员ID',
    `training_date` date NOT NULL COMMENT '培训日期',
    `training_method` char(1) DEFAULT '1' COMMENT '培训类型（1理论 2实践）',
    `training_hours` decimal(4,1) DEFAULT '0.0' COMMENT '培训时长(小时)',
    `attendance_status` char(1) DEFAULT '1' COMMENT '出勤状态（1正常 2迟到 3早退 4请假 5缺勤）',
    `performance_score` decimal(5,2) DEFAULT NULL COMMENT '课堂表现评分',
    `homework_score` decimal(5,2) DEFAULT NULL COMMENT '作业成绩',
    `exam_score` decimal(5,2) DEFAULT NULL COMMENT '考试成绩',
    `total_score` decimal(5,2) DEFAULT NULL COMMENT '综合评分',
    `training_content` text COMMENT '培训内容',
    `learning_notes` text COMMENT '学习心得',
    `instructor_comment` text COMMENT '教员评价',
    `training_effect` char(1) DEFAULT NULL COMMENT '培训效果（1优秀 2良好 3一般 4较差）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`training_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训记录表';

-- 证书管理表
CREATE TABLE `tms_certificate` (
    `certificate_id` bigint NOT NULL AUTO_INCREMENT COMMENT '证书ID',
    `certificate_code` varchar(50) NOT NULL COMMENT '证书编号',
    `certificate_name` varchar(200) NOT NULL COMMENT '证书名称',
    `student_id` bigint NOT NULL COMMENT '学员ID',
    `machine_type_id` bigint NOT NULL COMMENT '机型ID',
    `major_id` bigint NOT NULL COMMENT '专业ID',
    `training_ability_id` bigint NOT NULL COMMENT '培训能力ID',
    `training_class_id` bigint DEFAULT NULL COMMENT '培训班次ID',
    `certificate_type` varchar(50) DEFAULT NULL COMMENT '证书类型',
    `certificate_level` varchar(20) DEFAULT NULL COMMENT '证书等级',
    `issue_date` date NOT NULL COMMENT '颁发日期',
    `valid_until` date DEFAULT NULL COMMENT '有效期至',
    `issuing_authority` varchar(200) DEFAULT NULL COMMENT '颁发机构',
    `certificate_status` char(1) DEFAULT '0' COMMENT '证书状态（0有效 1过期 2吊销 3挂失）',
    `theory_score` decimal(5,2) DEFAULT NULL COMMENT '理论成绩',
    `practice_score` decimal(5,2) DEFAULT NULL COMMENT '实践成绩',
    `overall_score` decimal(5,2) DEFAULT NULL COMMENT '综合成绩',
    `assessment_date` date DEFAULT NULL COMMENT '考核日期',
    `assessor` varchar(100) DEFAULT NULL COMMENT '考核员',
    `certificate_file_path` varchar(500) DEFAULT NULL COMMENT '证书文件路径',
    `annual_review_date` date DEFAULT NULL COMMENT '年度复查日期',
    `next_review_date` date DEFAULT NULL COMMENT '下次复查日期',
    `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `is_deleted` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`certificate_id`),
    UNIQUE KEY `uk_certificate_code` (`certificate_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='证书管理表';

-- ==========================================
-- 4. 插入系统基础数据
-- ==========================================

-- 插入系统配置数据
INSERT INTO `sys_config` VALUES 
(1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2024-01-01 00:00:00', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),
(2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2024-01-01 00:00:00', '', NULL, '初始化密码 123456'),
(3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2024-01-01 00:00:00', '', NULL, '深色主题theme-dark，浅色主题theme-light'),
(4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', '2024-01-01 00:00:00', '', NULL, '是否开启验证码功能（true开启，false关闭）'),
(5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2024-01-01 00:00:00', '', NULL, '是否开启注册用户功能（true开启，false关闭）'),
(6, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2024-01-01 00:00:00', '', NULL, '设置IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');

-- 插入部门数据
INSERT INTO `sys_dept` VALUES 
(100, 0, '0', '飞安通航培训中心', 0, '管理员', '15888888888', 'admin@feian.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL),
(101, 100, '0,100', '培训部门', 1, '培训主管', '15888888881', 'training@feian.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL),
(102, 100, '0,100', '教学部门', 2, '教学主管', '15888888882', 'education@feian.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL),
(103, 100, '0,100', '考试中心', 3, '考试主管', '15888888883', 'exam@feian.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL),
(104, 100, '0,100', '质量管理部', 4, '质量主管', '15888888884', 'quality@feian.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL),
(105, 100, '0,100', '设备管理部', 5, '设备主管', '15888888885', 'equipment@feian.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL);

-- 插入字典类型数据
INSERT INTO `sys_dict_type` VALUES 
(1, '用户性别', 'sys_user_sex', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '用户性别列表'),
(2, '菜单状态', 'sys_show_hide', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '菜单状态列表'),
(3, '系统开关', 'sys_normal_disable', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '系统开关列表'),
(10, '培训方式', 'training_method', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '培训方式字典'),
(11, '出勤状态', 'attendance_status', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '出勤状态字典'),
(12, '培训效果', 'training_effect', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '培训效果字典'),
(13, '证书状态', 'certificate_status', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '证书状态字典'),
(14, '班次状态', 'class_status', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '班次状态字典'),
(15, '学员状态', 'student_status', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '学员状态字典');

-- 插入字典数据
INSERT INTO `sys_dict_data` VALUES 
(1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '性别男'),
(2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '性别女'),
(3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '性别未知'),
(4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '显示菜单'),
(5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '隐藏菜单'),
(6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '正常状态'),
(7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '停用状态'),
(20, 1, '理论教学', '1', 'training_method', '', 'primary', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '理论教学'),
(21, 2, '实践训练', '2', 'training_method', '', 'success', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '实践训练'),
(30, 1, '正常', '1', 'attendance_status', '', 'success', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '出勤正常'),
(31, 2, '迟到', '2', 'attendance_status', '', 'warning', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '迟到'),
(32, 3, '早退', '3', 'attendance_status', '', 'warning', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '早退'),
(33, 4, '请假', '4', 'attendance_status', '', 'info', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '请假'),
(34, 5, '缺勤', '5', 'attendance_status', '', 'danger', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '缺勤'),
(40, 1, '优秀', '1', 'training_effect', '', 'success', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '培训效果优秀'),
(41, 2, '良好', '2', 'training_effect', '', 'primary', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '培训效果良好'),
(42, 3, '一般', '3', 'training_effect', '', 'warning', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '培训效果一般'),
(43, 4, '较差', '4', 'training_effect', '', 'danger', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '培训效果较差'),
(50, 1, '有效', '0', 'certificate_status', '', 'success', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '证书有效'),
(51, 2, '过期', '1', 'certificate_status', '', 'danger', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '证书过期'),
(52, 3, '吊销', '2', 'certificate_status', '', 'danger', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '证书吊销'),
(53, 4, '挂失', '3', 'certificate_status', '', 'warning', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '证书挂失'),
(60, 1, '计划中', '0', 'class_status', '', 'info', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '班次计划中'),
(61, 2, '进行中', '1', 'class_status', '', 'primary', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '班次进行中'),
(62, 3, '已完成', '2', 'class_status', '', 'success', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '班次已完成'),
(63, 4, '已取消', '3', 'class_status', '', 'danger', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '班次已取消'),
(70, 1, '正常', '0', 'student_status', '', 'success', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '学员正常'),
(71, 2, '停用', '1', 'student_status', '', 'danger', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '学员停用'),
(72, 3, '毕业', '2', 'student_status', '', 'primary', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '学员毕业'),
(73, 4, '退学', '3', 'student_status', '', 'warning', 'N', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '学员退学');

-- 插入岗位数据
INSERT INTO `sys_post` VALUES 
(1, 'admin', '管理员', 1, '0', 'admin', '2024-01-01 00:00:00', '', NULL, '系统管理员'),
(2, 'instructor', '教员', 2, '0', 'admin', '2024-01-01 00:00:00', '', NULL, '培训教员'),
(3, 'examiner', '考试员', 3, '0', 'admin', '2024-01-01 00:00:00', '', NULL, '考试员'),
(4, 'supervisor', '监督员', 4, '0', 'admin', '2024-01-01 00:00:00', '', NULL, '质量监督员');

-- 插入角色数据
INSERT INTO `sys_role` VALUES 
(1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '超级管理员'),
(2, '培训管理员', 'training_admin', 2, '2', 1, 1, '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '培训管理员'),
(3, '教员', 'instructor', 3, '3', 1, 1, '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '授课教员'),
(4, '学员', 'student', 4, '4', 1, 1, '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '培训学员');

-- ==========================================
-- 5. 插入菜单数据
-- ==========================================