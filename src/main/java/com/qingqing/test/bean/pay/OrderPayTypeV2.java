package com.qingqing.test.bean.pay;

import com.qingqing.common.exception.ConvertException;
import com.qingqing.common.intf.HasValueInterface;

import java.util.EnumSet;

/**
 * @description 支付类型, 对应于前端提交的支付类型，(com.qingqing.api.proto.v1.Pay.OrderPayType)
 * @note  4.6版本引入, 支持多种第三方支付类型
 */
public enum OrderPayTypeV2 implements HasValueInterface {
    unknown(-1, 0, false),
    alipay(1, 2, true),
    qingqing_balance(2, 0, true),
    zhikang_balance(3, 999, false),
    weixin_pay(4, 3, false),	//微信app， h5支付
    unionpay(5, 999, false),
    cmbywtpay(6, 999, false), //招行一网通支付 5.0
    educloudpay(7, 999, false), //和教育支付
    combined_pay(8, 999, false), //合并支付
    cmb_installment(9, 7, false), // 招行分期支付
    hb_installment(10, 4, true), //6.3.0 花呗分期付
    jd_pay(11, 5, false), // 6.3.0 京东支付
    baidu_pay(12, 6, false),   // 6.3.5 百度支付
    multiple_pay(13, 1, false),    // 6.4.5 分次支付方式
    new_unionpay(14, 999, false),   // 6.7.5 新银联支付方式
    apple_pay(15, 999, false),  // 6.7.5 苹果支付方式
    lovehaimi_pay(16, 999, false),      //6.9.5 爱海米支付方式
    group_purchase_pay(17, 999, false), //6.9.5 团购支付
    ;

    public static final EnumSet<OrderPayTypeV2> SUPPORT_MULTIPLE_PAY_TYPE = EnumSet.of(alipay, weixin_pay, qingqing_balance, multiple_pay, unionpay, new_unionpay, apple_pay);

    public static final EnumSet<OrderPayTypeV2> THIRD_SUPPORT_MULTIPLE_PAY_TYPE = EnumSet.of(alipay, weixin_pay, unionpay, new_unionpay, apple_pay);

    private Integer value;
    private Integer displayOrder;
    private boolean isUnCollapsedPayType; // 不折叠显示

    OrderPayTypeV2(Integer value, Integer displayOrder, boolean isUnCollapsedPayType) {
        this.value = value;
        this.displayOrder = displayOrder;
        this.isUnCollapsedPayType = isUnCollapsedPayType;
    }

    public boolean isUnCollapsedPayType() {
        return isUnCollapsedPayType;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static final OrderPayTypeV2 valueOf(Integer value) {
        for (OrderPayTypeV2 status : OrderPayTypeV2.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new ConvertException("unknown OrderPayTypeV2 value:" + value);
    }
}
