package com.qingqing.test.bean.orderservice;

/**
 * Created by zhujianxing on 2020/6/15.
 */
public class TestStudentAssistantInfo {
    private Long studentId;
    private BelongDepartmentInfo qingqing ;
    private BelongDepartmentInfo online;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public BelongDepartmentInfo getQingqing() {
        return qingqing;
    }

    public void setQingqing(BelongDepartmentInfo qingqing) {
        this.qingqing = qingqing;
    }

    public BelongDepartmentInfo getOnline() {
        return online;
    }

    public void setOnline(BelongDepartmentInfo online) {
        this.online = online;
    }

    public static final class BelongDepartmentInfo{
        private Long department;
        private boolean isQingQing;
        private boolean isOnline;

        public Long getDepartment() {
            return department;
        }

        public void setDepartment(Long department) {
            this.department = department;
        }

        public boolean isQingQing() {
            return isQingQing;
        }

        public void setQingQing(boolean qingQing) {
            isQingQing = qingQing;
        }

        public boolean isOnline() {
            return isOnline;
        }

        public void setOnline(boolean online) {
            isOnline = online;
        }
    }
}
