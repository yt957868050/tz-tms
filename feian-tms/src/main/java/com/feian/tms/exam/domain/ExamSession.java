package com.feian.tms.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 考试场次
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_session")
public class ExamSession extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long sessionId;

    private String name;
    private Long paperId;
    private Long machineTypeId;
    private Date windowStart;
    private Date windowEnd;
    private Integer suggestTimeMin;
    private String passRuleType;
    private Integer passScore;
    private Integer passQuestionNum;
    private Integer shuffleQuestion;
    private Integer shuffleOption;
    private Integer antiCheatCheat;
    private Integer antiCheatWatch;
    private Integer antiCheatCapture;
    private String password;
    private Integer rangeType;
    private Integer credentialTemplateId;
    /** 发布状态：1草稿 2已发布 3已撤回 */
    private Integer publishStatus;
    /** 发布时间 */
    private Date publishTime;

    /** 原机型名称（迁移对账） */
    private String rawMachineType;

    /** 原始 build_config（旧系统结构） */
    private String rawBuildConfig;
}
