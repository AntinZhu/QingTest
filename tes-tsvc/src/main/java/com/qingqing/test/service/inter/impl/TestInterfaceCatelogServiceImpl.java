package com.qingqing.test.service.inter.impl;

import com.qingqing.test.aspect.datasource.BackupDataSource;
import com.qingqing.test.aspect.datasource.EmptyDataSource;
import com.qingqing.test.config.BackupSourceDataConfig;
import com.qingqing.test.dao.test.inter.TestInterfaceCatelogMapper;
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
public class TestInterfaceCatelogServiceImpl implements TestInterfaceCatelogService {

    private static final Logger logger = LoggerFactory.getLogger(TestInterfaceCatelogServiceImpl.class);

    @Autowired
    private TestInterfaceCatelogMapper testInterfaceCatelogMapper;

    @Override
    @BackupDataSource(dateSourceBeanName = BackupSourceDataConfig.DATA_SOURCE_NAME)
//    @EmptyDataSource(isList = true)
    public List<TestInterfaceCatelog> selectAll() {
//        logger.error("query from selectAll");
        return testInterfaceCatelogMapper.selectByAll();
    }

    @Override
    @BackupDataSource(dateSourceBeanName = BackupSourceDataConfig.DATA_SOURCE_NAME)
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
    @EmptyDataSource(groupKey = "not_default")
    public TestInterfaceCatelog selectByRefTypeAndRefValue(CatelogRefType refType, String refValue) {
        return testInterfaceCatelogMapper.selectByRefTypeAndRefValue(refType, refValue);
    }
}
