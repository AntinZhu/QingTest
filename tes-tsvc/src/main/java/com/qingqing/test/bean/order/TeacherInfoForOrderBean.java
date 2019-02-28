package com.qingqing.test.bean.order;

import com.qingqing.test.bean.base.InterfaceBaseResponse;
import com.qingqing.test.bean.base.KeyAndValue;

import java.util.List;

public class TeacherInfoForOrderBean extends InterfaceBaseResponse {
    private String qingqingTeacherId;
    private List<KeyAndValue> supportCourseList;
    private List<CourseOrderBean> courseOrderList;
    private List<CoursePackage> coursePackageList;
    private List<CourseContentPackage> courseContentPackageList;
    private List<StrengthenPackage> strengthenPackageList;
    private ServicePackage servicePackage;

    public String getQingqingTeacherId() {
        return qingqingTeacherId;
    }

    public void setQingqingTeacherId(String qingqingTeacherId) {
        this.qingqingTeacherId = qingqingTeacherId;
    }

    public List<KeyAndValue> getSupportCourseList() {
        return supportCourseList;
    }

    public void setSupportCourseList(List<KeyAndValue> supportCourseList) {
        this.supportCourseList = supportCourseList;
    }

    public List<CourseOrderBean> getCourseOrderList() {
        return courseOrderList;
    }

    public void setCourseOrderList(List<CourseOrderBean> courseOrderList) {
        this.courseOrderList = courseOrderList;
    }

    public List<CoursePackage> getCoursePackageList() {
        return coursePackageList;
    }

    public void setCoursePackageList(List<CoursePackage> coursePackageList) {
        this.coursePackageList = coursePackageList;
    }

    public List<CourseContentPackage> getCourseContentPackageList() {
        return courseContentPackageList;
    }

    public void setCourseContentPackageList(List<CourseContentPackage> courseContentPackageList) {
        this.courseContentPackageList = courseContentPackageList;
    }

    public List<StrengthenPackage> getStrengthenPackageList() {
        return strengthenPackageList;
    }

    public void setStrengthenPackageList(List<StrengthenPackage> strengthenPackageList) {
        this.strengthenPackageList = strengthenPackageList;
    }

    public ServicePackage getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(ServicePackage servicePackage) {
        this.servicePackage = servicePackage;
    }
}
