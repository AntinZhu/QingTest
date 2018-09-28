package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleDataResponse;
import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.common.response.ListResponse;
import com.qingqing.test.bean.inter.CatelogBean;
import com.qingqing.test.bean.inter.SaveInterfaceBean;
import com.qingqing.test.bean.inter.request.InterfaceInvokeRequest;
import com.qingqing.test.bean.inter.response.TestInterfaceBean;
import com.qingqing.test.bean.inter.response.TestInterfaceResponse;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import com.qingqing.test.controller.errorcode.TestInterfaceErrorCode;
import com.qingqing.test.domain.inter.TestInterfaceCatelog;
import com.qingqing.test.manager.TestInterfaceManager;
import com.qingqing.test.service.inter.TestInterfaceCatelogService;
import com.qingqing.test.util.QingParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/test")
public class TestController {

    @Autowired
    private TestInterfaceManager testInterfaceManager;
    @Autowired
    private TestInterfaceCatelogService catelogService;

    @RequestMapping("json_format")
    public String show(@RequestParam("id") Long id, Model model){
        model.addAttribute("interfaceId", id);
        return "interface/jsonformat";
    }

    @RequestMapping("/interface/edit")
    public String edit(@RequestParam(value="id", defaultValue = "0") Long interfaceId, Model model){
        if(interfaceId > 0){
            TestInterfaceBean interfaceBean = testInterfaceManager.getInterfaceBean(interfaceId);
            model.addAttribute("interfaceBean", interfaceBean);
        }
        return "interface/editInterface";
    }

    @RequestMapping("/interface/save")
    @ProtoResponseBody
    public SimpleDataResponse save(@RequestBody SaveInterfaceBean saveBean){
        Long parentCatelogId = saveBean.getParentCatelogId();
        TestInterfaceCatelog testInterfaceCatelog = catelogService.findById(parentCatelogId);
        if(testInterfaceCatelog == null){
            throw new ErrorCodeException(new SimpleErrorCode("未能找到对应所属目录"), "cannot found parent catelog");
        }

        Long interfaceId = testInterfaceManager.saveTestInterface(saveBean, testInterfaceCatelog);

        return SimpleDataResponse.newBuilder().setResponse(ProtoRespGenerator.SUCC_BASE_RESP)
                .setData(String.valueOf(interfaceId)).build();
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

    @RequestMapping("/catelog")
    @ResponseBody
    public ListResponse<CatelogBean> catelog(){
        List<CatelogBean> dataList = testInterfaceManager.getCatelogList();

        ListResponse<CatelogBean> resultList = new ListResponse<>();
        resultList.setResponse(BaseResponse.SUCC_RESP);
        resultList.setResultList(dataList);
        return resultList;
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

    @RequestMapping("/interface/request/convert")
    @ResponseBody
    public SimpleDataResponse convertParam(@ProtoRequestBody SimpleStringRequest request) {
        if(!request.hasData()){
            throw new RequestValidateException("need param className", "need param className");
        }

        String data = QingParamUtil.generateParamJson(request.getData());
        return SimpleDataResponse.newBuilder().setResponse(ProtoRespGenerator.SUCC_BASE_RESP)
                .setData(data).build();
    }
}