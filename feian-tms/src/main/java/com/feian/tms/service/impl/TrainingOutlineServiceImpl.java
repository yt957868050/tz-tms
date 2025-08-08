package com.feian.tms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.common.utils.PageUtils;
import com.feian.tms.common.PageRequest;
import com.feian.tms.domain.TrainingOutline;
import com.feian.tms.domain.MachineType;
import com.feian.tms.domain.Major;
import com.feian.tms.domain.TrainingAbility;
import com.feian.tms.dto.request.TrainingOutlineRequest;
import com.feian.tms.dto.response.TrainingOutlineResponse;
import com.feian.tms.mapper.TrainingOutlineMapper;
import com.feian.tms.service.TrainingOutlineService;
import com.feian.tms.service.MachineTypeService;
import com.feian.tms.service.MajorService;
import com.feian.tms.service.TrainingAbilityService;
import com.github.pagehelper.PageInfo;
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
    private final MachineTypeService machineTypeService;
    private final MajorService majorService;
    private final TrainingAbilityService trainingAbilityService;

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
    public PageInfo<TrainingOutlineResponse> selectTrainingOutlineList(PageRequest<TrainingOutlineRequest> pageRequest) {

        TrainingOutlineRequest request = pageRequest.getQuery();
        if (request == null) {
            request = new TrainingOutlineRequest();
        }

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

        // 启动分页
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());

        List<TrainingOutline> list = this.list(wrapper);

        PageInfo<TrainingOutline> page = new PageInfo<>(list);

        List<TrainingOutlineResponse> resltList = list.stream().map(this::convertToResponse).toList();

        PageInfo<TrainingOutlineResponse> result = new PageInfo<>(resltList);

        BeanUtils.copyProperties(page, result, "list");

        return result;
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
        
        // 总课时由数据库自动计算，不需要手动设置
        
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
        
        // 总课时由数据库自动计算，不需要手动设置
        
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
        return null;//selectTrainingOutlineList(request);
    }

    @Override
    public TrainingOutlineResponse downloadRegulationFile(Long outlineId) {
        return selectTrainingOutlineById(outlineId);
    }

    @Override
    public TrainingOutlineResponse downloadOutlineFile(Long outlineId) {
        return selectTrainingOutlineById(outlineId);
    }

    @Override
    public boolean deleteBatch(List<Long> idList) {
        trainingOutlineMapper.deleteBatch(idList);
        return true;
    }

    @Override
    public List<TrainingOutline> trainingOutlineList() {
        return trainingOutlineMapper.trainingOutlineList();
    }

    /**
     * 转换实体为响应对象
     */
    private TrainingOutlineResponse convertToResponse(TrainingOutline outline) {
        TrainingOutlineResponse response = new TrainingOutlineResponse();
        BeanUtils.copyProperties(outline, response);
        
        // 关联查询获取机型、专业、培训能力名称
        if (outline.getMachineTypeId() != null) {
            MachineType machineType = machineTypeService.getById(outline.getMachineTypeId());
            if (machineType != null) {
                response.setMachineTypeName(machineType.getMachineTypeName());
            }
        }
        
        if (outline.getMajorId() != null) {
            Major major = majorService.getById(outline.getMajorId());
            if (major != null) {
                response.setMajorName(major.getMajorName());
            }
        }
        
        if (outline.getTrainingAbilityId() != null) {
            TrainingAbility trainingAbility = trainingAbilityService.getById(outline.getTrainingAbilityId());
            if (trainingAbility != null) {
                response.setTrainingAbilityName(trainingAbility.getAbilityName());
            }
        }
        
        return response;
    }
}