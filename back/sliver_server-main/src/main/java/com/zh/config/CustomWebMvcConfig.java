package com.zh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {

    @Value("${custom.static.resource.path1}")
    private String customStaticResourcePath1;

    @Value("${custom.static.resource.path2}")
    private String customStaticResourcePath2;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/post/images/**")
                .addResourceLocations(customStaticResourcePath1);

        registry.addResourceHandler("/user/headPortrait/**")
                .addResourceLocations(customStaticResourcePath2);
    }

}
