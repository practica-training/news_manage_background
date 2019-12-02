package com.demo.practical_training.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <Description> <br>
 *
 * @author luoluocaihong<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate Jul 2, 2017 <br>
 * @since V8.0<br>
 * @see com.demo.practical_training <br>
 */
//@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
