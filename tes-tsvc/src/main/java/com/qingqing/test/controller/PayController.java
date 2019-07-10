package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.OrderCommonEnum.OrderType;
import com.qingqing.api.proto.v1.Pay.PayCheckRequest;
import com.qingqing.api.proto.v1.Pay.PayCheckResponse;
import com.qingqing.api.proto.v1.Pay.PayResult;
import com.qingqing.api.proto.v1.ProtoBufResponse.BaseResponse;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleResponse;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.bean.order.CoursePriceType;
import com.qingqing.test.bean.order.QingQingCommonOrderBean;
import com.qingqing.test.bean.pay.request.CheckPayRequest;
import com.qingqing.test.bean.pay.request.CheckPayRequestV2;
import com.qingqing.test.bean.pay.request.PayRequestBean;
import com.qingqing.test.bean.pay.request.PayRequestBeanV2;
import com.qingqing.test.bean.pay.request.PrePayRequestBean;
import com.qingqing.test.bean.pay.request.PrePayRequestBeanV2;
import com.qingqing.test.client.PtClient;
import com.qingqing.test.manager.OrderManager;
import com.qingqing.test.manager.PayManager;
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
@RequestMapping("/v1/pay")
public class PayController {

    @Autowired
    private OrderManager orderManager;
    @Autowired
    private PtClient ptClient;
    @Autowired
    private PayManager payManager;

    @RequestMapping("pay_infos")
    @ResponseBody
    public String payInfos(@RequestBody QingQingCommonOrderBean bean){
        CoursePriceType coursePriceType = CoursePriceType.valueOf(bean.getCoursePriceType());
        return JsonUtil.format(orderManager.getPayBriefInfo(coursePriceType.getOrderType().getNumber(), OrderIdEncoder.decodeQingqingOrderId(bean.getQingqingOrderId())));
    }

    @RequestMapping("pay_infos_2")
    @ResponseBody
    public String payInfos2(@ProtoRequestBody PayCheckRequest bean){
        return JsonUtil.format(orderManager.getPayBriefInfo(bean.getOrderType().getNumber(), OrderIdEncoder.decodeQingqingOrderId(bean.getQingqingCommonOrderId())));
    }

    @RequestMapping("mock_pay_notify")
    public String mockPay(
            @RequestParam(value = "env", defaultValue = "dev") String env,
            @RequestParam(value = "uid", defaultValue = "0") Long userId,
            @RequestParam(value = "uty", defaultValue = "") String userType,
            @RequestParam(value = "def", defaultValue = "{}") String defaultObj,
            @RequestParam(value = "paramId", defaultValue = "0") Long paramId,
            @RequestParam(value = "inv", defaultValue = "0") String inv,
            Model model
    ){
        model.addAttribute("paramExampleId", paramId);
        model.addAttribute("env", env);
        model.addAttribute("userId", userId);
        model.addAttribute("userType", userType);
        model.addAttribute("defaultObj", defaultObj);
        model.addAttribute("inv", inv);

        return "pay/mock_pay_notify";
    }

    @RequestMapping("ack_pay")
    @ResponseBody
    public PayResult pay(@RequestBody PayRequestBean request){
        return orderManager.payForOrder(request);
    }

    @RequestMapping("ack_pay_v2")
    @ResponseBody
    public PayResult payV2(@RequestBody PayRequestBeanV2 request){
        return orderManager.payForOrder(request);
    }

    @RequestMapping("pre_pay")
    @ResponseBody
    public String prePay(@RequestBody PrePayRequestBean requestBean){
        return JsonUtil.format(orderManager.getPrePayInfo(requestBean));
    }

    @RequestMapping("pre_pay_v2")
    @ResponseBody
    public String prePayV2(@RequestBody PrePayRequestBeanV2 requestBean){
        return JsonUtil.format(orderManager.getPrePayInfo(requestBean));
    }

    @RequestMapping("check_pay")
    @ProtoResponseBody
    public PayCheckResponse checkPay(@RequestBody CheckPayRequest bean){
        CoursePriceType coursePriceType = CoursePriceType.valueOf(bean.getCoursePriceType());
        return ptClient.checkPay(PayCheckRequest.newBuilder().setQingqingCommonOrderId(bean.getQingqingOrderId())
                .setOrderType(coursePriceType.getOrderType()).build(), bean.getStudentId(), UserType.student);
    }

    @RequestMapping("check_pay_v2")
    @ProtoResponseBody
    public PayCheckResponse checkPayV2(@RequestBody CheckPayRequestV2 bean){
        return ptClient.checkPay(PayCheckRequest.newBuilder().setQingqingCommonOrderId(bean.getQingqingOrderId())
                .setOrderType(OrderType.valueOf(bean.getOrderType())).build(), bean.getUserId(), UserType.valueOf(bean.getUserType()));
    }

    @RequestMapping("mock_third_pay")
    @ProtoResponseBody
    public SimpleResponse mockThirdPay(@ProtoRequestBody SimpleStringRequest request){
        String qingqingTradeNo = request.getData();
        boolean notifyResult = payManager.mockThirdPayNotify(qingqingTradeNo);

        if(notifyResult){
            return ProtoRespGenerator.SIMPLE_SUCC_RESP;
        }else{
            return SimpleResponse.newBuilder()
                    .setResponse(BaseResponse.newBuilder().setErrorCode(1).setHintMessage("模拟支付成功通知失败失败"))
                    .build();
        }
    }
}
