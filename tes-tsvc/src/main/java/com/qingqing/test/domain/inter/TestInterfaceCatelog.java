package com.qingqing.test.domain.inter;

import com.qingqing.common.intf.Composer;

import java.util.Date;

public class TestInterfaceCatelog {

    public static Composer<String, TestInterfaceCatelog> INDEX_COMPOSER = new Composer<String, TestInterfaceCatelog>() {
        @Override
        public String getComposerId(TestInterfaceCatelog testInterfaceCatelog) {
            return testInterfaceCatelog.getCatelogIndex();
        }
    };

    public static Composer<Long, TestInterfaceCatelog> ID_COMPOSER = new Composer<Long, TestInterfaceCatelog>() {
        @Override
        public Long getComposerId(TestInterfaceCatelog testInterfaceCatelog) {
            return testInterfaceCatelog.getId();
        }
    };

    private Long id;
    private String catelogName;
    private String catelogIndex;
    private Integer sortNum;
    private CatelogRefType refType;
    private String refValue;
    private Integer subItemCnt;
    private Long parentCatelogId;
    private Boolean isDeleted;
    private Date createTime;

    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatelogName() {
        return catelogName;
    }

    public void setCatelogName(String catelogName) {
        this.catelogName = catelogName == null ? null : catelogName.trim();
    }

    public String getCatelogIndex() {
        return catelogIndex;
    }

    public void setCatelogIndex(String catelogIndex) {
        this.catelogIndex = catelogIndex;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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

    public Integer getSubItemCnt() {
        return subItemCnt;
    }

    public void setSubItemCnt(Integer subItemCnt) {
        this.subItemCnt = subItemCnt;
    }

    public Long getParentCatelogId() {
        return parentCatelogId;
    }

    public void setParentCatelogId(Long parentCatelogId) {
        this.parentCatelogId = parentCatelogId;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}