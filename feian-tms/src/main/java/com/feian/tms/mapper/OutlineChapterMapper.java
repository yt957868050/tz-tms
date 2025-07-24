package com.feian.tms.mapper;

import com.feian.tms.domain.OutlineChapter;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 大纲章节Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface OutlineChapterMapper extends MPJBaseMapper<OutlineChapter> {

    /**
     * 根据大纲ID查询章节树
     * 
     * @param outlineId 大纲ID
     * @return 章节列表
     */
    @Select("SELECT * FROM tms_outline_chapter " +
            "WHERE outline_id = #{outlineId} " +
            "AND status = '0' " +
            "ORDER BY sort_order ASC, chapter_id ASC")
    List<OutlineChapter> selectChaptersByOutlineId(@Param("outlineId") Long outlineId);

    /**
     * 根据父章节ID查询子章节
     * 
     * @param parentId 父章节ID
     * @return 子章节列表
     */
    @Select("SELECT * FROM tms_outline_chapter " +
            "WHERE parent_id = #{parentId} " +
            "AND status = '0' " +
            "ORDER BY sort_order ASC, chapter_id ASC")
    List<OutlineChapter> selectChaptersByParentId(@Param("parentId") Long parentId);

    /**
     * 检查章节编码在同一大纲下的唯一性
     * 
     * @param outlineId 大纲ID
     * @param chapterCode 章节编码
     * @param chapterId 排除的章节ID（用于编辑时排除自己）
     * @return 符合条件的记录数
     */
    @Select("SELECT COUNT(*) FROM tms_outline_chapter " +
            "WHERE outline_id = #{outlineId} " +
            "AND chapter_code = #{chapterCode} " +
            "AND status = '0' " +
            "AND (#{chapterId} IS NULL OR chapter_id != #{chapterId})")
    int countByOutlineIdAndChapterCode(@Param("outlineId") Long outlineId, 
                                      @Param("chapterCode") String chapterCode,
                                      @Param("chapterId") Long chapterId);

    /**
     * 查询章节的最大排序号
     * 
     * @param outlineId 大纲ID
     * @param parentId 父章节ID
     * @return 最大排序号
     */
    @Select("SELECT COALESCE(MAX(sort_order), 0) FROM tms_outline_chapter " +
            "WHERE outline_id = #{outlineId} " +
            "AND (#{parentId} IS NULL OR parent_id = #{parentId})")
    Integer selectMaxSortOrder(@Param("outlineId") Long outlineId, 
                              @Param("parentId") Long parentId);

    /**
     * 检查是否有子章节
     * 
     * @param chapterId 章节ID
     * @return 子章节数量
     */
    @Select("SELECT COUNT(*) FROM tms_outline_chapter " +
            "WHERE parent_id = #{chapterId} " +
            "AND status = '0'")
    int countChildrenByChapterId(@Param("chapterId") Long chapterId);
}