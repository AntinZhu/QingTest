package com.qingqing.test.bean.base;

public class InterfaceBaseResponse {

    private BaseResponse response = BaseResponse.SUCC_RESP;

    public BaseResponse getResponse() {
        return response;
    }

    public void setResponse(BaseResponse response) {
        this.response = response;
    }
}
