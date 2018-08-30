package com.qingqing.test.service.impl;

import com.qingqing.common.auth.domain.User;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.test.dao.passport.UserSessionMapper;
import com.qingqing.test.domain.UserSession;
import com.qingqing.test.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yaoqijun on 2017/3/23.
 */
@Service
public class UserSessionServiceImpl implements UserSessionService {

    @Autowired
    private UserSessionMapper userSessionMapper;

    @Override
    public Long upsertSession(User user, Integer sessionType, String deviceId, String loginIp) {
        UserSession userSession = buildUserSession(user, sessionType, deviceId, loginIp);
        userSessionMapper.upsert(userSession);
        return userSession.getSessionId();
    }

    @Override
    public List<UserSession> findUser(Long userId, UserType userType) {
        return userSessionMapper.findByUser(userId, userType);
    }

    @Override
    public UserSession findUserSession(Long userId, UserType userType, Integer sessionType) {
        return userSessionMapper.findByUserSessionType(userId, userType, sessionType);
    }

    private UserSession buildUserSession(User user, Integer sessionType, String deviceId, String loginIp){
        UserSession userSession = new UserSession();
        userSession.setUserId(user.getUserId());
        userSession.setUserType(user.getUserType());
        userSession.setSessionType(sessionType);
        userSession.setLastLoginDeviceId(deviceId);
        userSession.setLastLoginIp(loginIp);
        userSession.setSessionId(generateSessionId());
        return userSession;
    }

    private Long generateSessionId(){
        return System.currentTimeMillis();
    }
}
