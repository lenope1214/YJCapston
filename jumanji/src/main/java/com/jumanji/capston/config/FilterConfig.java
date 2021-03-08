package com.jumanji.capston.config;

import com.jumanji.capston.config.Filter.MyFilter1;
import com.jumanji.capston.config.Filter.MyFilter2;
import com.jumanji.capston.config.Filter.MyFilter3;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter1> filter1(){
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(2); // 우선순위 설정. 낮은 번호가 필터에서 먼저 실행.
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> filter2(){
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(1); // 우선순위 설정. 낮은 번호가 필터에서 먼저 실행.
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter3> filter3(){
        FilterRegistrationBean<MyFilter3> bean = new FilterRegistrationBean<>(new MyFilter3());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 우선순위 설정. 낮은 번호가 필터에서 먼저 실행.
        return bean;
    }

}
