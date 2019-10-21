package com.qingqing.test.config.feign.exception;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessage;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.web.protobuf.ProtoExceptionHandler;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.common.web.protobuf.ResponseBuildInteceptor;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.InterfaceBaseResponse;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.config.inteceptor.MyResponseBuildInteceptor;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhujianxing on 2018/8/30.
 */
@ControllerAdvice
public class FeginExceptionHandler extends ProtoExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(FeginExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    Object errorResponse(Exception ex, HttpServletRequest request, HttpServletResponse response){
        GeneratedMessage.Builder<?> builder = (GeneratedMessage.Builder<?>) request.getAttribute(ResponseBuildInteceptor.RESP_BUILDER);

        FieldDescriptor pf = null;
        if( builder != null ) {
            pf = builder.getDescriptorForType().findFieldByName(PROTO_RESP_FIELD);
        }

        BaseResponse baseResponse = null;
        if(ex instanceof FeignException){
            FeignException fex = (FeignException)ex;
            switch (fex.status()){
                case 422:
                    logger.info("i am 1");
                    SimpleResponse result = JsonUtil.getObjectFromJson(fex.getMessage().substring(fex.getMessage().indexOf("content:\n") + 8), SimpleResponse.class);
                    baseResponse = new BaseResponse(result.getResponse().getError_code(), result.getResponse().getError_message(), result.getResponse().getHint_message());
                    logger.info("i am 1, result:" + JsonUtil.format(baseResponse));
                    break;
                case 401:
                   baseResponse = new BaseResponse(401, "", "token session校验不通过");
                   break;
                case 503:
                    baseResponse = new BaseResponse(503, "", "小伙子，看看是不是在发服务吧");
                    break;
                default:
                    break;
            }
        }else if (ex instanceof ErrorCodeException){
            ErrorCodeException ece = (ErrorCodeException) ex;
            baseResponse = new BaseResponse(ece.getErrorCodeInterface().getErrorCode(), ece.getErrorCodeInterface().getMsg(), ece.getErrorCodeInterface().getHintMessage());
        }

//        logger.info("exception class:" + ex.getClass().getName());
        if(baseResponse != null){
//            logger.info("baseResponse:" + JsonUtil.format(baseResponse));
            Object obj = request.getAttribute(MyResponseBuildInteceptor.BASE_RESP);
//            logger.info("obj:" + JsonUtil.format(obj));
            if(obj != null){
                InterfaceBaseResponse interfaceBaseResponse = new InterfaceBaseResponse();
                interfaceBaseResponse.setResponse(baseResponse);
                if(String.class.equals(obj)){
                    return JsonUtil.format(interfaceBaseResponse);
                }else {
                    return interfaceBaseResponse;
                }
            }

            if(pf != null){
                builder.setField(pf, ProtoRespGenerator.generateResponse(baseResponse.getError_code(), baseResponse.getError_message(), baseResponse.getHint_message()));
                return builder;
            }
        }

        logger.info("i am 3, goto super");
        return super.errorResponse(ex, request, response);
    }
}
