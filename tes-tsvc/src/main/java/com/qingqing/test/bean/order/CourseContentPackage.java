package com.qingqing.test.bean.order;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/28.
 */
public class CourseContentPackage {
    private Long packageId;
    private String packageName;
    private Integer classHour;
    private Integer classCount;
    private List<CourseOrderBean> courseOrderBeanList;

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

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public Integer getClassCount() {
        return classCount;
    }

    public void setClassCount(Integer classCount) {
        this.classCount = classCount;
    }

    public List<CourseOrderBean> getCourseOrderBeanList() {
        return courseOrderBeanList;
    }

    public void setCourseOrderBeanList(List<CourseOrderBean> courseOrderBeanList) {
        this.courseOrderBeanList = courseOrderBeanList;
    }
}
