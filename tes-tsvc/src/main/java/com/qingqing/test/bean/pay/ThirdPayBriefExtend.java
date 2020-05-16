package com.qingqing.test.bean.pay;

import com.qingqing.common.util.JsonUtil;

import java.util.Date;

public class ThirdPayBriefExtend {

    public static final String BAIDU_PAY_URL = "BAIDU_PAY_URL";
    public static final String CHINAUMS_TRADE_NO = "CHINAUMS_TRADE_NO";
    public static final String RETURN_BILL_REASON_TYPE_THIRD_PAY_BRIEF_EXTEND_TYPE_KEY = "return_bill_reason_type";
    public static final String INSTALLMENT_POUNDAGE_DISCOUNT_CONFIG_DETAIL = "INSTALLMENT_POUNDAGE_DISCOUNT_CONFIG_DETAIL";
    public static final String IS_BD_ACTIVE_SUCCESS = "IS_BD_ACTIVE_SUCCESS";

    // 实际支付方式
    public static final String COMBINED_REAL_PAY_TYPE = "real_pay_type";
    public static final String COMBINED_REAL_PAYMENT_ID = "real_payment_id";

    public static final String REAL_PAY_TYPE = "real_pay_type_v2";
    public static final String REAL_PAYMENT_ID = "real_payment_id_v2";

    public static final String IS_BD_ACTIVE_SUCCESS_VALUE_TRUE = "1";
    public static final String IS_BD_ACTIVE_SUCCESS_VALUE_FALSE = "0";

    private Long id;

    private Long thirdPayBriefId;

    private String refType;

    private String extendValue;

    private Boolean isDeleted;

    private Date createTime;

    private Date lastUpdateTime;

    public ThirdPayBriefExtend() {
    }

    public ThirdPayBriefExtend(Long thirdPayBriefId, String refType, String extendValue) {
        this.thirdPayBriefId = thirdPayBriefId;
        this.refType = refType;
        this.extendValue = extendValue;
        this.isDeleted = false;
//        this.createTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getThirdPayBriefId() {
        return thirdPayBriefId;
    }

    public void setThirdPayBriefId(Long thirdPayBriefId) {
        this.thirdPayBriefId = thirdPayBriefId;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getExtendValue() {
        return extendValue;
    }

    public void setExtendValue(String extendValue) {
        this.extendValue = extendValue == null ? null : extendValue.trim();
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }


    public <T> T parseExtendValue(Class<T> clz) {
        return JsonUtil.getObjectFromJson(extendValue, clz);
    }
}