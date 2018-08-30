package com.qingqing.test.bean.inter.response;

import com.qingqing.test.domain.inter.TestInterface;
import com.qingqing.test.domain.inter.TestInterfaceParam;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public class TestInterfaceBean {
    private TestInterface inter;
    private List<TestInterfaceParam> params;

    public TestInterface getInter() {
        return inter;
    }

    public void setInter(TestInterface inter) {
        this.inter = inter;
    }

    public List<TestInterfaceParam> getParams() {
        return params;
    }

    public void setParams(List<TestInterfaceParam> params) {
        this.params = params;
    }
}
