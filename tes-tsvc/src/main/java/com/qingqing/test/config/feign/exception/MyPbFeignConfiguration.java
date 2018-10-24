package com.qingqing.test.config.feign.exception;

import com.qingqing.test.feign.client.EnvClient;
import feign.Feign;
import org.springframework.context.annotation.Bean;

/**
 * Created by zhujianxing on 2017/11/28.
 */
public class MyPbFeignConfiguration {

    @Bean
    public Feign.Builder feignBuilder(MyErrorDecoder errorDecoder, EnvClient client){
        return Feign.builder().errorDecoder(errorDecoder).client(client);
    }
}
