package com.qingqing.test.config;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qingqing.common.web.manager.HttpClientManagerV2;
import org.springframework.beans.factory.annotation.Value;
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


    @Bean(initMethod = "closeIdleStart", destroyMethod = "destory")
    public HttpClientManagerV2 httpClientManagerV2(
            @Value("${socks.proxy.domain}") String domain,
            @Value("${socks.proxy.port}") Integer port
    ) {
        HttpClientManagerV2 httpClientManagerV2 = new HttpClientManagerV2(3000, 30000,
                Sets.<String>newHashSet(), domain, port);
        httpClientManagerV2.setMaxTotal(1023);
        httpClientManagerV2.setIsUseV1(false);
        return httpClientManagerV2;
    }
}
