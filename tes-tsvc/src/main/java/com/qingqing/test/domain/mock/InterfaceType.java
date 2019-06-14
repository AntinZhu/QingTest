package com.qingqing.test.domain.mock;

import com.qingqing.common.intf.HasDefaultInterface;

/**
 * Created by zhujianxing on 2018/6/12.
 */
public enum InterfaceType implements HasDefaultInterface<InterfaceType> {
    unknown(-1),
    alipay_transfer_apply(1, "out_biz_no", "amount"), // 支付宝转账申请接口
    alipay_transfer_query(2, "out_biz_no"), // 支付宝转账查询接口
    ahm_order_sync(3, "projectNo", "applyAmt"), // 爱海米同步订单接口
    ahm_order_query(4, "projectNo"), // 爱海米订单查询接口
    wx_refund_apply(5, ""),
    wx_refund_query(6, ""),
    ;

    private Integer value;
    private String qingqingOrderNoName;
    private String orderAmountName;

    InterfaceType(Integer value, String qingqingOrderNoName, String orderAmountName) {
        this.value = value;
        this.qingqingOrderNoName = qingqingOrderNoName;
        this.orderAmountName = orderAmountName;
    }

    InterfaceType(Integer value, String qingqingOrderNoName) {
        this.value = value;
        this.qingqingOrderNoName = qingqingOrderNoName;
    }

    InterfaceType(Integer value) {
        this.value = value;
    }

    @Override
    public InterfaceType getDefault() {
        return unknown;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getQingqingOrderNoName() {
        return qingqingOrderNoName;
    }

    public String getOrderAmountName() {
        return orderAmountName;
    }
}
