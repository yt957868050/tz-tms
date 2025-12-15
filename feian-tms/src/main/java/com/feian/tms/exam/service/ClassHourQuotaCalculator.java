package com.feian.tms.exam.service;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 按 class_hour 默认抽题并处理缺口补偿。
 */
@Component
public class ClassHourQuotaCalculator {

    /**
     * @param desired   每个章节期望抽题数（class_hour 或手工配额）
     * @param available 每个章节可抽题数
     * @return 最终配额，缺口会用其它章节的富余量补齐
     */
    public Map<Long, Integer> allocate(Map<Long, Integer> desired, Map<Long, Integer> available) {
        Map<Long, Integer> allocation = new LinkedHashMap<>();
        Map<Long, Integer> spare = new LinkedHashMap<>();
        final int[] shortage = {0};

        desired.forEach((categoryId, expect) -> {
            int need = Math.max(0, Objects.requireNonNullElse(expect, 0));
            int able = Math.max(0, Objects.requireNonNullElse(available.get(categoryId), 0));
            int take = Math.min(need, able);
            allocation.put(categoryId, take);
            spare.put(categoryId, Math.max(0, able - take));
            shortage[0] += Math.max(0, need - able);
        });

        if (shortage[0] <= 0) {
            return allocation;
        }

        // 将缺口按富余量从大到小分摊
        List<Map.Entry<Long, Integer>> spareList = spare.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        int remaining = shortage[0];
        for (Map.Entry<Long, Integer> entry : spareList) {
            if (remaining <= 0) {
                break;
            }
            int canUse = Math.min(entry.getValue(), remaining);
            if (canUse > 0) {
                Long categoryId = entry.getKey();
                allocation.put(categoryId, allocation.get(categoryId) + canUse);
                remaining -= canUse;
            }
        }
        return allocation;
    }
}
