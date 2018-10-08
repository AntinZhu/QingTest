package com.qingqing.test.config.inteceptor;

import com.qingqing.common.util.StringUtils;
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

    private static final String ENV = "env";
    private static final String GUID = "guid";
    private static final ThreadLocal<Map<String, String>> PARAM_MAPPING = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> params = new HashMap<>();
        String envValue = request.getParameter(ENV);
        if(StringUtils.isEmpty(envValue)){
            envValue = "dev";
        }
        params.put(ENV, envValue);

        String guid = request.getParameter(GUID);
        if(!StringUtils.isEmpty(guid)){
            params.put(GUID, guid);
        }

        PARAM_MAPPING.set(params);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        this.PARAM_MAPPING.remove();
    }

    public static final String getEnv(){
        return PARAM_MAPPING.get().get(ENV);
    }

    public static final String getGuid(){
        return PARAM_MAPPING.get().get(GUID);
    }
}
