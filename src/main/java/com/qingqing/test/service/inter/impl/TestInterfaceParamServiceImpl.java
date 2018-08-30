package com.qingqing.test.service.inter.impl;

import com.qingqing.test.dao.test.inter.TestInterfaceParamMapper;
import com.qingqing.test.domain.inter.TestInterfaceParam;
import com.qingqing.test.service.inter.TestInterfaceParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
@Component
public class TestInterfaceParamServiceImpl implements TestInterfaceParamService {

    @Autowired
    private TestInterfaceParamMapper testInterfaceParamMapper;

    @Override
    public List<TestInterfaceParam> selectParamListByInterface(Long interfaceId) {
        return testInterfaceParamMapper.selectByInterfaceId(interfaceId);
    }
}
