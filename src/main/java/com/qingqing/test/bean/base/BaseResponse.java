package com.qingqing.test.bean.base;

public class BaseResponse {
    public static final Integer SUCC_CODE = 0;
    public static BaseResponse SUCC_RESP = new BaseResponse(SUCC_CODE, "", "");

    private Integer error_code;
    private String error_message;
    private String hint_message;

    public BaseResponse(){}

    public BaseResponse(Integer error_code, String error_message, String hint_message) {
        this.error_code = error_code;
        this.error_message = error_message;
        this.hint_message = hint_message;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getHint_message() {
        return hint_message;
    }

    public void setHint_message(String hint_message) {
        this.hint_message = hint_message;
    }
}
