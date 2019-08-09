package com.qingqing.test.service.config.impl;

import com.qingqing.test.dao.test.config.TestCommonConfigMapper;
import com.qingqing.test.domain.config.TestCommonConfig;
import com.qingqing.test.service.config.TestCommonConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2019/8/8.
 */
@Component
public class TestCommonConfigServiceImpl implements TestCommonConfigService {

    @Autowired
    private TestCommonConfigMapper mapper;

    @Override
    public int insert(TestCommonConfig record) {
        return mapper.insert(record);
    }

    @Override
    public int update(TestCommonConfig record) {
        return mapper.update(record);
    }

    @Override
    public TestCommonConfig selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public TestCommonConfig selectByConfigKey(String configKey) {
        return mapper.selectByConfigKey(configKey);
    }

    @Override
    public boolean deleteById(Long id) {
        return mapper.deleteById(id) > 0;
    }
}
