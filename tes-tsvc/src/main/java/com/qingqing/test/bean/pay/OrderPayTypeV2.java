package com.qingqing.test.bean.pay;

import com.qingqing.common.exception.ConvertException;
import com.qingqing.common.intf.HasDefaultInterface;

/**
 * @description 支付类型, 对应于前端提交的支付类型，(com.qingqing.api.proto.v1.Pay.OrderPayType)
 * @note  4.6版本引入, 支持多种第三方支付类型
 */
public enum OrderPayTypeV2 implements HasDefaultInterface<OrderPayTypeV2>{
    unknown(-1, "未知",0, false),
    alipay(1, "支付宝支付",2, true),
    qingqing_balance(2, "轻轻钱包支付",0, true),
    zhikang_balance(3,"智康支付", 999, false),
    weixin_pay(4, "微信支付",3, false),	//微信app， h5支付
    unionpay(5, "银联支付（旧）",999, false),
    cmbywtpay(6, "招行支付",999, false), //招行一网通支付 5.0
    educloudpay(7, "和教育支付",999, false), //和教育支付
    combined_pay(8, "合并支付",999, false), //合并支付
    cmb_installment(9, "招行分期",7, false), // 招行分期支付
    hb_installment(10, "花呗分期",4, true), //6.3.0 花呗分期付
    jd_pay(11,"京东支付", 5, false), // 6.3.0 京东支付
    baidu_pay(12, "百度分期",6, false),   // 6.3.5 百度支付
    multiple_pay(13,"分次支付", 1, false),    // 6.4.5 分次支付方式
    new_unionpay(14, "银联支付",999, false),   // 6.7.5 新银联支付方式
    apple_pay(15, "苹果支付",999, false),  // 6.7.5 苹果支付方式
    lovehaimi_pay(16, "爱海米分期",999, false),      //6.9.5 爱海米支付方式
    group_purchase_pay(17, "团购支付",999, false), //6.9.5 团购支付
    alipay_chinaums(18,"支付宝支付（新）", 2, true), //8.8.5 银联支付宝支付
    weixin_pay_chinaums(19, "微信支付（新）",3, false) // 8.8.5 银联微信支付
    ;

    private Integer value;
    private String name;//支付方式名称，用于前端展示
    private Integer displayOrder;
    private boolean isUnCollapsedPayType; // 不折叠显示

    OrderPayTypeV2(Integer value, String name, Integer displayOrder, boolean isUnCollapsedPayType) {
        this.value = value;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public static final OrderPayTypeV2 valueOf(Integer value) {
        for (OrderPayTypeV2 status : OrderPayTypeV2.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new ConvertException("unknown OrderPayTypeV2 value:" + value);
    }

    @Override
    public OrderPayTypeV2 getDefault() {
        return unknown;
    }
}
