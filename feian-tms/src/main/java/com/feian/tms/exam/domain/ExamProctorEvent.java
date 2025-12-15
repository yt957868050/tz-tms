package com.feian.tms.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 监考事件
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_proctor_event")
public class ExamProctorEvent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long sheetId;
    private String eventType;
    private String payload;
}
