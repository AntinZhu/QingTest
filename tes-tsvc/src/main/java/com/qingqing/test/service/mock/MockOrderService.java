package com.qingqing.test.service.mock;


import com.qingqing.test.bean.mock.MockOrder;

/**
 * Created by zhujianxing on 2018/6/12.
 */
public interface MockOrderService {

    boolean insert(MockOrder mockOrder);

    MockOrder findByQingQingOrderId(String qingqingOrderId);

    MockOrder findById(Long id);
}
