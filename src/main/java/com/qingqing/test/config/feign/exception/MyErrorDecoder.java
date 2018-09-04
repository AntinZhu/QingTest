package com.qingqing.test.config.feign.exception;

import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.test.bean.base.SimpleResponse;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by zhujianxing on 2018/8/30.
 */
@Component
public class MyErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        SimpleResponse result;
        String responseValue = "";
        try {
            responseValue = Util.toString(response.body().asReader());
            result = JsonUtil.getObjectFromJson(responseValue, SimpleResponse.class);
        } catch (IOException e) {
            return new QingQingRuntimeException("parse exception, value:" + responseValue);
        }
        switch (response.status()){
            case HttpStatus.SC_UNPROCESSABLE_ENTITY:
                return new RequestValidateException(result.getResponse().getError_message(), result.getResponse().getError_message());
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
