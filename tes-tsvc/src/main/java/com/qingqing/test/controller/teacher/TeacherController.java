package com.qingqing.test.controller.teacher;

import com.qingqing.api.proto.v1.StudentProto.SimpleQingQingStudentIdRequest;
import com.qingqing.api.proto.v1.UserAddress.QueryUserAddressResponse;
import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.common.response.ListResponse;
import com.qingqing.test.bean.teacher.SetTeacherTagRequest;
import com.qingqing.test.client.ApiPiClient;
import com.qingqing.test.client.PtClient;
import com.qingqing.test.domain.teacher.TeacherAttributeTag;
import com.qingqing.test.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/6.
 */
@Controller
@RequestMapping("/v1/teacher")
public class TeacherController {

    @Autowired
    private PtClient ptClient;
    @Autowired
    private UserService userService;
    @Autowired
    private ApiPiClient apiPiClient;

    @RequestMapping("/addresses")
    @ProtoResponseBody
    public QueryUserAddressResponse studentAddress(@RequestParam("studentId") Long studentId){
        SimpleQingQingStudentIdRequest request = SimpleQingQingStudentIdRequest.newBuilder()
                .setQingqingStudentId(userService.encodeUser(UserType.student, studentId))
                .build();
        return ptClient.studentAddresses(request, studentId);
    }

    @RequestMapping("/tag/set")
    @ResponseBody
    public SimpleResponse setTag(@RequestBody SetTeacherTagRequest request){
        return apiPiClient.setTag(request.getTeacherId(), request.getTagType(), request.getTagValue());
    }

    @RequestMapping("/tag/list")
    @ResponseBody
    public ListResponse<TeacherAttributeTag> tagList(@ProtoRequestBody SimpleLongRequest request){
        return apiPiClient.tagList(request);
    }

    @RequestMapping("/tag")
    public String tagPage(){
        return "teacher/tag";
    }
}
