package com.qingqing.test.domain.pay;

import com.qingqing.common.domain.order.OrderType;
import com.qingqing.common.util.converter.lang.BigDecimalUtil;
import com.qingqing.test.bean.pay.OrderPayTypeV3;

import java.sql.Timestamp;
import java.util.Date;

public class ThirdPayBrief {

	private Long id;
	
	private Long studentId;
	
	private Long relatedOrderId;
	
	private OrderType relatedOrderType;
	
	private String qingqingTradeNo;
	
	private String thirdPaymentTradeNo;
	
	private Double balanceAmount;

	private Double unWithdrawableBalanceAmount;
	
	private Integer businessType;
	
	private Double thirdPaymentAmount;
	
	private OrderPayTypeV3 thirdPaymentTypeV3;
	
	private Long thirdPaymentId;

	/**
	* 第三方支付完成通知到API服务的时间
	*/
	private Date payTime;

	/**
	* 真实订单支付成功的时间
	*/
	private Date orderPayTime;
	
	private Date expireTime;
	
	private Date createTime;
	
	private Timestamp lastUpdateTime;
	
	private String tradeId;

	private String remoteIp;

	private Double installmentPoundagePrice;    //分期手续费

	private Integer stageNum;   //分期付期数

    private Double installmentPoundageRate; //分期费率

    private Long installmentPoundageConfigId;   //分期手续费利率配置id

    private String paymentAccountNo;

	public Double getUnWithdrawableBalanceAmount() {
		return unWithdrawableBalanceAmount;
	}

	public void setUnWithdrawableBalanceAmount(Double unWithdrawableBalanceAmount) {
		this.unWithdrawableBalanceAmount = unWithdrawableBalanceAmount;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getQingqingTradeNo() {
		return qingqingTradeNo;
	}

	public void setQingqingTradeNo(String qingqingTradeNo) {
		this.qingqingTradeNo = qingqingTradeNo;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Double getThirdPaymentAmount() {
		return thirdPaymentAmount;
	}

	public void setThirdPaymentAmount(Double thirdPaymentAmount) {
		this.thirdPaymentAmount = thirdPaymentAmount;
	}

	public OrderPayTypeV3 getThirdPaymentTypeV3() {
		return thirdPaymentTypeV3;
	}

	public void setThirdPaymentTypeV3(OrderPayTypeV3 thirdPaymentTypeV3) {
		this.thirdPaymentTypeV3 = thirdPaymentTypeV3;
	}

	public Long getThirdPaymentId() {
		return thirdPaymentId;
	}

	public void setThirdPaymentId(Long thirdPaymentId) {
		this.thirdPaymentId = thirdPaymentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getRelatedOrderId() {
		return relatedOrderId;
	}

	public void setRelatedOrderId(Long relatedOrderId) {
		this.relatedOrderId = relatedOrderId;
	}

	public OrderType getRelatedOrderType() {
		return relatedOrderType;
	}

	public void setRelatedOrderType(OrderType relatedOrderType) {
		this.relatedOrderType = relatedOrderType;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getThirdPaymentTradeNo() {
		return thirdPaymentTradeNo;
	}

	public void setThirdPaymentTradeNo(String thirdPaymentTradeNo) {
		this.thirdPaymentTradeNo = thirdPaymentTradeNo;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Date getOrderPayTime() {
		return orderPayTime;
	}

	public void setOrderPayTime(Date orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

    public Double getInstallmentPoundagePrice() {
        return installmentPoundagePrice;
    }

    public void setInstallmentPoundagePrice(Double installmentPoundagePrice) {
        this.installmentPoundagePrice = installmentPoundagePrice;
    }

    public Integer getStageNum() {
        return stageNum;
    }

    public void setStageNum(Integer stageNum) {
        this.stageNum = stageNum;
    }

    public Double getInstallmentPoundageRate() {
        return installmentPoundageRate;
    }

    public void setInstallmentPoundageRate(Double installmentPoundageRate) {
        this.installmentPoundageRate = installmentPoundageRate;
    }

    public Long getInstallmentPoundageConfigId() {
        return installmentPoundageConfigId;
    }

    public void setInstallmentPoundageConfigId(Long installmentPoundageConfigId) {
        this.installmentPoundageConfigId = installmentPoundageConfigId;
    }

    public String getPaymentAccountNo() {
        return paymentAccountNo;
    }

    public void setPaymentAccountNo(String paymentAccountNo) {
        this.paymentAccountNo = paymentAccountNo;
    }

    /**
     * 第三方支付总共支付的金额
     * 包含 原订单金额 和 手续费 金额
     *
     */
	public Double getTotalThirdPaymentAmount() {
		return BigDecimalUtil.add(thirdPaymentAmount, BigDecimalUtil.notNullTrim(installmentPoundagePrice));
	}
}
