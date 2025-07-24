-- TMS培训大纲管理数据库表结构
-- 执行前请确保数据库连接正常

-- 创建培训大纲表
DROP TABLE IF EXISTS `tms_training_outline`;
CREATE TABLE `tms_training_outline` (
    `outline_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '大纲ID',
    `machine_type_id` BIGINT(20) NOT NULL COMMENT '机型ID',
    `major_id` BIGINT(20) NOT NULL COMMENT '专业ID',
    `training_ability_id` BIGINT(20) NOT NULL COMMENT '培训能力ID',
    `outline_name` VARCHAR(100) NOT NULL COMMENT '大纲名称',
    `outline_code` VARCHAR(50) NULL COMMENT '大纲编码',
    `regulation_file_id` BIGINT(20) NULL COMMENT '培训规范文件ID',
    `regulation_file_name` VARCHAR(200) NULL COMMENT '培训规范文件名',
    `regulation_file_path` VARCHAR(500) NULL COMMENT '培训规范文件路径',
    `outline_file_id` BIGINT(20) NULL COMMENT '大纲文件ID',
    `outline_file_name` VARCHAR(200) NULL COMMENT '大纲文件名',
    `outline_file_path` VARCHAR(500) NULL COMMENT '大纲文件路径',
    `theory_hours` DECIMAL(5,1) NOT NULL DEFAULT 0.0 COMMENT '理论培训课时',
    `practical_hours` DECIMAL(5,1) NOT NULL DEFAULT 0.0 COMMENT '实作培训课时',
    `total_hours` DECIMAL(5,1) GENERATED ALWAYS AS (`theory_hours` + `practical_hours`) STORED COMMENT '总课时',
    `description` TEXT NULL COMMENT '大纲描述',
    `effective_date` DATE NULL COMMENT '生效日期',
    `expiry_date` DATE NULL COMMENT '失效日期',
    `version` VARCHAR(20) NULL COMMENT '版本号',
    `status` CHAR(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` VARCHAR(64) NULL DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark` VARCHAR(500) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`outline_id`),
    UNIQUE KEY `uk_machine_major_ability` (`machine_type_id`, `major_id`, `training_ability_id`, `status`),
    KEY `idx_machine_type` (`machine_type_id`),
    KEY `idx_major` (`major_id`),
    KEY `idx_training_ability` (`training_ability_id`),
    KEY `idx_status` (`status`),
    KEY `idx_effective_date` (`effective_date`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='培训大纲表';

-- 创建大纲章节表（可选，用于详细的章节管理）
DROP TABLE IF EXISTS `tms_outline_chapter`;
CREATE TABLE `tms_outline_chapter` (
    `chapter_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '章节ID',
    `outline_id` BIGINT(20) NOT NULL COMMENT '大纲ID',
    `chapter_code` VARCHAR(50) NOT NULL COMMENT '章节编码（如ATA章节）',
    `chapter_name` VARCHAR(200) NOT NULL COMMENT '章节名称',
    `parent_id` BIGINT(20) NULL DEFAULT 0 COMMENT '父章节ID',
    `chapter_level` INT(2) NOT NULL DEFAULT 1 COMMENT '章节层级',
    `sort_order` INT(5) NOT NULL DEFAULT 0 COMMENT '排序',
    `theory_hours` DECIMAL(5,1) NULL DEFAULT 0.0 COMMENT '理论课时',
    `practical_hours` DECIMAL(5,1) NULL DEFAULT 0.0 COMMENT '实作课时',
    `is_required` CHAR(1) NOT NULL DEFAULT '1' COMMENT '是否必修（0选修 1必修）',
    `description` TEXT NULL COMMENT '章节描述',
    `learning_objectives` TEXT NULL COMMENT '学习目标',
    `status` CHAR(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` VARCHAR(64) NULL DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark` VARCHAR(500) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`chapter_id`),
    KEY `idx_outline_id` (`outline_id`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_chapter_code` (`chapter_code`),
    KEY `idx_sort_order` (`sort_order`),
    CONSTRAINT `fk_outline_chapter_outline` FOREIGN KEY (`outline_id`) REFERENCES `tms_training_outline` (`outline_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='大纲章节表';

-- 插入示例数据
INSERT INTO `tms_training_outline` (`outline_id`, `machine_type_id`, `major_id`, `training_ability_id`, `outline_name`, `outline_code`, `theory_hours`, `practical_hours`, `description`, `effective_date`, `version`, `status`, `create_by`, `remark`)
VALUES 
(1, 1, 1, 1, 'H135空勤培训大纲', 'H135-PILOT-TRAIN-V1.0', 120.0, 80.0, 'H135机型空勤人员培训大纲，包含理论和实践培训内容', '2024-01-01', 'V1.0', '0', 'admin', '初始版本'),
(2, 1, 2, 1, 'H135地勤培训大纲', 'H135-MAINT-TRAIN-V1.0', 80.0, 120.0, 'H135机型地勤人员培训大纲，重点是维护和检修', '2024-01-01', 'V1.0', '0', 'admin', '初始版本'),
(3, 2, 1, 1, 'H145空勤培训大纲', 'H145-PILOT-TRAIN-V1.0', 140.0, 100.0, 'H145机型空勤人员培训大纲，包含理论和实践培训内容', '2024-01-01', 'V1.0', '0', 'admin', '初始版本');

-- 插入章节示例数据
INSERT INTO `tms_outline_chapter` (`chapter_id`, `outline_id`, `chapter_code`, `chapter_name`, `parent_id`, `chapter_level`, `sort_order`, `theory_hours`, `practical_hours`, `is_required`, `description`, `learning_objectives`, `status`, `create_by`)
VALUES 
-- H135空勤培训大纲章节
(1, 1, 'ATA-01', '通用知识', 0, 1, 1, 20.0, 0.0, '1', '航空器通用知识和基础理论', '掌握航空器基本原理和通用知识', '0', 'admin'),
(2, 1, 'ATA-01-01', '航空法规', 1, 2, 1, 8.0, 0.0, '1', '民航法规和规章制度', '熟悉相关法规要求', '0', 'admin'),
(3, 1, 'ATA-01-02', '空气动力学', 1, 2, 2, 12.0, 0.0, '1', '空气动力学基础知识', '理解飞行原理', '0', 'admin'),
(4, 1, 'ATA-27', '飞行操纵系统', 0, 1, 2, 25.0, 20.0, '1', '飞行操纵系统的结构和操作', '掌握飞行操纵系统操作技能', '0', 'admin'),
(5, 1, 'ATA-29', '液压系统', 0, 1, 3, 15.0, 10.0, '1', '液压系统的原理和维护', '理解液压系统工作原理', '0', 'admin'),
(6, 1, 'ATA-71', '动力装置', 0, 1, 4, 30.0, 25.0, '1', '发动机系统的知识和操作', '掌握发动机操作和应急程序', '0', 'admin'),
(7, 1, 'ATA-91', '应急程序', 0, 1, 5, 20.0, 15.0, '1', '各种应急情况的处理程序', '熟练掌握应急程序', '0', 'admin'),
(8, 1, 'ATA-95', '飞行训练', 0, 1, 6, 10.0, 10.0, '1', '实际飞行训练和考核', '达到飞行技能要求', '0', 'admin');

COMMIT;