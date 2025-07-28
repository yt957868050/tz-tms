package com.feian.tms.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MyBatis-Plus 自动填充处理器
 * 用于自动填充创建时间、更新时间、创建人、更新人等字段
 * 
 * @author feian
 * @date 2025-01-23
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MyMetaObjectHandler.class);

    /**
     * 插入时自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始插入填充...");
        
        // 自动填充创建时间
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        
        // 自动填充更新时间
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        
        // 自动填充创建人（这里可以从安全上下文获取当前用户）
        this.strictInsertFill(metaObject, "createBy", String.class, getCurrentUser());
        
        // 自动填充更新人
        this.strictInsertFill(metaObject, "updateBy", String.class, getCurrentUser());
        
        // 自动填充逻辑删除标志
        this.strictInsertFill(metaObject, "isDeleted", Integer.class, 0);
    }

    /**
     * 更新时自动填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始更新填充...");
        
        // 自动填充更新时间
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        
        // 自动填充更新人（这里可以从安全上下文获取当前用户）
        this.strictUpdateFill(metaObject, "updateBy", String.class, getCurrentUser());
    }

    /**
     * 获取当前用户
     * TODO: 这里需要根据实际的安全框架来获取当前登录用户
     */
    private String getCurrentUser() {
        // 这里可以集成 Spring Security 或其他安全框架来获取当前用户
        // 暂时返回默认值
        try {
            // 可以通过 SecurityContextHolder 获取当前用户
            // SecurityContext context = SecurityContextHolder.getContext();
            // Authentication authentication = context.getAuthentication();
            // if (authentication != null && authentication.isAuthenticated()) {
            //     return authentication.getName();
            // }
            return "system";
        } catch (Exception e) {
            log.warn("获取当前用户失败，使用默认用户: {}", e.getMessage());
            return "system";
        }
    }
}