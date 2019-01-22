package com.qingqing.test.config;

import com.qingqing.test.bean.common.Env;
import com.qingqing.test.manager.OrderManager;
import com.qingqing.test.spring.QingConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by zhujianxing on 2019/1/10.
 */
@Configuration
public class AutoConfigTest {

    @Bean(name = "devEnv")
    public Env aEnv(){
        return Env.dev;
    }

    @Bean(name = {"tstEnv", "sssss"})
//    @Bean
    public Env bEnv(){
        return Env.tst;
    }

    @Bean
    @QingConditionalOnBean(value = {AutoConfigTest.class, OrderManager.class}, name = "sssss")
    @ConditionalOnProperty(name = {"a.b", "c.d"})
    public Boolean aaa(List<Env> evnList){
        return Boolean.TRUE;
    }
}
