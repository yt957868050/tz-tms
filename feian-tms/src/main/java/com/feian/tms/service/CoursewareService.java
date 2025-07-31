package com.feian.tms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feian.tms.domain.Courseware;
import com.feian.tms.dto.request.CoursewareRequest;
import com.feian.tms.dto.response.CoursewareResponse;
import com.github.yulichang.base.MPJBaseService;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * 课件管理Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface CoursewareService extends MPJBaseService<Courseware> {

    /**
     * 分页查询课件列表（带关联信息）
     */
    Page<CoursewareResponse> selectCoursewarePage(Page<Courseware> page, CoursewareRequest request);

    /**
     * 查询课件列表（带关联信息）
     */
    List<CoursewareResponse> selectCoursewareList(CoursewareRequest request);

    /**
     * 根据ID查询课件详情（带关联信息）
     */
    CoursewareResponse selectCoursewareById(Long coursewareId);

    Long getCoursewareIdBycourse_code(@NotBlank(message = "课程编码不能为空") String courseCode);
}