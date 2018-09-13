package com.qingqing.test.config.inteceptor;

import com.qingqing.common.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhujianxing on 2018/9/13.
 */
public class EnvHandlerInteceptor extends HandlerInterceptorAdapter {

    private static final ThreadLocal<String> env = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String envValue = request.getParameter("env");
        if(StringUtils.isEmpty(envValue)){
            envValue = "dev";
        }
        this.env.set(envValue);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        this.env.remove();
    }

    public static final String getEnv(){
        return env.get();
    }
}
