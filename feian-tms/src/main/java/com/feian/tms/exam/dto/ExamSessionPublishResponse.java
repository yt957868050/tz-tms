package com.feian.tms.exam.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 发布/下发结果
 */
@Data
public class ExamSessionPublishResponse {
    private Long sessionId;
    private Integer publishStatus;
    private Date publishTime;

    private Integer classCount;
    private Integer totalStudents;
    private Integer matchedUsers;

    private Integer insertedCandidates;
    private Integer skippedExistingCandidates;

    private Integer missingUserCount;
    private List<Long> missingStudentIds;
}

