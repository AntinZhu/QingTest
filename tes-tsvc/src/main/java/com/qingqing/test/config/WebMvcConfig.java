package com.qingqing.test.config;

import com.qingqing.common.web.protobuf.ProtobufHttpMessageConverter;
import com.qingqing.common.web.protobuf.ProtobufReturnValueHandler;
import com.qingqing.common.web.protobuf.ResponseBuildInteceptor;
import com.qingqing.test.config.feign.exception.FeginExceptionHandler;
import com.qingqing.test.config.inteceptor.CatelogHandlerInteceptor;
import com.qingqing.test.config.inteceptor.EnvHandlerInteceptor;
import com.qingqing.test.config.inteceptor.MyResponseBuildInteceptor;
import com.qingqing.test.spring.interceptor.IpHandlerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;

import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created by zhujianxing on 2018/2/6.
 */
@Configuration
public class WebMvcConfig {

    public static final String PROTO_EXCEPTION_HANDLER_BEAN_NAME = "protoExceptionHandler";

    public final static String MEDIA_TYPES_PROTOBUF = "application/x-protobuf";
    public final static String MEDIA_TYPES_JSON = "application/json";
    public final static String MEDIA_TYPES_XML  = "application/xml";

    public final static Properties mediaTypes = new Properties();
    public final static MediaType DEFAULT_MEDIA_TYPE = MediaType.valueOf(MEDIA_TYPES_JSON);
    static {
        mediaTypes.put("proto", MEDIA_TYPES_PROTOBUF);
        mediaTypes.put("json", MEDIA_TYPES_JSON);
        mediaTypes.put("xml", MEDIA_TYPES_XML);
    }

    @Bean
    public ResponseBuildInteceptor responseBuildInteceptor(){
        return new MyResponseBuildInteceptor();
    }

    @Bean
    public EnvHandlerInteceptor envHandlerInteceptor(){
        return new EnvHandlerInteceptor();
    }

    @Bean
    public IpHandlerInterceptor ipHandlerInterceptor(){
        return new IpHandlerInterceptor();
    }

    @Bean
    public CatelogHandlerInteceptor catelogHandlerInteceptor(){
        return new CatelogHandlerInteceptor();
    }

    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter(){
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    public MarshallingHttpMessageConverter marshallingHttpMessageConverter(){
        return new MarshallingHttpMessageConverter();
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        return new StringHttpMessageConverter(Charset.forName("utf-8"));
    }

    /**HandlerMethodReturnValueHandler*/
    @Bean
    public ProtobufReturnValueHandler protobufReturnValueHandler(ContentNegotiationManager contentNegotiationManager){
        return new ProtobufReturnValueHandler(contentNegotiationManager);
    }

    @ConditionalOnMissingBean(name = PROTO_EXCEPTION_HANDLER_BEAN_NAME)
    @Bean(name = PROTO_EXCEPTION_HANDLER_BEAN_NAME)
    public FeginExceptionHandler protoExceptionHandler(){
        return new FeginExceptionHandler();
    }

    @Bean
    @Primary
    public ContentNegotiationManagerFactoryBean contentNegotiationManager(){
        ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
        contentNegotiationManager.setMediaTypes(mediaTypes);
        contentNegotiationManager.setDefaultContentType(DEFAULT_MEDIA_TYPE);
        contentNegotiationManager.setIgnoreAcceptHeader(false);
        contentNegotiationManager.setFavorPathExtension(true);
        contentNegotiationManager.setFavorParameter(true);

        return contentNegotiationManager;
    }
}