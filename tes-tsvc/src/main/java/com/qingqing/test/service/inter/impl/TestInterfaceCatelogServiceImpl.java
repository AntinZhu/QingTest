package com.qingqing.test.service.inter.impl;

import com.qingqing.test.dao.test.inter.TestInterfaceCatelogMapper;
import com.qingqing.test.domain.inter.CatelogRefType;
import com.qingqing.test.domain.inter.TestInterfaceCatelog;
import com.qingqing.test.service.inter.TestInterfaceCatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
@Component
public class TestInterfaceCatelogServiceImpl implements TestInterfaceCatelogService {

    @Autowired
    private TestInterfaceCatelogMapper testInterfaceCatelogMapper;

    @Override
    public List<TestInterfaceCatelog> selectAll() {
        return testInterfaceCatelogMapper.selectByAll();
    }

    @Override
    public TestInterfaceCatelog findById(Long id) {
        return testInterfaceCatelogMapper.findById(id);
    }

    @Override
    public void save(TestInterfaceCatelog catelog) {
        testInterfaceCatelogMapper.insert(catelog);
    }

    @Override
    public TestInterfaceCatelog selectForUpdate(Long id) {
        return testInterfaceCatelogMapper.selectForUpdate(id);
    }

    @Override
    public void incSubItemCnt(Long id) {
        testInterfaceCatelogMapper.incSubItemCnt(id);
    }

    @Override
    public void deletedById(Long id) {
        testInterfaceCatelogMapper.deleteById(id);
    }

    @Override
    public TestInterfaceCatelog selectByRefTypeAndRefValue(CatelogRefType refType, String refValue) {
        return testInterfaceCatelogMapper.selectByRefTypeAndRefValue(refType, refValue);
    }
}
