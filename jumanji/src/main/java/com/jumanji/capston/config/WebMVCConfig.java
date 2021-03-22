package com.jumanji.capston.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        MustacheViewResolver resolver = new MustacheViewResolver();
//        resolver.setCharset("UTF-8");  // 우리가 던져주는 파일은 utf-8이고
//        resolver.setContentType("text/html;charset=UTF-8");  // 던져주는 파일 타입은 html이야.
//        resolver.setPrefix("classpath:/templates/");  // 템플릿에 위치하고
//        resolver.setSuffix(".html");                  // html이야.

//        registry.viewResolver(resolver);
    }
}