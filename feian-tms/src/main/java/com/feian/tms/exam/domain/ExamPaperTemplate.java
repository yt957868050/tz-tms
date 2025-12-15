package com.feian.tms.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 试卷模板
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_paper_template")
public class ExamPaperTemplate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long paperId;

    private String name;
    private Long machineTypeId;
    private Integer totalScore;
    private Integer passScore;
    private Integer passQuestionNum;
    private Integer shuffleQuestion;
    private Integer shuffleOption;
    private Integer antiCheatCheat;
    private Integer antiCheatWatch;
    private Integer antiCheatCapture;

    /** 原机型名称（迁移对账） */
    private String rawMachineType;

    /** 原始 build_config（旧系统结构） */
    private String rawBuildConfig;

    /** 原始试卷 JSON（旧系统结构） */
    private String rawPaperJson;
}
