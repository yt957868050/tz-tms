package com.feian.tms.service.impl;

import com.feian.tms.domain.TrainingType;
import com.feian.tms.mapper.TrainingTypeMapper;
import com.feian.tms.service.TrainingTypeService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 培训类型Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class TrainingTypeServiceImpl extends MPJBaseServiceImpl<TrainingTypeMapper, TrainingType> implements TrainingTypeService {

}