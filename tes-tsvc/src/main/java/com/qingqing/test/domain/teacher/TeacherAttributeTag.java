package com.qingqing.test.domain.teacher;

import com.qingqing.common.auth.domain.UserType;

import java.util.Date;

public class TeacherAttributeTag {

	private Long id;
	private Long teacherId;
	private String tagType;
	private String tagValue;
	private String tagStatus;
	private Date createTime;
	private Date lastUpdateTime;
	private Date startEffectTime;

	private Long operateUserId;
	private UserType operateUserType;
	private Integer operateUserRoleType;

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
	public String getTagValue() {
		return tagValue;
	}
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Date getStartEffectTime() {
		return startEffectTime;
	}
	public void setStartEffectTime(Date startEffectTime) {
		this.startEffectTime = startEffectTime;
	}

	public Long getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}

	public UserType getOperateUserType() {
		return operateUserType;
	}

	public void setOperateUserType(UserType operateUserType) {
		this.operateUserType = operateUserType;
	}

	public Integer getOperateUserRoleType() {
		return operateUserRoleType;
	}

	public void setOperateUserRoleType(Integer operateUserRoleType) {
		this.operateUserRoleType = operateUserRoleType;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTagStatus() {
		return tagStatus;
	}

	public void setTagStatus(String tagStatus) {
		this.tagStatus = tagStatus;
	}
}
