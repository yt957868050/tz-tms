package com.feian.tms.exam.dto;

import com.feian.tms.exam.domain.ExamCategory;
import lombok.Data;

import java.util.List;

@Data
public class ExamCategoryBranch {
    private String machineType;
    private String majorName;
    private List<ExamCategory> examCategoryList;
}
