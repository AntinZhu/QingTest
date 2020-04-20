package com.qingqing.test.dao.test.config;

import com.qingqing.test.domain.config.TestConfig;

import java.util.List;

public interface TestConfigMapper {
    List<TestConfig> selectAll();

    void add(TestConfig config);

    TestConfig findById(Long id);
}