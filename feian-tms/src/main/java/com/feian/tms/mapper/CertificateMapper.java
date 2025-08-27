package com.feian.tms.mapper;

import com.feian.tms.domain.Certificate;
import com.feian.tms.dto.query.CertificatePageQuery;
import com.feian.tms.dto.request.CertificateRequest;
import com.feian.tms.dto.response.CertificatePageResponse;
import com.github.pagehelper.Page;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 证书管理Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface CertificateMapper extends MPJBaseMapper<Certificate> {
//    /**
//     * 新增证书
//     * @param entity
//     */
//    @Insert("insert into tms_certificate(certificate_code, student_id, student_code, student_name, e_student_name, training_course, e_training_course, start_date, end_date, total_hours, theory_hours, practice_hours)"+
//            " values" +
//            " (#{certificateCode},#{studentId},#{studentCode},#{studentName},#{eStudentName},#{trainingCourse},#{eTrainingCourse},#{},#{remark},#{certificateStatus},#{studentId},#{trainingClassId},#{trainingAbilityId},#{createTime},#{updateTime},#{certificateName},#{studentName},#{machineTypeName},#{majorName})")
//    void insertCertificate(Certificate entity);


    /**
     * 证书批量查询
     * @param certificatePageQuery
     * @return
     */

    List<Certificate> cerPageQueryList(CertificatePageQuery.Query certificatePageQuery);

    /**
     * 证书批量删除
     * @param id
     */
    void deleteBatch(List<Long> id);
    @Select("select * from tms_certificate where is_deleted=0")
    List<Certificate> certificateList();
}