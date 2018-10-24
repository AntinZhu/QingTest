package com.qingqing.test.bean.common;

import com.qingqing.common.auth.domain.UserType;

/**
 * Created by zhujianxing on 2018/10/24.
 */
public class UserWithDataBean {

    private UserType userType;
    private String data;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
