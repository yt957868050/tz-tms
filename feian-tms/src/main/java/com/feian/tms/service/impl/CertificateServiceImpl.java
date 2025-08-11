package com.feian.tms.service.impl;

import com.feian.tms.domain.Certificate;
import com.feian.tms.dto.query.CertificatePageQuery;
import com.feian.tms.dto.request.CertificateRequest;
import com.feian.tms.dto.response.CertificatePageQueryResponse;
import com.feian.tms.dto.response.CertificatePageResponse;
import com.feian.tms.mapper.CertificateMapper;
import com.feian.tms.service.CertificateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 证书管理Service业务层处理
 * 
 * @author feian
 * @date 2025-01-23
 */
@Service
@RequiredArgsConstructor
public class CertificateServiceImpl extends MPJBaseServiceImpl<CertificateMapper, Certificate> implements CertificateService {
    @Autowired
    public CertificateMapper certificateMapper;

    /**
     * 新增证书
     * @param entity
     */
    public void saveCertificate(Certificate entity) {
        certificateMapper.insertCertificate(entity);
    }

    /**
     * 证书分页查询
     * @param certificatePageQuery
     * @return
     */
    public CertificatePageResponse pageQuery(CertificatePageQuery certificatePageQuery) {
        //设置分页页数等
        PageHelper.startPage(certificatePageQuery.getPageNum(), certificatePageQuery.getPageSize());
        // 查询列表
        List<Certificate> certificates = certificateMapper.cerPageQueryList(certificatePageQuery);
        // 将查询的列表转为 分页
        PageInfo<Certificate> pageList = new PageInfo<>(certificates);

        //========

        // 组装返回结果实体
        List<CertificatePageQueryResponse> records = pageList.getList().stream()
                .map(entity -> {
                    CertificatePageQueryResponse dto = new CertificatePageQueryResponse();
                    dto.setCertificateId(entity.getCertificateId());
                    dto.setCertificateNumber(entity.getCertificateCode());
                    dto.setStudentName(entity.getStudentName());
                    dto.setMachineTypeName(entity.getMachineTypeName());
                    dto.setMajorName(entity.getMajorName());
                    dto.setCertificateType(entity.getCertificateType());
                    dto.setIssueDate(entity.getIssueDate());
                    dto.setExpiryDate(entity.getValidUntil());
                    dto.setStatus(entity.getCertificateStatus());
                    dto.setIssuingAuthority(entity.getIssueOrganization());
                    dto.setRemark(entity.getRemark());
                    dto.setStudentId(entity.getStudentId());
                    dto.setMachineTypeId(entity.getMachineTypeId());
                    dto.setMajorId(entity.getMajorId());
                    return dto;
                })
                .collect(Collectors.toList());

        //=======

        // 将 PageInfo 实体和 返回值进行适配


        // 组装返回值
        CertificatePageResponse result = new CertificatePageResponse();
        // 设置结果
        result.setRecords(records);
        //设置总数
        result.setTotal(pageList.getTotal());
        result.setSize(records.size());
        result.setPages(pageList.getPages());
        return result;


    }

    /**
     * 证书批量删除
     * @param id
     * @return
     */
    public boolean deleteBatch(List<Long> id) {
        certificateMapper.deleteBatch(id);

        return true;
    }

    @Override
    public List<Certificate> certificateList() {
        return certificateMapper.certificateList();
    }
}