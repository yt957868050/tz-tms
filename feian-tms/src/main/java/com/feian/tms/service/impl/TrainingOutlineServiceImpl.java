package com.feian.tms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.domain.TrainingOutline;
import com.feian.tms.dto.request.TrainingOutlineRequest;
import com.feian.tms.dto.response.TrainingOutlineResponse;
import com.feian.tms.mapper.TrainingOutlineMapper;
import com.feian.tms.service.TrainingOutlineService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 培训大纲Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class TrainingOutlineServiceImpl extends MPJBaseServiceImpl<TrainingOutlineMapper, TrainingOutline> implements TrainingOutlineService {

    private final TrainingOutlineMapper trainingOutlineMapper;

    @Override
    public Page<TrainingOutlineResponse> selectTrainingOutlinePage(Page<TrainingOutline> page, TrainingOutlineRequest request) {
        // 构建查询条件
        LambdaQueryWrapper<TrainingOutline> wrapper = Wrappers.lambdaQuery();
        
        if (request.getOutlineName() != null && !request.getOutlineName().trim().isEmpty()) {
            wrapper.like(TrainingOutline::getOutlineName, request.getOutlineName().trim());
        }
        if (request.getOutlineCode() != null && !request.getOutlineCode().trim().isEmpty()) {
            wrapper.like(TrainingOutline::getOutlineCode, request.getOutlineCode().trim());
        }
        if (request.getMachineTypeId() != null) {
            wrapper.eq(TrainingOutline::getMachineTypeId, request.getMachineTypeId());
        }
        if (request.getMajorId() != null) {
            wrapper.eq(TrainingOutline::getMajorId, request.getMajorId());
        }
        if (request.getTrainingAbilityId() != null) {
            wrapper.eq(TrainingOutline::getTrainingAbilityId, request.getTrainingAbilityId());
        }
        if (request.getStatus() != null && !request.getStatus().trim().isEmpty()) {
            wrapper.eq(TrainingOutline::getStatus, request.getStatus().trim());
        }
        if (request.getVersion() != null && !request.getVersion().trim().isEmpty()) {
            wrapper.like(TrainingOutline::getVersion, request.getVersion().trim());
        }

        // 按创建时间倒序
        wrapper.orderByDesc(TrainingOutline::getCreateTime);

        Page<TrainingOutline> result = this.page(page, wrapper);
        
        // 转换为响应对象
        Page<TrainingOutlineResponse> responsePage = new Page<>(result.getCurrent(), result.getSize(), result.getTotal());
        List<TrainingOutlineResponse> responses = result.getRecords().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        responsePage.setRecords(responses);
        
        return responsePage;
    }

    @Override
    public List<TrainingOutlineResponse> selectTrainingOutlineList(TrainingOutlineRequest request) {
        LambdaQueryWrapper<TrainingOutline> wrapper = Wrappers.lambdaQuery();
        
        // 查询条件（同分页查询）
        if (request.getOutlineName() != null && !request.getOutlineName().trim().isEmpty()) {
            wrapper.like(TrainingOutline::getOutlineName, request.getOutlineName().trim());
        }
        if (request.getOutlineCode() != null && !request.getOutlineCode().trim().isEmpty()) {
            wrapper.like(TrainingOutline::getOutlineCode, request.getOutlineCode().trim());
        }
        if (request.getMachineTypeId() != null) {
            wrapper.eq(TrainingOutline::getMachineTypeId, request.getMachineTypeId());
        }
        if (request.getMajorId() != null) {
            wrapper.eq(TrainingOutline::getMajorId, request.getMajorId());
        }
        if (request.getTrainingAbilityId() != null) {
            wrapper.eq(TrainingOutline::getTrainingAbilityId, request.getTrainingAbilityId());
        }
        if (request.getStatus() != null && !request.getStatus().trim().isEmpty()) {
            wrapper.eq(TrainingOutline::getStatus, request.getStatus().trim());
        }
        if (request.getVersion() != null && !request.getVersion().trim().isEmpty()) {
            wrapper.like(TrainingOutline::getVersion, request.getVersion().trim());
        }

        // 按创建时间倒序
        wrapper.orderByDesc(TrainingOutline::getCreateTime);

        List<TrainingOutline> list = this.list(wrapper);
        return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public TrainingOutlineResponse selectTrainingOutlineById(Long outlineId) {
        TrainingOutline outline = this.getById(outlineId);
        if (outline == null) {
            return null;
        }
        return convertToResponse(outline);
    }

    @Override
    public TrainingOutlineResponse insertTrainingOutline(TrainingOutlineRequest request) {
        TrainingOutline outline = new TrainingOutline();
        BeanUtils.copyProperties(request, outline);
        
        // 计算总课时
        BigDecimal theoryHours = request.getTheoryHours() != null ? request.getTheoryHours() : BigDecimal.ZERO;
        BigDecimal practicalHours = request.getPracticalHours() != null ? request.getPracticalHours() : BigDecimal.ZERO;
        outline.setTotalHours(theoryHours.add(practicalHours));
        
        // 保存实体
        boolean result = this.save(outline);
        if (result) {
            return selectTrainingOutlineById(outline.getOutlineId());
        }
        return null;
    }

    @Override
    public TrainingOutlineResponse updateTrainingOutline(TrainingOutlineRequest request) {
        TrainingOutline outline = new TrainingOutline();
        BeanUtils.copyProperties(request, outline);
        
        // 计算总课时
        BigDecimal theoryHours = request.getTheoryHours() != null ? request.getTheoryHours() : BigDecimal.ZERO;
        BigDecimal practicalHours = request.getPracticalHours() != null ? request.getPracticalHours() : BigDecimal.ZERO;
        outline.setTotalHours(theoryHours.add(practicalHours));
        
        // 更新实体
        boolean result = this.updateById(outline);
        if (result) {
            return selectTrainingOutlineById(outline.getOutlineId());
        }
        return null;
    }

    @Override
    public boolean deleteTrainingOutlineByIds(List<Long> outlineIds) {
        return this.removeByIds(outlineIds);
    }

    @Override
    public boolean deleteTrainingOutlineById(Long outlineId) {
        return this.removeById(outlineId);
    }

    @Override
    public boolean checkUniqueness(Long machineTypeId, Long majorId, Long trainingAbilityId, Long outlineId) {
        int count = trainingOutlineMapper.countByMachineTypeMajorAbility(machineTypeId, majorId, trainingAbilityId, outlineId);
        return count == 0;
    }

    @Override
    public TrainingOutlineResponse getActiveOutline(Long machineTypeId, Long majorId, Long trainingAbilityId) {
        TrainingOutline outline = trainingOutlineMapper.selectActiveOutline(machineTypeId, majorId, trainingAbilityId);
        if (outline == null) {
            return null;
        }
        return convertToResponse(outline);
    }

    @Override
    public List<TrainingOutlineResponse> exportTrainingOutlineList(TrainingOutlineRequest request) {
        return selectTrainingOutlineList(request);
    }

    @Override
    public TrainingOutlineResponse downloadRegulationFile(Long outlineId) {
        return selectTrainingOutlineById(outlineId);
    }

    @Override
    public TrainingOutlineResponse downloadOutlineFile(Long outlineId) {
        return selectTrainingOutlineById(outlineId);
    }

    /**
     * 转换实体为响应对象
     */
    private TrainingOutlineResponse convertToResponse(TrainingOutline outline) {
        TrainingOutlineResponse response = new TrainingOutlineResponse();
        BeanUtils.copyProperties(outline, response);
        
        // TODO: 这里可以添加关联查询来获取机型名称、专业名称、培训能力名称
        // 现在先返回基本信息，可以后续优化为联表查询
        
        return response;
    }
}