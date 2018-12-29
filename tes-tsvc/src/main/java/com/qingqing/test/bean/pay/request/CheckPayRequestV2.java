package com.qingqing.test.bean.pay.request;

/**
 * Created by zhujianxing on 2018/8/23.
 */
public class CheckPayRequestV2 {
    private String qingqingOrderId;
    private String orderType;
    private Long userId;
    private String UserType;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
