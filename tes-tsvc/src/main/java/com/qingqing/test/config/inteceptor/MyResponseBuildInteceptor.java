package com.qingqing.test.config.inteceptor;

import com.qingqing.common.web.protobuf.ResponseBuildInteceptor;
import com.qingqing.test.bean.base.InterfaceBaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public class MyResponseBuildInteceptor extends ResponseBuildInteceptor {
    private static final Logger logger = LoggerFactory.getLogger(ResponseBuildInteceptor.class);

    public static final String BASE_RESP = "interface_resp_clazz";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        try {
            if( handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Class<?> clazz=handlerMethod.getMethod().getReturnType();
                if(InterfaceBaseResponse.class.isAssignableFrom(clazz)){
                    request.setAttribute(BASE_RESP, clazz);
                }
            }
        } catch (Exception e) {
            logger.warn("MyResponseBuildInteceptor set response builder error:"+e.getMessage()+ ",uri:" + request.getRequestURI());
        }
        return super.preHandle(request, response, handler);
    }
}
