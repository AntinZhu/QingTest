package com.qingqing.test.feign;

import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.util.UserIdEncoder;
import com.qingqing.common.web.util.RequestExtract;
import com.qingqing.common.web.util.ServerAuthUtil;
import feign.RequestTemplate;

import java.util.Collection;
import java.util.Map;

/**
 * Created by zhujianxing on 2018/2/4.
 */
public class PiRequestInterceptor extends ProtoRequestInterceptor {
    @Override
    protected void doApply(RequestTemplate template) {
        Map<String, Collection<String>> headers = template.headers();

        Collection<String> userTypeParams  = headers.get(USER_TYPE);
        Collection<String> userIdParams  = headers.get(USER_ID);
        if(!CollectionsUtil.isNullOrEmpty(userTypeParams) && !CollectionsUtil.isNullOrEmpty(userIdParams)) {
            Long userId = Long.valueOf(userIdParams.iterator().next());
            UserType userType = UserType.valueOf(userTypeParams.iterator().next());
            template.header(RequestExtract.QINGQING_USER, UserIdEncoder.encodeUser(userType, userId));
        }else{
            template.header(RequestExtract.QINGQING_USER, UserIdEncoder.encodeUser(UserType.ta, 250L));
        }

        long time = System.currentTimeMillis();
        template.header(RequestExtract.HEADER_TIMESTAMP, String.valueOf(time));
        template.header(RequestExtract.HEADER_AUTHKEY, ServerAuthUtil.generatorToken(time));
    }
}
