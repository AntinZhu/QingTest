package com.qingqing.test.service.user;

import com.qingqing.common.auth.domain.User;
import com.qingqing.common.auth.domain.UserType;

/**
 * Created by zhujianxing on 2018/9/30.
 */
public interface UserService {
    String encodeUser(UserType userType, Long userId);

    String getUserPhone(UserType userType, Long userId);

    User getUserByPhone(UserType userType, String phoneNumber);
}
