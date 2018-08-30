package com.qingqing.test.dao.data.ordercourse;

import com.qingqing.test.domain.order.OrderCourseV1;

import java.util.List;

public interface OrderCourseMapperV1 {

    List<OrderCourseV1> findByOrderId(Long orderId);
}
