package com.feian.tms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.domain.Courseware;
import com.feian.tms.domain.MachineType;
import com.feian.tms.domain.Major;
import com.feian.tms.domain.TrainingType;
import com.feian.tms.dto.request.CoursewareRequest;
import com.feian.tms.dto.response.CoursewareResponse;
import com.feian.tms.mapper.CoursewareMapper;
import com.feian.tms.mapper.MachineTypeMapper;
import com.feian.tms.mapper.MajorMapper;
import com.feian.tms.mapper.TrainingTypeMapper;
import com.feian.tms.service.CoursewareService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 课件管理Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class CoursewareServiceImpl extends MPJBaseServiceImpl<CoursewareMapper, Courseware> implements CoursewareService {

    private final CoursewareMapper coursewareMapper;
    private final MachineTypeMapper machineTypeMapper;
    private final MajorMapper majorMapper;
    private final TrainingTypeMapper trainingTypeMapper;

    @Override
    public Page<CoursewareResponse> selectCoursewarePage(Page<Courseware> page, CoursewareRequest request) {
        // 构建查询条件
        LambdaQueryWrapper<Courseware> wrapper = buildQueryWrapper(request);
        
        // 分页查询
        Page<Courseware> coursewarePage = this.page(page, wrapper);
        
        // 转换为响应对象
        Page<CoursewareResponse> responsePage = new Page<>(coursewarePage.getCurrent(), coursewarePage.getSize(), coursewarePage.getTotal());
        List<CoursewareResponse> responses = coursewarePage.getRecords().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        
        // 批量填充关联信息
        fillRelationNames(responses);
        
        responsePage.setRecords(responses);
        return responsePage;
    }

    @Override
    public List<CoursewareResponse> selectCoursewareList(CoursewareRequest request) {
        LambdaQueryWrapper<Courseware> wrapper = buildQueryWrapper(request);
        List<Courseware> list = this.list(wrapper);
        List<CoursewareResponse> responses = list.stream().map(this::convertToResponse).collect(Collectors.toList());
        
        // 批量填充关联信息
        fillRelationNames(responses);
        
        return responses;
    }

    @Override
    public CoursewareResponse selectCoursewareById(Long coursewareId) {
        Courseware courseware = this.getById(coursewareId);
        if (courseware == null) {
            return null;
        }
        return convertToResponse(courseware);
    }

    @Override
    public Long getCoursewareIdBycourse_code(String courseCode) {
        return coursewareMapper.getCoursewareIdBycourse_code(courseCode);
    }

    /**
     * 构建查询条件
     */
    private LambdaQueryWrapper<Courseware> buildQueryWrapper(CoursewareRequest request) {
        LambdaQueryWrapper<Courseware> wrapper = Wrappers.lambdaQuery();
        
        if (request.getMachineTypeId() != null) {
            wrapper.eq(Courseware::getMachineTypeId, request.getMachineTypeId());
        }
        if (request.getMajorId() != null) {
            wrapper.eq(Courseware::getMajorId, request.getMajorId());
        }
        if (request.getTrainingTypeId() != null) {
            wrapper.eq(Courseware::getTrainingTypeId, request.getTrainingTypeId());
        }
        if (request.getCourseName() != null && !request.getCourseName().trim().isEmpty()) {
            wrapper.like(Courseware::getCourseName, request.getCourseName().trim());
        }
        if (request.getCourseCode() != null && !request.getCourseCode().trim().isEmpty()) {
            wrapper.like(Courseware::getCourseCode, request.getCourseCode().trim());
        }
        if (request.getTrainingType() != null && !request.getTrainingType().trim().isEmpty()) {
            wrapper.eq(Courseware::getTrainingType, request.getTrainingType().trim());
        }
        if (request.getStatus() != null && !request.getStatus().trim().isEmpty()) {
            wrapper.eq(Courseware::getStatus, request.getStatus().trim());
        }

        // 按排序号和创建时间排序
        wrapper.orderByAsc(Courseware::getOrderNum)
               .orderByDesc(Courseware::getCreateTime);

        return wrapper;
    }

    /**
     * 转换为响应对象并填充关联信息（批量优化版本）
     */
    private CoursewareResponse convertToResponse(Courseware courseware) {
        CoursewareResponse response = new CoursewareResponse();
        BeanUtils.copyProperties(courseware, response);
        return response;
    }

    /**
     * 批量填充关联信息，避免N+1查询问题
     */
    private void fillRelationNames(List<CoursewareResponse> responses) {
        if (responses.isEmpty()) {
            return;
        }

        // 收集所有需要查询的ID
        List<Long> machineTypeIds = responses.stream()
                .map(CoursewareResponse::getMachineTypeId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        List<Long> majorIds = responses.stream()
                .map(CoursewareResponse::getMajorId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        List<Long> trainingTypeIds = responses.stream()
                .map(CoursewareResponse::getTrainingTypeId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // 批量查询关联数据
        Map<Long, String> machineTypeMap = new HashMap<>();
        Map<Long, String> majorMap = new HashMap<>(); 
        Map<Long, String> trainingTypeMap = new HashMap<>();

        if (!machineTypeIds.isEmpty()) {
            List<MachineType> machineTypes = machineTypeMapper.selectBatchIds(machineTypeIds);
            machineTypeMap = machineTypes.stream()
                    .collect(Collectors.toMap(MachineType::getMachineTypeId, MachineType::getMachineTypeName));
        }

        if (!majorIds.isEmpty()) {
            List<Major> majors = majorMapper.selectBatchIds(majorIds);
            majorMap = majors.stream()
                    .collect(Collectors.toMap(Major::getMajorId, Major::getMajorName));
        }

        if (!trainingTypeIds.isEmpty()) {
            List<TrainingType> trainingTypes = trainingTypeMapper.selectBatchIds(trainingTypeIds);
            trainingTypeMap = trainingTypes.stream()
                    .collect(Collectors.toMap(TrainingType::getTrainingTypeId, TrainingType::getTrainingTypeName));
        }

        // 填充关联名称
        for (CoursewareResponse response : responses) {
            if (response.getMachineTypeId() != null) {
                response.setMachineTypeName(machineTypeMap.get(response.getMachineTypeId()));
            }
            if (response.getMajorId() != null) {
                response.setMajorName(majorMap.get(response.getMajorId()));
            }
            if (response.getTrainingTypeId() != null) {
                response.setTrainingTypeName(trainingTypeMap.get(response.getTrainingTypeId()));
            }
        }
    }
}