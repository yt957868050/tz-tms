package com.feian.tms.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class TmsWebMvcConfig implements WebMvcConfigurer {

    private final MachineTypeContextInterceptor machineTypeContextInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(machineTypeContextInterceptor).addPathPatterns("/**");
    }
}

