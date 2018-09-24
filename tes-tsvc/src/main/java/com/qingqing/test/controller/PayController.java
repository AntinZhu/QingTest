package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.Pay.PayCheckRequest;
import com.qingqing.api.proto.v1.Pay.PayCheckResponse;
import com.qingqing.api.proto.v1.ProtoBufResponse.BaseResponse;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleResponse;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.bean.order.CoursePriceType;
import com.qingqing.test.bean.order.QingQingCommonOrderBean;
import com.qingqing.test.bean.pay.request.CheckPayRequest;
import com.qingqing.test.bean.pay.request.PayRequestBean;
import com.qingqing.test.bean.pay.request.PrePayRequestBean;
import com.qingqing.test.client.PtClient;
import com.qingqing.test.manager.OrderManager;
import com.qingqing.test.manager.PayManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String mockPay(){
        return "pay/mock_pay_notify";
    }

    @RequestMapping("ack_pay")
    @ResponseBody
    public String pay(@RequestBody PayRequestBean request){
        return JsonUtil.format(orderManager.payForOrder(request));
    }

    @RequestMapping("pre_pay")
    @ResponseBody
    public String prePay(@RequestBody PrePayRequestBean requestBean){
        return JsonUtil.format(orderManager.getPrePayInfo(requestBean));
    }

    @RequestMapping("check_pay")
    @ProtoResponseBody
    public PayCheckResponse payInfos(@RequestBody CheckPayRequest bean){
        CoursePriceType coursePriceType = CoursePriceType.valueOf(bean.getCoursePriceType());
        return ptClient.checkPay(PayCheckRequest.newBuilder().setQingqingCommonOrderId(bean.getQingqingOrderId())
                .setOrderType(coursePriceType.getOrderType()).build(), bean.getStudentId());
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
