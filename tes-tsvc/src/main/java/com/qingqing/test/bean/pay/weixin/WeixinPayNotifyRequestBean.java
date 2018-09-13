package com.qingqing.test.bean.pay.weixin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @description: 微信支付成功 的通知
 * @XmlRootElement: 用于解析微信返回的xml文件
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeixinPayNotifyRequestBean {

	/** 下微信预付款订单，返回状态码 */
	@XmlElement(name = "return_code")
	private String returnCode = "SUCCESS";
	/** 下微信预付款订单，返回信息 */
	@XmlElement(name = "return_msg")
	private String returnMsg;
	/** 微信支付回调，业务结果 */
	@XmlElement(name = "result_code")
	private String resultCode = "SUCCESS";
	/** 支付金额，以分为单位 */
	@XmlElement(name = "total_fee")
	private Integer totalFeeInPoint = 100;
	/** 现金支付金额，以分为单位 */
	@XmlElement(name = "cash_fee")
	private Integer cashFeeInPoint = 100;
	/** 请求完成时间 */
	@XmlElement(name = "time_end")
	private String timeEnd;
	/** 取值如下：JSAPI，NATIVE，APP， */
	@XmlElement(name = "trade_type")
	private String tradeType = "JSAPI";
	/** 付款银行， */
	@XmlElement(name = "bank_type")
	private String bankType;
	/** 微信分配的公众账号ID（企业号corpid即为此appId） */
	@XmlElement(name = "appid")
	private String appId;
	/** 微信支付分配的商户号 */
	@XmlElement(name = "mch_id")
	private String mchId = "1213213123";
	/** 随机字符串，不长于32位。 */
	@XmlElement(name = "nonce_str")
	private String nonceStr;
	/** 签名 */
	@XmlElement(name = "sign")
	/**下单失败错误码， 非必须*/
	private String sign;
	@XmlElement(name = "err_code")
	private String errorCode = "dasdasd";
	/** 下单失败具体描述信息， 非必须 */
	@XmlElement(name = "err_code_des")
	private String errorCodeDescription = "dasdasdasd";
	/** 微信支付交易流水号 */
	@XmlElement(name = "transaction_id")
	private String weixinTransactionId = String.valueOf(System.currentTimeMillis());
	/** 轻轻家教交易流水号 */
	@XmlElement(name = "out_trade_no")
	private String qingqingTradeNo;
	/** trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。 */
	@XmlElement(name = "openid")
	private String openId = "asasokpojkpo";
	/** 是否关注了公众账号，仅在公众账号类型支付有效。Y-已关注  N-未关注*/
	@XmlElement(name = "is_subscribe")
	private String isSubscribe;

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

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public Integer getTotalFeeInPoint() {
		return totalFeeInPoint;
	}

	public void setTotalFeeInPoint(Integer totalFeeInPoint) {
		this.totalFeeInPoint = totalFeeInPoint;
	}

	public Integer getCashFeeInPoint() {
		return cashFeeInPoint;
	}

	public void setCashFeeInPoint(Integer cashFeeInPoint) {
		this.cashFeeInPoint = cashFeeInPoint;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCodeDescription() {
		return errorCodeDescription;
	}

	public void setErrorCodeDescription(String errorCodeDescription) {
		this.errorCodeDescription = errorCodeDescription;
	}

	public String getWeixinTransactionId() {
		return weixinTransactionId;
	}

	public void setWeixinTransactionId(String weixinTransactionId) {
		this.weixinTransactionId = weixinTransactionId;
	}

	public String getQingqingTradeNo() {
		return qingqingTradeNo;
	}

	public void setQingqingTradeNo(String qingqingTradeNo) {
		this.qingqingTradeNo = qingqingTradeNo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

}
