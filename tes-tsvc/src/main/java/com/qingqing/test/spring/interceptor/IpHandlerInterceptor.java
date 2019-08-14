package com.qingqing.test.spring.interceptor;

import com.qingqing.common.onoff.ISwitchDeterminer;
import com.qingqing.common.web.util.RequestExtract;
import com.qingqing.test.domain.user.TestUserIp;
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
    public static final String PAGE = "/logout";

    @Autowired
    private UserIpManager userIpManager;
    @Autowired
    private ISwitchDeterminer switchDeterminer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(switchDeterminer.isOn("all_user_allow", true)){
            return true;
        }

        String requestIp = RequestExtract.getServerIpByRequest((HttpServletRequest)request);
        if(!request.getRequestURI().equals(request.getContextPath() + PAGE)){
            TestUserIp userIp = userIpManager.getUserInfo(requestIp);
            if(userIp == null){
                response.sendRedirect(request.getContextPath() + PAGE);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String requestIp = RequestExtract.getServerIpByRequest((HttpServletRequest)httpServletRequest);
        if(modelAndView != null){
            modelAndView.addObject("qing_user_name", userIpManager.getUserNameByIp(requestIp));
            modelAndView.addObject("qing_user_ip", requestIp);
            modelAndView.addObject("qing_user", userIpManager.getUserInfo(requestIp));
        }
    }
}
