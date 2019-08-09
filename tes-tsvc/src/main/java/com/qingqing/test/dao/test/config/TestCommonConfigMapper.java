package com.qingqing.test.dao.test.config;

import com.qingqing.test.domain.config.TestCommonConfig;
import org.apache.ibatis.annotations.Param;

public interface TestCommonConfigMapper {
    int insert(TestCommonConfig record);

    int update(TestCommonConfig record);

    TestCommonConfig selectById(Long id);

    TestCommonConfig selectByConfigKey(String configKey);

    int deleteById(@Param("id") Long id);
}