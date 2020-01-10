package com.qingqing.test.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhujianxing on 2019/1/30.
 */
@Configuration
public class EsConfig {

    @Bean
    public RestClient getClient(@Value("${a}") String value) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        // 如果有多个从节点可以持续在内部new多个HttpHost，参数1是ip,参数2是HTTP端口，参数3是通信协议
        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("backend.es.idc.cedu.cn", 9201, "http"));

        // 添加其他配置，返回来的还是RestClientBuilder对象，这些配置都是可选的
        // clientBuilder.setXX()...

        // 最后配置好的clientBuilder再build一下即可得到真正的Client
        return clientBuilder.build();
    }
}
