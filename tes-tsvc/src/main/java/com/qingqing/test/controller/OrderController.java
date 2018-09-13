package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.OrderDetail.SimpleQingqingGroupSubOrderIdRequest;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleResponse;
import com.qingqing.api.proto.v1.order.Order.GroupSubOrderInfoDetailV2Response;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.bean.common.request.SimpleLongStudentRequest;
import com.qingqing.test.bean.order.AddOrderResultBean;
import com.qingqing.test.bean.order.StudentAddOrderBean;
import com.qingqing.test.bean.order.TeacherInfoForOrderBean;
import com.qingqing.test.bean.order.request.DetailForOrderRequest;
import com.qingqing.test.bean.order.request.StudentSubOrderDetailRequest;
import com.qingqing.test.client.ApiPtClient;
import com.qingqing.test.manager.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    private OrderManager orderManager;
    @Autowired
    private ApiPtClient apiPtClient;

    @RequestMapping("add")
    public String add(){
        return orderManager.test();
    }

    @RequestMapping("student/add_order")
    @ResponseBody
    public String studentAddOrder(@RequestBody StudentAddOrderBean addOrderBean){
        AddOrderResultBean result = orderManager.addOrder(addOrderBean);

        return JsonUtil.format(result);
    }

    @RequestMapping("teacher/detail_for_order")
    @ResponseBody
    public String detailForOrder(@RequestBody DetailForOrderRequest bean){
        TeacherInfoForOrderBean resultBean = orderManager.detailForOrder(bean.getStudentId(), bean.getTeacherId());

        return JsonUtil.format(resultBean);
    }

    @RequestMapping("student/sub_order_detail")
    @ProtoResponseBody
    public GroupSubOrderInfoDetailV2Response subOrderDetail(@RequestBody StudentSubOrderDetailRequest request){
        return apiPtClient.subOrderDetail(SimpleQingqingGroupSubOrderIdRequest.newBuilder().setQingqingGroupSubOrderId(request.getQingqingOrderId()).build(), request.getStudentId());
    }

    @RequestMapping("student/add_order_page")
    public String studentAddOrderPage(){
        return "order/addOrder";
    }

    @RequestMapping("made_up")
    @ProtoResponseBody
    public SimpleResponse madeUp(@RequestBody SimpleLongStudentRequest request){
        orderManager.madeUpGroupOrder(request.getData(), request.getStudentId());

        return ProtoRespGenerator.SIMPLE_SUCC_RESP;
    }
}
