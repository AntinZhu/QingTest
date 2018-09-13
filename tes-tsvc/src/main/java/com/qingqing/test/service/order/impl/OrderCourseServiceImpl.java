package com.qingqing.test.service.order.impl;

import com.qingqing.test.bean.common.response.ListResponse;
import com.qingqing.test.client.ApiPiClient;
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
    private ApiPiClient apiPiClient;

    @Override
    public List<OrderCourseV1> selectByOrderId(Long orderId) {
        ListResponse<OrderCourseV1> response = apiPiClient.getOrderCourseList(orderId);
        return response.getResultList();
    }
}
