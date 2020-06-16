package com.qingqing.test.config;

import com.qingqing.test.aspect.mock.QingMockAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhujianxing on 2019/10/18.
 */
@Configuration
public class AspectConfig {

//    @Bean
//    @ConditionalOnProperty(value = "a.b", havingValue="2", matchIfMissing = false)
//    public QingMockAspect qingMockAspect(){
//        return new QingMockAspect();
//    }
}
