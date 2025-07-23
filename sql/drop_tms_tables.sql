-- ----------------------------
-- 飞安直升机培训管理系统 - TMS业务表删除脚本
-- 用于清理TMS业务表，无外键约束，可以随意删除
-- 创建日期: 2025-01-23
-- ----------------------------

-- 设置外键检查为关闭（虽然没有外键，但为了安全起见）
SET FOREIGN_KEY_CHECKS = 0;

-- 按照依赖关系倒序删除表
-- 先删除依赖其他表的表，再删除基础表

-- 8. 证书管理表
DROP TABLE IF EXISTS tms_certificate;

-- 7. 培训记录表
DROP TABLE IF EXISTS tms_training_record;

-- 6. 培训班次表
DROP TABLE IF EXISTS tms_class_student;
DROP TABLE IF EXISTS tms_training_class;

-- 5. 培训计划表
DROP TABLE IF EXISTS tms_training_plan_detail;
DROP TABLE IF EXISTS tms_training_plan;

-- 4. 能力管理表
DROP TABLE IF EXISTS tms_student_ability;
DROP TABLE IF EXISTS tms_instructor_ability;

-- 3. 人员管理表
DROP TABLE IF EXISTS tms_instructor;
DROP TABLE IF EXISTS tms_student;

-- 2. 课件管理表
DROP TABLE IF EXISTS tms_courseware_file;
DROP TABLE IF EXISTS tms_courseware;

-- 1. 基础配置表
DROP TABLE IF EXISTS tms_training_ability;
DROP TABLE IF EXISTS tms_training_type;
DROP TABLE IF EXISTS tms_major;
DROP TABLE IF EXISTS tms_machine_type;

-- 恢复外键检查
SET FOREIGN_KEY_CHECKS = 1;

-- 显示删除结果
SELECT '=== TMS业务表删除完成 ===' AS message;
SELECT '✅ 所有16张TMS业务表已删除' AS result;
SELECT '✅ 无外键约束，删除顺利完成' AS status;
SELECT '现在可以重新创建TMS表结构' AS next_step;