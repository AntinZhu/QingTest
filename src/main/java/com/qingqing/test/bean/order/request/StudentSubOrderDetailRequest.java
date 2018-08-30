package com.qingqing.test.bean.order.request;

/**
 * Created by zhujianxing on 2018/8/27.
 */
public class StudentSubOrderDetailRequest {
    private String qingqingOrderId;
    private Long studentId;

    public String getQingqingOrderId() {
        return qingqingOrderId;
    }

    public void setQingqingOrderId(String qingqingOrderId) {
        this.qingqingOrderId = qingqingOrderId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
