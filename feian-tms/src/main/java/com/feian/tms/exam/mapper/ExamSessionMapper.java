package com.feian.tms.exam.mapper;

import com.feian.tms.exam.dto.ExamMySessionResponse;
import com.feian.tms.exam.domain.ExamSession;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamSessionMapper extends MPJBaseMapper<ExamSession> {

    @Select("""
            <script>
            select
                s.session_id           as sessionId,
                s.name                 as name,
                s.paper_id             as paperId,
                s.machine_type_id      as machineTypeId,
                s.window_start         as windowStart,
                s.window_end           as windowEnd,
                s.suggest_time_min     as suggestTimeMin,
                s.publish_status       as publishStatus,
                s.publish_time         as publishTime,
                s.pass_rule_type       as passRuleType,
                s.pass_score           as passScore,
                s.pass_question_num    as passQuestionNum,
                s.shuffle_question     as shuffleQuestion,
                s.shuffle_option       as shuffleOption,
                s.anti_cheat_cheat     as antiCheatCheat,
                s.anti_cheat_watch     as antiCheatWatch,
                s.anti_cheat_capture   as antiCheatCapture,
                c.id                   as candidateId,
                c.class_id             as classId,
                c.makeup_from_answer_id as makeupFromAnswerId,
                c.window_start_override as windowStartOverride,
                c.window_end_override   as windowEndOverride
            from exam_candidate c
            join exam_session s on s.session_id = c.session_id and s.is_deleted = 0
            where c.is_deleted = 0
              and c.user_id = #{userId}
              and (s.publish_status is null or s.publish_status = 2)
              <if test="machineTypeId != null">
                and s.machine_type_id = #{machineTypeId}
              </if>
            order by s.window_start desc, s.session_id desc
            </script>
            """)
    List<ExamMySessionResponse> selectMySessions(@Param("userId") Long userId, @Param("machineTypeId") Long machineTypeId);
}
