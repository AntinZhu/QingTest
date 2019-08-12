package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.OrderDetail.SimpleQingqingGroupSubOrderIdRequest;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleResponse;
import com.qingqing.api.proto.v1.ValueVoucher.ListValueVoucherWithRecommendIdResponse;
import com.qingqing.api.proto.v1.order.Order.GroupSubOrderInfoDetailV2Response;
import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.bean.common.request.SimpleLongStudentRequest;
import com.qingqing.test.bean.order.AddClassOrderBean;
import com.qingqing.test.bean.order.AddOrderResultBean;
import com.qingqing.test.bean.order.StudentAddOrderBean;
import com.qingqing.test.bean.order.TeacherInfoForOrderBean;
import com.qingqing.test.bean.order.request.DetailForOrderRequest;
import com.qingqing.test.bean.order.request.MadeUpClassOrderRequest;
import com.qingqing.test.bean.order.request.StudentSubOrderDetailRequest;
import com.qingqing.test.client.PtClient;
import com.qingqing.test.manager.OrderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderManager orderManager;
    @Autowired
    private PtClient ptClient;

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

    @RequestMapping("add_class_order")
    @ResponseBody
    public String addClassOrder(@RequestBody AddClassOrderBean addOrderBean){
        Long classId = orderManager.addClass(addOrderBean);
        Long classOrderId = orderManager.addClassOrder(classId, addOrderBean);
        AddOrderResultBean result = orderManager.joinClassOrder(classOrderId, addOrderBean.getStudentId(), addOrderBean.getCreateAssistantId());

        return JsonUtil.format(result);
    }

    @RequestMapping("detail_for_class_order")
    @ResponseBody
    public String detailForClassOrder(@RequestBody SimpleLongRequest request){
        TeacherInfoForOrderBean resultBean = orderManager.detailForAddClassOrder(request.getData());

        return JsonUtil.format(resultBean);
    }

    @RequestMapping("student/sub_order_detail")
    @ProtoResponseBody
    public GroupSubOrderInfoDetailV2Response subOrderDetail(@RequestBody StudentSubOrderDetailRequest request){
        return ptClient.subOrderDetail(SimpleQingqingGroupSubOrderIdRequest.newBuilder().setQingqingGroupSubOrderId(request.getQingqingOrderId()).build(), request.getStudentId());
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

    @RequestMapping("class_order/made_up")
    @ProtoResponseBody
    public SimpleResponse madeUpClassOrder(@RequestBody MadeUpClassOrderRequest request){
        logger.info("param:" + JsonUtil.format(request));
        orderManager.madeUpClassOrder(request.getGroupOrderId(), request.getStudentId(), request.getCreateAssistantId(), request.getOtherStudentIds());

        return ProtoRespGenerator.SIMPLE_SUCC_RESP;
    }

    @RequestMapping("class_order/add_page")
    public String addClassOrderPage(){
        return "order/add_class_order";
    }

    @RequestMapping("student/order/value_voucher_list")
    @ProtoResponseBody
    public ListValueVoucherWithRecommendIdResponse valueVoucherList(@RequestBody StudentSubOrderDetailRequest request){
        return ptClient.valueVouchersList(SimpleQingqingGroupSubOrderIdRequest.newBuilder()
                .setQingqingGroupSubOrderId(request.getQingqingOrderId())
                .build(), request.getStudentId());
    }
}
