package com.qingqing.test.bean.contract;

/**
 * Created by zhujianxing on 2020/6/13.
 */
public class ContractNotifyRequest {
    private String ssqContractId;
    private String ssqAccountId;

    public String getSsqContractId() {
        return ssqContractId;
    }

    public void setSsqContractId(String ssqContractId) {
        this.ssqContractId = ssqContractId;
    }

    public String getSsqAccountId() {
        return ssqAccountId;
    }

    public void setSsqAccountId(String ssqAccountId) {
        this.ssqAccountId = ssqAccountId;
    }
}
