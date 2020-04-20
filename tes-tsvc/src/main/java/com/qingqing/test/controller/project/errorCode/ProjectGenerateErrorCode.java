package com.qingqing.test.controller.project.errorCode;

import com.qingqing.common.exception.ErrorCodeInterface;

/**
 * Created by zhujianxing on 2020/4/20.
 */
public enum ProjectGenerateErrorCode implements ErrorCodeInterface {
    fail(1001, "fail", "生成项目失败，请联系朱建星");

    private Integer errorCode;
    private String msg;
    private String hintMsg;

    ProjectGenerateErrorCode(Integer errorCode, String msg, String hintMsg) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.hintMsg = hintMsg;
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
        return hintMsg;
    }
}
