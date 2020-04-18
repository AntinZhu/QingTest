package com.qingqing.test.dao.test.user;

import com.qingqing.test.domain.user.IpStatus;
import com.qingqing.test.domain.user.TestUserIp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestUserIpMapper {
    List<TestUserIp> selectAll();

    void insert(TestUserIp userIp);

    void updateDeleted(@Param("id") Long id, @Param("isDeleted") boolean isDeleted);

    void updateIpStatus(@Param("id") Long id, @Param("ipStatus")IpStatus ipStatus);

    int updateHeadImage(@Param("userIp") String userIp, @Param("headImage") String headImage);

    TestUserIp findById(@Param("id") Long id);
}