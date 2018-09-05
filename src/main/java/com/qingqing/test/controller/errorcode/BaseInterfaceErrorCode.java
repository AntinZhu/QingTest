package com.qingqing.test.controller.errorcode;

import com.qingqing.common.exception.ErrorCodeInterface;

/**
 * Created by zhujianxing on 2018/8/24.
 */
public enum BaseInterfaceErrorCode implements ErrorCodeInterface {
    get_token_session_fail(1001, "not support mock third pay notify", "获取Token,Session失败，请检查配置和相关服务"),
    ;

    private Integer errorCode;
    private String msg;
    private String hintMessage;

    BaseInterfaceErrorCode(Integer errorCode, String msg, String hintMessage) {
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
