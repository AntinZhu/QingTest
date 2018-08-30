package com.qingqing.test.feign;

import com.qingqing.common.web.util.RequestExtract;
import com.qingqing.common.web.util.ServerAuthUtil;
import feign.RequestTemplate;

/**
 * Created by zhujianxing on 2018/2/4.
 */
public class PiRequestInterceptor extends ProtoRequestInterceptor {
    @Override
    protected void doApply(RequestTemplate template) {
        long time = System.currentTimeMillis();
        template.header(RequestExtract.HEADER_TIMESTAMP, String.valueOf(time));
        template.header(RequestExtract.HEADER_AUTHKEY, ServerAuthUtil.generatorToken(time));
    }
}
