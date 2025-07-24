package com.feian.tms.service;

import com.feian.tms.domain.Certificate;
import com.feian.tms.dto.request.CertificateRequest;
import com.github.yulichang.base.MPJBaseService;

/**
 * 证书管理Service接口
 * 
 * @author feian
 * @date 2025-01-23
 */
public interface CertificateService extends MPJBaseService<Certificate> {



    void saveCertificate(Certificate entity);
}