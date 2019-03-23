package com.qingqing.test.service.inter.impl;

import com.qingqing.test.aspect.masterslave.QingReadSlaveDataSource;
import com.qingqing.test.domain.inter.CatelogRefType;
import com.qingqing.test.domain.inter.TestInterfaceCatelog;
import com.qingqing.test.service.inter.TestInterfaceCatelogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
@Component
public class TestInterfaceCatelogReadOnlyServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(TestInterfaceCatelogReadOnlyServiceImpl.class);

    @Autowired
    private TestInterfaceCatelogService targetService;

    @QingReadSlaveDataSource
    public List<TestInterfaceCatelog> selectAll() {
        return targetService.selectAll();
    }

    public TestInterfaceCatelog findById(Long id) {
        return targetService.findById(id);
    }

    public TestInterfaceCatelog selectByRefTypeAndRefValue(CatelogRefType refType, String refValue) {
        return targetService.selectByRefTypeAndRefValue(refType, refValue);
    }
}
