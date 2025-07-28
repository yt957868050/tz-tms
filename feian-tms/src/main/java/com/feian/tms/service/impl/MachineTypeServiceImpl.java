package com.feian.tms.service.impl;

import com.feian.tms.domain.MachineType;
import com.feian.tms.mapper.MachineTypeMapper;
import com.feian.tms.service.MachineTypeService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机型管理Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class MachineTypeServiceImpl extends MPJBaseServiceImpl<MachineTypeMapper, MachineType> implements MachineTypeService {
   @Autowired
    public MachineTypeMapper machineTypeMapper;

    @Override
    public boolean deleteBatch(List<Long> idList) {
        machineTypeMapper.deleteIds(idList);
        return true;
    }
}