package com.qingqing.test.controller.errorcode;

import com.qingqing.common.exception.ErrorCodeInterface;

/**
 * Created by zhujianxing on 2018/8/24.
 */
public class SimpleErrorCode implements ErrorCodeInterface {
    private Integer errorCode;
    private String msg;
    private String hintMessage;

    public SimpleErrorCode(Integer errorCode, String msg, String hintMessage) {
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
