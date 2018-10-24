package com.qingqing.test.client;

import com.qingqing.api.passort.proto.PassportLoginProto.PassportLoginResponse;
import com.qingqing.api.passort.proto.PassportLoginProto.PassportTkLoginRequestV2;
import com.qingqing.api.passort.proto.PassportQueryProto.PassportQueryUserInfoRequest;
import com.qingqing.api.passort.proto.PassportQueryProto.PassportQueryUserInfoResponse;
import com.qingqing.api.passort.proto.PassportQueryProto.PassportQueryUserPhoneNumberRequest;
import com.qingqing.api.passort.proto.PassportQueryProto.PassportQueryUserPhoneNumberResponse;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.config.feign.MyPiFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by zhujianxing on 2017/8/15.
 */
@FeignClient(value = "passportPiClient", url = "http://gateway.{env}.idc.cedu.cn/passportsvc/api/pi", configuration = MyPiFeignConfiguration.class)
public interface PassportPiClient {

    /*
    protobuf
     */
    @PostMapping(value = "/v2/auth/refresh", consumes="application/x-protobuf", produces = "application/x-protobuf")
    @ProtoResponseBody
    PassportLoginResponse getTokenAndSession(PassportTkLoginRequestV2 request);

     /*
    protobuf
     */
    @PostMapping(value = "/v1/account/query_user_phone_number", consumes="application/x-protobuf", produces = "application/x-protobuf")
    @ProtoResponseBody
    PassportQueryUserPhoneNumberResponse queryUserPhoneNumber(PassportQueryUserPhoneNumberRequest request);

    /*
   protobuf
    */
    @PostMapping(value = "/v1/account/query_user_by_account", consumes="application/x-protobuf", produces = "application/x-protobuf")
    @ProtoResponseBody
    PassportQueryUserInfoResponse queryUserByAccount(PassportQueryUserInfoRequest request);
}
