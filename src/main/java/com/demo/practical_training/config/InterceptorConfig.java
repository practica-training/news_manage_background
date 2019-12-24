package com.demo.practical_training.config;

import com.demo.practical_training.manage.interceptor.NewsManageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private NewsManageInterceptor newsManageInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(newsManageInterceptor).addPathPatterns("/manage/news/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
