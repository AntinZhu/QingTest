package com.qingqing.test.bean.inter;

import com.qingqing.test.domain.inter.TestInterface;

/**
 * Created by zhujianxing on 2018/9/28.
 */
public class SaveCatelogBean {
    private Long parentCatelogId;
    private String catelogName;

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
}
