package com.qingqing.test.config;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Configuration
public class OtherConfiguration {

    @Bean
    public Map<String, String> inspectMap(){
        return Maps.newHashMap();
    }
}
