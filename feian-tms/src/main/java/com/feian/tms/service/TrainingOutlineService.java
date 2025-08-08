package com.feian.tms.service;

import com.feian.tms.common.PageRequest;
import com.feian.tms.domain.TrainingOutline;
import com.feian.tms.dto.request.TrainingOutlineRequest;
import com.feian.tms.dto.response.TrainingOutlineResponse;
import com.github.pagehelper.PageInfo;
import com.github.yulichang.base.MPJBaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 培训大纲Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface TrainingOutlineService extends MPJBaseService<TrainingOutline> {

    /**
     * 分页查询培训大纲列表
     * 
     * @param page 分页参数
     * @param request 查询条件
     * @return 培训大纲分页结果
     */
    Page<TrainingOutlineResponse> selectTrainingOutlinePage(Page<TrainingOutline> page, TrainingOutlineRequest request);

    /**
     * 查询培训大纲列表
     * 
     * @param pageRequest 查询条件
     * @return 培训大纲列表
     */
    PageInfo<TrainingOutlineResponse> selectTrainingOutlineList(PageRequest<TrainingOutlineRequest> pageRequest);

    /**
     * 根据ID查询培训大纲详情
     * 
     * @param outlineId 大纲ID
     * @return 培训大纲详情
     */
    TrainingOutlineResponse selectTrainingOutlineById(Long outlineId);

    /**
     * 新增培训大纲
     * 
     * @param request 培训大纲信息
     * @return 操作结果
     */
    TrainingOutlineResponse insertTrainingOutline(TrainingOutlineRequest request);

    /**
     * 修改培训大纲
     * 
     * @param request 培训大纲信息
     * @return 操作结果
     */
    TrainingOutlineResponse updateTrainingOutline(TrainingOutlineRequest request);

    /**
     * 批量删除培训大纲
     * 
     * @param outlineIds 大纲ID列表
     * @return 操作结果
     */
    boolean deleteTrainingOutlineByIds(List<Long> outlineIds);

    /**
     * 删除培训大纲
     * 
     * @param outlineId 大纲ID
     * @return 操作结果
     */
    boolean deleteTrainingOutlineById(Long outlineId);

    /**
     * 检查机型+专业+培训能力组合的唯一性
     * 
     * @param machineTypeId 机型ID
     * @param majorId 专业ID
     * @param trainingAbilityId 培训能力ID
     * @param outlineId 排除的大纲ID（用于编辑时排除自己）
     * @return 是否唯一
     */
    boolean checkUniqueness(Long machineTypeId, Long majorId, Long trainingAbilityId, Long outlineId);

    /**
     * 根据机型、专业、培训能力查询有效大纲
     * 
     * @param machineTypeId 机型ID
     * @param majorId 专业ID
     * @param trainingAbilityId 培训能力ID
     * @return 培训大纲
     */
    TrainingOutlineResponse getActiveOutline(Long machineTypeId, Long majorId, Long trainingAbilityId);

    /**
     * 导出培训大纲列表
     * 
     * @param request 查询条件
     * @return 导出数据
     */
    List<TrainingOutlineResponse> exportTrainingOutlineList(TrainingOutlineRequest request);

    /**
     * 下载培训规范文件
     * 
     * @param outlineId 大纲ID
     * @return 文件信息
     */
    TrainingOutlineResponse downloadRegulationFile(Long outlineId);

    /**
     * 下载大纲文件
     * 
     * @param outlineId 大纲ID
     * @return 文件信息
     */
    TrainingOutlineResponse downloadOutlineFile(Long outlineId);

    boolean deleteBatch(List<Long> idList);

    List<TrainingOutline> trainingOutlineList();
}