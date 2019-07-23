package com.qingqing.test.bean.pay;

import com.qingqing.test.bean.base.InterfaceBaseResponse;
import com.qingqing.test.bean.base.KeyAndValue;

import java.util.List;

/**
 * Created by zhujianxing on 2018/2/9.
 */
public class PrePayBean extends InterfaceBaseResponse {
    private String needPayAmount;
    private String balanceAmount;
    private List<KeyAndValue> supportPayTypeList;
    private List<InstallmentConfigBean> installmentConfigs;
    private boolean isSupportMultiple;
    private String multipleMode;

    public boolean isSupportMultiple() {
        return isSupportMultiple;
    }

    public void setSupportMultiple(boolean supportMultiple) {
        isSupportMultiple = supportMultiple;
    }

    public String getMultipleMode() {
        return multipleMode;
    }

    public void setMultipleMode(String multipleMode) {
        this.multipleMode = multipleMode;
    }

    public String getNeedPayAmount() {
        return needPayAmount;
    }

    public void setNeedPayAmount(String needPayAmount) {
        this.needPayAmount = needPayAmount;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public List<KeyAndValue> getSupportPayTypeList() {
        return supportPayTypeList;
    }

    public void setSupportPayTypeList(List<KeyAndValue> supportPayTypeList) {
        this.supportPayTypeList = supportPayTypeList;
    }

    public List<InstallmentConfigBean> getInstallmentConfigs() {
        return installmentConfigs;
    }

    public void setInstallmentConfigs(List<InstallmentConfigBean> installmentConfigs) {
        this.installmentConfigs = installmentConfigs;
    }

    public static class InstallmentConfigBean{
        private String payType;
        private String payTypeName;
        private List<InstallmentConfigItemBean> items;
        private String firstPayAmount;
        private String lastPayAmount;

        public String getFirstPayAmount() {
            return firstPayAmount;
        }

        public void setFirstPayAmount(String firstPayAmount) {
            this.firstPayAmount = firstPayAmount;
        }

        public String getLastPayAmount() {
            return lastPayAmount;
        }

        public void setLastPayAmount(String lastPayAmount) {
            this.lastPayAmount = lastPayAmount;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPayTypeName() {
            return payTypeName;
        }

        public void setPayTypeName(String payTypeName) {
            this.payTypeName = payTypeName;
        }

        public List<InstallmentConfigItemBean> getItems() {
            return items;
        }

        public void setItems(List<InstallmentConfigItemBean> items) {
            this.items = items;
        }

        public String getKey(){
            return payType;
        }

        public String getValue(){
            return payTypeName;
        }
    }

    public static class InstallmentConfigItemBean{
        private Long configId;
        private Integer stageNum;
        private Double stageAmount;
        private Double serviceAmount;

        public Long getConfigId() {
            return configId;
        }

        public void setConfigId(Long configId) {
            this.configId = configId;
        }

        public Integer getStageNum() {
            return stageNum;
        }

        public void setStageNum(Integer stageNum) {
            this.stageNum = stageNum;
        }

        public Double getStageAmount() {
            return stageAmount;
        }

        public void setStageAmount(Double stageAmount) {
            this.stageAmount = stageAmount;
        }

        public Double getServiceAmount() {
            return serviceAmount;
        }

        public void setServiceAmount(Double serviceAmount) {
            this.serviceAmount = serviceAmount;
        }
    }
}