package com.qingqing.test.service.pay.impl;

import com.qingqing.api.proto.v1.ProtoBufResponse.SimpleResponse;
import com.qingqing.api.proto.v1.RechargeProto.OperateUserWalletRequest;
import com.qingqing.api.proto.v1.RechargeProto.OperateUserWalletRequest.OperateUserWalletType;
import com.qingqing.api.proto.v1.UserProto.User;
import com.qingqing.api.proto.v1.UserProto.UserType;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.client.ApiPiClient;
import com.qingqing.test.service.pay.StudentBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2018/8/28.
 */
@Component
public class StudentBalanceServiceImpl implements StudentBalanceService {

    @Autowired
    private ApiPiClient apiPiClient;

    @Override
    public boolean addUserBalance(Long userId, Double addAmount) {
        OperateUserWalletRequest request = OperateUserWalletRequest.newBuilder()
                .setAmount(addAmount)
                .setOperateType(OperateUserWalletType.recharge_wallet_inout_type)
                .setRemark("测试服务-支付订单")
                .setSerialNo(String.valueOf(System.currentTimeMillis()))
                .setUser(User.newBuilder().setUserId(userId).setUserType(UserType.student))
                .build();
        SimpleResponse response = apiPiClient.studentWalletOps(request);
        return response.getResponse().getErrorCode() == BaseResponse.SUCC_CODE;
    }
}