package com.qingqing.test.config.feign;

import com.qingqing.test.feign.PiRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Created by zhujianxing on 2017/11/28.
 */
public class MyPiFeignConfiguration {

    @Bean
    public PiRequestInterceptor piRequestInterceptor(){
        return new PiRequestInterceptor();
    }

//    @Bean
//    public Feign.Builder feignBuilder(MyJsonDecoder jsonDecoder){
//        return Feign.builder()..decoder(jsonDecoder);
//    }

//    @Bean
//    public MyJsonDecoder decoder(){
//        return new MyJsonDecoder();
//    }
//
//    @Bean
//    public QingqingClient qingqingClient(HttpClientManagerV2 httpClientManagerV2){
//        QingqingClient qingqingClient = new QingqingClient();
//        qingqingClient.setHttpClientManagerV2(httpClientManagerV2);
//
//        return qingqingClient;
//    }
}
