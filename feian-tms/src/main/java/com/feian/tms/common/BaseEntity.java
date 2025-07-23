package com.feian.tms.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseEntity 是一个基础实体类，包含了常见的字段和注解，用于简化数据库表的映射。
 * 该类使用了 MyBatis-Plus 的自动填充功能来自动填充字段值。
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     * 使用 MyBatis-Plus 的 @TableField 注解，自动填充插入时的时间。
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     * 使用 MyBatis-Plus 的 @TableField 注解，自动填充插入或更新时的时间。
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 创建人
     * 使用 MyBatis-Plus 的 @TableField 注解，自动填充插入时的创建人信息。
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新人
     * 使用 MyBatis-Plus 的 @TableField 注解，自动填充插入或更新时的更新人信息。
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 逻辑删除标志
     * 0 表示未删除，1 表示已删除。
     * 使用 MyBatis-Plus 的 @TableLogic 注解实现逻辑删除
     */
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 备注信息
     * 用于记录额外信息或注释。
     */
    private String remark;
}