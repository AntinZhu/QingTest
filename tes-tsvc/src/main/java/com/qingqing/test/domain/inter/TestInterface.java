package com.qingqing.test.domain.inter;

import com.qingqing.common.auth.domain.UserType;

import java.util.Date;

public class TestInterface {
    private Long id;
    private String interfaceName;
    private String interfaceUrl;
    private InterfaceType interfaceType;
    private RequestType requestType;
    private UserType requestUserType;
    private Integer sortDescNum;
    private String catelogIndex;
    private String paramDetail;
    private Boolean isDeleted;
    private String nextPageUrl;
    private Date createTime;
    private Date lastUpdateTime;
    private String paramClassName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName == null ? null : interfaceName.trim();
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl == null ? null : interfaceUrl.trim();
    }

    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(InterfaceType interfaceType) {
        this.interfaceType = interfaceType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public UserType getRequestUserType() {
        return requestUserType;
    }

    public void setRequestUserType(UserType requestUserType) {
        this.requestUserType = requestUserType;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getSortDescNum() {
        return sortDescNum;
    }

    public void setSortDescNum(Integer sortDescNum) {
        this.sortDescNum = sortDescNum;
    }

    public String getCatelogIndex() {
        return catelogIndex;
    }

    public void setCatelogIndex(String catelogIndex) {
        this.catelogIndex = catelogIndex;
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

    public String getParamDetail() {
        return paramDetail;
    }

    public void setParamDetail(String paramDetail) {
        this.paramDetail = paramDetail;
    }

    public String getParamClassName() {
        return paramClassName;
    }

    public void setParamClassName(String paramClassName) {
        this.paramClassName = paramClassName;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }
}