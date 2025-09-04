package com.feian.tms.mapper;

import com.feian.tms.domain.Student;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 学员信息Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface StudentMapper extends MPJBaseMapper<Student> {

    void deleteIds(List<Long> idList);


    void setPrimaryMachineName(Long studentId, Long primaryMachineTypeId);

    void setPrimaryMajor(Long studentId, Long primaryMajorId);

    List<Student> studentListByIds(List<Long> idList);

    List<Student> studentList();

    @Select("select student_name from tms_student where student_id=#{StudentId}")
    String getStudentName(Long StudentId);

    @Select("select student_code from tms_student where student_id=#{StudentId}")
    String getStudentCode(Long StudentId);

   @Select("select id_card from tms_student where student_id=#{studentId}")
    String getIdCardById(Long studentId);

    @Select("select student_id from tms_student where student_code=#{studentCode} and is_deleted=0")
    Long getStudentIdByCode(String studentCode);
}