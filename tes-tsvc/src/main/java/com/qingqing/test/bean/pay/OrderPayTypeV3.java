package com.qingqing.test.bean.pay;

import com.qingqing.common.exception.ConvertException;
import com.qingqing.common.intf.HasDefaultInterface;

import java.util.EnumSet;

/**
 * v4.9之后引入, 对应于Proto文件中的PayChannelEnum.class
 */
public enum OrderPayTypeV3 implements HasDefaultInterface<OrderPayTypeV3> {
	unknown(-1, OrderPayTypeV2.unknown, "未知支付类型"),
	alipayQingQingApp(1, OrderPayTypeV2.alipay, "支付宝app支付"), //支付宝支付
	balancepayQingQing(2, OrderPayTypeV2.qingqing_balance, "轻轻钱包支付"),
	balancepayZhikang(3, OrderPayTypeV2.zhikang_balance, "志康钱包支付"),
	weixinpay_1(4, OrderPayTypeV2.weixin_pay, "微信app支付1"),
	unionpay(5, OrderPayTypeV2.unionpay, "银联支付"),
	alipayZhikangApp(6, OrderPayTypeV2.alipay, "志康支付宝app支付"),
	weixinpayApp_2(7, OrderPayTypeV2.weixin_pay, "微信app支付2"),	//微信app支付账号2
	weixinpayH5_2(8, OrderPayTypeV2.weixin_pay, "微信H5支付2"),	//微信H5支付账号2
	alipayQingQingH5(9, OrderPayTypeV2.alipay, "支付宝h5支付"),
	alipayKhome(10, OrderPayTypeV2.alipay, "口碑支付"), //口碑支付
	cmbywt(11, OrderPayTypeV2.cmbywtpay, "招行一网通支付"), //招行一网通支付
	educloudpay(12, OrderPayTypeV2.educloudpay, "和教育支付"), //和教育支付
	combined_pay(13, OrderPayTypeV2.combined_pay, "合并代付"), //合并代付
	cmb_installment_app(14, OrderPayTypeV2.cmb_installment, "招行分期付"),//招行分期付
    cmb_installment_h5(15, OrderPayTypeV2.cmb_installment, "招行分期H5"), // 招行分期H5
	hb_installment_app(16, OrderPayTypeV2.hb_installment, "花呗分期付app"),	//花呗分期付app
	hb_installment_h5(17, OrderPayTypeV2.hb_installment, "花呗分期付H5"),	//花呗分期付H5
	jd_app_pay(18, OrderPayTypeV2.jd_pay, "京东支付"),	// 京东支付
	jd_h5_pay(19, OrderPayTypeV2.jd_pay, "京东h5支付"),	// 京东h5支付
    baidu_app_pay(20, OrderPayTypeV2.baidu_pay, "百度支付"), // 6.3.5 百度支付
    multiple_pay(21, OrderPayTypeV2.multiple_pay, "分次支付app"),   // 6.4.5 分次支付app 支付方式
    weixinpayApp_teacher(22, OrderPayTypeV2.weixin_pay, "微信app支付"),   // 6.6.3 微信App 支付方式
    weixinpayH5_teacher(23, OrderPayTypeV2.weixin_pay, "微信h5支付"),    // 6.6.3 微信H5 支付

    new_unionpay(24, OrderPayTypeV2.new_unionpay, "新银联支付"),   // 6.7.5 新银联支付
    apple_app_pay(25, OrderPayTypeV2.apple_pay, "苹果支付方式"),  // 6.7.5 苹果支付方式
    lovehaimi_app_pay(26, OrderPayTypeV2.lovehaimi_pay, "爱海米支付"),		//6.9.5 爱海米支付方式
	weixin_native_teacher(27, OrderPayTypeV2.weixin_pay, "微信二维码支付"), // 6.9.5 微信二维码支付
	group_purchase_pay(28, OrderPayTypeV2.group_purchase_pay, "团购支付"),  //6.9.5 团购支付

	alipay_app_nanjing(29, OrderPayTypeV2.alipay, "南京轻轻支付宝app支付"), // 7.4.0 南京轻轻支付宝app支付
	alipay_h5_nanjing(30, OrderPayTypeV2.alipay, "南京轻轻支付宝h5支付"),  // 7.4.0 南京轻轻支付宝h5支付
	weixin_pay_app_nanjing(31, OrderPayTypeV2.weixin_pay, "南京轻轻微信app支付"),	// 7.4.0 南京轻轻微信app支付
	weixin_pay_h5_nanjing(32, OrderPayTypeV2.weixin_pay, "南京轻轻微信H5支付"),	// 7.4.0 南京轻轻微信H5支付
	weixin_h5_qq_chinaums_pay_channel(39, OrderPayTypeV2.weixin_pay_chinaums, "银联微信H5支付"),//8.8.5 银联微信h5（轻轻侧使用）
	alipay_h5_qq_chinaums_pay_channel(40, OrderPayTypeV2.alipay_chinaums, "银联支付宝H5支付") // 8.8.5 银联支付宝h5 （轻轻侧使用）
	;
	
	private final int value;
	private final OrderPayTypeV2 orderPayTypeV2;
	private final String name;

	@Override
	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	OrderPayTypeV3(int value, OrderPayTypeV2 orderPayTypeV2, String name) {
		this.value = value;
		this.orderPayTypeV2 = orderPayTypeV2;
		this.name = name;
	}

	public OrderPayTypeV2 getOrderPayTypeV2() {
		return orderPayTypeV2;
	}

	public static final OrderPayTypeV3 parser(int value){
		for(OrderPayTypeV3 item : OrderPayTypeV3.values()){
			if(item.getValue().equals(value)){
				return item;
			}
		}
		throw new ConvertException("cannot convert " + value + " to OrderPayChannel");
	}

	@Override
	public OrderPayTypeV3 getDefault() {
		return unknown;
	}
}
