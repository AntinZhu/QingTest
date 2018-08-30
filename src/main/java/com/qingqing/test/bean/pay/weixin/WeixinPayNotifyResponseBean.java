package com.qingqing.test.bean.pay.weixin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *  @description: 微信支付完成回调通知
 *  @XmlRootElement: 用于生成返回给微信的xml文件
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeixinPayNotifyResponseBean {

	/** 下微信预付款订单，返回状态码 */
	@XmlElement(name = "return_code")
	private String returnCode;
	/** 下微信预付款订单，返回信息 */
	@XmlElement(name = "return_msg")
	private String returnMsg;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
}