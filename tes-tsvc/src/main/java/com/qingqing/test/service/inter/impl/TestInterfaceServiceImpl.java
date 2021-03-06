package com.qingqing.test.service.inter.impl;

import com.qingqing.test.aspect.datasource.BackupDataSource;
import com.qingqing.test.aspect.datasource.ErrorCodeDataSource;
import com.qingqing.test.config.BackupSourceDataConfig;
import com.qingqing.test.dao.test.inter.TestInterfaceMapper;
import com.qingqing.test.domain.inter.TestInterface;
import com.qingqing.test.service.inter.TestInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
@Component
public class TestInterfaceServiceImpl implements TestInterfaceService{

    @Autowired
    private TestInterfaceMapper testInterfaceMapper;

    @Override
    @ErrorCodeDataSource(hintMessage = "DB罢工，不让新增接口")
    public void save(TestInterface testInterface) {
        testInterfaceMapper.insert(testInterface);
    }

    @Override
    public void update(TestInterface testInterface) {
        testInterfaceMapper.update(testInterface);
    }

    @Override
    @BackupDataSource(dateSourceBeanName = BackupSourceDataConfig.DATA_SOURCE_NAME, groupKey = "not_default")
    public TestInterface findById(Long id) {
        return testInterfaceMapper.selectById(id);
    }

    @Override
    public List<TestInterface> findAll() {
        return testInterfaceMapper.selectAll();
    }
}
