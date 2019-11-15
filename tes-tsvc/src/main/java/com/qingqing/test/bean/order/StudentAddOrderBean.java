package com.qingqing.test.bean.order;

import java.util.Date;

/**
 * Created by zhujianxing on 2018/2/7.
 */
public class StudentAddOrderBean {
    private Long studentId;
    private String qingqingTeacherId;
    private Integer gradeId;
    private Integer courseId;
    private Integer orderSiteType;
    private Date startCourseTime;
    private Integer courseTimes;
    private Integer classHour;
    private Long addressId;
    private Integer coursePriceType;

    // 优惠包ID
    private Long packageCourseId;
    // 内容包ID
    private Long contentPackageId;

    // 巩固包信息
    private Integer strengthenType;
    private Integer normalTimes;
    private Integer strengthenTimes;

    private Long servicePackageId;
    private Integer startBlock;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getQingqingTeacherId() {
        return qingqingTeacherId;
    }

    public void setQingqingTeacherId(String qingqingTeacherId) {
        this.qingqingTeacherId = qingqingTeacherId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getOrderSiteType() {
        return orderSiteType;
    }

    public void setOrderSiteType(Integer orderSiteType) {
        this.orderSiteType = orderSiteType;
    }

    public Date getStartCourseTime() {
        return startCourseTime;
    }

    public void setStartCourseTime(Date startCourseTime) {
        this.startCourseTime = startCourseTime;
    }

    public Integer getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(Integer courseTimes) {
        this.courseTimes = courseTimes;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Integer getCoursePriceType() {
        return coursePriceType;
    }

    public void setCoursePriceType(Integer coursePriceType) {
        this.coursePriceType = coursePriceType;
    }

    public Long getPackageCourseId() {
        return packageCourseId;
    }

    public void setPackageCourseId(Long packageCourseId) {
        this.packageCourseId = packageCourseId;
    }

    public Long getContentPackageId() {
        return contentPackageId;
    }

    public void setContentPackageId(Long contentPackageId) {
        this.contentPackageId = contentPackageId;
    }

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public Integer getStrengthenType() {
        return strengthenType;
    }

    public void setStrengthenType(Integer strengthenType) {
        this.strengthenType = strengthenType;
    }

    public Integer getNormalTimes() {
        return normalTimes;
    }

    public void setNormalTimes(Integer normalTimes) {
        this.normalTimes = normalTimes;
    }

    public Integer getStrengthenTimes() {
        return strengthenTimes;
    }

    public void setStrengthenTimes(Integer strengthenTimes) {
        this.strengthenTimes = strengthenTimes;
    }

    public Long getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(Long servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public Integer getStartBlock() {
        return startBlock;
    }

    public void setStartBlock(Integer startBlock) {
        this.startBlock = startBlock;
    }
}
