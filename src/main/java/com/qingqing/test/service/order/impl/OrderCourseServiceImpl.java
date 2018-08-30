package com.qingqing.test.service.order.impl;

import com.qingqing.test.dao.data.ordercourse.OrderCourseMapperV1;
import com.qingqing.test.domain.order.OrderCourseV1;
import com.qingqing.test.service.order.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/27.
 */
@Component
public class OrderCourseServiceImpl implements OrderCourseService {

    @Autowired
    private OrderCourseMapperV1 orderCourseMapperV1;

    @Override
    public List<OrderCourseV1> selectByOrderId(Long orderId) {
        return orderCourseMapperV1.findByOrderId(orderId);
    }
}
