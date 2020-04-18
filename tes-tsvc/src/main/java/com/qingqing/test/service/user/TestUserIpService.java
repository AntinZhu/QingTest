package com.qingqing.test.service.user;

import com.qingqing.test.domain.user.IpStatus;
import com.qingqing.test.domain.user.TestUserIp;

import java.util.List;

/**
 * Created by zhujianxing on 2019/7/8.
 */
public interface TestUserIpService {

    List<TestUserIp> selectAll();

    void insert(TestUserIp userIp);

    TestUserIp findById(Long id);

    void updateDeleted(Long id, boolean isDeleted);

    void updateIpStatus(Long id, IpStatus ipStatus);

    boolean updateUserHeadImage(String userIp, String headImage);
}
