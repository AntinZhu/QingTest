package com.qingqing.test.controller.errorcode;

import com.qingqing.common.exception.ErrorCodeInterface;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public enum TestInterfaceErrorCode implements ErrorCodeInterface{
    unknown_test_interface(1001, "unknown test interface", "未找到对应的接口"),
    unsupport_interface_type(1002, "unsupport interface type", "当前暂不支持该接口类型"),
    unsupport_request_user_type(1003, "unsupport request user type", "当前暂不支持该请求用户类型"),
    ;

    private Integer errorCode;
    private String msg;
    private String hintMessage;

    TestInterfaceErrorCode(Integer errorCode, String msg, String hintMessage) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.hintMessage = hintMessage;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getHintMessage() {
        return hintMessage;
    }
}
