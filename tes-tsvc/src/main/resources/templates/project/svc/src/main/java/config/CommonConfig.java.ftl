package com.qingqing.api.teacher.config;

import com.qingqing.api.teacher.aspect.mock.QingMockAspect;
import com.qingqing.common.web.aspect.log.GuidAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    private static final Logger logger = LoggerFactory.getLogger(com.qingqing.api.teacher.config.CommonConfig.class);

    @Bean
    public GuidAspect guidAspect(){
        return new GuidAspect();
    }

    @Bean
    @ConditionalOnProperty(value = "config.common.mock.enable", havingValue = "true", matchIfMissing = false)
    public QingMockAspect qingMockAspect(){
        logger.info("init QingMockAspect");
        return new QingMockAspect();
    }
}
