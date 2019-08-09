package com.qingqing.test.bean.config;

import com.qingqing.test.domain.config.TestCommonConfig;
import com.qingqing.test.domain.inter.TestInterfaceCatelog;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public class TestCommonConfigBean {
    private TestCommonConfig config;
    private TestInterfaceCatelog catelog;
    private List<TestInterfaceCatelog> parentCatelogList;

    public TestCommonConfig getConfig() {
        return config;
    }

    public void setConfig(TestCommonConfig config) {
        this.config = config;
    }

    public TestInterfaceCatelog getCatelog() {
        return catelog;
    }

    public void setCatelog(TestInterfaceCatelog catelog) {
        this.catelog = catelog;
    }

    public List<TestInterfaceCatelog> getParentCatelogList() {
        return parentCatelogList;
    }

    public void setParentCatelogList(List<TestInterfaceCatelog> parentCatelogList) {
        this.parentCatelogList = parentCatelogList;
    }
}
