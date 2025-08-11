package com.feian.tms.service.impl;

import com.feian.tms.domain.Instructor;
import com.feian.tms.mapper.InstructorMapper;
import com.feian.tms.service.InstructorService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教员管理Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class InstructorServiceImpl extends MPJBaseServiceImpl<InstructorMapper, Instructor> implements InstructorService {
    @Autowired
    public InstructorMapper instructorMapper;
    @Override
    public boolean deleteBatch(List<Long> idList) {
        instructorMapper.deleteBatch(idList);
        return true;
    }

    /**
     * 搜索所有教员
     * @return
     */
    public List<Instructor> instructorList() {
        return instructorMapper.instructorList();
    }
}