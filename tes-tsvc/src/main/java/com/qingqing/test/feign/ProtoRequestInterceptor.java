package com.qingqing.test.feign;

import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.StringUtils;
import com.qingqing.common.web.util.RequestExtract;
import com.qingqing.test.bean.base.KeyAndValue;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Collection;
import java.util.List;

/**
 * Created by zhujianxing on 2018/2/4.
 */
public abstract class ProtoRequestInterceptor implements RequestInterceptor {

    public static final String USER_ID = "userId";
    public static final String USER_TYPE = "userType";
    public static final String HEADERS = "_headers";

    @Override
    public void apply(RequestTemplate template) {
        if(template.headers().get("Content-Type") == null){
            template.header("Content-Type", "application/x-protobuf");
        }
        if(template.headers().get("Accept") == null){
            template.header("Accept", "application/x-protobuf");
        }
        template.header("qingqing_debug_mode", "true");

        Collection<String> assignHeaders =  template.headers().get(HEADERS);
        if(!CollectionsUtil.isNullOrEmpty(assignHeaders)){
            String headerJson = assignHeaders.iterator().next();
            if(!StringUtils.isEmpty(headerJson)){
                List<KeyAndValue> assignHeaderList = JsonUtil.parserJsonList(assignHeaders.iterator().next(), KeyAndValue.class);
                for (KeyAndValue keyAndValue : assignHeaderList) {
                    template.header(keyAndValue.getKey(), keyAndValue.getValue());
                }
            }
        }
        template.header(HEADERS, "");

        doApply(template);
    }

    protected abstract void doApply(RequestTemplate template);
}
