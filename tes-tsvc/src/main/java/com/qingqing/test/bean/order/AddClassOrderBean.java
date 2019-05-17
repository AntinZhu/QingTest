package com.qingqing.test.bean.order;

import java.util.Date;
import java.util.List;

/**
 * Created by zhujianxing on 2018/2/7.
 */
public class AddClassOrderBean {
    private Long teacherId;
    private Integer gradeId;
    private Integer courseId;
    private String className;
    private String courseDesc;
    private String timeDesc;
    private Double price;
    private int minStudentCnt;
    private int maxStudentCnt;
    private Date startCourseTime;
    private Integer courseTimes;
    private Integer classHour;
    private List<Integer> publishCityIds;
    private Long createAssistantId;
    private Integer textCategoryId;
    private Long studentId;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getTimeDesc() {
        return timeDesc;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getMinStudentCnt() {
        return minStudentCnt;
    }

    public void setMinStudentCnt(int minStudentCnt) {
        this.minStudentCnt = minStudentCnt;
    }

    public int getMaxStudentCnt() {
        return maxStudentCnt;
    }

    public void setMaxStudentCnt(int maxStudentCnt) {
        this.maxStudentCnt = maxStudentCnt;
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

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public List<Integer> getPublishCityIds() {
        return publishCityIds;
    }

    public void setPublishCityIds(List<Integer> publishCityIds) {
        this.publishCityIds = publishCityIds;
    }

    public Long getCreateAssistantId() {
        return createAssistantId;
    }

    public void setCreateAssistantId(Long createAssistantId) {
        this.createAssistantId = createAssistantId;
    }

    public Integer getTextCategoryId() {
        return textCategoryId;
    }

    public void setTextCategoryId(Integer textCategoryId) {
        this.textCategoryId = textCategoryId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
