package com.qingqing.test.service.mock;

import com.qingqing.test.domain.mock.MockType;

import java.util.List;

/**
 * Created by zhujianxing on 2019/10/19.
 */
public interface MockTypeService {

    List<MockType> selectAll();

    void insert(MockType mockType);

    void delete(Long id);
}
