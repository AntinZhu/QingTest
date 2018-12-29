package com.qingqing.test.bean.pay.request;

import com.qingqing.common.auth.domain.UserType;

/**
 * Created by zhujianxing on 2018/8/23.
 */
public class PrePayRequestBeanV2 {
    private String qingqingOrderId;
    private String sourceChannel;
    private String orderType;
    private Long userId;
    private UserType userType;

    public String getQingqingOrderId() {
        return qingqingOrderId;
    }

    public void setQingqingOrderId(String qingqingOrderId) {
        this.qingqingOrderId = qingqingOrderId;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
