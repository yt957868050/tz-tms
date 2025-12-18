package com.feian.tms.exam.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 发布/下发考试：按班级生成考生名单
 */
@Data
public class ExamSessionPublishRequest {

    @NotNull(message = "场次ID不能为空")
    private Long sessionId;

    @NotEmpty(message = "班级ID列表不能为空")
    private List<Long> classIds;
}

