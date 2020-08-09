package com.qingqing.test.bean.pay;

import com.qingqing.common.exception.ConvertException;
import com.qingqing.common.intf.HasDefaultInterface;

import java.util.EnumSet;

/**
 * NOTICE: 支付显示名称见：
 * @description 支付类型, 对应于前端提交的支付类型，(com.qingqing.api.proto.v1.Pay.OrderPayType)
 * @note  4.6版本引入, 支持多种第三方支付类型
 */
public enum OrderPayTypeV2 implements HasDefaultInterface<OrderPayTypeV2> {
    unknown(-1, "未知","未知方式",0, false),
    alipay(1, "支付宝支付","支付宝",2, true),
    qingqing_balance(2, "轻轻钱包支付","轻轻钱包",0, true),
    zhikang_balance(3,"智康支付","智康钱包", 999, false),
    weixin_pay(4, "微信支付","微信支付",3, false),	//微信app， h5支付
    unionpay(5, "银联支付（旧）","银联支付",999, false),
    cmbywtpay(6, "招行支付","招行一网通支付",999, false), //招行一网通支付 5.0
    educloudpay(7, "和教育支付","和教育支付",999, false), //和教育支付
    combined_pay(8, "合并支付","合并支付",999, false), //合并支付
    cmb_installment(9, "招行分期","招行分期支付",7, false), // 招行分期支付
    hb_installment(10, "花呗分期","花呗分期付",4, true), //6.3.0 花呗分期付
    jd_pay(11,"京东支付","京东支付", 5, false), // 6.3.0 京东支付
    baidu_pay(12, "百度分期","百度支付",6, false),   // 6.3.5 百度支付
    multiple_pay(13,"分次支付","分次支付", 1, false),    // 6.4.5 分次支付方式
    new_unionpay(14, "银联支付","新银联支付",999, false),   // 6.7.5 新银联支付方式
    apple_pay(15, "苹果支付","苹果支付",999, false),  // 6.7.5 苹果支付方式
    lovehaimi_pay(16, "爱海米分期","爱海米支付",999, false),      //6.9.5 爱海米支付方式
    group_purchase_pay(17, "团购支付","团购支付",999, false), //6.9.5 团购支付
    alipay_chinaums(18,"支付宝支付（新）","支付宝（新）", 2, true), //8.8.5 银联支付宝支付
    weixin_pay_chinaums(19, "微信支付（新）","微信支付（新）",3, false), // 8.8.5 银联微信支付
    public_transfer_pay(20, "对公转账支付","对公转账", 1000, false),  //8.9.1-course 对公转账支付
    ;

    public static final EnumSet<OrderPayTypeV2> old_support = EnumSet.of(alipay, qingqing_balance, zhikang_balance,
            weixin_pay, unionpay, educloudpay);

    public static final EnumSet<OrderPayTypeV2> v4_support = EnumSet.allOf(OrderPayTypeV2.class);

    public static final EnumSet<OrderPayTypeV2> BACKWARD_REFUND = EnumSet.of(alipay, weixin_pay, unionpay, new_unionpay, apple_pay);

    private Integer value;
    private String name;//支付方式名称，用于前端展示
    private String shortName; //简称，用于协议、合同等
    private Integer displayOrder;
    private boolean isUnCollapsedPayType; // 不折叠显示

    OrderPayTypeV2(Integer value, String name, String shortName, Integer displayOrder, boolean isUnCollapsedPayType) {
        this.value = value;
        this.name = name;
        this.shortName = shortName;
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

    public String getShortName() {
        return shortName;
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

    public static String getServiceOrderPayTypeName(OrderPayTypeV3 orderPayTypeV3){
        if (OrderPayTypeV2.qingqing_balance.equals(orderPayTypeV3.getOrderPayTypeV2())){
            return OrderPayTypeV2.qingqing_balance.getShortName();
        }else if (OrderPayTypeV2.alipay.equals(orderPayTypeV3.getOrderPayTypeV2())){
            return OrderPayTypeV2.alipay.getShortName();
        }else if (OrderPayTypeV2.weixin_pay.equals(orderPayTypeV3.getOrderPayTypeV2())){
            return OrderPayTypeV2.weixin_pay.getShortName();
        }else if (OrderPayTypeV2.alipay_chinaums.equals(orderPayTypeV3.getOrderPayTypeV2())) {
            return OrderPayTypeV2.alipay_chinaums.getShortName();
        } else if (OrderPayTypeV2.weixin_pay_chinaums.equals(orderPayTypeV3.getOrderPayTypeV2())) {
            return OrderPayTypeV2.weixin_pay_chinaums.getShortName();
        }else {
            return "银行卡";
        }
    }
}
