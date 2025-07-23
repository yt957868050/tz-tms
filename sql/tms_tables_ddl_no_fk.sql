-- ----------------------------
-- 飞安直升机培训管理系统 - TMS业务表结构DDL (无外键约束版本)
-- 移除外键约束以提升性能和操作灵活性，保留索引优化查询
-- 创建日期: 2025-01-23
-- 包含BaseEntity通用字段和业务专用字段
-- ----------------------------

-- 设置存储引擎和字符集
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ================================
-- 1. 基础配置表
-- ================================

-- ----------------------------
-- 机型管理表
-- ----------------------------
DROP TABLE IF EXISTS tms_machine_type;
CREATE TABLE tms_machine_type (
  machine_type_id       BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '机型ID',
  machine_type_name     VARCHAR(100)    NOT NULL                   COMMENT '机型名称',
  machine_type_code     VARCHAR(50)     NOT NULL                   COMMENT '机型代码',
  machine_type_image    VARCHAR(500)                               COMMENT '机型图片路径',
  machine_type_desc     TEXT                                       COMMENT '机型描述',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0正常 1停用）',
  order_num             INT                                        COMMENT '排序',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (machine_type_id),
  UNIQUE KEY uk_machine_type_code (machine_type_code),
  KEY idx_machine_type_status (status),
  KEY idx_machine_type_order (order_num)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '机型管理表';

-- ----------------------------
-- 专业管理表
-- ----------------------------
DROP TABLE IF EXISTS tms_major;
CREATE TABLE tms_major (
  major_id              BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '专业ID',
  major_name            VARCHAR(100)    NOT NULL                   COMMENT '专业名称',
  major_code            VARCHAR(50)     NOT NULL                   COMMENT '专业代码',
  major_type            CHAR(1)         NOT NULL                   COMMENT '专业类型（1空勤 2地勤）',
  major_desc            TEXT                                       COMMENT '专业描述',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0正常 1停用）',
  order_num             INT                                        COMMENT '排序',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (major_id),
  UNIQUE KEY uk_major_code (major_code),
  KEY idx_major_type (major_type),
  KEY idx_major_status (status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '专业管理表';

-- ----------------------------
-- 培训类型表
-- ----------------------------
DROP TABLE IF EXISTS tms_training_type;
CREATE TABLE tms_training_type (
  training_type_id      BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '培训类型ID',
  training_type_name    VARCHAR(100)    NOT NULL                   COMMENT '培训类型名称',
  training_type_code    VARCHAR(50)     NOT NULL                   COMMENT '培训类型代码',
  training_method       CHAR(1)         NOT NULL                   COMMENT '培训方式（1理论 2实践）',
  training_type_desc    TEXT                                       COMMENT '培训类型描述',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0正常 1停用）',
  order_num             INT                                        COMMENT '排序',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (training_type_id),
  UNIQUE KEY uk_training_type_code (training_type_code),
  KEY idx_training_method (training_method),
  KEY idx_training_type_status (status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '培训类型表';

-- ----------------------------
-- 培训能力表
-- ----------------------------
DROP TABLE IF EXISTS tms_training_ability;
CREATE TABLE tms_training_ability (
  training_ability_id   BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '培训能力ID',
  training_ability_name VARCHAR(100)    NOT NULL                   COMMENT '培训能力名称',
  training_ability_code VARCHAR(50)     NOT NULL                   COMMENT '培训能力代码',
  ability_type          CHAR(1)         NOT NULL                   COMMENT '培训能力类型（1初始培训 2复训 3差异培训 4恢复培训）',
  training_ability_desc TEXT                                       COMMENT '培训能力描述',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0正常 1停用）',
  order_num             INT                                        COMMENT '排序',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (training_ability_id),
  UNIQUE KEY uk_training_ability_code (training_ability_code),
  KEY idx_ability_type (ability_type),
  KEY idx_training_ability_status (status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '培训能力表';

-- ================================
-- 2. 课件管理表
-- ================================

-- ----------------------------
-- 课件管理表
-- ----------------------------
DROP TABLE IF EXISTS tms_courseware;
CREATE TABLE tms_courseware (
  courseware_id         BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '课件ID',
  course_code           VARCHAR(50)     NOT NULL                   COMMENT '课程编码',
  course_name           VARCHAR(200)    NOT NULL                   COMMENT '课程名称',
  ata_chapter           VARCHAR(100)                               COMMENT 'ATA章节',
  machine_type_id       BIGINT(20)      NOT NULL                   COMMENT '机型ID',
  major_id              BIGINT(20)      NOT NULL                   COMMENT '专业ID',
  training_type_id      BIGINT(20)      NOT NULL                   COMMENT '培训类型ID',
  theory_hours          DECIMAL(10,2)                              COMMENT '理论培训时长(小时)',
  practice_hours        DECIMAL(10,2)                              COMMENT '实践培训时长(小时)',
  course_desc           TEXT                                       COMMENT '课程描述',
  course_objective      TEXT                                       COMMENT '课程目标',
  course_requirement    TEXT                                       COMMENT '课程要求',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0正常 1停用）',
  order_num             INT                                        COMMENT '排序',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (courseware_id),
  UNIQUE KEY uk_course_code (course_code),
  KEY idx_machine_type_id (machine_type_id),
  KEY idx_major_id (major_id),
  KEY idx_training_type_id (training_type_id),
  KEY idx_courseware_status (status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '课件管理表';

-- ----------------------------
-- 课件文件表
-- ----------------------------
DROP TABLE IF EXISTS tms_courseware_file;
CREATE TABLE tms_courseware_file (
  courseware_file_id    BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '课件文件ID',
  courseware_id         BIGINT(20)      NOT NULL                   COMMENT '课件ID',
  file_name             VARCHAR(255)    NOT NULL                   COMMENT '文件名称',
  file_type             CHAR(1)         NOT NULL                   COMMENT '文件类型（1教材 2教案 3PPT 4CBT 5工卡 6其他）',
  file_path             VARCHAR(500)    NOT NULL                   COMMENT '文件路径',
  file_size             BIGINT                                     COMMENT '文件大小(KB)',
  file_extension        VARCHAR(20)                                COMMENT '文件扩展名',
  file_desc             TEXT                                       COMMENT '文件描述',
  download_count        INT             DEFAULT 0                  COMMENT '下载次数',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0正常 1停用）',
  order_num             INT                                        COMMENT '排序',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (courseware_file_id),
  KEY idx_courseware_id (courseware_id),
  KEY idx_file_type (file_type),
  KEY idx_courseware_file_status (status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '课件文件表';

-- ================================
-- 3. 人员管理表
-- ================================

-- ----------------------------
-- 学员信息表
-- ----------------------------
DROP TABLE IF EXISTS tms_student;
CREATE TABLE tms_student (
  student_id            BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '学员ID',
  student_code          VARCHAR(50)     NOT NULL                   COMMENT '学员工号',
  student_name          VARCHAR(100)    NOT NULL                   COMMENT '学员姓名',
  gender                CHAR(1)                                    COMMENT '性别（0男 1女 2未知）',
  birth_date            DATE                                       COMMENT '出生日期',
  id_card               VARCHAR(18)                                COMMENT '身份证号',
  phone_number          VARCHAR(11)                                COMMENT '手机号码',
  email                 VARCHAR(100)                               COMMENT '邮箱',
  department            VARCHAR(100)                               COMMENT '部门',
  position              VARCHAR(100)                               COMMENT '职位',
  hire_date             DATE                                       COMMENT '入职时间',
  primary_machine_type_id BIGINT(20)                               COMMENT '主要机型ID',
  primary_major_id      BIGINT(20)                                 COMMENT '主要专业ID',
  education             CHAR(1)                                    COMMENT '学历（1中专 2大专 3本科 4硕士 5博士）',
  training_status       CHAR(1)                                    COMMENT '培训状态（0待培训 1培训中 2已培训 3培训暂停）',
  avatar                VARCHAR(500)                               COMMENT '头像路径',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0正常 1停用）',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (student_id),
  UNIQUE KEY uk_student_code (student_code),
  UNIQUE KEY uk_student_id_card (id_card),
  KEY idx_primary_machine_type_id (primary_machine_type_id),
  KEY idx_primary_major_id (primary_major_id),
  KEY idx_student_status (status),
  KEY idx_training_status (training_status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '学员信息表';

-- ----------------------------
-- 教员信息表
-- ----------------------------
DROP TABLE IF EXISTS tms_instructor;
CREATE TABLE tms_instructor (
  instructor_id         BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '教员ID',
  instructor_code       VARCHAR(50)     NOT NULL                   COMMENT '教员工号',
  instructor_name       VARCHAR(100)    NOT NULL                   COMMENT '教员姓名',
  gender                CHAR(1)                                    COMMENT '性别（0男 1女 2未知）',
  birth_date            DATE                                       COMMENT '出生日期',
  id_card               VARCHAR(18)                                COMMENT '身份证号',
  phone_number          VARCHAR(11)                                COMMENT '手机号码',
  email                 VARCHAR(100)                               COMMENT '邮箱',
  department            VARCHAR(100)                               COMMENT '部门',
  position              VARCHAR(100)                               COMMENT '职位',
  hire_date             DATE                                       COMMENT '入职时间',
  instructor_level      CHAR(1)                                    COMMENT '教员等级（1初级 2中级 3高级 4专家）',
  teaching_start_date   DATE                                       COMMENT '从教时间',
  education             CHAR(1)                                    COMMENT '学历（1中专 2大专 3本科 4硕士 5博士）',
  technical_title       VARCHAR(100)                               COMMENT '专业技术职务',
  teaching_status       CHAR(1)                                    COMMENT '授课状态（0可授课 1授课中 2暂停授课）',
  avatar                VARCHAR(500)                               COMMENT '头像路径',
  biography             TEXT                                       COMMENT '个人简介',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0正常 1停用）',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (instructor_id),
  UNIQUE KEY uk_instructor_code (instructor_code),
  UNIQUE KEY uk_instructor_id_card (id_card),
  KEY idx_instructor_level (instructor_level),
  KEY idx_teaching_status (teaching_status),
  KEY idx_instructor_status (status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '教员信息表';

-- ================================
-- 4. 能力管理表
-- ================================

-- ----------------------------
-- 教员授课能力表
-- ----------------------------
DROP TABLE IF EXISTS tms_instructor_ability;
CREATE TABLE tms_instructor_ability (
  instructor_ability_id BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '教员授课能力ID',
  instructor_id         BIGINT(20)      NOT NULL                   COMMENT '教员ID',
  machine_type_id       BIGINT(20)      NOT NULL                   COMMENT '机型ID',
  major_id              BIGINT(20)      NOT NULL                   COMMENT '专业ID',
  training_type_id      BIGINT(20)      NOT NULL                   COMMENT '培训类型ID',
  courseware_id         BIGINT(20)      NOT NULL                   COMMENT '课件ID',
  ability_level         CHAR(1)         NOT NULL                   COMMENT '授课资质等级（1初级 2中级 3高级 4专家）',
  qualification_date    DATE                                       COMMENT '资质获得时间',
  valid_until           DATE                                       COMMENT '资质有效期至',
  teaching_count        INT             DEFAULT 0                  COMMENT '授课次数',
  teaching_score        FLOAT                                      COMMENT '授课评分',
  is_primary            CHAR(1)                                    COMMENT '是否主讲（0否 1是）',
  ability_remark        TEXT                                       COMMENT '能力备注',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0正常 1停用）',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (instructor_ability_id),
  KEY idx_instructor_id (instructor_id),
  KEY idx_machine_type_id (machine_type_id),
  KEY idx_major_id (major_id),
  KEY idx_training_type_id (training_type_id),
  KEY idx_courseware_id (courseware_id),
  KEY idx_ability_level (ability_level),
  KEY idx_instructor_ability_status (status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '教员授课能力表';

-- ----------------------------
-- 学员学习能力表
-- ----------------------------
DROP TABLE IF EXISTS tms_student_ability;
CREATE TABLE tms_student_ability (
  student_ability_id    BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '学员学习能力ID',
  student_id            BIGINT(20)      NOT NULL                   COMMENT '学员ID',
  machine_type_id       BIGINT(20)      NOT NULL                   COMMENT '机型ID',
  major_id              BIGINT(20)      NOT NULL                   COMMENT '专业ID',
  training_ability_id   BIGINT(20)      NOT NULL                   COMMENT '培训能力ID',
  courseware_id         BIGINT(20)      NOT NULL                   COMMENT '课件ID',
  learning_progress     CHAR(1)         NOT NULL                   COMMENT '学习进度（0未开始 1学习中 2已完成）',
  theory_score          DECIMAL(5,2)                               COMMENT '理论成绩',
  practice_score        DECIMAL(5,2)                               COMMENT '实践成绩',
  total_score           DECIMAL(5,2)                               COMMENT '综合成绩',
  learning_start_time   DATE                                       COMMENT '学习开始时间',
  learning_end_time     DATE                                       COMMENT '学习完成时间',
  qualification_status  CHAR(1)         NOT NULL                   COMMENT '资质状态（0未获得 1已获得 2已过期）',
  qualification_date    DATE                                       COMMENT '资质获得时间',
  valid_until           DATE                                       COMMENT '资质有效期至',
  learning_remark       TEXT                                       COMMENT '学习备注',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0正常 1停用）',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (student_ability_id),
  KEY idx_student_id (student_id),
  KEY idx_machine_type_id (machine_type_id),
  KEY idx_major_id (major_id),
  KEY idx_training_ability_id (training_ability_id),
  KEY idx_courseware_id (courseware_id),
  KEY idx_learning_progress (learning_progress),
  KEY idx_qualification_status (qualification_status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '学员学习能力表';

-- ================================
-- 5. 培训计划表
-- ================================

-- ----------------------------
-- 培训计划表
-- ----------------------------
DROP TABLE IF EXISTS tms_training_plan;
CREATE TABLE tms_training_plan (
  training_plan_id      BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '培训计划ID',
  training_plan_name    VARCHAR(200)    NOT NULL                   COMMENT '培训计划名称',
  training_plan_code    VARCHAR(50)     NOT NULL                   COMMENT '培训计划编号',
  machine_type_id       BIGINT(20)      NOT NULL                   COMMENT '机型ID',
  major_id              BIGINT(20)      NOT NULL                   COMMENT '专业ID',
  training_ability_id   BIGINT(20)      NOT NULL                   COMMENT '培训能力ID',
  plan_start_time       DATE                                       COMMENT '计划开始时间',
  plan_end_time         DATE                                       COMMENT '计划结束时间',
  total_theory_hours    DECIMAL(10,2)                              COMMENT '总理论学时',
  total_practice_hours  DECIMAL(10,2)                              COMMENT '总实践学时',
  plan_student_count    INT                                        COMMENT '计划人数',
  training_plan_desc    TEXT                                       COMMENT '培训计划描述',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0草稿 1待审核 2已审核 3执行中 4已完成 5已取消）',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (training_plan_id),
  UNIQUE KEY uk_training_plan_code (training_plan_code),
  KEY idx_machine_type_id (machine_type_id),
  KEY idx_major_id (major_id),
  KEY idx_training_ability_id (training_ability_id),
  KEY idx_training_plan_status (status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '培训计划表';

-- ----------------------------
-- 培训计划明细表
-- ----------------------------
DROP TABLE IF EXISTS tms_training_plan_detail;
CREATE TABLE tms_training_plan_detail (
  training_plan_detail_id BIGINT(20)    NOT NULL AUTO_INCREMENT    COMMENT '培训计划明细ID',
  training_plan_id      BIGINT(20)      NOT NULL                   COMMENT '培训计划ID',
  courseware_id         BIGINT(20)      NOT NULL                   COMMENT '课件ID',
  course_order          INT             NOT NULL                   COMMENT '课程顺序',
  theory_hours          DECIMAL(10,2)                              COMMENT '理论学时',
  practice_hours        DECIMAL(10,2)                              COMMENT '实践学时',
  is_required           CHAR(1)         NOT NULL                   COMMENT '是否必修（0选修 1必修）',
  course_requirement    TEXT                                       COMMENT '课程要求',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (training_plan_detail_id),
  KEY idx_training_plan_id (training_plan_id),
  KEY idx_courseware_id (courseware_id),
  KEY idx_course_order (course_order)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '培训计划明细表';

-- ================================
-- 6. 培训班次表
-- ================================

-- ----------------------------
-- 培训班次表
-- ----------------------------
DROP TABLE IF EXISTS tms_training_class;
CREATE TABLE tms_training_class (
  training_class_id     BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '培训班次ID',
  class_code            VARCHAR(50)     NOT NULL                   COMMENT '班次编号',
  class_name            VARCHAR(200)    NOT NULL                   COMMENT '班次名称',
  training_plan_id      BIGINT(20)      NOT NULL                   COMMENT '培训计划ID',
  machine_type_id       BIGINT(20)      NOT NULL                   COMMENT '机型ID',
  major_id              BIGINT(20)      NOT NULL                   COMMENT '专业ID',
  training_ability_id   BIGINT(20)      NOT NULL                   COMMENT '培训能力ID',
  plan_start_time       DATE                                       COMMENT '计划开始时间',
  plan_end_time         DATE                                       COMMENT '计划结束时间',
  actual_start_time     DATE                                       COMMENT '实际开始时间',
  actual_end_time       DATE                                       COMMENT '实际结束时间',
  plan_student_count    INT                                        COMMENT '计划人数',
  actual_student_count  INT                                        COMMENT '实际人数',
  primary_instructor_id BIGINT(20)                                 COMMENT '主要教员ID',
  class_desc            TEXT                                       COMMENT '班次描述',
  status                CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '状态（0待开班 1培训中 2已结班 3已取消）',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (training_class_id),
  UNIQUE KEY uk_class_code (class_code),
  KEY idx_training_plan_id (training_plan_id),
  KEY idx_machine_type_id (machine_type_id),
  KEY idx_major_id (major_id),
  KEY idx_training_ability_id (training_ability_id),
  KEY idx_primary_instructor_id (primary_instructor_id),
  KEY idx_training_class_status (status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '培训班次表';

-- ----------------------------
-- 班次学员关联表
-- ----------------------------
DROP TABLE IF EXISTS tms_class_student;
CREATE TABLE tms_class_student (
  class_student_id      BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '班次学员关联ID',
  training_class_id     BIGINT(20)      NOT NULL                   COMMENT '培训班次ID',
  student_id            BIGINT(20)      NOT NULL                   COMMENT '学员ID',
  enroll_time           DATETIME                                   COMMENT '报名时间',
  student_status        CHAR(1)         NOT NULL                   COMMENT '学员状态（0待开班 1培训中 2已结业 3已退班 4请假中）',
  withdraw_reason       TEXT                                       COMMENT '退班原因',
  withdraw_time         DATETIME                                   COMMENT '退班时间',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (class_student_id),
  UNIQUE KEY uk_class_student (training_class_id, student_id),
  KEY idx_training_class_id (training_class_id),
  KEY idx_student_id (student_id),
  KEY idx_student_status (student_status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '班次学员关联表';

-- ================================
-- 7. 培训记录表
-- ================================

-- ----------------------------
-- 培训记录表
-- ----------------------------
DROP TABLE IF EXISTS tms_training_record;
CREATE TABLE tms_training_record (
  training_record_id    BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '培训记录ID',
  student_id            BIGINT(20)      NOT NULL                   COMMENT '学员ID',
  training_class_id     BIGINT(20)      NOT NULL                   COMMENT '培训班次ID',
  courseware_id         BIGINT(20)      NOT NULL                   COMMENT '课件ID',
  instructor_id         BIGINT(20)      NOT NULL                   COMMENT '教员ID',
  training_date         DATE            NOT NULL                   COMMENT '培训日期',
  training_method       CHAR(1)         NOT NULL                   COMMENT '培训类型（1理论 2实践）',
  training_hours        DECIMAL(5,2)                               COMMENT '培训时长(小时)',
  attendance_status     CHAR(1)         NOT NULL                   COMMENT '出勤状态（1正常 2迟到 3早退 4请假 5缺勤）',
  performance_score     DECIMAL(5,2)                               COMMENT '课堂表现评分',
  homework_score        DECIMAL(5,2)                               COMMENT '作业成绩',
  exam_score            DECIMAL(5,2)                               COMMENT '考试成绩',
  total_score           DECIMAL(5,2)                               COMMENT '综合评分',
  training_content      TEXT                                       COMMENT '培训内容',
  learning_notes        TEXT                                       COMMENT '学习心得',
  instructor_comment    TEXT                                       COMMENT '教员评价',
  training_effect       CHAR(1)                                    COMMENT '培训效果（1优秀 2良好 3一般 4较差）',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (training_record_id),
  KEY idx_student_id (student_id),
  KEY idx_training_class_id (training_class_id),
  KEY idx_courseware_id (courseware_id),
  KEY idx_instructor_id (instructor_id),
  KEY idx_training_date (training_date),
  KEY idx_training_method (training_method),
  KEY idx_attendance_status (attendance_status)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '培训记录表';

-- ================================
-- 8. 证书管理表
-- ================================

-- ----------------------------
-- 证书管理表
-- ----------------------------
DROP TABLE IF EXISTS tms_certificate;
CREATE TABLE tms_certificate (
  certificate_id        BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '证书ID',
  certificate_code      VARCHAR(100)    NOT NULL                   COMMENT '证书编号',
  certificate_name      VARCHAR(200)    NOT NULL                   COMMENT '证书名称',
  student_id            BIGINT(20)      NOT NULL                   COMMENT '学员ID',
  training_class_id     BIGINT(20)      NOT NULL                   COMMENT '培训班次ID',
  machine_type_id       BIGINT(20)      NOT NULL                   COMMENT '机型ID',
  major_id              BIGINT(20)      NOT NULL                   COMMENT '专业ID',
  training_ability_id   BIGINT(20)      NOT NULL                   COMMENT '培训能力ID',
  certificate_type      CHAR(1)         NOT NULL                   COMMENT '证书类型（1培训证书 2复训证书 3恢复考试证书）',
  issue_date            DATE                                       COMMENT '颁发日期',
  valid_from            DATE                                       COMMENT '有效期开始',
  valid_until           DATE                                       COMMENT '有效期结束',
  issue_organization    VARCHAR(200)                               COMMENT '颁发机构',
  issuer                VARCHAR(100)                               COMMENT '签发人',
  template_path         VARCHAR(500)                               COMMENT '证书模板路径',
  certificate_file_path VARCHAR(500)                               COMMENT '证书文件路径',
  certificate_status    CHAR(1)         NOT NULL DEFAULT '0'       COMMENT '证书状态（0草稿 1已颁发 2已作废 3已过期）',
  void_reason           TEXT                                       COMMENT '作废原因',
  void_time             DATETIME                                   COMMENT '作废时间',
  -- BaseEntity 公共字段
  create_time           DATETIME        NOT NULL                   COMMENT '创建时间',
  update_time           DATETIME        NOT NULL                   COMMENT '更新时间',
  create_by             VARCHAR(64)                                COMMENT '创建人',
  update_by             VARCHAR(64)                                COMMENT '更新人',
  is_deleted            TINYINT         NOT NULL DEFAULT 0         COMMENT '逻辑删除标志（0未删除 1已删除）',
  remark                VARCHAR(500)                               COMMENT '备注信息',
  PRIMARY KEY (certificate_id),
  UNIQUE KEY uk_certificate_code (certificate_code),
  KEY idx_student_id (student_id),
  KEY idx_training_class_id (training_class_id),
  KEY idx_machine_type_id (machine_type_id),
  KEY idx_major_id (major_id),
  KEY idx_training_ability_id (training_ability_id),
  KEY idx_certificate_type (certificate_type),
  KEY idx_certificate_status (certificate_status),
  KEY idx_issue_date (issue_date),
  KEY idx_valid_until (valid_until)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '证书管理表';

-- ================================
-- 创建复合索引优化查询性能
-- ================================

-- 复合索引优化常用查询
CREATE INDEX idx_courseware_machine_major_type ON tms_courseware (machine_type_id, major_id, training_type_id);
CREATE INDEX idx_student_machine_major ON tms_student (primary_machine_type_id, primary_major_id);
CREATE INDEX idx_instructor_ability_full ON tms_instructor_ability (instructor_id, machine_type_id, major_id, training_type_id);
CREATE INDEX idx_student_ability_full ON tms_student_ability (student_id, machine_type_id, major_id, training_ability_id);
CREATE INDEX idx_training_plan_machine_major_ability ON tms_training_plan (machine_type_id, major_id, training_ability_id);
CREATE INDEX idx_training_class_plan_time ON tms_training_class (training_plan_id, plan_start_time, plan_end_time);
CREATE INDEX idx_training_record_student_date ON tms_training_record (student_id, training_date);
CREATE INDEX idx_certificate_student_type_status ON tms_certificate (student_id, certificate_type, certificate_status);

-- 恢复外键检查
SET FOREIGN_KEY_CHECKS = 1;

-- ================================
-- 显示创建结果
-- ================================
SELECT '=== TMS业务表结构创建完成（无外键约束版本）===' AS message;
SELECT CONCAT('基础配置表: ', 4) AS basic_tables;
SELECT CONCAT('课件管理表: ', 2) AS courseware_tables;
SELECT CONCAT('人员管理表: ', 2) AS personnel_tables;
SELECT CONCAT('能力管理表: ', 2) AS ability_tables;
SELECT CONCAT('培训计划表: ', 2) AS plan_tables;
SELECT CONCAT('培训班次表: ', 2) AS class_tables;
SELECT CONCAT('培训记录表: ', 1) AS record_tables;
SELECT CONCAT('证书管理表: ', 1) AS certificate_tables;
SELECT CONCAT('总计TMS表: ', 16) AS total_tables;
SELECT '✅ 移除外键约束，提升性能和操作灵活性' AS optimization;
SELECT '✅ 保留索引优化查询性能' AS performance;
SELECT '✅ 支持灵活的表删除和重建' AS flexibility;