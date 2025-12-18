package com.feian.tms.exam.practice.mapper;

import com.feian.tms.exam.practice.dto.ExamPracticeChapterResponse;
import com.feian.tms.exam.practice.dto.ExamPracticeQuestionResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamPracticeQueryMapper {

    @Select("""
            <script>
            select
                c.category_id as categoryId,
                c.name as name,
                c.parent_id as parentId,
                c.path as path,
                c.class_hour as classHour,
                c.machine_type_id as machineTypeId,
                coalesce(p.cnt, 0) as practiceQuestionCount,
                coalesce(w.cnt, 0) as wrongQuestionCount,
                w.lastTime as wrongSavedTime
            from exam_category c
            left join (
                select category_id, count(1) as cnt
                from exam_question
                where is_deleted = 0
                  and machine_type_id = #{machineTypeId}
                  and if_check = 0
                group by category_id
            ) p on p.category_id = c.category_id
            left join (
                select category_id, count(1) as cnt, max(create_time) as lastTime
                from exam_practice_wrong_question
                where is_deleted = 0
                  and user_id = #{userId}
                  and machine_type_id = #{machineTypeId}
                group by category_id
            ) w on w.category_id = c.category_id
            where c.is_deleted = 0
              and c.machine_type_id = #{machineTypeId}
            order by c.category_id asc
            </script>
            """)
    List<ExamPracticeChapterResponse> selectChapterList(@Param("userId") Long userId,
                                                       @Param("machineTypeId") Long machineTypeId);

    @Select("""
            <script>
            select
                q.question_id as questionId,
                q.category_id as categoryId,
                q.question_type as questionType,
                q.score as score,
                q.difficult as difficult,
                qc.content_json as contentJson
            from exam_question q
            left join exam_question_content qc
                on qc.question_id = q.question_id
               and qc.is_deleted = 0
            where q.is_deleted = 0
              and q.machine_type_id = #{machineTypeId}
              and q.category_id = #{categoryId}
              and q.if_check = 0
            order by q.question_id asc
            </script>
            """)
    List<ExamPracticeQuestionResponse> selectPracticeQuestions(@Param("machineTypeId") Long machineTypeId,
                                                              @Param("categoryId") Long categoryId);

    @Select("""
            <script>
            select
                q.question_id as questionId,
                q.category_id as categoryId,
                q.question_type as questionType,
                q.score as score,
                q.difficult as difficult,
                qc.content_json as contentJson
            from exam_practice_wrong_question w
            join exam_question q
              on q.question_id = w.question_id
             and q.is_deleted = 0
             and q.if_check = 0
            left join exam_question_content qc
              on qc.question_id = q.question_id
             and qc.is_deleted = 0
            where w.is_deleted = 0
              and w.user_id = #{userId}
              and w.machine_type_id = #{machineTypeId}
              and w.category_id = #{categoryId}
            order by q.question_id asc
            </script>
            """)
    List<ExamPracticeQuestionResponse> selectWrongQuestions(@Param("userId") Long userId,
                                                           @Param("machineTypeId") Long machineTypeId,
                                                           @Param("categoryId") Long categoryId);

    @Select("""
            <script>
            select question_id
            from exam_practice_wrong_question
            where is_deleted = 0
              and user_id = #{userId}
              and machine_type_id = #{machineTypeId}
              and category_id = #{categoryId}
            order by question_id asc
            </script>
            """)
    List<Long> selectWrongQuestionIds(@Param("userId") Long userId,
                                      @Param("machineTypeId") Long machineTypeId,
                                      @Param("categoryId") Long categoryId);
}

