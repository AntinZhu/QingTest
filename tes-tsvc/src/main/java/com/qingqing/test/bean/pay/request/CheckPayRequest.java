package com.qingqing.test.bean.pay.request;

/**
 * Created by zhujianxing on 2018/8/23.
 */
public class CheckPayRequest {
    private String qingqingOrderId;
    private Integer coursePriceType;
    private Long studentId;

    public String getQingqingOrderId() {
        return qingqingOrderId;
    }

    public void setQingqingOrderId(String qingqingOrderId) {
        this.qingqingOrderId = qingqingOrderId;
    }

    public Integer getCoursePriceType() {
        return coursePriceType;
    }

    public void setCoursePriceType(Integer coursePriceType) {
        this.coursePriceType = coursePriceType;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
