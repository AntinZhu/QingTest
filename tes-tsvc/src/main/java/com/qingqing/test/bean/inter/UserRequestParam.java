package com.qingqing.test.bean.inter;

/**
 * Created by zhujianxing on 2019/1/16.
 */
public class UserRequestParam {
    private String token;
    private String session;
    private String timestamp;
    private String authkey;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }
}
