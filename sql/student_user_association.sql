-- 学员用户关联及机型权限完整SQL脚本
-- 执行前请确保数据库连接正常，建议先备份现有数据

-- 1. 为sys_user表添加学员关联字段（如果字段不存在）
-- 直接添加，如果列已存在会报错
ALTER TABLE sys_user
ADD COLUMN current_machine_type_name VARCHAR(100) COMMENT '当前选择的机型名称';
-- 2. 为tms_student表添加用户关联字段（如果字段不存在）
ALTER TABLE tms_student ADD COLUMN  user_id BIGINT COMMENT '关联的用户ID';

-- 3. 创建学员机型关联表
DROP TABLE IF EXISTS tms_student_machine_type;
CREATE TABLE tms_student_machine_type (
  relation_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  student_id BIGINT NOT NULL COMMENT '学员ID',
  machine_type_id BIGINT NOT NULL COMMENT '机型ID',
  is_primary CHAR(1) DEFAULT '0' COMMENT '是否为主要机型（0否 1是）',
  status CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATETIME COMMENT '创建时间',
  update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATETIME COMMENT '更新时间',
  remark VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (relation_id),
  UNIQUE KEY uk_student_machine (student_id, machine_type_id),
  KEY idx_student_id (student_id),
  KEY idx_machine_type_id (machine_type_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='学员机型关联表';

-- 4. 创建测试用机型数据
INSERT IGNORE INTO tms_machine_type (machine_type_name, machine_type_code, machine_type_desc, status, order_num, create_by, create_time) VALUES
('AS350 松鼠直升机', 'AS350', 'AS350是一款单发涡轴多用途直升机，广泛用于培训和商业运营', '0', 1, 'admin', NOW()),
('R44 雷鸟直升机', 'R44', 'R44是一款四座活塞式直升机，是全球最受欢迎的培训直升机之一', '0', 2, 'admin', NOW()),
('EC135 欧洲之星', 'EC135', 'EC135是一款双发轻型多用途直升机，具有出色的安全性能', '0', 3, 'admin', NOW()),
('Bell 206 喷射骑兵', 'BELL206', 'Bell 206是一款经典的单发涡轴直升机，在全球拥有大量用户', '0', 4, 'admin', NOW()),
('R22 贝塔直升机', 'R22', 'R22是一款双座轻型直升机，适合初级飞行培训', '0', 5, 'admin', NOW());

-- 5. 创建测试学员数据
INSERT IGNORE INTO tms_student (
    student_code, student_name, gender, birth_date, id_card, phone_number, email, 
    department, position, hire_date, primary_machine_type_id, primary_major_id, 
    education, training_status, status, create_by, create_time, remark
) VALUES
('ST001', '张三学员', '0', '1995-03-15', '110101199503151234', '13800001001', 'zhangsan@feian.com', 
 '培训部', '学员', '2024-01-15', 1, 1, '3', '1', '0', 'admin', NOW(), 'AS350机型培训学员'),
('ST002', '李四学员', '0', '1996-07-20', '110101199607201234', '13800001002', 'lisi@feian.com', 
 '培训部', '学员', '2024-02-01', 2, 1, '3', '1', '0', 'admin', NOW(), 'R44机型培训学员'),
('ST003', '王五学员', '1', '1994-11-08', '110101199411081234', '13800001003', 'wangwu@feian.com', 
 '培训部', '学员', '2024-03-10', 1, 1, '4', '0', '0', 'admin', NOW(), 'AS350机型培训学员'),
('ST004', '赵六学员', '0', '1997-05-25', '110101199705251234', '13800001004', 'zhaoliu@feian.com', 
 '培训部', '学员', '2024-04-01', 3, 1, '3', '1', '0', 'admin', NOW(), 'EC135机型培训学员'),
('ST005', '孙七学员', '1', '1995-09-12', '110101199509121234', '13800001005', 'sunqi@feian.com', 
 '培训部', '学员', '2024-05-15', 2, 1, '3', '2', '0', 'admin', NOW(), 'R44机型培训学员');

-- 6. 创建学员对应的系统用户账号
INSERT IGNORE INTO sys_user (user_name, nick_name, email, phonenumber, sex, dept_id, password, status, del_flag, create_by, create_time, student_id, remark) VALUES
('student001', '张三学员', 'zhangsan@feian.com', '13800001001', '0', 204, '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', 'admin', NOW(), 1, '学员账号-AS350培训'),
('student002', '李四学员', 'lisi@feian.com', '13800001002', '0', 204, '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', 'admin', NOW(), 2, '学员账号-R44培训'),
('student003', '王五学员', 'wangwu@feian.com', '13800001003', '1', 204, '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', 'admin', NOW(), 3, '学员账号-AS350培训'),
('student004', '赵六学员', 'zhaoliu@feian.com', '13800001004', '0', 204, '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', 'admin', NOW(), 4, '学员账号-EC135培训'),
('student005', '孙七学员', 'sunqi@feian.com', '13800001005', '1', 204, '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', 'admin', NOW(), 5, '学员账号-R44培训');

-- 7. 更新学员表中的用户关联ID
UPDATE tms_student s SET s.user_id = (
    SELECT u.user_id FROM sys_user u WHERE u.student_id = s.student_id LIMIT 1
) WHERE s.student_id <= 5;

-- 8. 为学员用户分配学员角色
INSERT IGNORE INTO sys_user_role (user_id, role_id) 
SELECT u.user_id, 5 FROM sys_user u WHERE u.student_id IS NOT NULL AND u.student_id <= 5;

-- 9. 创建学员机型关联关系（每个学员可以关联多个机型）
INSERT IGNORE INTO tms_student_machine_type (student_id, machine_type_id, is_primary, status, create_by, create_time, remark) VALUES
-- 张三学员：AS350(主) + R44
(1, 1, '1', '0', 'admin', NOW(), '主要机型AS350'),
(1, 2, '0', '0', 'admin', NOW(), '副机型R44'),
-- 李四学员：R44(主) + R22
(2, 2, '1', '0', 'admin', NOW(), '主要机型R44'),
(2, 5, '0', '0', 'admin', NOW(), '副机型R22'),
-- 王五学员：AS350(主) + EC135
(3, 1, '1', '0', 'admin', NOW(), '主要机型AS350'),
(3, 3, '0', '0', 'admin', NOW(), '副机型EC135'),
-- 赵六学员：EC135(主) + AS350
(4, 3, '1', '0', 'admin', NOW(), '主要机型EC135'),
(4, 1, '0', '0', 'admin', NOW(), '副机型AS350'),
-- 孙七学员：R44(主) + Bell206
(5, 2, '1', '0', 'admin', NOW(), '主要机型R44'),
(5, 4, '0', '0', 'admin', NOW(), '副机型Bell206');

-- 10. 创建一些培训班次测试数据
INSERT IGNORE INTO tms_training_class (
    class_name, class_code, machine_type_id, major_id, training_ability_id,
    planned_start_date, planned_end_date, student_count, status, 
    create_by, create_time, remark
) VALUES
('AS350初始培训班第一期', 'AS350-2024-01', 1, 1, 1, '2024-06-01', '2024-08-30', 15, '1', 'admin', NOW(), 'AS350机型初始培训'),
('R44复训班第二期', 'R44-2024-02', 2, 1, 2, '2024-07-01', '2024-07-15', 8, '1', 'admin', NOW(), 'R44机型复训'),
('EC135差异培训班', 'EC135-2024-03', 3, 1, 3, '2024-08-01', '2024-08-15', 6, '0', 'admin', NOW(), 'EC135机型差异培训');

-- 11. 创建班次学员关联
INSERT IGNORE INTO tms_class_student (class_id, student_id, enrollment_date, status, create_by, create_time) VALUES
(1, 1, '2024-05-15', '0', 'admin', NOW()),  -- 张三参加AS350培训
(1, 3, '2024-05-16', '0', 'admin', NOW()),  -- 王五参加AS350培训
(2, 2, '2024-06-20', '0', 'admin', NOW()),  -- 李四参加R44复训
(2, 5, '2024-06-21', '0', 'admin', NOW()),  -- 孙七参加R44复训
(3, 4, '2024-07-25', '0', 'admin', NOW());  -- 赵六参加EC135差异培训

-- 12. 创建一些课件测试数据
INSERT IGNORE INTO tms_courseware (
    course_name, course_code, ata_chapter, version, machine_type_id, major_id, training_type_id,
    theory_hours, practice_hours, course_description, learning_objectives, status,
    create_by, create_time
) VALUES
('AS350系统概述', 'AS350-SYS-001', 'ATA-05', 'V1.0', 1, 1, 1, 8.0, 0.0, 'AS350直升机各系统介绍', '掌握AS350各系统工作原理', '0', 'admin', NOW()),
('R44飞行操作程序', 'R44-OPS-001', 'ATA-06', 'V1.0', 2, 1, 2, 4.0, 12.0, 'R44标准操作程序', '熟练掌握R44操作程序', '0', 'admin', NOW()),
('EC135应急程序', 'EC135-EMG-001', 'ATA-07', 'V1.0', 3, 1, 2, 6.0, 8.0, 'EC135应急程序训练', '熟练处理各种应急情况', '0', 'admin', NOW());

-- 13. 更新现有sys_user表的密码（所有测试账号密码都是 admin123）
-- $2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2 对应密码：admin123

COMMIT;

-- 验证数据创建结果
SELECT '=== 学员信息 ===' as info;
SELECT s.student_id, s.student_name, s.student_code, u.user_name, u.user_id 
FROM tms_student s 
LEFT JOIN sys_user u ON s.user_id = u.user_id 
ORDER BY s.student_id;

SELECT '=== 学员机型关联 ===' as info;
SELECT s.student_name, mt.machine_type_name, smt.is_primary, smt.status
FROM tms_student_machine_type smt
JOIN tms_student s ON smt.student_id = s.student_id
JOIN tms_machine_type mt ON smt.machine_type_id = mt.machine_type_id
ORDER BY s.student_id, smt.is_primary DESC;

SELECT '=== 用户角色分配 ===' as info;
SELECT u.user_name, u.nick_name, r.role_name, u.student_id
FROM sys_user u
JOIN sys_user_role ur ON u.user_id = ur.user_id
JOIN sys_role r ON ur.role_id = r.role_id
WHERE u.student_id IS NOT NULL
ORDER BY u.user_id;