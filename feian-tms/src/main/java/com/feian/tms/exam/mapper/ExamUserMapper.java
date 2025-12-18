package com.feian.tms.exam.mapper;

import com.feian.tms.exam.dto.ExamUserStudentMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamUserMapper {

    @Select("""
            <script>
            select user_id as userId, student_id as studentId
            from sys_user
            where del_flag = '0'
              and student_id is not null
              <if test="studentIds != null and studentIds.size() &gt; 0">
                and student_id in
                <foreach collection="studentIds" item="sid" open="(" separator="," close=")">#{sid}</foreach>
              </if>
            </script>
            """)
    List<ExamUserStudentMapping> selectUserStudentMappings(@Param("studentIds") List<Long> studentIds);
}

