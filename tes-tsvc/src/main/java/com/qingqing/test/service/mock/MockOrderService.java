package com.qingqing.test.service.mock;

import com.qingqing.test.bean.mock.MockOrder;

/**
 * Created by zhujianxing on 2020/5/16.
 */
public interface MockOrderService {

    int insert(MockOrder mockOrder);

    MockOrder findByQingQingOrderId(String qingqingOrderId);

}
