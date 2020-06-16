package com.qingqing.test.client;

import com.qingqing.api.proto.liveclass.ApiLiveClassProto.ApiCreateLiveClassRequest;
import com.qingqing.api.proto.order.ClassOrderProto.CreateClassOrderRequest;
import com.qingqing.api.proto.order.ClassOrderProto.JoinClassOrderRequest;
import com.qingqing.api.proto.order.ClassOrderProto.JoinClassOrderResponse;
import com.qingqing.api.proto.order.ClassOrderProto.TeacherDetailForLiveClassOrderResponse;
import com.qingqing.api.proto.v1.GradeCourseProto.BatchTeacherGradeCoursePriceInfoResponse;
import com.qingqing.api.proto.v1.ProtoBufResponse;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleLongDataResponse;
import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleStringResponse;
import com.qingqing.api.proto.v1.TeacherProto.SimpleTeacherIdRequest;
import com.qingqing.api.proto.v1.TeacherProto.TeacherStartEndClassV2;
import com.qingqing.api.proto.v1.course.OrderCourse.PrivateCancelCourseRequest;
import com.qingqing.api.proto.v1.order.Order;
import com.qingqing.api.proto.v1.util.Common.SimpleRepeatedLongRequest;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.config.feign.MyPiFeignConfiguration;
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
@FeignClient(value = "piClient", url = "http://{host}", configuration = MyPiFeignConfiguration.class)
public interface PiClient {

    @RequestMapping(path = "/svc/api/pi/v2/group_order/add_order", method = RequestMethod.POST)
    @ProtoResponseBody
    Order.AddGroupOrderResponse piAddOrder(Order.AddGroupOrderRequestV2 request);

    @RequestMapping(path = "{url}", method = RequestMethod.POST, produces ={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String commonRequest(@PathVariable("url") String url, @RequestBody String request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(PtRequestInterceptor.USER_TYPE) UserType userType, @RequestHeader(PtRequestInterceptor.HEADERS) String headers);

    @RequestMapping(path = "{url}", method = RequestMethod.GET, produces ={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String commonGetRequest(@PathVariable("url") String url, @RequestBody String request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(PtRequestInterceptor.USER_TYPE) UserType userType, @RequestHeader(PtRequestInterceptor.HEADERS) String headers);


    @RequestMapping(path = "{url}", method = RequestMethod.POST, produces ={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String commonRequest(@PathVariable("url") String url, @RequestBody String request, @RequestHeader(PtRequestInterceptor.HEADERS) String headers);

    @RequestMapping(path = "{url}", method = RequestMethod.GET, produces ={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String commonGetRequest(@PathVariable("url") String url, @RequestBody String request);

    @RequestMapping(path = "/svc/api/pi/v4/live_order_course/start_class", method = RequestMethod.POST)
    @ProtoResponseBody
    ProtoBufResponse.SimpleResponse startClass(TeacherStartEndClassV2 request, @RequestHeader(name = PtRequestInterceptor.TEACHER_ID) Long teacherId);

    @RequestMapping(path = "/svc/api/pi/v1/teacher/price/batch_query", method = RequestMethod.POST)
    @ProtoResponseBody
    BatchTeacherGradeCoursePriceInfoResponse batchQueryTeacherPrice(SimpleRepeatedLongRequest request);

    @RequestMapping(path = "/svc/api/pi/v1/classorder/info_for_create", method = RequestMethod.POST)
    @ProtoResponseBody
    TeacherDetailForLiveClassOrderResponse preAddClassOrder(SimpleTeacherIdRequest request);

    @RequestMapping(path = "/svc/api/pi/v1/liveclass/create", method = RequestMethod.POST)
    @ProtoResponseBody
    SimpleLongDataResponse addClass(ApiCreateLiveClassRequest request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(name = PtRequestInterceptor.USER_TYPE) UserType userType);

    @RequestMapping(path = "/svc/api/pi/v1/classorder/join", method = RequestMethod.POST)
    @ProtoResponseBody
    JoinClassOrderResponse joinClassOrder(JoinClassOrderRequest joinClassOrderRequest, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(name = PtRequestInterceptor.USER_TYPE) UserType userType);

    @RequestMapping(path = "/svc/api/pi/v1/classorder/create", method = RequestMethod.POST)
    @ProtoResponseBody
    SimpleLongDataResponse addClassOrder(CreateClassOrderRequest request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(name = PtRequestInterceptor.USER_TYPE) UserType userType);

    @RequestMapping(path = "/svc/api/pi/v4/order_course/action/assistant/apply_cancel", method = RequestMethod.POST)
    @ProtoResponseBody
    SimpleStringResponse appCancel(PrivateCancelCourseRequest request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(name = PtRequestInterceptor.USER_TYPE) UserType userType);

}


