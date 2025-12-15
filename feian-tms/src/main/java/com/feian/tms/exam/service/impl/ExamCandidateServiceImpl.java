package com.feian.tms.exam.service.impl;

import com.feian.tms.exam.domain.ExamCandidate;
import com.feian.tms.exam.mapper.ExamCandidateMapper;
import com.feian.tms.exam.service.ExamCandidateService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ExamCandidateServiceImpl extends MPJBaseServiceImpl<ExamCandidateMapper, ExamCandidate> implements ExamCandidateService {
}
