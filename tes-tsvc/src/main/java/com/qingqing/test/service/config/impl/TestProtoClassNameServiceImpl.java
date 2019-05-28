package com.qingqing.test.service.config.impl;

import com.qingqing.test.dao.test.config.TestProtoClassNameMapper;
import com.qingqing.test.domain.config.TestProtoClassName;
import com.qingqing.test.service.config.TestProtoClassNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2019/5/28.
 */
@Component
public class TestProtoClassNameServiceImpl implements TestProtoClassNameService {

    @Autowired
    private TestProtoClassNameMapper mapper;

    @Override
    public List<TestProtoClassName> selectAll() {
        return mapper.selectAll();
    }
}
