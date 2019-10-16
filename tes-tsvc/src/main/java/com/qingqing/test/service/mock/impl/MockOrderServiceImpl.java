package com.qingqing.test.service.mock.impl;

import com.qingqing.test.bean.mock.MockOrder;
import com.qingqing.test.dao.test.mock.MockOrderMapper;
import com.qingqing.test.service.mock.MockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhujianxing on 2018/6/12.
 */
@Service
public class MockOrderServiceImpl implements MockOrderService {

    @Autowired
    private MockOrderMapper mockOrderMapper;

    @Override
    public boolean insert(MockOrder mockOrder) {
        return mockOrderMapper.insert(mockOrder) > 0;
    }

    @Override
    public MockOrder findByQingQingOrderId(String qingqingOrderId) {
        return mockOrderMapper.findByQingQingOrderId(qingqingOrderId);
    }

    @Override
    public MockOrder findById(Long id) {
        return mockOrderMapper.findById(id);
    }
}
