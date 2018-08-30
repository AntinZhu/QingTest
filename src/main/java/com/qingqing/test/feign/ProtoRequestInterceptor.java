package com.qingqing.test.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Created by zhujianxing on 2018/2/4.
 */
public abstract class ProtoRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("Content-Type", "application/x-protobuf");
        template.header("Accept", "application/x-protobuf");

        doApply(template);
    }

    protected abstract void doApply(RequestTemplate template);
}
