package com.qingqing.test.bean.pay.ahm;

import com.qingqing.test.bean.base.InterfaceBaseResponse;
import com.qingqing.test.bean.base.KeyAndValue;

import java.util.List;

/**
 * Created by zhujianxing on 2018/2/9.
 */
public class AhmNotifyRequest {
    private String appId;
    private String projectNo;
    private Integer auditStatus;
    private String desc;
    private Integer term;
    private Integer amt;
    private String signvalue;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }

    public String getSignvalue() {
        return signvalue;
    }

    public void setSignvalue(String signvalue) {
        this.signvalue = signvalue;
    }
}