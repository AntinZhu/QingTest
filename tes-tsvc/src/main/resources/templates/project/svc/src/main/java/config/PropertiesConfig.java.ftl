package com.qingqing.api.teacher.config;

import com.qingqing.api.teacher.bean.jiguang.JiGuangAuthGrantBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

    private static final Logger logger = LoggerFactory.getLogger(com.qingqing.api.teacher.config.PropertiesConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "config.jiguang")
    public JiGuangAuthGrantBean jiGuangAuthGrantBean(){
        return new JiGuangAuthGrantBean();
    }
}
