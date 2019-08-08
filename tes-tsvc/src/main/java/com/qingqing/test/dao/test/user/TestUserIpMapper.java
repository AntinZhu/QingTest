package com.qingqing.test.dao.test.user;

import com.qingqing.test.domain.user.TestUserIp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestUserIpMapper {
    List<TestUserIp> selectAll();

    int updateHeadImage(@Param("userIp") String userIp, @Param("headImage") String headImage);
}