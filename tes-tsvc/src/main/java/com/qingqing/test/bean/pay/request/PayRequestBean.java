package com.qingqing.test.bean.pay.request;

/**
 * Created by zhujianxing on 2018/8/23.
 */
public class PayRequestBean {
    private String qingqingOrderId;
    private Integer coursePriceType;
    private Double orderAmount;
    private Long studentId;
    private String payType;
    private Long stageConfigId;
    private Double balancePayAmount;
    private Integer sourceChannel;

    public String getQingqingOrderId() {
        return qingqingOrderId;
    }

    public void setQingqingOrderId(String qingqingOrderId) {
        this.qingqingOrderId = qingqingOrderId;
    }

    public Integer getCoursePriceType() {
        return coursePriceType;
    }

    public void setCoursePriceType(Integer coursePriceType) {
        this.coursePriceType = coursePriceType;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public Integer getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(Integer sourceChannel) {
        this.sourceChannel = sourceChannel;
    }
}
