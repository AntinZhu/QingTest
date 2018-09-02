package com.qingqing.test.config.feign;

import com.qingqing.test.config.feign.exception.MyErrorDecoder;
import com.qingqing.test.feign.PtRequestInterceptor;
import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Created by zhujianxing on 2017/11/28.
 */
public class MyPtFeignConfiguration {

    @Bean
    public RequestInterceptor piRequestInterceptor(){
        return new PtRequestInterceptor();
    }

    @Bean
    public Feign.Builder feignBuilder(MyErrorDecoder errorDecoder){
        return Feign.builder().errorDecoder(errorDecoder);
    }
//
//    @Bean
//    public QingqingClient qingqingClient(HttpClientManagerV2 httpClientManagerV2){
//        QingqingClient qingqingClient = new QingqingClient();
//        qingqingClient.setHttpClientManagerV2(httpClientManagerV2);
//
//        return qingqingClient;
//    }
}
