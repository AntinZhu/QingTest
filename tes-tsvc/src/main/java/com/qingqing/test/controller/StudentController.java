package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.StudentProto.SimpleQingQingStudentIdRequest;
import com.qingqing.api.proto.v1.UserAddress.QueryUserAddressResponse;
import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.util.UserIdEncoder;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.client.PtClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhujianxing on 2018/2/6.
 */
@Controller
@RequestMapping("/v1/student")
public class StudentController {

    @Autowired
    private PtClient ptClient;

    @RequestMapping("/addresses")
    @ProtoResponseBody
    public QueryUserAddressResponse studentAddress(@ProtoRequestBody SimpleLongRequest request){
        Long studentId = request.getData();
        SimpleQingQingStudentIdRequest requestProto = SimpleQingQingStudentIdRequest.newBuilder()
                .setQingqingStudentId(UserIdEncoder.encodeUser(UserType.student, studentId))
                .build();
        return ptClient.studentAddresses(requestProto, studentId);
    }
}
