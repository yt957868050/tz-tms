package com.feian.tms.exam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "exam.anti-cheat")
public class ExamAntiCheatProperties {
    /** 切屏事件阈值，超过则判不通过，0 表示不启用 */
    private Integer maxSwitchEvents = 3;
    /** 截屏/拍照事件阈值 */
    private Integer maxCaptureEvents = 3;
    /** cheat 事件阈值 */
    private Integer maxCheatEvents = 1;
}
