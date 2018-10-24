package com.qingqing.test.service.user.impl;

import com.qingqing.api.passort.proto.PassportLoginProto.PassportLoginResponse;
import com.qingqing.api.passort.proto.PassportLoginProto.PassportTkLoginRequestV2;
import com.qingqing.api.passort.proto.PassportQueryProto.PassportQueryUserInfoRequest;
import com.qingqing.api.passort.proto.PassportQueryProto.PassportQueryUserInfoResponse;
import com.qingqing.api.passort.proto.PassportQueryProto.PassportQueryUserPhoneNumberRequest;
import com.qingqing.api.passort.proto.PassportQueryProto.PassportQueryUserPhoneNumberResponse;
import com.qingqing.api.proto.v1.PassportCommon.PassportAccountType;
import com.qingqing.api.proto.v1.UserProto;
import com.qingqing.common.auth.domain.User;
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
            if(response.getResponse().getErrorCode() != 0){
                return null;
            }else{
                return response.getQingqingUserId();
            }
        }catch(Exception e){
            throw new ErrorCodeException(new SimpleErrorCode("加密用户ID失败"), "encode user fail, user_id:" + userId + ",userType:" + userType, e);
        }
    }

    @Override
    public String getUserPhone(UserType userType, Long userId) {
        PassportQueryUserPhoneNumberRequest request = PassportQueryUserPhoneNumberRequest.newBuilder().
                addUsers(UserProto.User.newBuilder().
                setUserId(userId).setUserType(UserProto.UserType.valueOf(userType.getValue()))).
                build();
        PassportQueryUserPhoneNumberResponse response = passportPiClient.queryUserPhoneNumber(request);
        if(response.getInfosCount() == 0){
            return null;
        }else{
            return response.getInfos(0).getPhoneNumber();
        }
    }

    @Override
    public User getUserByPhone(UserType userType, String phoneNumber) {
        PassportQueryUserInfoRequest request = PassportQueryUserInfoRequest.newBuilder().setAccountType(PassportAccountType.phone_passport_account_type)
                .setUserType(UserProto.UserType.valueOf(userType.getValue()))
                .addAccountNames(phoneNumber)
                .build();
        PassportQueryUserInfoResponse response = passportPiClient.queryUserByAccount(request);
        if(response.getAccountInfosCount() == 0){
            return null;
        }else{
            UserProto.User user = response.getAccountInfos(0).getUser();
            return new User(UserType.valueOf(user.getUserType().getNumber()), user.getUserId());
        }
    }
}
