package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.ProtoBufResponse;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.common.UrlAndParam;
import com.qingqing.test.bean.common.UserCommonRequest;
import com.qingqing.test.client.CommonPbClient;
import com.qingqing.test.client.PbClient;
import com.qingqing.test.client.PiClient;
import com.qingqing.test.client.PtClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private PbClient pbClient;
    @Autowired
    private PtClient ptClient;
    @Autowired
    private CommonPbClient commonPbClient;

    @RequestMapping("crond_task")
    @ResponseBody
    public SimpleResponse encode(@ProtoRequestBody SimpleStringRequest request) {
        String url = request.getData();

        piClient.commonGetRequest(url, "");
        return new SimpleResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
    }

    @RequestMapping("crond_task_page")
    public String crond(@RequestParam(value = "s", required = false, defaultValue = "") String search, Model model) {
        model.addAttribute("search", search);
        return "utils/crond_task";
    }

    @RequestMapping("haha")
    public String haha() {
        return "utils/haha";
    }

    @RequestMapping("pb")
    @ResponseBody
    public String pbRequest(@RequestBody UrlAndParam request){
        return pbClient.commonRequest(request.getUrl(), request.getParam());
    }

    @RequestMapping("pi")
    @ResponseBody
    public String piRequest(@RequestBody UserCommonRequest request){
         return  piClient.commonRequest(request.getUrl(), request.getParam(), request.getUserId(), request.getUserType());
    }

    @RequestMapping("pt")
    @ResponseBody
    public String ptRequest(@RequestBody UserCommonRequest request){
        return  ptClient.commonRequest(request.getUrl(), request.getParam(), request.getUserId(), request.getUserType());
    }

    @RequestMapping("wx_notify")
    @ResponseBody
    public ProtoBufResponse.SimpleResponse wxNotify(@RequestParam("content") String content){
        commonPbClient.selfNotify(content);

        return ProtoRespGenerator.SIMPLE_SUCC_RESP;
    }
}
