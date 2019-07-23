package com.qingqing.test.bean.pay.request;

/**
 * Created by zhujianxing on 2018/8/23.
 */
public class PayRequestBeanV2 {
    private String qingqingOrderId;
    private String orderType;
    private Double orderAmount;
    private Long userId;
    private String userType;
    private String payType;
    private Long stageConfigId;
    private Double balancePayAmount;
    private Double multiPayAmount;
    private String sourceChannel;
    private String multipleMode;
    private String nextPayType;

    public String getQingqingOrderId() {
        return qingqingOrderId;
    }

    public void setQingqingOrderId(String qingqingOrderId) {
        this.qingqingOrderId = qingqingOrderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Long getStageConfigId() {
        return stageConfigId;
    }

    public void setStageConfigId(Long stageConfigId) {
        this.stageConfigId = stageConfigId;
    }

    public Double getBalancePayAmount() {
        return balancePayAmount;
    }

    public void setBalancePayAmount(Double balancePayAmount) {
        this.balancePayAmount = balancePayAmount;
    }

    public Double getMultiPayAmount() {
        return multiPayAmount;
    }

    public void setMultiPayAmount(Double multiPayAmount) {
        this.multiPayAmount = multiPayAmount;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getMultipleMode() {
        return multipleMode;
    }

    public void setMultipleMode(String multipleMode) {
        this.multipleMode = multipleMode;
    }

    public String getNextPayType() {
        return nextPayType;
    }

    public void setNextPayType(String nextPayType) {
        this.nextPayType = nextPayType;
    }
}
