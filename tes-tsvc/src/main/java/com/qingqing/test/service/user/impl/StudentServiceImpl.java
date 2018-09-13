package com.qingqing.test.service.user.impl;

import com.qingqing.api.passort.proto.PassportQueryProto.PassportQueryUserPhoneNumberRequest;
import com.qingqing.api.passort.proto.PassportQueryProto.PassportQueryUserPhoneNumberResponse;
import com.qingqing.api.proto.v1.UserProto.User;
import com.qingqing.api.proto.v1.UserProto.UserType;
import com.qingqing.test.client.PassportPiClient;
import com.qingqing.test.service.user.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2018/9/13.
 */
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private PassportPiClient passportPiClient;

    @Override
    public boolean isUserExist(Long studentId) {
        PassportQueryUserPhoneNumberRequest request = PassportQueryUserPhoneNumberRequest.newBuilder()
                .addUsers(User.newBuilder().setUserType(UserType.student).setUserId(studentId)).build();
        PassportQueryUserPhoneNumberResponse response = passportPiClient.queryUserPhoneNumber(request);
        return response.getInfosCount() > 0;
    }
}
