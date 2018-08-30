package com.qingqing.test.bean.order.response;

import com.qingqing.test.bean.base.InterfaceBaseResponse;
import com.qingqing.test.domain.order.OrderCourseV1;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/27.
 */
public class OrderCourseListResponse extends InterfaceBaseResponse {
    private List<OrderCourseV1> orderCourseV1List;

    public List<OrderCourseV1> getOrderCourseV1List() {
        return orderCourseV1List;
    }

    public void setOrderCourseV1List(List<OrderCourseV1> orderCourseV1List) {
        this.orderCourseV1List = orderCourseV1List;
    }
}
