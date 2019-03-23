package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.client.PiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/common")
public class CommonController {

    @Autowired
    private PiClient piClient;

    @RequestMapping("crond_task")
    @ResponseBody
    public SimpleResponse encode(@ProtoRequestBody SimpleStringRequest request){
        String url = request.getData();

        piClient.commonRequest(url);
        return new SimpleResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
    }

    @RequestMapping("crond_task_page")
    public String crond(@RequestParam(value = "s", required = false, defaultValue="") String search, Model model){
        model.addAttribute("search", search);
        return "utils/crond_task";
    }

    @RequestMapping("haha")
    public String haha(){
        return "utils/haha";
    }
}
