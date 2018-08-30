package com.qingqing.test.dao.test.inter;

import com.qingqing.test.domain.inter.TestInterface;

import java.util.List;

public interface TestInterfaceMapper {
    int insert(TestInterface record);

    TestInterface selectById(Long id);

    List<TestInterface> selectByCatelogId(Long catelogId);
}