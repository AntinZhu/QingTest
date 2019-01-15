package com.qingqing.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhujianxing on 2019/1/14.
 */
@Controller
public class OkController {

    @RequestMapping("pb/ok.html")
    public ResponseEntity<String> ok(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
