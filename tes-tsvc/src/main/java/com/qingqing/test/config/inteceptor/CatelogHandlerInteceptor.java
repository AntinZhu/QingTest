package com.qingqing.test.config.inteceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhujianxing on 2018/9/13.
 */
public class CatelogHandlerInteceptor extends HandlerInterceptorAdapter {

    private static final String CATE_LOG_INDEX = "catelogIndex";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String catelogIndex = request.getParameter("catelogIndex");
        if(catelogIndex != null){
            modelAndView.addObject(CATE_LOG_INDEX, catelogIndex);
        }
    }
}
