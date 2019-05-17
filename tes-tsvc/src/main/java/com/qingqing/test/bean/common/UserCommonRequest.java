package com.qingqing.test.bean.common;

import com.qingqing.common.auth.domain.UserType;

/**
 * Created by zhujianxing on 2019/5/17.
 */
public class UserCommonRequest {
    private String url;
    private String param;
    private UserType userType;
    private Long userId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

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
}
