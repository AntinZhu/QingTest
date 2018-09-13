package com.qingqing.test.bean.pay.unionpay;

/**
 * 银联支付的回调请求对象
 */
public class UnionpayPrepayNotifyRequestBean{
	
	/**
	 * 冗余字段， 用于后续可能出现有多个银联账号的场景
	 * 轻轻家教在银联后台的商户代码
	 */
	private String merId = "";
	
	/**
	 * 冗余字段，用于可能出现的提交给银联的金额不等于实际支付金额的bug复查场景 
	 * 银联支付，交易金额，单位：分
	 */
	private Integer txnAmt = 100;
	
	/**轻轻家教内部交易流水号, v2版本， 不允许有"_"或“-”*/
	private String orderId;

	/**轻轻家教， 消费交易流水号， 查询用*/
	private String queryId = "req";
	
	/**银联支付， 银联支付返回码*/
	private String respCode = "00";
	
	/**银联支付， 银联支付返回信息*/
	private String respMsg = "succ";
	
	/**银联支付， 银联系统同银行清算金额*/
	private String settleAmt = "100";
	
    /**银联支付，银联系统同银行清算币种*/
    private String settleCurrencyCode = "as";
    
    /**银联系统同银行清算日期*/
    private String settleDate;
	
    /**银联系统同银行交易传输时间*/
    private String traceNo = String.valueOf(System.currentTimeMillis());
	
    /**银联系统同银行交易传输时间*/
    private String traceTime;
	
    /**银联支付，加密过之后的付款卡号*/
    private String accNo = "************";

	/**银联支付，付款卡类型，回调时生成*/
	private String payCardType;
    
	/**银联支付，付款方式，回调时生成*/
	private String payType;

	/**银联支付，付款卡标示，回调时生成*/
	private String payCardNo = "2018";
    
	/**银联支付，付款卡名称，回调时生成*/
	private String payCardIssueName = "张三银行";


	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public Integer getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(Integer txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}

	public String getSettleCurrencyCode() {
		return settleCurrencyCode;
	}

	public void setSettleCurrencyCode(String settleCurrencyCode) {
		this.settleCurrencyCode = settleCurrencyCode;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getTraceTime() {
		return traceTime;
	}

	public void setTraceTime(String traceTime) {
		this.traceTime = traceTime;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getPayCardType() {
		return payCardType;
	}

	public void setPayCardType(String payCardType) {
		this.payCardType = payCardType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public String getPayCardIssueName() {
		return payCardIssueName;
	}

	public void setPayCardIssueName(String payCardIssueName) {
		this.payCardIssueName = payCardIssueName;
	}
}
