package com.qingqing.test.client;

import com.qingqing.api.proto.v1.BankProto.GetSupportBanksResponse;
import com.qingqing.api.proto.v1.TeacherProto.SimpleQingQingTeacherIdRequest;
import com.qingqing.api.proto.v1.consult.Consult.GetPhoneNumberResponse;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.config.feign.MyPiFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Created by zhujianxing on 2017/8/15.
 */
@FeignClient(value = "apiPiClient", url = "${api.host}/api", configuration = MyPiFeignConfiguration.class)
public interface ApiPiClient {

    /*
    protobuf
     */
    @PostMapping(value = "/pb/v1/bank/list_support_banks", consumes="application/x-protobuf", produces = "application/x-protobuf")
    @ProtoResponseBody
    public GetSupportBanksResponse listBank();

    /*
    protobuf
     */
    @PostMapping(value = "/pi/v1/teacher/phone_number", consumes="application/x-protobuf", produces = "application/x-protobuf")
    @ProtoResponseBody
    public GetPhoneNumberResponse getPhoneNumber(SimpleQingQingTeacherIdRequest request, @RequestHeader(name = "AssistantId") Long assistantId);


    @PostMapping(value = "/pi/v1/teacher/xxxx")
    @ProtoResponseBody
    public String xxxx();
}
