package com.qingqing.test.bean.pay;

/**
 * Created by zhujianxing on 2018/2/12.
 */
public class ThirdPayBriefBean {

    private String payTypeKey;
    private String payTypeName;
    private String qingqingTradeNo;
    private String payStatus;
    private String tradeId;

    public String getPayTypeKey() {
        return payTypeKey;
    }

    public void setPayTypeKey(String payTypeKey) {
        this.payTypeKey = payTypeKey;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getQingqingTradeNo() {
        return qingqingTradeNo;
    }

    public void setQingqingTradeNo(String qingqingTradeNo) {
        this.qingqingTradeNo = qingqingTradeNo;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
}
