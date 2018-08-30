package com.qingqing.test.service.pay.impl;

import com.qingqing.common.domain.order.OrderType;
import com.qingqing.test.dao.data.ThirdPayBriefMapper;
import com.qingqing.test.domain.pay.ThirdPayBrief;
import com.qingqing.test.service.pay.ThirdPayBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2018/2/12.
 */
@Component
public class ThirdPayBriefServiceImpl implements ThirdPayBriefService {

    @Autowired
    private ThirdPayBriefMapper thirdPayBriefMapper;

    @Override
    public List<ThirdPayBrief> selectListByOrderTypeAndOrderId(Integer orderType, Long orderId) {
        return thirdPayBriefMapper.selectListByOrderIdAndOrderType(orderType, orderId);
    }

    @Override
    public ThirdPayBrief findByQingQingTradeNo(String qingqingTradeNo) {
        return thirdPayBriefMapper.findByQingQingTradeNo(qingqingTradeNo);
    }
}
