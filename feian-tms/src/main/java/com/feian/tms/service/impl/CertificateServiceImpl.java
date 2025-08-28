package com.feian.tms.service.impl;

import com.feian.tms.domain.Certificate;
import com.feian.tms.dto.query.CertificatePageQuery;
import com.feian.tms.dto.request.CertificateRequest;
import com.feian.tms.dto.response.CertificatePageQueryResponse;
import com.feian.tms.dto.response.CertificatePageResponse;
import com.feian.tms.dto.response.CertificateResponse;
import com.feian.tms.mapper.CertificateMapper;
import com.feian.tms.mapper.ClassStudentMapper;
import com.feian.tms.mapper.TrainingPlanMapper;
import com.feian.tms.mapper.TrainingRecordMapper;
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
    @Autowired
    public ClassStudentMapper classStudentMapper;
    @Autowired
    public TrainingPlanMapper trainingPlanMapper;
    @Autowired
    private TrainingRecordMapper trainingRecordMapper;

//    /**
//     * 新增证书
//     * @param entity
//     */
//    public void saveCertificate(Certificate entity) {
//        certificateMapper.insertCertificate(entity);
//    }

    /**
     * 证书分页查询
     * @param certificatePageQuery
     * @return
     */
    public CertificatePageResponse pageQuery(CertificatePageQuery certificatePageQuery) {
        //设置分页页数等
        PageHelper.startPage(certificatePageQuery.getPageNum(), certificatePageQuery.getPageSize());
        // 查询列表
        List<Certificate> certificates = certificateMapper.cerPageQueryList(certificatePageQuery.getQuery());
        // 将查询的列表转为 分页
        PageInfo<Certificate> pageList = new PageInfo<>(certificates);

        //========

        // 组装返回结果实体
        List<CertificateResponse> records = pageList.getList().stream()
                .map(entity -> {
                    CertificateResponse dto = new CertificateResponse();
                    Long classId=classStudentMapper.getClassIdByStudent(entity.getStudentId());
                    Long planId=trainingPlanMapper.getPlanIdByClass(classId);
                    dto.setCertificateId(entity.getCertificateId());
                    dto.setCertificateCode(entity.getCertificateCode());
                    dto.setStudentId(entity.getStudentId());
                    dto.setStudentCode(entity.getStudentCode());
                    dto.setStudentName(entity.getStudentName());
                    dto.setEngStudentName(entity.getEngStudentName());
                    dto.setTrainingCourse(entity.getTrainingCourse());
                    dto.setEngTrainingCourse(entity.getEngTrainingCourse());
                    dto.setStartDate(entity.getStartDate());
                    dto.setEndDate(entity.getEndDate());
                    dto.setTotalHours(trainingPlanMapper.getTotalHoursById(planId));
                    dto.setTheoryHours(trainingPlanMapper.getTheoryHoursById(planId));
                    dto.setPracticeHours(trainingPlanMapper.getPracticeHoursById(planId));
                    dto.setTotalScore(trainingRecordMapper.getTotalScoreByStuId(entity.getStudentId()));
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