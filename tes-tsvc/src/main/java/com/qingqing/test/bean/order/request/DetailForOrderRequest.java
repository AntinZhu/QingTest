package com.qingqing.test.bean.order.request;

/**
 * Created by zhujianxing on 2018/8/23.
 */
public class DetailForOrderRequest {
    private Long orderId;
    private Integer coursePriceType;
    private Long teacherId;
    private Long studentId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCoursePriceType() {
        return coursePriceType;
    }

    public void setCoursePriceType(Integer coursePriceType) {
        this.coursePriceType = coursePriceType;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
