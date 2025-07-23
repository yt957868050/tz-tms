package com.feian.tms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.feian.common.annotation.Excel;
import com.feian.tms.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 课件文件管理对象 tms_courseware_file
 * 
 * @author feian
 * @date 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tms_courseware_file")
public class CoursewareFile extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 课件文件ID */
    @TableId(type = IdType.AUTO)
    private Long coursewareFileId;

    /** 课件ID */
    @Excel(name = "课件ID")
    private Long coursewareId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件类型（1教材 2教案 3PPT 4CBT 5工卡 6其他） */
    @Excel(name = "文件类型", readConverterExp = "1=教材,2=教案,3=PPT,4=CBT,5=工卡,6=其他")
    private String fileType;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件大小(KB) */
    @Excel(name = "文件大小")
    private Long fileSize;

    /** 文件扩展名 */
    @Excel(name = "文件扩展名")
    private String fileExtension;

    /** 文件描述 */
    @Excel(name = "文件描述")
    private String fileDesc;

    /** 下载次数 */
    @Excel(name = "下载次数")
    private Integer downloadCount;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;

    /** 课程名称 */
    private String courseName;
}