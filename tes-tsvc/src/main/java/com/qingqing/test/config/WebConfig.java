package com.qingqing.test.config;

import com.qingqing.common.web.protobuf.ProtobufHttpMessageConverter;
import com.qingqing.common.web.protobuf.ProtobufReturnValueHandler;
import com.qingqing.common.web.protobuf.ResponseBuildInteceptor;
import com.qingqing.test.config.inteceptor.CatelogHandlerInteceptor;
import com.qingqing.test.config.inteceptor.EnvHandlerInteceptor;
import com.qingqing.test.spring.interceptor.IpHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import java.util.List;

/**
 * Created by zhujianxing on 2017/8/15.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ResponseBuildInteceptor responseBuildInteceptor;
    @Autowired
    private ProtobufReturnValueHandler protobufReturnValueHandler;
    @Autowired
    private ProtobufHttpMessageConverter protobufHttpMessageConverter;
    @Autowired
    private StringHttpMessageConverter stringHttpMessageConverter;
    @Autowired
    private MarshallingHttpMessageConverter marshallingHttpMessageConverter;
    @Autowired
    private EnvHandlerInteceptor envHandlerInteceptor;
    @Autowired
    private CatelogHandlerInteceptor catelogHandlerInteceptor;
    @Autowired
    private IpHandlerInterceptor ipHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseBuildInteceptor).addPathPatterns("/**");
        registry.addInterceptor(envHandlerInteceptor).addPathPatterns("/**");
        registry.addInterceptor(catelogHandlerInteceptor).addPathPatterns("/**");
        registry.addInterceptor(ipHandlerInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(protobufReturnValueHandler);
        super.addReturnValueHandlers(returnValueHandlers);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(protobufReturnValueHandler);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(protobufHttpMessageConverter);
        converters.add(marshallingHttpMessageConverter);
        converters.add(stringHttpMessageConverter);
        super.configureMessageConverters(converters);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("50MB");
        factory.setMaxRequestSize("50MB");

        return factory.createMultipartConfig();


    }
}
