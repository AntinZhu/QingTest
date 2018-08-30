package com.qingqing.test.controller.errorcode;

import com.qingqing.common.exception.ErrorCodeInterface;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public enum TestInterfaceErrorCode implements ErrorCodeInterface{
    unknown_test_interface(1001, "unknown test interface", "未找到对应的接口"),
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
