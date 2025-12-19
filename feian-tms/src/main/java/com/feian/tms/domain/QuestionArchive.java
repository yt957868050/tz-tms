package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 题目分类归档对象 tms_question_archive
 * * 对应SQL表: tms_question_archive
 * * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_question_archive")
public class QuestionArchive extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 题目分类名称 */
    @Excel(name = "题目分类名称")
    private String name;

    /** 机型ID */
    @Excel(name = "机型ID")
    private Long machineTypeId;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long majorId;

    /** 培训学时 */
    @Excel(name = "培训学时")
    private Integer classHour;

    /** 上级节点分类ID */
    @Excel(name = "上级节点分类")
    private Long parentId;

    /** 分类层级/路径 */
    @Excel(name = "分类层级")
    private String level;

    /** 子节点列表 (非数据库字段，用于树结构展示) */
    @TableField(exist = false)
    private List<QuestionArchive> children;

}
