package com.feian.tms.service;

import com.feian.tms.domain.Certificate;
import com.feian.tms.dto.query.CertificatePageQuery;
import com.feian.tms.dto.request.CertificateRequest;
import com.feian.tms.dto.response.CertificatePageResponse;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 证书管理Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface CertificateService extends MPJBaseService<Certificate> {


//    /**
//     * 新增证书
//     * @param entity
//     */
//    void saveCertificate(Certificate entity);

    /**
     * 证书分页查询
     * @param certificatePageQuery
     * @return
     */
    CertificatePageResponse pageQuery(CertificatePageQuery certificatePageQuery);

    /**
     * 证书批量删除
     * @param id
     * @return
     */
    boolean deleteBatch(List<Long> id);

    List<Certificate> certificateList();
}