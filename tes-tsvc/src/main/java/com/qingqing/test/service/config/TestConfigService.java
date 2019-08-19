package com.qingqing.test.service.config;

import com.qingqing.test.domain.config.TestConfig;

import java.util.List;

/**
 * Created by zhujianxing on 2019/3/5.
 */
public interface TestConfigService {
    List<TestConfig> selectAll();

    void add(TestConfig config);

    void deletedByConfigKey(String configKey);
}
