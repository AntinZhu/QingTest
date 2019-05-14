package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.CourseCommonProto.CourseChangeResponsibilityType;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleResponse;
import com.qingqing.api.proto.v1.TeacherProto.TeacherStartEndClassV2;
import com.qingqing.api.proto.v1.app.AppCommon.DeviceIdentification;
import com.qingqing.api.proto.v1.app.AppCommon.PlatformType;
import com.qingqing.api.proto.v1.course.OrderCourse.CancelCourseRequestV4;
import com.qingqing.api.proto.v1.course.OrderCourse.CancelOrFreezeCourseReasonType;
import com.qingqing.api.proto.v1.course.OrderCourse.CancelOrFreezeCourseRequestV2;
import com.qingqing.api.proto.v1.course.OrderCourse.OrderCourseFinishMockRequest;
import com.qingqing.api.proto.v1.course.OrderCourse.OrderCourseFinishMockResponse;
import com.qingqing.api.proto.v1.course.OrderCourse.OrderCourseFinishRequestV3;
import com.qingqing.api.proto.v1.course.OrderCourse.OrderCourseThirdPartyJudgeRequestV4;
import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.common.request.SimpleLongStudentRequest;
import com.qingqing.test.bean.common.response.SingleResponse;
import com.qingqing.test.bean.ordercourse.request.StartClassRequest;
import com.qingqing.test.client.ApiPiClient;
import com.qingqing.test.client.PiClient;
import com.qingqing.test.client.PtClient;
import com.qingqing.test.domain.order.GroupUserCourseApply;
import com.qingqing.test.manager.OrderManager;
import com.qingqing.test.service.order.OrderCourseService;
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
@RequestMapping("/v1/order_course/")
public class OrderCourseController {

    @Autowired
    private OrderManager orderManager;
    @Autowired
    private PtClient ptClient;
    @Autowired
    private PiClient piClient;
    @Autowired
    private OrderCourseService orderCourseService;
    @Autowired
    private ApiPiClient apiPiClient;

    @RequestMapping("order_course_list")
    @ResponseBody
    public String orderCourseList(@RequestBody SimpleStringRequest request) {
        Long orderId = OrderIdEncoder.decodeQingqingOrderId(request.getData());
        return JsonUtil.format(orderManager.getOrderCourseListByOrderId(orderId));
    }

    @RequestMapping("freeze_apply/page")
    public String queryFreezeApply(@RequestParam("orderCourseId") Long orderCourseId, @RequestParam(value = "env", defaultValue = "dev") String env, Model model){
        model.addAttribute("orderCourseId", orderCourseId);
        model.addAttribute("env", env);

        return "ordercourse/freeze_apply";
    }

    @RequestMapping("freeze_apply/query_by_order_course_id")
    @ResponseBody
    public SingleResponse<GroupUserCourseApply> queryFreezeApply(@RequestBody SimpleLongRequest request) {
        GroupUserCourseApply apply = orderCourseService.getFreezeApplyByOrderCourseId(request.getData());

        SingleResponse<GroupUserCourseApply> result = new SingleResponse<GroupUserCourseApply>();
        result.setResponse(BaseResponse.SUCC_RESP);
        result.setResultList(apply);

        return result;
    }

    @RequestMapping("freeze_apply/process")
    @ProtoResponseBody
    public SimpleResponse processFreeze(@RequestBody OrderCourseThirdPartyJudgeRequestV4 request) {
        return apiPiClient.processFreeze(request, 120L, UserType.ta);
    }

    @RequestMapping("freeze_apply/mock")
    @ProtoResponseBody
    public OrderCourseFinishMockResponse mockFinish(@RequestBody OrderCourseFinishMockRequest request) {
        return apiPiClient.mockFinish(request);
    }

    @RequestMapping("teacher/start_class")
    @ProtoResponseBody
    public SimpleResponse startClass(@RequestBody StartClassRequest request) {
        TeacherStartEndClassV2 protoRequest = TeacherStartEndClassV2.newBuilder()
                .setQingqingGroupOrderCourseId(OrderIdEncoder.encodeOrderId(request.getGroupOrderCourseId()))
                .setDeviceIdentification(getDeviceIdentification())
                .build();
       return  piClient.startClass(protoRequest, request.getTeacherId());
    }

    @RequestMapping("student/apply_freeze")
    @ProtoResponseBody
    public SimpleResponse applyFreeze(@RequestBody SimpleLongStudentRequest request) {
        CancelOrFreezeCourseRequestV2 protoRequest = CancelOrFreezeCourseRequestV2.newBuilder()
                .setQingqingOrderCourseId(OrderIdEncoder.encodeOrderId(request.getData()))
                .setReasonType(CancelOrFreezeCourseReasonType.inappropriate_cf_reason_type)
                .setExtraReason("test")
                .build();
        return ptClient.applyFreeze(protoRequest, request.getStudentId());
    }

    @RequestMapping("student/finish_class")
    @ProtoResponseBody
    public SimpleResponse finishClass(@RequestBody SimpleLongStudentRequest request) {
        OrderCourseFinishRequestV3 protoRequest = OrderCourseFinishRequestV3.newBuilder()
                .setQingqingOrderCourseId(OrderIdEncoder.encodeOrderId(request.getData()))
                .setDeviceIdentification(getDeviceIdentification())
                .build();
        return  ptClient.finishClass(protoRequest, request.getStudentId());
    }

    @RequestMapping("student/apply_cancel")
    @ProtoResponseBody
    public SimpleResponse applyCancel(@RequestBody SimpleLongStudentRequest request) {
        CancelCourseRequestV4 protoRequest = CancelCourseRequestV4.newBuilder()
                .addQingqingOrderCourseIds(OrderIdEncoder.encodeOrderId(request.getData()))
                .setReasonNumber(1)
                .setResponsibilityType(CourseChangeResponsibilityType.student_change_responsibility_type)
                .build();
        return  ptClient.applyCancel(protoRequest, request.getStudentId());
    }


    private DeviceIdentification getDeviceIdentification(){
        return DeviceIdentification.newBuilder().setPlatformType(PlatformType.ios).setImei("1").setIdfa("2").build();
    }
}