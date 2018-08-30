package com.qingqing.test.service.pay;

import com.qingqing.test.domain.pay.ThirdPayBrief;

import java.util.List;

/**
 * Created by zhujianxing on 2018/2/12.
 */
public interface ThirdPayBriefService {

    List<ThirdPayBrief> selectListByOrderTypeAndOrderId(Integer orderType, Long orderId);

    ThirdPayBrief findByQingQingTradeNo(String qingqingTradeNo);
}
