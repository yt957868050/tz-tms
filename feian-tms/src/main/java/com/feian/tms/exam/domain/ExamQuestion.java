package com.feian.tms.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考试题库
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_question")
public class ExamQuestion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 题目ID（沿用旧ID） */
    @TableId(type = IdType.INPUT)
    private Long questionId;

    /** 机型ID（TMS机型） */
    private Long machineTypeId;

    /** 分类/章节ID */
    private Long categoryId;

    /** 题型 */
    private Integer questionType;

    /** 分值 */
    private Integer score;

    /** 难度 */
    private Integer difficult;

    /** 状态 */
    private Integer status;

    /** 来源标识 raw/migrated */
    private String source;

    /** 原题内容ID（便于对照） */
    private String questionFrameId;

    /** 原机型名称（迁移对账用） */
    private String rawMachineType;

    /** 原始题目JSON快照 */
    private String rawJson;
}
