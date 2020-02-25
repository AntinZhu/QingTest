package com.qingqing.test.client;

import com.qingqing.api.proto.v1.OrderDetail.SimpleQingqingGroupSubOrderIdRequest;
import com.qingqing.api.proto.v1.Pay.GeneralOrderPaymentSummaryV2Response;
import com.qingqing.api.proto.v1.Pay.GeneralOrderPaymentSummaryV3Request;
import com.qingqing.api.proto.v1.Pay.PayCheckRequest;
import com.qingqing.api.proto.v1.Pay.PayCheckResponse;
import com.qingqing.api.proto.v1.Pay.PayGeneralOrderSubmitRequest;
import com.qingqing.api.proto.v1.Pay.PayResult;
import com.qingqing.api.proto.v1.ProtoBufResponse;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleResponse;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleStringResponse;
import com.qingqing.api.proto.v1.StudentProto.SimpleQingQingStudentIdRequest;
import com.qingqing.api.proto.v1.TeacherProto.SimpleQingQingTeacherIdRequest;
import com.qingqing.api.proto.v1.TeacherProto.TeacherBaseInfoForTeacherResponse;
import com.qingqing.api.proto.v1.TeacherProto.TeacherDetailForStudentToOrderResponse;
import com.qingqing.api.proto.v1.UserAddress.QueryUserAddressResponse;
import com.qingqing.api.proto.v1.ValueVoucher.ListValueVoucherWithRecommendIdResponse;
import com.qingqing.api.proto.v1.ValueVoucher.MultiOrderUseValueVoucherRequest;
import com.qingqing.api.proto.v1.course.OrderCourse.CancelCourseRequestV4;
import com.qingqing.api.proto.v1.course.OrderCourse.CancelOrFreezeCourseRequestV2;
import com.qingqing.api.proto.v1.course.OrderCourse.OrderCourseFinishRequestV3;
import com.qingqing.api.proto.v1.order.Order.AddGroupOrderRequestV2;
import com.qingqing.api.proto.v1.order.Order.GroupSubOrderInfoDetailV2Response;
import com.qingqing.api.proto.v1.order.Order.JoinGroupOrderRequest;
import com.qingqing.api.proto.v1.order.Order.StudentAddGroupOrderResponse;
import com.qingqing.api.proto.v1.serviceslice.ServiceSliceProto.ApiCourseReportDetailForTeacherResponse;
import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.common.auth.domain.UserType;
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
@FeignClient(value = "ptClient", url = "http://{host}", configuration = MyPtFeignConfiguration.class)
public interface PtClient {

    @RequestMapping(path = "/svc/api/pt/v1/teacher/base_info", method = RequestMethod.POST)
    @ProtoResponseBody
    TeacherBaseInfoForTeacherResponse baseInfo(@RequestHeader(name = PtRequestInterceptor.TEACHER_ID) Long teacherId);

    @RequestMapping(path = "/svc/api/pt/v2/group_order/student/add_order", method = RequestMethod.POST)
    @ProtoResponseBody
    StudentAddGroupOrderResponse studentAddOrderV2(AddGroupOrderRequestV2 request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/svc/api/pt/v2/student/getaddresslist", method = RequestMethod.POST)
    @ProtoResponseBody
    QueryUserAddressResponse studentAddresses(SimpleQingQingStudentIdRequest request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);


    @RequestMapping(path = "/svc/api/pt/v3/student/teacher/detail_for_order", method = RequestMethod.POST)
    @ProtoResponseBody
    TeacherDetailForStudentToOrderResponse detailForOrder(SimpleQingQingTeacherIdRequest request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/svc/api/pt/v7/payment/summary/for_order", method = RequestMethod.POST)
    @ProtoResponseBody
    GeneralOrderPaymentSummaryV2Response prePayForGeneralOrder(GeneralOrderPaymentSummaryV3Request request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(name = PtRequestInterceptor.USER_TYPE) UserType UserType);


    @RequestMapping(path = "/svc/api/pt/v6/payment/ackpay/for_order", method = RequestMethod.POST)
    @ProtoResponseBody
    PayResult payForOrder(PayGeneralOrderSubmitRequest request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(name = PtRequestInterceptor.USER_TYPE) UserType userType);

    @RequestMapping(path = "/svc/api/pt/v6/payment/check_pay", method = RequestMethod.POST)
    @ProtoResponseBody
    PayCheckResponse checkPay(PayCheckRequest request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(name = PtRequestInterceptor.USER_TYPE) UserType userType);

    @RequestMapping(path = "/svc/api/pt/v4/group_order/sub_order_detail", method = RequestMethod.POST)
    @ProtoResponseBody
    GroupSubOrderInfoDetailV2Response subOrderDetail(SimpleQingqingGroupSubOrderIdRequest request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/svc/api/pt/v2/order_course/action/student/apply_freeze", method = RequestMethod.POST)
    @ProtoResponseBody
    ProtoBufResponse.SimpleResponse applyFreeze(CancelOrFreezeCourseRequestV2 request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/svc/api/pt/v4/order_course/action/finish", method = RequestMethod.POST)
    @ProtoResponseBody
    ProtoBufResponse.SimpleResponse finishClass(OrderCourseFinishRequestV3 request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/svc/api/pt/v5/order_course/action/student/apply_cancel", method = RequestMethod.POST)
    @ProtoResponseBody
    SimpleStringResponse applyCancel(CancelCourseRequestV4 request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "/svc/api/pt/v1/group_order/student/join_group_order", method = RequestMethod.POST)
    @ProtoResponseBody
    StudentAddGroupOrderResponse joinGroup(JoinGroupOrderRequest request, @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);

    @RequestMapping(path = "{url}", method = RequestMethod.POST, produces ={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String commonRequest(@PathVariable("url") String url, @RequestBody String request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(PtRequestInterceptor.USER_TYPE)UserType userType, @RequestHeader(PtRequestInterceptor.HEADERS) String headers);

    @RequestMapping(path = "{url}", method = RequestMethod.GET, produces ={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String commonGetRequest(@PathVariable("url") String url, @RequestBody String request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(PtRequestInterceptor.USER_TYPE)UserType userType, @RequestHeader(PtRequestInterceptor.HEADERS) String headers);

    @RequestMapping(path = "/svc/api/pt/v2/order_course_report/teacher/detail", method = RequestMethod.POST)
    @ProtoResponseBody
    ApiCourseReportDetailForTeacherResponse orderCourseReportDetail(SimpleLongRequest request, @RequestHeader(name = PtRequestInterceptor.TEACHER_ID) Long teacherId);

    @RequestMapping(path = "/svc/api/pt/v1/valuevouchers/multi_order/use_value_vouchers", method = RequestMethod.POST)
    @ProtoResponseBody
    SimpleResponse useValueVouchers(MultiOrderUseValueVoucherRequest request,
                                    @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);


    @RequestMapping(path = "/svc/api/pt/v4/valuevouchers/list_for_order", method = RequestMethod.POST)
    @ProtoResponseBody
    ListValueVoucherWithRecommendIdResponse valueVouchersList(SimpleQingqingGroupSubOrderIdRequest request,
                                                              @RequestHeader(name = PtRequestInterceptor.STUDENT_ID) Long studentId);
}


