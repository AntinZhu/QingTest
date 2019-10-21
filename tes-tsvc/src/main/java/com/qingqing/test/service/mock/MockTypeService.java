package com.qingqing.test.service.mock;

import com.qingqing.test.domain.mock.MockType;

import java.util.List;

/**
 * Created by zhujianxing on 2019/10/19.
 */
public interface MockTypeService {

    List<MockType> selectAll();

    MockType findById(Long id);

    MockType findByMockType(String mockType);

    void insert(MockType mockType);

    void update(MockType mockType);

    void delete(Long id);
}
