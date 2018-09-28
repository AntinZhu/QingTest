package com.qingqing.test.service.inter;

import com.qingqing.test.domain.inter.TestInterfaceCatelog;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public interface TestInterfaceCatelogService {
    List<TestInterfaceCatelog> selectAll();

    TestInterfaceCatelog findById(Long id);

    void save(TestInterfaceCatelog catelog);

    TestInterfaceCatelog selectForUpdate(Long id);

    void incDescSortNum(Long id);
}
