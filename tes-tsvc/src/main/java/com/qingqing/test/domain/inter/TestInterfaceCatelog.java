package com.qingqing.test.domain.inter;

import com.qingqing.common.intf.Composer;

import java.util.Date;

public class TestInterfaceCatelog {

    public static Composer<Long, TestInterfaceCatelog> ID_COMPOSER = new Composer<Long, TestInterfaceCatelog>() {
        @Override
        public Long getComposerId(TestInterfaceCatelog testInterfaceCatelog) {
            return testInterfaceCatelog.getId();
        }
    };

    public static Composer<CatelogRef, TestInterfaceCatelog> REF_COMPOSER = new Composer<CatelogRef, TestInterfaceCatelog>() {
        @Override
        public CatelogRef getComposerId(TestInterfaceCatelog testInterfaceCatelog) {
            return new CatelogRef(testInterfaceCatelog.getRefType(), testInterfaceCatelog.getRefValue());
        }
    };

    public static class CatelogRef{
        private CatelogRefType refType;
        private String refValue;

        public CatelogRef(CatelogRefType refType, String refValue) {
            this.refType = refType;
            this.refValue = refValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CatelogRef)) return false;

            CatelogRef that = (CatelogRef) o;

            if (refType != that.refType) return false;
            return refValue != null ? refValue.equals(that.refValue) : that.refValue == null;
        }

        @Override
        public int hashCode() {
            int result = refType.hashCode();
            result = 31 * result + (refValue != null ? refValue.hashCode() : 0);
            return result;
        }
    }

    private Long id;
    private String catelogName;
    private Integer sortNum;
    private CatelogRefType refType;
    private String refValue;
    private Integer subItemCnt;
    private Long parentCatelogId;
    private Boolean isDeleted;
    private Date createTime;
    private String clazz;
    private Date lastUpdateTime;
    private String cacheCatelogIndex;

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

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCacheCatelogIndex() {
        return cacheCatelogIndex;
    }

    public void setCacheCatelogIndex(String cacheCatelogIndex) {
        this.cacheCatelogIndex = cacheCatelogIndex;
    }
}