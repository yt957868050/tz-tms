package com.feian.tms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feian.tms.domain.Courseware;
import com.feian.tms.domain.ScheduleDetail;
import com.feian.tms.domain.ScheduleMain;
import com.feian.tms.domain.TrainingPlan;
import com.feian.tms.dto.request.ScheduleRequest;
import com.feian.tms.dto.response.ScheduleResponse;
import com.feian.tms.dto.scheduleDto.ScheduleCourseDTO;
import com.feian.tms.dto.scheduleDto.ScheduleDayDTO;
import com.feian.tms.mapper.*;
import com.feian.tms.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private static final BigDecimal CLASS_SLOT_MINUTES = new BigDecimal(45);
    private static final int MAX_SLOTS_PER_DAY=7;

    @Autowired
    private CoursewareMapper coursewareMapper;
    @Autowired
    private ScheduleMainMapper scheduleMainMapper;
    @Autowired
    private ScheduleDetailMapper scheduleDetailMapper;
    @Autowired
    private TrainingPlanMapper trainingPlanMapper;
    @Autowired
    private TrainingClassMapper trainingClassMapper;
    /**
     * 理论排课功能
     * @param request
     * @return
     */
    public void autoScheduleTheory(ScheduleRequest request) {
        //新建排课主表ScheduleMain，存入数据库
        ScheduleMain scheduleMain=new ScheduleMain();
        BeanUtils.copyProperties(request,scheduleMain);
        scheduleMainMapper.insert(scheduleMain);

        //获取主表Id
        Long scheduleMainId=scheduleMain.getScheduleMainId();

       //查询符合条件的理论课件列表
        LambdaQueryWrapper<Courseware> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Courseware::getMachineTypeId,request.getMachineTypeId())
                .eq(Courseware::getMajorId,request.getMajorId())
                .eq(Courseware::getTrainingType,"1")//理论课
                .orderByAsc(Courseware::getOrderNum);

        //使用一个LinkedList，方便在处理完一个课件后移除
        List<Courseware> coursewareList=new LinkedList<>(coursewareMapper.selectList(queryWrapper));

        //准备新表，用于存放拆分和分配时段后的课程
        List<Courseware> scheduledCoursewares=new ArrayList<>();

        // 核心排课逻辑：基于剩余时间进行分配
        long currentTimeId=1;
        long currentDayNumber = 1;

        //当前课时槽的剩余时间
        BigDecimal remainingSlotTime=CLASS_SLOT_MINUTES;

        //循环知道所有课件都被排完
        while(!coursewareList.isEmpty()){
            //获取当前要排课的课件
            Courseware currentCourse=coursewareList.get(0);

            //获取当前课件的剩余时长
            BigDecimal courseRemainingMinutes=currentCourse.getTheoryMinutes();

            //循环直到当前课件的所有时长都排完
            while (courseRemainingMinutes.compareTo(BigDecimal.ZERO)>0){
                //计算当前课时槽可容纳的时间
                BigDecimal timeToAssign=courseRemainingMinutes.min(remainingSlotTime);

                //创建一个新对象表示这部分课程
                Courseware scheduledCourse=new Courseware();
                BeanUtils.copyProperties(currentCourse,scheduledCourse);

                // 分配排课天数
                scheduledCourse.setDayNumber(currentDayNumber);

                //分配课程序数和时段
                scheduledCourse.setTimeId(currentTimeId);
                scheduledCourse.setDayTime(currentTimeId >= 1 && currentTimeId <= 3 ? 1L : 2L);

                // 将这部分课程添加到已排课列表中
                scheduledCoursewares.add(scheduledCourse);

                //更新剩余时间
                courseRemainingMinutes = courseRemainingMinutes.subtract(timeToAssign);
                remainingSlotTime=remainingSlotTime.subtract(timeToAssign);

                //如果当前课时槽已满，进入下一个课时槽
                if(remainingSlotTime.compareTo(BigDecimal.ZERO)==0){
                    currentTimeId++;
                    //如果超过7节课，进入新的一天，重置课程序数
                    if(currentTimeId>MAX_SLOTS_PER_DAY){
                        currentTimeId=1;
                        currentDayNumber++;
                    }
                    //重置当前课时槽的剩余时间
                    remainingSlotTime=CLASS_SLOT_MINUTES;
                }

            }
            //当前课件已全部排完，从列表中移除
            coursewareList.remove(0);
        }

        //将scheduledCoursewares映射到ScheduleDetail的列表，并逐一存入数据库
        for(Courseware course:scheduledCoursewares){
            ScheduleDetail detail=new ScheduleDetail();

            //关联主表ID
            detail.setScheduleMainId(scheduleMainId);

            //映射课程ID、教员ID、时间和时间段
            detail.setCoursewareId(course.getCoursewareId());
            detail.setInstructorId(course.getInstructorId());
            detail.setTimeId(course.getTimeId());
            detail.setDayTime(course.getDayTime());

            //直接使用Courseware中已计算好的排课天数和日期
            detail.setDayNumber(course.getDayNumber());
            Date courseDate=new Date(request.getStartDate().getTime() + TimeUnit.DAYS.toMillis(course.getDayNumber() - 1));
            detail.setCoursewareDate(courseDate);

            //将记录存入数据库
            scheduleDetailMapper.insert(detail);
        }


    }

    /**
     * 查询理论课表
     * @param request
     * @return
     */
    public ScheduleResponse getTheorySchedule(ScheduleRequest request) {

        // 查询排课主表，获取主表ID
        LambdaQueryWrapper<ScheduleMain> mainWrapper = new LambdaQueryWrapper<>();
        mainWrapper.eq(ScheduleMain::getPlanId, request.getPlanId())
                .eq(ScheduleMain::getMajorId, request.getMajorId())
                .eq(ScheduleMain::getMachineTypeId, request.getMachineTypeId())
                .eq(ScheduleMain::getCourseType, request.getCourseType())
                .eq(ScheduleMain::getTrainingClassId, request.getTrainingClassId());

        ScheduleMain scheduleMain = scheduleMainMapper.selectOne(mainWrapper);

        // 如果主表记录不存在，表示该课程尚未排课，返回空
        if (scheduleMain == null) {
            return new ScheduleResponse(); // 或返回一个特定的状态码
        }

        //根据主表ID，查询所有排课详情记录
        LambdaQueryWrapper<ScheduleDetail> detailWrapper = new LambdaQueryWrapper<>();
        detailWrapper.eq(ScheduleDetail::getScheduleMainId, scheduleMain.getScheduleMainId())
                .orderByAsc(ScheduleDetail::getDayNumber)
                .orderByAsc(ScheduleDetail::getTimeId);

        List<ScheduleDetail> detailList = scheduleDetailMapper.selectList(detailWrapper);

        // 如果详情记录不存在，也返回空
        if (detailList.isEmpty()) {
            return new ScheduleResponse();
        }

        //构建响应数据：将详情列表按天分组并映射为DTO
        // 使用stream流按天数(dayNumber)分组，Map<天数, 每日课程列表>
        Map<Long, List<ScheduleDetail>> coursesByDay = detailList.stream()
                .collect(Collectors.groupingBy(ScheduleDetail::getDayNumber, LinkedHashMap::new, Collectors.toList()));

        List<ScheduleDayDTO> dayscourses = new ArrayList<>();

        //遍历分组后的Map，构建每日课程DTO列表
        for (Map.Entry<Long, List<ScheduleDetail>> entry : coursesByDay.entrySet()) {
            ScheduleDayDTO dayDto = new ScheduleDayDTO();
            // 计算日期
            Date courseDate = new Date(request.getStartDate().getTime() + TimeUnit.DAYS.toMillis(entry.getKey() - 1));
            dayDto.setDate(courseDate);

            // 将每日的ScheduleDetail列表映射为ScheduleCourseDTO列表
            List<ScheduleCourseDTO> courseDtos = entry.getValue().stream()
                    .map(detail -> {
                        ScheduleCourseDTO courseDto = new ScheduleCourseDTO();
                        BeanUtils.copyProperties(detail, courseDto);

                        // 补充课程名称和教员名称
                        // 这里需要假设可以根据ID查到名称，实际项目中可能需要缓存或更优的联表查询
                        Courseware courseware = coursewareMapper.selectById(detail.getCoursewareId());
                        if (courseware != null) {
                            courseDto.setCourseName(courseware.getCourseName());
                            courseDto.setAtaChapter(courseware.getAtaChapter());
                            courseDto.setInstructorName(courseware.getInstructorName());
                        }


                        return courseDto;
                    })
                    .collect(Collectors.toList());

            dayDto.setDaycourses(courseDtos);
            dayscourses.add(dayDto);
        }

        //  构建并填充最终的响应对象
        ScheduleResponse response = new ScheduleResponse();
        response.setPlanId(request.getPlanId());
        response.setStartDate(request.getStartDate());
        response.setTrainingClassId(request.getTrainingClassId());
        response.setTotalDays(dayscourses.size());
        response.setDayscourses(dayscourses);

        //  补充计划和班级名称等信息
         response.setPlanName(trainingPlanMapper.selectById(request.getPlanId()).getPlanName());
         response.setClassName(trainingClassMapper.selectById(request.getTrainingClassId()).getClassName());

        return response;

    }





}
