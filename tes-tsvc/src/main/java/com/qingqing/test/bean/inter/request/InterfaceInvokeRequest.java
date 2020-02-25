package com.qingqing.test.bean.inter.request;

import com.qingqing.common.auth.domain.UserType;
import com.qingqing.test.bean.base.KeyAndValue;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public class InterfaceInvokeRequest {
    private Long interfaceId;
    private Long requestUserId;
    private UserType requestUserType;
    private String param;
    private List<KeyAndValue> headers;
    private String requestUrl;

    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

    public Long getRequestUserId() {
        return requestUserId;
    }

    public void setRequestUserId(Long requestUserId) {
        this.requestUserId = requestUserId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public UserType getRequestUserType() {
        return requestUserType;
    }

    public void setRequestUserType(UserType requestUserType) {
        this.requestUserType = requestUserType;
    }

    public List<KeyAndValue> getHeaders() {
        return headers;
    }

    public void setHeaders(List<KeyAndValue> headers) {
        this.headers = headers;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
