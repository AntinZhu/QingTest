package com.qingqing.test.bean.inter.response;

import com.qingqing.test.bean.base.InterfaceBaseResponse;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public class TestInterfaceResponse extends InterfaceBaseResponse {
    private TestInterfaceBean interfaceInfo;

    public TestInterfaceBean getInterfaceInfo() {
        return interfaceInfo;
    }

    public void setInterfaceInfo(TestInterfaceBean interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
    }
}
