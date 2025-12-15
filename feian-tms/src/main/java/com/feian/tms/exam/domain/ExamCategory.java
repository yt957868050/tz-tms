package com.feian.tms.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考试分类/章节
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_category")
public class ExamCategory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long categoryId;

    /** 分类/章节名称 */
    private String name;

    /** 父级ID */
    private Long parentId;

    /** 路径（便于层级展示） */
    private String path;

    /** class_hour: 章节默认抽题数/权重 */
    private Integer classHour;

    /** 机型（TMS机型ID） */
    private Long machineTypeId;

    /** TMS 知识点/章节 ID（对齐教学大纲） */
    private Long tmsOutlineId;

    /** 原机型名称（迁移对账） */
    private String rawMachineType;

    /** 原始层级信息（兼容旧系统） */
    private String rawLevel;

    /** 原始ID（兼容旧系统） */
    private Long rawId;

    /** 状态 */
    private Integer status;
}
