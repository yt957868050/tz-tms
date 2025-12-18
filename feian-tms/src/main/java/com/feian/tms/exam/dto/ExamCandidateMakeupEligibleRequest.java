package com.feian.tms.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 查询可补考考生列表：用于从某场次中筛选 未通过/缺考/未交卷 的人员。
 */
@Data
public class ExamCandidateMakeupEligibleRequest {

    @NotNull(message = "场次ID不能为空")
    private Long sessionId;

    /** 可选：仅筛选某班级 */
    private Long classId;
}

