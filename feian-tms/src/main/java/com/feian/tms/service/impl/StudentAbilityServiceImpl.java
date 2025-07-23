package com.feian.tms.service.impl;

import com.feian.tms.domain.StudentAbility;
import com.feian.tms.mapper.StudentAbilityMapper;
import com.feian.tms.service.StudentAbilityService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 学员学习能力Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class StudentAbilityServiceImpl extends MPJBaseServiceImpl<StudentAbilityMapper, StudentAbility> implements StudentAbilityService {

}