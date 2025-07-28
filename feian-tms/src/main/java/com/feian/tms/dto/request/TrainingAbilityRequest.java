package com.feian.tms.dto.request;

import com.feian.tms.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 培训能力请求对象
 * 
 * @author feian
 * @date 2025-01-24
 */
@Schema(description = "培训能力请求对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class TrainingAbilityRequest extends PageRequest {

    /** 培训能力ID */
    @Schema(description = "培训能力ID")
    private Long trainingAbilityId;

    /** 能力编码 */
    @Schema(description = "能力编码")
    @NotBlank(message = "能力编码不能为空", groups = {Create.class})
    private String abilityCode;

    /** 能力名称 */
    @Schema(description = "能力名称")
    @NotBlank(message = "能力名称不能为空", groups = {Create.class})
    private String abilityName;

    /** 能力描述 */
    @Schema(description = "能力描述")
    private String abilityDesc;

    /** 每日培训时长 */
    @Schema(description = "每日培训时长")
    private BigDecimal dailyHours;

    /** 建议培训天数 */
    @Schema(description = "建议培训天数")
    private Integer totalDays;

    /** 状态（0正常 1停用） */
    @Schema(description = "状态", allowableValues = {"0", "1"})
    private String status;

    // 验证分组接口
    public interface Create {}
    public interface Update {}
}