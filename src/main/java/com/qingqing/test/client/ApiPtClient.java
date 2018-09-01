package com.qingqing.test.client;

import com.qingqing.api.proto.v1.OrderDetail.SimpleQingqingGroupSubOrderIdRequest;
import com.qingqing.api.proto.v1.Pay.GeneralOrderPaymentSummaryV2Response;
import com.qingqing.api.proto.v1.Pay.GeneralOrderPaymentSummaryV3Request;
import com.qingqing.api.proto.v1.Pay.PayCheckRequest;
import com.qingqing.api.proto.v1.Pay.PayCheckResponse;
import com.qingqing.api.proto.v1.Pay.PayGeneralOrderSubmitRequest;
import com.qingqing.api.proto.v1.Pay.PayResult;
import com.qingqing.api.proto.v1.ProtoBufResponse;
import com.qingqing.api.proto.v1.StudentProto.SimpleQingQingStudentIdRequest;
import com.qingqing.api.proto.v1.TeacherProto.SimpleQingQingTeacherIdRequest;
import com.qingqing.api.proto.v1.TeacherProto.TeacherBaseInfoForTeacherResponse;
import com.qingqing.api.proto.v1.TeacherProto.TeacherDetailForStudentToOrderResponse;
import com.qingqing.api.proto.v1.TeacherProto.TeacherStartEndClassV2;
import com.qingqing.api.proto.v1.UserAddress.QueryUserAddressResponse;
import com.qingqing.api.proto.v1.course.OrderCourse.CancelCourseRequestV4;
import com.qingqing.api.proto.v1.course.OrderCourse.CancelOrFreezeCourseRequestV2;
import com.qingqing.api.proto.v1.course.OrderCourse.OrderCourseFinishRequestV3;
import com.qingqing.api.proto.v1.order.Order.AddGroupOrderRequestV2;
import com.qingqing.api.proto.v1.order.Order.GroupSubOrderInfoDetailV2Response;
import com.qingqing.api.proto.v1.order.Order.JoinGroupOrderRequest;
import com.qingqing.api.proto.v1.order.Order.StudentAddGroupOrderResponse;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.config.feign.MyPtFeignConfiguration;
import com.qingqing.test.feign.PtRequestInterceptor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@FeignClient(value = "apiPiClient", url = "${api.host}/api/pt", configuration = MyPtFeignConfiguration.class)
public interface ApiPtClient {

    @RequestMapping(path = "/v1/teacher/base_info", method = RequestMethod.POST)
    @ProtoResponseBody
    TeacherBaseInfoForTeacherResponse baseInfo(@RequestHeader(name = PtRequestInterceptor.TEACHER_ID) Long teacherId);

    @RequestMapping(path = "/v2/group_order/student/add_order", method = RequestMethod.POST)
    @ProtoResponseBody
    StudentAddGroupOrderResponse studentAddOrderV2(AddGroupOrderRequestV2 request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/v2/student/getaddresslist", method = RequestMethod.POST)
    @ProtoResponseBody
    QueryUserAddressResponse studentAddresses(SimpleQingQingStudentIdRequest request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);


    @RequestMapping(path = "/v2/student/teacher/detail_for_order", method = RequestMethod.POST)
    @ProtoResponseBody
    TeacherDetailForStudentToOrderResponse detailForOrder(SimpleQingQingTeacherIdRequest request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/v6/payment/summary/for_order", method = RequestMethod.POST)
    @ProtoResponseBody
    GeneralOrderPaymentSummaryV2Response prePayForGeneralOrder(GeneralOrderPaymentSummaryV3Request request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);


    @RequestMapping(path = "/v5/payment/ackpay/for_order", method = RequestMethod.POST)
    @ProtoResponseBody
    PayResult payForOrder(PayGeneralOrderSubmitRequest request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/v6/payment/check_pay", method = RequestMethod.POST)
    @ProtoResponseBody
    PayCheckResponse checkPay(PayCheckRequest request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/v4/group_order/sub_order_detail", method = RequestMethod.POST)
    @ProtoResponseBody
    GroupSubOrderInfoDetailV2Response subOrderDetail(SimpleQingqingGroupSubOrderIdRequest request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/v2/teacher/start_class", method = RequestMethod.POST)
    @ProtoResponseBody
    ProtoBufResponse.SimpleResponse startClass(TeacherStartEndClassV2 request, @RequestHeader(name = PtRequestInterceptor.TEACHER_ID) Long teacherId);

    @RequestMapping(path = "/v2/order_course/action/student/apply_freeze", method = RequestMethod.POST)
    @ProtoResponseBody
    ProtoBufResponse.SimpleResponse applyFreeze(CancelOrFreezeCourseRequestV2 request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/v4/order_course/action/finish", method = RequestMethod.POST)
    @ProtoResponseBody
    ProtoBufResponse.SimpleResponse finishClass(OrderCourseFinishRequestV3 request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/v4/order_course/action/student/apply_cancel", method = RequestMethod.POST)
    @ProtoResponseBody
    ProtoBufResponse.SimpleResponse applyCancel(CancelCourseRequestV4 request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/v1/group_order/student/join_group_order", method = RequestMethod.POST)
    @ProtoResponseBody
    StudentAddGroupOrderResponse joinGroup(JoinGroupOrderRequest request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/{url}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String studentCommonRequest(@PathVariable("url") String url, @RequestBody String request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/{url}", method = RequestMethod.POST)
    @ResponseBody
    String teacherCommonRequest(@PathVariable("url") String url, String request, @RequestHeader(name = PtRequestInterceptor.TEACHER_ID) Long teacherId);
}
