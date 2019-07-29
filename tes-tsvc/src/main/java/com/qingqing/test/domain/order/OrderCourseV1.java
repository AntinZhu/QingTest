package com.qingqing.test.domain.order;

import com.qingqing.common.util.CollectionsUtil;

import java.util.Date;
import java.util.List;

public class OrderCourseV1{

    private Long id;
    private Long orderId;//group_sub_order_id
    /**
     * 实际上课时间
     */
    private Date startTime;
    /**
     * 实际下课时间
     */
    private Date endTime;
    /**
     * 预计上课时间
     */
    private Date startCourseTime;
    /**
     * 预计下课时间
     */
    private Date endCourseTime;
    /**
     * 家长结课时间
     */
    private Date finishClassTime;
    private String siteType;

    /**
     * 上课方式为老师上门时，才需要，表示学生地址Id
     */
    private Long studentAddressId;

    private String status;

    private String chargeMode;
    // 真实价格(含税)
    private Double realPrice;
    // 真是价格（不含税）
    private Double realPriceWithoutTax;
    private Long teacherId;
    private Long studentId;
    private Date createTime;
    /**
     * 老师填写课堂反馈时间
     */
    private Integer discountRate;
    private String orderType;
    private Long groupOrderId;
    private Long groupOrderCourseId;
    private Date cancelTime;
    private Double resetPrice;    //改价后折扣前的本课程价格
    private Integer courseId;
    private String priceType;
    private List<FreezeType> freezeTypes;
    private Integer startBlock;
    private Integer endBlock;
    private Date date;
    private Long assistantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartCourseTime() {
        return startCourseTime;
    }

    public void setStartCourseTime(Date startCourseTime) {
        this.startCourseTime = startCourseTime;
    }

    public Date getEndCourseTime() {
        return endCourseTime;
    }

    public void setEndCourseTime(Date endCourseTime) {
        this.endCourseTime = endCourseTime;
    }

    public Date getFinishClassTime() {
        return finishClassTime;
    }

    public void setFinishClassTime(Date finishClassTime) {
        this.finishClassTime = finishClassTime;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public Long getStudentAddressId() {
        return studentAddressId;
    }

    public void setStudentAddressId(Long studentAddressId) {
        this.studentAddressId = studentAddressId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(String chargeMode) {
        this.chargeMode = chargeMode;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Double getRealPriceWithoutTax() {
        return realPriceWithoutTax;
    }

    public void setRealPriceWithoutTax(Double realPriceWithoutTax) {
        this.realPriceWithoutTax = realPriceWithoutTax;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Double getResetPrice() {
        return resetPrice;
    }

    public void setResetPrice(Double resetPrice) {
        this.resetPrice = resetPrice;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public List<FreezeType> getFreezeTypes() {
        return freezeTypes;
    }

    public void setFreezeTypes(List<FreezeType> freezeTypes) {
        this.freezeTypes = freezeTypes;
    }

    public Integer getFreeze() {
        if(CollectionsUtil.isNullOrEmpty(freezeTypes)){
            return 0;
        }

        Integer freeze = 0;
        for(FreezeType freezeValue : freezeTypes){
            freeze += freezeValue.getValue();
        }

        return freeze;
    }

    public Integer getStartBlock() {
        return startBlock;
    }

    public void setStartBlock(Integer startBlock) {
        this.startBlock = startBlock;
    }

    public Integer getEndBlock() {
        return endBlock;
    }

    public void setEndBlock(Integer endBlock) {
        this.endBlock = endBlock;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Long assistantId) {
        this.assistantId = assistantId;
    }
}
