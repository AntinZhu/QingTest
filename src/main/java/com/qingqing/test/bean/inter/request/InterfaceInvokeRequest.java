package com.qingqing.test.bean.inter.request;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public class InterfaceInvokeRequest {
    private Long interfaceId;
    private Long requestUserId;
    private List<InterfaceInvokeParam> paramList;

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

    public List<InterfaceInvokeParam> getParamList() {
        return paramList;
    }

    public void setParamList(List<InterfaceInvokeParam> paramList) {
        this.paramList = paramList;
    }

    public static class InterfaceInvokeParam{
        private Long paramId;
        private String paramValue;

        public Long getParamId() {
            return paramId;
        }

        public void setParamId(Long paramId) {
            this.paramId = paramId;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }
    }
}
