package com.feian.tms.service.impl;

import com.feian.tms.domain.Major;
import com.feian.tms.mapper.MajorMapper;
import com.feian.tms.service.MajorService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 专业管理Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class MajorServiceImpl extends MPJBaseServiceImpl<MajorMapper, Major> implements MajorService {

}