-- 课件管理数据库结构更新SQL脚本
-- 支持理论课件和实践课件分类管理

-- ============================================
-- 1. 更新tms_courseware表结构
-- ============================================

-- 添加培训类型字段
ALTER TABLE tms_courseware 
ADD COLUMN training_type VARCHAR(10) DEFAULT '1' COMMENT '培训类型（1理论 2实践）' 
AFTER training_type_id;

-- 更新现有数据，根据实际情况设置培训类型
-- 这里假设现有课件都是理论课件，可以根据实际情况调整
UPDATE tms_courseware SET training_type = '1' WHERE training_type IS NULL;

-- ============================================
-- 2. 更新tms_courseware_file表结构
-- ============================================

-- 更新文件类型枚举，添加VMT文件支持
ALTER TABLE tms_courseware_file 
MODIFY COLUMN file_type VARCHAR(10) COMMENT '文件类型（1教材 2教案 3PPT 4CBT 5工卡 6其他 7VMT）';

-- ============================================
-- 3. 创建课件测试数据
-- ============================================

-- 理论课件测试数据
INSERT INTO tms_courseware (
    course_code, course_name, ata_chapter, machine_type_id, major_id, training_type_id, training_type,
    theory_hours, practice_hours, course_desc, status, order_num, create_by, create_time
) VALUES
-- AS350理论课件
('THEORY-AS350-001', 'AS350直升机系统概述', 'ATA-05', 1, 1, 1, '1', 2.0, 0.0, 'AS350直升机系统基础理论课程', '0', 1, 'admin', NOW()),
('THEORY-AS350-002', 'AS350发动机原理', 'ATA-71', 1, 1, 1, '1', 3.0, 0.0, 'AS350发动机工作原理与维护', '0', 2, 'admin', NOW()),
('THEORY-AS350-003', 'AS350飞行控制系统', 'ATA-27', 1, 1, 1, '1', 2.5, 0.0, 'AS350飞行控制系统理论知识', '0', 3, 'admin', NOW()),

-- R44理论课件
('THEORY-R44-001', 'R44直升机基础知识', 'ATA-05', 2, 1, 1, '1', 2.0, 0.0, 'R44直升机基础理论课程', '0', 1, 'admin', NOW()),
('THEORY-R44-002', 'R44发动机系统', 'ATA-71', 2, 1, 1, '1', 3.0, 0.0, 'R44发动机系统理论', '0', 2, 'admin', NOW());

-- 实践课件测试数据
INSERT INTO tms_courseware (
    course_code, course_name, ata_chapter, machine_type_id, major_id, training_type_id, training_type,
    theory_hours, practice_hours, course_desc, status, order_num, create_by, create_time
) VALUES
-- AS350实践课件
('PRACTICAL-AS350-001', 'AS350日常检查实作', 'ATA-05', 1, 2, 2, '2', 0.0, 4.0, 'AS350直升机日常检查实际操作', '0', 1, 'admin', NOW()),
('PRACTICAL-AS350-002', 'AS350发动机维护实作', 'ATA-71', 1, 2, 2, '2', 0.0, 6.0, 'AS350发动机维护实际操作', '0', 2, 'admin', NOW()),
('PRACTICAL-AS350-003', 'AS350航电系统检修', 'ATA-31', 1, 2, 2, '2', 0.0, 4.0, 'AS350航电系统检修实作', '0', 3, 'admin', NOW()),

-- R44实践课件
('PRACTICAL-R44-001', 'R44系统检查实作', 'ATA-05', 2, 2, 2, '2', 0.0, 3.0, 'R44系统检查实际操作', '0', 1, 'admin', NOW()),
('PRACTICAL-R44-002', 'R44发动机拆装', 'ATA-71', 2, 2, 2, '2', 0.0, 8.0, 'R44发动机拆装实作训练', '0', 2, 'admin', NOW());

-- ============================================
-- 4. 创建课件文件测试数据
-- ============================================

-- 获取课件ID用于关联文件
SET @theory_as350_001 = (SELECT courseware_id FROM tms_courseware WHERE course_code = 'THEORY-AS350-001');
SET @theory_as350_002 = (SELECT courseware_id FROM tms_courseware WHERE course_code = 'THEORY-AS350-002');
SET @practical_as350_001 = (SELECT courseware_id FROM tms_courseware WHERE course_code = 'PRACTICAL-AS350-001');
SET @practical_as350_002 = (SELECT courseware_id FROM tms_courseware WHERE course_code = 'PRACTICAL-AS350-002');

-- 理论课件文件
INSERT INTO tms_courseware_file (
    courseware_id, file_name, file_type, file_path, file_size, file_extension, 
    file_desc, download_count, status, order_num, create_by, create_time
) VALUES
-- AS350系统概述课件文件
(@theory_as350_001, 'AS350系统概述教材.pdf', '1', '/upload/courseware/AS350系统概述教材.pdf', 2048000, '.pdf', 'AS350系统概述理论教材', 0, '0', 1, 'admin', NOW()),
(@theory_as350_001, 'AS350系统概述.ppt', '3', '/upload/courseware/AS350系统概述.ppt', 5120000, '.ppt', 'AS350系统概述PPT课件', 0, '0', 2, 'admin', NOW()),
(@theory_as350_001, 'AS350基础CBT.exe', '4', '/upload/courseware/AS350基础CBT.exe', 50240000, '.exe', 'AS350基础知识CBT课件', 0, '0', 3, 'admin', NOW()),

-- AS350发动机原理课件文件
(@theory_as350_002, 'AS350发动机教材.pdf', '1', '/upload/courseware/AS350发动机教材.pdf', 3072000, '.pdf', 'AS350发动机原理教材', 0, '0', 1, 'admin', NOW()),
(@theory_as350_002, 'AS350发动机原理.ppt', '3', '/upload/courseware/AS350发动机原理.ppt', 8192000, '.ppt', 'AS350发动机原理PPT', 0, '0', 2, 'admin', NOW());

-- 实践课件文件
INSERT INTO tms_courseware_file (
    courseware_id, file_name, file_type, file_path, file_size, file_extension, 
    file_desc, download_count, status, order_num, create_by, create_time
) VALUES
-- AS350日常检查实作文件
(@practical_as350_001, 'AS350日常检查工卡01.pdf', '5', '/upload/courseware/AS350日常检查工卡01.pdf', 1024000, '.pdf', 'AS350日常检查工卡1', 0, '0', 1, 'admin', NOW()),
(@practical_as350_001, 'AS350日常检查工卡02.pdf', '5', '/upload/courseware/AS350日常检查工卡02.pdf', 1024000, '.pdf', 'AS350日常检查工卡2', 0, '0', 2, 'admin', NOW()),
(@practical_as350_001, 'AS350日常检查教案.pdf', '2', '/upload/courseware/AS350日常检查教案.pdf', 2048000, '.pdf', 'AS350日常检查实作教案', 0, '0', 3, 'admin', NOW()),

-- AS350发动机维护实作文件
(@practical_as350_002, 'AS350发动机维护工卡.pdf', '5', '/upload/courseware/AS350发动机维护工卡.pdf', 1536000, '.pdf', 'AS350发动机维护工卡', 0, '0', 1, 'admin', NOW()),
(@practical_as350_002, 'AS350发动机维护教案.pdf', '2', '/upload/courseware/AS350发动机维护教案.pdf', 2048000, '.pdf', 'AS350发动机维护教案', 0, '0', 2, 'admin', NOW()),
(@practical_as350_002, 'AS350发动机维护VMT.vmt', '7', '/upload/courseware/AS350发动机维护VMT.vmt', 10240000, '.vmt', 'AS350发动机维护VMT培训文件', 0, '0', 3, 'admin', NOW());

-- ============================================
-- 5. 验证数据创建结果
-- ============================================

-- 查看理论课件
SELECT 
    c.courseware_id,
    c.course_name,
    c.ata_chapter,
    m.machine_type_name,
    ma.major_name,
    c.training_type,
    c.theory_hours,
    COUNT(cf.courseware_file_id) as file_count
FROM tms_courseware c
LEFT JOIN tms_machine_type m ON c.machine_type_id = m.machine_type_id
LEFT JOIN tms_major ma ON c.major_id = ma.major_id
LEFT JOIN tms_courseware_file cf ON c.courseware_id = cf.courseware_id AND cf.status = '0'
WHERE c.training_type = '1' AND c.status = '0'
GROUP BY c.courseware_id
ORDER BY c.machine_type_id, c.order_num;

-- 查看实践课件
SELECT 
    c.courseware_id,
    c.course_name,
    c.ata_chapter,
    m.machine_type_name,
    ma.major_name,
    c.training_type,
    c.practice_hours,
    COUNT(cf.courseware_file_id) as file_count
FROM tms_courseware c
LEFT JOIN tms_machine_type m ON c.machine_type_id = m.machine_type_id
LEFT JOIN tms_major ma ON c.major_id = ma.major_id
LEFT JOIN tms_courseware_file cf ON c.courseware_id = cf.courseware_id AND cf.status = '0'
WHERE c.training_type = '2' AND c.status = '0'
GROUP BY c.courseware_id
ORDER BY c.machine_type_id, c.order_num;

-- 查看课件文件分布
SELECT 
    cf.file_type,
    CASE 
        WHEN cf.file_type = '1' THEN '教材'
        WHEN cf.file_type = '2' THEN '教案'
        WHEN cf.file_type = '3' THEN 'PPT'
        WHEN cf.file_type = '4' THEN 'CBT'
        WHEN cf.file_type = '5' THEN '工卡'
        WHEN cf.file_type = '6' THEN '其他'
        WHEN cf.file_type = '7' THEN 'VMT'
        ELSE '未知'
    END as file_type_name,
    COUNT(*) as file_count
FROM tms_courseware_file cf
WHERE cf.status = '0'
GROUP BY cf.file_type
ORDER BY cf.file_type;

SELECT '=== 课件管理数据库更新完成 ===' as message;