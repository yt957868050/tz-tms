package com.feian.tms.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 组卷请求：默认按 class_hour 抽题，可选手工配额覆盖。
 */
@Data
public class ExamPaperBuildRequest {

    @NotNull(message = "机型不能为空")
    private Long machineTypeId;

    /** 试卷名称，可为空时自动生成 */
    private String name;

    /** 允许的题型 */
    private List<Integer> questionTypes;

    /** 允许的难度 */
    private List<Integer> difficultList;

    /** 分数通过线 */
    private Integer passScore;

    /** 答对题数通过线 */
    private Integer passQuestionNum;

    /** 乱序与防作弊策略 */
    private Boolean shuffleQuestion;
    private Boolean shuffleOption;
    private Boolean antiCheatCheat;
    private Boolean antiCheatWatch;
    private Boolean antiCheatCapture;

    /** 章节/分类及配额，quota 为空则使用 class_hour */
    private List<CategoryQuota> categories;

    @Data
    public static class CategoryQuota {
        @NotNull(message = "章节ID不能为空")
        private Long categoryId;
        private Integer quota;
    }
}
