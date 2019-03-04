package com.qingqing.test.domain.phone;

import com.qingqing.common.auth.domain.UserType;

/**
 * Created by zhujianxing on 2019/3/1.
 */
public class UserAndPhoneNumber {
    private UserType userType;
    private Long userId;
    private String phoneNumber;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
