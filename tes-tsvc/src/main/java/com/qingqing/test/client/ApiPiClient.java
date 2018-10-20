package com.qingqing.test.client;

import com.qingqing.api.proto.v1.BankProto.GetSupportBanksResponse;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleResponse;
import com.qingqing.api.proto.v1.RechargeProto.OperateUserWalletRequest;
import com.qingqing.api.proto.v1.TeacherProto.SimpleQingQingTeacherIdRequest;
import com.qingqing.api.proto.v1.consult.Consult.GetPhoneNumberResponse;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.bean.common.response.ListResponse;
import com.qingqing.test.bean.common.response.SingleResponse;
import com.qingqing.test.config.feign.MyPiFeignConfiguration;
import com.qingqing.test.domain.order.OrderCourseV1;
import com.qingqing.test.domain.pay.ThirdPayBrief;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhujianxing on 2017/8/15.
 */
@FeignClient(value = "apiPiClient", url = "http://{host}/svc/api", configuration = MyPiFeignConfiguration.class)
public interface ApiPiClient {

    /*
    protobuf
     */
    @PostMapping(value = "/pb/v1/bank/list_support_banks")
    @ProtoResponseBody
    public GetSupportBanksResponse listBank();

    /*
    protobuf
     */
    @PostMapping(value = "/pi/v1/teacher/phone_number")
    @ProtoResponseBody
    public GetPhoneNumberResponse getPhoneNumber(SimpleQingQingTeacherIdRequest request, @RequestHeader(name = "AssistantId") Long assistantId);

    @PostMapping(value = "/pi/v1/pay/brief/list", consumes="application/json", produces="application/json")
    ListResponse<ThirdPayBrief> getThirdPayBriefList(@RequestParam(name = "orderId") Long orderId, @RequestParam(name = "orderType") Integer orderType);

    @PostMapping(value = "/pi/v1/pay/brief/detail", consumes="application/json", produces="application/json")
    SingleResponse<ThirdPayBrief> getThirdPayBriefDetail(@RequestParam(name = "qingqingTradeNo") String qingqingTradeNo);

    @PostMapping(value = "/pi/v1/order_course/list_by_order_id", consumes="application/json", produces="application/json")
    ListResponse<OrderCourseV1> getOrderCourseList(@RequestParam(name = "orderId") Long orderId);

    @PostMapping(value = "/pi/v1/teacher/xxxx")
    @ProtoResponseBody
    public String xxxx();

    @PostMapping(value = "/pi/v1/studentwallet/admin_operate")
    @ProtoResponseBody
    SimpleResponse studentWalletOps(OperateUserWalletRequest request);
}
