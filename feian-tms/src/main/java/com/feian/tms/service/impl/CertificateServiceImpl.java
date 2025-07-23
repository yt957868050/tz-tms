package com.feian.tms.service.impl;

import com.feian.tms.domain.Certificate;
import com.feian.tms.mapper.CertificateMapper;
import com.feian.tms.service.CertificateService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 证书管理Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class CertificateServiceImpl extends MPJBaseServiceImpl<CertificateMapper, Certificate> implements CertificateService {

}