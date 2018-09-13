package com.qingqing.test.domain;

import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.util.StringUtils;

import java.util.Date;

/**
 * Desc:  bean
 * author: yaoqijun
 * Date: 2017-03-21
 */
public class UserSession {

    private Long id;

    private Long userId;

    private UserType userType;

    // mobile = 1, web=2, 3,4跳过(push在用), 官网=5
    private Integer sessionType;

    private Long sessionId;

    // 最后登录设备号
    private String lastLoginDeviceId;

    // 最后登录ip
    private String lastLoginIp;

    private Date createTime;

    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Integer getSessionType() {
        return sessionType;
    }

    public void setSessionType(Integer sessionType) {
        this.sessionType = sessionType;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getLastLoginDeviceId() {
        return lastLoginDeviceId;
    }

    public void setLastLoginDeviceId(String lastLoginDeviceId) {
        this.lastLoginDeviceId = lastLoginDeviceId;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
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

    public boolean isUserIpUnChanged(String ip){
        return StringUtils.notNullTrim(getLastLoginIp()).equals(StringUtils.notNullTrim(ip));
    }

    public boolean isDeviceIdUnChanged(String deviceId) {
        return StringUtils.notNullTrim(getLastLoginDeviceId()).equalsIgnoreCase(StringUtils.notNullTrim(deviceId));
    }
}