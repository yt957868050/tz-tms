package com.feian.tms.controller;

import com.feian.common.utils.PageUtils;
import com.feian.tms.domain.Instructor;
import com.feian.tms.dto.request.IdsDeleteRequest;
import com.feian.tms.dto.response.ClassStudentResponse;
import com.feian.tms.dto.response.InstructorResponse;
import com.github.pagehelper.PageInfo;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.tms.domain.Courseware;
import com.feian.tms.dto.request.CoursewareRequest;
import com.feian.tms.dto.request.IdRequest;
import com.feian.tms.dto.response.CoursewareResponse;
import com.feian.tms.service.CoursewareService;
import com.feian.tms.service.CoursewareFileService;
import com.feian.tms.domain.CoursewareFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 课件管理Controller
 * 
 * @author feian
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/api/tms/courseware")
@RequiredArgsConstructor
@Tag(name = "课件管理", description = "课件管理相关接口")
public class CoursewareController {
    
    private final CoursewareService coursewareService;
    private final CoursewareFileService coursewareFileService;

    /**
     * 查询课件管理列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询课件管理列表", description = "根据查询条件分页查询课件列表")
    public R<PageInfo<CoursewareResponse>> list(@RequestBody PageRequest<CoursewareRequest> pageRequest) {


        // 使用service的新方法，包含关联查询
        PageInfo<CoursewareResponse> result = coursewareService.selectCoursewareList(pageRequest);
        

        // 返回分页信息
        return R.success(result);
    }

    /**
     * 获取课件管理详细信息
     */
    @PostMapping("/detail")
    @Operation(summary = "获取课件详细信息", description = "根据ID获取课件详细信息")
    public R<CoursewareResponse> detail(@Valid @RequestBody IdRequest request) {
        CoursewareResponse response = coursewareService.selectCoursewareById(request.getId());
        if (response == null) {
            return R.fail("课件信息不存在");
        }
        
        // 查询关联的文件
        var files = coursewareFileService.lambdaQuery()
                .eq(CoursewareFile::getCoursewareId, response.getCoursewareId())
                .eq(CoursewareFile::getStatus, "0")
                .orderByAsc(CoursewareFile::getOrderNum)
                .list();
        response.setFiles(files);
        
        return R.success(response);
    }

    /**
     * 新增课件管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增课件", description = "创建新的课件信息")
    public R<CoursewareResponse> create(@Valid @RequestBody CoursewareRequest request) {
        Courseware entity = new Courseware();
        BeanUtils.copyProperties(request, entity);
        boolean result = coursewareService.save(entity);
        entity.setCoursewareId(coursewareService.getCoursewareIdBycourse_code(request.getCourseCode()));
        request.getFiles().forEach(file -> file.setCoursewareId(entity.getCoursewareId()));
        coursewareFileService.saveBatch(request.getFiles());

        if (result) {
            CoursewareResponse response = new CoursewareResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("新增课件失败");
    }

    /**
     * 修改课件管理
     */
    @PostMapping("/update")
    @Operation(summary = "修改课件", description = "更新现有课件信息")
    public R<CoursewareResponse> update(@Valid @RequestBody CoursewareRequest request) {
        if (request.getCoursewareId() == null) {
            return R.fail("课件ID不能为空");
        }
        Courseware entity = new Courseware();
        BeanUtils.copyProperties(request, entity);
        boolean result = coursewareService.updateById(entity);
        entity.setCoursewareId(coursewareService.getCoursewareIdBycourse_code(request.getCourseCode()));
        request.getFiles().forEach(file -> file.setCoursewareId(entity.getCoursewareId()));
        int TypeOne=0;
        int TypeTwo=0;
        int TypeThree=0;
        int TypeFour=0;
        int TypeFive=0;
        int TypeSeven=0;
        for(CoursewareFile file : request.getFiles()) {
           if(file.getCoursewareFileId() == null) {
               coursewareFileService.save(file);
               switch (file.getFileType()){
                   case "1":
                       TypeOne=1;
                       break;
                   case "2":
                       TypeTwo=1;
                       break;
                   case "3":
                       TypeThree=1;
                       break;
                   case "4":
                       TypeFour=1;
                   case "5":
                       TypeFive=1;
                   case "7":
                       TypeSeven=1;
               }
           } else {
               coursewareFileService.removeById(file.getCoursewareFileId());
           }
        }
        for(CoursewareFile file : request.getFiles()){
            if(file.getCoursewareFileId() != null){
                if(Objects.equals(file.getFileType(), "1") &&TypeOne==0){
                    file.setCoursewareFileId(null);
                    coursewareFileService.save(file);
                }
                if(Objects.equals(file.getFileType(), "2") &&TypeTwo==0){
                    file.setCoursewareFileId(null);
                    coursewareFileService.save(file);
                }
                if(Objects.equals(file.getFileType(), "3") &&TypeThree==0){
                    file.setCoursewareFileId(null);
                    coursewareFileService.save(file);
                }
                if(Objects.equals(file.getFileType(), "4") &&TypeFour==0){
                    file.setCoursewareFileId(null);
                    coursewareFileService.save(file);
                }
                if(Objects.equals(file.getFileType(), "5") &&TypeFive==0){
                    file.setCoursewareFileId(null);
                    coursewareFileService.save(file);
                }
                if(Objects.equals(file.getFileType(), "7") &&TypeSeven==0){
                    file.setCoursewareFileId(null);
                    coursewareFileService.save(file);
                }
            }
            else{
                break;
            }
        }

            if (result) {
            CoursewareResponse response = new CoursewareResponse();
            BeanUtils.copyProperties(entity, response);
            return R.success(response);
        }
        return R.fail("更新课件失败");
    }

    /**
     * 删除课件管理
     */
    @PostMapping("/delete")
    @Operation(summary = "删除课件", description = "根据ID删除课件信息")
    public R<Void> delete(@Valid @RequestBody IdsDeleteRequest idsDeleteRequest) {
        boolean result = coursewareService.removeByIds(idsDeleteRequest.getIdList());
        coursewareFileService.removeByCoursewareIds(idsDeleteRequest.getIdList());
        if (result) {
            return R.success();
        }
        return R.fail("删除课件失败");
    }
}