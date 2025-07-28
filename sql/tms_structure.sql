
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Blob类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_CALENDARS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日历信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Cron类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint NOT NULL COMMENT '触发的时间',
  `sched_time` bigint NOT NULL COMMENT '定时器制定的时间',
  `priority` int NOT NULL COMMENT '优先级',
  `state` varchar(16) NOT NULL COMMENT '状态',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='已触发的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) NOT NULL COMMENT '任务组名',
  `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_LOCKS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存储的悲观锁信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='暂停的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='调度器状态表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='简单触发器的信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='同步机制的行锁表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) NOT NULL COMMENT '触发器的类型',
  `start_time` bigint NOT NULL COMMENT '开始时间',
  `end_time` bigint DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `QRTZ_JOB_DETAILS` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='触发器详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table` (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `tpl_web_type` varchar(30) DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table_column` (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度日志表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `route_name` varchar(50) DEFAULT '' COMMENT '路由名称',
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
) ENGINE=InnoDB AUTO_INCREMENT=2142 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(200) DEFAULT '' COMMENT '方法名称',
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
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `pwd_update_date` datetime DEFAULT NULL COMMENT '密码最后更新时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `student_id` bigint DEFAULT NULL COMMENT '学员ID（如果该用户为学员）',
  `current_machine_type_id` bigint DEFAULT NULL COMMENT '当前选择的机型ID（登录时选择）',
  `current_machine_type_name` varchar(100) DEFAULT NULL COMMENT '当前选择的机型名称',
  `instructor_id` bigint DEFAULT NULL COMMENT '教员ID（如果该用户为教员）',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_post` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_certificate` (
  `certificate_id` bigint NOT NULL AUTO_INCREMENT COMMENT '证书ID',
  `certificate_code` varchar(100) NOT NULL COMMENT '证书编号',
  `certificate_name` varchar(200) NOT NULL COMMENT '证书名称',
  `student_id` bigint NOT NULL COMMENT '学员ID',
  `training_class_id` bigint NOT NULL COMMENT '培训班次ID',
  `machine_type_id` bigint NOT NULL COMMENT '机型ID',
  `major_id` bigint NOT NULL COMMENT '专业ID',
  `training_ability_id` bigint NOT NULL COMMENT '培训能力ID',
  `certificate_type` char(1) NOT NULL COMMENT '证书类型（1培训证书 2复训证书 3恢复考试证书）',
  `issue_date` date DEFAULT NULL COMMENT '颁发日期',
  `valid_from` date DEFAULT NULL COMMENT '有效期开始',
  `valid_until` date DEFAULT NULL COMMENT '有效期结束',
  `issue_organization` varchar(200) DEFAULT NULL COMMENT '颁发机构',
  `issuer` varchar(100) DEFAULT NULL COMMENT '签发人',
  `template_path` varchar(500) DEFAULT NULL COMMENT '证书模板路径',
  `certificate_file_path` varchar(500) DEFAULT NULL COMMENT '证书文件路径',
  `certificate_status` char(1) NOT NULL DEFAULT '0' COMMENT '证书状态（0草稿 1已颁发 2已作废 3已过期）',
  `void_reason` text COMMENT '作废原因',
  `void_time` datetime DEFAULT NULL COMMENT '作废时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `student_name` varchar(255) DEFAULT NULL COMMENT '学员姓名',
  `class_name` varchar(255) DEFAULT NULL COMMENT '班次名称',
  `machine_type_name` varchar(255) DEFAULT NULL COMMENT '机型名称',
  `major_name` varchar(255) DEFAULT NULL COMMENT '专业名称',
  `training_ability_name` varchar(255) DEFAULT NULL COMMENT '培训能力名称',
  `certificate_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`certificate_id`),
  UNIQUE KEY `uk_certificate_code` (`certificate_code`),
  KEY `idx_student_id` (`student_id`),
  KEY `idx_training_class_id` (`training_class_id`),
  KEY `idx_machine_type_id` (`machine_type_id`),
  KEY `idx_major_id` (`major_id`),
  KEY `idx_training_ability_id` (`training_ability_id`),
  KEY `idx_certificate_type` (`certificate_type`),
  KEY `idx_certificate_status` (`certificate_status`),
  KEY `idx_issue_date` (`issue_date`),
  KEY `idx_valid_until` (`valid_until`),
  KEY `idx_certificate_student_type_status` (`student_id`,`certificate_type`,`certificate_status`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='证书管理表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_class_student` (
  `class_student_id` bigint NOT NULL AUTO_INCREMENT COMMENT '班次学员关联ID',
  `training_class_id` bigint NOT NULL COMMENT '培训班次ID',
  `student_id` bigint NOT NULL COMMENT '学员ID',
  `enroll_time` datetime DEFAULT NULL COMMENT '报名时间',
  `student_status` char(1) NOT NULL COMMENT '学员状态（0待开班 1培训中 2已结业 3已退班 4请假中）',
  `withdraw_reason` text COMMENT '退班原因',
  `withdraw_time` datetime DEFAULT NULL COMMENT '退班时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`class_student_id`),
  UNIQUE KEY `uk_class_student` (`training_class_id`,`student_id`),
  KEY `idx_training_class_id` (`training_class_id`),
  KEY `idx_student_id` (`student_id`),
  KEY `idx_student_status` (`student_status`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班次学员关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_courseware` (
  `courseware_id` bigint NOT NULL AUTO_INCREMENT COMMENT '课件ID',
  `course_code` varchar(50) NOT NULL COMMENT '课程编码',
  `course_name` varchar(200) NOT NULL COMMENT '课程名称',
  `ata_chapter` varchar(100) DEFAULT NULL COMMENT 'ATA章节',
  `machine_type_id` bigint NOT NULL COMMENT '机型ID',
  `major_id` bigint NOT NULL COMMENT '专业ID',
  `training_type_id` bigint NOT NULL COMMENT '培训类型ID',
  `training_type` varchar(10) DEFAULT '1' COMMENT '培训类型（1理论 2实践）',
  `theory_hours` decimal(10,2) DEFAULT NULL COMMENT '理论培训时长(小时)',
  `practice_hours` decimal(10,2) DEFAULT NULL COMMENT '实践培训时长(小时)',
  `course_desc` text COMMENT '课程描述',
  `course_objective` text COMMENT '课程目标',
  `course_requirement` text COMMENT '课程要求',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`courseware_id`),
  UNIQUE KEY `uk_course_code` (`course_code`),
  KEY `idx_machine_type_id` (`machine_type_id`),
  KEY `idx_major_id` (`major_id`),
  KEY `idx_training_type_id` (`training_type_id`),
  KEY `idx_courseware_status` (`status`),
  KEY `idx_courseware_machine_major_type` (`machine_type_id`,`major_id`,`training_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课件管理表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_courseware_file` (
  `courseware_file_id` bigint NOT NULL AUTO_INCREMENT COMMENT '课件文件ID',
  `courseware_id` bigint NOT NULL COMMENT '课件ID',
  `file_name` varchar(255) NOT NULL COMMENT '文件名称',
  `file_type` varchar(10) DEFAULT NULL COMMENT '文件类型（1教材 2教案 3PPT 4CBT 5工卡 6其他 7VMT）',
  `file_path` varchar(500) NOT NULL COMMENT '文件路径',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小(KB)',
  `file_extension` varchar(20) DEFAULT NULL COMMENT '文件扩展名',
  `file_desc` text COMMENT '文件描述',
  `download_count` int DEFAULT '0' COMMENT '下载次数',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`courseware_file_id`),
  KEY `idx_courseware_id` (`courseware_id`),
  KEY `idx_file_type` (`file_type`),
  KEY `idx_courseware_file_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课件文件表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_instructor` (
  `instructor_id` bigint NOT NULL AUTO_INCREMENT COMMENT '教员ID',
  `instructor_code` varchar(50) NOT NULL COMMENT '教员工号',
  `instructor_name` varchar(100) NOT NULL COMMENT '教员姓名',
  `gender` char(1) DEFAULT NULL COMMENT '性别（0男 1女 2未知）',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `department` varchar(100) DEFAULT NULL COMMENT '部门',
  `position` varchar(100) DEFAULT NULL COMMENT '职位',
  `hire_date` date DEFAULT NULL COMMENT '入职时间',
  `instructor_level` char(1) DEFAULT NULL COMMENT '教员等级（1初级 2中级 3高级 4专家）',
  `teaching_start_date` date DEFAULT NULL COMMENT '从教时间',
  `education` char(1) DEFAULT NULL COMMENT '学历（1中专 2大专 3本科 4硕士 5博士）',
  `technical_title` varchar(100) DEFAULT NULL COMMENT '专业技术职务',
  `teaching_status` char(1) DEFAULT NULL COMMENT '授课状态（0可授课 1授课中 2暂停授课）',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像路径',
  `biography` text COMMENT '个人简介',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `user_id` bigint NOT NULL,
  `professional_level` varchar(50) DEFAULT NULL,
  `working_years` int DEFAULT NULL,
  `instructor_ability_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`instructor_id`),
  UNIQUE KEY `uk_instructor_code` (`instructor_code`),
  UNIQUE KEY `uk_instructor_id_card` (`id_card`),
  KEY `idx_instructor_level` (`instructor_level`),
  KEY `idx_teaching_status` (`teaching_status`),
  KEY `idx_instructor_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_instructor_ability` (
  `instructor_ability_id` bigint NOT NULL AUTO_INCREMENT COMMENT '教员授课能力ID',
  `instructor_id` bigint NOT NULL COMMENT '教员ID',
  `machine_type_id` bigint NOT NULL COMMENT '机型ID',
  `major_id` bigint NOT NULL COMMENT '专业ID',
  `training_type_id` bigint NOT NULL COMMENT '培训类型ID',
  `courseware_id` bigint NOT NULL COMMENT '课件ID',
  `ability_level` char(1) NOT NULL COMMENT '授课资质等级（1初级 2中级 3高级 4专家）',
  `qualification_date` date DEFAULT NULL COMMENT '资质获得时间',
  `valid_until` date DEFAULT NULL COMMENT '资质有效期至',
  `teaching_count` int DEFAULT '0' COMMENT '授课次数',
  `teaching_score` float DEFAULT NULL COMMENT '授课评分',
  `is_primary` char(1) DEFAULT NULL COMMENT '是否主讲（0否 1是）',
  `ability_remark` text COMMENT '能力备注',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`instructor_ability_id`),
  KEY `idx_instructor_id` (`instructor_id`),
  KEY `idx_machine_type_id` (`machine_type_id`),
  KEY `idx_major_id` (`major_id`),
  KEY `idx_training_type_id` (`training_type_id`),
  KEY `idx_courseware_id` (`courseware_id`),
  KEY `idx_ability_level` (`ability_level`),
  KEY `idx_instructor_ability_status` (`status`),
  KEY `idx_instructor_ability_full` (`instructor_id`,`machine_type_id`,`major_id`,`training_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教员授课能力表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_instructor_machine_type` (
  `relation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `instructor_id` bigint NOT NULL COMMENT '教员ID',
  `machine_type_id` bigint NOT NULL COMMENT '机型ID',
  `is_primary` char(1) DEFAULT '0' COMMENT '是否为主要机型（0否 1是）',
  `instructor_level` varchar(20) DEFAULT 'STANDARD' COMMENT '教员等级（STANDARD=标准教员, SENIOR=高级教员, CHIEF=首席教员）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `is_deleted` char(1) DEFAULT '0' COMMENT 'æ˜¯å¦åˆ é™¤ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰',
  PRIMARY KEY (`relation_id`),
  UNIQUE KEY `uk_instructor_machine` (`instructor_id`,`machine_type_id`),
  KEY `idx_instructor_id` (`instructor_id`),
  KEY `idx_machine_type_id` (`machine_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教员机型关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_machine_type` (
  `machine_type_id` bigint NOT NULL AUTO_INCREMENT COMMENT '机型ID',
  `machine_type_name` varchar(100) NOT NULL COMMENT '机型名称',
  `machine_type_code` varchar(50) NOT NULL COMMENT '机型代码',
  `machine_type_image` varchar(500) DEFAULT NULL COMMENT '机型图片路径',
  `machine_type_desc` text COMMENT '机型描述',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`machine_type_id`),
  UNIQUE KEY `uk_machine_type_code` (`machine_type_code`),
  KEY `idx_machine_type_status` (`status`),
  KEY `idx_machine_type_order` (`order_num`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机型管理表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_major` (
  `major_id` bigint NOT NULL AUTO_INCREMENT COMMENT '专业ID',
  `major_name` varchar(100) NOT NULL COMMENT '专业名称',
  `major_code` varchar(50) NOT NULL COMMENT '专业代码',
  `major_type` char(1) NOT NULL COMMENT '专业类型（1空勤 2地勤）',
  `major_desc` text COMMENT '专业描述',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`major_id`),
  UNIQUE KEY `uk_major_code` (`major_code`),
  KEY `idx_major_type` (`major_type`),
  KEY `idx_major_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='专业管理表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_outline_chapter` (
  `chapter_id` bigint NOT NULL AUTO_INCREMENT COMMENT '章节ID',
  `outline_id` bigint NOT NULL COMMENT '大纲ID',
  `chapter_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节编码（如ATA章节）',
  `chapter_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父章节ID',
  `chapter_level` int NOT NULL DEFAULT '1' COMMENT '章节层级',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序',
  `theory_hours` decimal(5,1) DEFAULT '0.0' COMMENT '理论课时',
  `practical_hours` decimal(5,1) DEFAULT '0.0' COMMENT '实作课时',
  `is_required` char(1) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '是否必修（0选修 1必修）',
  `description` text COLLATE utf8mb4_general_ci COMMENT '章节描述',
  `learning_objectives` text COLLATE utf8mb4_general_ci COMMENT '学习目标',
  `status` char(1) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `is_deleted` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT 'æ˜¯å¦åˆ é™¤ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰',
  PRIMARY KEY (`chapter_id`),
  KEY `idx_outline_id` (`outline_id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_chapter_code` (`chapter_code`),
  KEY `idx_sort_order` (`sort_order`),
  CONSTRAINT `fk_outline_chapter_outline` FOREIGN KEY (`outline_id`) REFERENCES `tms_training_outline` (`outline_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='大纲章节表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_student` (
  `student_id` bigint NOT NULL AUTO_INCREMENT COMMENT '学员ID',
  `student_code` varchar(50) NOT NULL COMMENT '学员工号',
  `student_name` varchar(100) NOT NULL COMMENT '学员姓名',
  `gender` char(1) DEFAULT NULL COMMENT '性别（0男 1女 2未知）',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `department` varchar(100) DEFAULT NULL COMMENT '部门',
  `position` varchar(100) DEFAULT NULL COMMENT '职位',
  `hire_date` date DEFAULT NULL COMMENT '入职时间',
  `primary_machine_type_id` bigint DEFAULT NULL COMMENT '主要机型ID',
  `primary_major_id` bigint DEFAULT NULL COMMENT '主要专业ID',
  `education` char(1) DEFAULT NULL COMMENT '学历（1中专 2大专 3本科 4硕士 5博士）',
  `training_status` char(1) DEFAULT NULL COMMENT '培训状态（0待培训 1培训中 2已培训 3培训暂停）',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像路径',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `user_id` bigint DEFAULT NULL COMMENT '关联的用户ID',
  `primary_machine_type_name` varchar(255) DEFAULT NULL COMMENT '机型名称',
  `primary_major_name` varchar(255) DEFAULT NULL COMMENT '专业名称',
  `enrollment_date` date DEFAULT NULL COMMENT 'å…¥å­¦æ—¶é—´',
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `uk_student_code` (`student_code`),
  UNIQUE KEY `uk_student_id_card` (`id_card`),
  KEY `idx_primary_machine_type_id` (`primary_machine_type_id`),
  KEY `idx_primary_major_id` (`primary_major_id`),
  KEY `idx_student_status` (`status`),
  KEY `idx_training_status` (`training_status`),
  KEY `idx_student_machine_major` (`primary_machine_type_id`,`primary_major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_student_ability` (
  `student_ability_id` bigint NOT NULL AUTO_INCREMENT COMMENT '学员学习能力ID',
  `student_id` bigint NOT NULL COMMENT '学员ID',
  `machine_type_id` bigint NOT NULL COMMENT '机型ID',
  `major_id` bigint NOT NULL COMMENT '专业ID',
  `training_ability_id` bigint NOT NULL COMMENT '培训能力ID',
  `courseware_id` bigint NOT NULL COMMENT '课件ID',
  `learning_progress` char(1) NOT NULL COMMENT '学习进度（0未开始 1学习中 2已完成）',
  `theory_score` decimal(5,2) DEFAULT NULL COMMENT '理论成绩',
  `practice_score` decimal(5,2) DEFAULT NULL COMMENT '实践成绩',
  `total_score` decimal(5,2) DEFAULT NULL COMMENT '综合成绩',
  `learning_start_time` date DEFAULT NULL COMMENT '学习开始时间',
  `learning_end_time` date DEFAULT NULL COMMENT '学习完成时间',
  `qualification_status` char(1) NOT NULL COMMENT '资质状态（0未获得 1已获得 2已过期）',
  `qualification_date` date DEFAULT NULL COMMENT '资质获得时间',
  `valid_until` date DEFAULT NULL COMMENT '资质有效期至',
  `learning_remark` text COMMENT '学习备注',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`student_ability_id`),
  KEY `idx_student_id` (`student_id`),
  KEY `idx_machine_type_id` (`machine_type_id`),
  KEY `idx_major_id` (`major_id`),
  KEY `idx_training_ability_id` (`training_ability_id`),
  KEY `idx_courseware_id` (`courseware_id`),
  KEY `idx_learning_progress` (`learning_progress`),
  KEY `idx_qualification_status` (`qualification_status`),
  KEY `idx_student_ability_full` (`student_id`,`machine_type_id`,`major_id`,`training_ability_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学员学习能力表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_student_machine_type` (
  `relation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `student_id` bigint NOT NULL COMMENT '学员ID',
  `machine_type_id` bigint NOT NULL COMMENT '机型ID',
  `is_primary` char(1) DEFAULT '0' COMMENT '是否为主要机型（0否 1是）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `is_deleted` int DEFAULT NULL,
  PRIMARY KEY (`relation_id`),
  UNIQUE KEY `uk_student_machine` (`student_id`,`machine_type_id`),
  KEY `idx_student_id` (`student_id`),
  KEY `idx_machine_type_id` (`machine_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学员机型关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_training_ability` (
  `training_ability_id` bigint NOT NULL AUTO_INCREMENT COMMENT '培训能力ID',
  `ability_name` varchar(100) NOT NULL COMMENT '培训能力名称',
  `ability_code` varchar(50) NOT NULL COMMENT '培训能力代码',
  `ability_type` char(1) NOT NULL DEFAULT '0' COMMENT '培训能力类型（1初始培训 2复训 3差异培训 4恢复培训）',
  `ability_desc` text COMMENT '培训能力描述',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `daily_hours` int DEFAULT NULL,
  `total_days` int DEFAULT NULL,
  PRIMARY KEY (`training_ability_id`),
  UNIQUE KEY `uk_training_ability_code` (`ability_code`),
  KEY `idx_ability_type` (`ability_type`),
  KEY `idx_training_ability_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训能力表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  `actual_start_time` date DEFAULT NULL COMMENT '实际开始时间',
  `actual_end_time` date DEFAULT NULL COMMENT '实际结束时间',
  `plan_student_count` int DEFAULT NULL COMMENT '计划人数',
  `actual_student_count` int DEFAULT NULL COMMENT '实际人数',
  `primary_instructor_id` bigint DEFAULT NULL COMMENT '主要教员ID',
  `class_desc` text COMMENT '班次描述',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0待开班 1培训中 2已结班 3已取消）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`training_class_id`),
  UNIQUE KEY `uk_class_code` (`class_code`),
  KEY `idx_training_plan_id` (`training_plan_id`),
  KEY `idx_machine_type_id` (`machine_type_id`),
  KEY `idx_major_id` (`major_id`),
  KEY `idx_training_ability_id` (`training_ability_id`),
  KEY `idx_primary_instructor_id` (`primary_instructor_id`),
  KEY `idx_training_class_status` (`status`),
  KEY `idx_training_class_plan_time` (`training_plan_id`,`plan_start_time`,`plan_end_time`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训班次表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_training_outline` (
  `outline_id` bigint NOT NULL AUTO_INCREMENT COMMENT '大纲ID',
  `machine_type_id` bigint NOT NULL COMMENT '机型ID',
  `major_id` bigint NOT NULL COMMENT '专业ID',
  `training_ability_id` bigint NOT NULL COMMENT '培训能力ID',
  `outline_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '大纲名称',
  `outline_code` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '大纲编码',
  `regulation_file_id` bigint DEFAULT NULL COMMENT '培训规范文件ID',
  `regulation_file_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '培训规范文件名',
  `regulation_file_path` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '培训规范文件路径',
  `outline_file_id` bigint DEFAULT NULL COMMENT '大纲文件ID',
  `outline_file_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '大纲文件名',
  `outline_file_path` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '大纲文件路径',
  `theory_hours` decimal(5,1) NOT NULL DEFAULT '0.0' COMMENT '理论培训课时',
  `practical_hours` decimal(5,1) NOT NULL DEFAULT '0.0' COMMENT '实作培训课时',
  `total_hours` decimal(5,1) GENERATED ALWAYS AS ((`theory_hours` + `practical_hours`)) STORED COMMENT '总课时',
  `description` text COLLATE utf8mb4_general_ci COMMENT '大纲描述',
  `effective_date` date DEFAULT NULL COMMENT '生效日期',
  `expiry_date` date DEFAULT NULL COMMENT '失效日期',
  `version` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本号',
  `status` char(1) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `is_deleted` int DEFAULT '0',
  PRIMARY KEY (`outline_id`),
  UNIQUE KEY `uk_machine_major_ability` (`machine_type_id`,`major_id`,`training_ability_id`,`status`),
  KEY `idx_machine_type` (`machine_type_id`),
  KEY `idx_major` (`major_id`),
  KEY `idx_training_ability` (`training_ability_id`),
  KEY `idx_status` (`status`),
  KEY `idx_effective_date` (`effective_date`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='培训大纲表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_training_plan` (
  `plan_id` bigint NOT NULL AUTO_INCREMENT COMMENT '培训计划ID',
  `plan_name` varchar(200) NOT NULL COMMENT '培训计划名称',
  `plan_code` varchar(50) NOT NULL COMMENT '培训计划编号',
  `machine_type_id` bigint NOT NULL COMMENT '机型ID',
  `major_id` bigint NOT NULL COMMENT '专业ID',
  `training_ability_id` bigint NOT NULL COMMENT '培训能力ID',
  `plan_student_count` int DEFAULT NULL COMMENT '计划人数',
  `training_plan_desc` text COMMENT '培训计划描述',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0草稿 1待审核 2已审核 3执行中 4已完成 5已取消）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `total_hours` decimal(10,2) DEFAULT NULL,
  `theory_hours` decimal(10,2) DEFAULT NULL,
  `practice_hours` decimal(10,2) DEFAULT NULL,
  `plan_status` char(1) DEFAULT '0' COMMENT 'è®¡åˆ’çŠ¶æ€ï¼ˆ0è‰ç¨¿ 1æ‰§è¡Œä¸­ 2å·²å®Œæˆ 3å·²å–æ¶ˆï¼‰',
  `schedule_generated` char(1) DEFAULT '0' COMMENT 'æ˜¯å¦å·²ç”Ÿæˆè¯¾è¡¨ï¼ˆ0å¦ 1æ˜¯ï¼‰',
  `class_schedule_name` varchar(200) DEFAULT NULL COMMENT 'ç­æ¬¡è¯¾ç¨‹è¡¨åç§°',
  `teaching_schedule_name` varchar(200) DEFAULT NULL COMMENT 'æ•™å­¦è¿›åº¦å®‰æŽ’è¡¨åç§°',
  `practical_project_list_name` varchar(200) DEFAULT NULL COMMENT 'æœºåž‹å®žä½œåŸ¹è®­é¡¹ç›®æ¸…å•åç§°',
  PRIMARY KEY (`plan_id`),
  UNIQUE KEY `uk_training_plan_code` (`plan_code`),
  KEY `idx_machine_type_id` (`machine_type_id`),
  KEY `idx_major_id` (`major_id`),
  KEY `idx_training_ability_id` (`training_ability_id`),
  KEY `idx_training_plan_status` (`status`),
  KEY `idx_training_plan_machine_major_ability` (`machine_type_id`,`major_id`,`training_ability_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_training_plan_detail` (
  `training_plan_detail_id` bigint NOT NULL AUTO_INCREMENT COMMENT '培训计划明细ID',
  `training_plan_id` bigint NOT NULL COMMENT '培训计划ID',
  `courseware_id` bigint NOT NULL COMMENT '课件ID',
  `course_order` int NOT NULL COMMENT '课程顺序',
  `theory_hours` decimal(10,2) DEFAULT NULL COMMENT '理论学时',
  `practice_hours` decimal(10,2) DEFAULT NULL COMMENT '实践学时',
  `is_required` char(1) NOT NULL COMMENT '是否必修（0选修 1必修）',
  `course_requirement` text COMMENT '课程要求',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`training_plan_detail_id`),
  KEY `idx_training_plan_id` (`training_plan_id`),
  KEY `idx_courseware_id` (`courseware_id`),
  KEY `idx_course_order` (`course_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划明细表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_training_plan_instructor` (
  `relation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `plan_id` bigint NOT NULL COMMENT '计划ID',
  `instructor_id` bigint NOT NULL COMMENT '教员ID',
  `is_chief` char(1) DEFAULT '0' COMMENT '是否为主责教员（0否 1是）',
  `responsibility` varchar(200) DEFAULT NULL COMMENT '责任描述',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `is_deleted` char(1) DEFAULT '0' COMMENT 'æ˜¯å¦åˆ é™¤ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰',
  PRIMARY KEY (`relation_id`),
  UNIQUE KEY `uk_plan_instructor` (`plan_id`,`instructor_id`),
  KEY `idx_plan_id` (`plan_id`),
  KEY `idx_instructor_id` (`instructor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划教员关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_training_plan_schedule` (
  `schedule_id` bigint NOT NULL AUTO_INCREMENT COMMENT '课程安排ID',
  `plan_id` bigint NOT NULL COMMENT '计划ID',
  `schedule_type` varchar(20) NOT NULL COMMENT '安排类型（class_schedule=班次课程表, teaching_schedule=教学进度安排表, practical_project=实作项目清单）',
  `day_number` int NOT NULL COMMENT '天数（第几天）',
  `time_slot` varchar(20) DEFAULT NULL COMMENT '时间段（如09:00-12:00）',
  `courseware_id` bigint DEFAULT NULL COMMENT '课件ID',
  `course_name` varchar(200) NOT NULL COMMENT '课程名称',
  `course_type` varchar(10) DEFAULT NULL COMMENT '课程类型（1理论 2实践）',
  `course_hours` decimal(3,1) NOT NULL COMMENT '课程时长',
  `instructor_id` bigint DEFAULT NULL COMMENT '授课教员ID',
  `classroom` varchar(100) DEFAULT NULL COMMENT '教室/实训场地',
  `course_content` text COMMENT '课程内容',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `is_deleted` char(1) DEFAULT '0' COMMENT 'æ˜¯å¦åˆ é™¤ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰',
  PRIMARY KEY (`schedule_id`),
  KEY `idx_plan_schedule_type` (`plan_id`,`schedule_type`),
  KEY `idx_plan_day` (`plan_id`,`day_number`),
  KEY `idx_courseware_id` (`courseware_id`),
  KEY `idx_instructor_id` (`instructor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划课程安排表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_training_record` (
  `training_record_id` bigint NOT NULL AUTO_INCREMENT COMMENT '培训记录ID',
  `student_id` bigint NOT NULL COMMENT '学员ID',
  `training_class_id` bigint NOT NULL COMMENT '培训班次ID',
  `courseware_id` bigint NOT NULL COMMENT '课件ID',
  `instructor_id` bigint NOT NULL COMMENT '教员ID',
  `training_date` date NOT NULL COMMENT '培训日期',
  `training_method` char(1) NOT NULL COMMENT '培训类型（1理论 2实践）',
  `training_hours` decimal(5,2) DEFAULT NULL COMMENT '培训时长(小时)',
  `attendance_status` char(1) NOT NULL COMMENT '出勤状态（1正常 2迟到 3早退 4请假 5缺勤）',
  `performance_score` decimal(5,2) DEFAULT NULL COMMENT '课堂表现评分',
  `homework_score` decimal(5,2) DEFAULT NULL COMMENT '作业成绩',
  `exam_score` decimal(5,2) DEFAULT NULL COMMENT '考试成绩',
  `total_score` decimal(5,2) DEFAULT NULL COMMENT '综合评分',
  `training_content` text COMMENT '培训内容',
  `learning_notes` text COMMENT '学习心得',
  `instructor_comment` text COMMENT '教员评价',
  `training_effect` char(1) DEFAULT NULL COMMENT '培训效果（1优秀 2良好 3一般 4较差）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`training_record_id`),
  KEY `idx_student_id` (`student_id`),
  KEY `idx_training_class_id` (`training_class_id`),
  KEY `idx_courseware_id` (`courseware_id`),
  KEY `idx_instructor_id` (`instructor_id`),
  KEY `idx_training_date` (`training_date`),
  KEY `idx_training_method` (`training_method`),
  KEY `idx_attendance_status` (`attendance_status`),
  KEY `idx_training_record_student_date` (`student_id`,`training_date`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训记录表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_training_type` (
  `training_type_id` bigint NOT NULL AUTO_INCREMENT COMMENT '培训类型ID',
  `training_type_name` varchar(100) NOT NULL COMMENT '培训类型名称',
  `training_type_code` varchar(50) NOT NULL COMMENT '培训类型代码',
  `training_method` char(1) NOT NULL COMMENT '培训方式（1理论 2实践）',
  `training_type_desc` text COMMENT '培训类型描述',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`training_type_id`),
  UNIQUE KEY `uk_training_type_code` (`training_type_code`),
  KEY `idx_training_method` (`training_method`),
  KEY `idx_training_type_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训类型表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trace_log` (
  `trace_id` bigint NOT NULL AUTO_INCREMENT COMMENT '追踪ID',
  `table_name` varchar(64) NOT NULL COMMENT '表名',
  `operation_type` varchar(10) NOT NULL COMMENT '操作类型（INSERT/UPDATE/DELETE）',
  `record_id` varchar(64) NOT NULL COMMENT '记录ID',
  `old_data` json DEFAULT NULL COMMENT '修改前数据（JSON格式）',
  `new_data` json DEFAULT NULL COMMENT '修改后数据（JSON格式）',
  `changed_fields` varchar(1000) DEFAULT NULL COMMENT '变更字段列表',
  `operator_id` bigint DEFAULT NULL COMMENT '操作人ID',
  `operator_name` varchar(64) DEFAULT NULL COMMENT '操作人姓名',
  `operation_time` datetime NOT NULL COMMENT '操作时间',
  `operation_ip` varchar(128) DEFAULT NULL COMMENT '操作IP',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`trace_id`),
  KEY `idx_trace_table` (`table_name`),
  KEY `idx_trace_time` (`operation_time`),
  KEY `idx_trace_operator` (`operator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据变化追踪表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

