package com.qingqing.test.bean.order;

/**
 * 优惠包
 * Created by zhujianxing on 2018/8/28.
 */
public class CoursePackage {
    private Long packageId;
    private String packageName;
    private Integer courseTimes;

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(Integer courseTimes) {
        this.courseTimes = courseTimes;
    }
}