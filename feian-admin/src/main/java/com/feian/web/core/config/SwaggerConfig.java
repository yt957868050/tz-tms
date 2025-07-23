package com.feian.web.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.feian.common.config.RuoYiConfig;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;

/**
 * Swagger2的接口配置
 * 
 * @author ruoyi
 */
@Configuration
public class SwaggerConfig
{
    /** 系统基础配置 */
    @Autowired
    private RuoYiConfig ruoyiConfig;
    
    /**
     * 自定义的 OpenAPI 对象
     */
    @Bean
    public OpenAPI customOpenApi()
    {
        return new OpenAPI().components(new Components()
            // 设置认证的请求头
            .addSecuritySchemes("apikey", securityScheme()))
            .addSecurityItem(new SecurityRequirement().addList("apikey"))
            .info(getApiInfo());
    }
    
    @Bean
    public SecurityScheme securityScheme()
    {
        return new SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .name("Authorization")
            .in(SecurityScheme.In.HEADER)
            .scheme("Bearer");
    }
    
    /**
     * 系统管理模块API分组
     */
    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
            .group("01-系统管理")
            .pathsToMatch("/system/**", "/monitor/**", "/tool/**")
            .packagesToScan("com.feian.web.controller")
            .build();
    }

    /**
     * TMS培训管理模块API分组
     */
    @Bean
    public GroupedOpenApi tmsApi() {
        return GroupedOpenApi.builder()
            .group("02-TMS培训管理")
            .pathsToMatch("/api/tms/**")
            .packagesToScan("com.feian.tms.controller")
            .build();
    }

    /**
     * 所有API分组（用于调试）
     */
    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
            .group("00-所有接口")
            .pathsToMatch("/**")
            .packagesToScan("com.feian")
            .build();
    }

    /**
     * 添加摘要信息
     */
    public Info getApiInfo()
    {
        return new Info()
            // 设置标题
            .title("飞安直升机培训管理系统 API 文档")
            // 描述
            .description("直升机培训管理系统，包括课件管理、培训计划、学员管理、证书管理等模块")
            // 作者信息
            .contact(new Contact().name(ruoyiConfig.getName()).email("support@feian.com"))
            // 版本
            .version("v" + ruoyiConfig.getVersion());
    }
}
