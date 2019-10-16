package com.qingqing.test.controller;

import com.qingqing.test.manager.UserIpManager;
import com.qingqing.test.spring.filter.IpFilter;
import com.qingqing.test.spring.interceptor.IpHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhujianxing on 2019/3/25.
 */
@Controller
public class MyErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Autowired
    private UserIpManager userIpManager;

    @RequestMapping(ERROR_PATH)
    public String error(){
        return "/common/404";
    }

    @RequestMapping(IpHandlerInterceptor.PAGE)
    public String logout(){
        if(userIpManager.isUserIpExist(IpFilter.getRequestUserIp())){
            return "/common/404";
        }

        return "/common/logout";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
