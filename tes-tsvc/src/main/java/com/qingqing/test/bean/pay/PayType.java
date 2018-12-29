package com.qingqing.test.bean.pay;

import com.qingqing.api.proto.v1.Pay.OrderPayType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujianxing on 2018/2/9.
 */
public enum PayType {
    unknown("", "未知", null),
    qingqing_balance("qingqing_balance", "轻轻钱包", OrderPayType.qingqing_balance),
    alipay("alipay", "支付宝", OrderPayType.alipay),
    weixin_pay("weixin_pay", "微信", OrderPayType.weixin_pay),
    hb_installment("hb_installment", "花呗分期", OrderPayType.hb_installment),
    cmd_installment("cmd_installment", "招行分期付", OrderPayType.cmb_installment),
    unionpay("unionpay", "银联支付", OrderPayType.unionpay),
    cmbywtpay("cmbywtpay", "招商银行", OrderPayType.cmbywtpay),
    jd_pay("jd_pay", "京东支付", OrderPayType.jd_pay),
    baidu_pay("baidu_pay", "百度有钱花支付", OrderPayType.baidu_pay),
    multiple_pay("multiple_pay", "分次支付", OrderPayType.multiple_pay),
    new_unionpay("new_unionpay", "新银联支付", OrderPayType.new_unionpay),
    apple_pay("apple_pay", "Apple Pay", OrderPayType.apple_pay),
    lovehaimi_pay("lovehaimi_pay", "爱海米分期", OrderPayType.lovehaimi_pay),
    cmb_installment("cmb_installment", "招行分期", OrderPayType.cmb_installment),
    ;

    private static Map<String, String> keyNameMap = new HashMap<>(PayType.values().length);
    static{
        for(PayType payType : PayType.values()){
            keyNameMap.put(payType.getKey(), payType.getName());
        }
    }

    private String key;
    private String name;
    private OrderPayType orderPayType;

    PayType(String key, String name, OrderPayType orderPayType) {
        this.key = key;
        this.name = name;
        this.orderPayType = orderPayType;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public OrderPayType getOrderPayType() {
        return orderPayType;
    }

    public static PayType parseKey(String key){
        for(PayType payType : PayType.values()){
            if(payType.getKey().equals(key)){
                return payType;
            }
        }

        return unknown;
    }

    public static String getName(String key){
        return keyNameMap.get(key);
    }
}
