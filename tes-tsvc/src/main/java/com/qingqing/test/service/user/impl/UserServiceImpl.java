package com.qingqing.test.service.user.impl;

import com.qingqing.api.passort.proto.PassportLoginProto.PassportLoginResponse;
import com.qingqing.api.passort.proto.PassportLoginProto.PassportTkLoginRequestV2;
import com.qingqing.api.proto.v1.UserProto;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.test.client.PassportPiClient;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import com.qingqing.test.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2018/9/30.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private PassportPiClient passportPiClient;

    @Override
    public String encodeUser(UserType userType, Long userId) {
        PassportTkLoginRequestV2 request = PassportTkLoginRequestV2.newBuilder()
                .setUserType(UserProto.UserType.valueOf(userType.getValue()))
                .setUserId(userId).setAppPlatform("win").build();
        try{
            PassportLoginResponse response = passportPiClient.getTokenAndSession(request);
            return response.getQingqingUserId();
        }catch(Exception e){
            throw new ErrorCodeException(new SimpleErrorCode("加密用户ID失败"), "encode user fail, user_id:" + userId + ",userType:" + userType, e);
        }
    }

}
