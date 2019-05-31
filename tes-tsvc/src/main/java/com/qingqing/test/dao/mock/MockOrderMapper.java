package com.qingqing.test.dao.mock;

import com.qingqing.test.bean.mock.MockOrder;

public interface MockOrderMapper {

	int insert(MockOrder mockOrder);

	MockOrder findByQingQingOrderId(String qingqingOrderId);
}