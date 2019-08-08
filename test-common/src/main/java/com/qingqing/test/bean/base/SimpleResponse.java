package com.qingqing.test.bean.base;

public class SimpleResponse {

    public static final SimpleResponse SUCC = new SimpleResponse(BaseResponse.SUCC_RESP);

    private BaseResponse response;

    public SimpleResponse(){}

    public SimpleResponse(BaseResponse response) {
        this.response = response;
    }

    public BaseResponse getResponse() {
        return response;
    }

    public void setResponse(BaseResponse response) {
        this.response = response;
    }
}
