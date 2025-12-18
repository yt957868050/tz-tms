package com.feian.tms.exam.mapper;

import com.feian.tms.exam.domain.ExamQuestion;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamQuestionMapper extends MPJBaseMapper<ExamQuestion> {

    @Select("""
        <script>
        select count(1)
        from exam_question
        where is_deleted = 0
          and machine_type_id = #{machineTypeId}
          <if test="ifCheck != null">
            and if_check = #{ifCheck}
          </if>
          <if test="categoryId != null">
            and category_id = #{categoryId}
          </if>
          <if test="questionTypes != null and questionTypes.size() &gt; 0">
            and question_type in
            <foreach collection="questionTypes" item="qt" open="(" separator="," close=")">#{qt}</foreach>
          </if>
          <if test="difficultList != null and difficultList.size() &gt; 0">
            and difficult in
            <foreach collection="difficultList" item="df" open="(" separator="," close=")">#{df}</foreach>
          </if>
        </script>
        """)
    Integer countAvailable(@Param("machineTypeId") Long machineTypeId,
                           @Param("ifCheck") Integer ifCheck,
                           @Param("categoryId") Long categoryId,
                           @Param("questionTypes") List<Integer> questionTypes,
                           @Param("difficultList") List<Integer> difficultList);

    @Select("""
        <script>
        select *
        from exam_question
        where is_deleted = 0
          and machine_type_id = #{machineTypeId}
          <if test="ifCheck != null">
            and if_check = #{ifCheck}
          </if>
          <if test="categoryId != null">
            and category_id = #{categoryId}
          </if>
          <if test="questionTypes != null and questionTypes.size() &gt; 0">
            and question_type in
            <foreach collection="questionTypes" item="qt" open="(" separator="," close=")">#{qt}</foreach>
          </if>
          <if test="difficultList != null and difficultList.size() &gt; 0">
            and difficult in
            <foreach collection="difficultList" item="df" open="(" separator="," close=")">#{df}</foreach>
          </if>
        order by rand()
        limit #{limit}
        </script>
        """)
    List<ExamQuestion> selectRandomByCategory(@Param("machineTypeId") Long machineTypeId,
                                              @Param("ifCheck") Integer ifCheck,
                                              @Param("categoryId") Long categoryId,
                                              @Param("questionTypes") List<Integer> questionTypes,
                                              @Param("difficultList") List<Integer> difficultList,
                                              @Param("limit") Integer limit);
}
