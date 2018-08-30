package com.qingqing.test.dao.passport;

import com.qingqing.common.auth.domain.UserType;
import com.qingqing.test.domain.UserSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Desc:  Mapper
 * author: yaoqijun
 * Date: 2017-03-21
 */
@Mapper
public interface UserSessionMapper {

    void upsert(UserSession record);

    List<UserSession> findByUser(@Param("userId") Long userId,
                                 @Param("userType") UserType userType);

    UserSession findByUserSessionType(@Param("userId") Long userId,
                                      @Param("userType") UserType userType,
                                      @Param("sessionType") Integer sessionType);
}
