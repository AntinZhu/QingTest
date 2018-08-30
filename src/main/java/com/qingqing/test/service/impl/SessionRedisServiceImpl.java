package com.qingqing.test.service.impl;

import com.qingqing.common.auth.domain.User;
import com.qingqing.common.redissvc.impl.BaseRedisService;
import com.qingqing.test.service.SessionRedisService;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SessionRedisServiceImpl extends BaseRedisService implements SessionRedisService {

    private static final String user_session_key_prefix = "user_session:";

    private Integer keyExpireSeconds = 60 * 5;
    private Long sessionExpireMilliSeconds = 1000L * keyExpireSeconds;

    @Override
    public void addSession(User user, Integer sessionType, String sessionId) {
        String key = getKey(user, sessionType);
        StringRedisTemplate redisTemplate = getRedisTemplate(key);
        redisTemplate.opsForZSet().add(key, sessionId, System.currentTimeMillis() + sessionExpireMilliSeconds);
        redisTemplate.expire(key, keyExpireSeconds, TimeUnit.SECONDS);
    }


    @Override
    public Set<String> getUserSession(User user, Integer sessionType) {
        String key = getKey(user, sessionType);
        StringRedisTemplate redisTemplate = getRedisTemplate(key);
        Set<String> sessionIds = redisTemplate.opsForZSet().rangeByScore(key, System.currentTimeMillis(), Long.MAX_VALUE);
        return sessionIds;
    }

    public String getKey(User user, Integer sessionType) {
        return user_session_key_prefix + user.getUserId() + ":" + user.getUserType().getValue() + ":" + sessionType;
    }


    @Override
    public void deleteSession(User user, Integer sessionType) {
        String key = getKey(user, sessionType);
        StringRedisTemplate redisTemplate = getRedisTemplate(key);
        redisTemplate.delete(key);
    }
}