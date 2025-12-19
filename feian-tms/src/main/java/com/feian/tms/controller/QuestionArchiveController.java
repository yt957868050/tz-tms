package com.feian.tms.controller;

import com.feian.common.utils.PageUtils;
import com.feian.tms.common.PageRequest;
import com.feian.tms.common.R;
import com.feian.tms.domain.Major;
import com.feian.tms.domain.QuestionArchive;
import com.feian.tms.dto.query.MajorQuery;
import com.feian.tms.dto.request.IdsDeleteRequest;
import com.feian.tms.dto.request.MajorRequest;
import com.feian.tms.dto.request.QuestionArchiveRequest;
import com.feian.tms.dto.response.MajorResponse;
import com.feian.tms.dto.response.QuestionArchiveResponse;
import com.feian.tms.service.MachineTypeService;
import com.feian.tms.service.MajorService;
import com.feian.tms.service.QuestionArchiveService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 题目分类管理Controller
 *
 * @author feian
 * @date 2025-010-13
 */
@RestController
@RequestMapping("/api/tms/questionArchive")
@RequiredArgsConstructor
@Tag(name = "题目分类管理", description = "题目分类相关接口")
public class QuestionArchiveController {

    @Autowired
    private QuestionArchiveService questionArchiveService;
    @Autowired
    private MachineTypeService machineTypeService;
    @Autowired
    private MajorService majorService;

    /**
     * 题目分类树形
     * @return
     */
    @PostMapping("/tree")
    @Operation(summary = "查询题目分类树结构", description = "直接查询题目分类树结构")
    public R<List<QuestionArchive>> tree() {
        List<QuestionArchive> questionArchiveRoot = questionArchiveService.getRootQuestionArchive();
        questionArchiveRecursion(questionArchiveRoot);
        return R.success(questionArchiveRoot);
    }

    /**
     * 构建子分支
     * @param questionArchiveList
     */
    private void questionArchiveRecursion(List<QuestionArchive> questionArchiveList) {
        questionArchiveList.forEach(item -> {
            List<QuestionArchive> questionArchiveChild = questionArchiveService.getQuestionArchiveByParentId(item.getId());
            if (!questionArchiveChild.isEmpty()) {
                item.setChildren(questionArchiveChild);
                questionArchiveRecursion(questionArchiveChild);
            }
        });
    }

    /**
     * 新建题目分类结构树节点
     * @param questionArchiveRequest
     * @return
     */
    @PostMapping("/create")
    @Operation(summary = "新建节点", description = "创建新的题目分类结构树节点")
    public R<QuestionArchive> create(@RequestBody QuestionArchiveRequest questionArchiveRequest) {
        QuestionArchive questionArchive = new QuestionArchive();
        BeanUtils.copyProperties(questionArchiveRequest, questionArchive);
        if (questionArchive.getParentId()!=null) {
            questionArchive.setLevel(String.format("/%s/", questionArchive.getName()));
        } else {
            QuestionArchive parentDepartment = questionArchiveService.getById(questionArchive.getParentId());
            questionArchive.setLevel(String.format("%s%s/", parentDepartment.getLevel(), questionArchive.getName()));
            if(parentDepartment.getParentId()!=null){
                String Level = questionArchive.getLevel();
                String[] parts = Level.split("/");
                questionArchive.setMachineTypeId(machineTypeService.getIdByName(parts[1]));
                questionArchive.setMajorId(majorService.getIdByName(parts[2]));
            }
        }

        boolean save = questionArchiveService.save(questionArchive);
        if (save) {
            return R.success(questionArchive);
        }
        return R.fail("创建失败");
    }


    /**
     * 修改题目分类结构树节点信息
     * @param request
     * @return
     */
    @PostMapping("/update")
    @Operation(summary = "修改节点", description = "更新现有节点信息")
    public R<QuestionArchiveResponse> update(@Valid @RequestBody QuestionArchiveRequest request){
        if (request.getMajorId() == null) {
            return R.fail("专业ID不能为空");
        }
        QuestionArchive questionArchive = new QuestionArchive();
        BeanUtils.copyProperties(request, questionArchive);
        boolean update = questionArchiveService.updateById(questionArchive);
        if (update) {
            QuestionArchiveResponse questionArchiveResponse = new QuestionArchiveResponse();
            BeanUtils.copyProperties(questionArchive, questionArchiveResponse);
            return R.success(questionArchiveResponse);
        }
        return R.fail("更新失败");

    }

    /**
     * 删除题目分类结构树节点
     * @param idsDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    @Operation(summary = "删除节点", description = "删除题目分类结构树节点")
    public R<String> delete(@Valid @RequestBody IdsDeleteRequest idsDeleteRequest) {
        boolean delete = questionArchiveService.removeByIds(idsDeleteRequest.getIdList());
        if (delete) {
            return R.success("删除成功");
        }
        return R.fail("删除失败");
    }








}
