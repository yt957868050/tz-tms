-- 培训计划管理模块数据库设计
-- 支持培训计划的创建、编辑和自动排课功能

-- ============================================
-- 1. 培训计划主表
-- ============================================

CREATE TABLE IF NOT EXISTS tms_training_plan (
    plan_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '计划ID',
    plan_code VARCHAR(50) NOT NULL COMMENT '计划编号',
    plan_name VARCHAR(200) NOT NULL COMMENT '计划名称',
    machine_type_id BIGINT NOT NULL COMMENT '机型ID',
    major_id BIGINT NOT NULL COMMENT '专业ID',
    training_ability_id BIGINT NOT NULL COMMENT '培训能力ID（培训/复训/恢复考试）',
    start_date DATE NOT NULL COMMENT '开始时间',
    end_date DATE COMMENT '结束时间',
    total_hours DECIMAL(5,1) DEFAULT 0 COMMENT '总培训时长',
    theory_hours DECIMAL(5,1) DEFAULT 0 COMMENT '理论时长',
    practice_hours DECIMAL(5,1) DEFAULT 0 COMMENT '实践时长',
    plan_status VARCHAR(10) DEFAULT '0' COMMENT '计划状态（0草稿 1执行中 2已完成 3已取消）',
    schedule_generated CHAR(1) DEFAULT '0' COMMENT '是否已生成课表（0否 1是）',
    class_schedule_name VARCHAR(200) COMMENT '班次课程表名称',
    teaching_schedule_name VARCHAR(200) COMMENT '教学进度安排表名称',
    practical_project_list_name VARCHAR(200) COMMENT '机型实作培训项目清单名称',
    
    status CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',
    
    PRIMARY KEY (plan_id),
    UNIQUE KEY uk_plan_code (plan_code),
    KEY idx_machine_major_ability (machine_type_id, major_id, training_ability_id),
    KEY idx_start_date (start_date),
    KEY idx_plan_status (plan_status)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='培训计划表';

-- ============================================
-- 2. 培训计划教员关联表
-- ============================================

CREATE TABLE IF NOT EXISTS tms_training_plan_instructor (
    relation_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    plan_id BIGINT NOT NULL COMMENT '计划ID',
    instructor_id BIGINT NOT NULL COMMENT '教员ID',
    is_chief CHAR(1) DEFAULT '0' COMMENT '是否为主责教员（0否 1是）',
    responsibility VARCHAR(200) COMMENT '责任描述',
    
    status CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',
    
    PRIMARY KEY (relation_id),
    UNIQUE KEY uk_plan_instructor (plan_id, instructor_id),
    KEY idx_plan_id (plan_id),
    KEY idx_instructor_id (instructor_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='培训计划教员关联表';

-- ============================================
-- 3. 培训计划课程安排表
-- ============================================

CREATE TABLE IF NOT EXISTS tms_training_plan_schedule (
    schedule_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '课程安排ID',
    plan_id BIGINT NOT NULL COMMENT '计划ID',
    schedule_type VARCHAR(20) NOT NULL COMMENT '安排类型（class_schedule=班次课程表, teaching_schedule=教学进度安排表, practical_project=实作项目清单）',
    day_number INT NOT NULL COMMENT '天数（第几天）',
    time_slot VARCHAR(20) COMMENT '时间段（如09:00-12:00）',
    courseware_id BIGINT COMMENT '课件ID',
    course_name VARCHAR(200) NOT NULL COMMENT '课程名称',
    course_type VARCHAR(10) COMMENT '课程类型（1理论 2实践）',
    course_hours DECIMAL(3,1) NOT NULL COMMENT '课程时长',
    instructor_id BIGINT COMMENT '授课教员ID',
    classroom VARCHAR(100) COMMENT '教室/实训场地',
    course_content TEXT COMMENT '课程内容',
    
    status CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',
    
    PRIMARY KEY (schedule_id),
    KEY idx_plan_schedule_type (plan_id, schedule_type),
    KEY idx_plan_day (plan_id, day_number),
    KEY idx_courseware_id (courseware_id),
    KEY idx_instructor_id (instructor_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='培训计划课程安排表';

-- ============================================
-- 4. 培训能力字典表（如果不存在）
-- ============================================

CREATE TABLE IF NOT EXISTS tms_training_ability (
    training_ability_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '培训能力ID',
    ability_code VARCHAR(50) NOT NULL COMMENT '能力编码',
    ability_name VARCHAR(100) NOT NULL COMMENT '能力名称',
    ability_desc VARCHAR(500) COMMENT '能力描述',
    daily_hours DECIMAL(3,1) DEFAULT 7.0 COMMENT '每日培训时长',
    total_days INT DEFAULT 30 COMMENT '建议培训天数',
    
    status CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',
    
    PRIMARY KEY (training_ability_id),
    UNIQUE KEY uk_ability_code (ability_code),
    KEY idx_status (status)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='培训能力表';

-- ============================================
-- 5. 初始化培训能力数据
-- ============================================

INSERT INTO tms_training_ability (training_ability_code, tms_training_ability.training_ability_name, training_ability_desc, daily_hours, total_days, status, create_by, create_time) VALUES
('TRAINING', '培训', '完整的培训计划，包含理论和实践的全面培训', 7.0, 45, '0', 'admin', NOW()),
('RETRAIN', '复训', '复习和强化已有技能的培训', 6.0, 20, '0', 'admin', NOW()),
('RECOVERY_EXAM', '恢复考试', '恢复资质的考试培训', 4.0, 10, '0', 'admin', NOW());

-- ============================================
-- 6. 创建培训计划测试数据
-- ============================================

-- AS350空勤培训计划
INSERT INTO tms_training_plan (
    plan_code, plan_name, machine_type_id, major_id, training_ability_id,
    start_date, total_hours, plan_status, create_by, create_time
) VALUES
('PLAN-AS350-AIR-001', 'AS350空勤人员培训计划', 1, 1, 1, '2025-02-01', 315.0, '0', 'admin', NOW()),
('PLAN-AS350-GROUND-001', 'AS350地勤人员培训计划', 1, 2, 1, '2025-02-15', 280.0, '0', 'admin', NOW()),
('PLAN-R44-AIR-001', 'R44空勤人员复训计划', 2, 1, 2, '2025-03-01', 120.0, '0', 'admin', NOW());

-- 为培训计划分配教员
INSERT INTO tms_training_plan_instructor (plan_id, instructor_id, is_chief, responsibility, create_by, create_time) VALUES
-- AS350空勤培训计划教员
(1, 1, '1', '主责教员，负责整体培训进度', 'admin', NOW()),
(1, 3, '0', '理论课程教学', 'admin', NOW()),
-- AS350地勤培训计划教员
(2, 2, '1', '主责教员，负责地勤培训', 'admin', NOW()),
(2, 4, '0', '实作指导教员', 'admin', NOW()),
-- R44复训计划教员
(3, 1, '1', '复训主责教员', 'admin', NOW());

-- ============================================
-- 7. 验证数据创建结果
-- ============================================

-- 查看培训计划概览
SELECT 
    tp.plan_id,
    tp.plan_name,
    tp.plan_code,
    mt.machine_type_name,
    ma.major_name,
    ta.ability_name,
    tp.start_date,
    tp.total_hours,
    tp.plan_status,
    COUNT(tpi.instructor_id) as instructor_count
FROM tms_training_plan tp
LEFT JOIN tms_machine_type mt ON tp.machine_type_id = mt.machine_type_id
LEFT JOIN tms_major ma ON tp.major_id = ma.major_id
LEFT JOIN tms_training_ability ta ON tp.training_ability_id = ta.training_ability_id
LEFT JOIN tms_training_plan_instructor tpi ON tp.plan_id = tpi.plan_id AND tpi.status = '0'
WHERE tp.status = '0'
GROUP BY tp.plan_id
ORDER BY tp.create_time DESC;

-- 查看培训计划教员分配
SELECT 
    tp.plan_name,
    i.instructor_name,
    tpi.is_chief,
    CASE WHEN tpi.is_chief = '1' THEN '主责教员' ELSE '协助教员' END as role_name,
    tpi.responsibility
FROM tms_training_plan tp
JOIN tms_training_plan_instructor tpi ON tp.plan_id = tpi.plan_id
JOIN tms_instructor i ON tpi.instructor_id = i.instructor_id
WHERE tp.status = '0' AND tpi.status = '0'
ORDER BY tp.plan_id, tpi.is_chief DESC;

-- 查看培训能力配置
SELECT 
    ability_code,
    ability_name,
    ability_desc,
    daily_hours,
    total_days,
    CONCAT(daily_hours * total_days, '小时') as total_hours_estimate
FROM tms_training_ability
WHERE status = '0'
ORDER BY training_ability_id;

SELECT '=== 培训计划管理数据库创建完成 ===' as message;