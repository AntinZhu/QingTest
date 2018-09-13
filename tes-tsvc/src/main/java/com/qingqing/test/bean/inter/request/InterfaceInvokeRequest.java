package com.qingqing.test.bean.inter.request;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public class InterfaceInvokeRequest {
    private String env;
    private Long interfaceId;
    private Long requestUserId;
    private String param;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

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
}
