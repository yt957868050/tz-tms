package com.feian.tms.exam.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExamCategoryTree {
    private List<ExamCategoryBranch> examCategoryBranchList = new ArrayList<>();

    // getter and setter methods
    public List<ExamCategoryBranch> getExamCategoryBranchList() {
        return examCategoryBranchList;
    }

    public void setExamCategoryBranchList(List<ExamCategoryBranch> examCategoryBranchList) {
        this.examCategoryBranchList = examCategoryBranchList;
    }

}
