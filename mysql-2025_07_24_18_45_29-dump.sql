-- MySQL dump 10.13  Distrib 9.3.0, for macos15.4 (arm64)
--
-- Host: 127.0.0.1    Database: tms
-- ------------------------------------------------------
-- Server version	8.0.42

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

--
-- Table structure for table `QRTZ_BLOB_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
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

--
-- Dumping data for table `QRTZ_BLOB_TRIGGERS`
--

LOCK TABLES `QRTZ_BLOB_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CALENDARS`
--

DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_CALENDARS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日历信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CALENDARS`
--

LOCK TABLES `QRTZ_CALENDARS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CRON_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
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

--
-- Dumping data for table `QRTZ_CRON_TRIGGERS`
--

LOCK TABLES `QRTZ_CRON_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_FIRED_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
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

--
-- Dumping data for table `QRTZ_FIRED_TRIGGERS`
--

LOCK TABLES `QRTZ_FIRED_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_JOB_DETAILS`
--

DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
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

--
-- Dumping data for table `QRTZ_JOB_DETAILS`
--

LOCK TABLES `QRTZ_JOB_DETAILS` WRITE;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_LOCKS`
--

DROP TABLE IF EXISTS `QRTZ_LOCKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_LOCKS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存储的悲观锁信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_LOCKS`
--

LOCK TABLES `QRTZ_LOCKS` WRITE;
/*!40000 ALTER TABLE `QRTZ_LOCKS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_LOCKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='暂停的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

LOCK TABLES `QRTZ_PAUSED_TRIGGER_GRPS` WRITE;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SCHEDULER_STATE`
--

DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
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

--
-- Dumping data for table `QRTZ_SCHEDULER_STATE`
--

LOCK TABLES `QRTZ_SCHEDULER_STATE` WRITE;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPLE_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
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

--
-- Dumping data for table `QRTZ_SIMPLE_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPLE_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPROP_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
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

--
-- Dumping data for table `QRTZ_SIMPROP_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPROP_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
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

--
-- Dumping data for table `QRTZ_TRIGGERS`
--

LOCK TABLES `QRTZ_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS `gen_table`;
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

--
-- Dumping data for table `gen_table`
--

LOCK TABLES `gen_table` WRITE;
/*!40000 ALTER TABLE `gen_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `gen_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table_column`
--

DROP TABLE IF EXISTS `gen_table_column`;
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

--
-- Dumping data for table `gen_table_column`
--

LOCK TABLES `gen_table_column` WRITE;
/*!40000 ALTER TABLE `gen_table_column` DISABLE KEYS */;
/*!40000 ALTER TABLE `gen_table_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
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

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2025-07-23 08:52:18','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2025-07-23 08:52:18','',NULL,'初始化密码 123456'),(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2025-07-23 08:52:18','',NULL,'深色主题theme-dark，浅色主题theme-light'),(4,'账号自助-验证码开关','sys.account.captchaEnabled','true','Y','admin','2025-07-23 08:52:18','',NULL,'是否开启验证码功能（true开启，false关闭）'),(5,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2025-07-23 08:52:18','',NULL,'是否开启注册用户功能（true开启，false关闭）'),(6,'用户登录-黑名单列表','sys.login.blackIPList','','Y','admin','2025-07-23 08:52:18','',NULL,'设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）'),(7,'用户管理-初始密码修改策略','sys.account.initPasswordModify','1','Y','admin','2025-07-23 08:52:18','',NULL,'0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框'),(8,'用户管理-账号密码更新周期','sys.account.passwordValidateDays','0','Y','admin','2025-07-23 08:52:18','',NULL,'密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
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

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (100,0,'0','飞安直升机培训中心',0,'张主任','13800138000','admin@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(101,100,'0,100','培训管理部',1,'李部长','13800138001','training@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(102,100,'0,100','教学质量部',2,'王部长','13800138002','quality@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(103,100,'0,100','安全管理部',3,'刘部长','13800138003','safety@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(104,100,'0,100','综合办公室',4,'陈主任','13800138004','office@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(201,101,'0,100,101','理论培训科',1,'赵科长','13800138011','theory@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(202,101,'0,100,101','实践培训科',2,'孙科长','13800138012','practice@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(203,101,'0,100,101','课件开发科',3,'周科长','13800138013','courseware@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(204,101,'0,100,101','学员管理科',4,'吴科长','13800138014','student@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(205,102,'0,100,102','教学监察科',1,'郑科长','13800138021','inspect@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(206,102,'0,100,102','考试评估科',2,'何科长','13800138022','exam@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(207,102,'0,100,102','证书管理科',3,'林科长','13800138023','certificate@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(208,103,'0,100,103','飞行安全科',1,'黄科长','13800138031','flight@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL),(209,103,'0,100,103','地面安全科',2,'徐科长','13800138032','ground@feian.com','0','0','admin','2025-07-23 08:54:03','',NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
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

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` VALUES (1,1,'男','0','sys_user_sex','','','Y','0','admin','2025-07-23 08:52:18','',NULL,'性别男'),(2,2,'女','1','sys_user_sex','','','N','0','admin','2025-07-23 08:52:18','',NULL,'性别女'),(3,3,'未知','2','sys_user_sex','','','N','0','admin','2025-07-23 08:52:18','',NULL,'性别未知'),(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2025-07-23 08:52:18','',NULL,'显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2025-07-23 08:52:18','',NULL,'隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2025-07-23 08:52:18','',NULL,'正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2025-07-23 08:52:18','',NULL,'停用状态'),(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2025-07-23 08:52:18','',NULL,'正常状态'),(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2025-07-23 08:52:18','',NULL,'停用状态'),(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2025-07-23 08:52:18','',NULL,'默认分组'),(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2025-07-23 08:52:18','',NULL,'系统分组'),(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2025-07-23 08:52:18','',NULL,'系统默认是'),(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2025-07-23 08:52:18','',NULL,'系统默认否'),(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2025-07-23 08:52:18','',NULL,'通知'),(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2025-07-23 08:52:18','',NULL,'公告'),(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2025-07-23 08:52:18','',NULL,'正常状态'),(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2025-07-23 08:52:18','',NULL,'关闭状态'),(18,99,'其他','0','sys_oper_type','','info','N','0','admin','2025-07-23 08:52:18','',NULL,'其他操作'),(19,1,'新增','1','sys_oper_type','','info','N','0','admin','2025-07-23 08:52:18','',NULL,'新增操作'),(20,2,'修改','2','sys_oper_type','','info','N','0','admin','2025-07-23 08:52:18','',NULL,'修改操作'),(21,3,'删除','3','sys_oper_type','','danger','N','0','admin','2025-07-23 08:52:18','',NULL,'删除操作'),(22,4,'授权','4','sys_oper_type','','primary','N','0','admin','2025-07-23 08:52:18','',NULL,'授权操作'),(23,5,'导出','5','sys_oper_type','','warning','N','0','admin','2025-07-23 08:52:18','',NULL,'导出操作'),(24,6,'导入','6','sys_oper_type','','warning','N','0','admin','2025-07-23 08:52:18','',NULL,'导入操作'),(25,7,'强退','7','sys_oper_type','','danger','N','0','admin','2025-07-23 08:52:18','',NULL,'强退操作'),(26,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2025-07-23 08:52:18','',NULL,'生成操作'),(27,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2025-07-23 08:52:18','',NULL,'清空操作'),(28,1,'成功','0','sys_common_status','','primary','N','0','admin','2025-07-23 08:52:18','',NULL,'正常状态'),(29,2,'失败','1','sys_common_status','','danger','N','0','admin','2025-07-23 08:52:18','',NULL,'停用状态'),(100,1,'AS350','AS350','tms_aircraft_type','','primary','N','0','admin','2025-07-23 08:54:03','',NULL,'AS350直升机'),(101,2,'R44','R44','tms_aircraft_type','','success','N','0','admin','2025-07-23 08:54:03','',NULL,'R44直升机'),(102,3,'R22','R22','tms_aircraft_type','','info','N','0','admin','2025-07-23 08:54:03','',NULL,'R22直升机'),(103,4,'EC135','EC135','tms_aircraft_type','','warning','N','0','admin','2025-07-23 08:54:03','',NULL,'EC135直升机'),(104,5,'Bell206','Bell206','tms_aircraft_type','','danger','N','0','admin','2025-07-23 08:54:03','',NULL,'Bell206直升机'),(110,1,'空勤','AIRCREW','tms_training_major','','primary','Y','0','admin','2025-07-23 08:54:03','',NULL,'空勤专业'),(111,2,'地勤','GROUND_CREW','tms_training_major','','success','N','0','admin','2025-07-23 08:54:03','',NULL,'地勤专业'),(120,1,'理论培训','THEORY','tms_training_type','','primary','Y','0','admin','2025-07-23 08:54:03','',NULL,'理论培训'),(121,2,'实践培训','PRACTICE','tms_training_type','','success','N','0','admin','2025-07-23 08:54:03','',NULL,'实践培训'),(130,1,'初始培训','INITIAL','tms_training_ability','','primary','Y','0','admin','2025-07-23 08:54:03','',NULL,'初始培训'),(131,2,'复训','RECURRENT','tms_training_ability','','warning','N','0','admin','2025-07-23 08:54:03','',NULL,'复训'),(132,3,'差异培训','DIFFERENCE','tms_training_ability','','info','N','0','admin','2025-07-23 08:54:03','',NULL,'差异培训'),(133,4,'恢复培训','RESTORATION','tms_training_ability','','danger','N','0','admin','2025-07-23 08:54:03','',NULL,'恢复培训'),(140,1,'培训合格证','TRAINING_CERTIFICATE','tms_certificate_type','','primary','Y','0','admin','2025-07-23 08:54:03','',NULL,'培训合格证'),(141,2,'技能证书','SKILL_CERTIFICATE','tms_certificate_type','','success','N','0','admin','2025-07-23 08:54:03','',NULL,'技能证书'),(142,3,'资质证书','QUALIFICATION_CERTIFICATE','tms_certificate_type','','warning','N','0','admin','2025-07-23 08:54:03','',NULL,'资质证书'),(150,1,'教材','TEXTBOOK','tms_courseware_type','','primary','Y','0','admin','2025-07-23 08:54:03','',NULL,'培训教材'),(151,2,'教案','LESSON_PLAN','tms_courseware_type','','success','N','0','admin','2025-07-23 08:54:03','',NULL,'教学教案'),(152,3,'PPT','PPT','tms_courseware_type','','info','N','0','admin','2025-07-23 08:54:03','',NULL,'演示文稿'),(153,4,'CBT','CBT','tms_courseware_type','','warning','N','0','admin','2025-07-23 08:54:03','',NULL,'计算机培训'),(154,5,'工卡','JOB_CARD','tms_courseware_type','','danger','N','0','admin','2025-07-23 08:54:03','',NULL,'实操工卡'),(160,1,'未开始','NOT_STARTED','tms_training_status','','info','Y','0','admin','2025-07-23 08:54:03','',NULL,'培训未开始'),(161,2,'进行中','IN_PROGRESS','tms_training_status','','primary','N','0','admin','2025-07-23 08:54:03','',NULL,'培训进行中'),(162,3,'已完成','COMPLETED','tms_training_status','','success','N','0','admin','2025-07-23 08:54:03','',NULL,'培训已完成'),(163,4,'已暂停','SUSPENDED','tms_training_status','','warning','N','0','admin','2025-07-23 08:54:03','',NULL,'培训已暂停'),(164,5,'已取消','CANCELLED','tms_training_status','','danger','N','0','admin','2025-07-23 08:54:03','',NULL,'培训已取消'),(170,1,'合格','PASS','tms_exam_result','','success','Y','0','admin','2025-07-23 08:54:03','',NULL,'考试合格'),(171,2,'不合格','FAIL','tms_exam_result','','danger','N','0','admin','2025-07-23 08:54:03','',NULL,'考试不合格'),(172,3,'缺考','ABSENT','tms_exam_result','','warning','N','0','admin','2025-07-23 08:54:03','',NULL,'缺考'),(173,4,'待评分','PENDING','tms_exam_result','','info','N','0','admin','2025-07-23 08:54:03','',NULL,'待评分');
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
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

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'用户性别','sys_user_sex','0','admin','2025-07-23 08:52:18','',NULL,'用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2025-07-23 08:52:18','',NULL,'菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2025-07-23 08:52:18','',NULL,'系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2025-07-23 08:52:18','',NULL,'任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2025-07-23 08:52:18','',NULL,'任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2025-07-23 08:52:18','',NULL,'系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2025-07-23 08:52:18','',NULL,'通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2025-07-23 08:52:18','',NULL,'通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2025-07-23 08:52:18','',NULL,'操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2025-07-23 08:52:18','',NULL,'登录状态列表'),(100,'机型类别','tms_aircraft_type','0','admin','2025-07-23 08:54:03','',NULL,'直升机机型分类'),(101,'培训专业','tms_training_major','0','admin','2025-07-23 08:54:03','',NULL,'培训专业类别'),(102,'培训类型','tms_training_type','0','admin','2025-07-23 08:54:03','',NULL,'培训类型分类'),(103,'培训能力','tms_training_ability','0','admin','2025-07-23 08:54:03','',NULL,'培训能力类别'),(104,'证书类型','tms_certificate_type','0','admin','2025-07-23 08:54:03','',NULL,'培训证书类型'),(105,'课件类型','tms_courseware_type','0','admin','2025-07-23 08:54:03','',NULL,'培训课件类型'),(106,'培训状态','tms_training_status','0','admin','2025-07-23 08:54:03','',NULL,'培训进度状态'),(107,'考试结果','tms_exam_result','0','admin','2025-07-23 08:54:03','',NULL,'考试结果状态');
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
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

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2025-07-23 08:52:18','',NULL,''),(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2025-07-23 08:52:18','',NULL,''),(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2025-07-23 08:52:18','',NULL,'');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
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

--
-- Dumping data for table `sys_job_log`
--

LOCK TABLES `sys_job_log` WRITE;
/*!40000 ALTER TABLE `sys_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS `sys_logininfor`;
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

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
INSERT INTO `sys_logininfor` VALUES (100,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 08:55:19'),(101,'admin','192.168.1.61','内网IP','Chrome 11','Windows 10','0','登录成功','2025-07-23 09:07:08'),(102,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 09:18:26'),(103,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 09:18:30'),(104,'admin','127.0.0.1','内网IP','Chrome 13','Windows 10','0','登录成功','2025-07-23 09:27:46'),(105,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 09:45:52'),(106,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 09:45:56'),(107,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 09:57:52'),(108,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','Expected one result (or null) to be returned by selectOne(), but found: 2','2025-07-23 09:58:12'),(109,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','验证码错误','2025-07-23 09:58:13'),(110,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','验证码错误','2025-07-23 09:58:14'),(111,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','验证码错误','2025-07-23 09:58:14'),(112,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','Expected one result (or null) to be returned by selectOne(), but found: 2','2025-07-23 09:58:15'),(113,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','验证码错误','2025-07-23 09:58:15'),(114,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','验证码错误','2025-07-23 09:58:34'),(115,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','Expected one result (or null) to be returned by selectOne(), but found: 2','2025-07-23 09:58:44'),(116,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 09:59:18'),(117,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 10:12:22'),(118,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 10:12:29'),(119,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 10:13:08'),(120,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','验证码错误','2025-07-23 10:17:22'),(121,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 10:17:26'),(122,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 10:18:32'),(123,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 10:18:41'),(124,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 10:24:08'),(125,'student','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','用户不存在/密码错误','2025-07-23 10:24:20'),(126,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 10:24:26'),(127,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 10:25:06'),(128,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 10:25:15'),(129,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 10:27:25'),(130,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 10:27:36'),(131,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 10:27:56'),(132,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 10:28:03'),(133,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 10:28:22'),(134,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 10:28:34'),(135,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-23 10:28:49'),(136,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-23 10:29:04'),(137,'admin','127.0.0.1','内网IP','Chrome 13','Windows 10','0','登录成功','2025-07-24 01:09:04'),(138,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 01:30:08'),(139,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 01:38:10'),(140,'ST002','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','用户不存在/密码错误','2025-07-24 01:38:17'),(141,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','Expected one result (or null) to be returned by selectOne(), but found: 2','2025-07-24 01:38:28'),(142,'student003','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','Expected one result (or null) to be returned by selectOne(), but found: 2','2025-07-24 01:38:48'),(143,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 01:39:57'),(144,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 01:40:19'),(145,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 01:40:24'),(146,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 01:41:11'),(147,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','用户不存在/密码错误','2025-07-24 01:41:22'),(148,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','用户不存在/密码错误','2025-07-24 01:41:35'),(149,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','用户不存在/密码错误','2025-07-24 01:41:36'),(150,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 01:41:47'),(151,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 01:50:08'),(152,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','用户不存在/密码错误','2025-07-24 01:50:14'),(153,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 01:50:20'),(154,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 01:50:28'),(155,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 01:50:32'),(156,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 01:51:29'),(157,'admin','127.0.0.1','内网IP','Chrome 13','Windows 10','0','登录成功','2025-07-24 02:38:36'),(158,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 02:39:01'),(159,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 03:03:42'),(160,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 03:03:44'),(161,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 03:08:02'),(162,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 03:08:04'),(163,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 03:08:50'),(164,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 03:08:56'),(165,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 03:10:13'),(166,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 03:10:16'),(167,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 03:17:02'),(168,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 03:17:05'),(169,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 03:19:59'),(170,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 03:20:02'),(171,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2025-07-24 03:35:15'),(172,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 03:45:24'),(173,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 03:45:28'),(174,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 03:49:19'),(175,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 03:50:29'),(176,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 04:10:43'),(177,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 04:10:46'),(178,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 04:53:34'),(179,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 04:53:37'),(180,'admin','127.0.0.1','内网IP','Chrome 13','Windows 10','0','登录成功','2025-07-24 05:29:28'),(181,'admin','127.0.0.1','内网IP','Chrome 13','Windows 10','0','登录成功','2025-07-24 05:59:12'),(182,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2025-07-24 06:00:58'),(183,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 06:01:14'),(184,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','验证码错误','2025-07-24 06:01:22'),(185,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','用户不存在/密码错误','2025-07-24 06:01:25'),(186,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 06:01:31'),(187,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 06:02:07'),(188,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 06:02:18'),(189,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2025-07-24 06:08:42'),(190,'admin','127.0.0.1','内网IP','Chrome 13','Windows 10','0','退出成功','2025-07-24 06:09:38'),(191,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 06:21:11'),(192,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 06:21:19'),(193,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 06:21:44'),(194,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 06:21:54'),(195,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 06:30:08'),(196,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 06:30:16'),(197,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 06:44:22'),(198,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 06:44:30'),(199,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 06:47:51'),(200,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 06:48:05'),(201,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 06:52:55'),(202,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 06:52:55'),(203,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 06:53:07'),(204,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 06:59:50'),(205,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 06:59:59'),(206,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 07:00:30'),(207,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 07:00:39'),(208,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 07:00:50'),(209,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 07:00:57'),(210,'student002','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 07:04:34'),(211,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 07:04:43'),(212,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 07:05:30'),(213,'student001','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 07:05:40'),(214,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 07:16:26'),(215,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 07:23:09'),(216,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 07:23:15'),(217,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 07:24:53'),(218,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','1','验证码错误','2025-07-24 07:24:56'),(219,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 07:25:01'),(220,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 08:27:15'),(221,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 08:27:20'),(222,'admin','127.0.0.1','内网IP','Chrome 13','Windows 10','0','登录成功','2025-07-24 08:36:00'),(223,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 09:13:00'),(224,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 09:13:04'),(225,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 09:54:26'),(226,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 09:54:29'),(227,'admin','127.0.0.1','内网IP','Downloading Tool','Unknown','1','验证码已失效','2025-07-24 10:18:28'),(228,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','退出成功','2025-07-24 10:44:09'),(229,'admin','127.0.0.1','内网IP','Chrome 13','Mac OS X','0','登录成功','2025-07-24 10:44:12');
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
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

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,'','',1,0,'M','0','0','','system','admin','2025-07-24 03:18:46','',NULL,'系统管理目录'),(100,'用户管理',1,1,'user','system/user/index','','',1,0,'C','0','0','system:user:list','user','admin','2025-07-24 03:18:46','',NULL,'用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index','','',1,0,'C','0','0','system:role:list','peoples','admin','2025-07-24 03:18:46','',NULL,'角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index','','',1,0,'C','0','0','system:menu:list','tree-table','admin','2025-07-24 03:18:46','',NULL,'菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index','','',1,0,'C','0','0','system:dept:list','tree','admin','2025-07-24 03:18:46','',NULL,'部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index','','',1,0,'C','0','0','system:post:list','post','admin','2025-07-24 03:18:46','',NULL,'岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index','','',1,0,'C','0','0','system:dict:list','dict','admin','2025-07-24 03:18:46','',NULL,'字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index','','',1,0,'C','0','0','system:config:list','edit','admin','2025-07-24 03:18:46','',NULL,'参数设置菜单'),(107,'通知公告',1,8,'notice','system/notice/index','','',1,0,'C','0','0','system:notice:list','message','admin','2025-07-24 03:18:46','',NULL,'通知公告菜单'),(108,'日志管理',1,9,'log','','','',1,0,'M','0','0','','log','admin','2025-07-24 03:18:46','',NULL,'日志管理菜单'),(500,'操作日志',108,1,'operlog','monitor/operlog/index','','',1,0,'C','0','0','monitor:operlog:list','form','admin','2025-07-24 03:18:46','',NULL,'操作日志菜单'),(501,'登录日志',108,2,'logininfor','monitor/logininfor/index','','',1,0,'C','0','0','monitor:logininfor:list','logininfor','admin','2025-07-24 03:18:46','',NULL,'登录日志菜单'),(1000,'用户查询',100,1,'','','','',1,0,'F','0','0','system:user:query','#','admin','2025-07-24 03:18:46','',NULL,''),(1001,'用户新增',100,2,'','','','',1,0,'F','0','0','system:user:add','#','admin','2025-07-24 03:18:46','',NULL,''),(1002,'用户修改',100,3,'','','','',1,0,'F','0','0','system:user:edit','#','admin','2025-07-24 03:18:46','',NULL,''),(1003,'用户删除',100,4,'','','','',1,0,'F','0','0','system:user:remove','#','admin','2025-07-24 03:18:46','',NULL,''),(1004,'用户导出',100,5,'','','','',1,0,'F','0','0','system:user:export','#','admin','2025-07-24 03:18:46','',NULL,''),(1005,'用户导入',100,6,'','','','',1,0,'F','0','0','system:user:import','#','admin','2025-07-24 03:18:46','',NULL,''),(1006,'重置密码',100,7,'','','','',1,0,'F','0','0','system:user:resetPwd','#','admin','2025-07-24 03:18:46','',NULL,''),(1007,'角色查询',101,1,'','','','',1,0,'F','0','0','system:role:query','#','admin','2025-07-24 03:18:46','',NULL,''),(1008,'角色新增',101,2,'','','','',1,0,'F','0','0','system:role:add','#','admin','2025-07-24 03:18:46','',NULL,''),(1009,'角色修改',101,3,'','','','',1,0,'F','0','0','system:role:edit','#','admin','2025-07-24 03:18:46','',NULL,''),(1010,'角色删除',101,4,'','','','',1,0,'F','0','0','system:role:remove','#','admin','2025-07-24 03:18:46','',NULL,''),(1011,'角色导出',101,5,'','','','',1,0,'F','0','0','system:role:export','#','admin','2025-07-24 03:18:46','',NULL,''),(1012,'菜单查询',102,1,'','','','',1,0,'F','0','0','system:menu:query','#','admin','2025-07-24 03:18:46','',NULL,''),(1013,'菜单新增',102,2,'','','','',1,0,'F','0','0','system:menu:add','#','admin','2025-07-24 03:18:46','',NULL,''),(1014,'菜单修改',102,3,'','','','',1,0,'F','0','0','system:menu:edit','#','admin','2025-07-24 03:18:46','',NULL,''),(1015,'菜单删除',102,4,'','','','',1,0,'F','0','0','system:menu:remove','#','admin','2025-07-24 03:18:46','',NULL,''),(1016,'部门查询',103,1,'','','','',1,0,'F','0','0','system:dept:query','#','admin','2025-07-24 03:18:46','',NULL,''),(1017,'部门新增',103,2,'','','','',1,0,'F','0','0','system:dept:add','#','admin','2025-07-24 03:18:46','',NULL,''),(1018,'部门修改',103,3,'','','','',1,0,'F','0','0','system:dept:edit','#','admin','2025-07-24 03:18:46','',NULL,''),(1019,'部门删除',103,4,'','','','',1,0,'F','0','0','system:dept:remove','#','admin','2025-07-24 03:18:46','',NULL,''),(1020,'岗位查询',104,1,'','','','',1,0,'F','0','0','system:post:query','#','admin','2025-07-24 03:18:46','',NULL,''),(1021,'岗位新增',104,2,'','','','',1,0,'F','0','0','system:post:add','#','admin','2025-07-24 03:18:47','',NULL,''),(1022,'岗位修改',104,3,'','','','',1,0,'F','0','0','system:post:edit','#','admin','2025-07-24 03:18:47','',NULL,''),(1023,'岗位删除',104,4,'','','','',1,0,'F','0','0','system:post:remove','#','admin','2025-07-24 03:18:47','',NULL,''),(1024,'岗位导出',104,5,'','','','',1,0,'F','0','0','system:post:export','#','admin','2025-07-24 03:18:47','',NULL,''),(1025,'字典查询',105,1,'#','','','',1,0,'F','0','0','system:dict:query','#','admin','2025-07-24 03:18:47','',NULL,''),(1026,'字典新增',105,2,'#','','','',1,0,'F','0','0','system:dict:add','#','admin','2025-07-24 03:18:47','',NULL,''),(1027,'字典修改',105,3,'#','','','',1,0,'F','0','0','system:dict:edit','#','admin','2025-07-24 03:18:47','',NULL,''),(1028,'字典删除',105,4,'#','','','',1,0,'F','0','0','system:dict:remove','#','admin','2025-07-24 03:18:47','',NULL,''),(1029,'字典导出',105,5,'#','','','',1,0,'F','0','0','system:dict:export','#','admin','2025-07-24 03:18:47','',NULL,''),(1030,'参数查询',106,1,'#','','','',1,0,'F','0','0','system:config:query','#','admin','2025-07-24 03:18:47','',NULL,''),(1031,'参数新增',106,2,'#','','','',1,0,'F','0','0','system:config:add','#','admin','2025-07-24 03:18:47','',NULL,''),(1032,'参数修改',106,3,'#','','','',1,0,'F','0','0','system:config:edit','#','admin','2025-07-24 03:18:47','',NULL,''),(1033,'参数删除',106,4,'#','','','',1,0,'F','0','0','system:config:remove','#','admin','2025-07-24 03:18:47','',NULL,''),(1034,'参数导出',106,5,'#','','','',1,0,'F','0','0','system:config:export','#','admin','2025-07-24 03:18:47','',NULL,''),(1035,'公告查询',107,1,'#','','','',1,0,'F','0','0','system:notice:query','#','admin','2025-07-24 03:18:47','',NULL,''),(1036,'公告新增',107,2,'#','','','',1,0,'F','0','0','system:notice:add','#','admin','2025-07-24 03:18:47','',NULL,''),(1037,'公告修改',107,3,'#','','','',1,0,'F','0','0','system:notice:edit','#','admin','2025-07-24 03:18:47','',NULL,''),(1038,'公告删除',107,4,'#','','','',1,0,'F','0','0','system:notice:remove','#','admin','2025-07-24 03:18:47','',NULL,''),(1039,'操作查询',500,1,'#','','','',1,0,'F','0','0','monitor:operlog:query','#','admin','2025-07-24 03:18:47','',NULL,''),(1040,'操作删除',500,2,'#','','','',1,0,'F','0','0','monitor:operlog:remove','#','admin','2025-07-24 03:18:47','',NULL,''),(1041,'日志导出',500,3,'#','','','',1,0,'F','0','0','monitor:operlog:export','#','admin','2025-07-24 03:18:47','',NULL,''),(1042,'登录查询',501,1,'#','','','',1,0,'F','0','0','monitor:logininfor:query','#','admin','2025-07-24 03:18:47','',NULL,''),(1043,'登录删除',501,2,'#','','','',1,0,'F','0','0','monitor:logininfor:remove','#','admin','2025-07-24 03:18:47','',NULL,''),(1044,'日志导出',501,3,'#','','','',1,0,'F','0','0','monitor:logininfor:export','#','admin','2025-07-24 03:18:47','',NULL,''),(1045,'账户解锁',501,4,'#','','','',1,0,'F','0','0','monitor:logininfor:unlock','#','admin','2025-07-24 03:18:47','',NULL,''),(1046,'在线查询',109,1,'#','','','',1,0,'F','0','0','monitor:online:query','#','admin','2025-07-24 03:18:47','',NULL,''),(1047,'批量强退',109,2,'#','','','',1,0,'F','0','0','monitor:online:batchLogout','#','admin','2025-07-24 03:18:47','',NULL,''),(1048,'单条强退',109,3,'#','','','',1,0,'F','0','0','monitor:online:forceLogout','#','admin','2025-07-24 03:18:47','',NULL,''),(1049,'任务查询',110,1,'#','','','',1,0,'F','0','0','monitor:job:query','#','admin','2025-07-24 03:18:47','',NULL,''),(1050,'任务新增',110,2,'#','','','',1,0,'F','0','0','monitor:job:add','#','admin','2025-07-24 03:18:47','',NULL,''),(1051,'任务修改',110,3,'#','','','',1,0,'F','0','0','monitor:job:edit','#','admin','2025-07-24 03:18:47','',NULL,''),(1052,'任务删除',110,4,'#','','','',1,0,'F','0','0','monitor:job:remove','#','admin','2025-07-24 03:18:47','',NULL,''),(1053,'状态修改',110,5,'#','','','',1,0,'F','0','0','monitor:job:changeStatus','#','admin','2025-07-24 03:18:47','',NULL,''),(1054,'任务导出',110,6,'#','','','',1,0,'F','0','0','monitor:job:export','#','admin','2025-07-24 03:18:47','',NULL,''),(1055,'生成查询',116,1,'#','','','',1,0,'F','0','0','tool:gen:query','#','admin','2025-07-24 03:18:47','',NULL,''),(1056,'生成修改',116,2,'#','','','',1,0,'F','0','0','tool:gen:edit','#','admin','2025-07-24 03:18:47','',NULL,''),(1057,'生成删除',116,3,'#','','','',1,0,'F','0','0','tool:gen:remove','#','admin','2025-07-24 03:18:47','',NULL,''),(1058,'导入代码',116,4,'#','','','',1,0,'F','0','0','tool:gen:import','#','admin','2025-07-24 03:18:47','',NULL,''),(1059,'预览代码',116,5,'#','','','',1,0,'F','0','0','tool:gen:preview','#','admin','2025-07-24 03:18:47','',NULL,''),(1060,'生成代码',116,6,'#','','','',1,0,'F','0','0','tool:gen:code','#','admin','2025-07-24 03:18:47','',NULL,''),(2000,'基础数据管理',0,2,'basicData',NULL,NULL,'',1,0,'M','0','0',NULL,'system','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','基础数据管理'),(2001,'机型管理',2000,1,'machineType','tms/machineType/index',NULL,'',1,0,'C','0','0','tms:machineType:list','component','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','机型管理'),(2002,'机型查询',2001,1,'','',NULL,'',1,0,'F','0','0','tms:machineType:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2003,'机型新增',2001,2,'','',NULL,'',1,0,'F','0','0','tms:machineType:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2004,'机型修改',2001,3,'','',NULL,'',1,0,'F','0','0','tms:machineType:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2005,'机型删除',2001,4,'','',NULL,'',1,0,'F','0','0','tms:machineType:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2006,'机型导出',2001,5,'','',NULL,'',1,0,'F','0','0','tms:machineType:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2007,'专业管理',2000,2,'major','tms/major/index',NULL,'',1,0,'C','0','0','tms:major:list','skill','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','专业管理'),(2008,'专业查询',2007,1,'','',NULL,'',1,0,'F','0','0','tms:major:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2009,'专业新增',2007,2,'','',NULL,'',1,0,'F','0','0','tms:major:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2010,'专业修改',2007,3,'','',NULL,'',1,0,'F','0','0','tms:major:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2011,'专业删除',2007,4,'','',NULL,'',1,0,'F','0','0','tms:major:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2012,'专业导出',2007,5,'','',NULL,'',1,0,'F','0','0','tms:major:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2025,'大纲管理',2058,3,'outline','tms/outline/index',NULL,'',1,0,'C','0','0','tms:outline:list','documentation','admin','2025-07-24 03:41:08','admin','2025-07-24 04:10:39','培训大纲管理'),(2026,'大纲查询',2025,1,'','',NULL,'',1,0,'F','0','0','tms:outline:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2027,'大纲新增',2025,2,'','',NULL,'',1,0,'F','0','0','tms:outline:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2028,'大纲修改',2025,3,'','',NULL,'',1,0,'F','0','0','tms:outline:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2029,'大纲删除',2025,4,'','',NULL,'',1,0,'F','0','0','tms:outline:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2030,'大纲导出',2025,5,'','',NULL,'',1,0,'F','0','0','tms:outline:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2031,'大纲文件预览',2025,6,'','',NULL,'',1,0,'F','0','0','tms:outline:preview','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2032,'大纲文件下载',2025,7,'','',NULL,'',1,0,'F','0','0','tms:outline:download','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2033,'人员管理',0,3,'personnel',NULL,NULL,'',1,0,'M','0','0',NULL,'peoples','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','人员管理'),(2034,'学员管理',2033,1,'student','tms/student/index',NULL,'',1,0,'C','0','0','tms:student:list','user','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','学员管理'),(2035,'学员查询',2034,1,'','',NULL,'',1,0,'F','0','0','tms:student:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2036,'学员新增',2034,2,'','',NULL,'',1,0,'F','0','0','tms:student:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2037,'学员修改',2034,3,'','',NULL,'',1,0,'F','0','0','tms:student:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2038,'学员删除',2034,4,'','',NULL,'',1,0,'F','0','0','tms:student:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2039,'学员导出',2034,5,'','',NULL,'',1,0,'F','0','0','tms:student:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2040,'教员管理',2033,2,'instructor','tms/instructor/index',NULL,'',1,0,'C','0','0','tms:instructor:list','guide','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','教员管理'),(2041,'教员查询',2040,1,'','',NULL,'',1,0,'F','0','0','tms:instructor:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2042,'教员新增',2040,2,'','',NULL,'',1,0,'F','0','0','tms:instructor:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2043,'教员修改',2040,3,'','',NULL,'',1,0,'F','0','0','tms:instructor:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2044,'教员删除',2040,4,'','',NULL,'',1,0,'F','0','0','tms:instructor:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2045,'教员导出',2040,5,'','',NULL,'',1,0,'F','0','0','tms:instructor:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2058,'教学资源管理',0,4,'courseware',NULL,NULL,'',1,0,'M','0','0',NULL,'documentation','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','课件管理'),(2059,'理论课件',2058,1,'theory','tms/courseware/theory/index',NULL,'',1,0,'C','0','0','tms:courseware:list','education','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','理论课件管理'),(2060,'理论课件查询',2059,1,'','',NULL,'',1,0,'F','0','0','tms:courseware:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2061,'理论课件新增',2059,2,'','',NULL,'',1,0,'F','0','0','tms:courseware:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2062,'理论课件修改',2059,3,'','',NULL,'',1,0,'F','0','0','tms:courseware:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2063,'理论课件删除',2059,4,'','',NULL,'',1,0,'F','0','0','tms:courseware:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2064,'理论课件导出',2059,5,'','',NULL,'',1,0,'F','0','0','tms:courseware:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2065,'实践课件',2058,2,'practical','tms/courseware/practical/index',NULL,'',1,0,'C','0','0','tms:courseware:list','build','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','实践课件管理'),(2066,'实践课件查询',2065,1,'','',NULL,'',1,0,'F','0','0','tms:courseware:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2067,'实践课件新增',2065,2,'','',NULL,'',1,0,'F','0','0','tms:courseware:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2068,'实践课件修改',2065,3,'','',NULL,'',1,0,'F','0','0','tms:courseware:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2069,'实践课件删除',2065,4,'','',NULL,'',1,0,'F','0','0','tms:courseware:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2070,'实践课件导出',2065,5,'','',NULL,'',1,0,'F','0','0','tms:courseware:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2071,'培训计划',0,5,'plan',NULL,NULL,'',1,0,'M','0','0',NULL,'date-range','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','培训计划'),(2072,'培训计划',2071,1,'training-plan','tms/training-plan/index',NULL,'',1,0,'C','0','0','tms:trainingPlan:list','calendar','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','培训计划管理'),(2073,'培训计划查询',2072,1,'','',NULL,'',1,0,'F','0','0','tms:trainingPlan:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2074,'培训计划新增',2072,2,'','',NULL,'',1,0,'F','0','0','tms:trainingPlan:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2075,'培训计划修改',2072,3,'','',NULL,'',1,0,'F','0','0','tms:trainingPlan:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2076,'培训计划删除',2072,4,'','',NULL,'',1,0,'F','0','0','tms:trainingPlan:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2077,'培训计划导出',2072,5,'','',NULL,'',1,0,'F','0','0','tms:trainingPlan:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2084,'培训执行',0,6,'execution',NULL,NULL,'',1,0,'M','0','0',NULL,'nested','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','培训执行'),(2085,'培训班次',2084,1,'trainingClass','tms/trainingClass/index',NULL,'',1,0,'C','0','0','tms:trainingClass:list','tab','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','培训班次管理'),(2086,'班次查询',2085,1,'','',NULL,'',1,0,'F','0','0','tms:trainingClass:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2087,'班次新增',2085,2,'','',NULL,'',1,0,'F','0','0','tms:trainingClass:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2088,'班次修改',2085,3,'','',NULL,'',1,0,'F','0','0','tms:trainingClass:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2089,'班次删除',2085,4,'','',NULL,'',1,0,'F','0','0','tms:trainingClass:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2090,'班次导出',2085,5,'','',NULL,'',1,0,'F','0','0','tms:trainingClass:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2091,'班次学员管理',2084,2,'classStudent','tms/classStudent/index',NULL,'',1,0,'C','0','0','tms:classStudent:list','peoples','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','班次学员管理'),(2092,'班次学员查询',2091,1,'','',NULL,'',1,0,'F','0','0','tms:classStudent:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2093,'班次学员新增',2091,2,'','',NULL,'',1,0,'F','0','0','tms:classStudent:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2094,'班次学员修改',2091,3,'','',NULL,'',1,0,'F','0','0','tms:classStudent:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2095,'班次学员删除',2091,4,'','',NULL,'',1,0,'F','0','0','tms:classStudent:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2096,'班次学员导出',2091,5,'','',NULL,'',1,0,'F','0','0','tms:classStudent:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2097,'培训记录',2084,3,'trainingRecord','tms/trainingRecord/index',NULL,'',1,0,'C','0','0','tms:trainingRecord:list','log','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','培训记录管理'),(2098,'培训记录查询',2097,1,'','',NULL,'',1,0,'F','0','0','tms:trainingRecord:query','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2099,'培训记录新增',2097,2,'','',NULL,'',1,0,'F','0','0','tms:trainingRecord:add','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2100,'培训记录修改',2097,3,'','',NULL,'',1,0,'F','0','0','tms:trainingRecord:edit','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2101,'培训记录删除',2097,4,'','',NULL,'',1,0,'F','0','0','tms:trainingRecord:remove','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2102,'培训记录导出',2097,5,'','',NULL,'',1,0,'F','0','0','tms:trainingRecord:export','#','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08',''),(2103,'成果管理',0,7,'certificate',NULL,NULL,'',1,0,'M','0','0',NULL,'star','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','证书管理'),(2104,'证书管理',2103,1,'certificateManage','tms/certificate/index',NULL,'',1,0,'C','0','0','tms:certificate:list','medal','admin','2025-07-24 03:41:08','admin','2025-07-24 03:41:08','证书管理'),(2105,'证书查询',2104,1,'','',NULL,'',1,0,'F','0','0','tms:certificate:query','#','admin','2025-07-24 03:41:09','admin','2025-07-24 03:41:09',''),(2106,'证书新增',2104,2,'','',NULL,'',1,0,'F','0','0','tms:certificate:add','#','admin','2025-07-24 03:41:09','admin','2025-07-24 03:41:09',''),(2107,'证书修改',2104,3,'','',NULL,'',1,0,'F','0','0','tms:certificate:edit','#','admin','2025-07-24 03:41:09','admin','2025-07-24 03:41:09',''),(2108,'证书删除',2104,4,'','',NULL,'',1,0,'F','0','0','tms:certificate:remove','#','admin','2025-07-24 03:41:09','admin','2025-07-24 03:41:09',''),(2109,'证书导出',2104,5,'','',NULL,'',1,0,'F','0','0','tms:certificate:export','#','admin','2025-07-24 03:41:09','admin','2025-07-24 03:41:09','');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
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

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (1,'飞安培训中心2025年培训计划发布','2',_binary '<p>各位教员、学员：</p><p>2025年直升机培训计划已经制定完成，请各部门按照计划安排做好相关准备工作。</p><p>重点培训项目包括：</p><ul><li>AS350直升机型别培训</li><li>R44直升机初始培训</li><li>仪表飞行规则培训</li><li>应急程序训练</li></ul>','0','admin','2025-07-23 08:54:03','',NULL,'培训计划'),(2,'关于加强飞行安全管理的通知','1',_binary '<p>为确保培训安全，现就飞行安全管理要求通知如下：</p><p>1. 严格执行飞行前检查程序</p><p>2. 恶劣天气条件下禁止飞行训练</p><p>3. 加强学员安全意识教育</p><p>4. 定期进行安全评估和隐患排查</p>','0','admin','2025-07-23 08:54:03','',NULL,'安全管理'),(3,'新版培训教材和CBT课件上线通知','2',_binary '<p>各位教员和学员：</p><p>新版培训教材和CBT课件已经上线，主要更新内容包括：</p><ul><li>更新最新的航空法规</li><li>增加实际案例分析</li><li>优化交互式学习体验</li><li>新增VR虚拟现实训练模块</li></ul><p>请及时下载学习使用。</p>','0','admin','2025-07-23 08:54:03','',NULL,'教材更新');
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
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

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
INSERT INTO `sys_oper_log` VALUES (100,'个人信息',2,'com.feian.web.controller.system.SysProfileController.updatePwd()','PUT',1,'student001','学员管理科','/system/user/profile/updatePwd','127.0.0.1','内网IP','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2025-07-23 10:28:14',216),(101,'个人信息',2,'com.feian.web.controller.system.SysProfileController.updatePwd()','PUT',1,'student002','学员管理科','/system/user/profile/updatePwd','127.0.0.1','内网IP','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2025-07-24 01:40:15',223),(102,'菜单管理',2,'com.feian.web.controller.system.SysMenuController.edit()','PUT',1,'admin','飞安直升机培训中心','/system/menu','127.0.0.1','内网IP','{\"children\":[],\"component\":\"\",\"createTime\":\"2025-07-24 03:03:29\",\"icon\":\"#\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2058,\"menuName\":\"机型查询\",\"menuType\":\"F\",\"orderNum\":1,\"params\":{},\"parentId\":2056,\"path\":\"\",\"perms\":\"tms:machineType:query\",\"routeName\":\"\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2025-07-24 03:08:45',16),(103,'菜单管理',3,'com.feian.web.controller.system.SysMenuController.remove()','DELETE',1,'admin','飞安直升机培训中心','/system/menu/2099','127.0.0.1','内网IP','2099','{\"msg\":\"菜单已分配,不允许删除\",\"code\":601}',0,NULL,'2025-07-24 03:09:11',14),(104,'菜单管理',3,'com.feian.web.controller.system.SysMenuController.remove()','DELETE',1,'admin','飞安直升机培训中心','/system/menu/2100','127.0.0.1','内网IP','2100','{\"msg\":\"菜单已分配,不允许删除\",\"code\":601}',0,NULL,'2025-07-24 03:09:17',10),(105,'菜单管理',3,'com.feian.web.controller.system.SysMenuController.remove()','DELETE',1,'admin','飞安直升机培训中心','/system/menu/2046','127.0.0.1','内网IP','2046','{\"msg\":\"存在子菜单,不允许删除\",\"code\":601}',0,NULL,'2025-07-24 03:48:29',10),(106,'菜单管理',3,'com.feian.web.controller.system.SysMenuController.remove()','DELETE',1,'admin','飞安直升机培训中心','/system/menu/2047','127.0.0.1','内网IP','2047','{\"msg\":\"菜单已分配,不允许删除\",\"code\":601}',0,NULL,'2025-07-24 03:48:38',9),(107,'菜单管理',3,'com.feian.web.controller.system.SysMenuController.remove()','DELETE',1,'admin','飞安直升机培训中心','/system/menu/4','127.0.0.1','内网IP','4','{\"msg\":\"菜单已分配,不允许删除\",\"code\":601}',0,NULL,'2025-07-24 04:10:16',18),(108,'菜单管理',2,'com.feian.web.controller.system.SysMenuController.edit()','PUT',1,'admin','飞安直升机培训中心','/system/menu','127.0.0.1','内网IP','{\"children\":[],\"component\":\"tms/outline/index\",\"createTime\":\"2025-07-24 03:41:08\",\"icon\":\"documentation\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2025,\"menuName\":\"大纲管理\",\"menuType\":\"C\",\"orderNum\":5,\"params\":{},\"parentId\":0,\"path\":\"outline\",\"perms\":\"tms:outline:list\",\"routeName\":\"\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2025-07-24 04:10:39',17);
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
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

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'director','培训中心主任',1,'0','admin','2025-07-23 08:54:03','',NULL,'负责培训中心全面工作'),(2,'dept_manager','部门经理',2,'0','admin','2025-07-23 08:54:03','',NULL,'负责部门管理工作'),(3,'chief_instructor','首席教员',3,'0','admin','2025-07-23 08:54:03','',NULL,'资深飞行教员，负责教学指导'),(4,'flight_instructor','飞行教员',4,'0','admin','2025-07-23 08:54:03','',NULL,'执行飞行培训任务'),(5,'ground_instructor','地面教员',5,'0','admin','2025-07-23 08:54:03','',NULL,'执行理论教学任务'),(6,'safety_officer','安全员',6,'0','admin','2025-07-23 08:54:03','',NULL,'负责安全管理工作'),(7,'exam_officer','考试员',7,'0','admin','2025-07-23 08:54:03','',NULL,'负责考试评估工作'),(8,'admin_staff','行政人员',8,'0','admin','2025-07-23 08:54:03','',NULL,'负责行政管理工作');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
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

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,'1',1,1,'0','0','admin','2025-07-23 08:54:03','',NULL,'系统超级管理员'),(2,'培训主管','training_manager',2,'2',1,1,'0','0','admin','2025-07-23 08:54:03','',NULL,'培训部门主管'),(3,'飞行教员','flight_instructor',3,'3',1,1,'0','0','admin','2025-07-23 08:54:03','',NULL,'飞行培训教员'),(4,'地面教员','ground_instructor',4,'4',1,1,'0','0','admin','2025-07-23 08:54:03','',NULL,'地面理论教员'),(5,'学员','student',5,'5',1,1,'0','0','admin','2025-07-23 08:54:03','',NULL,'培训学员'),(6,'安全管理员','safety_manager',6,'3',1,1,'0','0','admin','2025-07-23 08:54:03','',NULL,'安全管理人员'),(7,'考试管理员','exam_manager',7,'3',1,1,'0','0','admin','2025-07-23 08:54:03','',NULL,'考试管理人员');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (2,100),(2,101),(2,105),(3,201),(3,202),(4,201),(5,204);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1,2000),(1,2001),(1,2002),(1,2003),(1,2004),(1,2005),(1,2006),(1,2007),(1,2008),(1,2009),(1,2010),(1,2011),(1,2012),(1,2013),(1,2014),(1,2015),(1,2016),(1,2017),(1,2018),(1,2019),(1,2020),(1,2021),(1,2022),(1,2023),(1,2024),(1,2025),(1,2026),(1,2027),(1,2028),(1,2029),(1,2030),(1,2031),(1,2032),(1,2033),(1,2034),(1,2035),(1,2036),(1,2037),(1,2038),(1,2039),(1,2040),(1,2041),(1,2042),(1,2043),(1,2044),(1,2045),(1,2046),(1,2047),(1,2048),(1,2049),(1,2050),(1,2051),(1,2052),(1,2053),(1,2054),(1,2055),(1,2056),(1,2057),(1,2058),(1,2059),(1,2060),(1,2061),(1,2062),(1,2063),(1,2064),(1,2065),(1,2066),(1,2067),(1,2068),(1,2069),(1,2070),(1,2071),(1,2072),(1,2073),(1,2074),(1,2075),(1,2076),(1,2077),(1,2078),(1,2079),(1,2080),(1,2081),(1,2082),(1,2083),(1,2084),(1,2085),(1,2086),(1,2087),(1,2088),(1,2089),(1,2090),(1,2091),(1,2092),(1,2093),(1,2094),(1,2095),(1,2096),(1,2097),(1,2098),(1,2099),(1,2100),(1,2101),(1,2102),(1,2103),(1,2104),(1,2105),(1,2106),(1,2107),(1,2108),(1,2109),(2,1),(2,3),(2,100),(2,101),(2,102),(2,103),(2,104),(2,105),(2,106),(2,107),(2,108),(2,115),(2,116),(2,117),(2,500),(2,501),(2,1000),(2,1001),(2,1002),(2,1003),(2,1004),(2,1005),(2,1006),(2,1007),(2,1008),(2,1009),(2,1010),(2,1011),(2,1012),(2,1013),(2,1014),(2,1015),(2,1016),(2,1017),(2,1018),(2,1019),(2,1020),(2,1021),(2,1022),(2,1023),(2,1024),(2,1025),(2,1026),(2,1027),(2,1028),(2,1029),(2,1030),(2,1031),(2,1032),(2,1033),(2,1034),(2,1035),(2,1036),(2,1037),(2,1038),(2,1039),(2,1040),(2,1041),(2,1042),(2,1043),(2,1044),(2,1045),(2,1046),(2,1047),(2,1048),(2,1049),(2,1050),(2,1051),(2,1052),(2,1053),(2,1054),(2,1055),(2,1056),(2,1057),(2,1058),(2,1059),(2,1060),(3,2000),(3,2001),(3,2002),(3,2007),(3,2008),(3,2013),(3,2014),(3,2019),(3,2020),(3,2025),(3,2026),(3,2031),(3,2032),(3,2033),(3,2034),(3,2035),(3,2037),(3,2040),(3,2041),(3,2043),(3,2046),(3,2047),(3,2048),(3,2049),(3,2050),(3,2051),(3,2052),(3,2053),(3,2055),(3,2058),(3,2059),(3,2060),(3,2061),(3,2062),(3,2063),(3,2064),(3,2065),(3,2066),(3,2067),(3,2068),(3,2069),(3,2070),(3,2071),(3,2072),(3,2073),(3,2075),(3,2078),(3,2079),(3,2081),(3,2084),(3,2085),(3,2086),(3,2088),(3,2091),(3,2092),(3,2094),(3,2097),(3,2098),(3,2099),(3,2100),(3,2101),(3,2102),(3,2103),(3,2104),(3,2105),(3,2106),(3,2107),(4,2000),(4,2001),(4,2002),(4,2007),(4,2008),(4,2013),(4,2014),(4,2019),(4,2020),(4,2025),(4,2026),(4,2031),(4,2032),(4,2033),(4,2034),(4,2035),(4,2037),(4,2046),(4,2047),(4,2048),(4,2049),(4,2050),(4,2051),(4,2058),(4,2059),(4,2060),(4,2061),(4,2062),(4,2063),(4,2064),(4,2071),(4,2072),(4,2073),(4,2075),(4,2078),(4,2079),(4,2081),(4,2084),(4,2085),(4,2086),(4,2088),(4,2091),(4,2092),(4,2094),(4,2097),(4,2098),(4,2099),(4,2100),(4,2101),(4,2102),(4,2103),(4,2104),(4,2105),(4,2106),(5,2000),(5,2025),(5,2026),(5,2031),(5,2032),(5,2033),(5,2034),(5,2035),(5,2058),(5,2059),(5,2060),(5,2065),(5,2066),(5,2084),(5,2085),(5,2086),(5,2097),(5,2098),(5,2103),(5,2104),(5,2105);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
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

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,100,'admin','系统管理员','00','admin@feian.com','13800138000','0','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2025-07-24 18:44:13','2025-07-23 08:54:03','admin','2025-07-23 08:54:03','','2025-07-24 10:44:12','系统管理员',NULL,NULL,NULL,NULL),(5,204,'student001','张三学员','00','zhangsan@feian.com','13800001001','0','','$2a$10$FWMvd0EzyKhi5tq5bIDepuVmygxjSKjBnexqwmFh/0Sxpc4l9swEu','0','0','127.0.0.1','2025-07-24 15:05:40','2025-07-23 10:28:14','admin','2025-07-23 09:55:45','','2025-07-24 07:05:40','学员账号-AS350培训',1,1,NULL,NULL),(6,204,'student002','李四学员','00','lisi@feian.com','13800001002','0','','$2a$10$WyXIOGXIbKOv0.uL07y4g.svEBB5bogRnCfbypXYM3jqfrGIHWmbO','0','0','127.0.0.1','2025-07-24 15:00:58','2025-07-24 01:40:15','admin','2025-07-23 09:55:45','','2025-07-24 07:04:25','学员账号-R44培训',2,2,NULL,NULL),(7,204,'student003','王五学员','00','wangwu@feian.com','13800001003','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','',NULL,NULL,'admin','2025-07-23 09:55:45','',NULL,'学员账号-AS350培训',3,NULL,NULL,NULL),(8,204,'student004','赵六学员','00','zhaoliu@feian.com','13800001004','0','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','',NULL,NULL,'admin','2025-07-23 09:55:45','',NULL,'学员账号-EC135培训',4,NULL,NULL,NULL),(9,204,'student005','孙七学员','00','sunqi@feian.com','13800001005','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','',NULL,NULL,'admin','2025-07-23 09:55:45','',NULL,'学员账号-R44培训',5,NULL,NULL,NULL),(10,201,'instructor001','张教员','00','zhang.instructor@feian.com','13800002001','0','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','',NULL,NULL,'admin','2025-07-23 10:12:00','',NULL,'飞行教员账号',NULL,NULL,NULL,1),(11,201,'instructor002','李教员','00','li.instructor@feian.com','13800002002','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','',NULL,NULL,'admin','2025-07-23 10:12:00','',NULL,'飞行教员账号',NULL,NULL,NULL,2),(12,201,'instructor003','王教员','00','wang.instructor@feian.com','13800002003','0','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','',NULL,NULL,'admin','2025-07-23 10:12:00','',NULL,'首席教员账号',NULL,NULL,NULL,3),(13,202,'instructor004','赵教员','00','zhao.instructor@feian.com','13800002004','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','',NULL,NULL,'admin','2025-07-23 10:12:00','',NULL,'地面教员账号',NULL,NULL,NULL,4);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_post` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` VALUES (1,1),(2,2),(2,4),(3,5);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(2,2),(2,3),(3,4),(4,5),(7,5),(8,5),(9,5),(10,3),(11,3),(12,3),(13,4),(14,5),(15,5),(16,5),(17,5),(18,5);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_certificate`
--

DROP TABLE IF EXISTS `tms_certificate`;
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

--
-- Dumping data for table `tms_certificate`
--

LOCK TABLES `tms_certificate` WRITE;
/*!40000 ALTER TABLE `tms_certificate` DISABLE KEYS */;
INSERT INTO `tms_certificate` VALUES (1,'12138','01证书',4,1,1,1,1,'1','2025-07-02',NULL,NULL,'天直',NULL,NULL,NULL,'0',NULL,'2025-07-08 00:00:00','2025-07-02 00:00:00','2025-07-02 00:00:00',NULL,NULL,0,'33',NULL,NULL,NULL,NULL,NULL,'22'),(2,'单独','01证书',5,1,1,1,1,'2','2025-07-17',NULL,NULL,'单独',NULL,NULL,NULL,'0',NULL,'2025-07-09 00:00:00','2025-07-17 00:00:00','2025-07-17 00:00:00',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,'单独');
/*!40000 ALTER TABLE `tms_certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_class_student`
--

DROP TABLE IF EXISTS `tms_class_student`;
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

--
-- Dumping data for table `tms_class_student`
--

LOCK TABLES `tms_class_student` WRITE;
/*!40000 ALTER TABLE `tms_class_student` DISABLE KEYS */;
INSERT INTO `tms_class_student` VALUES (1,4,4,NULL,'0',NULL,NULL,'2025-07-24 18:11:43','2025-07-24 18:11:43','system','system',0,'试试');
/*!40000 ALTER TABLE `tms_class_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_courseware`
--

DROP TABLE IF EXISTS `tms_courseware`;
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

--
-- Dumping data for table `tms_courseware`
--

LOCK TABLES `tms_courseware` WRITE;
/*!40000 ALTER TABLE `tms_courseware` DISABLE KEYS */;
INSERT INTO `tms_courseware` VALUES (1,'AS350-AIRCREW-THEORY-001','AS350基础理论','ATA-71',1,1,1,'1',20.00,0.00,'AS350直升机基础理论知识','掌握AS350直升机基本构造和工作原理','完成理论考试并达到80分以上','0',1,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'AS350理论课程'),(2,'AS350-AIRCREW-PRACTICE-001','AS350飞行训练','ATA-27',1,1,2,'1',0.00,50.00,'AS350直升机飞行训练','掌握AS350直升机基本飞行技能','完成规定飞行科目和检查','0',2,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'AS350飞行训练'),(3,'R44-AIRCREW-THEORY-001','R44基础理论','ATA-71',2,1,1,'1',15.00,0.00,'R44直升机基础理论知识','掌握R44直升机基本构造和工作原理','完成理论考试并达到80分以上','0',3,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'R44理论课程'),(4,'R44-AIRCREW-PRACTICE-001','R44飞行训练','ATA-27',2,1,2,'1',0.00,40.00,'R44直升机飞行训练','掌握R44直升机基本飞行技能','完成规定飞行科目和检查','0',4,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'R44飞行训练'),(5,'GROUND-THEORY-001','地勤基础理论','ATA-05',1,2,1,'1',30.00,0.00,'直升机地勤基础理论','掌握直升机维护基础知识','完成理论考试并达到85分以上','0',5,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'地勤理论课程'),(6,'THEORY-AS350-001','AS350直升机系统概述','ATA-05',1,1,1,'1',2.00,0.00,'AS350直升机系统基础理论课程',NULL,NULL,'0',1,'2025-07-24 02:07:27','2025-07-24 02:07:27','admin',NULL,0,NULL),(7,'THEORY-AS350-002','AS350发动机原理','ATA-71',1,1,1,'1',3.00,0.00,'AS350发动机工作原理与维护',NULL,NULL,'0',2,'2025-07-24 02:07:27','2025-07-24 02:07:27','admin',NULL,0,NULL),(8,'THEORY-AS350-003','AS350飞行控制系统','ATA-27',1,1,1,'1',2.50,0.00,'AS350飞行控制系统理论知识',NULL,NULL,'0',3,'2025-07-24 02:07:27','2025-07-24 02:07:27','admin',NULL,0,NULL),(9,'THEORY-R44-001','R44直升机基础知识','ATA-05',2,1,1,'1',2.00,0.00,'R44直升机基础理论课程',NULL,NULL,'0',1,'2025-07-24 02:07:27','2025-07-24 02:07:27','admin',NULL,0,NULL),(10,'THEORY-R44-002','R44发动机系统','ATA-71',2,1,1,'1',3.00,0.00,'R44发动机系统理论',NULL,NULL,'0',2,'2025-07-24 02:07:27','2025-07-24 02:07:27','admin',NULL,0,NULL),(11,'PRACTICAL-AS350-001','AS350日常检查实作','ATA-05',1,2,2,'2',0.00,4.00,'AS350直升机日常检查实际操作',NULL,NULL,'0',1,'2025-07-24 02:07:27','2025-07-24 02:07:27','admin',NULL,0,NULL),(12,'PRACTICAL-AS350-002','AS350发动机维护实作','ATA-71',1,2,2,'2',0.00,6.00,'AS350发动机维护实际操作',NULL,NULL,'0',2,'2025-07-24 02:07:27','2025-07-24 02:07:27','admin',NULL,0,NULL),(13,'PRACTICAL-AS350-003','AS350航电系统检修','ATA-31',1,2,2,'2',0.00,4.00,'AS350航电系统检修实作',NULL,NULL,'0',3,'2025-07-24 02:07:27','2025-07-24 02:07:27','admin',NULL,0,NULL),(14,'PRACTICAL-R44-001','R44系统检查实作','ATA-05',2,2,2,'2',0.00,3.00,'R44系统检查实际操作',NULL,NULL,'0',1,'2025-07-24 02:07:27','2025-07-24 02:07:27','admin',NULL,0,NULL),(15,'PRACTICAL-R44-002','R44发动机拆装','ATA-71',2,2,2,'2',0.00,8.00,'R44发动机拆装实作训练',NULL,NULL,'0',2,'2025-07-24 02:07:27','2025-07-24 02:07:27','admin',NULL,0,NULL),(16,'001','单独','单独',1,1,1,'1',2.00,NULL,NULL,NULL,NULL,'0',1,'2025-07-24 11:58:04','2025-07-24 11:58:04','system','system',0,NULL),(17,'300000','张杰的名字','AKA章节',3,2,1,'1',100.00,NULL,NULL,NULL,NULL,'0',1,'2025-07-24 13:41:31','2025-07-24 13:43:22','system','system',1,NULL);
/*!40000 ALTER TABLE `tms_courseware` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_courseware_file`
--

DROP TABLE IF EXISTS `tms_courseware_file`;
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

--
-- Dumping data for table `tms_courseware_file`
--

LOCK TABLES `tms_courseware_file` WRITE;
/*!40000 ALTER TABLE `tms_courseware_file` DISABLE KEYS */;
INSERT INTO `tms_courseware_file` VALUES (1,6,'AS350系统概述教材.pdf','1','/upload/courseware/AS350系统概述教材.pdf',2048000,'.pdf','AS350系统概述理论教材',0,'0',1,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL),(2,6,'AS350系统概述.ppt','3','/upload/courseware/AS350系统概述.ppt',5120000,'.ppt','AS350系统概述PPT课件',0,'0',2,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL),(3,6,'AS350基础CBT.exe','4','/upload/courseware/AS350基础CBT.exe',50240000,'.exe','AS350基础知识CBT课件',0,'0',3,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL),(4,7,'AS350发动机教材.pdf','1','/upload/courseware/AS350发动机教材.pdf',3072000,'.pdf','AS350发动机原理教材',0,'0',1,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL),(5,7,'AS350发动机原理.ppt','3','/upload/courseware/AS350发动机原理.ppt',8192000,'.ppt','AS350发动机原理PPT',0,'0',2,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL),(6,11,'AS350日常检查工卡01.pdf','5','/upload/courseware/AS350日常检查工卡01.pdf',1024000,'.pdf','AS350日常检查工卡1',0,'0',1,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL),(7,11,'AS350日常检查工卡02.pdf','5','/upload/courseware/AS350日常检查工卡02.pdf',1024000,'.pdf','AS350日常检查工卡2',0,'0',2,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL),(8,11,'AS350日常检查教案.pdf','2','/upload/courseware/AS350日常检查教案.pdf',2048000,'.pdf','AS350日常检查实作教案',0,'0',3,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL),(9,12,'AS350发动机维护工卡.pdf','5','/upload/courseware/AS350发动机维护工卡.pdf',1536000,'.pdf','AS350发动机维护工卡',0,'0',1,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL),(10,12,'AS350发动机维护教案.pdf','2','/upload/courseware/AS350发动机维护教案.pdf',2048000,'.pdf','AS350发动机维护教案',0,'0',2,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL),(11,12,'AS350发动机维护VMT.vmt','7','/upload/courseware/AS350发动机维护VMT.vmt',10240000,'.vmt','AS350发动机维护VMT培训文件',0,'0',3,'2025-07-24 02:07:48','2025-07-24 02:07:48','admin',NULL,0,NULL);
/*!40000 ALTER TABLE `tms_courseware_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_instructor`
--

DROP TABLE IF EXISTS `tms_instructor`;
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

--
-- Dumping data for table `tms_instructor`
--

LOCK TABLES `tms_instructor` WRITE;
/*!40000 ALTER TABLE `tms_instructor` DISABLE KEYS */;
INSERT INTO `tms_instructor` VALUES (1,'INS001','张教员','0','1980-03-15','110101198003150011','13900001001','zhang_instructor@feian.com','理论培训科','高级教员','2010-06-01','4','2012-01-01','4','高级工程师','0','/avatars/instructor1.jpg','资深飞行教员，拥有15年教学经验','0','2025-07-23 08:54:03','2025-07-24 11:48:03','admin','system',1,'AS350首席教员',10,NULL,NULL,NULL),(2,'INS002','李教员','1','1985-07-22','110101198507220022','13900001002','li_instructor@feian.com','实践培训科','中级教员','2015-09-01','3','2016-03-01','3','工程师','0','/avatars/instructor2.jpg','实践培训专家，专注实操技能培训','0','2025-07-23 08:54:03','2025-07-24 11:48:00','admin','system',1,'R44飞行教员',11,NULL,NULL,NULL),(3,'INS003','王教员','0','1982-11-08','110101198211080033','13900001003','wang_instructor@feian.com','理论培训科','高级教员','2012-04-01','4','2013-01-01','4','高级工程师','0','/avatars/instructor3.jpg','理论教学专家，擅长复杂理论讲解','0','2025-07-23 08:54:03','2025-07-24 11:47:58','admin','system',1,'地勤理论教员',12,NULL,NULL,NULL),(4,'INST001','张教员','0','1980-05-15','110101198005151234','13800002001','zhang.instructor@feian.com',NULL,NULL,'2010-03-01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','2025-07-23 10:12:00','2025-07-24 15:46:34','admin','system',0,'AS350/R44资深飞行教员',13,'高级教员',15,NULL),(5,'INST002','李教员','1','1982-08-22','110101198208221234','13800002002','li.instructor@feian.com',NULL,NULL,'2012-06-15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','2025-07-23 10:12:00','0000-00-00 00:00:00','admin',NULL,0,'R44/EC135飞行教员',0,'标准教员',12,NULL),(6,'INST003','王教员','0','1978-12-08','110101197812081234','13800002003','wang.instructor@feian.com',NULL,NULL,'2008-01-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','2025-07-23 10:12:00','0000-00-00 00:00:00','admin',NULL,0,'全机型首席教员',0,'首席教员',20,NULL),(7,'INST004','赵教员','1','1985-03-25','110101198503251234','13800002004','zhao.instructor@feian.com',NULL,NULL,'2016-09-01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','2025-07-23 10:12:00','0000-00-00 00:00:00','admin',NULL,0,'EC135/Bell206教员',0,'标准教员',8,NULL);
/*!40000 ALTER TABLE `tms_instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_instructor_ability`
--

DROP TABLE IF EXISTS `tms_instructor_ability`;
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

--
-- Dumping data for table `tms_instructor_ability`
--

LOCK TABLES `tms_instructor_ability` WRITE;
/*!40000 ALTER TABLE `tms_instructor_ability` DISABLE KEYS */;
/*!40000 ALTER TABLE `tms_instructor_ability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_instructor_machine_type`
--

DROP TABLE IF EXISTS `tms_instructor_machine_type`;
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

--
-- Dumping data for table `tms_instructor_machine_type`
--

LOCK TABLES `tms_instructor_machine_type` WRITE;
/*!40000 ALTER TABLE `tms_instructor_machine_type` DISABLE KEYS */;
INSERT INTO `tms_instructor_machine_type` VALUES (1,1,1,'1','SENIOR','0','admin','2025-07-23 10:12:00','',NULL,'主要机型AS350，高级教员','0'),(2,1,2,'0','SENIOR','0','admin','2025-07-23 10:12:00','',NULL,'副机型R44，高级教员','0'),(3,2,2,'1','STANDARD','0','admin','2025-07-23 10:12:00','',NULL,'主要机型R44，标准教员','0'),(4,2,3,'0','STANDARD','0','admin','2025-07-23 10:12:00','',NULL,'副机型EC135，标准教员','0'),(5,3,1,'1','CHIEF','0','admin','2025-07-23 10:12:00','',NULL,'主要机型AS350，首席教员','0'),(6,3,2,'0','CHIEF','0','admin','2025-07-23 10:12:00','',NULL,'R44机型，首席教员','0'),(7,3,3,'0','CHIEF','0','admin','2025-07-23 10:12:00','',NULL,'EC135机型，首席教员','0'),(8,3,4,'0','CHIEF','0','admin','2025-07-23 10:12:00','',NULL,'Bell206机型，首席教员','0'),(9,4,3,'1','STANDARD','0','admin','2025-07-23 10:12:00','',NULL,'主要机型EC135，标准教员','0'),(10,4,4,'0','STANDARD','0','admin','2025-07-23 10:12:00','',NULL,'副机型Bell206，标准教员','0');
/*!40000 ALTER TABLE `tms_instructor_machine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_machine_type`
--

DROP TABLE IF EXISTS `tms_machine_type`;
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

--
-- Dumping data for table `tms_machine_type`
--

LOCK TABLES `tms_machine_type` WRITE;
/*!40000 ALTER TABLE `tms_machine_type` DISABLE KEYS */;
INSERT INTO `tms_machine_type` VALUES (1,'AS350直升机','AS350','/images/aircraft/as350.jpg','AS350 Ecureuil是法国宇航公司生产的单发涡轴轻型直升机','0',1,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'AS350系列直升机'),(2,'R44直升机','R44','/images/aircraft/r44.jpg','Robinson R44是美国罗宾逊直升机公司生产的四座轻型直升机','0',2,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'R44系列直升机'),(3,'R22直升机','R22','/images/aircraft/r22.jpg','Robinson R22是美国罗宾逊直升机公司生产的双座轻型直升机','0',3,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'R22系列直升机'),(4,'EC135直升机','EC135','/images/aircraft/ec135.jpg','EC135是欧洲直升机公司（现空客直升机）生产的双发轻型直升机','0',4,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'EC135系列直升机'),(5,'Bell206直升机','Bell206','/images/aircraft/bell206.jpg','Bell 206是贝尔直升机公司生产的单发轻型直升机','0',5,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'Bell206系列直升机');
/*!40000 ALTER TABLE `tms_machine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_major`
--

DROP TABLE IF EXISTS `tms_major`;
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

--
-- Dumping data for table `tms_major`
--

LOCK TABLES `tms_major` WRITE;
/*!40000 ALTER TABLE `tms_major` DISABLE KEYS */;
INSERT INTO `tms_major` VALUES (1,'空勤专业','AIRCREW','1','负责直升机驾驶和空中作业的专业人员','0',1,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'空勤人员专业'),(2,'地勤专业','GROUND_CREW','2','负责直升机维护和地面保障的专业人员','0',2,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'地勤人员专业');
/*!40000 ALTER TABLE `tms_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_outline_chapter`
--

DROP TABLE IF EXISTS `tms_outline_chapter`;
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

--
-- Dumping data for table `tms_outline_chapter`
--

LOCK TABLES `tms_outline_chapter` WRITE;
/*!40000 ALTER TABLE `tms_outline_chapter` DISABLE KEYS */;
INSERT INTO `tms_outline_chapter` VALUES (1,1,'ATA-01','通用知识',0,1,1,20.0,0.0,'1','航空器通用知识和基础理论','掌握航空器基本原理和通用知识','0','admin','2025-07-24 03:54:01','','2025-07-24 03:54:01',NULL,'0'),(2,1,'ATA-01-01','航空法规',1,2,1,8.0,0.0,'1','民航法规和规章制度','熟悉相关法规要求','0','admin','2025-07-24 03:54:01','','2025-07-24 03:54:01',NULL,'0'),(3,1,'ATA-01-02','空气动力学',1,2,2,12.0,0.0,'1','空气动力学基础知识','理解飞行原理','0','admin','2025-07-24 03:54:01','','2025-07-24 03:54:01',NULL,'0'),(4,1,'ATA-27','飞行操纵系统',0,1,2,25.0,20.0,'1','飞行操纵系统的结构和操作','掌握飞行操纵系统操作技能','0','admin','2025-07-24 03:54:01','','2025-07-24 03:54:01',NULL,'0'),(5,1,'ATA-29','液压系统',0,1,3,15.0,10.0,'1','液压系统的原理和维护','理解液压系统工作原理','0','admin','2025-07-24 03:54:01','','2025-07-24 03:54:01',NULL,'0'),(6,1,'ATA-71','动力装置',0,1,4,30.0,25.0,'1','发动机系统的知识和操作','掌握发动机操作和应急程序','0','admin','2025-07-24 03:54:01','','2025-07-24 03:54:01',NULL,'0'),(7,1,'ATA-91','应急程序',0,1,5,20.0,15.0,'1','各种应急情况的处理程序','熟练掌握应急程序','0','admin','2025-07-24 03:54:01','','2025-07-24 03:54:01',NULL,'0'),(8,1,'ATA-95','飞行训练',0,1,6,10.0,10.0,'1','实际飞行训练和考核','达到飞行技能要求','0','admin','2025-07-24 03:54:01','','2025-07-24 03:54:01',NULL,'0');
/*!40000 ALTER TABLE `tms_outline_chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_student`
--

DROP TABLE IF EXISTS `tms_student`;
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

--
-- Dumping data for table `tms_student`
--

LOCK TABLES `tms_student` WRITE;
/*!40000 ALTER TABLE `tms_student` DISABLE KEYS */;
INSERT INTO `tms_student` VALUES (4,'ST001','张三学员','0','1995-03-15','110101199503151234','13800001001','zhangsan@feian.com','培训部','学员','2024-01-15',1,1,'3','1',NULL,'0','2025-07-23 09:55:45','2025-07-24 15:59:05','admin','system',0,'AS350机型培训学员',5,NULL,NULL,NULL),(5,'ST002','李四学员','0','1996-07-20','110101199607201234','13800001002','lisi@feian.com','培训部','学员','2024-02-01',2,1,'3','1',NULL,'0','2025-07-23 09:55:45','2025-07-24 15:34:15','admin','system',0,'R44机型培训学员',6,NULL,NULL,NULL),(6,'ST003','王五学员','1','1994-11-08','110101199411081234','13800001003','wangwu@feian.com','培训部','学员','2024-03-10',1,1,'4','0',NULL,'0','2025-07-23 09:55:45','2025-07-24 09:51:07','admin','system',0,'AS350机型培训学员',7,NULL,NULL,NULL),(7,'ST004','赵六学员','0','1997-05-25','110101199705251234','13800001004','zhaoliu@feian.com','培训部','学员','2024-04-01',3,1,'3','1',NULL,'0','2025-07-23 09:55:45','0000-00-00 00:00:00','admin',NULL,0,'EC135机型培训学员',8,NULL,NULL,NULL),(8,'ST005','孙七学员','1','1995-09-12','110101199509121234','13800001005','sunqi@feian.com','培训部','学员','2024-05-15',2,1,'3','2',NULL,'0','2025-07-23 09:55:45','0000-00-00 00:00:00','admin',NULL,0,'R44机型培训学员',9,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tms_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_student_ability`
--

DROP TABLE IF EXISTS `tms_student_ability`;
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

--
-- Dumping data for table `tms_student_ability`
--

LOCK TABLES `tms_student_ability` WRITE;
/*!40000 ALTER TABLE `tms_student_ability` DISABLE KEYS */;
/*!40000 ALTER TABLE `tms_student_ability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_student_machine_type`
--

DROP TABLE IF EXISTS `tms_student_machine_type`;
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

--
-- Dumping data for table `tms_student_machine_type`
--

LOCK TABLES `tms_student_machine_type` WRITE;
/*!40000 ALTER TABLE `tms_student_machine_type` DISABLE KEYS */;
INSERT INTO `tms_student_machine_type` VALUES (11,1,1,'1','0','admin','2025-07-24 01:36:27','',NULL,'张三学员-AS350主要机型',NULL),(12,1,2,'0','0','admin','2025-07-24 01:36:27','',NULL,'张三学员-R44副机型',NULL),(13,1,3,'0','0','admin','2025-07-24 01:36:27','',NULL,'张三学员-EC135副机型',NULL),(16,3,1,'1','0','admin','2025-07-24 01:36:34','',NULL,'王五学员-AS350主要机型',NULL),(17,3,3,'0','0','admin','2025-07-24 01:36:34','',NULL,'王五学员-EC135副机型',NULL),(29,6,1,'1','0','system','2025-07-24 09:51:15','system','2025-07-24 09:51:15',NULL,NULL),(30,6,2,'0','0','system','2025-07-24 09:51:15','system','2025-07-24 09:51:15',NULL,NULL),(46,15,1,'1','0','system','2025-07-24 13:33:40','system','2025-07-24 13:33:40',NULL,NULL),(47,15,2,'0','0','system','2025-07-24 13:33:40','system','2025-07-24 13:33:40',NULL,NULL),(48,15,3,'0','0','system','2025-07-24 13:33:40','system','2025-07-24 13:33:40',NULL,NULL),(49,15,4,'0','0','system','2025-07-24 13:33:40','system','2025-07-24 13:33:40',NULL,NULL),(50,15,5,'0','0','system','2025-07-24 13:33:40','system','2025-07-24 13:33:40',NULL,NULL),(63,5,2,'1','0','system','2025-07-24 15:34:15','system','2025-07-24 15:34:15',NULL,NULL),(64,5,3,'0','0','system','2025-07-24 15:34:15','system','2025-07-24 15:34:15',NULL,NULL),(65,5,4,'0','0','system','2025-07-24 15:34:15','system','2025-07-24 15:34:15',NULL,NULL),(66,4,1,'1','0','system','2025-07-24 15:59:05','system','2025-07-24 15:59:05',NULL,NULL),(67,4,2,'0','0','system','2025-07-24 15:59:05','system','2025-07-24 15:59:05',NULL,NULL),(68,4,3,'0','0','system','2025-07-24 15:59:05','system','2025-07-24 15:59:05',NULL,NULL),(69,4,4,'0','0','system','2025-07-24 15:59:05','system','2025-07-24 15:59:05',NULL,NULL);
/*!40000 ALTER TABLE `tms_student_machine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_training_ability`
--

DROP TABLE IF EXISTS `tms_training_ability`;
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

--
-- Dumping data for table `tms_training_ability`
--

LOCK TABLES `tms_training_ability` WRITE;
/*!40000 ALTER TABLE `tms_training_ability` DISABLE KEYS */;
INSERT INTO `tms_training_ability` VALUES (1,'初始培训','INITIAL','1','首次获得某机型或专业资质的培训','0',1,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'初始培训能力',NULL,NULL),(2,'复训','RECURRENT','2','定期进行的复习和更新培训','0',2,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'复训能力',NULL,NULL),(3,'差异培训','DIFFERENCE','3','不同机型或设备间的差异化培训','0',3,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'差异培训能力',NULL,NULL),(4,'恢复培训','RESTORATION','4','恢复已过期或暂停资质的培训','0',4,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'恢复培训能力',NULL,NULL),(5,'培训','TRAINING','0','完整的培训计划，包含理论和实践的全面培训','0',NULL,'2025-07-24 02:25:39',NULL,'admin',NULL,0,NULL,7,45),(6,'复训','RETRAIN','0','复习和强化已有技能的培训','0',NULL,'2025-07-24 02:25:39',NULL,'admin',NULL,0,NULL,6,20),(7,'恢复考试','RECOVERY_EXAM','0','恢复资质的考试培训','0',NULL,'2025-07-24 02:25:39',NULL,'admin',NULL,0,NULL,4,10);
/*!40000 ALTER TABLE `tms_training_ability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_training_class`
--

DROP TABLE IF EXISTS `tms_training_class`;
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

--
-- Dumping data for table `tms_training_class`
--

LOCK TABLES `tms_training_class` WRITE;
/*!40000 ALTER TABLE `tms_training_class` DISABLE KEYS */;
INSERT INTO `tms_training_class` VALUES (1,'CLASS-2025-001','AS350空勤第一期培训班',1,1,1,1,'2025-03-01','2025-05-30',NULL,NULL,10,0,1,'AS350直升机空勤人员初始培训第一期','0','2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'首期AS350培训班'),(2,'CLASS-2025-002','R44空勤第一期培训班',2,2,1,1,'2025-04-01','2025-06-30',NULL,NULL,8,0,2,'R44直升机空勤人员初始培训第一期','0','2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'首期R44培训班'),(3,'2025-003','AS350直升机-空勤专业-复训-培训计划-2025-003班',8,1,1,2,'2025-07-07',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','2025-07-24 17:29:12','2025-07-24 17:29:12','system','system',0,NULL),(4,'2025-004','AS350直升机-空勤专业-初始培训-培训计划-2025-004班',9,1,1,1,'2025-07-03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','2025-07-24 17:39:11','2025-07-24 17:39:11','system','system',0,NULL),(5,'查查','等我',9,1,1,1,'2025-07-02','2025-07-04',NULL,NULL,0,0,NULL,NULL,'0','2025-07-24 18:02:58','2025-07-24 18:02:58','system','system',0,NULL);
/*!40000 ALTER TABLE `tms_training_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_training_outline`
--

DROP TABLE IF EXISTS `tms_training_outline`;
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

--
-- Dumping data for table `tms_training_outline`
--

LOCK TABLES `tms_training_outline` WRITE;
/*!40000 ALTER TABLE `tms_training_outline` DISABLE KEYS */;
INSERT INTO `tms_training_outline` (`outline_id`, `machine_type_id`, `major_id`, `training_ability_id`, `outline_name`, `outline_code`, `regulation_file_id`, `regulation_file_name`, `regulation_file_path`, `outline_file_id`, `outline_file_name`, `outline_file_path`, `theory_hours`, `practical_hours`, `description`, `effective_date`, `expiry_date`, `version`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `is_deleted`) VALUES (1,1,1,1,'H135空勤培训大纲','H135-PILOT-TRAIN-V1.0',NULL,NULL,NULL,NULL,NULL,NULL,120.0,80.0,'H135机型空勤人员培训大纲，包含理论和实践培训内容','2024-01-01',NULL,'V1.0','0','admin','2025-07-24 03:54:01','','2025-07-24 08:47:30','初始版本',0),(2,1,2,1,'H135地勤培训大纲','H135-MAINT-TRAIN-V1.0',NULL,NULL,NULL,NULL,NULL,NULL,80.0,120.0,'H135机型地勤人员培训大纲，重点是维护和检修','2024-01-01',NULL,'V1.0','0','admin','2025-07-24 03:54:01','','2025-07-24 08:47:30','初始版本',0),(3,2,1,1,'H145空勤培训大纲','H145-PILOT-TRAIN-V1.0',NULL,NULL,NULL,NULL,NULL,NULL,140.0,100.0,'H145机型空勤人员培训大纲，包含理论和实践培训内容','2024-01-01',NULL,'V1.0','0','admin','2025-07-24 03:54:01','','2025-07-24 08:47:30','初始版本',0),(4,1,1,4,'测试','11',NULL,NULL,NULL,NULL,NULL,NULL,2.0,2.0,NULL,'2025-07-08','2025-07-17','1','0','system','2025-07-24 16:45:06','system','2025-07-24 08:47:30',NULL,0),(5,1,1,5,'测试','11',NULL,NULL,NULL,NULL,NULL,NULL,2.0,2.0,NULL,'2025-07-08','2025-07-17','1','0','system','2025-07-24 16:45:45','system','2025-07-24 08:47:30',NULL,0),(6,1,1,7,'测试','11',NULL,NULL,NULL,NULL,NULL,NULL,2.0,2.0,NULL,'2025-07-08','2025-07-17','1','0','system','2025-07-24 16:46:20','system','2025-07-24 08:47:30',NULL,0);
/*!40000 ALTER TABLE `tms_training_outline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_training_plan`
--

DROP TABLE IF EXISTS `tms_training_plan`;
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

--
-- Dumping data for table `tms_training_plan`
--

LOCK TABLES `tms_training_plan` WRITE;
/*!40000 ALTER TABLE `tms_training_plan` DISABLE KEYS */;
INSERT INTO `tms_training_plan` VALUES (1,'2025年AS350空勤初始培训计划','PLAN-2025-AS350-AIRCREW-001',1,1,1,10,'AS350直升机空勤人员初始培训，包括理论学习和飞行训练','2','2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'2025年度重点培训计划',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL),(2,'2025年R44空勤初始培训计划','PLAN-2025-R44-AIRCREW-001',2,1,1,8,'R44直升机空勤人员初始培训，注重实用性和安全性','2','2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'R44专项培训计划',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL),(3,'2025年地勤专业培训计划','PLAN-2025-GROUND-001',1,2,1,15,'直升机地勤人员专业技能培训','2','2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'地勤人员培训计划',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL),(4,'AS350空勤人员培训计划','PLAN-AS350-AIR-001',1,1,1,NULL,NULL,'0','2025-07-24 02:28:37',NULL,'admin',NULL,0,NULL,'2025-02-01',NULL,315.00,NULL,NULL,'0','0',NULL,NULL,NULL),(5,'AS350地勤人员培训计划','PLAN-AS350-GROUND-001',1,2,1,NULL,NULL,'0','2025-07-24 02:28:37',NULL,'admin',NULL,0,NULL,'2025-02-15',NULL,280.00,NULL,NULL,'0','0',NULL,NULL,NULL),(6,'R44空勤人员复训计划','PLAN-R44-AIR-001',2,1,2,NULL,NULL,'0','2025-07-24 02:28:37',NULL,'admin',NULL,0,NULL,'2025-03-01',NULL,120.00,NULL,NULL,'0','0',NULL,NULL,NULL),(7,'菜单','测试',1,1,1,NULL,NULL,'0','2025-07-24 17:19:51','2025-07-24 17:19:51','system','system',0,NULL,'2025-07-02','2025-07-10',4.00,2.00,2.00,'0','0',NULL,NULL,NULL),(8,'AS350直升机-空勤专业-复训-培训计划','2025-PLAN-001',1,1,2,NULL,NULL,'0','2025-07-24 17:29:12','2025-07-24 17:29:12','system','system',0,NULL,'2025-07-07',NULL,NULL,NULL,NULL,'0','1','AS350直升机-空勤专业-复训-培训计划-2025-003班-课程表','AS350直升机-空勤专业-复训-培训计划-2025-003班-教学进度安排表','AS350直升机-空勤专业-复训-培训计划-2025-003班-实作项目清单'),(9,'AS350直升机-空勤专业-初始培训-培训计划','2025-PLAN-002',1,1,1,NULL,NULL,'0','2025-07-24 17:39:11','2025-07-24 17:39:11','system','system',0,NULL,'2025-07-03',NULL,NULL,NULL,NULL,'0','1','AS350直升机-空勤专业-初始培训-培训计划-2025-004班-课程表','AS350直升机-空勤专业-初始培训-培训计划-2025-004班-教学进度安排表','AS350直升机-空勤专业-初始培训-培训计划-2025-004班-实作项目清单');
/*!40000 ALTER TABLE `tms_training_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_training_plan_detail`
--

DROP TABLE IF EXISTS `tms_training_plan_detail`;
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

--
-- Dumping data for table `tms_training_plan_detail`
--

LOCK TABLES `tms_training_plan_detail` WRITE;
/*!40000 ALTER TABLE `tms_training_plan_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `tms_training_plan_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_training_plan_instructor`
--

DROP TABLE IF EXISTS `tms_training_plan_instructor`;
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

--
-- Dumping data for table `tms_training_plan_instructor`
--

LOCK TABLES `tms_training_plan_instructor` WRITE;
/*!40000 ALTER TABLE `tms_training_plan_instructor` DISABLE KEYS */;
INSERT INTO `tms_training_plan_instructor` VALUES (1,1,1,'1','主责教员，负责整体培训进度','0','admin','2025-07-24 02:28:37','',NULL,NULL,'0'),(2,1,3,'0','理论课程教学','0','admin','2025-07-24 02:28:37','',NULL,NULL,'0'),(3,2,2,'1','主责教员，负责地勤培训','0','admin','2025-07-24 02:28:37','',NULL,NULL,'0'),(4,2,4,'0','实作指导教员','0','admin','2025-07-24 02:28:37','',NULL,NULL,'0'),(5,3,1,'1','复训主责教员','0','admin','2025-07-24 02:28:37','',NULL,NULL,'0'),(6,7,5,'0',NULL,'0','system','2025-07-24 17:19:51','system','2025-07-24 17:19:51',NULL,'0'),(7,7,6,'0',NULL,'0','system','2025-07-24 17:19:51','system','2025-07-24 17:19:51',NULL,'0'),(8,9,4,'1',NULL,'0','system','2025-07-24 17:39:11','system','2025-07-24 17:39:11',NULL,'0'),(9,9,5,'0',NULL,'0','system','2025-07-24 17:39:11','system','2025-07-24 17:39:11',NULL,'0');
/*!40000 ALTER TABLE `tms_training_plan_instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_training_plan_schedule`
--

DROP TABLE IF EXISTS `tms_training_plan_schedule`;
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

--
-- Dumping data for table `tms_training_plan_schedule`
--

LOCK TABLES `tms_training_plan_schedule` WRITE;
/*!40000 ALTER TABLE `tms_training_plan_schedule` DISABLE KEYS */;
INSERT INTO `tms_training_plan_schedule` VALUES (1,8,'class_schedule',1,'08:30-12:00',1,'AS350基础理论','1',3.5,NULL,NULL,'理论学习：AS350基础理论','0','system','2025-07-24 17:29:12','system','2025-07-24 17:29:12',NULL,'0'),(2,8,'class_schedule',2,'08:30-12:00',6,'AS350直升机系统概述','1',3.5,NULL,NULL,'理论学习：AS350直升机系统概述','0','system','2025-07-24 17:29:12','system','2025-07-24 17:29:12',NULL,'0'),(3,8,'class_schedule',3,'08:30-12:00',16,'单独','1',3.5,NULL,NULL,'理论学习：单独','0','system','2025-07-24 17:29:12','system','2025-07-24 17:29:12',NULL,'0'),(4,8,'class_schedule',4,'08:30-12:00',7,'AS350发动机原理','1',3.5,NULL,NULL,'理论学习：AS350发动机原理','0','system','2025-07-24 17:29:12','system','2025-07-24 17:29:12',NULL,'0'),(5,8,'class_schedule',5,'14:00-17:30',2,'AS350飞行训练','2',3.5,NULL,NULL,'实践训练：AS350飞行训练','0','system','2025-07-24 17:29:12','system','2025-07-24 17:29:12',NULL,'0'),(6,8,'class_schedule',6,'08:30-12:00',8,'AS350飞行控制系统','1',3.5,NULL,NULL,'理论学习：AS350飞行控制系统','0','system','2025-07-24 17:29:12','system','2025-07-24 17:29:12',NULL,'0'),(7,9,'class_schedule',1,'08:30-12:00',1,'AS350基础理论','1',3.5,NULL,NULL,'理论学习：AS350基础理论','0','system','2025-07-24 17:39:11','system','2025-07-24 17:39:11',NULL,'0'),(8,9,'class_schedule',2,'08:30-12:00',6,'AS350直升机系统概述','1',3.5,NULL,NULL,'理论学习：AS350直升机系统概述','0','system','2025-07-24 17:39:11','system','2025-07-24 17:39:11',NULL,'0'),(9,9,'class_schedule',3,'08:30-12:00',16,'单独','1',3.5,NULL,NULL,'理论学习：单独','0','system','2025-07-24 17:39:11','system','2025-07-24 17:39:11',NULL,'0'),(10,9,'class_schedule',4,'08:30-12:00',7,'AS350发动机原理','1',3.5,NULL,NULL,'理论学习：AS350发动机原理','0','system','2025-07-24 17:39:11','system','2025-07-24 17:39:11',NULL,'0'),(11,9,'class_schedule',5,'14:00-17:30',2,'AS350飞行训练','2',3.5,NULL,NULL,'实践训练：AS350飞行训练','0','system','2025-07-24 17:39:11','system','2025-07-24 17:39:11',NULL,'0'),(12,9,'class_schedule',6,'08:30-12:00',8,'AS350飞行控制系统','1',3.5,NULL,NULL,'理论学习：AS350飞行控制系统','0','system','2025-07-24 17:39:11','system','2025-07-24 17:39:11',NULL,'0');
/*!40000 ALTER TABLE `tms_training_plan_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_training_record`
--

DROP TABLE IF EXISTS `tms_training_record`;
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

--
-- Dumping data for table `tms_training_record`
--

LOCK TABLES `tms_training_record` WRITE;
/*!40000 ALTER TABLE `tms_training_record` DISABLE KEYS */;
INSERT INTO `tms_training_record` VALUES (1,4,4,6,4,'2025-07-02','2',2.00,'0',2.00,1.00,2.00,2.00,NULL,NULL,NULL,'2','2025-07-24 18:23:28','2025-07-24 18:23:28','system','system',0,NULL);
/*!40000 ALTER TABLE `tms_training_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_training_type`
--

DROP TABLE IF EXISTS `tms_training_type`;
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

--
-- Dumping data for table `tms_training_type`
--

LOCK TABLES `tms_training_type` WRITE;
/*!40000 ALTER TABLE `tms_training_type` DISABLE KEYS */;
INSERT INTO `tms_training_type` VALUES (1,'理论培训','THEORY','1','课堂理论知识学习，包括法规、原理、程序等','0',1,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'理论培训课程'),(2,'实践培训','PRACTICE','2','实际操作训练，包括模拟器和真机训练','0',2,'2025-07-23 08:54:03','2025-07-23 08:54:03','admin','admin',0,'实践培训课程');
/*!40000 ALTER TABLE `tms_training_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trace_log`
--

DROP TABLE IF EXISTS `trace_log`;
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

--
-- Dumping data for table `trace_log`
--

LOCK TABLES `trace_log` WRITE;
/*!40000 ALTER TABLE `trace_log` DISABLE KEYS */;
INSERT INTO `trace_log` VALUES (1,'sys_dept','INSERT','100',NULL,'{\"leader\": \"张主任\", \"dept_id\": 100, \"dept_name\": \"飞安直升机培训中心\"}','dept_name,leader',1,'admin','2025-07-23 08:54:03','127.0.0.1','初始化培训中心架构'),(2,'sys_user','INSERT','2',NULL,'{\"dept_id\": 201, \"user_id\": 2, \"nick_name\": \"张教员\", \"user_name\": \"instructor001\"}','user_name,nick_name,dept_id',1,'admin','2025-07-23 08:54:03','127.0.0.1','添加飞行教员'),(3,'sys_dict_type','INSERT','100',NULL,'{\"dict_id\": 100, \"dict_name\": \"机型类别\", \"dict_type\": \"tms_aircraft_type\"}','dict_name,dict_type',1,'admin','2025-07-23 08:54:03','127.0.0.1','初始化培训相关字典'),(5,'tms_courseware','INSERT','1-5',NULL,'{\"count\": 5, \"courses\": [\"AS350基础理论\", \"AS350飞行训练\", \"R44基础理论\", \"R44飞行训练\", \"地勤基础理论\"]}','initial_data',1,'admin','2025-07-23 08:54:03','127.0.0.1','初始化课件数据'),(6,'tms_student','INSERT','1-3',NULL,'{\"count\": 3, \"students\": [\"张三\", \"李四\", \"王五\"]}','initial_data',1,'admin','2025-07-23 08:54:03','127.0.0.1','初始化学员数据'),(7,'tms_instructor','INSERT','1-3',NULL,'{\"count\": 3, \"instructors\": [\"张教员\", \"李教员\", \"王教员\"]}','initial_data',1,'admin','2025-07-23 08:54:03','127.0.0.1','初始化教员数据');
/*!40000 ALTER TABLE `trace_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-24 18:45:29
