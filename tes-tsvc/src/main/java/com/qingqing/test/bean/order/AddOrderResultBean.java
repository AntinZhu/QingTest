package com.qingqing.test.bean.order;

import com.qingqing.test.bean.base.InterfaceBaseResponse;

/**
 * Created by zhujianxing on 2018/2/8.
 */
public class AddOrderResultBean extends InterfaceBaseResponse {

    private Long classOrderId;
    private String qingqingGroupOrderId;
    private String groupOrderId;
    private String qingqingOrderId;
    private String orderId;
    private String orderBriefStatus;

    public String getQingqingGroupOrderId() {
        return qingqingGroupOrderId;
    }

    public void setQingqingGroupOrderId(String qingqingGroupOrderId) {
        this.qingqingGroupOrderId = qingqingGroupOrderId;
    }

    public String getGroupOrderId() {
        return groupOrderId;
    }

    public void setGroupOrderId(String groupOrderId) {
        this.groupOrderId = groupOrderId;
    }

    public String getQingqingOrderId() {
        return qingqingOrderId;
    }

    public void setQingqingOrderId(String qingqingOrderId) {
        this.qingqingOrderId = qingqingOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderBriefStatus() {
        return orderBriefStatus;
    }

    public void setOrderBriefStatus(String orderBriefStatus) {
        this.orderBriefStatus = orderBriefStatus;
    }

    public Long getClassOrderId() {
        return classOrderId;
    }

    public void setClassOrderId(Long classOrderId) {
        this.classOrderId = classOrderId;
    }
}
