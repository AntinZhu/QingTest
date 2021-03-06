package com.qingqing.test.config.feign.exception;

import com.qingqing.api.proto.v1.ProtoBufResponse;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.StringUtils;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.controller.converter.BaseConverter;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by zhujianxing on 2018/8/30.
 */
@Component
public class MyErrorDecoder implements ErrorDecoder {
    private static final Logger logger = LoggerFactory.getLogger(MyErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        logger.info("status code:" + response.status());
        switch (response.status()){
            case HttpStatus.SC_UNPROCESSABLE_ENTITY:
            case HttpStatus.SC_NOT_FOUND:
                SimpleResponse result = null;
                try {
                    result = genSimpleResponse(response);
                } catch (IOException e) {
                    return new QingQingRuntimeException("parse exception, value:" + response.body().toString());
                }
                if(result != null){
                    String errorMsg = result.getResponse().getError_message();
                    String hintMsg = result.getResponse().getHint_message();
                    if(StringUtils.isEmpty(hintMsg)){
                        hintMsg = errorMsg;
                    }
                    logger.info("errorMsg:{} hintMsg:{}", errorMsg, hintMsg);
                    return new RequestValidateException(errorMsg, hintMsg);
                }
                return new RequestValidateException("", "服务器返回：" + response.status());
            case HttpStatus.SC_FORBIDDEN:
                return new RequestValidateException("forbid request", "服务器返回403,请检查你的参数，请求人，环境是否对应");
            case HttpStatus.SC_UNAUTHORIZED:
                return new RequestValidateException("unauth request", "服务器返回401,请检查请求人及类型，环境是否对应");
            case HttpStatus.SC_SERVICE_UNAVAILABLE:
                return new RequestValidateException("service unavailable", "服务器返回503,估计是有人在重发服务吧");
            default:
                break;
        }

        return FeignException.errorStatus(methodKey, response);
    }

    private SimpleResponse genSimpleResponse(Response response) throws IOException {
        boolean isProto = response.request().headers().get("Content-Type").contains("application/x-protobuf;charset=UTF-8");
        if(isProto){
            ProtoBufResponse.SimpleResponse proto = ProtoBufResponse.SimpleResponse.parseFrom(response.body().asInputStream());
            return new SimpleResponse(BaseConverter.convertBaseResponse(proto.getResponse()));
        }else{
            if(HttpStatus.SC_NOT_FOUND == response.status()){
                return new SimpleResponse(new BaseResponse(1001, "", "服务返回404，请检查接口地址是否配置正确"));
            }

            String responseValue = "";
            if(response.body() != null) {
                responseValue = Util.toString(response.body().asReader());
            }

            if(!StringUtils.isEmpty(responseValue)){
                logger.info("i am 4 debug:" + responseValue);
                return JsonUtil.getObjectFromJson(responseValue, SimpleResponse.class);
            }else{
                logger.info("i am 5 empty body");
                String reason = response.status() + response.reason();
                return new SimpleResponse(new BaseResponse(1001, reason, reason));
            }
        }
    }
}
