package com.feian.tms.mapper;

import com.feian.tms.domain.TrainingOutline;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 培训大纲Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface TrainingOutlineMapper extends MPJBaseMapper<TrainingOutline> {

    /**
     * 查询培训大纲列表（包含关联信息）
     * 
     * @param outline 培训大纲查询条件
     * @return 培训大纲列表
     */
//    List<TrainingOutline> selectTrainingOutlineListWithRelations(@Param("outline") TrainingOutline outline);

    /**
     * 查询培训大纲详情（包含关联信息）
     * 
     * @param outlineId 大纲ID
     * @return 培训大纲详情
     */
//    TrainingOutline selectTrainingOutlineByIdWithRelations(@Param("outlineId") Long outlineId);

    /**
     * 检查机型+专业+培训能力组合的唯一性
     * 
     * @param machineTypeId 机型ID
     * @param majorId 专业ID
     * @param trainingAbilityId 培训能力ID
     * @param outlineId 排除的大纲ID（用于编辑时排除自己）
     * @return 符合条件的记录数
     */
    @Select("SELECT COUNT(*) FROM tms_training_outline " +
            "WHERE machine_type_id = #{machineTypeId} " +
            "AND major_id = #{majorId} " +
            "AND training_ability_id = #{trainingAbilityId} " +
            "AND status = '0' " +
            "AND (#{outlineId} IS NULL OR outline_id != #{outlineId})")
    int countByMachineTypeMajorAbility(@Param("machineTypeId") Long machineTypeId, 
                                      @Param("majorId") Long majorId, 
                                      @Param("trainingAbilityId") Long trainingAbilityId,
                                      @Param("outlineId") Long outlineId);

    /**
     * 根据机型、专业、培训能力查询有效大纲
     * 
     * @param machineTypeId 机型ID
     * @param majorId 专业ID
     * @param trainingAbilityId 培训能力ID
     * @return 培训大纲
     */
    @Select("SELECT * FROM tms_training_outline " +
            "WHERE machine_type_id = #{machineTypeId} " +
            "AND major_id = #{majorId} " +
            "AND training_ability_id = #{trainingAbilityId} " +
            "AND status = '0' " +
            "AND (effective_date IS NULL OR effective_date <= CURDATE()) " +
            "AND (expiry_date IS NULL OR expiry_date >= CURDATE()) " +
            "ORDER BY effective_date DESC LIMIT 1")
    TrainingOutline selectActiveOutline(@Param("machineTypeId") Long machineTypeId, 
                                       @Param("majorId") Long majorId, 
                                       @Param("trainingAbilityId") Long trainingAbilityId);

    void deleteBatch(List<Long> idList);
}