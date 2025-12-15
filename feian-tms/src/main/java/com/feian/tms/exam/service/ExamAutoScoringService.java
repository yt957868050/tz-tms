package com.feian.tms.exam.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 简单自动判分工具：
 *  - 支持从题目内容 JSON 中提取正确答案（常见键：answer/answers/rightAnswer/correctAnswer/correct）
 *  - 支持从用户作答 JSON 中提取用户答案（同名键或纯字符串）
 *  - 比较时忽略顺序与空白，完全匹配则得分，否则 0 分
 * 说明：
 *  - 内容格式未知时返回 null，调用方可回落到用户上传的得分/正确标记或人工批改
 */
@Component
public class ExamAutoScoringService {

    public ScoreResult autoScore(String contentJson, String answerJson, Integer defaultScore) {
        Set<String> correctSet = extract(contentJson);
        Set<String> userSet = extract(answerJson);
        if (CollectionUtils.isEmpty(correctSet) || CollectionUtils.isEmpty(userSet)) {
            return null;
        }
        boolean match = correctSet.equals(userSet);
        ScoreResult result = new ScoreResult();
        result.setCorrectFlag(match ? 1 : 0);
        result.setScore(match ? (defaultScore != null ? defaultScore : 0) : 0);
        return result;
    }

    private Set<String> extract(String jsonOrString) {
        if (!StringUtils.hasText(jsonOrString)) {
            return Collections.emptySet();
        }
        String trimmed = jsonOrString.trim();
        // 如果不是 JSON 对象/数组，直接当作单值
        if (!(trimmed.startsWith("{") || trimmed.startsWith("["))) {
            return normalizeSingle(trimmed);
        }
        try {
            // 优先解析对象
            if (trimmed.startsWith("{")) {
                JSONObject obj = JSON.parseObject(trimmed);
                // 常见键集合
                String[] keys = {"answer", "answers", "rightAnswer", "correctAnswer", "correct"};
                for (String key : keys) {
                    if (obj.containsKey(key)) {
                        Object val = obj.get(key);
                        return normalize(val);
                    }
                }
            }
            // 再尝试数组
            if (trimmed.startsWith("[")) {
                JSONArray arr = JSON.parseArray(trimmed);
                return normalize(arr);
            }
        } catch (Exception ignore) {
            // 不可解析时忽略
        }
        return normalizeSingle(trimmed);
    }

    private Set<String> normalize(Object val) {
        if (val == null) {
            return Collections.emptySet();
        }
        if (val instanceof JSONArray arr) {
            List<String> list = arr.stream().map(String::valueOf).toList();
            return normalize(list);
        }
        if (val instanceof List<?> list) {
            return normalize(list);
        }
        return normalizeSingle(String.valueOf(val));
    }

    private Set<String> normalize(List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptySet();
        }
        return list.stream()
                .map(String::valueOf)
                .map(this::clean)
                .filter(StringUtils::hasText)
                .collect(Collectors.toCollection(HashSet::new));
    }

    private Set<String> normalizeSingle(String s) {
        if (!StringUtils.hasText(s)) {
            return Collections.emptySet();
        }
        // 支持逗号/分号分隔的复选答案
        String[] parts = s.split("[,;]");
        Set<String> set = new HashSet<>();
        for (String p : parts) {
            String c = clean(p);
            if (StringUtils.hasText(c)) {
                set.add(c);
            }
        }
        return set;
    }

    private String clean(String s) {
        return s == null ? null : s.trim();
    }

    public static class ScoreResult {
        private Integer score;
        private Integer correctFlag;

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public Integer getCorrectFlag() {
            return correctFlag;
        }

        public void setCorrectFlag(Integer correctFlag) {
            this.correctFlag = correctFlag;
        }
    }
}
