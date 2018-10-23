package com.qingqing.test.service.inter.impl;

import com.qingqing.test.dao.test.inter.TestInterfaceParamMapper;
import com.qingqing.test.domain.inter.TestInterfaceParam;
import com.qingqing.test.service.inter.TestInterfaceParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2018/10/23.
 */
@Component
public class TestInterfaceParamServiceImpl implements TestInterfaceParamService {

    @Autowired
    private TestInterfaceParamMapper testInterfaceParamMapper;

    @Override
    public void save(TestInterfaceParam testInterface) {
        testInterfaceParamMapper.insert(testInterface);
    }

    @Override
    public TestInterfaceParam findById(Long id) {
        return testInterfaceParamMapper.selectById(id);
    }

    @Override
    public List<TestInterfaceParam> selectListByInterfaceId(Long interfaceId) {
        return testInterfaceParamMapper.selectByInterfaceId(interfaceId);
    }
}
