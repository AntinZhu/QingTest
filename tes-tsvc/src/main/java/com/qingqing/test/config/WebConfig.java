package com.qingqing.test.config;

import com.qingqing.common.web.protobuf.ProtobufHttpMessageConverter;
import com.qingqing.common.web.protobuf.ProtobufReturnValueHandler;
import com.qingqing.common.web.protobuf.ResponseBuildInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseBuildInteceptor).addPathPatterns("/**");
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
}
