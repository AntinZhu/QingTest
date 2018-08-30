package com.qingqing.test.bean.pay;

import com.qingqing.test.bean.base.InterfaceBaseResponse;

import java.util.List;

/**
 * Created by zhujianxing on 2018/2/12.
 */
public class PayBriefListResponse extends InterfaceBaseResponse {

    private List<ThirdPayBriefBean> payBriefList;

    public List<ThirdPayBriefBean> getPayBriefList() {
        return payBriefList;
    }

    public void setPayBriefList(List<ThirdPayBriefBean> payBriefList) {
        this.payBriefList = payBriefList;
    }
}
