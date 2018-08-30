package com.qingqing.test.service;

import com.qingqing.common.auth.domain.User;

import java.util.Set;

public interface SessionRedisService {

    void addSession(User user, Integer sessionType, String sessionId);

    Set<String> getUserSession(User user, Integer sessionType);

    void deleteSession(User user, Integer sessionType);
}