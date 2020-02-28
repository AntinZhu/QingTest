package com.qingqing.test.bean.order;

import com.google.common.collect.Sets;
import com.qingqing.api.proto.v1.OrderCommonEnum.OrderType;
import com.qingqing.api.proto.v1.OrderCommonEnum.TeacherCoursePriceType;

import java.util.Set;

/**
 * Created by zhujianxing on 2018/2/7.
 */
public class CoursePriceType {
    public static CoursePriceType unknown = new CoursePriceType(-1, "未知", null, null);
    public static CoursePriceType genrenal = new CoursePriceType(1, "1V1", TeacherCoursePriceType.normal_course_price_type, OrderType.general_order_type);
    public static CoursePriceType group_two = new CoursePriceType(2, "二人团", TeacherCoursePriceType.group_two_teacher_course_price_type, OrderType.group_order_type);
    public static CoursePriceType group_three = new CoursePriceType(3, "三人团", TeacherCoursePriceType.group_three_teacher_course_price_type, OrderType.group_order_type);
    public static CoursePriceType group_four = new CoursePriceType(4, "四人团", TeacherCoursePriceType.group_four_teacher_course_price_type, OrderType.group_order_type);
    public static CoursePriceType group_five = new CoursePriceType(5, "五人团", TeacherCoursePriceType.group_five_teacher_course_price_type, OrderType.group_order_type);
    public static CoursePriceType package_course = new CoursePriceType(101, "优惠包", null, null);
    public static CoursePriceType content_package = new CoursePriceType(102, "内容包", null, null);
    public static CoursePriceType class_course = new CoursePriceType(103, "小组课", TeacherCoursePriceType.live_class_teacher_course_price_type, OrderType.live_class_order_type);
    public static CoursePriceType class_hour = new CoursePriceType(104, "课时包", TeacherCoursePriceType.class_hour_normal_price_type, OrderType.class_hour_order_type);

    public static Set<CoursePriceType> values = Sets.newHashSet(genrenal, group_two, group_three, group_four, group_five, class_course, class_hour);

    private CoursePriceType(Integer value, String name, TeacherCoursePriceType priceType, OrderType orderType) {
        this.value = value;
        this.name = name;
        this.priceType = priceType;
        this.orderType = orderType;
    }

    private Integer value;
    private String name;
    private TeacherCoursePriceType priceType;
    private OrderType orderType;

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public TeacherCoursePriceType getPriceType() {
        return priceType;
    }

    public static CoursePriceType valueOf(TeacherCoursePriceType priceType){
        for(CoursePriceType coursePriceType : values){
            if(coursePriceType.getPriceType() != null && coursePriceType.getPriceType().getNumber() == priceType.getNumber()){
                return coursePriceType;
            }
        }

        return unknown;
    }

    public static CoursePriceType valueOf(Integer value){
        for(CoursePriceType coursePriceType : values){
            if(coursePriceType.getValue().equals(value)){
                return coursePriceType;
            }
        }

        return unknown;
    }

    public OrderType getOrderType() {
        return orderType;
    }
}
