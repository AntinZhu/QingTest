package com.qingqing.api.teacher.config;

import com.qingqing.common.web.manager.HttpClientManagerV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Collections;
import java.util.Set;

@Configuration
public class CommonManagerConfig {

    private static final Logger logger = LoggerFactory.getLogger(com.qingqing.api.teacher.config.CommonManagerConfig.class);

    public final static String HTTPCLIENT_MANAGER_V2 = "httpClientManagerV2";

    @Bean(name = HTTPCLIENT_MANAGER_V2,initMethod = "closeIdleStart", destroyMethod = "destory")
    @Primary
    public HttpClientManagerV2 httpClientManagerV2() {
        Set<String> excludeProxyHosts = Collections.emptySet();
        HttpClientManagerV2 httpClientManagerV2 = new HttpClientManagerV2(3000, 5000,
                excludeProxyHosts, null, null);
        httpClientManagerV2.setMaxTotal(1023);
        httpClientManagerV2.setIsUseV1(false);
        return httpClientManagerV2;
    }
}
