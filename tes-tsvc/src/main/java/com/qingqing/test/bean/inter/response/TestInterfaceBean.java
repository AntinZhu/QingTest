package com.qingqing.test.bean.inter.response;

import com.qingqing.test.domain.inter.TestInterface;
import com.qingqing.test.domain.inter.TestInterfaceCatelog;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public class TestInterfaceBean {
    private TestInterface inter;
    private TestInterfaceCatelog catelog;
    private List<TestInterfaceCatelog> parentCatelogList;

    public TestInterface getInter() {
        return inter;
    }

    public void setInter(TestInterface inter) {
        this.inter = inter;
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
