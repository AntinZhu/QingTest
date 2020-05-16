package com.qingqing.test.service.mock.impl;

import com.qingqing.test.bean.mock.MockOrder;
import com.qingqing.test.dao.test.mock.MockOrderMapper;
import com.qingqing.test.service.mock.MockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2020/5/16.
 */
@Component
public class MockOrderServiceImpl implements MockOrderService {

    @Autowired
    private MockOrderMapper mapper;

    @Override
    public int insert(MockOrder mockOrder) {
        return mapper.insert(mockOrder);
    }

    @Override
    public MockOrder findByQingQingOrderId(String qingqingOrderId) {
        return mapper.findByQingQingOrderId(qingqingOrderId);
    }
}
