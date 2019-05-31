package com.qingqing.test.domain.mock;


import java.util.Date;

/**
 * Created by zhujianxing on 2018/6/12.
 */
public class MockRule implements Comparable<MockRule>{

    private Integer id;
    private InterfaceType interfaceType;
    private RuleType ruleType;
    private String ruleValue;
    private Integer ruleOrderNum;
    private String resp;
    private Boolean isNeedRecordOrder;
    private Boolean isDeleted;
    private Date createTime;
    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(InterfaceType interfaceType) {
        this.interfaceType = interfaceType;
    }

    public RuleType getRuleType() {
        return ruleType;
    }

    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }

    public Integer getRuleOrderNum() {
        return ruleOrderNum;
    }

    public void setRuleOrderNum(Integer ruleOrderNum) {
        this.ruleOrderNum = ruleOrderNum;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public Boolean getNeedRecordOrder() {
        return isNeedRecordOrder;
    }

    public void setNeedRecordOrder(Boolean needRecordOrder) {
        isNeedRecordOrder = needRecordOrder;
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

    @Override
    public int compareTo(MockRule o) {
        if(o.getRuleOrderNum() == getRuleOrderNum()){
            return o.getId() - getId();
        }

        return o.getRuleOrderNum() - getRuleOrderNum();
    }
}
