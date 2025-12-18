package com.feian.tms.exam.practice.mapper;

import com.feian.tms.exam.practice.domain.ExamPracticeWrongQuestion;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExamPracticeWrongQuestionMapper extends MPJBaseMapper<ExamPracticeWrongQuestion> {

    @Delete("""
            delete from exam_practice_wrong_question
            where user_id = #{userId}
              and machine_type_id = #{machineTypeId}
              and category_id = #{categoryId}
            """)
    int purgeByUserMachineCategory(@Param("userId") Long userId,
                                   @Param("machineTypeId") Long machineTypeId,
                                   @Param("categoryId") Long categoryId);
}

