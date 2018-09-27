package com.qingqing.test.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Created by zhujianxing on 2018/2/4.
 */
public abstract class ProtoRequestInterceptor implements RequestInterceptor {

    public static final String USER_ID = "userId";
    public static final String USER_TYPE = "userType";

    @Override
    public void apply(RequestTemplate template) {
        if(template.headers().get("Content-Type") == null){
            template.header("Content-Type", "application/x-protobuf");
        }
        if(template.headers().get("Accept") == null){
            template.header("Accept", "application/x-protobuf");
        }

        doApply(template);
    }

    protected abstract void doApply(RequestTemplate template);
}
