package com.demo.practical_training.config;

import com.demo.practical_training.manage.interceptor.NewsManageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private NewsManageInterceptor newsManageInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(newsManageInterceptor).addPathPatterns("/manage/**");
        super.addInterceptors(registry);
    }

}
