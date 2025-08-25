package com.feian.tms.service.impl;

import com.feian.tms.domain.ClassStudent;
import com.feian.tms.domain.Student;
import com.feian.tms.domain.TrainingClass;
import com.feian.tms.dto.query.ClassStudentQuery;
import com.feian.tms.dto.response.ClassStudentResponse;
import com.feian.tms.mapper.ClassStudentMapper;
import com.feian.tms.service.ClassStudentService;
import com.feian.tms.service.StudentService;
import com.feian.tms.service.TrainingClassService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班次学员关联Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class ClassStudentServiceImpl extends MPJBaseServiceImpl<ClassStudentMapper, ClassStudent> implements ClassStudentService {

    private final StudentService studentService;
    private final TrainingClassService trainingClassService;
    private final ClassStudentMapper classStudentMapper;

    @Override
    public List<ClassStudentResponse> getAvailableStudentsForClass(Long trainingClassId) {
        // 获取已在班次中的学员ID列表
        List<Long> existingStudentIds = this.lambdaQuery()
                .eq(ClassStudent::getTrainingClassId, trainingClassId)
                .list()
                .stream()
                .map(ClassStudent::getStudentId)
                .toList();

        // 获取所有有效学员，排除已在班次中的学员
        var studentQuery = studentService.lambdaQuery()
                .eq(Student::getStatus, "0"); // 只查询正常状态的学员
        
        if (!existingStudentIds.isEmpty()) {
            studentQuery.notIn(Student::getStudentId, existingStudentIds);
        }
        
        List<Student> availableStudents = studentQuery.list();

        // 转换为响应对象
        return availableStudents.stream()
                .map(student -> {
                    ClassStudentResponse response = new ClassStudentResponse();
                    response.setStudentId(student.getStudentId());
                    response.setStudentCode(student.getStudentCode());
                    response.setStudentName(student.getStudentName());
                    response.setIdCard(student.getIdCard());
                    response.setPhoneNumber(student.getPhoneNumber());
                    return response;
                })
                .toList();
    }

    @Override
    public List<ClassStudentResponse> getClassStudentListWithDetails(ClassStudentQuery query) {
        // 使用MPJ进行多表连接查询
        MPJLambdaWrapper<ClassStudent> wrapper = new MPJLambdaWrapper<ClassStudent>()
                .selectAll(ClassStudent.class)
                .select(Student::getStudentCode, Student::getStudentName, Student::getIdCard, Student::getPhoneNumber)
                .select(TrainingClass::getClassCode, TrainingClass::getClassName)
                .leftJoin(Student.class, Student::getStudentId, ClassStudent::getStudentId)
                .leftJoin(TrainingClass.class, TrainingClass::getTrainingClassId, ClassStudent::getTrainingClassId);

        // 添加查询条件
        if (query != null) {
            wrapper.eq(query.getTrainingClassId() != null, ClassStudent::getTrainingClassId, query.getTrainingClassId())
                   .eq(query.getStudentId() != null, ClassStudent::getStudentId, query.getStudentId())
                   .ge(query.getEnrollTime() != null, ClassStudent::getEnrollTime, query.getEnrollTime())
                   .eq(query.getStudentStatus() != null, ClassStudent::getStudentStatus, query.getStudentStatus());
        }
        wrapper.eq(Student::getIsDeleted, 0)
                .eq(ClassStudent::getIsDeleted, 0)
                .eq(TrainingClass::getIsDeleted, 0);

        wrapper.orderByDesc(ClassStudent::getEnrollTime);

        // 执行查询
        List<ClassStudentResponse> results = this.selectJoinList(ClassStudentResponse.class, wrapper);

        // 转换学员状态名称
        results.forEach(result -> {
            result.setStudentStatusName(convertStudentStatus(result.getStudentStatus()));
        });

        return results;
    }

    @Override
    public void removeStudentByIds(List<Long> idList) {
        classStudentMapper.remremoveStudentByIds(idList);
    }

    @Override
    public void removeAllByIds(List<Long> idList) {
        classStudentMapper.removeAllByIds(idList);
    }

    @Override
    public void removeByClassIds(List<Long> idList) {
        classStudentMapper.removeByClassIds(idList);
    }

    /**
     * 获取学生关联列表
     * @return
     */
    public List<ClassStudent> classStudentList() {
        return classStudentMapper.classStudentList();
    }


    /**
     * 转换学员状态枚举
     */
    private String convertStudentStatus(String studentStatus) {
        if (studentStatus == null) return "";
        return switch (studentStatus) {
            case "0" -> "待开班";
            case "1" -> "培训中";
            case "2" -> "已结业";
            case "3" -> "已退班";
            case "4" -> "请假中";
            default -> "";
        };
    }
}