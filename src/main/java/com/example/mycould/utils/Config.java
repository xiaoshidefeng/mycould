package com.example.mycould.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.TimeUnit;

@Configuration
public class Config extends WebMvcConfigurerAdapter {

    @Value("${config.me.fileroot}")
    public String fileRoot;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/filePath/**").addResourceLocations("file:" + fileRoot);
//        super.addResourceHandlers(registry);
//        registry.addResourceHandler("/filePath/**")
//                .addResourceLocations(fileRoot)
//                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
//        registry.addResourceHandler("/filePath/**")
//                .addResourceLocations("classpath:" + fileRoot);
//        super.addResourceHandlers(registry);
    }
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        configurer.setUseSuffixPatternMatch(false);
    }
}