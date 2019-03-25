package com.qingqing.test.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhujianxing on 2019/3/25.
 */
@Controller
public class MyErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String error(){
        return "/common/404";
    }
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
