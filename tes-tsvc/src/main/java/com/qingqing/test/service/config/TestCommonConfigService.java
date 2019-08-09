package com.qingqing.test.service.config;

import com.qingqing.test.domain.config.TestCommonConfig;

/**
 * Created by zhujianxing on 2019/8/8.
 */
public interface TestCommonConfigService {

    int insert(TestCommonConfig record);

    int update(TestCommonConfig record);

    TestCommonConfig selectById(Long id);

    TestCommonConfig selectByConfigKey(String configKey);

    boolean deleteById(Long id);
}
