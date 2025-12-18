package com.feian.tms.mapper;

import com.feian.tms.domain.QuestionArchive;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 题目分类Mapper接口
 *
 * @author feian
 * @date 2025-10-13
 */
@Mapper
public interface QuestionArchiveMapper extends MPJBaseMapper<QuestionArchive> {

    /**
     * 获取根分类
     * @return
     */
    @Select("select * from tms_question_archive where is_deleted=0 and parent_id is null")
    List<QuestionArchive> getRootQuestionArchive();

    /**
     * 获取子分类
     * @param id
     * @return
     */
    @Select("select * from tms_question_archive where is_deleted=0 and parent_id=#{id}")
    List<QuestionArchive> getQuestionArchiveByParentId(Long id);
}
