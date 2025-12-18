package com.feian.tms.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * 补考设置：基于一次已产生的答卷（通常为未通过）为对应考生开放补考窗口
 */
@Data
public class ExamCandidateMakeupRequest {

    /** 原答卷ID（作为补考来源） */
    @NotNull(message = "来源答卷ID不能为空")
    private Long fromAnswerSheetId;

    /** 补考开始时间（可选，覆盖场次窗口） */
    private Date windowStartOverride;

    /** 补考结束时间（可选，覆盖场次窗口） */
    private Date windowEndOverride;
}

