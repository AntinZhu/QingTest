package com.qingqing.test.dao.test.inter;

import com.qingqing.test.domain.inter.TestInterfaceCatelog;

import java.util.List;

public interface TestInterfaceCatelogMapper {
    int insert(TestInterfaceCatelog record);

    List<TestInterfaceCatelog> selectByAll();
}