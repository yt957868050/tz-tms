package com.feian.tms.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * 开放补考窗口（只针对已存在的考生记录，不支持补人）
 */
@Data
public class ExamCandidateMakeupOpenRequest {

    @NotNull(message = "考生ID不能为空")
    private Long candidateId;

    /** 补考开始时间（可选，默认=当前时间） */
    private Date windowStartOverride;

    /** 补考结束时间（必填） */
    @NotNull(message = "补考结束时间不能为空")
    private Date windowEndOverride;
}

