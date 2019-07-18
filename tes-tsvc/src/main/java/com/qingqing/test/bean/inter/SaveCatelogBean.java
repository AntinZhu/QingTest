package com.qingqing.test.bean.inter;

import com.qingqing.test.domain.inter.CatelogRefType;

/**
 * Created by zhujianxing on 2018/9/28.
 */
public class SaveCatelogBean {
    private Long parentCatelogId;
    private String catelogName;
    private CatelogRefType refType;
    private String refValue;

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

    public CatelogRefType getRefType() {
        return refType;
    }

    public void setRefType(CatelogRefType refType) {
        this.refType = refType;
    }

    public String getRefValue() {
        return refValue;
    }

    public void setRefValue(String refValue) {
        this.refValue = refValue;
    }
}
