package com.qingqing.test.config;

import com.qingqing.test.spring.filter.IpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangzirong on 12/6/2016.
 */
@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean setCharacterEncodingFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("setCharacterEncodingFilter");
        IpFilter greetingFilter = new IpFilter();
        registrationBean.setFilter(greetingFilter);
        registrationBean.addUrlPatterns("/*");  // 原始 /apns/*
        registrationBean.setOrder(10);
        return registrationBean;
    }
}