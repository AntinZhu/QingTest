package com.qingqing.test.bean.inter;

import com.qingqing.test.domain.inter.TestInterface;

/**
 * Created by zhujianxing on 2018/9/28.
 */
public class SaveInterfaceBean {
    private TestInterface inter;
    private Long parentCatelogId;
    private String catelogName;
    private String clazz;

    public TestInterface getInter() {
        return inter;
    }

    public void setInter(TestInterface inter) {
        this.inter = inter;
    }

    public Long getParentCatelogId() {
        return parentCatelogId;
    }

    public void setParentCatelogId(Long parentCatelogId) {
        this.parentCatelogId = parentCatelogId;
    }

    public String getCatelogName() {
        return catelogName;
    }

    public void setCatelogName(String catelogName) {
        this.catelogName = catelogName;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
