package com.qingqing.test.dao.test.inter;

import com.qingqing.test.domain.inter.TestInterfaceCatelog;

import java.util.List;

public interface TestInterfaceCatelogMapper {
    int insert(TestInterfaceCatelog record);

    List<TestInterfaceCatelog> selectByAll();

    TestInterfaceCatelog findById(Long id);

    TestInterfaceCatelog selectForUpdate(Long id);

    void incSortDescNum(Long id);
}