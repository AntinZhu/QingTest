package com.qingqing.test.config.inteceptor;

import com.qingqing.common.util.StringUtils;
import com.qingqing.common.web.util.ServletUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujianxing on 2018/9/13.
 */
public class EnvHandlerInteceptor extends HandlerInterceptorAdapter {

    public static final String ENV = "env";
    public static final String GUID = "guid";
    public static final String IS_LOCAL_DEBUG = "is_local";
    public static final String LOCAL_PORT = "local_port";
    public static final String IP = "ip";
    private static final String[] SAVE_PARAM_SET = new String[]{GUID, IS_LOCAL_DEBUG, LOCAL_PORT};
    private static final ThreadLocal<Map<String, String>> PARAM_MAPPING = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> params = new HashMap<>();
        String envValue = request.getParameter(ENV);
        if(StringUtils.isEmpty(envValue)){
            envValue = "dev";
        }
        params.put(ENV, envValue);

        for(String saveParam : SAVE_PARAM_SET){
            String paramValue = request.getParameter(saveParam);
            if(!StringUtils.isEmpty(paramValue)){
                params.put(saveParam, paramValue);
            }
        }

        String ip = ServletUtil.getRemoteAddress(request);
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }
        params.put(IP, ip);
        PARAM_MAPPING.set(params);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        this.PARAM_MAPPING.remove();
    }

    public static final String getParam(String paramName){
        return PARAM_MAPPING.get().get(paramName);
    }

    public static final boolean isLocalDebug(){
        return "1".equals(PARAM_MAPPING.get().get(IS_LOCAL_DEBUG));
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
         }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getRemoteAddr();
         }
        return ip;
    }
}
