package com.qingqing.test.service.mock.impl;

import com.qingqing.test.dao.test.mock.MockTypeMapper;
import com.qingqing.test.domain.mock.MockType;
import com.qingqing.test.service.mock.MockTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2019/10/19.
 */
@Component
public class MockTypeServiceImpl implements MockTypeService {

    @Autowired
    private MockTypeMapper mapper;

    @Override
    public List<MockType> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void insert(MockType mockType) {
        mapper.insert(mockType);
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }
}
