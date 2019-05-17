package com.qingqing.test.bean.order.request;

/**
 * Created by zhujianxing on 2019/5/16.
 */
public class MadeUpClassOrderRequest {
    private Long groupOrderId;
    private Long studentId;
    private Long createAssistantId;

    public Long getGroupOrderId() {
        return groupOrderId;
    }

    public void setGroupOrderId(Long groupOrderId) {
        this.groupOrderId = groupOrderId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCreateAssistantId() {
        return createAssistantId;
    }

    public void setCreateAssistantId(Long createAssistantId) {
        this.createAssistantId = createAssistantId;
    }
}
