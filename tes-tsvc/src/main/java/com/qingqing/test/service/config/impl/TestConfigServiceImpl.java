package com.qingqing.test.service.config.impl;

import com.qingqing.test.dao.test.config.TestConfigMapper;
import com.qingqing.test.domain.config.TestConfig;
import com.qingqing.test.service.config.TestConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2019/3/5.
 */
@Component
public class TestConfigServiceImpl implements TestConfigService {

    @Autowired
    private TestConfigMapper mapper;

    @Override
    public List<TestConfig> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void add(TestConfig config) {
        mapper.add(config);
    }

    @Override
    public void deletedByConfigKey(String configKey) {
        mapper.deletedByConfigKey(configKey);
    }
}
