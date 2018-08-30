package com.qingqing.test.bean.order;

import java.util.List;

/**
 * Created by zhujianxing on 2018/2/7.
 */
public class CourseOrderBean{
    private CoursePriceType coursePriceType;
    private List<CoursePrice> supprtGradeAndSiteTypeList;

    public CourseOrderBean(){}

    public CourseOrderBean(CoursePriceType coursePriceType, List<CoursePrice> supprtGradeAndSiteTypeList) {
        this.coursePriceType = coursePriceType;
        this.supprtGradeAndSiteTypeList = supprtGradeAndSiteTypeList;
    }

    public CoursePriceType getCoursePriceType() {
        return coursePriceType;
    }

    public void setCoursePriceType(CoursePriceType coursePriceType) {
        this.coursePriceType = coursePriceType;
    }

    public List<CoursePrice> getSupprtGradeAndSiteTypeList() {
        return supprtGradeAndSiteTypeList;
    }

    public void setSupprtGradeAndSiteTypeList(List<CoursePrice> supprtGradeAndSiteTypeList) {
        this.supprtGradeAndSiteTypeList = supprtGradeAndSiteTypeList;
    }
}
