package com.qingqing.test.bean.order;

import com.google.common.collect.Sets;
import com.qingqing.api.proto.v1.OrderCommonEnum;

import java.util.Set;

public class OrderSiteType {
    public static final OrderSiteType unknown = new OrderSiteType(-1, "未知", OrderCommonEnum.OrderSiteType.unknown_ost);
    public static final OrderSiteType student_home = new OrderSiteType(0, "老师上门", OrderCommonEnum.OrderSiteType.student_home_ost);
    public static final OrderSiteType teacher_home = new OrderSiteType(1, "家长上门", OrderCommonEnum.OrderSiteType.teacher_home_ost);
    public static final OrderSiteType live = new OrderSiteType(3, "在线上课", OrderCommonEnum.OrderSiteType.live_ost);

    public static Set<OrderSiteType> values = Sets.newHashSet(student_home, teacher_home, live);

    private Integer value;
    private String name;
    private OrderCommonEnum.OrderSiteType siteTypeProto;

    public OrderSiteType(Integer value, String name, OrderCommonEnum.OrderSiteType siteTypeProto) {
        this.value = value;
        this.name = name;
        this.siteTypeProto = siteTypeProto;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public OrderCommonEnum.OrderSiteType getSiteTypeProto() {
        return siteTypeProto;
    }

    public static OrderSiteType valueOf(Integer value){
        for(OrderSiteType siteType : OrderSiteType.values){
            if(siteType.getValue().equals(value)){
                return siteType;
            }
        }

        return unknown;
    }
}
