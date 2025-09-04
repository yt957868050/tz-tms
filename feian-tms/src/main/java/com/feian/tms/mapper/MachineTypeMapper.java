package com.feian.tms.mapper;

import com.feian.tms.domain.MachineType;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 机型管理Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface MachineTypeMapper extends MPJBaseMapper<MachineType> {

    void deleteIds(List<Long> idList);

    @Select("select machine_type_name from tms_machine_type where machine_type_id=#{machineTypeId} and is_deleted=0")
    String getMachineTypeName(Long machineTypeId);
}