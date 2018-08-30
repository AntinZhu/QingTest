package com.qingqing.test.service;

import com.qingqing.common.auth.domain.User;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.test.domain.UserSession;

import java.util.List;

/**
 * Created by yaoqijun on 2017/3/23.
 */
public interface UserSessionService {

    Long upsertSession(User user, Integer sessionType, String deviceId, String loginIp);

    List<UserSession> findUser(Long userId, UserType userType);

    UserSession findUserSession(Long userId, UserType userType, Integer sessionType);
}
