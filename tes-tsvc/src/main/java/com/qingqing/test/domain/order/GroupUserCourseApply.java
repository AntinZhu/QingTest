package com.qingqing.test.domain.order;

import com.qingqing.common.auth.domain.UserType;

import java.util.Date;

/**
 * Created by yangzirong on 1/19/2017.
 */
public class GroupUserCourseApply {

    private Long id;
    private Long teacherId;
    private Long groupOrderId;
    private Long groupOrderCourseId;
    private Long orderCourseId;
    private Long teacherAssistantId; // nullable
    private Long applyUserId;
    private UserType applyUserType;
    private Integer applyReasonType;
    private Long handleUserId;
    private UserType handleUserType;
    private Date handleTime;
    private String applyType;
    private String applyStatus;
    private String applyExtraReason;
    private Date createTime;
    private String handleComment;
    private Long batchApplyId;

    //6.1 退调课
    private String responsibilityType;  //调退课责任人

    private Boolean isUrgent;           //是否紧急

    private Boolean needCalcPenalty;    //是否需要进行罚金计算

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getGroupOrderId() {
        return groupOrderId;
    }

    public void setGroupOrderId(Long groupOrderId) {
        this.groupOrderId = groupOrderId;
    }

    public Long getGroupOrderCourseId() {
        return groupOrderCourseId;
    }

    public void setGroupOrderCourseId(Long groupOrderCourseId) {
        this.groupOrderCourseId = groupOrderCourseId;
    }

    public Long getOrderCourseId() {
        return orderCourseId;
    }

    public void setOrderCourseId(Long orderCourseId) {
        this.orderCourseId = orderCourseId;
    }

    public Long getTeacherAssistantId() {
        return teacherAssistantId;
    }

    public void setTeacherAssistantId(Long teacherAssistantId) {
        this.teacherAssistantId = teacherAssistantId;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public UserType getApplyUserType() {
        return applyUserType;
    }

    public void setApplyUserType(UserType applyUserType) {
        this.applyUserType = applyUserType;
    }

    public Integer getApplyReasonType() {
        return applyReasonType;
    }

    public void setApplyReasonType(Integer applyReasonType) {
        this.applyReasonType = applyReasonType;
    }

    public Long getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(Long handleUserId) {
        this.handleUserId = handleUserId;
    }

    public UserType getHandleUserType() {
        return handleUserType;
    }

    public void setHandleUserType(UserType handleUserType) {
        this.handleUserType = handleUserType;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyExtraReason() {
        return applyExtraReason;
    }

    public void setApplyExtraReason(String applyExtraReason) {
        this.applyExtraReason = applyExtraReason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHandleComment() {
        return handleComment;
    }

    public void setHandleComment(String handleComment) {
        this.handleComment = handleComment;
    }

    public Long getBatchApplyId() {
        return batchApplyId;
    }

    public void setBatchApplyId(Long batchApplyId) {
        this.batchApplyId = batchApplyId;
    }

    public String getResponsibilityType() {
        return responsibilityType;
    }

    public void setResponsibilityType(String responsibilityType) {
        this.responsibilityType = responsibilityType;
    }

    public Boolean getUrgent() {
        return isUrgent;
    }

    public void setUrgent(Boolean urgent) {
        isUrgent = urgent;
    }

    public Boolean getNeedCalcPenalty() {
        return needCalcPenalty;
    }

    public void setNeedCalcPenalty(Boolean needCalcPenalty) {
        this.needCalcPenalty = needCalcPenalty;
    }
}
