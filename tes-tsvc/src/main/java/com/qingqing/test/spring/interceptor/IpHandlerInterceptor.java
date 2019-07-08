package com.qingqing.test.spring.interceptor;

import com.qingqing.common.web.util.RequestExtract;
import com.qingqing.test.manager.UserIpManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhujianxing on 2019/7/8.
 */
public class IpHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(IpHandlerInterceptor.class);

    @Autowired
    private UserIpManager userIpManager;

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String requestIp = RequestExtract.getServerIpByRequest((HttpServletRequest)httpServletRequest);
        if(modelAndView != null){
            modelAndView.addObject("qing_ip", userIpManager.getUserNameByIp(requestIp));
            logger.info("add qing_ip:" + requestIp);
        }
    }
}
