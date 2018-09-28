package com.qingqing.test.domain.inter;

import java.util.Date;

public class TestInterfaceCatelog {
    private Long id;
    private String catelogName;
    private String catelogIndex;
    private String linkUrl;
    private Integer sortDescNum;
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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getSortDescNum() {
        return sortDescNum;
    }

    public void setSortDescNum(Integer sortDescNum) {
        this.sortDescNum = sortDescNum;
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