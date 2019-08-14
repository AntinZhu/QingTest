package com.qingqing.test.config;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qingqing.common.web.manager.HttpClientManagerV2;
import com.qingqing.test.aspect.externalsystem.QingExternalSystemFusingAspect;
import com.qingqing.test.aspect.qingswitch.aspect.AnnotationSwitchAspect;
import com.qingqing.test.aspect.qingswitch.determiner.ISwitchKeyDeterminer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
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

    @Bean
    public ISwitchKeyDeterminer falseISwitchKeyDeterminer(){
        return new ISwitchKeyDeterminer() {
            @Override
            public boolean isOn(String switchKey, boolean defaultValue) {
                return false;
            }

            @Override
            public boolean isSupport(String switchKey) {
                return "false".equals(switchKey);
            }

            @Override
            public String hintMessage() {
                return "";
            }

            @Override
            public int getOrder() {
                return 0;
            }
        };
    }

    @Bean
    public AnnotationSwitchAspect switchBean(@Autowired(required = false) List<ISwitchKeyDeterminer> switchKeyDeterminerList){
        return new AnnotationSwitchAspect(switchKeyDeterminerList);
    }

    @Bean
    public QingExternalSystemFusingAspect externalSystemFusingAspect(){
        return new QingExternalSystemFusingAspect();
    }
}
