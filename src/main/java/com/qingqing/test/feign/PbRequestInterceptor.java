package com.qingqing.test.feign;

import feign.RequestTemplate;

/**
 * Created by zhujianxing on 2018/2/4.
 */
public class PbRequestInterceptor extends ProtoRequestInterceptor {
    @Override
    protected void doApply(RequestTemplate template) {
      // nothing
    }
}
