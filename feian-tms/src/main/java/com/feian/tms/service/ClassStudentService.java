package com.feian.tms.service;

import com.feian.tms.domain.ClassStudent;
import com.feian.tms.dto.response.ClassStudentResponse;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 班次学员关联Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface ClassStudentService extends MPJBaseService<ClassStudent> {

    /**
     * 获取指定班次的可选学员列表（排除已在班次中的学员）
     * 
     * @param trainingClassId 培训班次ID
     * @return 可选学员列表
     */
    List<ClassStudentResponse> getAvailableStudentsForClass(Long trainingClassId);
    
    /**
     * 获取班次学员关联列表（包含关联信息）
     * 
     * @param query 查询条件
     * @return 班次学员关联列表
     */
    List<ClassStudentResponse> getClassStudentListWithDetails(com.feian.tms.dto.query.ClassStudentQuery query);

    /**
     * 根据学员id删除学员班次关联
     * @param idList
     */
    void removeStudentByIds(List<Long> idList);


    void removeAllByIds(List<Long> idList);

    void removeByClassIds(List<Long> idList);
}