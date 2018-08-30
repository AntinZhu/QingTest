package com.qingqing.test.bean.pay;

import com.qingqing.common.intf.HasDefaultInterface;

/**
 * v4.9之后引入, 对应于Proto文件中的PayChannelEnum.class
 */
public enum OrderPayTypeV3 implements HasDefaultInterface<OrderPayTypeV3> {
	unknown(-1, OrderPayTypeV2.unknown),
	alipayQingQingApp(1, OrderPayTypeV2.alipay), //支付宝支付
	balancepayQingQing(2, OrderPayTypeV2.qingqing_balance),
	balancepayZhikang(3, OrderPayTypeV2.zhikang_balance),
	weixinpay_1(4, OrderPayTypeV2.weixin_pay),
	unionpay(5, OrderPayTypeV2.unionpay),
	alipayZhikangApp(6, OrderPayTypeV2.alipay),
	weixinpayApp_2(7, OrderPayTypeV2.weixin_pay),	//微信app支付账号2
	weixinpayH5_2(8, OrderPayTypeV2.weixin_pay),	//微信H5支付账号2
	alipayQingQingH5(9, OrderPayTypeV2.alipay),
	alipayKhome(10, OrderPayTypeV2.alipay), //口碑支付
	cmbywt(11, OrderPayTypeV2.cmbywtpay), //招行一网通支付
	educloudpay(12, OrderPayTypeV2.educloudpay), //和教育支付
	combined_pay(13, OrderPayTypeV2.combined_pay), //合并代付
	cmb_installment_app(14, OrderPayTypeV2.cmb_installment),//招行分期付
    cmb_installment_h5(15, OrderPayTypeV2.cmb_installment), // 招行分期H5
	hb_installment_app(16, OrderPayTypeV2.hb_installment),	//花呗分期付app
	hb_installment_h5(17, OrderPayTypeV2.hb_installment),	//花呗分期付H5
	jd_app_pay(18, OrderPayTypeV2.jd_pay),	// 京东支付
	jd_h5_pay(19, OrderPayTypeV2.jd_pay),	// 京东h5支付
    baidu_app_pay(20, OrderPayTypeV2.baidu_pay), // 6.3.5 百度支付
    multiple_pay(21, OrderPayTypeV2.multiple_pay),   // 6.4.5 分次支付app 支付方式
//    weixinpayApp_teacher(22),   // 6.6.3 微信App 支付方式
    weixinpayH5_teacher(23, OrderPayTypeV2.weixin_pay),    // 6.6.3 微信H5 支付

    new_unionpay(24, OrderPayTypeV2.new_unionpay),   // 6.7.5 新银联支付
    apple_app_pay(25, OrderPayTypeV2.apple_pay),  // 6.7.5 苹果支付方式
    lovehaimi_app_pay(26, OrderPayTypeV2.lovehaimi_pay),		//6.9.5 爱海米支付方式
	weixin_native_teacher(27, OrderPayTypeV2.weixin_pay), // 6.9.5 微信二维码支付
	group_purchase_pay(28, OrderPayTypeV2.group_purchase_pay),  //6.9.5 团购支付
	;
	
	private final int value;
	private final OrderPayTypeV2 orderPayTypeV2;

	@Override
	public Integer getValue() {
		return value;
	}

	public OrderPayTypeV2 getOrderPayTypeV2() {
		return orderPayTypeV2;
	}

	OrderPayTypeV3(int value, OrderPayTypeV2 orderPayTypeV2) {
		this.value = value;
		this.orderPayTypeV2 = orderPayTypeV2;
	}

	@Override
	public OrderPayTypeV3 getDefault() {
		return unknown;
	}
}
