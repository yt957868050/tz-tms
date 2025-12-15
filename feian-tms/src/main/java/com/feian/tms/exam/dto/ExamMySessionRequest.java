package com.feian.tms.exam.dto;

import lombok.Data;

/**
 * 我的考试场次查询
 */
@Data
public class ExamMySessionRequest {

    /**
     * 机型ID（管理员可选；普通用户忽略，始终使用当前登录用户已选择的机型）
     */
    private Long machineTypeId;
}

