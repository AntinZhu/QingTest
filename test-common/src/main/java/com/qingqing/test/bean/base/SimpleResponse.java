package com.qingqing.test.bean.base;

public class SimpleResponse extends InterfaceBaseResponse{

    public static final SimpleResponse SUCC = new SimpleResponse(BaseResponse.SUCC_RESP);

    public SimpleResponse(){}

    public SimpleResponse(BaseResponse response) {
        super(response);
    }

}
