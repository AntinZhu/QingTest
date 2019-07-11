package com.qingqing.test.bean.teacher;

/**
 * Created by zhujianxing on 2019/7/11.
 */
public class SetTeacherTagRequest {
    private Long teacherId;
    private String tagType;
    private String tagValue;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }
}
