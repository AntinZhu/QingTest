package com.qingqing.test.feign;

import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.util.UserIdEncoder;
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
        template.header(RequestExtract.QINGQING_USER, UserIdEncoder.encodeUser(UserType.ta, 250L));
        template.header(RequestExtract.HEADER_TIMESTAMP, String.valueOf(time));
        template.header(RequestExtract.HEADER_AUTHKEY, ServerAuthUtil.generatorToken(time));
    }
}
