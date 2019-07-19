package com.qingqing.test.bean.test;

/**
 * Created by zhujianxing on 2018/1/9.
 */
public class BankValidateResult {
    private boolean isSuccess; //是否允许添加
    private boolean isCostTimes; // 是否花费校验次数
    private String hintMsg; // 提示信息
    private boolean isValidated; // 是否校验通过

    public BankValidateResult(boolean isSuccess, boolean isCostTimes, String hintMsg, boolean isValidated) {
        this.isSuccess = isSuccess;
        this.isCostTimes = isCostTimes;
        this.hintMsg = hintMsg;
        this.isValidated = isValidated;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isCostTimes() {
        return isCostTimes;
    }

    public String getHintMsg() {
        return hintMsg;
    }

    public boolean isValidated() {
        return isValidated;
    }
}
