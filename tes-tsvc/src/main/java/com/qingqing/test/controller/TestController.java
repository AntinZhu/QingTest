package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.inter.request.InterfaceInvokeRequest;
import com.qingqing.test.bean.inter.response.TestInterfaceBean;
import com.qingqing.test.bean.inter.response.TestInterfaceResponse;
import com.qingqing.test.controller.errorcode.TestInterfaceErrorCode;
import com.qingqing.test.manager.TestInterfaceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/test")
public class TestController {

    @Autowired
    private TestInterfaceManager testInterfaceManager;

    @RequestMapping("json_format")
    public String studentAddOrderPage(){
        return "interface/jsonformat";
    }

    @RequestMapping("/interface")
    @ResponseBody
    public TestInterfaceResponse testInterface(@ProtoRequestBody SimpleLongRequest request){
        Long interfaceId = request.getData();
        TestInterfaceBean interfaceBean = testInterfaceManager.getInterfaceBean(interfaceId);
        if(interfaceBean.getInter() == null){
            throw new ErrorCodeException(TestInterfaceErrorCode.unknown_test_interface, "unknown ytest interface, interfaceId:" + interfaceId);
        }

        TestInterfaceResponse interfaceResponse = new TestInterfaceResponse();
        interfaceResponse.setResponse(BaseResponse.SUCC_RESP);
        interfaceResponse.setInterfaceInfo(interfaceBean);
        return interfaceResponse;
    }

    @RequestMapping("/interface/invoke")
    @ProtoResponseBody
    public com.qingqing.api.proto.v1.ProtoBufResponse.SimpleDataResponse invoke(@RequestBody InterfaceInvokeRequest request) {
        String result =  testInterfaceManager.invoke(request);
        return com.qingqing.api.proto.v1.ProtoBufResponse.SimpleDataResponse.newBuilder()
                .setData(result)
                .setResponse(ProtoRespGenerator.SUCC_BASE_RESP)
                .build();
    }
}
