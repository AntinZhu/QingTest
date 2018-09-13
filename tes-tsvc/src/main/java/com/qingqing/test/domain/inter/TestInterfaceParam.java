package com.qingqing.test.domain.inter;

import java.util.Date;

public class TestInterfaceParam {
    private Long id;

    private Long interfaceId;

    private String paramName;

    private String remark;

    private Integer sortDescNum;

    private String defaultValue;

    private String selectableValues;

    private Boolean isMust;

    private HtmlElementType htmlEleType;

    private ParamEncodeType encodeType;

    private Boolean isDeleted;

    private Date createTime;

    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSortDescNum() {
        return sortDescNum;
    }

    public void setSortDescNum(Integer sortDescNum) {
        this.sortDescNum = sortDescNum;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public String getSelectableValues() {
        return selectableValues;
    }

    public void setSelectableValues(String selectableValues) {
        this.selectableValues = selectableValues == null ? null : selectableValues.trim();
    }

    public Boolean getMust() {
        return isMust;
    }

    public void setMust(Boolean must) {
        isMust = must;
    }

    public HtmlElementType getHtmlEleType() {
        return htmlEleType;
    }

    public void setHtmlEleType(HtmlElementType htmlEleType) {
        this.htmlEleType = htmlEleType;
    }

    public ParamEncodeType getEncodeType() {
        return encodeType;
    }

    public void setEncodeType(ParamEncodeType encodeType) {
        this.encodeType = encodeType;
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