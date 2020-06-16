package com.qingqing.test.config;

import com.qingqing.test.spring.filter.IpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangzirong on 12/6/2016.
 */
@Configuration
public class WebFilterConfig {
    @Autowired
    private IpFilter ipFilter;

//    @Bean
//    public FilterRegistrationBean setCharacterEncodingFilterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setName("ipFilter");
//        registrationBean.setFilter(ipFilter);
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(10);
//        return registrationBean;
//    }
}