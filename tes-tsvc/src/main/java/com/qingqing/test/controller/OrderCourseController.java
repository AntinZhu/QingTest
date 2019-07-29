package com.qingqing.test.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleResponse;
import com.qingqing.api.proto.v1.TeacherProto.TeacherStartEndClassV2;
import com.qingqing.api.proto.v1.app.AppCommon.DeviceIdentification;
import com.qingqing.api.proto.v1.app.AppCommon.PlatformType;
import com.qingqing.api.proto.v1.course.OrderCourse.CancelOrFreezeCourseReasonType;
import com.qingqing.api.proto.v1.course.OrderCourse.CancelOrFreezeCourseRequestV2;
import com.qingqing.api.proto.v1.course.OrderCourse.OrderCourseFinishMockRequest;
import com.qingqing.api.proto.v1.course.OrderCourse.OrderCourseFinishMockResponse;
import com.qingqing.api.proto.v1.course.OrderCourse.OrderCourseFinishRequestV3;
import com.qingqing.api.proto.v1.course.OrderCourse.OrderCourseThirdPartyJudgeRequestV4;
import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.common.util.UserIdEncoder;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.common.UserCommonRequest;
import com.qingqing.test.bean.common.request.SimpleLongStudentRequest;
import com.qingqing.test.bean.common.response.SingleResponse;
import com.qingqing.test.bean.ordercourse.request.StartClassRequest;
import com.qingqing.test.client.ApiPiClient;
import com.qingqing.test.client.PiClient;
import com.qingqing.test.client.PtClient;
import com.qingqing.test.domain.order.GroupUserCourseApply;
import com.qingqing.test.domain.order.OrderCourseV1;
import com.qingqing.test.manager.OrderManager;
import com.qingqing.test.manager.TestInterfaceManager;
import com.qingqing.test.service.order.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

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
    @Autowired
    private TestInterfaceManager testInterfaceManager;

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

    @RequestMapping("cancel_apply/process")
    public String processCancel(@RequestParam("qingqingBatchApplyId") String qingqingBatchApplyId, @RequestParam(value = "env", defaultValue = "dev") String env, Model model) {
        JSONObject defaultObj = new JSONObject();
        defaultObj.put("qingqing_batch_group_apply_id", qingqingBatchApplyId);
        defaultObj.put("unique_id", UUID.randomUUID());

        model.addAttribute("interfaceId", 183L);
        model.addAttribute("paramExampleId", 0);
        model.addAttribute("env", env);
        model.addAttribute("cross", 1);
        model.addAttribute("defaultObj", defaultObj.toJSONString());

        return "interface/jsonformat";
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
    public String applyCancel(@RequestBody UserCommonRequest userCommonRequest) {
        return  piClient.commonRequest(userCommonRequest.getUrl(), userCommonRequest.getParam(), userCommonRequest.getUserId(), userCommonRequest.getUserType());
    }


    private DeviceIdentification getDeviceIdentification(){
        return DeviceIdentification.newBuilder().setPlatformType(PlatformType.ios).setImei("1").setIdfa("2").build();
    }

    @RequestMapping("change_apply/page")
    public String changeApplyPage(@RequestParam("groupOrderCourseId") Long groupOrderCourseId, @RequestParam("orderId") Long orderId, @RequestParam(value = "env", defaultValue = "dev") String env, Model model){
        List<OrderCourseV1> orderCourseV1s = orderCourseService.selectByOrderId(orderId);
        OrderCourseV1 applyOne = null;
        for (OrderCourseV1 orderCourseV1 : orderCourseV1s) {
            if(orderCourseV1.getGroupOrderCourseId().equals(groupOrderCourseId)){
                applyOne = orderCourseV1;
            }
        }

        if(applyOne == null){
            throw new RequestValidateException("unknown groupOrderCourse", "unknown groupOrderCourse");
        }

        JSONObject newTime = new JSONObject();
        newTime.put("date", TimeUtil.dateToString(TimeUtil.dayAfter(applyOne.getDate(), 1), TimeUtil.DATE_TO_YEAR_MONTH_DAY));
        newTime.put("start_block", applyOne.getStartBlock());
        newTime.put("end_block", applyOne.getEndBlock());

        JSONObject item = new JSONObject();
        item.put("qingqing_group_order_course_id", OrderIdEncoder.encodeOrderId(groupOrderCourseId));
        item.put("qingqing_teacher_id", UserIdEncoder.encodeUser(UserType.teacher, applyOne.getTeacherId()));
        item.put("new_time_param", newTime);
        item.put("site_type", applyOne.getSiteType() + "_ost");

        JSONArray array = new JSONArray();
        array.add(item);

        JSONObject defaultObj = new JSONObject();
        defaultObj.put("items", array);

        model.addAttribute("interfaceId", 259L);
        model.addAttribute("paramExampleId", 0);
        model.addAttribute("env", env);
        model.addAttribute("cross", 1);
        model.addAttribute("defaultObj", defaultObj.toJSONString());
        model.addAttribute("userId", applyOne.getAssistantId());
        model.addAttribute("userType", "ta");

        return "interface/jsonformat";
    }

    @RequestMapping("change_apply/apply_page")
    public String changeApplyPageApply(@RequestParam("groupOrderCourseId") Long groupOrderCourseId, @RequestParam("orderId") Long orderId, @RequestParam(value = "env", defaultValue = "dev") String env, Model model){
        List<OrderCourseV1> orderCourseV1s = orderCourseService.selectByOrderId(orderId);
        OrderCourseV1 applyOne = null;
        for (OrderCourseV1 orderCourseV1 : orderCourseV1s) {
            if(orderCourseV1.getGroupOrderCourseId().equals(groupOrderCourseId)){
                applyOne = orderCourseV1;
            }
        }

        if(applyOne == null){
            throw new RequestValidateException("unknown groupOrderCourse", "unknown groupOrderCourse");
        }

        JSONObject newTime = new JSONObject();
        newTime.put("date", TimeUtil.dateToString(TimeUtil.dayAfter(applyOne.getDate(), 1), TimeUtil.DATE_TO_YEAR_MONTH_DAY));
        newTime.put("start_block", applyOne.getStartBlock());
        newTime.put("end_block", applyOne.getEndBlock());

        JSONObject defaultObj = new JSONObject();
        defaultObj.put("qingqing_group_order_course_id", OrderIdEncoder.encodeOrderId(groupOrderCourseId));
        defaultObj.put("qingqing_teacher_id", UserIdEncoder.encodeUser(UserType.teacher, applyOne.getTeacherId()));
        defaultObj.put("new_time_param", newTime);
        defaultObj.put("site_type", applyOne.getSiteType() + "_ost");

        model.addAttribute("interfaceId", 260L);
        model.addAttribute("paramExampleId", 0);
        model.addAttribute("env", env);
        model.addAttribute("cross", 1);
        model.addAttribute("defaultObj", defaultObj.toJSONString());
        model.addAttribute("userId", applyOne.getTeacherId());
        model.addAttribute("userType", "teacher");

        return "interface/jsonformat";
    }
}