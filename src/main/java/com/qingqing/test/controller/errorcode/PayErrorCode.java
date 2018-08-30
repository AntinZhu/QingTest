package com.qingqing.test.controller.errorcode;

import com.qingqing.common.exception.ErrorCodeInterface;

/**
 * Created by zhujianxing on 2018/8/24.
 */
public enum PayErrorCode implements ErrorCodeInterface {
    not_usupport_mock_notify_type(1001, "not support mock third pay notify", "当前支付方式暂不支持模拟回调"),
    ;

    private Integer errorCode;
    private String msg;
    private String hintMessage;

    PayErrorCode(Integer errorCode, String msg, String hintMessage) {
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
