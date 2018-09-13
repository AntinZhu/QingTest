package com.qingqing.test.bean.ordercourse.request;

/**
 * Created by zhujianxing on 2018/8/27.
 */
public class StartClassRequest {
    private Long groupOrderCourseId;
    private Long teacherId;

    public Long getGroupOrderCourseId() {
        return groupOrderCourseId;
    }

    public void setGroupOrderCourseId(Long groupOrderCourseId) {
        this.groupOrderCourseId = groupOrderCourseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
