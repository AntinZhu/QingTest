package com.qingqing.test.controller;

import com.qingqing.common.util.StringUtils;
import com.qingqing.test.domain.user.IpStatus;
import com.qingqing.test.domain.user.TestUserIp;
import com.qingqing.test.manager.UserIpManager;
import com.qingqing.test.spring.filter.IpFilter;
import com.qingqing.test.spring.interceptor.IpHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String logout(@RequestParam(value = "requestUrl", defaultValue = "") String requestUrl,
                         @RequestParam(value = "inBlack", defaultValue = "0") int inBlack,
                        Model model){
        TestUserIp testUserIp = userIpManager.getUserInfoIncTmp(IpFilter.getRequestUserIp());
        if(testUserIp != null && IpStatus.enable.equals(testUserIp.getIpStatus())){
            if(StringUtils.isEmpty(requestUrl)){
                return "/common/404";
            }else{
                return "redirect:" + requestUrl;
            }
        }

        model.addAttribute("inBlack", inBlack);
        return "/common/logout";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
