package com.feian.tms.exam.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feian.tms.domain.ClassStudent;
import com.feian.tms.domain.TrainingClass;
import com.feian.tms.exam.domain.ExamCandidate;
import com.feian.tms.exam.domain.ExamSession;
import com.feian.tms.exam.dto.ExamSessionPublishRequest;
import com.feian.tms.exam.dto.ExamSessionPublishResponse;
import com.feian.tms.exam.dto.ExamUserStudentMapping;
import com.feian.tms.exam.mapper.ExamUserMapper;
import com.feian.tms.service.ClassStudentService;
import com.feian.tms.service.TrainingClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 考试发布/下发服务：按班级生成考生名单，并更新场次发布状态。
 */
@Service
@RequiredArgsConstructor
public class ExamSessionDispatchService {

    private final ExamSessionService examSessionService;
    private final ExamCandidateService examCandidateService;
    private final TrainingClassService trainingClassService;
    private final ClassStudentService classStudentService;
    private final ExamUserMapper examUserMapper;

    @Transactional(rollbackFor = Exception.class)
    public ExamSessionPublishResponse publish(ExamSessionPublishRequest request) {
        ExamSession session = examSessionService.getById(request.getSessionId());
        if (session == null) {
            throw new IllegalArgumentException("场次不存在");
        }
        if (session.getPublishStatus() != null && session.getPublishStatus() == 2) {
            throw new IllegalArgumentException("场次已发布，发布后不支持补人；如需补考请使用补考功能");
        }
        boolean everPublished = session.getPublishTime() != null;
        List<Long> classIds = request.getClassIds() != null
                ? request.getClassIds().stream().filter(Objects::nonNull).distinct().toList()
                : List.of();
        if (!everPublished && classIds.isEmpty()) {
            throw new IllegalArgumentException("班级ID不能为空");
        }

        Map<Long, Long> studentToClass = new HashMap<>();
        List<Long> allStudentIds = new ArrayList<>();
        List<Long> studentIds = List.of();
        List<Long> userIds = List.of();
        Set<Long> missingStudentIds = Set.of();
        List<ExamCandidate> toInsert = List.of();
        int skipped = 0;
        int totalStudents = 0;

        // 仅首次发布时生成考生名单；撤回后再次发布也不补人（保持名单不变）
        if (!everPublished) {
            for (Long classId : classIds) {
                TrainingClass trainingClass = trainingClassService.getById(classId);
                if (trainingClass == null) {
                    throw new IllegalArgumentException("班级不存在: " + classId);
                }
                if (session.getMachineTypeId() != null
                        && trainingClass.getMachineTypeId() != null
                        && !session.getMachineTypeId().equals(trainingClass.getMachineTypeId())) {
                    throw new IllegalArgumentException("班级机型与考试场次机型不一致（classId=" + classId + "）");
                }
                List<ClassStudent> classStudents = classStudentService.list(new LambdaQueryWrapper<ClassStudent>()
                        .eq(ClassStudent::getTrainingClassId, classId)
                        .ne(ClassStudent::getStudentStatus, "3"));
                for (ClassStudent cs : classStudents) {
                    if (cs.getStudentId() == null) {
                        continue;
                    }
                    studentToClass.putIfAbsent(cs.getStudentId(), classId);
                    allStudentIds.add(cs.getStudentId());
                }
            }

            studentIds = allStudentIds.stream().distinct().toList();
            totalStudents = studentIds.size();
            if (studentIds.isEmpty()) {
                throw new IllegalArgumentException("所选班级暂无学员");
            }

            List<ExamUserStudentMapping> mappings = examUserMapper.selectUserStudentMappings(studentIds);
            Map<Long, Long> studentToUser = mappings.stream()
                    .filter(m -> m.getStudentId() != null && m.getUserId() != null)
                    .collect(Collectors.toMap(ExamUserStudentMapping::getStudentId, ExamUserStudentMapping::getUserId, (a, b) -> a));
            userIds = studentToUser.values().stream().distinct().toList();

            missingStudentIds = new HashSet<>(studentIds);
            missingStudentIds.removeAll(studentToUser.keySet());

            Set<Long> existingUserIds = new HashSet<>();
            if (!CollectionUtils.isEmpty(userIds)) {
                existingUserIds.addAll(examCandidateService.list(new LambdaQueryWrapper<ExamCandidate>()
                                .eq(ExamCandidate::getSessionId, session.getSessionId())
                                .in(ExamCandidate::getUserId, userIds))
                        .stream()
                        .map(ExamCandidate::getUserId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet()));
            }

            List<ExamCandidate> insertList = new ArrayList<>();
            for (Map.Entry<Long, Long> entry : studentToUser.entrySet()) {
                Long studentId = entry.getKey();
                Long userId = entry.getValue();
                if (userId == null) {
                    continue;
                }
                if (existingUserIds.contains(userId)) {
                    skipped++;
                    continue;
                }
                ExamCandidate candidate = new ExamCandidate();
                candidate.setSessionId(session.getSessionId());
                candidate.setUserId(userId);
                candidate.setClassId(studentToClass.get(studentId));
                insertList.add(candidate);
            }
            toInsert = insertList;
            if (!toInsert.isEmpty()) {
                examCandidateService.saveBatch(toInsert);
            }
        }

        // 更新发布状态（发布后不补人）
        session.setPublishStatus(2);
        if (session.getPublishTime() == null) {
            session.setPublishTime(new Date());
        }
        if (session.getRangeType() == null) {
            session.setRangeType(2);
        }
        examSessionService.updateById(session);

        ExamSessionPublishResponse resp = new ExamSessionPublishResponse();
        resp.setSessionId(session.getSessionId());
        resp.setPublishStatus(session.getPublishStatus());
        resp.setPublishTime(session.getPublishTime());
        resp.setClassCount(classIds.size());
        resp.setTotalStudents(totalStudents);
        resp.setMatchedUsers(userIds.size());
        resp.setInsertedCandidates(toInsert.size());
        resp.setSkippedExistingCandidates(skipped);
        resp.setMissingUserCount(missingStudentIds.size());
        resp.setMissingStudentIds(missingStudentIds.stream().limit(50).toList());
        return resp;
    }

    @Transactional(rollbackFor = Exception.class)
    public void unpublish(Long sessionId) {
        ExamSession session = examSessionService.getById(sessionId);
        if (session == null) {
            throw new IllegalArgumentException("场次不存在");
        }
        session.setPublishStatus(3);
        examSessionService.updateById(session);
    }
}
