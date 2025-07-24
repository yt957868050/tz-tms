package com.feian.tms.mapper;

import com.feian.tms.domain.Certificate;
import com.feian.tms.dto.request.CertificateRequest;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 证书管理Mapper接口
 * 
 * @author feian
 * @date 2025-01-23
 */
@Mapper
public interface CertificateMapper extends MPJBaseMapper<Certificate> {
    /**
     * 新增证书
     * @param entity
     */
    @Insert("insert into tms_certificate(certificate_description,certificate_code,certificate_type,void_time,issue_date,issue_organization,machine_type_id,major_id,remark,certificate_status,student_id,training_class_id,training_ability_id,create_time,update_time,certificate_name)"+
            " values" +
            " (#{certificateDescription},#{certificateCode},#{certificateType},#{voidTime},#{issueDate},#{issueOrganization},#{machineTypeId},#{majorId},#{remark},#{certificateStatus},#{studentId},#{trainingClassId},#{trainingAbilityId},#{createTime},#{updateTime},#{certificateName})")
    void insertCertificate(Certificate entity);
}