package com.qingqing.test.config.feign.exception;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessage;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.web.protobuf.ProtoExceptionHandler;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.common.web.protobuf.ResponseBuildInteceptor;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.InterfaceBaseResponse;
import com.qingqing.test.config.MyResponseBuildInteceptor;
import feign.FeignException;
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

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    Object errorResponse(Exception ex, HttpServletRequest request, HttpServletResponse response){
        GeneratedMessage.Builder<?> builder = (GeneratedMessage.Builder<?>) request.getAttribute(ResponseBuildInteceptor.RESP_BUILDER);

        FieldDescriptor pf = null;
        if( builder != null ) {
            pf = builder.getDescriptorForType().findFieldByName(PROTO_RESP_FIELD);
        }

        if(ex instanceof FeignException){
            FeignException fex = (FeignException)ex;
            response.setStatus(fex.status());
            if( pf != null ) {
                String reason = fex.getMessage();
                builder.setField(pf, ProtoRespGenerator.getUnprocessableErrResp(reason));
                return builder.build();
            }
        }else if(pf == null){
            Object obj = request.getAttribute(MyResponseBuildInteceptor.BASE_RESP);
            if(obj != null){
                if(ex instanceof ErrorCodeException){
                    ErrorCodeException ece = (ErrorCodeException) ex;
                    InterfaceBaseResponse interfaceBaseResponse = new InterfaceBaseResponse();
                    interfaceBaseResponse.setResponse(new BaseResponse(ece.getErrorCodeInterface().getErrorCode(), ece.getErrorCodeInterface().getMsg(), ece.getErrorCodeInterface().getHintMessage()));
                    return interfaceBaseResponse;
                }
            }
        }

        return super.errorResponse(ex, request, response);
    }
}
