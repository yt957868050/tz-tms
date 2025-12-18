package com.feian.tms.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 开始考试（生成答卷）请求
 */
@Data
public class ExamAnswerStartRequest {
    /** 场次ID */
    @NotNull(message = "考试场次不能为空")
    private Long sessionId;

    /** 用户ID（考生）；学员端可不传，默认取当前登录用户 */
    private Long userId;

    /** 试卷ID（可空，优先取场次绑定） */
    private Long paperId;

    /** 场次密码（如设置了 password 则必填） */
    private String password;
}
