package com.feian.tms.exam.service.impl;

import com.feian.tms.exam.domain.ExamSession;
import com.feian.tms.exam.mapper.ExamSessionMapper;
import com.feian.tms.exam.service.ExamSessionService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ExamSessionServiceImpl extends MPJBaseServiceImpl<ExamSessionMapper, ExamSession> implements ExamSessionService {
}
