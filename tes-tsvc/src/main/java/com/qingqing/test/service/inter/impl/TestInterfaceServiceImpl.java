package com.qingqing.test.service.inter.impl;

import com.qingqing.test.dao.test.inter.TestInterfaceMapper;
import com.qingqing.test.domain.inter.TestInterface;
import com.qingqing.test.service.inter.TestInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2018/8/30.
 */
@Component
public class TestInterfaceServiceImpl implements TestInterfaceService{

    @Autowired
    private TestInterfaceMapper testInterfaceMapper;

    @Override
    public void save(TestInterface testInterface) {
        testInterfaceMapper.insert(testInterface);
    }

    @Override
    public void update(TestInterface testInterface) {
        testInterfaceMapper.update(testInterface);
    }

    @Override
    public TestInterface findById(Long id) {
        return testInterfaceMapper.selectById(id);
    }
}
