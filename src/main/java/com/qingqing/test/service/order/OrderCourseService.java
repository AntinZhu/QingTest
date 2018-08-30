package com.qingqing.test.service.order;

import com.qingqing.test.domain.order.OrderCourseV1;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/27.
 */
public interface OrderCourseService {

    List<OrderCourseV1> selectByOrderId(Long orderId);
}
